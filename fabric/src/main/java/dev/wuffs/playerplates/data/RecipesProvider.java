package dev.wuffs.playerplates.data;

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

    protected RecipesProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
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
                .define('O', PPRegistry.OBSIDIAN_PLATE_ITEM.get())
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
                .define('M', PPRegistry.MOSSY_PLATE_ITEM.get())
                .define('G', Items.GLASS_PANE)
                .save(output, "invisible_mossy_plate");

        ShapedRecipeBuilder.shaped(itemHolderGetter, RecipeCategory.REDSTONE, PPRegistry.ITEM_PLATE_ITEM.get())
                .unlockedBy("has_copper", has(Items.COPPER_BLOCK))
                .pattern("CC")
                .define('C', Items.COPPER_BLOCK)
                .save(output, "item_plate");

        ShapedRecipeBuilder.shaped(itemHolderGetter, RecipeCategory.REDSTONE, PPRegistry.INVISIBLE_ITEM_PLATE_ITEM.get())
                .unlockedBy("has_copper", has(Items.COPPER_BLOCK))
                .pattern("GC")
                .define('C',  PPRegistry.ITEM_PLATE_ITEM.get())
                .define('G', Items.GLASS_PANE)
                .save(output, "invisible_item_plate");

        ShapedRecipeBuilder.shaped(itemHolderGetter, RecipeCategory.REDSTONE, PPRegistry.PASSIVE_MOB_PLATE_ITEM.get())
                .unlockedBy("has_moss", has(Items.MOSS_BLOCK))
                .pattern("MM")
                .define('M', Items.MOSS_BLOCK)
                .save(output, "passive_mob_plate");

        ShapedRecipeBuilder.shaped(itemHolderGetter, RecipeCategory.REDSTONE, PPRegistry.INVISIBLE_PASSIVE_MOB_PLATE_ITEM.get())
                .unlockedBy("has_moss", has(Items.MOSS_BLOCK))
                .pattern("GM")
                .define('M', PPRegistry.PASSIVE_MOB_PLATE_ITEM.get())
                .define('G', Items.GLASS_PANE)
                .save(output, "invisible_passive_mob_plate");
    }
}
