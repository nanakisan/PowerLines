package untouchedwagons.minecraft.powerlines.extra;

public enum EnergyMode
{
    INPUT("input"), OUTPUT("output"), UNKNOWN("unknown");

    public static final EnergyMode[] VALID_MODES = { INPUT, OUTPUT };
    public static final int[] OPPOSITES = { 1, 0, 0 };

    private final String mode;

    EnergyMode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }

    public static EnergyMode fromString(String mode)
    {
        for (EnergyMode valid_mode : EnergyMode.VALID_MODES)
        {
            if (valid_mode.getMode().equals(mode))
            {
                return valid_mode;
            }
        }

        return EnergyMode.UNKNOWN;
    }

    @Override
    public String toString() {
        return this.mode;
    }

    public EnergyMode getOpposite()
    {
        return VALID_MODES[OPPOSITES[ordinal()]];
    }
}