package untouchedwagons.minecraft.powerlines.items.blocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import untouchedwagons.minecraft.powerlines.PowerLinesMod;

import java.util.List;

public class ItemBlockSubStation extends ItemBlock {
    public ItemBlockSubStation(Block p_i45328_1_) {
        super(p_i45328_1_);

        this.setFull3D();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean p_77624_4_) {
        tooltip.add(StatCollector.translateToLocal("tooltip.substation-info.1"));
        tooltip.add(String.format(StatCollector.translateToLocal("tooltip.substation-info.2"), PowerLinesMod.config.get("power-grid", "max-energy", 1000000).getInt()));
        tooltip.add(String.format(StatCollector.translateToLocal("tooltip.substation-info.3"), PowerLinesMod.config.get("substation", "max-distance", 8).getInt()));
        tooltip.add(String.format(StatCollector.translateToLocal("tooltip.substation-info.4"), PowerLinesMod.config.get("substation", "max-angle", 45).getInt()));
        tooltip.add(StatCollector.translateToLocal("tooltip.substation-info.5"));
    }
}
