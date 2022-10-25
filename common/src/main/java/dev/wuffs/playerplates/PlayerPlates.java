package dev.wuffs.playerplates;

import com.google.common.base.Suppliers;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registries;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class PlayerPlates {
    public static final String MOD_ID = "playerplates";
    // Registering a new creative tab
    public static final CreativeModeTab CREATIVE_TAB = CreativeTabRegistry.create(new ResourceLocation(MOD_ID, MOD_ID), () ->
            new ItemStack(PPRegistry.OBSIDIAN_PLATE_ITEM.get()));
    public static void init() {
        PPRegistry.BLOCKS.register();
        PPRegistry.ITEMS.register();
    }
}
