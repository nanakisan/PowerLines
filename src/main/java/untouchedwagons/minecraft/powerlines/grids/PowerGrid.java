package untouchedwagons.minecraft.powerlines.grids;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyStorage;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import untouchedwagons.minecraft.powerlines.PowerLinesMod;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class PowerGrid implements IEnergyStorage {
    private final EnergyStorage energy = new EnergyStorage(PowerLinesMod.config.get("power-grid", "max-energy", 1000000).getInt());
    private final List<PowerGridNode> nodes = new LinkedList<PowerGridNode>();
    private UUID grid_uuid = UUID.randomUUID();
    private PowerGridWorldSavedData storage;

    public PowerGrid(PowerGridWorldSavedData storage) {
        this.storage = storage;
    }

    public PowerGrid(UUID grid_uuid) {
        this.grid_uuid = grid_uuid;
    }

    public PowerGrid() {
    }

    /**
     * This method checks to see if the power grid is working, that is, all the substations can connect to each other
     * via power lines. The substations don't have to be loaded for this to work. If one substation can't route to its
     * brothers, the grid won't work. This method is called aut
     */
    private void connectGrid()
    {

    }

    public void connectGridNode(PowerGridNode node)
    {
        this.nodes.add(node);

        this.connectGrid();
    }

    public void disconnectGridNode(PowerGridNode node)
    {
        this.nodes.remove(node);

        if (this.nodes.size() == 0)
        {
            this.storage.removePowerGrid(this);
            return;
        }

        this.connectGrid();
    }

    public void readFromNBT(NBTTagCompound nbt) {
        this.energy.readFromNBT(nbt);

        this.grid_uuid = UUID.fromString(nbt.getString("grid-uuid"));

        NBTTagList node_list = nbt.getTagList("nodes", 10);

        for (int i = 0; i < node_list.tagCount(); i++)
        {
            NBTTagCompound node = node_list.getCompoundTagAt(i);

            PowerGridNode node_coord = new PowerGridNode();
            node_coord.readFromNBT(node);

            this.nodes.add(node_coord);
        }

        this.connectGrid();
    }

    public void writeToNBT(NBTTagCompound nbt) {
        this.energy.writeToNBT(nbt);

        nbt.setString("grid-uuid", this.grid_uuid.toString());

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
        return this.energy.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
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

    public UUID getGridUUID() {
        return this.grid_uuid;
    }

    public List<PowerGridNode> getSubStations()
    {
        List<PowerGridNode> substations = new LinkedList<PowerGridNode>();

        for (PowerGridNode node : this.nodes)
        {
            if (node.isSubStation())
            {
                substations.add(node);
            }
        }

        return substations;
    }
}
