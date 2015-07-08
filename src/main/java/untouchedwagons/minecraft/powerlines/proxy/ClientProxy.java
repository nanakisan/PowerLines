package untouchedwagons.minecraft.powerlines.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import untouchedwagons.minecraft.powerlines.render.LargePowerLineRenderer;
import untouchedwagons.minecraft.powerlines.render.SubStationRenderer;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityLargePowerLine;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntitySubStation;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerTileEntitiesRenderers() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLargePowerLine.class, new LargePowerLineRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySubStation.class, new SubStationRenderer());
    }
}
