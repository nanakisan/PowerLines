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

public class SetGridUUIDMessage extends AbstractMessage<SetGridUUIDMessage> {
    private int x, y, z;
    private UUID grid_uuid;

    public SetGridUUIDMessage() {
    }

    public SetGridUUIDMessage(int x, int y, int z, UUID grid_uuid) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.grid_uuid = grid_uuid;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.grid_uuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, this.grid_uuid.toString());
        buf.writeInt(this.getX());
        buf.writeInt(this.getY());
        buf.writeInt(this.getZ());
    }

    @Override
    public IMessage onMessage(SetGridUUIDMessage message, MessageContext ctx) {
        World world = Minecraft.getMinecraft().theWorld;

        TileEntityPowerGridNode tepgn = (TileEntityPowerGridNode) world.getTileEntity(message.getX(), message.getY(), message.getZ());

        tepgn.setGridUUID(message.getGridUUID());

        return null;
    }

    public UUID getGridUUID() {
        return grid_uuid;
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
