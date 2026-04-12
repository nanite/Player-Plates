package dev.wuffs.playerplates.data.lang;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class ENUSLangGenerator extends FabricLanguageProvider {
    public ENUSLangGenerator(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(packOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add("itemGroup.playerplates.player_plates", "Player Plates");

        translationBuilder.add("block.playerplates.obsidian_plate", "Obsidian Pressure Plate");
        translationBuilder.add("block.playerplates.invisible_obsidian_plate", "Invisible Obsidian Pressure Plate");

        translationBuilder.add("block.playerplates.mossy_plate", "Mossy Cobblestone Pressure Plate");
        translationBuilder.add("block.playerplates.invisible_mossy_plate", "Invisible Mossy Cobblestone Pressure Plate");

        translationBuilder.add("block.playerplates.item_plate", "Copper Pressure Plate");
        translationBuilder.add("block.playerplates.invisible_item_plate", "Invisible Copper Pressure Plate");

        translationBuilder.add("block.playerplates.passive_mob_plate", "Mossy Pressure Plate");
        translationBuilder.add("block.playerplates.invisible_passive_mob_plate", "Invisible Mossy Mob Pressure Plate");

    }
}
