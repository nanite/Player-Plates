package dev.wuffs.playerplates;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class PlayerPlates {
    public static final String MOD_ID = "playerplates";
    public static final Identifier CREATIVE_TAB_ID = Identifier.fromNamespaceAndPath(MOD_ID, "creative_tab");

//    public static CreativeModeTab createCreativeTab() {
//        return CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
//                .title(Component.translatable("itemGroup.playerplates.player_plates"))
//                .displayItems((itemDisplayParameters, output) -> PPRegistry.TAB_ITEMS.forEach(item -> output.accept(item.getDefaultInstance())))
//                .icon(() -> new ItemStack(PPRegistry.OBSIDIAN_PLATE_ITEM))
//                .build();
//    }
}
