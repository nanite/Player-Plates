package dev.wuffs.playerplates;

import dev.wuffs.playerplates.Blocks.PlayerPlatesBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class Registry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, PlayerPlates.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PlayerPlates.MOD_ID);

    public static final RegistryObject<Block> OBSIDIAN_PLATE_BLOCK = BLOCKS.register("obsidian_plate", () -> new PlayerPlatesBlock(PlayerPlatesBlock.Sensitivity.PLAYER, false,50.0f, 6000.0f));
    public static final RegistryObject<Item> OBSIDIAN_PLATE_ITEM = blockItem("obsidian_plate", OBSIDIAN_PLATE_BLOCK);
    public static final RegistryObject<Block> INVISIBLE_OBSIDIAN_PLATE_BLOCK = BLOCKS.register("invisible_obsidian_plate", () -> new PlayerPlatesBlock(PlayerPlatesBlock.Sensitivity.PLAYER, true,50.0f, 6000.0f));
    public static final RegistryObject<Item> INVISIBLE_OBSIDIAN_PLATE_ITEM = blockItem("invisible_obsidian_plate", INVISIBLE_OBSIDIAN_PLATE_BLOCK);

    public static final RegistryObject<Block> MOSSY_PLATE_BLOCK = BLOCKS.register("mossy_plate", () -> new PlayerPlatesBlock(PlayerPlatesBlock.Sensitivity.ITEMS_MOB, false,1.0f, 1.0f));
    public static final RegistryObject<Item> MOSSY_PLATE_ITEM = blockItem("mossy_plate", MOSSY_PLATE_BLOCK);
    public static final RegistryObject<Block> INVISIBLE_MOSSY_PLATE_BLOCK = BLOCKS.register("invisible_mossy_plate", () -> new PlayerPlatesBlock(PlayerPlatesBlock.Sensitivity.ITEMS_MOB, true,1.0f, 1.0f));
    public static final RegistryObject<Item> INVISIBLE_MOSSY_PLATE_ITEM = blockItem("invisible_mossy_plate", INVISIBLE_MOSSY_PLATE_BLOCK);

    private static RegistryObject<Item> blockItem(String id, Supplier<Block> b) {
        return ITEMS.register(id, () -> new BlockItem(b.get(), new Item.Properties().tab(PlayerPlates.creativeTab)));
    }
}
