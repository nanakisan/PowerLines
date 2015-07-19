package untouchedwagons.minecraft.powerlines.extra;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.PowerLinesMod;
import untouchedwagons.minecraft.powerlines.network.BoundingBoxHomePositionMessage;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityBoundingBox;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityDumbFluxedBoundingBox;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityFluxedBoundingBox;

import java.util.List;

public class PowerLineUtils {
    private PowerLineUtils(){}

    public static void placeBoundingBlock(World world, int x, int y, int z, int orig_x, int orig_y, int orig_z)
    {
        placeBoundingBlock(world, x, y, z, orig_x, orig_y, orig_z, 0);
    }

    public static void placeFluxedBoundingBlock(World world, int x, int y, int z, int orig_x, int orig_y, int orig_z)
    {
         placeBoundingBlock(world, x, y, z, orig_x, orig_y, orig_z, 1);
    }

    public static void placeDumbFluxedBoundingBlock(World world, int x, int y, int z, int orig_x, int orig_y, int orig_z)
    {
         placeBoundingBlock(world, x, y, z, orig_x, orig_y, orig_z, 2);
    }

    private static void placeBoundingBlock(World world, int x, int y, int z, int orig_x, int orig_y, int orig_z, int meta)
    {
        world.setBlock(x, y, z, PowerLinesMod.bounding_box, meta, 2);

        ((TileEntityBoundingBox) world.getTileEntity(x, y, z)).setParentLocation(orig_x, orig_y, orig_z);

        BoundingBoxHomePositionMessage message = new BoundingBoxHomePositionMessage(x, y, z, orig_x, orig_y, orig_z);

        for (EntityPlayer player : (List<EntityPlayer>)world.playerEntities)
        {
            PowerLinesMod.networking.sendTo(message, (EntityPlayerMP) player);
        }
    }
}
