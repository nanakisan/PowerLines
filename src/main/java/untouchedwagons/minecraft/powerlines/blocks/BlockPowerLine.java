package untouchedwagons.minecraft.powerlines.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.extra.IBoundingBlock;

abstract public class BlockPowerLine extends Block implements ITileEntityProvider
{
    private final PowerLineInfo pole_info;
    private final ITileEntityFactory factory;

    protected BlockPowerLine(Material p_i45394_1_, ITileEntityFactory factory, PowerLineInfo pole_info) {
        super(p_i45394_1_);

        this.factory = factory;
        this.pole_info = pole_info;

        this.setHardness(2F);
        this.setStepSound(Block.soundTypeStone);
        this.setCreativeTab(CreativeTabs.tabMisc);
    }

    public PowerLineInfo getPoleInfo() {
        return pole_info;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return this.factory.makeTileEntity(p_149915_1_, p_149915_2_);
    }

    @Override
    public boolean renderAsNormalBlock() { return false; }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return -1;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        super.onBlockPlacedBy(world, x, y, z, entity, stack);

        TileEntity te = world.getTileEntity(x, y, z);

        if (te instanceof IBoundingBlock)
        {
            ((IBoundingBlock)te).onPlace();
        }
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        TileEntity te = world.getTileEntity(x, y, z);

        if (te instanceof IBoundingBlock)
        {
            ((IBoundingBlock)te).onBreak();
        }

        super.breakBlock(world, x, y, z, block, meta);
    }

    @Override
    public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest) {
        if (!player.capabilities.isCreativeMode && !world.isRemote && canHarvestBlock(player, world.getBlockMetadata(x, y, z)))
        {
            EntityItem entityItem = new EntityItem(world, x, y, z, getPickBlock(null, world, x, y, z, player));

            world.spawnEntityInWorld(entityItem);
        }

        return world.setBlockToAir(x, y, z);
    }

    public static class PowerLineInfo
    {
        public final int max_distance;
        public final double max_angle;

        public PowerLineInfo(int max_distance, double max_angle) {
            this.max_distance = max_distance;
            this.max_angle = max_angle;
        }
    }

    public interface ITileEntityFactory
    {
        TileEntity makeTileEntity(World p_149915_1_, int p_149915_2_);
    }
}
