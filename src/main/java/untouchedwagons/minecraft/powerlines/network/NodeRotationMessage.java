package untouchedwagons.minecraft.powerlines.network;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.extra.IRotatable;
import untouchedwagons.minecraft.powerlines.extra.Rotation;

public class NodeRotationMessage extends AbstractMessage<NodeRotationMessage> {
    private int x, y, z;
    private Rotation rotation;

    public NodeRotationMessage() {
    }

    public NodeRotationMessage(int x, int y, int z, Rotation rotation) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.rotation = rotation;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.rotation = Rotation.fromString(ByteBufUtils.readUTF8String(buf));
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.getX());
        buf.writeInt(this.getY());
        buf.writeInt(this.getZ());
        ByteBufUtils.writeUTF8String(buf, this.getRotation().toString());
    }

    @Override
    public IMessage onMessage(NodeRotationMessage message, MessageContext ctx) {
        World world = Minecraft.getMinecraft().theWorld;

        if (world.blockExists(message.getX(), message.getY(), message.getZ()))
        {
            IRotatable ir = (IRotatable) world.getTileEntity(message.getX(), message.getY(), message.getZ());
            ir.setRotation(message.getRotation());
        }

        return null;
    }

    public Rotation getRotation() {
        return rotation;
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
