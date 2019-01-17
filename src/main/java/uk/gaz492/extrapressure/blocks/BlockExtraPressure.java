package uk.gaz492.extrapressure.blocks;

import net.minecraft.block.BlockBasePressurePlate;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import uk.gaz492.extrapressure.util.ModInfo;

import java.util.List;

public class BlockExtraPressure extends BlockBasePressurePlate {

    public static final PropertyBool POWERED = PropertyBool.create("powered");
    public final BlockExtraPressure.Sensitivity sensitivity;

    public BlockExtraPressure(BlockExtraPressure.Sensitivity sensitivity) {
        super(Material.ROCK);
        this.setDefaultState(this.blockState.getBaseState().withProperty(POWERED, Boolean.FALSE));

        this.sensitivity = sensitivity;
    }

    @Override
    public int getRedstoneStrength(IBlockState state)
    {
        return state.getValue(POWERED) ? 15 : 0;
    }

    @Override
    public IBlockState setRedstoneStrength(IBlockState state, int strength)
    {
        return state.withProperty(POWERED, strength > 0);
    }

    @Override
    public void playClickOnSound(World worldIn, BlockPos color)
    {
        if (this.blockMaterial == Material.WOOD)
        {
            worldIn.playSound((EntityPlayerMP)null, color, SoundEvents.BLOCK_WOOD_PRESSPLATE_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.8F);
        }
        else
        {
            worldIn.playSound((EntityPlayerMP)null, color, SoundEvents.BLOCK_STONE_PRESSPLATE_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.6F);
        }
    }

    @Override
    public void playClickOffSound(World worldIn, BlockPos pos)
    {
        if (this.blockMaterial == Material.WOOD)
        {
            worldIn.playSound((EntityPlayerMP)null, pos, SoundEvents.BLOCK_WOOD_PRESSPLATE_CLICK_OFF, SoundCategory.BLOCKS, 0.3F, 0.7F);
        }
        else
        {
            worldIn.playSound((EntityPlayerMP)null, pos, SoundEvents.BLOCK_STONE_PRESSPLATE_CLICK_OFF, SoundCategory.BLOCKS, 0.3F, 0.5F);
        }
    }

    @Override
    public int computeRedstoneStrength(World worldIn, BlockPos pos)
    {
        AxisAlignedBB axisalignedbb = PRESSURE_AABB.offset(pos);
        List<? extends Entity> list;

        switch (this.sensitivity)
        {
            case EVERYTHING:
                list = worldIn.getEntitiesWithinAABBExcludingEntity((Entity)null, axisalignedbb);
                break;
            case MOBS:
                list = worldIn.<Entity>getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);
                break;
            case PLAYER:
                list = worldIn.getEntitiesWithinAABB(EntityPlayerMP.class, axisalignedbb);
                break;
            default:
                return 0;
        }

        if (!list.isEmpty())
        {
            for (Entity entity : list)
            {
                if (!entity.doesEntityNotTriggerPressurePlate())
                {
                    return 15;
                }
            }
        }

        return 0;
    }

    @SuppressWarnings("deprecation")
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(POWERED, meta == 1);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return (Boolean) state.getValue(POWERED) ? 1 : 0;
    }

    @Override
    public BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, POWERED);
    }

    public enum Sensitivity
    {
        EVERYTHING,
        PLAYER,
        MOBS;
    }
}
