package untouchedwagons.minecraft.powerlines.network.grids;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.grids.PowerGrid;
import untouchedwagons.minecraft.powerlines.grids.PowerGridNode;
import untouchedwagons.minecraft.powerlines.grids.PowerGridWorldSavedData;

import java.util.UUID;

public class PowerGridConnectionStateMessage extends AbstractPowerGridMessage<PowerGridConnectionStateMessage> {
    private boolean is_connected;

    public PowerGridConnectionStateMessage() {

    }

    public PowerGridConnectionStateMessage(UUID grid_uuid, boolean is_connected) {
        super(grid_uuid);

        this.is_connected = is_connected;
    }

    @Override
    public IMessage onMessage(PowerGridConnectionStateMessage message, MessageContext ctx) {
        World world = Minecraft.getMinecraft().theWorld;
        PowerGridWorldSavedData pgwsd = PowerGridWorldSavedData.get(world);
        PowerGrid grid = pgwsd.getGridByUUID(message.getGridUUID());

        grid.setIsConnected(message.isConnected());

        return null;
    }

    public boolean isConnected() {
        return is_connected;
    }
}
