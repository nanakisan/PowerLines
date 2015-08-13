package untouchedwagons.minecraft.powerlines.render.blocks;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
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

        GL11.glPopMatrix();
    }
}
