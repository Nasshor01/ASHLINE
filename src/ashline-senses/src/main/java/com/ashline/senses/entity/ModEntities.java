package com.ashline.senses.entity;

import com.ashline.senses.AshlineSenses;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModEntities {
    public static final DeferredRegister<EntityType<?>> REGISTER =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, AshlineSenses.MODID);

    public static final RegistryObject<EntityType<PebbleEntity>> PEBBLE = REGISTER.register("pebble",
            () -> EntityType.Builder.<PebbleEntity>of(PebbleEntity::new, MobCategory.MISC)
                    .sized(0.25f, 0.25f)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("pebble"));

    private ModEntities() {}
}
