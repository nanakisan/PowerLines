package untouchedwagons.minecraft.powerlines.render.blocks;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import untouchedwagons.minecraft.powerlines.extra.Rotation;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityMediumPowerLine;

public class MediumPowerLineRenderer extends TileEntitySpecialRenderer {
    private final ResourceLocation texture;
    private final ResourceLocation obj_model;
    private final IModelCustom model;

    public MediumPowerLineRenderer() {
        this.texture = new ResourceLocation("powerlines", "render/mediumPowerLine.png");
        this.obj_model = new ResourceLocation("powerlines", "models/MediumPowerLine.obj");
        this.model = AdvancedModelLoader.loadModel(obj_model);
    }

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTick) {
        renderTileEntityAt((TileEntityMediumPowerLine)te, x, y, z, partialTick);
    }

    public void renderTileEntityAt(TileEntityMediumPowerLine te, double x, double y, double z, float partialTick) {
        bindTexture(texture);

        GL11.glPushMatrix();

        if (te.getRotation() == Rotation.EAST_WEST)
        {
            GL11.glTranslated(x + 1, y, z + 1);
            GL11.glRotatef(90f, 0f, 1f, 0f);
        }
        else
        {
            GL11.glTranslated(x, y, z + 1);
        }

        GL11.glScaled(0.62, 0.62, 0.62);

        this.model.renderAll();

        GL11.glPopMatrix();
    }
}
