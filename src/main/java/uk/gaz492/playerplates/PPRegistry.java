package uk.gaz492.playerplates;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import uk.gaz492.playerplates.blocks.BlockPlayerPlate;
import uk.gaz492.playerplates.util.ModInfo;

import java.util.function.Supplier;

public class PPRegistry {

    public static Block obsidianPlateBlock;
    public static Item obsidianPlateItem;

    public static Block invisibleObsidianPlateBlock;
    public static Item invisibleObsidianPlateItem;

    public static Block mossyPlateBlock;
    public static Item mossyPlateItem;

    public static Block invisibleMossyPlateBlock;
    public static Item invisibleMossyPlateItem;

    public static ItemGroup creativeTab = FabricItemGroupBuilder.build(new Identifier(ModInfo.MOD_ID + ":player_plates"), new Supplier<ItemStack>() {
        @Override
        public ItemStack get() {
            return new ItemStack(obsidianPlateBlock);
        }
    });

    private final Item.Settings defaultSettings = new Item.Settings().group(creativeTab);

    PPRegistry(PlayerPlates mod) {
        obsidianPlateBlock = this.blockRegister("obsidian_plate", new BlockPlayerPlate(FabricBlockSettings.of(Material.STONE).strength(50.0F, 1200.0F).noCollision().build(), BlockPlayerPlate.Type.PLAYER, false));
        obsidianPlateItem = this.itemRegister("obsidian_plate", obsidianPlateBlock, this.defaultSettings);

        invisibleObsidianPlateBlock = this.blockRegister("invisible_obsidian_plate", new BlockPlayerPlate(FabricBlockSettings.of(Material.STONE).strength(50.0F, 1200.0F).noCollision().build(), BlockPlayerPlate.Type.PLAYER, true));
        invisibleObsidianPlateItem = this.itemRegister("invisible_obsidian_plate", invisibleObsidianPlateBlock, this.defaultSettings);

        mossyPlateBlock = this.blockRegister("mossy_plate", new BlockPlayerPlate(FabricBlockSettings.of(Material.STONE).strength(2.0F, 6.0F).noCollision().build(), BlockPlayerPlate.Type.ITEMS_MOB, false));
        mossyPlateItem = this.itemRegister("mossy_plate", mossyPlateBlock, this.defaultSettings);

        invisibleMossyPlateBlock = this.blockRegister("invisible_mossy_plate", new BlockPlayerPlate(FabricBlockSettings.of(Material.STONE).strength(2.0F, 6.0F).noCollision().build(), BlockPlayerPlate.Type.ITEMS_MOB, true));
        invisibleMossyPlateItem = this.itemRegister("invisible_mossy_plate", invisibleMossyPlateBlock, this.defaultSettings);
    }

    private Block blockRegister(String id, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(ModInfo.MOD_ID, id), block);
    }

    private Item itemRegister(String id, Block block, Item.Settings settings) {
        return Registry.register(Registry.ITEM, new Identifier(ModInfo.MOD_ID, id), new BlockItem(block, settings));
    }
}
