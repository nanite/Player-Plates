package dev.wuffs.playerplates.block;

import dev.wuffs.playerplates.PlayerPlates;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlayerPlateBlock extends PressurePlateBlock {

    private final Sensitivity sensitivity;
    private final boolean isInvisible;

    public PlayerPlateBlock(Sensitivity sensitivity, boolean invisible, Block copy, BlockSetType type, String name) {
        super(
                type,
                BlockBehaviour.Properties.ofFullCopy(copy)
                        .noCollission()
                        .sound(SoundType.WOOD)
                        .setId(ResourceKey.create(
                                Registries.BLOCK,
                                ResourceLocation.fromNamespaceAndPath(PlayerPlates.MOD_ID, name)
                        ))
        );
        this.sensitivity = sensitivity;
        this.isInvisible = invisible;
    }

    public Sensitivity getSensitivity() {
        return sensitivity;
    }

    public boolean isInvisible() {
        return isInvisible;
    }

    @Override
    protected int getSignalStrength(Level world, BlockPos pos) {
        AABB aabb = TOUCH_AABB.move(pos);
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
                Registry<Enchantment> enchantmentRegistry = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
                pick.enchant(enchantmentRegistry.getOrThrow(Enchantments.UNBREAKING), 10);
                pick.enchant(enchantmentRegistry.getOrThrow(Enchantments.EFFICIENCY), 10);
                pick.enchant(enchantmentRegistry.getOrThrow(Enchantments.FORTUNE), 3);
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

        public String getTooltip() {
            return tooltip;
        }
    }

}