package untouchedwagons.minecraft.powerlines.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.PowerLinesMod;
import untouchedwagons.minecraft.powerlines.extra.MultiblockPosition;
import untouchedwagons.minecraft.powerlines.extra.Rotation;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityLargePowerLine;

import java.util.ArrayList;
import java.util.List;

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
    public List<MultiblockPosition> getMultiblockPositions(Rotation rotation) {
        List<MultiblockPosition> positions = new ArrayList<MultiblockPosition>();

        for (int x = -1; x < 2; x++)
        {
            for (int y = 0; y < 8; y++)
            {
                for (int z = -1; z < 2; z++)
                {
                    if (x == 0 && y == 0 && z == 0) continue;

                    positions.add(new MultiblockPosition(x, y, z, MultiblockPosition.BoundingBlockType.Simple));
                }
            }
        }

        return positions;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityLargePowerLine();
    }
}
