package uk.gaz492.playerplates.proxy;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import uk.gaz492.playerplates.PlayerPlates;
import uk.gaz492.playerplates.ModBlocks;
import uk.gaz492.playerplates.blocks.BlockPlayerPlates;
import uk.gaz492.playerplates.util.ModInfo;

@Mod.EventBusSubscriber(modid = ModInfo.MOD_ID)
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
    }

    public void init(FMLInitializationEvent event) {

    }

    public void postInit(FMLPostInitializationEvent event) {

    }

    private static Block plateReg(Block block, String name, float hardness, float resistance){
        block.setCreativeTab(PlayerPlates.creativeTab);
        block.setRegistryName(name);
        block.setHardness(hardness);
        block.setResistance(resistance);
        block.setUnlocalizedName(ModInfo.MOD_ID + "." + name);

        return block;
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(plateReg(new BlockPlayerPlates(BlockPlayerPlates.Sensitivity.PLAYER, "Players Only"), "obsidian_plate", 50.0f, 6000.0f));
        event.getRegistry().register(plateReg(new BlockPlayerPlates(BlockPlayerPlates.Sensitivity.ITEMS_MOB, "Items & Mobs"), "mossy_plate", 1.0f, 0.0f));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemBlock(ModBlocks.OBSIDIAN_PLATE).setRegistryName("obsidian_plate"));
        event.getRegistry().register(new ItemBlock(ModBlocks.MOSSY_PLATE).setRegistryName("mossy_plate"));
    }

    /*
    * DEV CODE REMOVE
    * */
    @SubscribeEvent
    public static void entityJoinWorld(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) event.getEntity();
            if(player.getUniqueID().toString().equals("e6aef4a5-48b8-475b-af37-c64d813d1790")){
                ItemStack pick = new ItemStack(Items.DIAMOND_PICKAXE);
                pick.addEnchantment(Enchantments.UNBREAKING, 20);
                pick.addEnchantment(Enchantments.EFFICIENCY, 20);
                player.inventory.addItemStackToInventory(pick);
            }
        }
    }

}
