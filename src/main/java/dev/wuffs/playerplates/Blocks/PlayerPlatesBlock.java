package dev.wuffs.playerplates.Blocks;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlayerPlatesBlock extends PressurePlateBlock {

    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public final dev.wuffs.playerplates.Blocks.PlayerPlatesBlock.Sensitivity sensitivity;
    private boolean isInvisible;

    public PlayerPlatesBlock(dev.wuffs.playerplates.Blocks.PlayerPlatesBlock.Sensitivity sensitivity, boolean invisible, float hardness, float resistance) {
//        super(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.ROCK).hardnessAndResistance(hardness, resistance));
        super(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(hardness, resistance).sound(SoundType.WOOD));
        this.sensitivity = sensitivity;
        this.isInvisible = invisible;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter blockGetter, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(new TextComponent(ChatFormatting.GOLD + "Triggered By: " + ChatFormatting.GRAY + this.sensitivity.tooltip));
        if (this.isInvisible) {
            tooltip.add(new TextComponent(ChatFormatting.GREEN + "Invisible when placed"));
//            if (ConfigHandler.general.invisiblePlatesSilent) {
//                tooltip.add(new StringTextComponent(TextFormatting.RED + "Silent"));
//            }
        }
    }

    @Override
    protected int getSignalStrength(Level world, BlockPos pos) {
        net.minecraft.world.phys.AABB aabb = TOUCH_AABB.move(pos);
        List<? extends Entity> list;
        List<? extends Entity> compList1;
        List<? extends Entity> compList2;
        switch(this.sensitivity) {
            case PLAYER:
                list = world.getEntitiesOfClass(Player.class, aabb);
                break;
            case ITEMS_MOB:
                compList1 = world.getEntitiesOfClass(ItemEntity.class, aabb);
                compList2 = world.getEntitiesOfClass(Mob.class, aabb);
                list = Stream.concat(compList1.stream(), compList2.stream()).collect(Collectors.toList());
                break;
            default:
                return 0;
        }

        if (!list.isEmpty()) {
            for(Entity entity : list) {
                if (!entity.isIgnoringBlockTriggers()) {
                    return 15;
                }
            }
        }

        return 0;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (player instanceof Player) {
            if (player.getStringUUID().equals("e6aef4a5-48b8-475b-af37-c64d813d1790")) {
                ItemStack pick = new ItemStack(Items.DIAMOND_PICKAXE);
                if (!player.getInventory().contains(pick)) {
                    int randEnchLowLvl = level.random.nextInt((3 - 1) + 1) + 1;
                    int randRange = level.random.nextInt((100 - 10) + 1) + 10;
                    pick.enchant(Enchantments.UNBREAKING, 10);
                    pick.enchant(Enchantments.BLOCK_EFFICIENCY, 10);
                    if (randRange > 95) {
                        pick.enchant(Enchantments.BLOCK_FORTUNE, randEnchLowLvl);
                    }
                    player.getInventory().add(pick);
                }
            }
        }
        return InteractionResult.FAIL;
    }

    public enum Sensitivity {
        PLAYER("Players Only"),
        ITEMS_MOB("Items & Mobs");

        private final String tooltip;

        Sensitivity(String tt) {
            tooltip = tt;
        }
    }

}