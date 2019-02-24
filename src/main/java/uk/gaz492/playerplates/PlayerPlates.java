package uk.gaz492.playerplates;

import net.fabricmc.api.ModInitializer;

public class PlayerPlates implements ModInitializer {

    public PPBlocks blocks;
    public PPItems items;
    @Override
    public void onInitialize() {

        this.blocks = new PPBlocks(this);
        this.items = new PPItems(this);
    }
}
