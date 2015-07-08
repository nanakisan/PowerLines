package untouchedwagons.minecraft.powerlines.tileentity;

import cofh.api.energy.IEnergyStorage;

public class TileEntityFluxedBoundingBox extends TileEntityBoundingBox implements IEnergyStorage {
    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        if (!worldObj.blockExists(this.orig_x, this.orig_y, this.orig_z))
        {
            return 0;
        }

        TileEntitySubStation sub_station = (TileEntitySubStation) worldObj.getTileEntity(this.orig_x, this.orig_y, this.orig_z);

        return sub_station.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        if (!worldObj.blockExists(this.orig_x, this.orig_y, this.orig_z))
        {
            return 0;
        }

        TileEntitySubStation sub_station = (TileEntitySubStation) worldObj.getTileEntity(this.orig_x, this.orig_y, this.orig_z);

        return sub_station.extractEnergy(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored() {
        if (!worldObj.blockExists(this.orig_x, this.orig_y, this.orig_z))
        {
            return 0;
        }

        TileEntitySubStation sub_station = (TileEntitySubStation) worldObj.getTileEntity(this.orig_x, this.orig_y, this.orig_z);

        return sub_station.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored() {
        if (!worldObj.blockExists(this.orig_x, this.orig_y, this.orig_z))
        {
            return 0;
        }

        TileEntitySubStation sub_station = (TileEntitySubStation) worldObj.getTileEntity(this.orig_x, this.orig_y, this.orig_z);

        return sub_station.getMaxEnergyStored();
    }
}
