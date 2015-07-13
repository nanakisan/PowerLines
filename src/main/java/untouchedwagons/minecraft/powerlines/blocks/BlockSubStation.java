package untouchedwagons.minecraft.powerlines.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.PowerLinesMod;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityLargePowerLine;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntitySubStation;

public class BlockSubStation extends BlockPowerLine implements ITileEntityProvider {
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
                        world.isAirBlock(x + 1, y, z + 1);

        return super.canPlaceBlockAt(world, x, y, z) && is_air;
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
