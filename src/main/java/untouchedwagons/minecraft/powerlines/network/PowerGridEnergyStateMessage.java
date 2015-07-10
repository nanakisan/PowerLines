package untouchedwagons.minecraft.powerlines.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.grids.PowerGrid;
import untouchedwagons.minecraft.powerlines.grids.PowerGridWorldSavedData;

import java.util.UUID;

public class PowerGridEnergyStateMessage extends AbstractMessage<PowerGridEnergyStateMessage> {
    private UUID grid_uuid;
    private int energy;

    public PowerGridEnergyStateMessage() {
    }

    public PowerGridEnergyStateMessage(UUID grid_uuid, int energy) {
        this.grid_uuid = grid_uuid;
        this.energy = energy;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.grid_uuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
        this.energy = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, this.grid_uuid.toString());
        buf.writeInt(this.energy);
    }

    @Override
    public IMessage onMessage(PowerGridEnergyStateMessage message, MessageContext ctx) {
        World world = Minecraft.getMinecraft().theWorld;
        PowerGrid grid = PowerGridWorldSavedData.get(world).getGridByUUID(message.getGridUUID());

        grid.setEnergyStored(message.getEnergy());

        return null;
    }

    public UUID getGridUUID() {
        return grid_uuid;
    }

    public int getEnergy() {
        return energy;
    }
}
