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
import net.minecraft.text.StringTextComponent;
import net.minecraft.text.TextComponent;
import net.minecraft.text.TextFormat;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BoundingBox;
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
        if (isInvisible){
            return BlockRenderType.INVISIBLE;
        }else{
            return super.getRenderType(blockState);
        }
    }

    @Override
    public void buildTooltip(ItemStack itemStack, BlockView blockView, List<TextComponent> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new StringTextComponent(TextFormat.GOLD + "Triggered By: " + TextFormat.GRAY + this.type.tooltip));
        if(isInvisible){
            tooltip.add(new StringTextComponent(TextFormat.GREEN + "Invisible"));
        }
    }

    @Override
    protected void playPressSound(IWorld iWorld, BlockPos pos) {
        iWorld.playSound(null, pos, SoundEvents.BLOCK_STONE_PRESSURE_PLATE_CLICK_ON, SoundCategory.BLOCK, 0.3F, 0.6F);
    }

    @Override
    protected void playDepressSound(IWorld iWorld, BlockPos pos) {
        iWorld.playSound(null, pos, SoundEvents.BLOCK_STONE_PRESSURE_PLATE_CLICK_OFF, SoundCategory.BLOCK, 0.3F, 0.6F);
    }

    @Override
    protected int getRedstoneOutput(World world, BlockPos pos) {
        BoundingBox boundingBox = BOX.offset(pos);
        List list;

        List<? extends Entity> list1;
        List<? extends Entity> list2;
        switch(this.type) {
            case EVERYTHING:
                list = world.getVisibleEntities(null, boundingBox);
                break;
            case PLAYER:
                list = world.method_18467(PlayerEntity.class, boundingBox);
                break;
            case ITEMS_MOB:
                list1 = world.method_18467(ItemEntity.class, boundingBox);
                list2 = world.method_18467(MobEntity.class, boundingBox);
                list = Stream.concat(list1.stream(), list2.stream()).collect(Collectors.toList());
                break;
            default:
                return 0;
        }

        if (!list.isEmpty()) {
            Iterator var5 = list.iterator();

            while(var5.hasNext()) {
                Entity entity_1 = (Entity)var5.next();
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
        stateFactory$Builder_1.with(POWERED);
    }

    static {
        POWERED = Properties.POWERED;
    }

    public enum Type {
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

        Type(String tt) {
            tooltip = tt;
        }
    }
}
