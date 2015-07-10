package untouchedwagons.minecraft.powerlines.render.blocks;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import untouchedwagons.minecraft.powerlines.models.ModelSubStation;

public class SubStationRenderer extends TileEntitySpecialRenderer {
    private final ModelSubStation model = new ModelSubStation();
    private final ResourceLocation texture = new ResourceLocation("powerlines", "render/subStation.png");

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTick) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 1F, (float) y + 1.5F, (float) z + 1F);

        bindTexture(texture);

        GL11.glRotatef(180, 0F, 0F, 1F);

        model.render(null, 0, 0, 0, 0, 0, 0.0625F);

        GL11.glPopMatrix();
    }
}
