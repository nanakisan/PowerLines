package untouchedwagons.minecraft.powerlines.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import untouchedwagons.minecraft.powerlines.models.ModelLargePowerLine;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityLargePowerLine;

public class LargePowerLineRenderer extends TileEntitySpecialRenderer {
    private final ModelLargePowerLine model = new ModelLargePowerLine();

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTick) {
        renderTileEntityAt((TileEntityLargePowerLine)te, x, y, z, partialTick);
    }

    public void renderTileEntityAt(TileEntityLargePowerLine te, double x, double y, double z, float partialTick) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x, (float) y, (float) z);

        bindTexture(new ResourceLocation("powerlines", "render/largePowerLine.png"));

        GL11.glRotatef(180, 0F, 0F, 1F);

        model.render(null, (float) x, (float) y, (float) z, 0, 0, partialTick);

        GL11.glPopMatrix();


    }
}
