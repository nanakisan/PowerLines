package untouchedwagons.minecraft.powerlines.tileentity;

import untouchedwagons.minecraft.powerlines.extra.ConnectionPointCoordinateCalculator;

public class TileEntitySmallPowerLine extends TileEntityPowerLine {

    public TileEntitySmallPowerLine() {
        super(new ConnectionPointCoordinateCalculator(8));
    }
}
