package uk.gaz492.playerplates;

import net.fabricmc.api.ModInitializer;

public class PlayerPlates implements ModInitializer {

    public PPBlocks blocks;
    public PPItems items;
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        System.out.println("Hello Fabric world!");
        this.blocks = new PPBlocks(this);
        this.items = new PPItems(this);
    }
}
