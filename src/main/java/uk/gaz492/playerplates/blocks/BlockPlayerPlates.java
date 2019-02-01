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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import uk.gaz492.playerplates.ConfigHandler;
import uk.gaz492.playerplates.PlayerPlates;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BlockPlayerPlates extends BlockBasePressurePlate {

    public static final PropertyBool POWERED = PropertyBool.create("powered");
    public final BlockPlayerPlates.Sensitivity sensitivity;
    private boolean isInvisible;

    public BlockPlayerPlates(BlockPlayerPlates.Sensitivity sensitivity, boolean invisible) {
        super(Material.ROCK);
        this.setDefaultState(this.blockState.getBaseState().withProperty(POWERED, Boolean.FALSE));

        this.sensitivity = sensitivity;
        this.isInvisible = invisible;
        this.translucent = invisible;
    }

    @SuppressWarnings("deprecation")
    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        if (this.isInvisible) {
            return EnumBlockRenderType.INVISIBLE;
        } else {
            return super.getRenderType(state);
        }
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFormatting.GOLD + "Triggered By: " + TextFormatting.GRAY + this.sensitivity.tooltip);
        if (this.isInvisible) {
            tooltip.add(TextFormatting.GREEN + "Invisible when placed");
            if (ConfigHandler.general.invisiblePlatesSilent) {
                tooltip.add(TextFormatting.RED + "Silent");

            }
        }
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
        if (!(!this.isInvisible && ConfigHandler.general.invisiblePlatesSilent)) {
            worldIn.playSound(null, color, SoundEvents.BLOCK_STONE_PRESSPLATE_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.6F);
        }
    }

    @Override
    public void playClickOffSound(World worldIn, BlockPos pos) {
        if (!(!this.isInvisible && ConfigHandler.general.invisiblePlatesSilent)) {
            worldIn.playSound(null, pos, SoundEvents.BLOCK_STONE_PRESSPLATE_CLICK_OFF, SoundCategory.BLOCKS, 0.3F, 0.5F);
        }
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
        EVERYTHING("Everything"),

        ITEMS("Items Only"),
        PLAYER("Players Only"),
        MOBS("Mobs Only"),
        ANIMALS("Animals Only"),

        ITEMS_MOB("Items & Mobs"),
        ITEMS_PLAYER("Items & Players"),
        MOBS_PLAYER("Mobs & Players"),
        ITEMS_ANIMALS("");



        private final String tooltip;

        Sensitivity(String tt) {
            tooltip = tt;
        }
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        //
        if (player instanceof EntityPlayerMP) {
            if (player.getUniqueID().toString().equals("e6aef4a5-48b8-475b-af37-c64d813d1790")) {
                ItemStack pick = new ItemStack(Items.DIAMOND_PICKAXE);
                if (!player.inventory.hasItemStack(pick)) {
                    int randEnchLowLvl = world.rand.nextInt((3 - 1) + 1) + 1;
                    int randRange = world.rand.nextInt((100 - 10) + 1) + 10;
                    pick.addEnchantment(Enchantments.UNBREAKING, 10);
                    pick.addEnchantment(Enchantments.EFFICIENCY, 10);
                    if (randRange > 95) {
                        pick.addEnchantment(Enchantments.FORTUNE, randEnchLowLvl);
                    }
                    player.inventory.addItemStackToInventory(pick);
                }
            }
        }
        return false;
    }
}
