package untouchedwagons.minecraft.powerlines.extra;

public class MultiblockPosition {
    private int x_offset, y_offset, z_offset;
    private BoundingBlockType type;

    public enum BoundingBlockType {
        Simple(0),
        Fluxed(1),
        DumbFluxed(2),
        None(4);

        private final int type;

        BoundingBlockType(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }
    };

    public MultiblockPosition(int x_offset, int y_offset, int z_offset, BoundingBlockType type) {
        this.x_offset = x_offset;
        this.y_offset = y_offset;
        this.z_offset = z_offset;
        this.type = type;
    }

    public int getXOffset() {
        return x_offset;
    }

    public int getYOffset() {
        return y_offset;
    }

    public int getZOffset() {
        return z_offset;
    }

    public BoundingBlockType getType() {
        return type;
    }
}
