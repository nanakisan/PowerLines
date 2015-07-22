package untouchedwagons.minecraft.powerlines.tileentity;

import untouchedwagons.minecraft.powerlines.extra.ConnectionPointCoordinateCalculator;

public class TileEntityMediumPowerLine extends TileEntityPowerLine {
    public TileEntityMediumPowerLine() {
        super(new ConnectionPointCoordinateCalculator(8));
    }
}
