package uk.gaz492.playerplates.item;

import net.minecraft.block.Block;
import net.minecraft.item.block.BlockItem;

public class DescriptiveBlockItem extends BlockItem {

    public DescriptiveBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

//    @Override
//    public void buildTooltip(ItemStack itemStack, World world, List<TextComponent> tooltip, TooltipOptions tooltipOptions) {
//        tooltip.add(new TranslatableTextComponent("info." + this.getBlock().getTranslationKey()).applyFormat(TextFormat.GRAY));
//    }
}
