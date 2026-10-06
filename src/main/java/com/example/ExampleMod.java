package com.example;

import net.fabricmc.api.ModInitializer;
import net.minecraft.world.item.Item;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

public class ExampleMod implements ModInitializer {

    public static final String MOD_ID = "modid";

    private static final Identifier RECALL_STONE_ID =
        Identifier.fromNamespaceAndPath(MOD_ID, "recall_stone");
    private static final ResourceKey<Item> RECALL_STONE_KEY =
        ResourceKey.create(Registries.ITEM, RECALL_STONE_ID);

    public static final Item RECALL_STONE =
        new RecallStoneItem(new Item.Properties().setId(RECALL_STONE_KEY).stacksTo(1));

    @Override
    public void onInitialize() {

        Registry.register(
            BuiltInRegistries.ITEM,
            RECALL_STONE_KEY,
            RECALL_STONE
        );
    }
}
