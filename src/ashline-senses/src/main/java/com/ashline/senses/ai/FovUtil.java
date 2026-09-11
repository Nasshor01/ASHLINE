package com.ashline.senses.ai;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public final class FovUtil {
    private FovUtil() {}

    /**
     * Horizontal cone check using mob yRot. Pitch to target above 60 degrees is ignored (not a look).
     */
    public static boolean isInFov(LivingEntity mob, LivingEntity target, double fovDegrees) {
        Vec3 toTarget = target.getEyePosition().subtract(mob.getEyePosition());
        double horiz = Math.sqrt(toTarget.x * toTarget.x + toTarget.z * toTarget.z);
        if (horiz < 1.0E-6) {
            return true;
        }
        double pitchDeg = Math.toDegrees(Math.atan2(-toTarget.y, horiz));
        if (Math.abs(pitchDeg) > 60.0) {
            return false;
        }
        Vec3 look = Vec3.directionFromRotation(0.0f, mob.getYRot());
        Vec3 flat = new Vec3(toTarget.x, 0.0, toTarget.z).normalize();
        double dot = look.x * flat.x + look.z * flat.z;
        double angle = Math.toDegrees(Math.acos(Mth.clamp(dot, -1.0, 1.0)));
        return angle <= fovDegrees * 0.5;
    }
}
