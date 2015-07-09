package untouchedwagons.minecraft.powerlines.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import java.util.UUID;

public abstract class TileEntityPowerGridNode extends TileEntity {
    protected UUID node_uuid = UUID.randomUUID();
    protected UUID grid_uuid = UUID.randomUUID();

    public TileEntityPowerGridNode() {
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        this.node_uuid = UUID.fromString(nbt.getString("node-uuid"));
        this.grid_uuid = UUID.fromString(nbt.getString("grid-uuid"));
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        nbt.setString("node-uuid", this.node_uuid.toString());
        nbt.setString("grid-uuid", this.grid_uuid.toString());
    }

    public UUID getPowerGridUUID() {
        return grid_uuid;
    }

    public UUID getNodeUUID() {
        return node_uuid;
    }

    public void setGridUUID(UUID grid_uuid) {
        this.grid_uuid = grid_uuid;
        this.markDirty();
    }
}
