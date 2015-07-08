package untouchedwagons.minecraft.powerlines.extra;

import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.PowerLinesMod;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityBoundingBox;

public class PowerLineUtils {
    private PowerLineUtils(){}

    public static void placeBoundingBlock (World world, int x, int y, int z, int orig_x, int orig_y, int orig_z)
    {
        world.setBlock(x, y, z, PowerLinesMod.bounding_box);

        if (!world.isRemote)
        {
            ((TileEntityBoundingBox)world.getTileEntity(x, y, z)).setParentLocation(orig_x, orig_y, orig_z);
        }
    }
}
