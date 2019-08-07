package uk.gaz492.playerplates.blocks;

import net.minecraft.block.*;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BlockPlayerPlate extends AbstractPressurePlateBlock {

    public static final BooleanProperty POWERED;
    private final BlockPlayerPlate.Type type;
    private boolean isInvisible;


    public BlockPlayerPlate(Block.Settings settings, BlockPlayerPlate.Type type, boolean invisible) {
        super(settings);

        this.setDefaultState(this.stateFactory.getDefaultState().with(POWERED, false));
        this.type = type;
        this.isInvisible = invisible;
    }

    @Override
    public BlockRenderType getRenderType(BlockState blockState) {
        if (isInvisible) {
            return BlockRenderType.INVISIBLE;
        } else {
            return super.getRenderType(blockState);
        }
    }

    @Override
    public void buildTooltip(ItemStack itemStack, BlockView blockView, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new LiteralText(Formatting.GOLD + "Triggered By: " + Formatting.GRAY + this.type.tooltip));
        if (isInvisible) {
            tooltip.add(new LiteralText(Formatting.GREEN + "Invisible when placed"));
        }
    }

    @Override
    protected void playPressSound(IWorld iWorld, BlockPos pos) {
        iWorld.playSound(null, pos, SoundEvents.BLOCK_STONE_PRESSURE_PLATE_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.6F);
    }

    @Override
    protected void playDepressSound(IWorld iWorld, BlockPos pos) {
        iWorld.playSound(null, pos, SoundEvents.BLOCK_STONE_PRESSURE_PLATE_CLICK_OFF, SoundCategory.BLOCKS, 0.3F, 0.6F);
    }

    @Override
    protected int getRedstoneOutput(World world, BlockPos pos) {
        Box boundingBox = BOX.offset(pos);
        List list;

        List<? extends Entity> compList1;
        List<? extends Entity> compList2;
        switch (this.type) {
            case PLAYER:
                list = world.getEntities(PlayerEntity.class, boundingBox);
                break;
            case ITEMS_MOB:
                compList1 = world.getEntities(ItemEntity.class, boundingBox);
                compList2 = world.getEntities(MobEntity.class, boundingBox);
                list = Stream.concat(compList1.stream(), compList2.stream()).collect(Collectors.toList());
                break;
            default:
                return 0;
        }

        if (!list.isEmpty()) {
            Iterator listIterator = list.iterator();
            while (listIterator.hasNext()) {
                Entity entity_1 = (Entity) listIterator.next();
                if (!entity_1.canAvoidTraps()) {
                    return 15;
                }
            }
        }

        return 0;
    }

    @Override
    protected int getRedstoneOutput(BlockState blockState) {
        return blockState.get(POWERED) ? 15 : 0;
    }

    @Override
    protected BlockState setRedstoneOutput(BlockState blockState, int strength) {
        return blockState.with(POWERED, strength > 0);
    }

    protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
//        stateFactory$Builder_1.with(POWERED);
        stateFactory$Builder_1.add(POWERED);
    }

    static {
        POWERED = Properties.POWERED;
    }

    public enum Type {
        PLAYER("Players Only"),
        ITEMS_MOB("Items & Mobs");

        private final String tooltip;

        Type(String tt) {
            tooltip = tt;
        }
    }
}
