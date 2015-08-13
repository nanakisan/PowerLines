package untouchedwagons.minecraft.powerlines.network.grids;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.grids.PowerGrid;
import untouchedwagons.minecraft.powerlines.grids.PowerGridWorldSavedData;
import untouchedwagons.minecraft.powerlines.network.AbstractMessage;

import java.util.UUID;

public class PowerGridEnergyStateMessage extends AbstractPowerGridMessage<PowerGridEnergyStateMessage> {
    private int energy;

    public PowerGridEnergyStateMessage() {
    }

    public PowerGridEnergyStateMessage(UUID grid_uuid, int energy) {
        super(grid_uuid);

        this.energy = energy;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        super.fromBytes(buf);

        this.energy = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        super.toBytes(buf);

        buf.writeInt(this.energy);
    }

    @Override
    public IMessage onMessage(PowerGridEnergyStateMessage message, MessageContext ctx) {
        World world = Minecraft.getMinecraft().theWorld;
        PowerGrid grid = PowerGridWorldSavedData.get(world).getGridByUUID(message.getGridUUID());

        grid.setEnergyStored(message.getEnergy());

        return null;
    }

    public int getEnergy() {
        return energy;
    }
}
