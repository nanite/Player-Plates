package dev.wuffs.playerplates.data.lang;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class ZHCNLangGenerator extends FabricLanguageProvider {
    public ZHCNLangGenerator(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(packOutput, "zn_ch", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add("itemGroup.playerplates.player_plates", "Player Plates");

        translationBuilder.add("block.playerplates.obsidian_plate", "黑曜石压力板");
        translationBuilder.add("block.playerplates.invisible_obsidian_plate", "黑曜石压力板（隐形）");

        translationBuilder.add("block.playerplates.mossy_plate", "苔石压力板");
        translationBuilder.add("block.playerplates.invisible_mossy_plate", "苔石压力板（隐形）");

        translationBuilder.add("block.playerplates.item_plate", "Item Pressure Plate");
        translationBuilder.add("block.playerplates.invisible_item_plate", "Invisible Item Pressure Plate");

        translationBuilder.add("block.playerplates.passive_mob_plate", "Passive Mob Pressure Plate");
        translationBuilder.add("block.playerplates.invisible_passive_mob_plate", "Invisible Passive Mob Pressure Plate");

    }
}
