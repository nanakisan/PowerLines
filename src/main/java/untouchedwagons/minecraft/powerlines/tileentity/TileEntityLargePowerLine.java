package untouchedwagons.minecraft.powerlines.tileentity;

import net.minecraft.util.AxisAlignedBB;

public class TileEntityLargePowerLine extends TileEntityPowerGridNode {

    public TileEntityLargePowerLine() {
        super();
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.getBoundingBox(this.xCoord - 2, this.yCoord, this.zCoord - 2, this.xCoord + 1, this.yCoord + 8, this.zCoord + 1);
    }
}
