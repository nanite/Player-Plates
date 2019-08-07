package uk.gaz492.playerplates;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import uk.gaz492.playerplates.blocks.PlayerPlateBlock;

import java.util.stream.Collectors;

import static uk.gaz492.playerplates.util.ModInfo.MOD_ID;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MOD_ID)
public class PlayerPlates
{
    public static PlayerPlateBlock playerPlateBlock;

    public static final ItemGroup creativeTab = new ItemGroup(MOD_ID + ".player_plates") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(PlayerPlatesEventHandler.OBSIDIAN_PLATE);
        }
    };
}
