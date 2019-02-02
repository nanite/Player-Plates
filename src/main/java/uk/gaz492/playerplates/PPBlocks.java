package uk.gaz492.playerplates;

import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import uk.gaz492.playerplates.blocks.BlockPlayerPlate;
import uk.gaz492.playerplates.util.ModInfo;

public class PPBlocks {

    public final Block obsidianPlate;
    public final Block invisibleObsidianPlate;

    public final Block mossyPlate;
    public final Block invisibleMossyPlate;

    PPBlocks(PlayerPlates mod) {
        this.obsidianPlate = this.register("obsidian_plate", new BlockPlayerPlate(Block.Settings.of(Material.STONE).strength(50.0F, 1200.0F).noCollision(), BlockPlayerPlate.Type.PLAYER, false));
        this.invisibleObsidianPlate = this.register("invisible_obsidian_plate", new BlockPlayerPlate(Block.Settings.of(Material.STONE).strength(50.0F, 1200.0F).noCollision(), BlockPlayerPlate.Type.PLAYER, true));

        this.mossyPlate = this.register("mossy_plate", new BlockPlayerPlate(Block.Settings.of(Material.STONE).strength(1.0F, 1.0F).noCollision(), BlockPlayerPlate.Type.ITEMS_MOB, false));
        this.invisibleMossyPlate = this.register("invisible_mossy_plate", new BlockPlayerPlate(Block.Settings.of(Material.STONE).strength(1.0F, 1.0F).noCollision(), BlockPlayerPlate.Type.ITEMS_MOB, true));
    }

    private Block register(String id, Block block) {
        return Registry.BLOCK.register(new Identifier(ModInfo.MOD_ID, id), block);
    }
}
