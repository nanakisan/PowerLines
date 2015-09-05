package untouchedwagons.minecraft.powerlines.network.grids;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.grids.PowerGrid;
import untouchedwagons.minecraft.powerlines.grids.PowerGridNode;
import untouchedwagons.minecraft.powerlines.grids.PowerGridWorldSavedData;

import java.util.UUID;

public class PowerGridNodeConnectedMessage extends AbstractGridNodeMessage<PowerGridNodeConnectedMessage> {
    private boolean is_substation;
    private String node_type;

    public PowerGridNodeConnectedMessage() {
    }

    public PowerGridNodeConnectedMessage(UUID grid_uuid, UUID node_uuid, int x, int y, int z, boolean is_substation, String node_type) {
        super(grid_uuid, node_uuid, x, y, z);

        this.is_substation = is_substation;
        this.node_type = node_type;
    }

    public PowerGridNodeConnectedMessage(UUID grid_uuid, PowerGridNode node)
    {
        this(grid_uuid, node.getNodeUUID(), node.getX(), node.getY(), node.getZ(), node.isSubStation(), node.getNodeType());
    }

    @Override
    public IMessage onMessage(PowerGridNodeConnectedMessage message, MessageContext ctx) {
        World world = Minecraft.getMinecraft().theWorld;
        PowerGrid grid = PowerGridWorldSavedData.get(world).getGridByUUID(message.getGridUUID());

        PowerGridNode node = new PowerGridNode(message.getNodeUUID(), message.getX(), message.getY(), message.getZ(), message.isSubStation(), message.getNodeType());

        grid.connectGridNode(node);

        return null;
    }

    public boolean isSubStation() {
        return is_substation;
    }

    public String getNodeType() {
        return node_type;
    }
}
