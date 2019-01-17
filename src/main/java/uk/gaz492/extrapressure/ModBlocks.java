package uk.gaz492.extrapressure;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import uk.gaz492.extrapressure.blocks.BlockPlateObsidian;

public class ModBlocks {

    @GameRegistry.ObjectHolder("extrapressure:obsidian_plate")
    public static BlockPlateObsidian blockPlateObsidian;

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        blockPlateObsidian.initModel();
    }

}
