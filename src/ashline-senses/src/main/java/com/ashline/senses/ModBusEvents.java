package com.ashline.senses;

import com.ashline.senses.item.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AshlineSenses.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModBusEvents {
    private ModBusEvents() {}

    @SubscribeEvent
    public static void creative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(ModItems.PEBBLE);
        }
    }
}
