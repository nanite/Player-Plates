package dev.wuffs.playerplates;

import dev.wuffs.playerplates.block.PlayerPlateBlock;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockSetType;

import java.util.ArrayList;
import java.util.List;

public class PPRegistry {

    public static final String OBSIDIAN_PLATE_NAME = "obsidian_plate";
    public static final String INVISIBLE_OBSIDIAN_PLATE_NAME = "invisible_obsidian_plate";
    public static final String MOSSY_PLATE_NAME = "mossy_plate";
    public static final String INVISIBLE_MOSSY_PLATE_NAME = "invisible_mossy_plate";

    public static final Identifier OBSIDIAN_PLATE_ID = id(OBSIDIAN_PLATE_NAME);
    public static final Identifier INVISIBLE_OBSIDIAN_PLATE_ID = id(INVISIBLE_OBSIDIAN_PLATE_NAME);
    public static final Identifier MOSSY_PLATE_ID = id(MOSSY_PLATE_NAME);
    public static final Identifier INVISIBLE_MOSSY_PLATE_ID = id(INVISIBLE_MOSSY_PLATE_NAME);

    public static final PlayerPlateBlock OBSIDIAN_PLATE_BLOCK = new PlayerPlateBlock(PlayerPlateBlock.Sensitivity.PLAYER, false, Blocks.OBSIDIAN, BlockSetType.STONE, OBSIDIAN_PLATE_NAME);
    public static final Item OBSIDIAN_PLATE_ITEM = blockItem(OBSIDIAN_PLATE_ID, OBSIDIAN_PLATE_BLOCK);
    public static final PlayerPlateBlock INVISIBLE_OBSIDIAN_PLATE_BLOCK = new PlayerPlateBlock(PlayerPlateBlock.Sensitivity.PLAYER, true, Blocks.OBSIDIAN, BlockSetType.STONE, INVISIBLE_OBSIDIAN_PLATE_NAME);
    public static final Item INVISIBLE_OBSIDIAN_PLATE_ITEM = blockItem(INVISIBLE_OBSIDIAN_PLATE_ID, INVISIBLE_OBSIDIAN_PLATE_BLOCK);

    public static final PlayerPlateBlock MOSSY_PLATE_BLOCK = new PlayerPlateBlock(PlayerPlateBlock.Sensitivity.ITEMS_MOB, false, Blocks.MOSSY_COBBLESTONE, BlockSetType.STONE, MOSSY_PLATE_NAME);
    public static final Item MOSSY_PLATE_ITEM = blockItem(MOSSY_PLATE_ID, MOSSY_PLATE_BLOCK);
    public static final PlayerPlateBlock INVISIBLE_MOSSY_PLATE_BLOCK = new PlayerPlateBlock(PlayerPlateBlock.Sensitivity.ITEMS_MOB, true, Blocks.MOSSY_COBBLESTONE, BlockSetType.STONE, INVISIBLE_MOSSY_PLATE_NAME);
    public static final Item INVISIBLE_MOSSY_PLATE_ITEM = blockItem(INVISIBLE_MOSSY_PLATE_ID, INVISIBLE_MOSSY_PLATE_BLOCK);

    public static final List<Item> TAB_ITEMS = List.of(
            OBSIDIAN_PLATE_ITEM,
            INVISIBLE_OBSIDIAN_PLATE_ITEM,
            MOSSY_PLATE_ITEM,
            INVISIBLE_MOSSY_PLATE_ITEM
    );

    private static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(PlayerPlates.MOD_ID, path);
    }

    private static Item blockItem(Identifier id, PlayerPlateBlock block) {
        final List<Component> tooltip = new ArrayList<>();

        tooltip.add(Component.literal(ChatFormatting.GOLD + "Triggered By: " + ChatFormatting.GRAY + block.getSensitivity().getTooltip()));
        if (block.isInvisible()) {
            tooltip.add(Component.literal(ChatFormatting.GREEN + "Invisible when placed"));
        }

        return new BlockItem(
                block,
                new Item.Properties().setId(
                        ResourceKey.create(
                                Registries.ITEM,
                                id
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
}
