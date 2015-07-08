package untouchedwagons.minecraft.powerlines.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.PowerLinesMod;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityMediumPowerLine;

public class BlockMediumPowerLine extends BlockPowerLine {
    public BlockMediumPowerLine() {
        super(
                Material.iron,
                new ITileEntityFactory() {
                    @Override
                    public TileEntity makeTileEntity(World p_149915_1_, int p_149915_2_) {
                        return new TileEntityMediumPowerLine();
                    }
                },
                new PowerLineInfo(
                        PowerLinesMod.config.get("medium-power-line", "max-distance", 48).getInt(),
                        PowerLinesMod.config.get("medium-power-line", "max-angle", 45).getDouble()
                )
        );

        this.setBlockName("medium-power-line");
        this.setBlockTextureName("powerlines:medium-power-line");
    }
}
