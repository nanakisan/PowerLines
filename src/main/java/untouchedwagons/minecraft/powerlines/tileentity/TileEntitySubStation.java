package untouchedwagons.minecraft.powerlines.tileentity;

import cofh.api.energy.IEnergyConnection;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyReceiver;
import cofh.api.energy.IEnergyStorage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import untouchedwagons.minecraft.powerlines.PowerLinesMod;
import untouchedwagons.minecraft.powerlines.extra.IBoundingBlock;
import untouchedwagons.minecraft.powerlines.extra.IRotatable;
import untouchedwagons.minecraft.powerlines.extra.PowerLineUtils;
import untouchedwagons.minecraft.powerlines.extra.Rotation;
import untouchedwagons.minecraft.powerlines.grids.PowerGrid;
import untouchedwagons.minecraft.powerlines.grids.PowerGridNode;
import untouchedwagons.minecraft.powerlines.grids.PowerGridWorldSavedData;
import untouchedwagons.minecraft.powerlines.network.NodeRotationMessage;

import java.util.List;
import java.util.UUID;

public class TileEntitySubStation extends TileEntityPowerGridNode implements IBoundingBlock, IEnergyStorage, IEnergyConnection, IEnergyHandler, IRotatable {

    private EnergyMode mode = EnergyMode.INPUT;
    private Rotation rotation = Rotation.NORTH_SOUTH;

    public TileEntitySubStation() {
        super(UUID.randomUUID());
    }

    @Override
    public void onPlace() {
        // The four corners are dumb fluxed bounding boxes. They can report the energy state, but cannot handle power
        PowerLineUtils.placeDumbFluxedBoundingBlock(this.worldObj, this.xCoord - 1, this.yCoord, this.zCoord - 1, this.xCoord, this.yCoord, this.zCoord);
        PowerLineUtils.placeDumbFluxedBoundingBlock(this.worldObj, this.xCoord - 1, this.yCoord, this.zCoord + 1, this.xCoord, this.yCoord, this.zCoord);
        PowerLineUtils.placeDumbFluxedBoundingBlock(this.worldObj, this.xCoord + 1, this.yCoord, this.zCoord - 1, this.xCoord, this.yCoord, this.zCoord);
        PowerLineUtils.placeDumbFluxedBoundingBlock(this.worldObj, this.xCoord + 1, this.yCoord, this.zCoord + 1, this.xCoord, this.yCoord, this.zCoord);

        // The four bounding blocks immediately adjacent to the sub station can handle power, half are active due to rotation
        PowerLineUtils.placeFluxedBoundingBlock(this.worldObj, this.xCoord, this.yCoord, this.zCoord - 1, this.xCoord, this.yCoord, this.zCoord);
        PowerLineUtils.placeFluxedBoundingBlock(this.worldObj, this.xCoord, this.yCoord, this.zCoord + 1, this.xCoord, this.yCoord, this.zCoord);
        PowerLineUtils.placeFluxedBoundingBlock(this.worldObj, this.xCoord - 1, this.yCoord, this.zCoord, this.xCoord, this.yCoord, this.zCoord);
        PowerLineUtils.placeFluxedBoundingBlock(this.worldObj, this.xCoord + 1, this.yCoord, this.zCoord, this.xCoord, this.yCoord, this.zCoord);

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
    public int receiveEnergy(int maxReceive, boolean simulate) {
        PowerGrid grid = PowerGridWorldSavedData.get(this.worldObj).getGridByUUID(this.getPowerGridUUID());
        PowerGridNode node = grid.getGridNode(this.getNodeUUID());

        if (!node.isConnected())
            return 0;

        return grid.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        PowerGrid grid = PowerGridWorldSavedData.get(this.worldObj).getGridByUUID(this.getPowerGridUUID());
        PowerGridNode node = grid.getGridNode(this.getNodeUUID());

        if (!node.isConnected())
            return 0;

        return grid.extractEnergy(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored() {
        PowerGrid grid = PowerGridWorldSavedData.get(this.worldObj).getGridByUUID(this.getPowerGridUUID());
        PowerGridNode node = grid.getGridNode(this.getNodeUUID());

        if (!node.isConnected())
            return 0;

        return grid.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored() {
        PowerGrid grid = PowerGridWorldSavedData.get(this.worldObj).getGridByUUID(this.getPowerGridUUID());
        PowerGridNode node = grid.getGridNode(this.getNodeUUID());

        if (!node.isConnected())
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
    public void rotate() {
        this.setRotation(this.rotation.getOpposite());
    }

    @Override
    public Rotation getRotation() {
        return this.rotation;
    }

    @Override
    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }

    @Override
    public void updateEntity() {
        if (!this.worldObj.blockExists(this.xCoord, this.yCoord - 1, this.zCoord))
            return;

        TileEntity te = this.worldObj.getTileEntity(this.xCoord, this.yCoord - 1, this.zCoord);

        if (!(te instanceof IEnergyReceiver))
            return;

        PowerGrid grid = PowerGridWorldSavedData.get(this.worldObj).getGridByUUID(this.getPowerGridUUID());
        PowerGridNode node = grid.getGridNode(this.getNodeUUID());

        if (!node.isConnected())
            return;

        IEnergyReceiver ier = (IEnergyReceiver) te;

        grid.setEnergyStored(grid.getEnergyStored() - ier.receiveEnergy(ForgeDirection.UP, grid.getEnergyStored(), false));
    }

    public enum EnergyMode
    {
        INPUT("input"), OUTPUT("output"), UNKNOWN("unknown");

        public static final EnergyMode[] VALID_MODES = { INPUT, OUTPUT };

        private final String mode;

        EnergyMode(String mode) {
            this.mode = mode;
        }

        public String getMode() {
            return mode;
        }

        public static EnergyMode fromString(String mode)
        {
            for (EnergyMode valid_mode : EnergyMode.VALID_MODES)
            {
                if (valid_mode.getMode().equals(mode))
                {
                    return valid_mode;
                }
            }

            return EnergyMode.UNKNOWN;
        }

        @Override
        public String toString() {
            return this.mode;
        }
    }
}
