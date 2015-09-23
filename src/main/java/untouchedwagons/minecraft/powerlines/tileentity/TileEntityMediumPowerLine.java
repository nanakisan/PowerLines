package untouchedwagons.minecraft.powerlines.tileentity;

import net.minecraft.util.AxisAlignedBB;

public class TileEntityMediumPowerLine extends TileEntityPowerGridNode {
    public TileEntityMediumPowerLine() {
        super();
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.getBoundingBox(this.xCoord, this.yCoord, this.zCoord, this.xCoord, this.yCoord + 7, this.zCoord);
    }
}
