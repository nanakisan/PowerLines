package untouchedwagons.minecraft.powerlines.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import untouchedwagons.minecraft.powerlines.models.ModelLargePowerLine;

public class LargePowerLineRenderer extends TileEntitySpecialRenderer {
    private final ModelLargePowerLine model = new ModelLargePowerLine();
    private final ResourceLocation texture = new ResourceLocation("powerlines", "render/largePowerLine.png");

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTick) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);

        bindTexture(texture);

        GL11.glRotatef(180, 0F, 0F, 1F);

        model.render(null, 0, 0, 0, 0, 0, 0.0625F);

        GL11.glPopMatrix();
    }
}
