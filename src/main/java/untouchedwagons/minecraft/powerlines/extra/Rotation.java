package untouchedwagons.minecraft.powerlines.extra;

public enum Rotation {
    NORTH_SOUTH("north-south"), EAST_WEST("east-west"), UNKNOWN("unknown");

    private final String rotation;

    public static final Rotation[] ROTATION_MAP = { NORTH_SOUTH, EAST_WEST, NORTH_SOUTH, EAST_WEST };
    public static final Rotation[] VALID_ROTATIONS = { NORTH_SOUTH, EAST_WEST };
    public static final int[] OPPOSITES = { 1, 0, 0 };

    Rotation(String rotation) {
        this.rotation = rotation;
    }

    public String getRotation() {
        return rotation;
    }

    public static Rotation fromString(String rotation)
    {
        for (Rotation valid_rotation : Rotation.VALID_ROTATIONS)
        {
            if (valid_rotation.getRotation().equals(rotation))
            {
                return valid_rotation;
            }
        }

        return Rotation.UNKNOWN;
    }

    public static Rotation fromInt(int rotation)
    {
        return ROTATION_MAP[rotation];
    }

    @Override
    public String toString() {
        return this.rotation;
    }

    public Rotation getOpposite()
    {
        return VALID_ROTATIONS[OPPOSITES[ordinal()]];
    }
}
