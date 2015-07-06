package untouchedwagons.minecraft.powerlines.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import untouchedwagons.minecraft.powerlines.render.LargePowerLineRenderer;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityLargePowerLine;

public class ClientProxy extends CommonProxy {
    public ClientProxy() {
        RENDER_ID = RenderingRegistry.getNextAvailableRenderId();
    }

    @Override
    public void registerSpecialTileEntities() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLargePowerLine.class, new LargePowerLineRenderer());
    }
}
