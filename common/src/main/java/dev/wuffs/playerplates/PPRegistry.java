package dev.wuffs.playerplates;

import dev.nanite.library.core.registry.NaniteRegistry;
import dev.nanite.library.core.registry.RegistryHolder;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockSetType;

import java.util.ArrayList;
import java.util.List;

public class PPRegistry {

    public record PlateEntry(String name, PlayerPlateBlock block, Item item){
        public Identifier id() {
            return Identifier.fromNamespaceAndPath(PlayerPlates.MOD_ID, name);
        }
    }

    public static final NaniteRegistry<Block> BLOCKS = NaniteRegistry.create(PlayerPlates.MOD_ID, BuiltInRegistries.BLOCK);
    public static final NaniteRegistry<Item> ITEMS = NaniteRegistry.create(PlayerPlates.MOD_ID, BuiltInRegistries.ITEM);

    public static final RegistryHolder<Block, PlayerPlateBlock> OBSIDIAN_PLATE = plate("obsidian_plate", PlayerPlateBlock.Sensitivity.PLAYER, false, Blocks.OBSIDIAN, BlockSetType.STONE);
    public static final RegistryHolder<Block, PlayerPlateBlock> INVISIBLE_OBSIDIAN_PLATE = plate("invisible_obsidian_plate", PlayerPlateBlock.Sensitivity.PLAYER, true, Blocks.OBSIDIAN, BlockSetType.STONE);

    public static final RegistryHolder<Block, PlayerPlateBlock> MOSSY_PLATE = plate("mossy_plate", PlayerPlateBlock.Sensitivity.ITEMS_MOBS, false, Blocks.MOSSY_COBBLESTONE, BlockSetType.STONE);
    public static final RegistryHolder<Block, PlayerPlateBlock> INVISIBLE_MOSSY_PLATE = plate("invisible_mossy_plate", PlayerPlateBlock.Sensitivity.ITEMS_MOBS, true, Blocks.MOSSY_COBBLESTONE, BlockSetType.STONE);

    public static final RegistryHolder<Block, PlayerPlateBlock> ITEM_PLATE = plate("item_plate", PlayerPlateBlock.Sensitivity.ITEMS, false, Blocks.STONE, BlockSetType.STONE);
    public static final RegistryHolder<Block, PlayerPlateBlock> INVISIBLE_ITEM_PLATE = plate("invisible_item_plate", PlayerPlateBlock.Sensitivity.ITEMS, true, Blocks.STONE, BlockSetType.STONE);

    public static final RegistryHolder<Block, PlayerPlateBlock> PASSIVE_MOB_PLATE = plate("passive_mob_plate", PlayerPlateBlock.Sensitivity.PASSIVE_MOB, false, Blocks.STONE, BlockSetType.STONE);
    public static final RegistryHolder<Block, PlayerPlateBlock> INVISIBLE_PASSIVE_MOB_PLATE = plate("invisible_passive_mob_plate", PlayerPlateBlock.Sensitivity.PASSIVE_MOB, true, Blocks.STONE, BlockSetType.STONE);

    public static final List<PlateEntry> ALL_PLATES = List.of(
            OBSIDIAN_PLATE,
            INVISIBLE_OBSIDIAN_PLATE,
            MOSSY_PLATE,
            INVISIBLE_MOSSY_PLATE,
            ITEM_PLATE,
            INVISIBLE_ITEM_PLATE,
            PASSIVE_MOB_PLATE,
            INVISIBLE_PASSIVE_MOB_PLATE
    );

    public static final List<Item> TAB_ITEMS = ALL_PLATES.stream()
            .map(PlateEntry::item)
            .toList();

    private static RegistryHolder<Block, PlayerPlateBlock> plate(String name, PlayerPlateBlock.Sensitivity sensitivity, boolean invisible, Block base, BlockSetType setType) {
        Identifier id = Identifier.fromNamespaceAndPath(PlayerPlates.MOD_ID, name);
        BLOCKS.register(id, () -> new PlayerPlateBlock(sensitivity, invisible, base, setType, name));
        PlayerPlateBlock block = new PlayerPlateBlock(sensitivity, invisible, base, setType, name);
        Item item = blockItem(id, block);
        return new PlateEntry(name, block, item);
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
