package dev.wuffs.playerplates.data;

import dev.wuffs.playerplates.PPRegistry;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class ModelGenerator extends FabricModelProvider {

    public ModelGenerator(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        registerPressurePlate(blockModelGenerators, PPRegistry.OBSIDIAN_PLATE.block(), Blocks.OBSIDIAN);
        registerPressurePlate(blockModelGenerators, PPRegistry.INVISIBLE_OBSIDIAN_PLATE.block(), Blocks.OBSIDIAN);

        registerPressurePlate(blockModelGenerators, PPRegistry.MOSSY_PLATE.block(), Blocks.MOSSY_COBBLESTONE);
        registerPressurePlate(blockModelGenerators, PPRegistry.INVISIBLE_MOSSY_PLATE.block(), Blocks.MOSSY_COBBLESTONE);

        registerPressurePlate(blockModelGenerators, PPRegistry.ITEM_PLATE.block(), Blocks.COPPER_BLOCK);
        registerPressurePlate(blockModelGenerators, PPRegistry.INVISIBLE_ITEM_PLATE.block(), Blocks.COPPER_BLOCK);

        registerPressurePlate(blockModelGenerators, PPRegistry.PASSIVE_MOB_PLATE.block(), Blocks.MOSS_BLOCK);
        registerPressurePlate(blockModelGenerators, PPRegistry.INVISIBLE_PASSIVE_MOB_PLATE.block(), Blocks.MOSS_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {

    }

    public static void registerPressurePlate(BlockModelGenerators generator, Block block, Block textureBlock) {
        MultiVariant off = BlockModelGenerators.plainVariant(ModelTemplates.PRESSURE_PLATE_UP.create(block, TextureMapping.defaultTexture(textureBlock), generator.modelOutput));
        MultiVariant on = BlockModelGenerators.plainVariant(ModelTemplates.PRESSURE_PLATE_DOWN.create(block,TextureMapping.defaultTexture(textureBlock), generator.modelOutput));
        generator.blockStateOutput.accept(BlockModelGenerators.createPressurePlate(block, off, on));
    }
}