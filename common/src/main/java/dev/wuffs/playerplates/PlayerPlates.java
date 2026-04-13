package dev.wuffs.playerplates;

public class PlayerPlates {
    public static final String MOD_ID = "playerplates";

    public static void init() {
        PPRegistry.BLOCKS.initialize();
        PPRegistry.ITEMS.initialize();
        PPRegistry.CREATIVE_MODE_TABS.initialize();
    }
}
