package untouchedwagons.minecraft.powerlines.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.grids.PowerGridWorldSavedData;

import java.util.UUID;

public class PowerGridCreatedMessage extends AbstractMessage<PowerGridCreatedMessage> {
    private UUID grid_uuid;

    public PowerGridCreatedMessage() {
    }

    public PowerGridCreatedMessage(UUID grid_uuid) {
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
    public IMessage onMessage(PowerGridCreatedMessage message, MessageContext ctx) {
        World world = Minecraft.getMinecraft().theWorld;
        PowerGridWorldSavedData.get(world).getGridByUUID(message.getGridUUID());

        return null;
    }

    public UUID getGridUUID() {
        return grid_uuid;
    }
}
