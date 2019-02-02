package uk.gaz492.playerplates;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import uk.gaz492.playerplates.item.DescriptiveBlockItem;

public class PPItems {

    private final Settings defaultSettings = new Settings().itemGroup(ItemGroup.REDSTONE);

    public final Item obsidianPlate;
    public final Item invisibleObsidianPlate;

    public final Item mossyPlate;
    public final Item invisibleMossyPlate;

    PPItems(PlayerPlates mod) {
        this.obsidianPlate = this.register("obsidian_plate", mod.blocks.obsidianPlate, this.defaultSettings);
        this.invisibleObsidianPlate = this.register("invisible_obsidian_plate", mod.blocks.invisibleObsidianPlate, this.defaultSettings);

        this.mossyPlate = this.register("mossy_plate", mod.blocks.mossyPlate, this.defaultSettings);
        this.invisibleMossyPlate = this.register("invisible_mossy_plate", mod.blocks.invisibleMossyPlate, this.defaultSettings);
    }

    private Item register (String id, Item item){
        return Registry.ITEM.register(new Identifier("playerplates", id), item);
    }

    private Item register (String id, Block block, Settings settings) {

        return this.register(id, new DescriptiveBlockItem(block, settings));
    }
}
