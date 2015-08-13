package untouchedwagons.minecraft.powerlines.network.grids;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import untouchedwagons.minecraft.powerlines.network.AbstractMessage;

import java.util.UUID;

public abstract class AbstractPowerGridMessage<T extends IMessage> extends AbstractMessage<T> {
    private UUID grid_uuid;

    public AbstractPowerGridMessage() {
    }

    public AbstractPowerGridMessage(UUID grid_uuid) {
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

    public UUID getGridUUID() {
        return grid_uuid;
    }
}
