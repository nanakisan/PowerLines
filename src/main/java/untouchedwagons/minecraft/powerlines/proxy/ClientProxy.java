package untouchedwagons.minecraft.powerlines.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import untouchedwagons.minecraft.powerlines.render.LargePowerLineRenderer;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityLargePowerLine;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerTileEntitiesRenderers() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLargePowerLine.class, new LargePowerLineRenderer());
    }
}
