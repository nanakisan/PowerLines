package untouchedwagons.minecraft.powerlines.render.blocks;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import untouchedwagons.minecraft.powerlines.extra.Rotation;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityLargePowerLine;

public class LargePowerLineRenderer extends TileEntitySpecialRenderer {
    private final ResourceLocation texture;
    private final ResourceLocation obj_model;
    private final IModelCustom model;

    public LargePowerLineRenderer() {
        this.texture = new ResourceLocation("powerlines", "render/largePowerLine.png");
        this.obj_model = new ResourceLocation("powerlines", "models/LargePowerLine.obj");
        this.model = AdvancedModelLoader.loadModel(obj_model);
    }

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTick) {
        renderTileEntityAt((TileEntityLargePowerLine)te, x, y, z, partialTick);
    }

    public void renderTileEntityAt(TileEntityLargePowerLine te, double x, double y, double z, float partialTick) {
        bindTexture(texture);

        GL11.glPushMatrix();

        if (te.getRotation() == Rotation.EAST_WEST)
        {
            GL11.glTranslated(x + 2.9, y, z + 2.9);
            GL11.glRotated(90, 0, 1, 0);
        }
        else
        {
            GL11.glTranslated(x - 1.9, y, z + 2.9);
        }

        this.model.renderAll();

        GL11.glPopMatrix();
    }
}
