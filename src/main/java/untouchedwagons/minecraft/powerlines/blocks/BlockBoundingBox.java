package untouchedwagons.minecraft.powerlines.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityBoundingBox;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityDumbFluxedBoundingBox;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityFluxedBoundingBox;

import java.util.Random;

public class BlockBoundingBox extends Block implements ITileEntityProvider {
    public BlockBoundingBox() {
        super(Material.iron);

        this.setBlockName("pl-bounding-box");
        this.setBlockName("powerlines:pl-bounding-box");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        switch (meta){
            case 0:
                return new TileEntityBoundingBox();
            case 1:
                return new TileEntityFluxedBoundingBox();
            case 2:
                return new TileEntityDumbFluxedBoundingBox();
            default:
                return null;
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hit_x, float hit_y, float hit_z) {
        TileEntityBoundingBox tebb = (TileEntityBoundingBox) world.getTileEntity(x, y, z);

        if (!world.blockExists(tebb.orig_x, tebb.orig_y, tebb.orig_z))
        {
            return false;
        }

        Block b = world.getBlock(tebb.orig_x, tebb.orig_y, tebb.orig_z);
        b.onBlockActivated(world, tebb.orig_x, tebb.orig_y, tebb.orig_z, player, side, hit_x, hit_y, hit_z);

        return false;
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
        TileEntityBoundingBox tebbox = (TileEntityBoundingBox)world.getTileEntity(x, y, z);

        return super.getPickBlock(target, world, tebbox.orig_x, tebbox.orig_y, tebbox.orig_z, player);
    }

    @Override
    public int quantityDropped(Random p_149745_1_) {
        return 0;
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return null;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return -1;
    }

    @Override
    public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest) {
        TileEntityBoundingBox tebbox = (TileEntityBoundingBox)world.getTileEntity(x, y, z);

        return world.getBlock(tebbox.orig_x, tebbox.orig_y, tebbox.orig_z).
                removedByPlayer(world, player, tebbox.orig_x, tebbox.orig_y, tebbox.orig_z, willHarvest);
    }
}
