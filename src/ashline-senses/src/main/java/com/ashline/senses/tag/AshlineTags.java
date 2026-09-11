package com.ashline.senses.tag;

import com.ashline.senses.AshlineSenses;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public final class AshlineTags {
    /** Shared pack tag — datapack and this mod both contribute values. */
    public static final TagKey<EntityType<?>> INFECTED = TagKey.create(
            Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath("ashline", "infected"));

    public static final ResourceLocation MOD_PACK =
            ResourceLocation.fromNamespaceAndPath(AshlineSenses.MODID, "infected");

    private AshlineTags() {}
}
