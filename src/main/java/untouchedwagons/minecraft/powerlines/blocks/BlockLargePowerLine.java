package untouchedwagons.minecraft.powerlines.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.PowerLineInfo;
import untouchedwagons.minecraft.powerlines.PowerLinesMod;
import untouchedwagons.minecraft.powerlines.proxy.CommonProxy;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityLargePowerLine;

public class BlockLargePowerLine extends BlockPowerLine {
    public BlockLargePowerLine() {
        super(
                Material.iron,
                new PowerLineInfo(
                        PowerLinesMod.config.get("large-power-line", "max-energy", 1000000).getInt(),
                        PowerLinesMod.config.get("large-power-line", "max-io", 100000).getInt(),
                        PowerLinesMod.config.get("large-power-line", "max-distance", 48).getInt(),
                        PowerLinesMod.config.get("large-power-line", "max-angle", 45).getDouble()
                )
        );

        this.setBlockName("large-power-line");
        this.setBlockTextureName("powerlines:large-power-line");
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityLargePowerLine();
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return CommonProxy.RENDER_ID;
    }
}
