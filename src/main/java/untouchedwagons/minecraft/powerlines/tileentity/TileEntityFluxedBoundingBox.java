package untouchedwagons.minecraft.powerlines.tileentity;

import cofh.api.energy.IEnergyConnection;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyReceiver;
import cofh.api.energy.IEnergyStorage;
import cpw.mods.fml.common.FMLLog;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import untouchedwagons.minecraft.powerlines.grids.PowerGrid;
import untouchedwagons.minecraft.powerlines.grids.PowerGridNode;
import untouchedwagons.minecraft.powerlines.grids.PowerGridWorldSavedData;

public class TileEntityFluxedBoundingBox extends TileEntityBoundingBox implements IEnergyStorage, IEnergyConnection, IEnergyHandler {
    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        if (!worldObj.blockExists(this.orig_x, this.orig_y, this.orig_z))
        {
            FMLLog.info("Block doesn't exist");
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

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        return this.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
        return this.extractEnergy(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored(ForgeDirection from) {
        return this.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from) {
        return this.getMaxEnergyStored();
    }

    @Override
    public void updateEntity() {
        if (!this.worldObj.blockExists(this.xCoord, this.yCoord - 1, this.zCoord))
            return;

        if (!this.worldObj.blockExists(this.orig_x, this.orig_y, this.orig_z))
            return;

        TileEntitySubStation sub_station = (TileEntitySubStation) worldObj.getTileEntity(this.orig_x, this.orig_y, this.orig_z);
        TileEntity te = this.worldObj.getTileEntity(this.xCoord, this.yCoord - 1, this.zCoord);

        if (!(te instanceof IEnergyReceiver))
            return;

        PowerGrid grid = PowerGridWorldSavedData.get(this.worldObj).getGridByUUID(sub_station.getPowerGridUUID());
        PowerGridNode node = grid.getGridNode(this.orig_x, this.orig_y, this.orig_z);

        if (!node.isConnected())
            return;

        IEnergyReceiver ier = (IEnergyReceiver) te;

        grid.setEnergyStored(grid.getEnergyStored() - ier.receiveEnergy(ForgeDirection.UP, grid.getEnergyStored(), false));
    }
}
