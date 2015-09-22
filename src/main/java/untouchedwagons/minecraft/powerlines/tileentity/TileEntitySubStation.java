package untouchedwagons.minecraft.powerlines.tileentity;

import cofh.api.energy.IEnergyConnection;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyReceiver;
import cofh.api.energy.IEnergyStorage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.ForgeDirection;
import untouchedwagons.minecraft.powerlines.extra.*;
import untouchedwagons.minecraft.powerlines.grids.PowerGrid;
import untouchedwagons.minecraft.powerlines.grids.PowerGridWorldSavedData;

public class TileEntitySubStation extends TileEntityPowerGridNode implements IEnergyStorage, IEnergyConnection, IEnergyHandler, IWrenchable {

    private EnergyMode mode = EnergyMode.INPUT;

    public TileEntitySubStation() {

    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        this.mode = EnergyMode.fromString(nbt.getString("mode"));
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        nbt.setString("mode", this.mode.toString());
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbtTag = new NBTTagCompound();

        this.writeToNBT(nbtTag);

        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 0, nbtTag);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        this.readFromNBT(packet.func_148857_g());
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        if (this.getPowerGridUUID() == null)
            return 0;

        PowerGrid grid = PowerGridWorldSavedData.get(this.worldObj).getGridByUUID(this.getPowerGridUUID());

        if (!grid.isConnected())
            return 0;

        return grid.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        if (this.getPowerGridUUID() == null)
            return 0;

        PowerGrid grid = PowerGridWorldSavedData.get(this.worldObj).getGridByUUID(this.getPowerGridUUID());

        if (!grid.isConnected())
            return 0;

        return grid.extractEnergy(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored() {
        if (this.getPowerGridUUID() == null)
            return 0;

        PowerGrid grid = PowerGridWorldSavedData.get(this.worldObj).getGridByUUID(this.getPowerGridUUID());

        if (!grid.isConnected())
            return 0;

        return grid.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored() {
        if (this.getPowerGridUUID() == null)
            return 0;

        PowerGrid grid = PowerGridWorldSavedData.get(this.worldObj).getGridByUUID(this.getPowerGridUUID());

        if (!grid.isConnected())
            return 0;

        return grid.getMaxEnergyStored();
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return from == ForgeDirection.DOWN;
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

    public EnergyMode getEnergyMode() {
        return mode;
    }

    @Override
    public boolean requiresGridUUID() {
        return true;
    }

    @Override
    public void updateEntity() {
        if (!this.worldObj.blockExists(this.xCoord, this.yCoord - 1, this.zCoord))
            return;

        TileEntity te = this.worldObj.getTileEntity(this.xCoord, this.yCoord - 1, this.zCoord);

        if (!(te instanceof IEnergyReceiver))
            return;

        if (this.getPowerGridUUID() == null)
            return;

        PowerGrid grid = PowerGridWorldSavedData.get(this.worldObj).getGridByUUID(this.getPowerGridUUID());

        if (!grid.isConnected())
            return;

        if (this.getEnergyMode() != EnergyMode.OUTPUT)
            return;

        IEnergyReceiver ier = (IEnergyReceiver) te;

        grid.setEnergyStored(grid.getEnergyStored() - ier.receiveEnergy(ForgeDirection.UP, grid.getEnergyStored(), false));
    }

    @Override
    public void wrench() {
        this.mode = this.mode.getOpposite();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.getBoundingBox(this.xCoord - 2, this.yCoord, this.zCoord - 2, this.xCoord + 1, this.yCoord + 3, this.zCoord + 1);
    }
}
