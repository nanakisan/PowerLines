package untouchedwagons.minecraft.powerlines;

public class PowerLineInfo
{
    public final int max_energy;
    public final int max_io;
    public final int max_distance;
    public final double max_angle;

    public PowerLineInfo(int max_energy, int max_io, int max_distance, double max_angle) {
        this.max_energy = max_energy;
        this.max_io = max_io;
        this.max_distance = max_distance;
        this.max_angle = max_angle;
    }
}
