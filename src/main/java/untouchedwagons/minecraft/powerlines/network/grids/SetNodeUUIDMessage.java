package untouchedwagons.minecraft.powerlines.network.grids;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.network.AbstractMessage;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityPowerGridNode;

import java.util.UUID;

public class SetNodeUUIDMessage extends AbstractMessage<SetNodeUUIDMessage> {
    private int x, y, z;
    private UUID node_uuid;

    public SetNodeUUIDMessage() {
    }

    public SetNodeUUIDMessage(int x, int y, int z, UUID node_uuid) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.node_uuid = node_uuid;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.node_uuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, this.node_uuid.toString());
        buf.writeInt(this.getX());
        buf.writeInt(this.getY());
        buf.writeInt(this.getZ());
    }

    @Override
    public IMessage onMessage(SetNodeUUIDMessage message, MessageContext ctx) {
        World world = Minecraft.getMinecraft().theWorld;

        TileEntityPowerGridNode tepgn = (TileEntityPowerGridNode) world.getTileEntity(message.getX(), message.getY(), message.getZ());

        tepgn.setNodeUUID(this.getNodeUUID());

        return null;
    }

    public UUID getNodeUUID() {
        return node_uuid;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getZ() {
        return this.z;
    }
}
