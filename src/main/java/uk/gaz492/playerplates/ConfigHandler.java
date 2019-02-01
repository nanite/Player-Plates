package uk.gaz492.playerplates;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import uk.gaz492.playerplates.util.ModInfo;

@Mod.EventBusSubscriber(modid = ModInfo.MOD_ID)
@Config(modid = ModInfo.MOD_ID, category = "")
public class ConfigHandler {

    public static class General {

        @Config.RequiresMcRestart
        @Config.Comment({"Enables/Disables all invisible pressure playes"})
        public boolean enableAllInvisiblePlates = true;

        @Config.RequiresMcRestart
        @Config.Comment({"Makes invisible pressure plates silent"})
        public boolean invisiblePlatesSilent = true;
    }

    public static class ObsidianPlates {
        @Config.Comment({"Enable/Disable Obsidian Pressure Plate"})
        public boolean enableObsidianPlate = true;

        @Config.Comment({"Enable/Disable Invisible Obsidian Pressure Plate", "enableAllInvisiblePlates must will override this"})
        public boolean enableInvisibleObsidianPlate = true;
    }

    public static class MossyPlates {
        @Config.Comment({"Enable/Disable Mossy Pressure Plate"})
        public boolean enableMossyPlate = true;

        @Config.Comment({"Enable/Disable Invisible Mossy Pressure Plate", "enableAllInvisiblePlates must will override this"})
        public boolean enableInvisibleMossyPlate = true;
    }

    public static General general = new General();
    public static ObsidianPlates obsidianPlates = new ObsidianPlates();
    public static MossyPlates mossyPlates = new MossyPlates();

    @SubscribeEvent
    public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(ModInfo.MOD_ID)) {
            ConfigManager.sync(ModInfo.MOD_ID, Config.Type.INSTANCE);
        }
    }

}

