package uk.gaz492.extrapressure;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import uk.gaz492.extrapressure.proxy.CommonProxy;
import uk.gaz492.extrapressure.util.ModInfo;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, version = ModInfo.MOD_VERSION, dependencies = ModInfo.MOD_DEPENDENCIES, useMetadata = true)
public class ExtraPressure {

    @SidedProxy(clientSide = ModInfo.CLIENT_PROXY)
    public static CommonProxy proxy;

    public static CreativeTabs creativeTab = new CreativeTabs("Extra Pressure") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE);
        }
    };

    @Mod.Instance
    public static ExtraPressure instance;


    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

}
