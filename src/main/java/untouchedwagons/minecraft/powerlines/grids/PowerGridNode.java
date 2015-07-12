package untouchedwagons.minecraft.powerlines.grids;

import cpw.mods.fml.common.network.ByteBufUtils;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import untouchedwagons.math.MathHelper;
import untouchedwagons.minecraft.powerlines.blocks.BlockPowerLine;

import java.util.*;

public class PowerGridNode {
    private UUID node_uuid;
    private int x;
    private int y;
    private int z;
    private boolean is_sub_station;
    private boolean is_connected = false;
    private String node_type;

    private final Set<PowerGridNode> neighbours = new LinkedHashSet<PowerGridNode>();
    private final List<PowerGridNode> undiscovered_neighbours = new LinkedList<PowerGridNode>();

    public PowerGridNode(UUID node_uuid, int x, int y, int z) {
        this.node_uuid = node_uuid;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public PowerGridNode(UUID node_uuid, int x, int y, int z, boolean is_sub_station, boolean is_connected, String node_type) {
        this.node_uuid = node_uuid;
        this.x = x;
        this.y = y;
        this.z = z;
        this.is_sub_station = is_sub_station;
        this.is_connected = is_connected;
        this.node_type = node_type;
    }

    public PowerGridNode() {

    }

    public void discoverNeighbours(PowerGrid grid)
    {
        for (PowerGridNode undiscovered_neighbour : this.undiscovered_neighbours)
        {
            PowerGridNode discovered_neighbour = grid.getGridNode(undiscovered_neighbour.getNodeUUID());

            this.neighbours.add(discovered_neighbour);
        }

        this.undiscovered_neighbours.clear();
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

    public UUID getNodeUUID() {
        return node_uuid;
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

    public Set<PowerGridNode> getNeighbours() {
        return neighbours;
    }

    public void setIsConnected(boolean isConnected) {
        this.is_connected = isConnected;
    }

    public void readFromNBT(NBTTagCompound nbt) {
        this.node_uuid = UUID.fromString(nbt.getString("node-uuid"));
        this.x = nbt.getInteger("x");
        this.y = nbt.getInteger("y");
        this.z = nbt.getInteger("z");
        this.is_sub_station = nbt.getBoolean("is-sub-station");
        this.is_connected = nbt.getBoolean("is-connected");
        this.node_type = nbt.getString("node-type");

        NBTTagList neighbours = nbt.getTagList("neighbours", 10);

        for (int i = 0; i < neighbours.tagCount(); i++)
        {
            NBTTagCompound node_tag = neighbours.getCompoundTagAt(i);

            PowerGridNode neighbour = new PowerGridNode(
                    UUID.fromString(node_tag.getString("node-uuid")),
                    node_tag.getInteger("x"),
                    node_tag.getInteger("y"),
                    node_tag.getInteger("z")
            );

            this.undiscovered_neighbours.add(neighbour);
        }
    }

    public void writeToNBT(NBTTagCompound nbt) {
        nbt.setString("node-uuid", this.node_uuid.toString());
        nbt.setInteger("x", this.getX());
        nbt.setInteger("y", this.getY());
        nbt.setInteger("z", this.getZ());
        nbt.setBoolean("is-sub-station", this.isSubStation());
        nbt.setBoolean("is-connected", this.isConnected());
        nbt.setString("node-type", this.getNodeType());

        NBTTagList neighbours_list = new NBTTagList();
        nbt.setTag("neighbours", neighbours_list);

        for (PowerGridNode node : this.neighbours)
        {
            NBTTagCompound node_tag = new NBTTagCompound();
            node_tag.setString("node-uuid", node.getNodeUUID().toString());
            node_tag.setInteger("x", node.getX());
            node_tag.setInteger("y", node.getY());
            node_tag.setInteger("z", node.getZ());

            neighbours_list.appendTag(node_tag);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PowerGridNode))
            return false;

        PowerGridNode node = (PowerGridNode) obj;

        return  this.getNodeUUID().equals(node.getNodeUUID()) &&
                this.getX() == node.getX() &&
                this.getY() == node.getY() &&
                this.getZ() == node.getZ() &&
                this.isSubStation() == node.isSubStation();
    }
}
