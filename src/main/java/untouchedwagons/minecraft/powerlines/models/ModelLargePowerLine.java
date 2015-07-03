package untouchedwagons.minecraft.powerlines.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLargePowerLine extends ModelBase
{
    ModelRenderer Base;
    ModelRenderer Pole;

    public ModelLargePowerLine()
    {
        this( 0.0f );
    }

    public ModelLargePowerLine(float par1)
    {
        Base = new ModelRenderer( this, 0, 0 );
        Base.setTextureSize( 64, 32 );
        Base.addBox( -8F, -8F, -8F, 16, 16, 16);
        Base.setRotationPoint( 0F, -8F, 0F );
        Pole = new ModelRenderer( this, 0, 0 );
        Pole.setTextureSize( 64, 32 );
        Pole.addBox( -8F, -8F, -8F, 16, 16, 16);
        Pole.setRotationPoint( 0F, -408F, 0F );
    }

   public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
   {
        Base.rotateAngleX = 0F;
        Base.rotateAngleY = 0F;
        Base.rotateAngleZ = 0F;
        Base.renderWithRotation(par7);

        Pole.rotateAngleX = 0F;
        Pole.rotateAngleY = 0F;
        Pole.rotateAngleZ = 0F;
        Pole.renderWithRotation(par7);

    }

}
