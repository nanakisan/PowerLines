package untouchedwagons.minecraft.powerlines.grids;

import net.minecraft.nbt.NBTTagCompound;
import untouchedwagons.math.MathHelper;
import untouchedwagons.minecraft.powerlines.blocks.BlockPowerLine;

import java.util.LinkedList;
import java.util.List;

public class PowerGridNode {
    private int x;
    private int y;
    private int z;
    private boolean is_sub_station;
    private boolean is_connected = false;
    private String node_type;

    private final List<PowerGridNode> neighbours = new LinkedList<PowerGridNode>();

    public PowerGridNode() {

    }

    public PowerGridNode(int x, int y, int z, boolean is_sub_station, boolean is_connected, String node_type) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.is_sub_station = is_sub_station;
        this.is_connected = is_connected;
        this.node_type = node_type;
    }

    public void findNeighours(List<PowerGridNode> nodes)
    {
        BlockPowerLine.PowerLineInfo this_node = BlockPowerLine.getPowerLineInfoByType(this.getNodeType());

        for (PowerGridNode node : nodes)
        {
            if (node == this)
                continue;

            BlockPowerLine.PowerLineInfo that_node = BlockPowerLine.getPowerLineInfoByType(node.getNodeType());

            int max_distance = this_node.max_distance > that_node.max_distance ? that_node.max_distance : this_node.max_distance;
            double max_angle = this_node.max_angle > that_node.max_distance ? that_node.max_angle : this_node.max_angle;

            int distance = (int)MathHelper.calculateDistance(this.x, this.y, this.z, node.x, node.y, node.z);
            double angle = Math.round(Math.toDegrees(MathHelper.calculateAngle(this.x, this.y, this.z, node.x, node.y, node.z)));

            if (distance <= max_distance && angle <= max_angle)
                this.neighbours.add(node);
        }
    }

    public void killConnection(PowerGridNode node)
    {
        this.neighbours.remove(node);
    }

    public void disconnect()
    {
        for (PowerGridNode node : this.neighbours)
        {
            node.killConnection(this);
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getZ() {
        return this.z;
    }

    public boolean isSubStation() {
        return this.is_sub_station;
    }

    public boolean isConnected() {
        return is_connected;
    }

    public String getNodeType() {
        return node_type;
    }

    public List<PowerGridNode> getNeighbours() {
        return neighbours;
    }

    public void setIsConnected(boolean isConnected) {
        this.is_connected = isConnected;
    }

    public void readFromNBT(NBTTagCompound nbt) {
        this.x = nbt.getInteger("x");
        this.y = nbt.getInteger("y");
        this.z = nbt.getInteger("z");
        this.is_sub_station = nbt.getBoolean("is-sub-station");
        this.is_connected = nbt.getBoolean("is-connected");
        this.node_type = nbt.getString("node-type");
    }

    public void writeToNBT(NBTTagCompound nbt) {
        nbt.setInteger("x", this.getX());
        nbt.setInteger("y", this.getY());
        nbt.setInteger("z", this.getZ());
        nbt.setBoolean("is-sub-station", this.isSubStation());
        nbt.setBoolean("is-connected", this.isConnected());
        nbt.setString("node-type", this.getNodeType());
    }
}
