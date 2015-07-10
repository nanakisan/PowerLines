package untouchedwagons.minecraft.powerlines.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.grids.PowerGrid;
import untouchedwagons.minecraft.powerlines.grids.PowerGridNode;
import untouchedwagons.minecraft.powerlines.grids.PowerGridWorldSavedData;

import java.util.UUID;

public class PowerGridNodeDisconnectedMessage extends AbstractMessage<PowerGridNodeDisconnectedMessage> {
    private UUID grid_uuid;
    private PowerGridNode node;

    public PowerGridNodeDisconnectedMessage() {
    }

    public PowerGridNodeDisconnectedMessage(UUID grid_uuid, PowerGridNode node) {
        this.grid_uuid = grid_uuid;
        this.node = node;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.grid_uuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));

        int x = buf.readInt(), y = buf.readInt(), z = buf.readInt();
        boolean is_sub_station = buf.readBoolean();
        String node_type = ByteBufUtils.readUTF8String(buf);

        this.node = new PowerGridNode(x, y, z, is_sub_station, false, node_type);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, this.grid_uuid.toString());
        buf.writeInt(this.node.getX());
        buf.writeInt(this.node.getY());
        buf.writeInt(this.node.getZ());
        buf.writeBoolean(this.node.isSubStation());
        ByteBufUtils.writeUTF8String(buf, this.node.getNodeType());
    }

    @Override
    public IMessage onMessage(PowerGridNodeDisconnectedMessage message, MessageContext ctx) {
        World world = Minecraft.getMinecraft().theWorld;
        PowerGrid grid = PowerGridWorldSavedData.get(world).getGridByUUID(message.getGridUUID());

        grid.disconnectGridNode(message.getNode());
        grid.connectGrid();

        return null;
    }

    public UUID getGridUUID() {
        return grid_uuid;
    }

    public PowerGridNode getNode() {
        return node;
    }
}
