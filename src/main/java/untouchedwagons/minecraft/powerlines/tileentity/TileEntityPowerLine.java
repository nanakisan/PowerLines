package untouchedwagons.minecraft.powerlines.tileentity;

import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyReceiver;
import cofh.api.energy.IEnergyStorage;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import untouchedwagons.minecraft.powerlines.blocks.BlockPowerLine;

public abstract class TileEntityPowerLine extends TileEntity implements IEnergyStorage, IEnergyHandler
{
    protected int energy_level = 0;

    public TileEntityPowerLine() {

    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate)
    {
        int max_energy = this.getMaxEnergyStored();

        if (maxReceive > this.getPowerLineInfo().max_io)
            maxReceive = this.getPowerLineInfo().max_io;

        if (this.energy_level + max_energy > max_energy)
            maxReceive = max_energy - this.energy_level;

        if (!simulate)
            this.energy_level += maxReceive;

        return maxReceive;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate)
    {
        if (maxExtract > this.getPowerLineInfo().max_io)
            maxExtract = this.getPowerLineInfo().max_io;

        if (maxExtract > this.energy_level)
            maxExtract = this.energy_level;

        if (!simulate)
            this.energy_level -= maxExtract;

        return maxExtract;
    }

    @Override
    public int getEnergyStored()
    {
        return this.energy_level;
    }

    @Override
    public int getMaxEnergyStored()
    {
        return this.getPowerLineInfo().max_energy;
    }

    public BlockPowerLine.PowerLineInfo getPowerLineInfo()
    {
        Block b = this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord);

        return ((BlockPowerLine)b).getPoleInfo();
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
        return this.getEnergyStored();
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        this.energy_level = nbt.getInteger("energy-level");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        nbt.setInteger("energy-level", this.energy_level);
    }

    @Override
    public void updateEntity() {
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
        {
            TileEntity te = this.worldObj.getTileEntity(this.xCoord + direction.offsetX, this.yCoord + direction.offsetY, this.zCoord + direction.offsetZ);

            if (te == null) continue;

            if (te instanceof IEnergyReceiver)
            {
                int max_send = this.energy_level > this.getPowerLineInfo().max_io ? this.getPowerLineInfo().max_io : this.energy_level;

                this.energy_level -= ((IEnergyReceiver)te).receiveEnergy(direction.getOpposite(), max_send, false);
            }
        }
    }
}
