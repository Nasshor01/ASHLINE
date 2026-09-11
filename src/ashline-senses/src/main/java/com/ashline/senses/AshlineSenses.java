package com.ashline.senses;

import com.ashline.senses.command.AshlineCommands;
import com.ashline.senses.config.SensesConfig;
import com.ashline.senses.entity.ModEntities;
import com.ashline.senses.faction.FactionId;
import com.ashline.senses.faction.StandingData;
import com.ashline.senses.item.ModItems;
import com.ashline.senses.sound.LureSource;
import com.ashline.senses.sound.SoundLureManager;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.UUID;

/**
 * Public API for other ASHLINE mods (vehicles, guns, factions) to emit noise and read standing.
 */
@Mod(AshlineSenses.MODID)
public class AshlineSenses {
    public static final String MODID = "ashline_senses";

    public AshlineSenses() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.REGISTER.register(modBus);
        ModEntities.REGISTER.register(modBus);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SensesConfig.SPEC);
        MinecraftForge.EVENT_BUS.register(SensesEvents.class);
        MinecraftForge.EVENT_BUS.addListener(this::onRegisterCommands);
    }

    /** Bible API: noise 0–100, radius in blocks. */
    public static void emitSound(Level level, BlockPos pos, int noise, int radius, LureSource source) {
        int duration = SoundLureManager.durationForNoise(noise);
        SoundLureManager.emit(level, pos, noise, radius, duration, source);
    }

    public static int getStanding(MinecraftServer server, UUID player, FactionId faction) {
        return StandingData.get(server).get(player, faction);
    }

    public static int addStanding(MinecraftServer server, UUID player, FactionId faction, int delta) {
        return StandingData.get(server).add(player, faction, delta);
    }

    public static int setStanding(MinecraftServer server, UUID player, FactionId faction, int value) {
        return StandingData.get(server).set(player, faction, value);
    }

    private void onRegisterCommands(RegisterCommandsEvent event) {
        AshlineCommands.register(event.getDispatcher());
    }
}
