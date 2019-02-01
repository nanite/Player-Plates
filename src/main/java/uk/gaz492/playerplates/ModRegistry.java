package uk.gaz492.playerplates;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import uk.gaz492.playerplates.blocks.BlockPlayerPlates;
import uk.gaz492.playerplates.util.ModInfo;

@GameRegistry.ObjectHolder(ModInfo.MOD_ID)
@Mod.EventBusSubscriber(modid = ModInfo.MOD_ID)
public class ModRegistry {

    public static final Block OBSIDIAN_PLATE = Blocks.AIR;
    public static final Block INVISIBLE_OBSIDIAN_PLATE = Blocks.AIR;

    public static final Block MOSSY_PLATE = Blocks.AIR;
    public static final Block INVISIBLE_MOSSY_PLATE = Blocks.AIR;


    private static Block plateReg(Block block, String name, float hardness, float resistance) {
        block.setCreativeTab(PlayerPlates.creativeTab);
        block.setRegistryName(name);
        block.setHardness(hardness);
        block.setResistance(resistance);
        block.setUnlocalizedName(ModInfo.MOD_ID + "." + name);

        return block;
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(plateReg(new BlockPlayerPlates(BlockPlayerPlates.Sensitivity.PLAYER, false), "obsidian_plate", 50.0f, 6000.0f));
        event.getRegistry().register(plateReg(new BlockPlayerPlates(BlockPlayerPlates.Sensitivity.ITEMS_MOB, false), "mossy_plate", 1.0f, 1.0f));

        // Invisible Pressure Plates
        if (ConfigHandler.general.enableAllInvisiblePlates) {
            event.getRegistry().register(plateReg(new BlockPlayerPlates(BlockPlayerPlates.Sensitivity.PLAYER, true), "invisible_obsidian_plate", 50.0f, 6000.0f));
            event.getRegistry().register(plateReg(new BlockPlayerPlates(BlockPlayerPlates.Sensitivity.ITEMS_MOB, true), "invisible_mossy_plate", 1.0f, 1.0f));
        }
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemBlock(OBSIDIAN_PLATE).setRegistryName("obsidian_plate"));
        event.getRegistry().register(new ItemBlock(MOSSY_PLATE).setRegistryName("mossy_plate"));

        // Invisible pressure plates
        if (ConfigHandler.general.enableAllInvisiblePlates) {
            event.getRegistry().register(new ItemBlock(INVISIBLE_OBSIDIAN_PLATE).setRegistryName("invisible_obsidian_plate"));
            event.getRegistry().register(new ItemBlock(INVISIBLE_MOSSY_PLATE).setRegistryName("invisible_mossy_plate"));
        }
    }


    public static void addModel(Block block, String variant) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), variant));
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        addModel(OBSIDIAN_PLATE, "inventory");
        addModel(MOSSY_PLATE, "inventory");

        if (ConfigHandler.general.enableAllInvisiblePlates) {
            addModel(INVISIBLE_OBSIDIAN_PLATE, "inventory");
            addModel(INVISIBLE_MOSSY_PLATE, "inventory");
        }
    }
}
