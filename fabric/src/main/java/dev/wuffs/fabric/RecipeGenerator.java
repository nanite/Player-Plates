package dev.wuffs.fabric;

import dev.wuffs.playerplates.PlayerPlates;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.concurrent.CompletableFuture;

public class RecipeGenerator extends FabricRecipeProvider {
    public RecipeGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(
            final HolderLookup.Provider provider,
            final RecipeOutput recipeOutput
    ) {
        return new RecipesProvider(provider, recipeOutput);
    }

    @Override
    public String getName() {
        return PlayerPlates.MOD_ID + " recipes";
    }
}