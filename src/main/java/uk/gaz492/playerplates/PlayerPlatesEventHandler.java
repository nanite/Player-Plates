package uk.gaz492.playerplates;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import uk.gaz492.playerplates.blocks.PlayerPlateBlock;

import static uk.gaz492.playerplates.util.ModInfo.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus=Mod.EventBusSubscriber.Bus.MOD)
public class PlayerPlatesEventHandler {

    public static final Block OBSIDIAN_PLATE = new PlayerPlateBlock(PlayerPlateBlock.Sensitivity.PLAYER, false,50.0f, 6000.0f);
    public static final Block INVISIBLE_OBSIDIAN_PLATE = new PlayerPlateBlock(PlayerPlateBlock.Sensitivity.PLAYER, true,50.0f, 6000.0f);

    public static final Block MOSSY_PLATE = new PlayerPlateBlock(PlayerPlateBlock.Sensitivity.ITEMS_MOB, false,1.0f, 1.0f);
    public static final Block INVISIBLE_MOSSY_PLATE = new PlayerPlateBlock(PlayerPlateBlock.Sensitivity.ITEMS_MOB, true,1.0f, 1.0f);

    private static Block plateReg(Block block, String name) {
        block.setRegistryName(name);

        return block;
    }

    @SubscribeEvent
    public static void onBlockRegistry(RegistryEvent.Register<Block> event){
        event.getRegistry().register(OBSIDIAN_PLATE.setRegistryName(MOD_ID, "obsidian_plate"));
        event.getRegistry().register(INVISIBLE_OBSIDIAN_PLATE.setRegistryName(MOD_ID, "invisible_obsidian_plate"));
        event.getRegistry().register(MOSSY_PLATE.setRegistryName(MOD_ID, "mossy_plate"));
        event.getRegistry().register(INVISIBLE_MOSSY_PLATE.setRegistryName(MOD_ID, "invisible_mossy_plate"));
    }

    @SubscribeEvent
    public static void onItemRegisrty(RegistryEvent.Register<Item> event){
        event.getRegistry().register(new BlockItem(OBSIDIAN_PLATE, new Item.Properties().group(PlayerPlates.creativeTab)).setRegistryName(OBSIDIAN_PLATE.getRegistryName()));
        event.getRegistry().register(new BlockItem(INVISIBLE_OBSIDIAN_PLATE, new Item.Properties().group(PlayerPlates.creativeTab)).setRegistryName(INVISIBLE_OBSIDIAN_PLATE.getRegistryName()));
        event.getRegistry().register(new BlockItem(MOSSY_PLATE, new Item.Properties().group(PlayerPlates.creativeTab)).setRegistryName(MOSSY_PLATE.getRegistryName()));
        event.getRegistry().register(new BlockItem(INVISIBLE_MOSSY_PLATE, new Item.Properties().group(PlayerPlates.creativeTab)).setRegistryName(INVISIBLE_MOSSY_PLATE.getRegistryName()));
    }
}
