package untouchedwagons.minecraft.powerlines.tileentity;

import net.minecraft.entity.EntityLivingBase;
import untouchedwagons.minecraft.powerlines.extra.ConnectionPointCoordinateCalculator;
import untouchedwagons.minecraft.powerlines.extra.IBoundingBlock;
import untouchedwagons.minecraft.powerlines.extra.PowerLineUtils;

public class TileEntityLargePowerLine extends TileEntityPowerLine implements IBoundingBlock {

    public TileEntityLargePowerLine() {
        super(new ConnectionPointCoordinateCalculator(8));
    }

    @Override
    public void onPlace(EntityLivingBase entity) {
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
