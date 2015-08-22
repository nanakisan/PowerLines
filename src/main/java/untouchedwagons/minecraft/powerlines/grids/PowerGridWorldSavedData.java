package untouchedwagons.minecraft.powerlines.grids;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class PowerGridWorldSavedData extends WorldSavedData {
    private static final String IDENTIFIER = "power-lines";
    private final List<PowerGrid> grids = new LinkedList<PowerGrid>();
    private World world;

    public PowerGridWorldSavedData(String p_i2141_1_) {
        super(p_i2141_1_);
    }

    public PowerGridWorldSavedData() {
        super(IDENTIFIER);
    }

    public boolean hasPowerGrid(UUID grid_uuid)
    {
        for (PowerGrid grid : this.grids)
        {
            if (grid.getGridUUID().equals(grid_uuid))
                return true;
        }

        return false;
    }

    public PowerGrid getGridByUUID(UUID grid_uuid)
    {
        for (PowerGrid grid : this.grids)
        {
            if (grid.getGridUUID().equals(grid_uuid))
                return grid;
        }

        PowerGrid new_grid = new PowerGrid(grid_uuid, this);
        new_grid.setWorld(world);

        this.grids.add(new_grid);
        this.markDirty();

        return new_grid;
    }

    public List<PowerGrid> getGrids()
    {
        return this.grids;
    }

    public void removePowerGrid(PowerGrid grid)
    {
        this.grids.remove(grid);
        this.markDirty();
    }

    public void removePowerGrid(UUID grid_uuid)
    {
        for (PowerGrid grid : this.grids)
        {
            if (grid.getGridUUID().equals(grid_uuid))
            {
                this.grids.remove(grid);
                return;
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        this.grids.clear();

        NBTTagList grids = nbt.getTagList("grids", 10);

        for (int i = 0; i < grids.tagCount(); i++)
        {
            NBTTagCompound grid_tag = grids.getCompoundTagAt(i);
            PowerGrid grid = new PowerGrid(this);
            grid.readFromNBT(grid_tag);

            this.grids.add(grid);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        NBTTagList grid_list = new NBTTagList();

        for (PowerGrid grid : this.grids)
        {
            NBTTagCompound grid_tag = new NBTTagCompound();

            grid.writeToNBT(grid_tag);

            grid_list.appendTag(grid_tag);
        }

        nbt.setTag("grids", grid_list);
    }

    public static PowerGridWorldSavedData get(World world) {
        PowerGridWorldSavedData data = (PowerGridWorldSavedData) world.mapStorage.loadData(PowerGridWorldSavedData.class, IDENTIFIER);

        if (data == null) {
            data = new PowerGridWorldSavedData();
            world.mapStorage.setData(IDENTIFIER, data);
        }

        data.setWorld(world);

        for (PowerGrid grid : data.getGrids()) {
            grid.setWorld(world);
        }

        return data;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
