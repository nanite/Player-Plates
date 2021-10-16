package dev.wuffs.playerplates;

import dev.wuffs.playerplates.Blocks.PlayerPlatesBlock;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(PlayerPlates.MOD_ID)
public class PlayerPlates
{
    public static final String MOD_ID = "playerplates";
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public static final Block OBSIDIAN_PLATE = new PlayerPlatesBlock(PlayerPlatesBlock.Sensitivity.PLAYER, false,50.0f, 6000.0f);
//    public static final Block INVISIBLE_OBSIDIAN_PLATE = new PlayerPlatesBlock(PlayerPlatesBlock.Sensitivity.PLAYER, true,50.0f, 6000.0f);

    public static final Block MOSSY_PLATE = new PlayerPlatesBlock(PlayerPlatesBlock.Sensitivity.ITEMS_MOB, false,1.0f, 1.0f);
//    public static final Block INVISIBLE_MOSSY_PLATE = new PlayerPlatesBlock(PlayerPlatesBlock.Sensitivity.ITEMS_MOB, true,1.0f, 1.0f);

    public static final CreativeModeTab creativeTab = new CreativeModeTab(MOD_ID + ".player_plates"){
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(OBSIDIAN_PLATE);
        }
    };

    private static Block plateReg(Block block, String name) {
        block.setRegistryName(name);

        return block;
    }

    public PlayerPlates() {
        MinecraftForge.EVENT_BUS.register(this);
    }

//    private void ClientSetup(final FMLClientSetupEvent clientSetupEvent) {
//        ItemBlockRenderTypes.setRenderLayer(P.get(), renderType -> renderType == RenderType.solid() || renderType == RenderType.translucent());
//    }

    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> event) {
            event.getRegistry().register(OBSIDIAN_PLATE.setRegistryName(MOD_ID, "obsidian_plate"));
//            event.getRegistry().register(INVISIBLE_OBSIDIAN_PLATE.setRegistryName(MOD_ID, "invisible_obsidian_plate"));
            event.getRegistry().register(MOSSY_PLATE.setRegistryName(MOD_ID, "mossy_plate"));
//            event.getRegistry().register(INVISIBLE_MOSSY_PLATE.setRegistryName(MOD_ID, "invisible_mossy_plate"));
        }

        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
            event.getRegistry().register(new BlockItem(OBSIDIAN_PLATE, new Item.Properties().tab(PlayerPlates.creativeTab)).setRegistryName(OBSIDIAN_PLATE.getRegistryName()));
//            event.getRegistry().register(new BlockItem(INVISIBLE_OBSIDIAN_PLATE, new Item.Properties().tab(PlayerPlates.creativeTab)).setRegistryName(INVISIBLE_OBSIDIAN_PLATE.getRegistryName()));
            event.getRegistry().register(new BlockItem(MOSSY_PLATE, new Item.Properties().tab(PlayerPlates.creativeTab)).setRegistryName(MOSSY_PLATE.getRegistryName()));
//            event.getRegistry().register(new BlockItem(INVISIBLE_MOSSY_PLATE, new Item.Properties().tab(PlayerPlates.creativeTab)).setRegistryName(INVISIBLE_MOSSY_PLATE.getRegistryName()));
        }
    }
}
