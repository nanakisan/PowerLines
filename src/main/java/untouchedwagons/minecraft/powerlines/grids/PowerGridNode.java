package untouchedwagons.minecraft.powerlines.grids;

import net.minecraft.nbt.NBTTagCompound;

public class PowerGridNode {
    private int x;
    private int y;
    private int z;
    private boolean isSubStation;
    private boolean isConnected;

    public PowerGridNode() {
    }

    public PowerGridNode(int x, int y, int z, boolean isSubStation, boolean isConnected) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.isSubStation = isSubStation;
        this.isConnected = isConnected;
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
        return this.isSubStation;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void readFromNBT(NBTTagCompound nbt) {
        this.x = nbt.getInteger("x");
        this.y = nbt.getInteger("y");
        this.z = nbt.getInteger("z");
        this.isSubStation = nbt.getBoolean("is-sub-station");
        this.isConnected = nbt.getBoolean("is-connected");
    }

    public void writeToNBT(NBTTagCompound nbt) {
        nbt.setInteger("x", this.getX());
        nbt.setInteger("y", this.getY());
        nbt.setInteger("z", this.getZ());
        nbt.setBoolean("is-sub-station", this.isSubStation());
        nbt.setBoolean("is-connected", this.isConnected());
    }
}
