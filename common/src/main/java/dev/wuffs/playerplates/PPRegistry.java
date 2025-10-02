package dev.wuffs.playerplates;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.wuffs.playerplates.block.PlayerPlateBlock;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockSetType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class PPRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(PlayerPlates.MOD_ID, Registries.BLOCK);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(PlayerPlates.MOD_ID, Registries.ITEM);

    public static final String OBSIDIAN_PLATE_NAME = "obsidian_plate";
    public static final String INVISIBLE_OBSIDIAN_PLATE_NAME = "invisible_obsidian_plate";
    public static final String MOSSY_PLATE_NAME = "mossy_plate";
    public static final String INVISIBLE_MOSSY_PLATE_NAME = "invisible_mossy_plate";

    public static final RegistrySupplier<PlayerPlateBlock> OBSIDIAN_PLATE_BLOCK = BLOCKS.register(OBSIDIAN_PLATE_NAME, () -> new PlayerPlateBlock(PlayerPlateBlock.Sensitivity.PLAYER, false, Blocks.OBSIDIAN, BlockSetType.STONE, OBSIDIAN_PLATE_NAME));
    public static final RegistrySupplier<Item> OBSIDIAN_PLATE_ITEM = blockItem(OBSIDIAN_PLATE_NAME, OBSIDIAN_PLATE_BLOCK);
    public static final RegistrySupplier<PlayerPlateBlock> INVISIBLE_OBSIDIAN_PLATE_BLOCK = BLOCKS.register(INVISIBLE_OBSIDIAN_PLATE_NAME, () -> new PlayerPlateBlock(PlayerPlateBlock.Sensitivity.PLAYER, true,Blocks.OBSIDIAN, BlockSetType.STONE, INVISIBLE_OBSIDIAN_PLATE_NAME));
    public static final RegistrySupplier<Item> INVISIBLE_OBSIDIAN_PLATE_ITEM = blockItem(INVISIBLE_OBSIDIAN_PLATE_NAME, INVISIBLE_OBSIDIAN_PLATE_BLOCK);

    public static final RegistrySupplier<PlayerPlateBlock> MOSSY_PLATE_BLOCK = BLOCKS.register(MOSSY_PLATE_NAME, () -> new PlayerPlateBlock(PlayerPlateBlock.Sensitivity.ITEMS_MOB, false, Blocks.MOSSY_COBBLESTONE, BlockSetType.STONE, MOSSY_PLATE_NAME));
    public static final RegistrySupplier<Item> MOSSY_PLATE_ITEM = blockItem(MOSSY_PLATE_NAME, MOSSY_PLATE_BLOCK);
    public static final RegistrySupplier<PlayerPlateBlock> INVISIBLE_MOSSY_PLATE_BLOCK = BLOCKS.register(INVISIBLE_MOSSY_PLATE_NAME, () -> new PlayerPlateBlock(PlayerPlateBlock.Sensitivity.ITEMS_MOB, true, Blocks.MOSSY_COBBLESTONE, BlockSetType.STONE, INVISIBLE_MOSSY_PLATE_NAME));
    public static final RegistrySupplier<Item> INVISIBLE_MOSSY_PLATE_ITEM = blockItem(INVISIBLE_MOSSY_PLATE_NAME, INVISIBLE_MOSSY_PLATE_BLOCK);

    private static RegistrySupplier<Item> blockItem(String id, Supplier<PlayerPlateBlock> b) {
        return ITEMS.register(
                id,
                () -> {
                    final List<Component> tooltip = new ArrayList<>();
                    final PlayerPlateBlock block = b.get();

                    tooltip.add(Component.literal(ChatFormatting.GOLD + "Triggered By: " + ChatFormatting.GRAY + block.getSensitivity().getTooltip()));
                    if (block.isInvisible()) {
                        tooltip.add(Component.literal(ChatFormatting.GREEN + "Invisible when placed"));
                    }

                    return new BlockItem(
                            b.get(),
                            new Item.Properties().setId(
                                    ResourceKey.create(
                                            Registries.ITEM,
                                            ResourceLocation.fromNamespaceAndPath(PlayerPlates.MOD_ID, id)
                                    )
                            ).component(
                                    DataComponents.LORE,
                                    new ItemLore(
                                            List.of(),
                                            tooltip
                                    )
                            ).useBlockDescriptionPrefix()
                    );
                }
        );
    }
}
