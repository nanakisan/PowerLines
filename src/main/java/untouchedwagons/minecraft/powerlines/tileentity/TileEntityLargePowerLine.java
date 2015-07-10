package untouchedwagons.minecraft.powerlines.tileentity;

import untouchedwagons.minecraft.powerlines.extra.IBoundingBlock;
import untouchedwagons.minecraft.powerlines.extra.PowerLineUtils;

public class TileEntityLargePowerLine extends TileEntityPowerGridNode implements IBoundingBlock {

    @Override
    public void onPlace() {
        PowerLineUtils.placeBoundingBlock(this.worldObj, this.xCoord, this.yCoord + 1, this.zCoord, this.xCoord, this.yCoord, this.zCoord);
        PowerLineUtils.placeBoundingBlock(this.worldObj, this.xCoord, this.yCoord + 2, this.zCoord, this.xCoord, this.yCoord, this.zCoord);
        PowerLineUtils.placeBoundingBlock(this.worldObj, this.xCoord, this.yCoord + 3, this.zCoord, this.xCoord, this.yCoord, this.zCoord);
    }

    @Override
    public void onBreak() {
        this.worldObj.setBlockToAir(this.xCoord, this.yCoord + 1, this.zCoord);
        this.worldObj.setBlockToAir(this.xCoord, this.yCoord + 2, this.zCoord);
        this.worldObj.setBlockToAir(this.xCoord, this.yCoord + 3, this.zCoord);
    }
}
