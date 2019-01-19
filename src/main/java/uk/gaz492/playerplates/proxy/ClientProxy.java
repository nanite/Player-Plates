package uk.gaz492.playerplates.proxy;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import uk.gaz492.playerplates.ModBlocks;
import uk.gaz492.playerplates.util.ModInfo;

@Mod.EventBusSubscriber(modid = ModInfo.MOD_ID, value = Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    public static void addModel(Block block, String variant){
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), variant));
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event){
        addModel(ModBlocks.OBSIDIAN_PLATE, "inventory");
        addModel(ModBlocks.MOSSY_PLATE, "inventory");
        addModel(ModBlocks.INVISIBLE_OBSIDIAN_PLATE, "inventory");
        addModel(ModBlocks.INVISIBLE_MOSSY_PLATE, "inventory");
//        addModel(ModBlocks.END_STONE_PLATE, "inventory");
    }

}
