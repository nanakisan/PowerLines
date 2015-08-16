package untouchedwagons.minecraft.powerlines.tileentity;

import untouchedwagons.minecraft.powerlines.extra.ConnectionPointCoordinateCalculator;

public abstract class TileEntityPowerLine extends TileEntityPowerGridNode {
    private float rotation;

    private final ConnectionPointCoordinateCalculator calculator;

    public TileEntityPowerLine(ConnectionPointCoordinateCalculator calculator) {
        this.calculator = calculator;
    }

    public void calculateRotation()
    {

    }
}
