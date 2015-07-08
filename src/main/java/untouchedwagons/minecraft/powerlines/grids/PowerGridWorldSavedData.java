package untouchedwagons.minecraft.powerlines.grids;

import net.minecraft.nbt.NBTBase;
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

    public PowerGridWorldSavedData(String p_i2141_1_) {
        super(p_i2141_1_);
    }

    public PowerGridWorldSavedData() {
        super(IDENTIFIER);
    }

    public PowerGrid getGridByUUID(UUID grid_uuid)
    {
        for (PowerGrid grid : this.grids)
        {
            if (grid.getGridUUID().equals(grid_uuid))
                return grid;
        }

        PowerGrid new_grid = new PowerGrid(grid_uuid);

        this.grids.add(new_grid);
        this.markDirty();

        return new_grid;
    }

    public void removePowerGrid(PowerGrid grid)
    {
        this.grids.remove(grid);
        this.markDirty();
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        NBTTagList grids = nbt.getTagList("grids", 10);

        for (int i = 0; i < grids.tagCount(); i++)
        {
            NBTTagCompound grid_tag = grids.getCompoundTagAt(i);
            PowerGrid grid = new PowerGrid(this);
            grid.readFromNBT(grid_tag);

            this.grids.add(grid);
        }

        this.markDirty();
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
        return (PowerGridWorldSavedData)world.perWorldStorage.loadData(PowerGridWorldSavedData.class, IDENTIFIER);
    }
}