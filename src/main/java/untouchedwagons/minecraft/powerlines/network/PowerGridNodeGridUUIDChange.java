package untouchedwagons.minecraft.powerlines.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityPowerGridNode;

import java.util.UUID;

public class PowerGridNodeGridUUIDChange extends AbstractMessage<PowerGridNodeGridUUIDChange> {
    private int x, y, z;
    private UUID grid_uuid;

    public PowerGridNodeGridUUIDChange() {
    }

    public PowerGridNodeGridUUIDChange(int x, int y, int z, UUID grid_uuid) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.grid_uuid = grid_uuid;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.grid_uuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.x);
        buf.writeInt(this.y);
        buf.writeInt(this.z);
        ByteBufUtils.writeUTF8String(buf, this.grid_uuid.toString());
    }

    @Override
    public IMessage onMessage(PowerGridNodeGridUUIDChange message, MessageContext ctx) {
        World world = Minecraft.getMinecraft().theWorld;

        if (!world.blockExists(message.getX(), message.getY(), message.getZ()))
            return null;

        TileEntityPowerGridNode tepgn = (TileEntityPowerGridNode) world.getTileEntity(message.getX(), message.getY(), message.getZ());
        tepgn.setGridUUID(message.getGridUUID());

        return null;
    }

    public UUID getGridUUID() {
        return grid_uuid;
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
