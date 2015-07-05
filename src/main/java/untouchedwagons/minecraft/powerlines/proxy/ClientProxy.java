package untouchedwagons.minecraft.powerlines.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import untouchedwagons.minecraft.powerlines.render.RenderLargePowerLine;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityLargePowerLine;

public class ClientProxy extends CommonProxy {
    public ClientProxy() {
        RENDER_ID = RenderingRegistry.getNextAvailableRenderId();
    }

    @Override
    public void registerSpecialTileEntities() {
        GameRegistry.registerTileEntity(TileEntityLargePowerLine.class, "LargePowerLine");
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLargePowerLine.class, new RenderLargePowerLine());
    }
}
