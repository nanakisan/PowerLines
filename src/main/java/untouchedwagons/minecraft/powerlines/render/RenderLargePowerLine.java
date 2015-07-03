package untouchedwagons.minecraft.powerlines.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import untouchedwagons.minecraft.powerlines.models.ModelLargePowerLine;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityLargePowerLine;

public class RenderLargePowerLine extends TileEntitySpecialRenderer {
    private final ModelLargePowerLine model = new ModelLargePowerLine();

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTick) {
        renderTileEntityAt((TileEntityLargePowerLine)te, x, y, z, partialTick);
    }

    public void renderTileEntityAt(TileEntityLargePowerLine te, double x, double y, double z, float partialTick) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);

        bindTexture(new ResourceLocation("powerlines", "render/largePowerLine.png"));

        GL11.glRotatef(180, 0F, 0F, 1F);

        model.render(null, 0, 0, 0, 0, 0, 0);

        GL11.glPopMatrix();
    }
}
