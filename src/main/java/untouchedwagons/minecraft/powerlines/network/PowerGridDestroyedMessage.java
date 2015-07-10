package untouchedwagons.minecraft.powerlines.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.grids.PowerGridWorldSavedData;

import java.util.UUID;

public class PowerGridDestroyedMessage extends AbstractMessage<PowerGridDestroyedMessage> {
    private UUID grid_uuid;

    public PowerGridDestroyedMessage() {
    }

    public PowerGridDestroyedMessage(UUID grid_uuid) {
        this.grid_uuid = grid_uuid;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.grid_uuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, this.grid_uuid.toString());
    }

    @Override
    public IMessage onMessage(PowerGridDestroyedMessage message, MessageContext ctx) {
        World world = Minecraft.getMinecraft().theWorld;
        PowerGridWorldSavedData.get(world).removePowerGrid(message.getGridUUID());

        return null;
    }

    public UUID getGridUUID() {
        return grid_uuid;
    }
}
