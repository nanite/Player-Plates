package dev.wuffs.playerplates.data;

import dev.wuffs.playerplates.data.lang.ENUSLangGenerator;
import dev.wuffs.playerplates.data.lang.PLPLLangGenerator;
import dev.wuffs.playerplates.data.lang.ZHCNLangGenerator;
import dev.wuffs.playerplates.data.lang.ZHTWLangGenerator;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class DataGens implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(RecipeGenerator::new);
        pack.addProvider(ModelGenerator::new);

        // Lang Gen
        pack.addProvider(ENUSLangGenerator::new);
        pack.addProvider(PLPLLangGenerator::new);
        pack.addProvider(ZHCNLangGenerator::new);
        pack.addProvider(ZHTWLangGenerator::new);
    }
}