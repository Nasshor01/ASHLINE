package com.ashline.senses;

import com.ashline.senses.ai.FovUtil;
import com.ashline.senses.ai.InvestigateSoundGoal;
import com.ashline.senses.config.SensesConfig;
import com.ashline.senses.sound.SoundLureManager;
import com.ashline.senses.tag.AshlineTags;
import com.ashline.senses.world.BuildingTracker;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class SensesEvents {
    private SensesEvents() {}

    @SubscribeEvent
    public static void onJoin(EntityJoinLevelEvent event) {
        if (event.getLevel().isClientSide()) {
            return;
        }
        if (event.getEntity() instanceof PathfinderMob mob && mob.getType().is(AshlineTags.INFECTED)) {
            mob.goalSelector.addGoal(2, new InvestigateSoundGoal(mob));
            if (event.getLevel() instanceof ServerLevel server) {
                BuildingTracker.get(server).addInfected(server, mob.blockPosition());
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onChangeTarget(LivingChangeTargetEvent event) {
        // Forge 1.20.1: only block MOB_TARGET acquisition (not command/revenge plumbing).
        if (event.getTargetType() != LivingChangeTargetEvent.LivingTargetType.MOB_TARGET) {
            return;
        }
        LivingEntity mob = event.getEntity();
        if (mob.level().isClientSide()) {
            return;
        }
        if (!mob.getType().is(AshlineTags.INFECTED)) {
            return;
        }
        LivingEntity newTarget = event.getNewTarget();
        if (!(newTarget instanceof Player player)) {
            return;
        }
        if (mob.getLastHurtByMob() == player) {
            return;
        }
        double fov = SensesConfig.FOV_DEGREES.get();
        if (!FovUtil.isInFov(mob, player, fov)
                && !SoundLureManager.playerMadeSoundNear(player, mob, 40)) {
            event.setNewTarget(null);
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onDeath(LivingDeathEvent event) {
        if (!(event.getEntity().level() instanceof ServerLevel server)) {
            return;
        }
        if (!event.getEntity().getType().is(AshlineTags.INFECTED)) {
            return;
        }
        BuildingTracker.get(server).onDeathInBox(server, event.getEntity().blockPosition());
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            SoundLureManager.tick(event.getServer());
        }
    }

    @SubscribeEvent
    public static void onLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        SoundLureManager.clearPlayer(event.getEntity().getUUID());
    }
}
