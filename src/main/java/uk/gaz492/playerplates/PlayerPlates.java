package uk.gaz492.playerplates;

import net.fabricmc.api.ModInitializer;

public class PlayerPlates implements ModInitializer {

    public PPRegistry registry;

    @Override
    public void onInitialize() {
        this.registry = new PPRegistry(this);
    }
}
