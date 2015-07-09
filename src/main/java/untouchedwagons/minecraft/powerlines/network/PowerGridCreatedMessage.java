package untouchedwagons.minecraft.powerlines.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class PowerGridCreatedMessage extends AbstractMessage<PowerGridCreatedMessage> {
    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    @Override
    public IMessage onMessage(PowerGridCreatedMessage message, MessageContext ctx) {
        return null;
    }
}
