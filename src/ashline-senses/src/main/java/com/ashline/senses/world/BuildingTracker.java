package com.ashline.senses.world;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.phys.AABB;

import java.util.HashMap;
import java.util.Map;

/**
 * Persists remaining infected + sealed flag per structure start (chunkpos + structure id).
 */
public class BuildingTracker extends SavedData {
    private static final String NAME = "ashline_buildings";
    private final Map<String, BuildingState> buildings = new HashMap<>();

    public static BuildingTracker get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(BuildingTracker::load, BuildingTracker::new, NAME);
    }

    public static BuildingTracker load(CompoundTag tag) {
        BuildingTracker data = new BuildingTracker();
        ListTag list = tag.getList("buildings", Tag.TAG_COMPOUND);
        for (int i = 0; i < list.size(); i++) {
            CompoundTag n = list.getCompound(i);
            BuildingState s = new BuildingState();
            s.remaining = n.getInt("remaining");
            s.sealed = n.getBoolean("sealed");
            s.initialized = n.getBoolean("initialized");
            data.buildings.put(n.getString("key"), s);
        }
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        ListTag list = new ListTag();
        for (Map.Entry<String, BuildingState> e : buildings.entrySet()) {
            CompoundTag n = new CompoundTag();
            n.putString("key", e.getKey());
            n.putInt("remaining", e.getValue().remaining);
            n.putBoolean("sealed", e.getValue().sealed);
            n.putBoolean("initialized", e.getValue().initialized);
            list.add(n);
        }
        tag.put("buildings", list);
        return tag;
    }

    public void addInfected(ServerLevel level, BlockPos pos) {
        StructureHit hit = find(level, pos);
        if (hit == null) {
            return;
        }
        BuildingState state = buildings.computeIfAbsent(hit.key(), k -> new BuildingState());
        if (!state.initialized) {
            state.remaining = countInfected(level, hit.start());
            state.initialized = true;
        } else {
            state.remaining++;
        }
        setDirty();
    }

    public void onDeathInBox(ServerLevel level, BlockPos pos) {
        StructureHit hit = find(level, pos);
        if (hit == null) {
            return;
        }
        BuildingState state = buildings.computeIfAbsent(hit.key(), k -> new BuildingState());
        if (!state.initialized) {
            state.remaining = Math.max(0, countInfected(level, hit.start()));
            state.initialized = true;
        } else {
            state.remaining = Math.max(0, state.remaining - 1);
        }
        setDirty();
    }

    public int getRemaining(ServerLevel level, BlockPos pos) {
        StructureHit hit = find(level, pos);
        if (hit == null) {
            return -1;
        }
        BuildingState state = buildings.get(hit.key());
        if (state == null || !state.initialized) {
            int c = countInfected(level, hit.start());
            BuildingState s = new BuildingState();
            s.remaining = c;
            s.initialized = true;
            buildings.put(hit.key(), s);
            setDirty();
            return c;
        }
        return state.remaining;
    }

    public boolean setSealed(ServerLevel level, BlockPos pos, boolean sealed) {
        StructureHit hit = find(level, pos);
        if (hit == null) {
            return false;
        }
        BuildingState state = buildings.computeIfAbsent(hit.key(), k -> new BuildingState());
        if (!state.initialized) {
            state.remaining = countInfected(level, hit.start());
            state.initialized = true;
        }
        state.sealed = sealed;
        setDirty();
        return true;
    }

    public boolean isSealed(ServerLevel level, BlockPos pos) {
        StructureHit hit = find(level, pos);
        if (hit == null) {
            return false;
        }
        BuildingState state = buildings.get(hit.key());
        return state != null && state.sealed;
    }

    public String describe(ServerLevel level, BlockPos pos) {
        StructureHit hit = find(level, pos);
        if (hit == null) {
            return "no structure";
        }
        BuildingState state = buildings.get(hit.key());
        int rem = getRemaining(level, pos);
        boolean sealed = state != null && state.sealed;
        return hit.key() + " remaining=" + rem + " sealed=" + sealed;
    }

    private static int countInfected(ServerLevel level, StructureStart start) {
        AABB box = AABB.of(start.getBoundingBox());
        return level.getEntitiesOfClass(net.minecraft.world.entity.Entity.class, box,
                e -> e.getType().is(com.ashline.senses.tag.AshlineTags.INFECTED)).size();
    }

    private static StructureHit find(ServerLevel level, BlockPos pos) {
        var starts = level.getChunk(pos).getAllStarts();
        for (Map.Entry<Structure, StructureStart> e : starts.entrySet()) {
            StructureStart start = e.getValue();
            if (start != null && start.isValid() && start.getBoundingBox().isInside(pos)) {
                ResourceLocation id = level.registryAccess().registryOrThrow(Registries.STRUCTURE).getKey(e.getKey());
                ChunkPos cp = start.getChunkPos();
                String key = (id == null ? "unknown" : id.toString()) + "@" + cp.x + "," + cp.z;
                return new StructureHit(key, start);
            }
        }
        return null;
    }

    private record StructureHit(String key, StructureStart start) {}

    private static final class BuildingState {
        int remaining;
        boolean sealed;
        boolean initialized;
    }
}
