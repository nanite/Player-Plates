package dev.wuffs.playerplates;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class PlayerPlates {
    public static final String MOD_ID = "playerplates";
    // Registering a new creative tab

    public static final DeferredRegister<CreativeModeTab> CREATIVE_TAB = DeferredRegister.create(MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static final RegistrySupplier<CreativeModeTab> TAB = CREATIVE_TAB.register(MOD_ID, () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
            .title(Component.translatable("itemGroup.playerplates.player_plates"))
            .displayItems(
                    (itemDisplayParameters, output) -> {
                        PPRegistry.ITEMS.forEach(e -> output.accept(e.get()));
                    })
            .icon(() -> new ItemStack(PPRegistry.OBSIDIAN_PLATE_ITEM.get())).build());
    public static void init() {
        PPRegistry.BLOCKS.register();
        PPRegistry.ITEMS.register();
        CREATIVE_TAB.register();
    }
}
