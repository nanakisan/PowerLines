package untouchedwagons.minecraft.powerlines.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBoundingBox extends TileEntity {
    public int orig_x, orig_y, orig_z;

    public void setParentLocation(int orig_x, int orig_y, int orig_z) {
        this.orig_x = orig_x;
        this.orig_y = orig_y;
        this.orig_z = orig_z;
    }
    
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        this.orig_x = nbt.getInteger("orig-x");
        this.orig_y = nbt.getInteger("orig-y");
        this.orig_z = nbt.getInteger("orig-z");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        nbt.setInteger("orig-x", this.orig_x);
        nbt.setInteger("orig-y", this.orig_y);
        nbt.setInteger("orig-z", this.orig_z);
    }
}
