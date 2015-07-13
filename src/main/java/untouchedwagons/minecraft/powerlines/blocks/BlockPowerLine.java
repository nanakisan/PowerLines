package untouchedwagons.minecraft.powerlines.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.PowerLinesMod;
import untouchedwagons.minecraft.powerlines.extra.IBoundingBlock;
import untouchedwagons.minecraft.powerlines.extra.IRotatable;
import untouchedwagons.minecraft.powerlines.grids.PowerGrid;
import untouchedwagons.minecraft.powerlines.grids.PowerGridNode;
import untouchedwagons.minecraft.powerlines.grids.PowerGridWorldSavedData;
import untouchedwagons.minecraft.powerlines.network.*;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityPowerGridNode;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

abstract public class BlockPowerLine extends Block implements ITileEntityProvider
{
    private final PowerLineInfo pole_info;
    private final ITileEntityFactory factory;

    private static final Map<String, PowerLineInfo> node_types = new LinkedHashMap<String, PowerLineInfo>();

    protected BlockPowerLine(Material p_i45394_1_, ITileEntityFactory factory, PowerLineInfo pole_info) {
        super(p_i45394_1_);

        this.factory = factory;
        this.pole_info = pole_info;

        this.setHardness(2F);
        this.setStepSound(Block.soundTypeStone);
        this.setCreativeTab(CreativeTabs.tabMisc);

        BlockPowerLine.node_types.put(this.getNodeIdentifier(), pole_info);
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
    public int getRenderType() { return -1; }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hit_x, float hit_y, float hit_z) {
        if (world.isRemote)
            return false;

        TileEntity te = world.getTileEntity(x, y, z);

        if (te instanceof IRotatable && player.isSneaking())
        {
            ((IRotatable) te).rotate();

            NodeRotationMessage message = new NodeRotationMessage(x, y, z, ((IRotatable) te).getRotation());

            //noinspection unchecked
            for (EntityPlayer p : (List<EntityPlayer>)world.playerEntities)
            {
                PowerLinesMod.networking.sendTo(message, (EntityPlayerMP) p);
            }
        }

        return false;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        if (world.isRemote)
            return;

        TileEntity te = world.getTileEntity(x, y, z);

        if (te instanceof IBoundingBlock)
        {
            ((IBoundingBlock)te).onPlace();
        }

        UUID grid_uuid = ((TileEntityPowerGridNode)te).getPowerGridUUID();

        if (grid_uuid != null)
            connectToPowerGrid(grid_uuid, (TileEntityPowerGridNode) te);

        super.onBlockPlacedBy(world, x, y, z, entity, stack);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        if (world.isRemote)
            return;

        TileEntity te = world.getTileEntity(x, y, z);

        if (te instanceof IBoundingBlock)
        {
            ((IBoundingBlock)te).onBreak();
        }

        UUID grid_uuid = ((TileEntityPowerGridNode)te).getPowerGridUUID();

        if (grid_uuid != null)
            disconnectFromPowerGrid(grid_uuid, (TileEntityPowerGridNode) te);

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

    public void connectToPowerGrid(UUID grid_uuid, TileEntityPowerGridNode te_node)
    {
        PowerGridNode node = new PowerGridNode(te_node.getNodeUUID(), te_node.xCoord, te_node.yCoord, te_node.zCoord, this.isSubStation(), false, this.getNodeIdentifier());
        PowerGridWorldSavedData data = PowerGridWorldSavedData.get(te_node.getWorldObj());

        PowerGrid grid = data.getGridByUUID(grid_uuid);
        grid.connectGridNode(node);
        grid.connectGrid();

        // Inform everyone in the world of the changes.
        PowerGridSynchronizationMessage message = new PowerGridSynchronizationMessage(PowerGridWorldSavedData.get(te_node.getWorldObj()));

        //noinspection unchecked
        for (EntityPlayer player : (List<EntityPlayer>)te_node.getWorldObj().playerEntities)
        {
            PowerLinesMod.networking.sendTo(message, (EntityPlayerMP) player);
        }
    }

    public void disconnectFromPowerGrid(UUID grid_uuid, TileEntityPowerGridNode te_node)
    {
        PowerGrid grid = PowerGridWorldSavedData.get(te_node.getWorldObj()).getGridByUUID(grid_uuid);
        PowerGridNode node = grid.getGridNode(te_node.getNodeUUID());

        grid.disconnectGridNode(node);
        grid.connectGrid();

        // Inform everyone in the world of the changes.
        PowerGridSynchronizationMessage message = new PowerGridSynchronizationMessage(PowerGridWorldSavedData.get(te_node.getWorldObj()));

        //noinspection unchecked
        for (EntityPlayer player : (List<EntityPlayer>)te_node.getWorldObj().playerEntities)
        {
            PowerLinesMod.networking.sendTo(message, (EntityPlayerMP) player);
        }
    }

    public abstract String getNodeIdentifier();

    public abstract boolean isSubStation();

    public static PowerLineInfo getPowerLineInfoByType(String node_type)
    {
        return BlockPowerLine.node_types.get(node_type);
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
