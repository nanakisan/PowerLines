package untouchedwagons.minecraft.powerlines.items;

import cofh.api.item.IToolHammer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.PowerLinesMod;
import untouchedwagons.minecraft.powerlines.extra.IRotatable;
import untouchedwagons.minecraft.powerlines.extra.IWrenchable;
import untouchedwagons.minecraft.powerlines.extra.NetworkUtils;
import untouchedwagons.minecraft.powerlines.network.NodeRotationMessage;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityBoundingBox;

import java.util.List;

public class ItemScrewdriver extends Item implements IToolHammer {
    public ItemScrewdriver() {
        this.setUnlocalizedName("screwdriver");
        this.setTextureName("powerlines:screwdriver");
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.tabMisc);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
        list.add(String.format("%s%s%s", EnumChatFormatting.GOLD, StatCollector.translateToLocal("text.screwdriver.snark"), EnumChatFormatting.RESET));
    }

    @Override
    public boolean isUsable(ItemStack item, EntityLivingBase user, int x, int y, int z) {
        return true;
    }

    @Override
    public void toolUsed(ItemStack item, EntityLivingBase user, int x, int y, int z) {

    }
}
