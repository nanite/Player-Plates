package dev.wuffs.fabric;

import dev.wuffs.playerplates.PPRegistry;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class RecipesProvider extends RecipeProvider {
    private final HolderGetter<Item> itemHolderGetter;

    protected RecipesProvider(
            final HolderLookup.Provider provider,
            final RecipeOutput recipeOutput
    ) {
        super(provider, recipeOutput);
        this.itemHolderGetter = registries.lookupOrThrow(Registries.ITEM);
    }

    @Override
    public void buildRecipes() {
        ShapedRecipeBuilder.shaped(itemHolderGetter, RecipeCategory.REDSTONE, PPRegistry.OBSIDIAN_PLATE_ITEM.get())
                .unlockedBy("has_obsidian", has(Items.OBSIDIAN))
                .pattern("OO")
                .define('O', Items.OBSIDIAN)
                .save(output, "obsidian_plate");

        ShapedRecipeBuilder.shaped(itemHolderGetter, RecipeCategory.REDSTONE, PPRegistry.INVISIBLE_OBSIDIAN_PLATE_ITEM.get())
                .unlockedBy("has_obsidian", has(Items.OBSIDIAN))
                .pattern("GO")
                .define('O', Items.OBSIDIAN)
                .define('G', Items.GLASS_PANE)
                .save(output, "invisible_obsidian_plate");

        ShapedRecipeBuilder.shaped(itemHolderGetter, RecipeCategory.REDSTONE, PPRegistry.MOSSY_PLATE_ITEM.get())
                .unlockedBy("has_mossycobble", has(Items.MOSSY_COBBLESTONE))
                .pattern("MM")
                .define('M', Items.MOSSY_COBBLESTONE)
                .save(output, "mossy_plate");

        ShapedRecipeBuilder.shaped(itemHolderGetter, RecipeCategory.REDSTONE, PPRegistry.INVISIBLE_MOSSY_PLATE_ITEM.get())
                .unlockedBy("has_mossycobble", has(Items.MOSSY_COBBLESTONE))
                .pattern("GM")
                .define('M', Items.MOSSY_COBBLESTONE)
                .define('G', Items.GLASS_PANE)
                .save(output, "invisible_mossy_plate");
    }
}
