package uk.gaz492.extrapressure.blocks;

import net.minecraft.block.BlockBasePressurePlate;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import uk.gaz492.extrapressure.ExtraPressure;
import uk.gaz492.extrapressure.util.ModInfo;

public class BlockPlateObsidian extends BlockBasePressurePlate {

    public static final ResourceLocation PLATE_OBSIDIAN = new ResourceLocation(ModInfo.MOD_ID, "obsidian_plate");

    public BlockPlateObsidian() {
        super(Material.ROCK);
        setRegistryName(PLATE_OBSIDIAN);
        setUnlocalizedName(ModInfo.MOD_ID + ".obsidian_plate");
        setCreativeTab(ExtraPressure.creativeTab);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }


    @Override
    protected void playClickOnSound(World worldIn, BlockPos color) {
        worldIn.playSound((EntityPlayer)null, color, SoundEvents.BLOCK_STONE_PRESSPLATE_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.6F);
    }

    @Override
    protected void playClickOffSound(World worldIn, BlockPos pos) {

    }

    @Override
    protected int computeRedstoneStrength(World worldIn, BlockPos pos) {
        return 0;
    }

    @Override
    protected int getRedstoneStrength(IBlockState state) {
        return 0;
    }

    @Override
    protected IBlockState setRedstoneStrength(IBlockState state, int strength) {
        return null;
    }
}
