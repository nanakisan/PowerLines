package untouchedwagons.minecraft.powerlines.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityBoundingBox;

public class BoundingBoxHomePositionMessage extends AbstractMessage<BoundingBoxHomePositionMessage> {
    private int x, y, z, orig_x, orig_y, orig_z;

    public BoundingBoxHomePositionMessage() {
    }

    public BoundingBoxHomePositionMessage(int x, int y, int z, int orig_x, int orig_y, int orig_z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.orig_x = orig_x;
        this.orig_y = orig_y;
        this.orig_z = orig_z;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.orig_x = buf.readInt();
        this.orig_y = buf.readInt();
        this.orig_z = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.getX());
        buf.writeInt(this.getY());
        buf.writeInt(this.getZ());
        buf.writeInt(this.getOrigX());
        buf.writeInt(this.getOrigY());
        buf.writeInt(this.getOrigZ());
    }

    @Override
    public IMessage onMessage(BoundingBoxHomePositionMessage message, MessageContext ctx) {
        World world = Minecraft.getMinecraft().theWorld;

        if (!world.blockExists(message.getX(), message.getY(), message.getZ()))
            return null;

        TileEntityBoundingBox tebb = (TileEntityBoundingBox) world.getTileEntity(message.getX(), message.getY(), message.getZ());

        tebb.setParentLocation(message.getOrigX(), message.getOrigY(), message.getOrigZ());

        return null;
    }

    public int getOrigX() {
        return orig_x;
    }

    public int getOrigY() {
        return orig_y;
    }

    public int getOrigZ() {
        return orig_z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}
