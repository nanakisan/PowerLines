package untouchedwagons.minecraft.powerlines.blocks;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.extra.*;
import untouchedwagons.minecraft.powerlines.grids.PowerGrid;
import untouchedwagons.minecraft.powerlines.grids.PowerGridNode;
import untouchedwagons.minecraft.powerlines.grids.PowerGridWorldSavedData;
import untouchedwagons.minecraft.powerlines.network.grids.*;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityPowerGridNode;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class BlockPowerLine extends Block implements ITileEntityProvider
{
    private final PowerLineInfo pole_info;

    private static final Map<String, PowerLineInfo> node_types = new LinkedHashMap<String, PowerLineInfo>();

    protected BlockPowerLine(Material p_i45394_1_, PowerLineInfo pole_info) {
        super(p_i45394_1_);

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
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        for (MultiblockPosition mbp : this.getMultiblockPositions(Rotation.NORTH_SOUTH))
        {
            if (!world.isAirBlock(x + mbp.getXOffset(), y + mbp.getYOffset(), z + mbp.getZOffset()))
                return false;
        }

        return super.canPlaceBlockAt(world, x, y, z);
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
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        if (world.isRemote)
            return;

        TileEntity te = world.getTileEntity(x, y, z);

        TileEntityPowerGridNode tepgn = (TileEntityPowerGridNode) te;

        tepgn.setRotation(PowerLineUtils.getEntityRotation(entity));
        tepgn.setNodeUUID(UUID.randomUUID());

        IMessage message = new SetNodeUUIDMessage(x, y, z, tepgn.getNodeUUID());
        NetworkUtils.broadcastToWorld(world, message);

        for (MultiblockPosition mbp : this.getMultiblockPositions(tepgn.getRotation()))
        {
            PowerLineUtils.placeBoundingBlock(world, x + mbp.getXOffset(), y + mbp.getYOffset(), z + mbp.getZOffset(), x, y, z, mbp.getType());
        }

        if (tepgn.requiresGridUUID()) {
            tepgn.setGridUUID(UUID.randomUUID());

            PowerGridWorldSavedData pgwsd = PowerGridWorldSavedData.get(world);
            PowerGrid grid = pgwsd.getGridByUUID(tepgn.getPowerGridUUID());

            message = new PowerGridCreatedMessage(tepgn.getPowerGridUUID());
            NetworkUtils.broadcastToWorld(world, message);

            message = new SetGridUUIDMessage(x, y, z, tepgn.getPowerGridUUID());
            NetworkUtils.broadcastToWorld(world, message);

            PowerGridNode node = new PowerGridNode(tepgn.getNodeUUID(), x, y, z, this.isSubStation(), this.getNodeIdentifier());
            grid.connectGridNode(node);

            message = new PowerGridNodeConnectedMessage(tepgn.getPowerGridUUID(), node);
            NetworkUtils.broadcastToWorld(world, message);

            grid.connectGrid();
        }

        super.onBlockPlacedBy(world, x, y, z, entity, stack);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        if (world.isRemote)
            return;

        TileEntity te = world.getTileEntity(x, y, z);
        TileEntityPowerGridNode tepgn = (TileEntityPowerGridNode) te;

        for (MultiblockPosition mbp : this.getMultiblockPositions(tepgn.getRotation()))
        {
            if (mbp.getType() == MultiblockPosition.BoundingBlockType.None)
                continue;

            world.setBlockToAir(x + mbp.getXOffset(), y + mbp.getYOffset(), z + mbp.getZOffset());
        }

        UUID grid_uuid = ((TileEntityPowerGridNode)te).getPowerGridUUID();

        if (grid_uuid != null)
            disconnectFromPowerGrid(world, (TileEntityPowerGridNode) te);

        super.breakBlock(world, x, y, z, block, meta);
    }

    public void disconnectFromPowerGrid(World world, TileEntityPowerGridNode te_node)
    {
        UUID grid_uuid = te_node.getPowerGridUUID();
        PowerGrid grid = PowerGridWorldSavedData.get(te_node.getWorldObj()).getGridByUUID(grid_uuid);
        PowerGridNode node = grid.getGridNode(te_node.getNodeUUID());

        grid.disconnectGridNode(node);

        IMessage message = new PowerGridNodeDisconnectedMessage(grid_uuid, te_node.getNodeUUID(), te_node.xCoord, te_node.yCoord, te_node.zCoord);
        NetworkUtils.broadcastToWorld(world, message);

        if (grid.getNodes().size() == 0)
        {
            message = new PowerGridDestroyedMessage(grid_uuid);
            NetworkUtils.broadcastToWorld(world, message);
        }

        grid.connectGrid();
    }

    public abstract String getNodeIdentifier();

    public abstract boolean isSubStation();

    public abstract List<MultiblockPosition> getMultiblockPositions(Rotation rotation);

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
}
