package com.ashline.senses.config;

import net.minecraftforge.common.ForgeConfigSpec;

public final class SensesConfig {
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.DoubleValue FOV_DEGREES;
    public static final ForgeConfigSpec.IntValue DAY_SIGHT;
    public static final ForgeConfigSpec.IntValue NIGHT_SIGHT;
    public static final ForgeConfigSpec.IntValue DARK_SIGHT;
    public static final ForgeConfigSpec.IntValue LURE_TICK_INTERVAL;

    static {
        ForgeConfigSpec.Builder b = new ForgeConfigSpec.Builder();
        b.push("senses");
        FOV_DEGREES = b.comment("Horizontal FOV cone in degrees. Never 360.")
                .defineInRange("fovDegrees", 100.0, 40.0, 160.0);
        DAY_SIGHT = b.defineInRange("daySightRange", 20, 4, 64);
        NIGHT_SIGHT = b.defineInRange("nightSightRange", 10, 2, 48);
        DARK_SIGHT = b.defineInRange("darkCrouchSightRange", 6, 1, 24);
        LURE_TICK_INTERVAL = b.comment("How often lures re-apply investigate goals.")
                .defineInRange("lureTickInterval", 10, 1, 40);
        b.pop();
        SPEC = b.build();
    }

    private SensesConfig() {}
}
