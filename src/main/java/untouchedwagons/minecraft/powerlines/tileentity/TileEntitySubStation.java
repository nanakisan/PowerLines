package untouchedwagons.minecraft.powerlines.tileentity;

import cofh.api.energy.IEnergyConnection;
import cofh.api.energy.IEnergyStorage;
import net.minecraftforge.common.util.ForgeDirection;
import untouchedwagons.minecraft.powerlines.extra.IBoundingBlock;
import untouchedwagons.minecraft.powerlines.extra.PowerLineUtils;
import untouchedwagons.minecraft.powerlines.grids.PowerGrid;
import untouchedwagons.minecraft.powerlines.grids.PowerGridNode;
import untouchedwagons.minecraft.powerlines.grids.PowerGridWorldSavedData;

public class TileEntitySubStation extends TileEntityPowerLine implements IBoundingBlock, IEnergyStorage, IEnergyConnection {
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
        PowerGrid grid = PowerGridWorldSavedData.get(this.worldObj).getGridByUUID(this.getPowerGridUUID());
        PowerGridNode node = grid.getGridNode(this.xCoord, this.yCoord, this.zCoord);

        if (!node.isConnected())
            return 0;

        return grid.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        PowerGrid grid = PowerGridWorldSavedData.get(this.worldObj).getGridByUUID(this.getPowerGridUUID());
        PowerGridNode node = grid.getGridNode(this.xCoord, this.yCoord, this.zCoord);

        if (!node.isConnected())
            return 0;

        return grid.extractEnergy(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored() {
        PowerGrid grid = PowerGridWorldSavedData.get(this.worldObj).getGridByUUID(this.getPowerGridUUID());
        PowerGridNode node = grid.getGridNode(this.xCoord, this.yCoord, this.zCoord);

        if (!node.isConnected())
            return 0;

        return grid.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored() {
        PowerGrid grid = PowerGridWorldSavedData.get(this.worldObj).getGridByUUID(this.getPowerGridUUID());
        PowerGridNode node = grid.getGridNode(this.xCoord, this.yCoord, this.zCoord);

        if (!node.isConnected())
            return 0;

        return grid.getMaxEnergyStored();
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return from == ForgeDirection.DOWN;
    }
}
