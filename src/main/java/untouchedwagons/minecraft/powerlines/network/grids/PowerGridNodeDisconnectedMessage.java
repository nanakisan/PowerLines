package untouchedwagons.minecraft.powerlines.network.grids;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.grids.PowerGrid;
import untouchedwagons.minecraft.powerlines.grids.PowerGridWorldSavedData;

import java.util.UUID;

public class PowerGridNodeDisconnectedMessage extends AbstractGridNodeMessage<PowerGridNodeDisconnectedMessage> {
    public PowerGridNodeDisconnectedMessage() {
    }

    public PowerGridNodeDisconnectedMessage(UUID grid_uuid, UUID node_uuid, int x, int y, int z) {
        super(grid_uuid, node_uuid, x, y, z);
    }

    @Override
    public IMessage onMessage(PowerGridNodeDisconnectedMessage message, MessageContext ctx) {
        World world = Minecraft.getMinecraft().theWorld;
        PowerGrid grid = PowerGridWorldSavedData.get(world).getGridByUUID(message.getGridUUID());

        grid.disconnectGridNode(grid.getGridNode(message.getNodeUUID()));

        return null;
    }
}
