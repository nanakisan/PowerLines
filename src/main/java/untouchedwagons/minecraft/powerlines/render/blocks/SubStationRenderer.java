package untouchedwagons.minecraft.powerlines.render.blocks;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import untouchedwagons.math.MathHelper;
import untouchedwagons.minecraft.powerlines.extra.Rotation;
import untouchedwagons.minecraft.powerlines.models.ModelSubStation;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntitySubStation;

public class SubStationRenderer extends TileEntitySpecialRenderer {
    private final ModelSubStation model = new ModelSubStation();
    private final ResourceLocation texture = new ResourceLocation("powerlines", "render/subStation.png");

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTick) {
        renderTileEntityAt((TileEntitySubStation)te, x, y, z, partialTick);
    }

    /**
     * Renders the substation
     * @param te the substation to be rendered
     * @param x The X coordinate of the player
     * @param y The Y coordinate of the player
     * @param z The Z coordinate of the player
     * @param partialTick /me shrugs
     */
    public void renderTileEntityAt(TileEntitySubStation te, double x, double y, double z, float partialTick) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);

        bindTexture(texture);

        GL11.glRotatef(180F, 0F, 0F, 1F);

        if (te.getRotation() == Rotation.EAST_WEST)
        {
            GL11.glRotatef(90f, 0f, 1f, 0f);
        }

        model.render(null, 0, 0, 0, 0, 0, 0.0625F);

        /*Tessellator tessellator = Tessellator.instance;

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LIGHTING);
        tessellator.startDrawing(GL11.GL_LINES);
        tessellator.setColorOpaque_I(0);

        tessellator.addVertex(0, 5, 0);
        tessellator.addVertex(0, 10, 0);

        tessellator.draw();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_TEXTURE_2D);*/

        GL11.glPopMatrix();
    }
}
