package untouchedwagons.minecraft.powerlines.tileentity;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBoundingBox extends TileEntity {
    public int orig_x, orig_y, orig_z;

    public void setParentLocation(int orig_x, int orig_y, int orig_z) {
        this.orig_x = orig_x;
        this.orig_y = orig_y;
        this.orig_z = orig_z;

        this.markDirty();
    }
    
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        this.orig_x = nbt.getInteger("orig-x");
        this.orig_y = nbt.getInteger("orig-y");
        this.orig_z = nbt.getInteger("orig-z");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        nbt.setInteger("orig-x", this.orig_x);
        nbt.setInteger("orig-y", this.orig_y);
        nbt.setInteger("orig-z", this.orig_z);
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
}
