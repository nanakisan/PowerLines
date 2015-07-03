package untouchedwagons.minecraft.powerlines.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.PowerLineInfo;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityPowerLine;

abstract public class BlockPowerLine extends Block implements ITileEntityProvider
{
    private final PowerLineInfo pole_info;

    protected BlockPowerLine(Material p_i45394_1_, PowerLineInfo pole_info) {
        super(p_i45394_1_);

        this.pole_info = pole_info;

        this.setHardness(2F);
        this.setStepSound(Block.soundTypeStone);
        this.setCreativeTab(CreativeTabs.tabMisc);
    }

    public PowerLineInfo getPoleInfo() {
        return pole_info;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if (world.isRemote) return false;

        TileEntity te = world.getTileEntity(x, y, z);
        TileEntityPowerLine tepl = (TileEntityPowerLine)te;

        player.addChatComponentMessage(new ChatComponentText(String.format("%d", tepl.getEnergyStored())));

        return false;
    }
}
