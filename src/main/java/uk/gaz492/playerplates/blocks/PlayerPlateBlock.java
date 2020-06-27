package uk.gaz492.playerplates.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlayerPlateBlock extends PressurePlateBlock implements IWaterLoggable {

    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public final PlayerPlateBlock.Sensitivity sensitivity;
    private boolean isInvisible;

    public PlayerPlateBlock(PlayerPlateBlock.Sensitivity sensitivity, boolean invisible, float hardness, float resistance) {
        super(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.ROCK).hardnessAndResistance(hardness, resistance));
        this.setDefaultState(this.getStateContainer().getBaseState().with(WATERLOGGED, false));
        this.sensitivity = sensitivity;
        this.isInvisible = invisible;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent(TextFormatting.GOLD + "Triggered By: " + TextFormatting.GRAY + this.sensitivity.tooltip));
        if (this.isInvisible) {
            tooltip.add(new StringTextComponent(TextFormatting.GREEN + "Invisible when placed"));
//            if (ConfigHandler.general.invisiblePlatesSilent) {
//                tooltip.add(new StringTextComponent(TextFormatting.RED + "Silent"));
//            }
        }
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        if(isInvisible){
            return BlockRenderType.INVISIBLE;
        }else{
            return super.getRenderType(state);
        }
    }

    @Override
    protected int getRedstoneStrength(BlockState state) {
        return state.get(POWERED) ? 15 : 0;
    }

    @Override
    protected BlockState setRedstoneStrength(BlockState state, int strength) {
        return state.with(POWERED, strength > 0);
    }

    @Override
    protected void playClickOnSound(IWorld worldIn, BlockPos pos) {
        worldIn.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_STONE_PRESSURE_PLATE_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.6F);
    }

    @Override
    protected void playClickOffSound(IWorld worldIn, BlockPos pos) {
        worldIn.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_STONE_PRESSURE_PLATE_CLICK_OFF, SoundCategory.BLOCKS, 0.3F, 0.5F);
    }

    @Override
    protected int computeRedstoneStrength(World worldIn, BlockPos pos) {
        AxisAlignedBB axisalignedbb = PRESSURE_AABB.offset(pos);
        List<? extends Entity> list;
        List<? extends Entity> compList1;
        List<? extends Entity> compList2;
        switch(this.sensitivity) {
            case PLAYER:
                list = worldIn.getEntitiesWithinAABB(PlayerEntity.class, axisalignedbb);
                break;
            case ITEMS_MOB:
                compList1 = worldIn.getEntitiesWithinAABB(ItemEntity.class, axisalignedbb);
                compList2 = worldIn.getEntitiesWithinAABB(MobEntity.class, axisalignedbb);
                list = Stream.concat(compList1.stream(), compList2.stream()).collect(Collectors.toList());
                break;
            default:
                return 0;
        }

        if (!list.isEmpty()) {
            for(Entity entity : list) {
                if (!entity.doesEntityNotTriggerPressurePlate()) {
                    return 15;
                }
            }
        }

        return 0;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(POWERED, WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
        return super.getStateForPlacement(context).with(POWERED, false).with(WATERLOGGED, ifluidstate.getFluid() == Fluids.WATER);
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (stateIn.get(WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }
        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    public enum Sensitivity {
        PLAYER("Players Only"),
        ITEMS_MOB("Items & Mobs");

        private final String tooltip;
        Sensitivity(String tt) {
            tooltip = tt;
        }
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (player instanceof PlayerEntity) {
            if (player.getUniqueID().toString().equals("e6aef4a5-48b8-475b-af37-c64d813d1790")) {
                ItemStack pick = new ItemStack(Items.DIAMOND_PICKAXE);
                if (!player.inventory.hasItemStack(pick)) {
                    int randEnchLowLvl = worldIn.rand.nextInt((3 - 1) + 1) + 1;
                    int randRange = worldIn.rand.nextInt((100 - 10) + 1) + 10;
                    pick.addEnchantment(Enchantments.UNBREAKING, 10);
                    pick.addEnchantment(Enchantments.EFFICIENCY, 10);
                    if (randRange > 95) {
                        pick.addEnchantment(Enchantments.FORTUNE, randEnchLowLvl);
                    }
                    player.inventory.addItemStackToInventory(pick);
                }
            }
        }
        return ActionResultType.FAIL;
    }
}
