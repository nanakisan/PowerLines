package untouchedwagons.minecraft.powerlines.tileentity;

import cofh.api.energy.IEnergyConnection;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyReceiver;
import cofh.api.energy.IEnergyStorage;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.ForgeDirection;
import untouchedwagons.minecraft.powerlines.extra.*;
import untouchedwagons.minecraft.powerlines.grids.PowerGrid;
import untouchedwagons.minecraft.powerlines.grids.PowerGridWorldSavedData;

public class TileEntitySubStation extends TileEntityPowerGridNode implements IBoundingBlock, IEnergyStorage, IEnergyConnection, IEnergyHandler, IWrenchable {

    private EnergyMode mode = EnergyMode.INPUT;
    private Rotation rotation = Rotation.NORTH_SOUTH;

    public TileEntitySubStation() {

    }

    @Override
    public void onPlace(EntityLivingBase entity) {
        int player_direction = MathHelper.floor_double((double) (entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        switch (player_direction)
        {
            case 0:
            case 2:
                this.rotation = Rotation.NORTH_SOUTH;
                PowerLineUtils.placeDumbFluxedBoundingBlock(this.worldObj, this.xCoord, this.yCoord + 3, this.zCoord - 1, this.xCoord, this.yCoord, this.zCoord);
                PowerLineUtils.placeDumbFluxedBoundingBlock(this.worldObj, this.xCoord, this.yCoord + 3, this.zCoord + 1, this.xCoord, this.yCoord, this.zCoord);
                PowerLineUtils.placeFluxedBoundingBlock(this.worldObj, this.xCoord, this.yCoord, this.zCoord - 1, this.xCoord, this.yCoord, this.zCoord);
                PowerLineUtils.placeFluxedBoundingBlock(this.worldObj, this.xCoord, this.yCoord, this.zCoord + 1, this.xCoord, this.yCoord, this.zCoord);
                PowerLineUtils.placeDumbFluxedBoundingBlock(this.worldObj, this.xCoord - 1, this.yCoord, this.zCoord, this.xCoord, this.yCoord, this.zCoord);
                PowerLineUtils.placeDumbFluxedBoundingBlock(this.worldObj, this.xCoord + 1, this.yCoord, this.zCoord, this.xCoord, this.yCoord, this.zCoord);

                break;
            case 1:
            case 3:
                this.rotation = Rotation.EAST_WEST;
                PowerLineUtils.placeDumbFluxedBoundingBlock(this.worldObj, this.xCoord - 1, this.yCoord + 3, this.zCoord, this.xCoord, this.yCoord, this.zCoord);
                PowerLineUtils.placeDumbFluxedBoundingBlock(this.worldObj, this.xCoord + 1, this.yCoord + 3, this.zCoord, this.xCoord, this.yCoord, this.zCoord);
                PowerLineUtils.placeFluxedBoundingBlock(this.worldObj, this.xCoord - 1, this.yCoord, this.zCoord, this.xCoord, this.yCoord, this.zCoord);
                PowerLineUtils.placeFluxedBoundingBlock(this.worldObj, this.xCoord + 1, this.yCoord, this.zCoord, this.xCoord, this.yCoord, this.zCoord);
                PowerLineUtils.placeDumbFluxedBoundingBlock(this.worldObj, this.xCoord, this.yCoord, this.zCoord - 1, this.xCoord, this.yCoord, this.zCoord);
                PowerLineUtils.placeDumbFluxedBoundingBlock(this.worldObj, this.xCoord, this.yCoord, this.zCoord + 1, this.xCoord, this.yCoord, this.zCoord);
                break;
        }

        PowerLineUtils.placeDumbFluxedBoundingBlock(this.worldObj, this.xCoord, this.yCoord + 3, this.zCoord, this.xCoord, this.yCoord, this.zCoord);

        // The four corners are dumb fluxed bounding boxes. They can report the energy state, but cannot handle power
        PowerLineUtils.placeDumbFluxedBoundingBlock(this.worldObj, this.xCoord - 1, this.yCoord, this.zCoord - 1, this.xCoord, this.yCoord, this.zCoord);
        PowerLineUtils.placeDumbFluxedBoundingBlock(this.worldObj, this.xCoord - 1, this.yCoord, this.zCoord + 1, this.xCoord, this.yCoord, this.zCoord);
        PowerLineUtils.placeDumbFluxedBoundingBlock(this.worldObj, this.xCoord + 1, this.yCoord, this.zCoord - 1, this.xCoord, this.yCoord, this.zCoord);
        PowerLineUtils.placeDumbFluxedBoundingBlock(this.worldObj, this.xCoord + 1, this.yCoord, this.zCoord + 1, this.xCoord, this.yCoord, this.zCoord);

        for (int x = -1; x < 2; x++)
        {
            for (int y = 1; y < 3; y++)
            {
                for (int z = -1; z < 2; z++)
                {
                    PowerLineUtils.placeDumbFluxedBoundingBlock(this.worldObj, this.xCoord + x, this.yCoord + y, this.zCoord + z, this.xCoord, this.yCoord, this.zCoord);
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

        this.worldObj.setBlockToAir(this.xCoord, this.yCoord + 3, this.zCoord);

        if (this.getRotation() == Rotation.NORTH_SOUTH)
        {
            this.worldObj.setBlockToAir(this.xCoord, this.yCoord + 3, this.zCoord - 1);
            this.worldObj.setBlockToAir(this.xCoord, this.yCoord + 3, this.zCoord + 1);
        }
        else if (this.getRotation() == Rotation.EAST_WEST)
        {
            this.worldObj.setBlockToAir(this.xCoord - 1, this.yCoord + 3, this.zCoord);
            this.worldObj.setBlockToAir(this.xCoord + 1, this.yCoord + 3, this.zCoord);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        this.mode = EnergyMode.fromString(nbt.getString("mode"));
        this.rotation = Rotation.fromString(nbt.getString("rotation"));
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        nbt.setString("mode", this.mode.toString());
        nbt.setString("rotation", this.rotation.toString());
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

    public Rotation getRotation()
    {
        return this.rotation;
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

        IEnergyReceiver ier = (IEnergyReceiver) te;

        grid.setEnergyStored(grid.getEnergyStored() - ier.receiveEnergy(ForgeDirection.UP, grid.getEnergyStored(), false));
    }

    @Override
    public void wrench() {
        this.mode = this.mode.getOpposite();
    }
}
