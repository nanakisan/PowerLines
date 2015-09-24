package untouchedwagons.minecraft.powerlines.render.blocks;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import untouchedwagons.math.MathHelper;
import untouchedwagons.minecraft.powerlines.extra.Rotation;
import untouchedwagons.minecraft.powerlines.models.ModelSubStation;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntitySubStation;

public class SubStationRenderer extends TileEntitySpecialRenderer {
    private final ResourceLocation texture;
    private final ResourceLocation obj_model;
    private final IModelCustom model;

    public SubStationRenderer() {
        texture = new ResourceLocation("powerlines", "render/subStation.png");
        obj_model = new ResourceLocation("powerlines", "models/SubStation.obj");
        model = AdvancedModelLoader.loadModel(obj_model);
    }

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
        bindTexture(texture);

        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);

        if (te.getRotation() == Rotation.EAST_WEST)
        {
            GL11.glRotatef(90f, 0f, 1f, 0f);
        }

        model.renderAll();

        GL11.glPopMatrix();
    }
}
