package untouchedwagons.minecraft.powerlines.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.*;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.PowerLinesMod;
import untouchedwagons.minecraft.powerlines.extra.MultiblockPosition;
import untouchedwagons.minecraft.powerlines.extra.NetworkUtils;
import untouchedwagons.minecraft.powerlines.extra.Rotation;
import untouchedwagons.minecraft.powerlines.grids.PowerGrid;
import untouchedwagons.minecraft.powerlines.grids.PowerGridWorldSavedData;
import untouchedwagons.minecraft.powerlines.network.NodeWrenchedMessage;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntitySubStation;

import java.util.ArrayList;
import java.util.List;

public class BlockSubStation extends BlockPowerLine {
    public BlockSubStation() {
        super(Material.iron,
                new PowerLineInfo(
                        PowerLinesMod.config.get("substation", "max-distance", 8).getInt(),
                        PowerLinesMod.config.get("substation", "max-angle", 45).getDouble()
                ));

        this.setHardness(2F);
        this.setStepSound(Block.soundTypeStone);
        this.setCreativeTab(CreativeTabs.tabMisc);

        this.setBlockName("sub-station");
        this.setBlockTextureName("powerlines:sub-station");
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
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        return Blocks.iron_block.getIcon(p_149691_1_, p_149691_2_);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hit_x, float hit_y, float hit_z) {
        if (world.isRemote)
            return false;

        if (player.isSneaking() && player.getCurrentEquippedItem() == null)
        {
            TileEntitySubStation tess = (TileEntitySubStation) world.getTileEntity(x, y, z);

            if (tess == null) return false;

            tess.wrench();

            NodeWrenchedMessage message = new NodeWrenchedMessage(x, y, z);

            NetworkUtils.broadcastToWorld(world, message);
        }

        return true;
    }

    @Override
    public String getNodeIdentifier() {
        return "SubStation";
    }

    @Override
    public boolean isSubStation() {
        return true;
    }

    @Override
    public List<MultiblockPosition> getMultiblockPositions(Rotation rotation) {
        List<MultiblockPosition> positions = new ArrayList<MultiblockPosition>();

        if (rotation == Rotation.NORTH_SOUTH)
        {
            /*
             * The reason for having a None bounding block type is due to a limitation in Block.canPlaceBlockAt. Since
             * no entity object is given, it cannot be determined what the orientation of the multiblock will be. Thus
             * the North/South orientation must include the position of blocks unique to the East/West rotation. No
             * actual blocks will ever be placed but no non-air blocks must exist at those spots
             */
            positions.add(new MultiblockPosition(-1, 3, -1, MultiblockPosition.BoundingBlockType.None));
            positions.add(new MultiblockPosition(-1, 3, 0, MultiblockPosition.BoundingBlockType.None));
            positions.add(new MultiblockPosition(1, 3, -1, MultiblockPosition.BoundingBlockType.None));
            positions.add(new MultiblockPosition(1, 3, 0, MultiblockPosition.BoundingBlockType.None));
            positions.add(new MultiblockPosition(-1, 3, 1, MultiblockPosition.BoundingBlockType.None));
            positions.add(new MultiblockPosition(1, 3, 1, MultiblockPosition.BoundingBlockType.None));

            positions.add(new MultiblockPosition(0, 3, -1, MultiblockPosition.BoundingBlockType.DumbFluxed));
            positions.add(new MultiblockPosition(0, 3, 1, MultiblockPosition.BoundingBlockType.DumbFluxed));
            positions.add(new MultiblockPosition(0, 0, -1, MultiblockPosition.BoundingBlockType.Fluxed));
            positions.add(new MultiblockPosition(0, 0, 1, MultiblockPosition.BoundingBlockType.Fluxed));
            positions.add(new MultiblockPosition(-1, 0, 0, MultiblockPosition.BoundingBlockType.DumbFluxed));
            positions.add(new MultiblockPosition(1, 0, 0, MultiblockPosition.BoundingBlockType.DumbFluxed));
        }
        else
        {
            positions.add(new MultiblockPosition(-1, 3, 0, MultiblockPosition.BoundingBlockType.DumbFluxed));
            positions.add(new MultiblockPosition(1, 3, 0, MultiblockPosition.BoundingBlockType.DumbFluxed));
            positions.add(new MultiblockPosition(-1, 0, 0, MultiblockPosition.BoundingBlockType.Fluxed));
            positions.add(new MultiblockPosition(1, 0, 0, MultiblockPosition.BoundingBlockType.Fluxed));
            positions.add(new MultiblockPosition(0, 0, -1, MultiblockPosition.BoundingBlockType.DumbFluxed));
            positions.add(new MultiblockPosition(0, 0, 1, MultiblockPosition.BoundingBlockType.DumbFluxed));
        }

        positions.add(new MultiblockPosition(0, 3, 0, MultiblockPosition.BoundingBlockType.DumbFluxed));
        positions.add(new MultiblockPosition(-1, 0, -1, MultiblockPosition.BoundingBlockType.DumbFluxed));
        positions.add(new MultiblockPosition(-1, 0, 1, MultiblockPosition.BoundingBlockType.DumbFluxed));
        positions.add(new MultiblockPosition(1, 0, -1, MultiblockPosition.BoundingBlockType.DumbFluxed));
        positions.add(new MultiblockPosition(1, 0, 1, MultiblockPosition.BoundingBlockType.DumbFluxed));

        for (int x = -1; x < 2; x++)
        {
            for (int y = 1; y < 3; y++)
            {
                for (int z = -1; z < 2; z++)
                {
                    positions.add(new MultiblockPosition(x, y, z, MultiblockPosition.BoundingBlockType.DumbFluxed));
                }
            }
        }

        return positions;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntitySubStation();
    }
}
