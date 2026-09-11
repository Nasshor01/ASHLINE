package com.ashline.senses.ai;

import com.ashline.senses.sound.SoundLureManager;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

/** Pathfinds to lure position, never to the player. Priority should sit above RandomStroll, below HurtBy. */
public class InvestigateSoundGoal extends Goal {
    private final PathfinderMob mob;
    private BlockPos target;
    private int recalc;

    public InvestigateSoundGoal(PathfinderMob mob) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        SoundLureManager.SoundLure lure = SoundLureManager.findNearest(mob);
        if (lure == null) {
            return false;
        }
        this.target = lure.pos();
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        return canUse() && !mob.getNavigation().isDone();
    }

    @Override
    public void start() {
        move();
    }

    @Override
    public void tick() {
        if (--recalc <= 0) {
            recalc = 10;
            SoundLureManager.SoundLure lure = SoundLureManager.findNearest(mob);
            if (lure != null) {
                target = lure.pos();
                move();
            }
        }
    }

    @Override
    public void stop() {
        target = null;
        mob.getNavigation().stop();
    }

    private void move() {
        if (target != null) {
            mob.getNavigation().moveTo(target.getX() + 0.5, target.getY(), target.getZ() + 0.5, 1.05);
        }
    }
}
