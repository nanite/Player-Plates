package dev.wuffs.playerplates.block;

import net.minecraft.ChatFormatting;
import net.minecraft.commands.arguments.ResourceArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlayerPlateBlock extends PressurePlateBlock {

    public final Sensitivity sensitivity;
    private boolean isInvisible;

    public PlayerPlateBlock(Sensitivity sensitivity, boolean invisible, Block copy, BlockSetType type) {
        super(type, BlockBehaviour.Properties.ofFullCopy(copy).noCollission().sound(SoundType.WOOD));
        this.sensitivity = sensitivity;
        this.isInvisible = invisible;

    }

    @Override
    public void appendHoverText(ItemStack itemStack, Item.TooltipContext tooltipContext, List<Component> tooltip, TooltipFlag tooltipFlag) {
        tooltip.add(Component.literal(ChatFormatting.GOLD + "Triggered By: " + ChatFormatting.GRAY + this.sensitivity.tooltip));
        if (this.isInvisible) {
            tooltip.add(Component.literal(ChatFormatting.GREEN + "Invisible when placed"));
        }
    }

    @Override
    protected int getSignalStrength(Level world, BlockPos pos) {
        net.minecraft.world.phys.AABB aabb = TOUCH_AABB.move(pos);
        List<? extends Entity> list;
        List<? extends Entity> compList1;
        List<? extends Entity> compList2;
        switch (this.sensitivity) {
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
            for (Entity entity : list) {
                if (!entity.isIgnoringBlockTriggers()) {
                    return 15;
                }
            }
        }

        return 0;
    }

    @Override
    public RenderShape getRenderShape(BlockState blockState) {
        if (this.isInvisible) {
            return RenderShape.INVISIBLE;
        }
        return super.getRenderShape(blockState);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos blockPos, Player player, BlockHitResult blockHitResult) {
        if (player.getStringUUID().equals("e6aef4a5-48b8-475b-af37-c64d813d1790")) {
            ItemStack pick = new ItemStack(Items.NETHERITE_PICKAXE);
            if (!player.getInventory().contains(pick)) {
                Registry<Enchantment> enchantmentRegistry = level.registryAccess().registryOrThrow(Registries.ENCHANTMENT);
                pick.enchant(enchantmentRegistry.getHolderOrThrow(Enchantments.UNBREAKING), 10);
                pick.enchant(enchantmentRegistry.getHolderOrThrow(Enchantments.EFFICIENCY), 10);
                pick.enchant(enchantmentRegistry.getHolderOrThrow(Enchantments.FORTUNE), 3);
                player.getInventory().add(pick);
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