package dev.wuffs.playerplates;

import dev.nanite.library.core.registry.NaniteRegistry;
import dev.nanite.library.core.registry.RegistryHolder;
import dev.nanite.library.core.weirdness.CreativeModeTabBuilder;
import dev.nanite.library.platform.Platform;
import dev.wuffs.playerplates.block.PlayerPlateBlock;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockSetType;

import java.util.ArrayList;
import java.util.List;

public class PPRegistry {
    public static final NaniteRegistry<Block> BLOCKS = NaniteRegistry.create(PlayerPlates.MOD_ID, BuiltInRegistries.BLOCK);
    public static final NaniteRegistry<Item> ITEMS = NaniteRegistry.create(PlayerPlates.MOD_ID, BuiltInRegistries.ITEM);

    public static final NaniteRegistry<CreativeModeTab> CREATIVE_MODE_TABS = NaniteRegistry.create(PlayerPlates.MOD_ID, BuiltInRegistries.CREATIVE_MODE_TAB);

    public static final RegistryHolder<Block, PlayerPlateBlock> OBSIDIAN_PLATE = plate("obsidian_plate", PlayerPlateBlock.Sensitivity.PLAYER, false, Blocks.OBSIDIAN, BlockSetType.STONE);
    public static final RegistryHolder<Block, PlayerPlateBlock> INVISIBLE_OBSIDIAN_PLATE = plate("invisible_obsidian_plate", PlayerPlateBlock.Sensitivity.PLAYER, true, Blocks.OBSIDIAN, BlockSetType.STONE);

    public static final RegistryHolder<Block, PlayerPlateBlock> MOSSY_PLATE = plate("mossy_plate", PlayerPlateBlock.Sensitivity.ITEMS_MOBS, false, Blocks.MOSSY_COBBLESTONE, BlockSetType.STONE);
    public static final RegistryHolder<Block, PlayerPlateBlock> INVISIBLE_MOSSY_PLATE = plate("invisible_mossy_plate", PlayerPlateBlock.Sensitivity.ITEMS_MOBS, true, Blocks.MOSSY_COBBLESTONE, BlockSetType.STONE);

    public static final RegistryHolder<Block, PlayerPlateBlock> ITEM_PLATE = plate("item_plate", PlayerPlateBlock.Sensitivity.ITEMS, false, Blocks.STONE, BlockSetType.STONE);
    public static final RegistryHolder<Block, PlayerPlateBlock> INVISIBLE_ITEM_PLATE = plate("invisible_item_plate", PlayerPlateBlock.Sensitivity.ITEMS, true, Blocks.STONE, BlockSetType.STONE);

    public static final RegistryHolder<Block, PlayerPlateBlock> PASSIVE_MOB_PLATE = plate("passive_mob_plate", PlayerPlateBlock.Sensitivity.PASSIVE_MOB, false, Blocks.STONE, BlockSetType.STONE);
    public static final RegistryHolder<Block, PlayerPlateBlock> INVISIBLE_PASSIVE_MOB_PLATE = plate("invisible_passive_mob_plate", PlayerPlateBlock.Sensitivity.PASSIVE_MOB, true, Blocks.STONE, BlockSetType.STONE);

    public static final RegistryHolder<Item, BlockItem> OBSIDIAN_PLATE_ITEM = blockItem(Identifier.fromNamespaceAndPath(PlayerPlates.MOD_ID, "obsidian_plate"), OBSIDIAN_PLATE);
    public static final RegistryHolder<Item, BlockItem> INVISIBLE_OBSIDIAN_PLATE_ITEM = blockItem(Identifier.fromNamespaceAndPath(PlayerPlates.MOD_ID, "invisible_obsidian_plate"), INVISIBLE_OBSIDIAN_PLATE);

    public static final RegistryHolder<Item, BlockItem> MOSSY_PLATE_ITEM = blockItem(Identifier.fromNamespaceAndPath(PlayerPlates.MOD_ID, "mossy_plate"), MOSSY_PLATE);
    public static final RegistryHolder<Item, BlockItem> INVISIBLE_MOSSY_PLATE_ITEM = blockItem(Identifier.fromNamespaceAndPath(PlayerPlates.MOD_ID, "invisible_mossy_plate"), INVISIBLE_MOSSY_PLATE);

    public static final RegistryHolder<Item, BlockItem> ITEM_PLATE_ITEM = blockItem(Identifier.fromNamespaceAndPath(PlayerPlates.MOD_ID, "item_plate"), ITEM_PLATE);
    public static final RegistryHolder<Item, BlockItem> INVISIBLE_ITEM_PLATE_ITEM = blockItem(Identifier.fromNamespaceAndPath(PlayerPlates.MOD_ID, "invisible_item_plate"), INVISIBLE_ITEM_PLATE);

    public static final RegistryHolder<Item, BlockItem> PASSIVE_MOB_PLATE_ITEM = blockItem(Identifier.fromNamespaceAndPath(PlayerPlates.MOD_ID, "passive_mob_plate"), PASSIVE_MOB_PLATE);
    public static final RegistryHolder<Item, BlockItem> INVISIBLE_PASSIVE_MOB_PLATE_ITEM = blockItem(Identifier.fromNamespaceAndPath(PlayerPlates.MOD_ID, "invisible_passive_mob_plate"), INVISIBLE_PASSIVE_MOB_PLATE);

    public static final RegistryHolder<CreativeModeTab, CreativeModeTab> CUSTOM_CREATIVE_TAB =
            CREATIVE_MODE_TABS.register("playerplates", () ->
                    new CreativeModeTabBuilder(Component.translatable("itemGroup.playerplates.player_plates"))
                            .iconFromItem(PPRegistry.OBSIDIAN_PLATE_ITEM::get)
                            .populateFromRegistry(ITEMS)
                            .build());

    private static RegistryHolder<Block, PlayerPlateBlock> plate(String name, PlayerPlateBlock.Sensitivity sensitivity, boolean invisible, Block base, BlockSetType setType) {
        return BLOCKS.registerPassKey(name, (resourceKey) -> new PlayerPlateBlock(resourceKey, sensitivity, invisible, base, setType));
    }

    private static RegistryHolder<Item, BlockItem> blockItem(Identifier id, RegistryHolder<Block, PlayerPlateBlock> heldBlock) {
        return ITEMS.register(id.getPath(), () -> {
            final List<Component> tooltip = new ArrayList<>();

            var block = heldBlock.get();

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
        });
    }
}
