package untouchedwagons.minecraft.powerlines.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.PowerLinesMod;
import untouchedwagons.minecraft.powerlines.extra.NetworkUtils;
import untouchedwagons.minecraft.powerlines.grids.PowerGrid;
import untouchedwagons.minecraft.powerlines.grids.PowerGridWorldSavedData;
import untouchedwagons.minecraft.powerlines.network.NodeWrenchedMessage;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntitySubStation;

public class BlockSubStation extends BlockPowerLine {
    public BlockSubStation() {
        super(Material.iron,
                new ITileEntityFactory() {
                    @Override
                    public TileEntity makeTileEntity(World p_149915_1_, int p_149915_2_) {
                        return new TileEntitySubStation();
                    }
                },
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
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {

        boolean is_air = world.isAirBlock(x - 1, y, z - 1) &&
                        world.isAirBlock(x - 1, y, z) &&
                        world.isAirBlock(x - 1, y, z + 1) &&
                        world.isAirBlock(x, y, z - 1) &&
                        world.isAirBlock(x, y, z + 1) &&
                        world.isAirBlock(x + 1, y, z - 1) &&
                        world.isAirBlock(x + 1, y, z) &&
                        world.isAirBlock(x + 1, y, z + 1) &&
                        world.isAirBlock(x - 1, y + 3, z) &&
                        world.isAirBlock(x + 1, y + 3, z) &&
                        world.isAirBlock(x, y + 3, z) &&
                        world.isAirBlock(x, y + 3, z - 1) &&
                        world.isAirBlock(x, y + 3, z + 1);

        return super.canPlaceBlockAt(world, x, y, z) && is_air;
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

            player.addChatComponentMessage(new ChatComponentText(String.format("SubStation energy mode has been changed to %s", tess.getEnergyMode().toString())));
        }
        else
        {
            TileEntitySubStation tess = (TileEntitySubStation) world.getTileEntity(x, y, z);

            if (tess == null) return false;

            PowerGridWorldSavedData pgwsd = PowerGridWorldSavedData.get(world);
            PowerGrid grid = pgwsd.getGridByUUID(tess.getPowerGridUUID());

            player.addChatComponentMessage(new ChatComponentText(String.format("is connected: %b", grid.isConnected())));
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
}
