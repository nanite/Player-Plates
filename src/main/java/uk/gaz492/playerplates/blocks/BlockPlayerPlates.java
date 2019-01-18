package uk.gaz492.playerplates.blocks;

import net.minecraft.block.BlockBasePressurePlate;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BlockPlayerPlates extends BlockBasePressurePlate {

    public static final PropertyBool POWERED = PropertyBool.create("powered");
    public final BlockPlayerPlates.Sensitivity sensitivity;
    private final String ToolTipText;

    public BlockPlayerPlates(BlockPlayerPlates.Sensitivity sensitivity, String toolTipText) {
        super(Material.ROCK);
        this.setDefaultState(this.blockState.getBaseState().withProperty(POWERED, Boolean.FALSE));

        this.sensitivity = sensitivity;

        this.ToolTipText = toolTipText;
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFormatting.GOLD + "Triggered By: " + TextFormatting.GRAY + ToolTipText);
    }

    @Override
    public int getRedstoneStrength(IBlockState state) {
        return state.getValue(POWERED) ? 15 : 0;
    }

    @Override
    public IBlockState setRedstoneStrength(IBlockState state, int strength) {
        return state.withProperty(POWERED, strength > 0);
    }

    @Override
    public void playClickOnSound(World worldIn, BlockPos color) {
        worldIn.playSound(null, color, SoundEvents.BLOCK_STONE_PRESSPLATE_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.6F);
    }

    @Override
    public void playClickOffSound(World worldIn, BlockPos pos) {

        worldIn.playSound(null, pos, SoundEvents.BLOCK_STONE_PRESSPLATE_CLICK_OFF, SoundCategory.BLOCKS, 0.3F, 0.5F);
    }

    @Override
    public int computeRedstoneStrength(World worldIn, BlockPos pos) {
        AxisAlignedBB axisalignedbb = PRESSURE_AABB.offset(pos);
        List<? extends Entity> list;

        List<? extends Entity> list1;
        List<? extends Entity> list2;

        switch (this.sensitivity) {
            case EVERYTHING:
                list = worldIn.getEntitiesWithinAABBExcludingEntity(null, axisalignedbb);
                break;
            case ITEMS:
                list = worldIn.getEntitiesWithinAABB(EntityItem.class, axisalignedbb);
                break;
            case ITEMS_MOB:
                list1 = worldIn.getEntitiesWithinAABB(EntityItem.class, axisalignedbb);
                list2 = worldIn.<Entity>getEntitiesWithinAABB(EntityLiving.class, axisalignedbb);
                list = Stream.concat(list1.stream(), list2.stream()).collect(Collectors.toList());
                break;
            case ITEMS_PLAYER:
                list1 = worldIn.getEntitiesWithinAABB(EntityItem.class, axisalignedbb);
                list2 = worldIn.<Entity>getEntitiesWithinAABB(EntityPlayerMP.class, axisalignedbb);
                list = Stream.concat(list1.stream(), list2.stream()).collect(Collectors.toList());
                break;
            case MOBS_PLAYER:
                list1 = worldIn.getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);
                list2 = worldIn.<Entity>getEntitiesWithinAABB(EntityLiving.class, axisalignedbb);
                list = Stream.concat(list1.stream(), list2.stream()).collect(Collectors.toList());
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

        if (!list.isEmpty()) {

            for (Entity entity : list) {
                if (!entity.doesEntityNotTriggerPressurePlate()) {
                    return 15;
                }
            }
        }

        return 0;
    }

    @SuppressWarnings("deprecation")
    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(POWERED, meta == 1);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return (Boolean) state.getValue(POWERED) ? 1 : 0;
    }

    @Override
    public BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, POWERED);
    }

    public enum Sensitivity {
        EVERYTHING,
        ITEMS,
        ITEMS_MOB,
        ITEMS_PLAYER,
        MOBS_PLAYER,
        PLAYER,
        MOBS
    }
}
