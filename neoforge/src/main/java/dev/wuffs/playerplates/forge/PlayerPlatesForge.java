package dev.wuffs.playerplates.forge;

import dev.wuffs.playerplates.PlayerPlates;
import net.neoforged.fml.common.Mod;

@Mod(PlayerPlates.MOD_ID)
public class PlayerPlatesForge {
    public PlayerPlatesForge() {
        PlayerPlates.init();
    }
}
