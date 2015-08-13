package untouchedwagons.minecraft.powerlines.network.grids;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

import java.util.UUID;

public abstract class AbstractGridNodeMessage<T extends IMessage> extends AbstractPowerGridMessage<T> {
    private int x, y, z;
    private UUID node_uuid;

    public AbstractGridNodeMessage() {
    }

    protected AbstractGridNodeMessage(UUID grid_uuid, UUID node_uuid, int x, int y, int z) {
        super(grid_uuid);
        this.node_uuid = node_uuid;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        super.fromBytes(buf);

        this.node_uuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        super.toBytes(buf);

        ByteBufUtils.writeUTF8String(buf, this.node_uuid.toString());
        buf.writeInt(this.getX());
        buf.writeInt(this.getY());
        buf.writeInt(this.getZ());
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
