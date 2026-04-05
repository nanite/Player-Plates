package dev.wuffs.playerplates.neoforge;

import dev.wuffs.playerplates.PPRegistry;
import dev.wuffs.playerplates.PlayerPlates;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.*;

@Mod(PlayerPlates.MOD_ID)
public class PlayerPlatesNeoForge {

    // Creative Tab
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PlayerPlates.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CUSTOM_CREATIVE_TAB = CREATIVE_MODE_TABS.register("playerplates", () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.playerplates.player_plates")).icon(() -> new ItemStack(PPRegistry.OBSIDIAN_PLATE_ITEM)).displayItems((parameters, output) -> {
        PPRegistry.TAB_ITEMS.forEach(item -> output.accept(item.getDefaultInstance()));
    }).build());

    // Register blocks/items
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(PlayerPlates.MOD_ID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PlayerPlates.MOD_ID);

    public static final DeferredBlock<Block> OBSIDIAN_PLATE_BLOCK = BLOCKS.register(PPRegistry.OBSIDIAN_PLATE_NAME, () -> PPRegistry.OBSIDIAN_PLATE_BLOCK);
    public static final DeferredItem<Item> OBSIDIAN_PLATE_ITEM = ITEMS.register(PPRegistry.OBSIDIAN_PLATE_NAME, () -> PPRegistry.OBSIDIAN_PLATE_ITEM);
    public static final DeferredBlock<Block> MOSSY_PLATE_BLOCK = BLOCKS.register(PPRegistry.MOSSY_PLATE_NAME, () -> PPRegistry.MOSSY_PLATE_BLOCK);
    public static final DeferredItem<Item> MOSSY_PLATE_ITEM = ITEMS.register(PPRegistry.MOSSY_PLATE_NAME, () -> PPRegistry.MOSSY_PLATE_ITEM);
    public static final DeferredBlock<Block> INVISIBLE_OBSIDIAN_PLATE_BLOCK = BLOCKS.register(PPRegistry.INVISIBLE_OBSIDIAN_PLATE_NAME, () -> PPRegistry.INVISIBLE_OBSIDIAN_PLATE_BLOCK);
    public static final DeferredItem<Item> INVISIBLE_OBSIDIAN_PLATE_ITEM = ITEMS.register(PPRegistry.INVISIBLE_OBSIDIAN_PLATE_NAME, () -> PPRegistry.INVISIBLE_OBSIDIAN_PLATE_ITEM);
    public static final DeferredBlock<Block> INVISIBLE_MOSSY_PLATE_BLOCK = BLOCKS.register(PPRegistry.INVISIBLE_MOSSY_PLATE_NAME, () -> PPRegistry.INVISIBLE_MOSSY_PLATE_BLOCK);
    public static final DeferredItem<Item> INVISIBLE_MOSSY_PLATE_ITEM = ITEMS.register(PPRegistry.INVISIBLE_MOSSY_PLATE_NAME, () -> PPRegistry.INVISIBLE_MOSSY_PLATE_ITEM);

    public PlayerPlatesNeoForge(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);

        CREATIVE_MODE_TABS.register(modEventBus);
    }
}
