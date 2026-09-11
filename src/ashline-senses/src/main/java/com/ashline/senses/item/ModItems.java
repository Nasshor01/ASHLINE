package com.ashline.senses.item;

import com.ashline.senses.AshlineSenses;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModItems {
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, AshlineSenses.MODID);

    public static final RegistryObject<Item> PEBBLE = REGISTER.register("pebble",
            () -> new PebbleItem(new Item.Properties().stacksTo(16)));

    private ModItems() {}
}
