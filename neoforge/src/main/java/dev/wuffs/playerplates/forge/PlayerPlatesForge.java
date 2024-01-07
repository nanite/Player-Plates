package dev.wuffs.playerplates.forge;

import dev.wuffs.playerplates.PlayerPlates;
import net.neoforged.fml.common.Mod;

@Mod(PlayerPlates.MOD_ID)
public class PlayerPlatesForge {
    public PlayerPlatesForge() {
//        EventBuses.registerModEventBus(PlayerPlates.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        PlayerPlates.init();
    }
}
