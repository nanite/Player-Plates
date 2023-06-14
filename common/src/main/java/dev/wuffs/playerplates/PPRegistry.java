package dev.wuffs.playerplates;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.wuffs.playerplates.Block.PlayerPlateBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockSetType;

import java.util.function.Supplier;

public class PPRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(PlayerPlates.MOD_ID, Registries.BLOCK);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(PlayerPlates.MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<Block> OBSIDIAN_PLATE_BLOCK = BLOCKS.register("obsidian_plate", () -> new PlayerPlateBlock(PlayerPlateBlock.Sensitivity.PLAYER, false,50.0f, 6000.0f, BlockSetType.STONE));
    public static final RegistrySupplier<Item> OBSIDIAN_PLATE_ITEM = blockItem("obsidian_plate", OBSIDIAN_PLATE_BLOCK);
    public static final RegistrySupplier<Block> INVISIBLE_OBSIDIAN_PLATE_BLOCK = BLOCKS.register("invisible_obsidian_plate", () -> new PlayerPlateBlock(PlayerPlateBlock.Sensitivity.PLAYER, true,50.0f, 6000.0f, BlockSetType.STONE));
    public static final RegistrySupplier<Item> INVISIBLE_OBSIDIAN_PLATE_ITEM = blockItem("invisible_obsidian_plate", INVISIBLE_OBSIDIAN_PLATE_BLOCK);

    public static final RegistrySupplier<Block> MOSSY_PLATE_BLOCK = BLOCKS.register("mossy_plate", () -> new PlayerPlateBlock(PlayerPlateBlock.Sensitivity.ITEMS_MOB, false,1.0f, 1.0f, BlockSetType.STONE));
    public static final RegistrySupplier<Item> MOSSY_PLATE_ITEM = blockItem("mossy_plate", MOSSY_PLATE_BLOCK);
    public static final RegistrySupplier<Block> INVISIBLE_MOSSY_PLATE_BLOCK = BLOCKS.register("invisible_mossy_plate", () -> new PlayerPlateBlock(PlayerPlateBlock.Sensitivity.ITEMS_MOB, true,1.0f, 1.0f, BlockSetType.STONE));
    public static final RegistrySupplier<Item> INVISIBLE_MOSSY_PLATE_ITEM = blockItem("invisible_mossy_plate", INVISIBLE_MOSSY_PLATE_BLOCK);

    private static RegistrySupplier<Item> blockItem(String id, Supplier<Block> b) {
        return ITEMS.register(id, () -> new BlockItem(b.get(), new Item.Properties()));
    }
}
