package untouchedwagons.minecraft.powerlines.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;

public abstract class AbstractMessage<T extends IMessage> implements IMessage, IMessageHandler<T, IMessage> {
}
