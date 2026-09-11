package com.ashline.senses.sound;

import com.ashline.senses.config.SensesConfig;
import com.ashline.senses.tag.AshlineTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class SoundLureManager {
    private static final List<SoundLure> LURES = new ArrayList<>();
    private static final Map<UUID, Long> PLAYER_SOUND_GAME_TIME = new ConcurrentHashMap<>();

    private SoundLureManager() {}

    public static int durationForNoise(int noise) {
        if (noise >= 90) {
            return 160;
        }
        if (noise >= 60) {
            return 140;
        }
        return 120;
    }

    public static void emit(Level level, BlockPos pos, int noise, int radius, int durationTicks, LureSource source) {
        if (level.isClientSide() || !(level instanceof ServerLevel server)) {
            return;
        }
        LURES.add(new SoundLure(server.dimension(), pos.immutable(), noise, radius, durationTicks, source,
                source == LureSource.PLAYER ? findNearestPlayer(server, pos) : null));
        if (source == LureSource.PLAYER) {
            UUID owner = findNearestPlayer(server, pos);
            if (owner != null) {
                PLAYER_SOUND_GAME_TIME.put(owner, server.getGameTime());
            }
        }
    }

    public static void markPlayerSound(Player player) {
        if (player.level() instanceof ServerLevel server) {
            PLAYER_SOUND_GAME_TIME.put(player.getUUID(), server.getGameTime());
        }
    }

    public static boolean playerMadeSoundNear(Player player, LivingEntity mob, int rangeBlocks) {
        Long t = PLAYER_SOUND_GAME_TIME.get(player.getUUID());
        if (t == null || !(mob.level() instanceof ServerLevel server)) {
            return false;
        }
        if (server.getGameTime() - t > 40) {
            return false;
        }
        return mob.distanceToSqr(player) <= (double) rangeBlocks * rangeBlocks;
    }

    public static SoundLure findNearest(PathfinderMob mob) {
        if (!(mob.level() instanceof ServerLevel server)) {
            return null;
        }
        SoundLure best = null;
        double bestD = Double.MAX_VALUE;
        for (SoundLure lure : LURES) {
            if (!lure.dimension().equals(server.dimension())) {
                continue;
            }
            double d = mob.distanceToSqr(lure.pos().getX() + 0.5, lure.pos().getY() + 0.5, lure.pos().getZ() + 0.5);
            double r = lure.radius();
            if (d <= r * r && d < bestD) {
                best = lure;
                bestD = d;
            }
        }
        return best;
    }

    public static void tick(MinecraftServer server) {
        int interval = SensesConfig.LURE_TICK_INTERVAL.get();
        if (server.getTickCount() % interval != 0) {
            return;
        }
        Iterator<SoundLure> it = LURES.iterator();
        while (it.hasNext()) {
            SoundLure lure = it.next();
            lure.ticksLeft--;
            if (lure.ticksLeft <= 0) {
                it.remove();
                continue;
            }
            ServerLevel level = server.getLevel(lure.dimension());
            if (level == null) {
                continue;
            }
            AABB box = new AABB(lure.pos()).inflate(lure.radius());
            List<PathfinderMob> infected = level.getEntitiesOfClass(PathfinderMob.class, box,
                    m -> m.isAlive() && m.getType().is(AshlineTags.INFECTED));
            for (PathfinderMob mob : infected) {
                if (mob.getNavigation().isDone()) {
                    mob.getNavigation().moveTo(lure.pos().getX() + 0.5, lure.pos().getY(), lure.pos().getZ() + 0.5, 1.05);
                }
            }
        }
    }

    public static void clearPlayer(UUID id) {
        PLAYER_SOUND_GAME_TIME.remove(id);
    }

    private static UUID findNearestPlayer(ServerLevel level, BlockPos pos) {
        Player p = level.getNearestPlayer(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 8.0, false);
        return p == null ? null : p.getUUID();
    }

    public static final class SoundLure {
        private final net.minecraft.resources.ResourceKey<Level> dimension;
        private final BlockPos pos;
        private final int noise;
        private final int radius;
        private int ticksLeft;
        private final LureSource source;
        private final UUID playerId;

        private SoundLure(net.minecraft.resources.ResourceKey<Level> dimension, BlockPos pos, int noise, int radius,
                          int durationTicks, LureSource source, UUID playerId) {
            this.dimension = dimension;
            this.pos = pos;
            this.noise = noise;
            this.radius = radius;
            this.ticksLeft = durationTicks;
            this.source = source;
            this.playerId = playerId;
        }

        public net.minecraft.resources.ResourceKey<Level> dimension() {
            return dimension;
        }

        public BlockPos pos() {
            return pos;
        }

        public int noise() {
            return noise;
        }

        public int radius() {
            return radius;
        }

        public LureSource source() {
            return source;
        }

        public UUID playerId() {
            return playerId;
        }
    }
}
