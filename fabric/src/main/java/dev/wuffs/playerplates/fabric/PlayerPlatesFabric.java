package dev.wuffs.playerplates.fabric;

import dev.wuffs.playerplates.PPRegistry;
import dev.wuffs.playerplates.PlayerPlates;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ItemLore;

import java.util.List;

public class PlayerPlatesFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Registry.register(BuiltInRegistries.BLOCK, PPRegistry.OBSIDIAN_PLATE_ID, PPRegistry.OBSIDIAN_PLATE_BLOCK);
        Registry.register(BuiltInRegistries.BLOCK, PPRegistry.INVISIBLE_OBSIDIAN_PLATE_ID, PPRegistry.INVISIBLE_OBSIDIAN_PLATE_BLOCK);
        Registry.register(BuiltInRegistries.BLOCK, PPRegistry.MOSSY_PLATE_ID, PPRegistry.MOSSY_PLATE_BLOCK);
        Registry.register(BuiltInRegistries.BLOCK, PPRegistry.INVISIBLE_MOSSY_PLATE_ID, PPRegistry.INVISIBLE_MOSSY_PLATE_BLOCK);

        Registry.register(BuiltInRegistries.ITEM, PPRegistry.OBSIDIAN_PLATE_ID, PPRegistry.OBSIDIAN_PLATE_ITEM);
        Registry.register(BuiltInRegistries.ITEM, PPRegistry.INVISIBLE_OBSIDIAN_PLATE_ID, PPRegistry.INVISIBLE_OBSIDIAN_PLATE_ITEM);
        Registry.register(BuiltInRegistries.ITEM, PPRegistry.MOSSY_PLATE_ID, PPRegistry.MOSSY_PLATE_ITEM);
        Registry.register(BuiltInRegistries.ITEM, PPRegistry.INVISIBLE_MOSSY_PLATE_ID, PPRegistry.INVISIBLE_MOSSY_PLATE_ITEM);

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CREATIVE_TAB_KEY, CUSTOM_CREATIVE_TAB);
    }

    public static final ResourceKey<CreativeModeTab> CREATIVE_TAB_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), PlayerPlates.CREATIVE_TAB_ID);

    public static final CreativeModeTab CUSTOM_CREATIVE_TAB = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(PPRegistry.OBSIDIAN_PLATE_ITEM))
            .title(Component.translatable("itemGroup.playerplates.player_plates"))
            .displayItems((params, output) -> {
                PPRegistry.TAB_ITEMS.forEach(item -> output.accept(item.getDefaultInstance()));
            })
            .build();
}

