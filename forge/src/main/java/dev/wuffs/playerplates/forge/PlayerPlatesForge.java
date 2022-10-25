package dev.wuffs.playerplates.forge;

import dev.architectury.platform.forge.EventBuses;
import dev.wuffs.playerplates.PlayerPlates;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(PlayerPlates.MOD_ID)
public class PlayerPlatesForge {
    public PlayerPlatesForge() {
        EventBuses.registerModEventBus(PlayerPlates.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        PlayerPlates.init();
    }
}
