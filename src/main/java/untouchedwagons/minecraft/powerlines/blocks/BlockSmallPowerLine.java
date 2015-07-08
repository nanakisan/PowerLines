package untouchedwagons.minecraft.powerlines.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.PowerLinesMod;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityMediumPowerLine;

public class BlockSmallPowerLine extends BlockPowerLine {
    public BlockSmallPowerLine() {
        super(
                Material.wood,
                new ITileEntityFactory() {
                    @Override
                    public TileEntity makeTileEntity(World p_149915_1_, int p_149915_2_) {
                        return new TileEntityMediumPowerLine();
                    }
                },
                new PowerLineInfo(
                        PowerLinesMod.config.get("small-power-line", "max-distance", 48).getInt(),
                        PowerLinesMod.config.get("small-power-line", "max-angle", 45).getDouble()
                )
        );

        this.setBlockName("small-power-line");
        this.setBlockTextureName("powerlines:small-power-line");
    }
}
