package untouchedwagons.minecraft.powerlines.extra;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.MathHelper;
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
        world.setBlock(x, y, z, PowerLinesMod.blocks.boundingBox, meta, 2);

        ((TileEntityBoundingBox) world.getTileEntity(x, y, z)).setParentLocation(orig_x, orig_y, orig_z);
    }

    public static void placeBoundingBlock(World world, int x, int y, int z, int orig_x, int orig_y, int orig_z, MultiblockPosition.BoundingBlockType type)
    {
        if (type == MultiblockPosition.BoundingBlockType.None)
            return;

        placeBoundingBlock(world, x, y, z, orig_x, orig_y, orig_z, type.getType());
    }

    public static Rotation getEntityRotation(Entity entity)
    {
        int entity_rotation = MathHelper.floor_double((double) (entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        return Rotation.fromInt(entity_rotation);
    }
}
