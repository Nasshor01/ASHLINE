package com.ashline.senses.item;

import com.ashline.senses.entity.PebbleEntity;
import com.ashline.senses.sound.SoundLureManager;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PebbleItem extends Item {
    public PebbleItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.4f, 0.6f);
        if (!level.isClientSide) {
            SoundLureManager.markPlayerSound(player);
            PebbleEntity pebble = new PebbleEntity(level, player);
            pebble.setItem(stack);
            pebble.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0f, 1.15f, 1.0f);
            level.addFreshEntity(pebble);
        }
        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }
}
