package untouchedwagons.minecraft.powerlines.tileentity;

import cofh.api.energy.IEnergyStorage;
import untouchedwagons.minecraft.powerlines.extra.IBoundingBlock;
import untouchedwagons.minecraft.powerlines.extra.PowerLineUtils;

public class TileEntitySubStation extends TileEntityPowerGridNode implements IBoundingBlock, IEnergyStorage {

    public TileEntitySubStation() {

    }

    @Override
    public void onPlace() {
        PowerLineUtils.placeBoundingBlock(this.worldObj, this.xCoord - 1, this.yCoord, this.zCoord - 1, this.xCoord, this.yCoord, this.zCoord);
        PowerLineUtils.placeBoundingBlock(this.worldObj, this.xCoord - 1, this.yCoord, this.zCoord, this.xCoord, this.yCoord, this.zCoord);
        PowerLineUtils.placeBoundingBlock(this.worldObj, this.xCoord - 1, this.yCoord, this.zCoord + 1, this.xCoord, this.yCoord, this.zCoord);
        PowerLineUtils.placeFluxedBoundingBlock(this.worldObj, this.xCoord, this.yCoord, this.zCoord - 1, this.xCoord, this.yCoord, this.zCoord);
        PowerLineUtils.placeFluxedBoundingBlock(this.worldObj, this.xCoord, this.yCoord, this.zCoord + 1, this.xCoord, this.yCoord, this.zCoord);
        PowerLineUtils.placeBoundingBlock(this.worldObj, this.xCoord + 1, this.yCoord, this.zCoord - 1, this.xCoord, this.yCoord, this.zCoord);
        PowerLineUtils.placeBoundingBlock(this.worldObj, this.xCoord + 1, this.yCoord, this.zCoord, this.xCoord, this.yCoord, this.zCoord);
        PowerLineUtils.placeBoundingBlock(this.worldObj, this.xCoord + 1, this.yCoord, this.zCoord + 1, this.xCoord, this.yCoord, this.zCoord);

        for (int x = -1; x < 2; x++)
        {
            for (int y = 1; y < 3; y++)
            {
                for (int z = -1; z < 2; z++)
                {
                    PowerLineUtils.placeBoundingBlock(this.worldObj, this.xCoord + x, this.yCoord + y, this.zCoord + z, this.xCoord, this.yCoord, this.zCoord);
                }
            }
        }
    }

    @Override
    public void onBreak() {
        for (int x = -1; x < 2; x++)
        {
            for (int y = 0; y < 3; y++)
            {
                for (int z = -1; z < 2; z++)
                {
                    if (x == 0 && y == 0 && z == 0)
                        continue;

                    this.worldObj.setBlockToAir(this.xCoord + x, this.yCoord + y, this.zCoord + z);
                }
            }
        }
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return 0;
    }

    @Override
    public int getEnergyStored() {
        return 0;
    }

    @Override
    public int getMaxEnergyStored() {
        return 0;
    }
}
