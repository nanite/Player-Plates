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
    public PlayerPlatesNeoForge(IEventBus modEventBus) {
        PlayerPlates.init();
    }
}
