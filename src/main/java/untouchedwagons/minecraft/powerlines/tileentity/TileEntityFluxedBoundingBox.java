package untouchedwagons.minecraft.powerlines.tileentity;

import cofh.api.energy.IEnergyConnection;
import cofh.api.energy.IEnergyStorage;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityFluxedBoundingBox extends TileEntityBoundingBox implements IEnergyStorage, IEnergyConnection {
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

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        TileEntitySubStation sub_station = (TileEntitySubStation) worldObj.getTileEntity(this.orig_x, this.orig_y, this.orig_z);

        return sub_station.canConnectEnergy(from);
    }
}
