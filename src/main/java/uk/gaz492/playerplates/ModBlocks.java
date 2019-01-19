package uk.gaz492.playerplates;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import uk.gaz492.playerplates.util.ModInfo;

@GameRegistry.ObjectHolder(ModInfo.MOD_ID)
@Mod.EventBusSubscriber(modid = ModInfo.MOD_ID)
public class ModBlocks {

    public static final Block OBSIDIAN_PLATE = Blocks.AIR;
    public static final Block MOSSY_PLATE = Blocks.AIR;
    public static final Block INVISIBLE_OBSIDIAN_PLATE = Blocks.AIR;
    public static final Block INVISIBLE_MOSSY_PLATE = Blocks.AIR;
}
