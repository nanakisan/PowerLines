package untouchedwagons.minecraft.powerlines.render.blocks;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import untouchedwagons.minecraft.powerlines.extra.Rotation;
import untouchedwagons.minecraft.powerlines.models.ModelLargePowerLine;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityLargePowerLine;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntitySubStation;

public class LargePowerLineRenderer extends TileEntitySpecialRenderer {
    private final ModelLargePowerLine model = new ModelLargePowerLine();
    private final ResourceLocation texture = new ResourceLocation("powerlines", "render/largePowerLine.png");

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTick) {
        renderTileEntityAt((TileEntityLargePowerLine)te, x, y, z, partialTick);
    }

    public void renderTileEntityAt(TileEntityLargePowerLine te, double x, double y, double z, float partialTick) {
        bindTexture(texture);

        GL11.glPushMatrix();

        if (te.getRotation() == Rotation.EAST_WEST)
        {
            GL11.glTranslated((float) x + 1F, (float) y + 1.5F, (float) z + 1);
        }
        else
        {
            GL11.glTranslatef((float) x + 1F, (float) y + 1.5F, (float) z);
            GL11.glRotatef(90f, 0f, 1f, 0f);
        }

        GL11.glRotatef(180, 0F, 0F, 1F);

        model.render(null, 0, 0, 0, 0, 0, 0.0625F);

        GL11.glPopMatrix();
    }
}
