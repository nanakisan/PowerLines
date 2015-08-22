package untouchedwagons.minecraft.powerlines.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.PowerLinesMod;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityLargePowerLine;

public class BlockLargePowerLine extends BlockPowerLine {
    public BlockLargePowerLine() {
        super(
                Material.iron,
                new PowerLineInfo(
                        PowerLinesMod.config.get("large-power-line", "max-distance", 48).getInt(),
                        PowerLinesMod.config.get("large-power-line", "max-angle", 45).getDouble()
                )
        );

        this.setBlockName("large-power-line");
        this.setBlockTextureName("powerlines:large-power-line");

        this.setBlockBounds(0, 0, 0, 1, 4, 1);
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        boolean is_air = world.isAirBlock(x, y + 1, z) &&
                        world.isAirBlock(x, y + 2, z) &&
                        world.isAirBlock(x, y + 3, z);

        return super.canPlaceBlockAt(world, x, y, z) && is_air;
    }

    @Override
    public String getNodeIdentifier() {
        return "LargePowerLine";
    }

    @Override
    public boolean isSubStation() {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityLargePowerLine();
    }
}
