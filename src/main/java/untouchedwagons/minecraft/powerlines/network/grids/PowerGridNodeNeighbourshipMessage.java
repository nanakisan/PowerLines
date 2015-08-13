package untouchedwagons.minecraft.powerlines.network.grids;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.grids.PowerGrid;
import untouchedwagons.minecraft.powerlines.grids.PowerGridNode;
import untouchedwagons.minecraft.powerlines.grids.PowerGridWorldSavedData;

import java.util.UUID;

// Is 'neighbourship' a word? It is now.
public class PowerGridNodeNeighbourshipMessage extends AbstractGridNodeMessage<PowerGridNodeNeighbourshipMessage> {
    private UUID other_node;

    public PowerGridNodeNeighbourshipMessage() {
    }

    public PowerGridNodeNeighbourshipMessage(UUID grid_uuid, UUID node_uuid, UUID other_node) {
        super(grid_uuid, node_uuid, 0, 0, 0);

        this.other_node = other_node;
    }

    @Override
    public IMessage onMessage(PowerGridNodeNeighbourshipMessage message, MessageContext ctx) {
        World world = Minecraft.getMinecraft().theWorld;
        PowerGrid grid = PowerGridWorldSavedData.get(world).getGridByUUID(message.getGridUUID());

        PowerGridNode alpha_node = grid.getGridNode(this.getNodeUUID());
        PowerGridNode bravo_node = grid.getGridNode(this.getOtherNodeUUID());

        alpha_node.getNeighbours().add(bravo_node);
        bravo_node.getNeighbours().add(alpha_node);

        return null;
    }

    public UUID getOtherNodeUUID() {
        return other_node;
    }
}
