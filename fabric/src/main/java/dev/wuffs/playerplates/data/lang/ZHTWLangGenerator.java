package dev.wuffs.playerplates.data.lang;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class ZHTWLangGenerator extends FabricLanguageProvider {
    public ZHTWLangGenerator(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(packOutput, "zn_tw", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add("itemGroup.playerplates.player_plates", "玩家壓力板");

        translationBuilder.add("block.playerplates.obsidian_plate", "黑曜石壓力板");
        translationBuilder.add("block.playerplates.invisible_obsidian_plate", "黑曜石壓力板（隱形）");

        translationBuilder.add("block.playerplates.mossy_plate", "青苔鵝卵石壓力板");
        translationBuilder.add("block.playerplates.invisible_mossy_plate", "青苔鵝卵石壓力板（隱形）");

        translationBuilder.add("block.playerplates.item_plate", "Item Pressure Plate");
        translationBuilder.add("block.playerplates.invisible_item_plate", "Invisible Item Pressure Plate");

        translationBuilder.add("block.playerplates.passive_mob_plate", "Passive Mob Pressure Plate");
        translationBuilder.add("block.playerplates.invisible_passive_mob_plate", "Invisible Passive Mob Pressure Plate");

    }
}
