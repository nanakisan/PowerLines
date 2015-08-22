package untouchedwagons.minecraft.powerlines.grids;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyStorage;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.PowerLinesMod;
import untouchedwagons.minecraft.powerlines.extra.NetworkUtils;
import untouchedwagons.minecraft.powerlines.network.grids.PowerGridConnectionStateMessage;

import java.util.*;

public class PowerGrid implements IEnergyStorage {
    private final EnergyStorage energy = new EnergyStorage(PowerLinesMod.config.get("power-grid", "max-energy", 1000000).getInt());
    private final List<PowerGridNode> nodes = new LinkedList<PowerGridNode>();

    private boolean is_connected;
    private UUID grid_uuid;
    private PowerGridWorldSavedData storage;
    private World world;

    public PowerGrid(PowerGridWorldSavedData storage) {
        this.storage = storage;
    }

    public PowerGrid(UUID grid_uuid, PowerGridWorldSavedData storage) {
        this.grid_uuid = grid_uuid;
        this.storage = storage;
    }

    /**
     * This method checks to see if the power grid is working, that is, all the substations can connect to each other
     * via power lines. The substations don't have to be loaded for this to work. If one substation can't route to its
     * brothers, the grid won't work.
     */
    public void connectGrid()
    {
        PowerGridNode first_substation = null;

        // Get any node that's a substation, we'll use it as a starting point for finding everything
        for (PowerGridNode node : this.nodes)
        {
            if (node.isSubStation())
            {
                first_substation = node;
                break;
            }
        }

        // No substations in the grid. This can occur if a player links a power line to a substation,
        // destroys the substation, then links another power line to the original power line.
        // Power lines don't use the isConnected boolean anyways
        if (first_substation == null)
            return;

        Set<PowerGridNode> visited_nodes = new LinkedHashSet<PowerGridNode>();
        Set<PowerGridNode> substations = new LinkedHashSet<PowerGridNode>();
        List<PowerGridNode> substations_in_grid = this.getSubStations();

        this.searchForConnectedSubStations(first_substation, substations, visited_nodes);

        int substation_count = substations_in_grid.size();
        int connected_substations = substations.size();
        this.is_connected = substation_count == connected_substations;

        PowerGridConnectionStateMessage m1 = new PowerGridConnectionStateMessage(this.getGridUUID(), this.is_connected);
        NetworkUtils.broadcastToWorld(this.world, m1);
    }

    public void connectGridNode(PowerGridNode node)
    {
        this.nodes.add(node);
        this.storage.markDirty();
    }

    public void disconnectGridNode(PowerGridNode node_to_remove)
    {
        for (PowerGridNode node : this.nodes)
        {
            if (node.equals(node_to_remove))
            {
                this.nodes.remove(node);
                node.disconnect();
                this.storage.markDirty();

                if (this.nodes.size() == 0)
                {
                    this.storage.removePowerGrid(this);

                    return;
                }

                break;
            }
        }
    }

    public PowerGridNode getGridNode(UUID node_uuid)
    {
        for (PowerGridNode node : this.nodes) {
            if (node.getNodeUUID().equals(node_uuid))
                return node;
        }

        return null;
    }

    public void readFromNBT(NBTTagCompound nbt) {
        this.energy.readFromNBT(nbt);

        this.grid_uuid = UUID.fromString(nbt.getString("grid-uuid"));
        this.is_connected = nbt.getBoolean("is-connected");

        NBTTagList node_list = nbt.getTagList("nodes", 10);

        for (int i = 0; i < node_list.tagCount(); i++)
        {
            NBTTagCompound node_tag = node_list.getCompoundTagAt(i);

            PowerGridNode node = new PowerGridNode();
            node.readFromNBT(node_tag);

            this.nodes.add(node);
        }

        for (PowerGridNode node : this.nodes)
        {
            node.discoverNeighbours(this);
        }
    }

    public void writeToNBT(NBTTagCompound nbt) {
        this.energy.writeToNBT(nbt);

        nbt.setString("grid-uuid", this.grid_uuid.toString());
        nbt.setBoolean("is-connected", this.isConnected());

        NBTTagList node_list = new NBTTagList();

        for (PowerGridNode node : this.nodes)
        {
            NBTTagCompound node_tag = new NBTTagCompound();

            node.writeToNBT(node_tag);

            node_list.appendTag(node_tag);
        }

        nbt.setTag("nodes", node_list);
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        int energy_injected = this.energy.receiveEnergy(maxReceive, simulate);

        this.storage.markDirty();

        return energy_injected;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        this.storage.markDirty();
        return this.energy.extractEnergy(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored() {
        return this.energy.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored() {
        return this.energy.getMaxEnergyStored();
    }

    public void setEnergyStored(int energy) {
        this.energy.setEnergyStored(energy);
    }

    public UUID getGridUUID() {
        return this.grid_uuid;
    }

    public List<PowerGridNode> getSubStations()
    {
        List<PowerGridNode> substations = new LinkedList<PowerGridNode>();

        for (PowerGridNode node : this.nodes)
        {
            if (node.isSubStation())
                substations.add(node);
        }

        return substations;
    }

    public boolean isConnected() {
        return is_connected;
    }

    public void setIsConnected(boolean is_connected) {
        this.is_connected = is_connected;
    }

    /**
     * This method recursively walks the network, looking for connected substations
     * @param current_node The node that the walker is currently at
     * @param substations A set of found and connected substations
     * @param visited_nodes A set of all the nodes that have been visited
     */
    private void searchForConnectedSubStations(PowerGridNode current_node, Set<PowerGridNode> substations, Set<PowerGridNode> visited_nodes)
    {
        if (current_node.isSubStation())
            substations.add(current_node);

        visited_nodes.add(current_node);

        for (PowerGridNode neighbour : current_node.getNeighbours())
        {
            if (visited_nodes.contains(neighbour))
                continue;

            searchForConnectedSubStations(neighbour, substations, visited_nodes);
        }
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public List<PowerGridNode> getNodes() {
        return nodes;
    }
}
