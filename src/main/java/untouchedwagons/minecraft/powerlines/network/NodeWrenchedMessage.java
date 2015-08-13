package untouchedwagons.minecraft.powerlines.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.extra.IWrenchable;

public class NodeWrenchedMessage extends AbstractMessage<NodeWrenchedMessage> {
    private int x, y, z;

    public NodeWrenchedMessage() {
    }

    public NodeWrenchedMessage(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.getX());
        buf.writeInt(this.getY());
        buf.writeInt(this.getZ());
    }

    @Override
    public IMessage onMessage(NodeWrenchedMessage message, MessageContext ctx) {
        World world = Minecraft.getMinecraft().theWorld;

        if (world.blockExists(message.getX(), message.getY(), message.getZ()))
        {
            IWrenchable wrenchable = (IWrenchable) world.getTileEntity(message.getX(), message.getY(), message.getZ());
            wrenchable.wrench();
        }

        return null;
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
