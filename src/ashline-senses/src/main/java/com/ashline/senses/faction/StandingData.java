package com.ashline.senses.faction;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Per-player faction standing, clamped to [-1000, 1000].
 * Stored on overworld SavedData so it survives dimension travel.
 */
public class StandingData extends SavedData {
    public static final int MIN = -1000;
    public static final int MAX = 1000;
    private static final String NAME = "ashline_standing";

    private final Map<UUID, EnumMap<FactionId, Integer>> players = new HashMap<>();

    public static StandingData get(MinecraftServer server) {
        ServerLevel overworld = server.overworld();
        return overworld.getDataStorage().computeIfAbsent(StandingData::load, StandingData::new, NAME);
    }

    public static StandingData load(CompoundTag tag) {
        StandingData data = new StandingData();
        ListTag list = tag.getList("players", Tag.TAG_COMPOUND);
        for (int i = 0; i < list.size(); i++) {
            CompoundTag entry = list.getCompound(i);
            UUID id = entry.getUUID("uuid");
            EnumMap<FactionId, Integer> map = new EnumMap<>(FactionId.class);
            CompoundTag standing = entry.getCompound("standing");
            for (FactionId f : FactionId.values()) {
                if (standing.contains(f.id())) {
                    map.put(f, clamp(standing.getInt(f.id())));
                }
            }
            data.players.put(id, map);
        }
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        ListTag list = new ListTag();
        for (Map.Entry<UUID, EnumMap<FactionId, Integer>> e : players.entrySet()) {
            CompoundTag entry = new CompoundTag();
            entry.putUUID("uuid", e.getKey());
            CompoundTag standing = new CompoundTag();
            for (Map.Entry<FactionId, Integer> s : e.getValue().entrySet()) {
                standing.putInt(s.getKey().id(), s.getValue());
            }
            entry.put("standing", standing);
            list.add(entry);
        }
        tag.put("players", list);
        return tag;
    }

    public int get(UUID player, FactionId faction) {
        EnumMap<FactionId, Integer> map = players.get(player);
        if (map == null) {
            return 0;
        }
        return map.getOrDefault(faction, 0);
    }

    public int set(UUID player, FactionId faction, int value) {
        int clamped = clamp(value);
        players.computeIfAbsent(player, u -> new EnumMap<>(FactionId.class)).put(faction, clamped);
        setDirty();
        return clamped;
    }

    public int add(UUID player, FactionId faction, int delta) {
        return set(player, faction, get(player, faction) + delta);
    }

    public static int clamp(int v) {
        return Math.max(MIN, Math.min(MAX, v));
    }
}
