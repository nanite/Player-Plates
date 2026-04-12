package dev.wuffs.playerplates.neoforge;

import dev.wuffs.playerplates.PPRegistry;
import dev.wuffs.playerplates.PlayerPlates;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.*;

@Mod(PlayerPlates.MOD_ID)
public class PlayerPlatesNeoForge {

    // Creative Tab
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PlayerPlates.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CUSTOM_CREATIVE_TAB =
            CREATIVE_MODE_TABS.register("playerplates", () ->
                    CreativeModeTab.builder()
                            .title(Component.translatable("itemGroup.playerplates.player_plates"))
                            .icon(() -> new ItemStack(PPRegistry.OBSIDIAN_PLATE.item()))
                            .displayItems((parameters, output) ->
                                    PPRegistry.TAB_ITEMS.forEach(item -> output.accept(item.getDefaultInstance())))
                            .build());

    // Register blocks/items
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(PlayerPlates.MOD_ID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PlayerPlates.MOD_ID);

    static {
        PPRegistry.ALL_PLATES.forEach(plateEntry -> {
            BLOCKS.register(plateEntry.name(), plateEntry::block);
            ITEMS.register(plateEntry.name(), plateEntry::item);
        });
    }

    public PlayerPlatesNeoForge(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);

        CREATIVE_MODE_TABS.register(modEventBus);
    }
}
