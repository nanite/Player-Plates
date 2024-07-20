package dev.wuffs.fabric;

import dev.wuffs.playerplates.PPRegistry;
import dev.wuffs.playerplates.PlayerPlates;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class RecipeGenerator extends FabricRecipeProvider {
    public RecipeGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void buildRecipes(RecipeOutput exporter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, PPRegistry.OBSIDIAN_PLATE_ITEM.get())
                .unlockedBy("has_obsidian", has(Items.OBSIDIAN))
                .pattern("OO")
                .define('O', Items.OBSIDIAN)
                .save(exporter, ResourceLocation.fromNamespaceAndPath(PlayerPlates.MOD_ID, "obsidian_plate"));

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, PPRegistry.INVISIBLE_OBSIDIAN_PLATE_ITEM.get())
                .unlockedBy("has_obsidian", has(Items.OBSIDIAN))
                .pattern("GO")
                .define('O', Items.OBSIDIAN)
                .define('G', Items.GLASS_PANE)
                .save(exporter, ResourceLocation.fromNamespaceAndPath(PlayerPlates.MOD_ID, "invisible_obsidian_plate"));

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, PPRegistry.MOSSY_PLATE_ITEM.get())
                .unlockedBy("has_mossycobble", has(Items.MOSSY_COBBLESTONE))
                .pattern("MM")
                .define('M', Items.MOSSY_COBBLESTONE)
                .save(exporter, ResourceLocation.fromNamespaceAndPath(PlayerPlates.MOD_ID, "mossy_plate"));

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, PPRegistry.INVISIBLE_MOSSY_PLATE_ITEM.get())
                .unlockedBy("has_mossycobble", has(Items.MOSSY_COBBLESTONE))
                .pattern("GM")
                .define('M', Items.MOSSY_COBBLESTONE)
                .define('G', Items.GLASS_PANE)
                .save(exporter, ResourceLocation.fromNamespaceAndPath(PlayerPlates.MOD_ID, "invisible_mossy_plate"));
    }
}