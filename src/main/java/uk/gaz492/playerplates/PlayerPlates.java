package uk.gaz492.playerplates;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class PlayerPlates implements ModInitializer {

    public PPBlocks blocks;
    public PPItems items;
    @Override
    public void onInitialize() {

        this.blocks = new PPBlocks(this);
        this.items = new PPItems(this);
    }
}
