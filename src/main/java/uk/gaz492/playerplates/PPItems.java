package uk.gaz492.playerplates;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.client.itemgroup.FabricCreativeGuiComponents;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import uk.gaz492.playerplates.item.DescriptiveBlockItem;
import uk.gaz492.playerplates.util.ModInfo;

import java.util.function.Supplier;

public class PPItems {

    public static Item obsidianPlate;
    public static Item invisibleObsidianPlate;

    public static Item mossyPlate;
    public static Item invisibleMossyPlate;

    public static ItemGroup creativeTab = FabricItemGroupBuilder.build(new Identifier(ModInfo.MOD_ID + ":player_plates"), new Supplier<ItemStack>() {
        @Override
        public ItemStack get() {
            return new ItemStack(obsidianPlate);
        }
    });

    private final Settings defaultSettings = new Settings().itemGroup(creativeTab);

    PPItems(PlayerPlates mod) {
        obsidianPlate = this.register("obsidian_plate", mod.blocks.obsidianPlate, this.defaultSettings);
        invisibleObsidianPlate = this.register("invisible_obsidian_plate", mod.blocks.invisibleObsidianPlate, this.defaultSettings);

        mossyPlate = this.register("mossy_plate", mod.blocks.mossyPlate, this.defaultSettings);
        invisibleMossyPlate = this.register("invisible_mossy_plate", mod.blocks.invisibleMossyPlate, this.defaultSettings);
    }

    private Item register(String id, Item item) {
        return Registry.ITEM.register(new Identifier(ModInfo.MOD_ID, id), item);
    }

    private Item register(String id, Block block, Settings settings) {

        return this.register(id, new DescriptiveBlockItem(block, settings));
    }
}
