package untouchedwagons.minecraft.powerlines.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.PowerLinesMod;
import untouchedwagons.minecraft.powerlines.extra.IRotatable;
import untouchedwagons.minecraft.powerlines.extra.IWrenchable;
import untouchedwagons.minecraft.powerlines.extra.NetworkUtils;
import untouchedwagons.minecraft.powerlines.network.NodeRotationMessage;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityBoundingBox;

import java.util.List;

public class ItemScrewdriver extends Item {
    public ItemScrewdriver() {
        this.setUnlocalizedName("screwdriver");
        this.setTextureName("powerlines:screwdriver");
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.tabMisc);
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        TileEntity te = world.getTileEntity(x, y, z);
        TileEntityBoundingBox tebb = null;

        if (te != null && te instanceof TileEntityBoundingBox)
        {
            tebb = (TileEntityBoundingBox) te;

            te = world.getTileEntity(tebb.orig_x, tebb.orig_y, tebb.orig_z);

            if (te == null)
            {
                return false;
            }
        }

        if (te != null && te instanceof IRotatable && player.isSneaking())
        {
            IRotatable rotatable = (IRotatable) te;
            rotatable.rotate();

            NodeRotationMessage message = new NodeRotationMessage(te.xCoord, te.yCoord, te.zCoord, ((IRotatable) te).getRotation());

            NetworkUtils.broadcastToWorld(world, message);

            return false;
        }

        if (te != null && te instanceof IWrenchable && !player.isSneaking())
        {
            IWrenchable wrenchable = (IWrenchable) te;
            wrenchable.wrench();
        }

        return false;
    }
}
