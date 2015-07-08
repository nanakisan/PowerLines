package untouchedwagons.minecraft.powerlines.tileentity;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import untouchedwagons.minecraft.powerlines.blocks.BlockPowerLine;

public abstract class TileEntityPowerLine extends TileEntityPowerGridNode
{
    public TileEntityPowerLine() {

    }

    public BlockPowerLine.PowerLineInfo getPowerLineInfo()
    {
        Block b = this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord);

        return ((BlockPowerLine)b).getPoleInfo();
    }
}
