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
        PPRegistry.ALL_PLATES.forEach(plateEntry -> {
            Registry.register(BuiltInRegistries.BLOCK, plateEntry.id(), plateEntry.block());
            Registry.register(BuiltInRegistries.ITEM, plateEntry.id(), plateEntry.item());
        });

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CREATIVE_TAB_KEY, CUSTOM_CREATIVE_TAB);
    }

    public static final ResourceKey<CreativeModeTab> CREATIVE_TAB_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), PlayerPlates.CREATIVE_TAB_ID);

    public static final CreativeModeTab CUSTOM_CREATIVE_TAB = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(PPRegistry.OBSIDIAN_PLATE.item()))
            .title(Component.translatable("itemGroup.playerplates.player_plates"))
            .displayItems((params, output) -> {
                PPRegistry.TAB_ITEMS.forEach(item -> output.accept(item.getDefaultInstance()));
            })
            .build();
}

