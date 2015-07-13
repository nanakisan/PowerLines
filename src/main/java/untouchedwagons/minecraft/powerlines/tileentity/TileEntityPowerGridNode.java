package untouchedwagons.minecraft.powerlines.tileentity;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import untouchedwagons.minecraft.powerlines.blocks.BlockPowerLine;
import untouchedwagons.minecraft.powerlines.extra.Rotation;

import java.util.UUID;

public abstract class TileEntityPowerGridNode extends TileEntity {
    protected UUID node_uuid = UUID.randomUUID();
    protected UUID grid_uuid;

    public TileEntityPowerGridNode() {
        this(null);
    }

    public TileEntityPowerGridNode(UUID grid_uuid) {
        this.grid_uuid = grid_uuid;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        this.node_uuid = UUID.fromString(nbt.getString("node-uuid"));

        String grid_uuid = nbt.getString("grid-uuid");
        this.grid_uuid = grid_uuid.equals("") ? null : UUID.fromString(grid_uuid);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        nbt.setString("node-uuid", this.node_uuid.toString());
        nbt.setString("grid-uuid", this.grid_uuid != null ? this.grid_uuid.toString() : "");
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

    public BlockPowerLine.PowerLineInfo getPowerLineInfo()
    {
        Block b = this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord);

        return ((BlockPowerLine)b).getPoleInfo();
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
