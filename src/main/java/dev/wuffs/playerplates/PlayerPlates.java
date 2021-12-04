package dev.wuffs.playerplates;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(PlayerPlates.MOD_ID)
public class PlayerPlates
{
    public static final String MOD_ID = "playerplates";
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public static final CreativeModeTab creativeTab = new CreativeModeTab(MOD_ID + ".player_plates"){
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Registry.OBSIDIAN_PLATE_BLOCK.get());
        }
    };

    private static Block plateReg(Block block, String name) {
        block.setRegistryName(name);
        return block;
    }

    public PlayerPlates() {
        Registry.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        Registry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
