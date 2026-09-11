package com.ashline.senses.entity;

import com.ashline.senses.AshlineSenses;
import com.ashline.senses.item.ModItems;
import com.ashline.senses.sound.LureSource;
import com.ashline.senses.sound.SoundLureManager;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class PebbleEntity extends ThrowableItemProjectile {
    public PebbleEntity(EntityType<? extends PebbleEntity> type, Level level) {
        super(type, level);
    }

    public PebbleEntity(Level level, LivingEntity shooter) {
        super(ModEntities.PEBBLE.get(), shooter, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.PEBBLE.get();
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        // No damage — lure only.
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!level().isClientSide) {
            BlockPos pos = BlockPos.containing(result.getLocation());
            // Bible: pebble noise 35 / radius 24 via public API
            AshlineSenses.emitSound(level(), pos, 35, 24, LureSource.PLAYER);
            if (getOwner() instanceof Player player) {
                SoundLureManager.markPlayerSound(player);
            }
            discard();
        }
    }
}
