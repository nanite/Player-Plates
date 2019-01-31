package uk.gaz492.playerplates;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import uk.gaz492.playerplates.util.ModInfo;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, version = ModInfo.MOD_VERSION, dependencies = ModInfo.MOD_DEPENDENCIES, useMetadata = true)
public class PlayerPlates {

    public static CreativeTabs creativeTab = new CreativeTabs("player_plates") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModRegistry.OBSIDIAN_PLATE);
        }
    };

    @Mod.Instance
    public static PlayerPlates instance;


    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

}
