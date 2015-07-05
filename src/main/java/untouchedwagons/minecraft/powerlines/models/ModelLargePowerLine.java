package untouchedwagons.minecraft.powerlines.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * LargePowerLine - UntouchedWagons
 * Created using Tabula 4.1.1
 */
public class ModelLargePowerLine extends ModelBase {
    public ModelRenderer BasePlate;
    public ModelRenderer Pole;
    public ModelRenderer MainCrossbar;
    public ModelRenderer LeftArm;
    public ModelRenderer RightArm;
    public ModelRenderer RightConnector;
    public ModelRenderer LeftConnector;

    public ModelLargePowerLine() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.LeftConnector = new ModelRenderer(this, 0, 0);
        this.LeftConnector.setRotationPoint(-22.0F, -41.0F, -2.0F);
        this.LeftConnector.addBox(0.0F, 0.0F, 0.0F, 4, 10, 4, 0.0F);
        this.MainCrossbar = new ModelRenderer(this, 0, 0);
        this.MainCrossbar.setRotationPoint(-2.0F, -42.0F, -2.0F);
        this.MainCrossbar.addBox(0.0F, 0.0F, 0.0F, 4, 16, 4, 0.0F);
        this.RightConnector = new ModelRenderer(this, 0, 0);
        this.RightConnector.setRotationPoint(18.0F, -41.0F, -2.0F);
        this.RightConnector.addBox(0.0F, 0.0F, 0.0F, 4, 10, 4, 0.0F);
        this.BasePlate = new ModelRenderer(this, 0, 0);
        this.BasePlate.setRotationPoint(-8.0F, 22.0F, -8.0F);
        this.BasePlate.addBox(0.0F, 0.0F, 0.0F, 16, 2, 16, 0.0F);
        this.LeftArm = new ModelRenderer(this, 0, 0);
        this.LeftArm.setRotationPoint(-18.0F, -39.0F, -1.0F);
        this.LeftArm.addBox(0.0F, 0.0F, 0.0F, 16, 6, 2, 0.0F);
        this.RightArm = new ModelRenderer(this, 0, 0);
        this.RightArm.setRotationPoint(2.0F, -39.0F, -1.0F);
        this.RightArm.addBox(0.0F, 0.0F, 0.0F, 16, 6, 2, 0.0F);
        this.Pole = new ModelRenderer(this, 0, 0);
        this.Pole.setRotationPoint(-4.0F, -26.0F, -4.0F);
        this.Pole.addBox(0.0F, 0.0F, 0.0F, 8, 48, 8, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.LeftConnector.render(f5);
        this.MainCrossbar.render(f5);
        this.RightConnector.render(f5);
        this.BasePlate.render(f5);
        this.LeftArm.render(f5);
        this.RightArm.render(f5);
        this.Pole.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

