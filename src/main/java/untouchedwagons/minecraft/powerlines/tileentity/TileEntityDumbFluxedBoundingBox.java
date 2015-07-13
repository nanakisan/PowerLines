package untouchedwagons.minecraft.powerlines.tileentity;

import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityDumbFluxedBoundingBox extends TileEntityFluxedBoundingBox {
    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return false;
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
        return 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return 0;
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        return 0;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return 0;
    }

    @Override
    public void updateEntity() {

    }
}
