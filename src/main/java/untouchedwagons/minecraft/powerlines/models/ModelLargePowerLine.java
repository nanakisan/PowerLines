package untouchedwagons.minecraft.powerlines.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLargePowerLine extends ModelBase
{
    ModelRenderer Base_part;
    ModelRenderer Foot_Plate_Alpha;
    ModelRenderer Alpha_Upright;
    ModelRenderer Bravo_Upright;
    ModelRenderer Charlie_Upright;
    ModelRenderer Alpha_Crossmember;
    ModelRenderer Bravo_Crossmember;
    ModelRenderer Charlie_Crossmember;
    ModelRenderer Delta_Crossmember;
    ModelRenderer Echo_Crossmember;
    ModelRenderer Foxtrot_Crossmember;
    ModelRenderer Connector_Bar;
    ModelRenderer Connector_Disk_Alpha;
    ModelRenderer Part_Alpha;
    ModelRenderer Part_Bravo;
    ModelRenderer Part_Charlie;
    ModelRenderer Part_Delta;
    ModelRenderer Connector_Disk_Bravo;
    ModelRenderer Part_Alpha1;
    ModelRenderer Part_Bravo1;
    ModelRenderer Part_Charlie1;
    ModelRenderer Part_Delta1;
    ModelRenderer Connector_Disk_Charlie;
    ModelRenderer Part_Alpha2;
    ModelRenderer Part_Bravo2;
    ModelRenderer Part_Charlie2;
    ModelRenderer Part_Delta2;
    ModelRenderer Connector_Disk_Delta;
    ModelRenderer Part_Alpha3;
    ModelRenderer Part_Bravo3;
    ModelRenderer Part_Charlie3;
    ModelRenderer Part_Delta3;
    ModelRenderer Connector_Disk_Echo;
    ModelRenderer Part_Alpha4;
    ModelRenderer Part_Bravo4;
    ModelRenderer Part_Charlie4;
    ModelRenderer Part_Delta4;
    ModelRenderer Foot_Plate_Bravo;
    ModelRenderer Alpha_Upright1;
    ModelRenderer Bravo_Upright1;
    ModelRenderer Charlie_Upright1;
    ModelRenderer Alpha_Crossmember1;
    ModelRenderer Bravo_Crossmember1;
    ModelRenderer Charlie_Crossmember1;
    ModelRenderer Delta_Crossmember1;
    ModelRenderer Foot_Plate_Charlie;
    ModelRenderer Alpha_Upright2;
    ModelRenderer Bravo_Upright2;
    ModelRenderer Charlie_Upright2;
    ModelRenderer Alpha_Crossmember2;
    ModelRenderer Bravo_Crossmember2;
    ModelRenderer Charlie_Crossmember2;
    ModelRenderer Delta_Crossmember2;
    ModelRenderer Echo_Crossmember1;
    ModelRenderer Foxtrot_Crossmember1;
    ModelRenderer Connector_Bar1;
    ModelRenderer Connector_Disk_Alpha1;
    ModelRenderer Part_Alpha5;
    ModelRenderer Part_Bravo5;
    ModelRenderer Part_Charlie5;
    ModelRenderer Part_Delta5;
    ModelRenderer Connector_Disk_Bravo1;
    ModelRenderer Part_Alpha6;
    ModelRenderer Part_Bravo6;
    ModelRenderer Part_Charlie6;
    ModelRenderer Part_Delta6;
    ModelRenderer Connector_Disk_Charlie1;
    ModelRenderer Part_Alpha7;
    ModelRenderer Part_Bravo7;
    ModelRenderer Part_Charlie7;
    ModelRenderer Part_Delta7;
    ModelRenderer Connector_Disk_Delta1;
    ModelRenderer Part_Alpha8;
    ModelRenderer Part_Bravo8;
    ModelRenderer Part_Charlie8;
    ModelRenderer Part_Delta8;
    ModelRenderer Connector_Disk_Echo1;
    ModelRenderer Part_Alpha9;
    ModelRenderer Part_Bravo9;
    ModelRenderer Part_Charlie9;
    ModelRenderer Part_Delta9;
    ModelRenderer Foot_Plate_Delta;
    ModelRenderer Alpha_Upright3;
    ModelRenderer Bravo_Upright3;
    ModelRenderer Charlie_Upright3;
    ModelRenderer Alpha_Crossmember3;
    ModelRenderer Bravo_Crossmember3;
    ModelRenderer Charlie_Crossmember3;
    ModelRenderer Delta_Crossmember3;
    ModelRenderer Connecter_Arm_Alpha;
    ModelRenderer Connector_Helper_Arm;
    ModelRenderer Connecter_Arm_Bravo;
    ModelRenderer Connector_Helper_Arm1;
    ModelRenderer Connecter_Arm_Delta;
    ModelRenderer Connector_Helper_Arm2;
    ModelRenderer Connecter_Arm_Charlie;
    ModelRenderer Connector_Helper_Arm3;

    public ModelLargePowerLine()
    {
        this( 0.0f );
    }

    public ModelLargePowerLine( float par1 )
    {
        Base_part = new ModelRenderer( this, 16, 16 );
        Base_part.setTextureSize( 128, 64 );
        Base_part.addBox( 0F, 0F, 0F, 0, 0, 0);
        Base_part.setRotationPoint( 0F, 24F, 0F );
        Foot_Plate_Alpha = new ModelRenderer( this, 32, 0 );
        Foot_Plate_Alpha.setTextureSize( 128, 64 );
        Foot_Plate_Alpha.addBox( -2F, -0.5F, -2F, 4, 1, 4);
        Foot_Plate_Alpha.setRotationPoint( -14F, 23.5F, -30F );
        Alpha_Upright = new ModelRenderer( this, 0, 9 );
        Alpha_Upright.setTextureSize( 128, 64 );
        Alpha_Upright.addBox( -1F, -48F, -1F, 2, 96, 2);
        Alpha_Upright.setRotationPoint( -6F, -23F, -22F );
        Bravo_Upright = new ModelRenderer( this, 32, 21 );
        Bravo_Upright.setTextureSize( 128, 64 );
        Bravo_Upright.addBox( -1F, -8F, -1F, 2, 16, 2);
        Bravo_Upright.setRotationPoint( 2.299999F, -76.5F, -13.8F );
        Charlie_Upright = new ModelRenderer( this, 40, 21 );
        Charlie_Upright.setTextureSize( 128, 64 );
        Charlie_Upright.addBox( -1F, -4.5F, -1F, 2, 9, 2);
        Charlie_Upright.setRotationPoint( 2.299999F, -86.97F, -10.9F );
        Alpha_Crossmember = new ModelRenderer( this, 0, 13 );
        Alpha_Crossmember.setTextureSize( 128, 64 );
        Alpha_Crossmember.addBox( -21F, -1F, -1F, 42, 2, 2);
        Alpha_Crossmember.setRotationPoint( 8F, 12.5F, -28.18F );
        Bravo_Crossmember = new ModelRenderer( this, 0, 17 );
        Bravo_Crossmember.setTextureSize( 128, 64 );
        Bravo_Crossmember.addBox( -12.5F, -1F, -1F, 25, 2, 2);
        Bravo_Crossmember.setRotationPoint( 8F, -33.5F, -20F );
        Charlie_Crossmember = new ModelRenderer( this, 54, 17 );
        Charlie_Crossmember.setTextureSize( 128, 64 );
        Charlie_Crossmember.addBox( -6.5F, -1F, -1F, 13, 2, 2);
        Charlie_Crossmember.setRotationPoint( 8F, -68.4F, -13.98F );
        Delta_Crossmember = new ModelRenderer( this, 84, 17 );
        Delta_Crossmember.setTextureSize( 128, 64 );
        Delta_Crossmember.addBox( -5F, -1F, -1F, 10, 2, 2);
        Delta_Crossmember.setRotationPoint( 8F, -83.5F, -13.8F );
        Echo_Crossmember = new ModelRenderer( this, 108, 17 );
        Echo_Crossmember.setTextureSize( 128, 64 );
        Echo_Crossmember.addBox( -3.5F, -1F, -1F, 7, 2, 2);
        Echo_Crossmember.setRotationPoint( 11.21F, -89.9F, -8F );
        Foxtrot_Crossmember = new ModelRenderer( this, 88, 13 );
        Foxtrot_Crossmember.setTextureSize( 128, 64 );
        Foxtrot_Crossmember.addBox( -5F, -1F, -1F, 10, 2, 2);
        Foxtrot_Crossmember.setRotationPoint( 8F, -68.6F, -41F );
        Connector_Bar = new ModelRenderer( this, 0, 21 );
        Connector_Bar.setTextureSize( 128, 64 );
        Connector_Bar.addBox( -0.5F, -8F, -0.5F, 1, 16, 1);
        Connector_Bar.setRotationPoint( 8F, -59.6F, -41F );
        Connector_Disk_Alpha = new ModelRenderer( this, 4, 21 );
        Connector_Disk_Alpha.setTextureSize( 128, 64 );
        Connector_Disk_Alpha.addBox( -3F, -1F, -3F, 6, 2, 6);
        Connector_Disk_Alpha.setRotationPoint( 8F, -65.6F, -41F );
        Part_Alpha = new ModelRenderer( this, 0, 0 );
        Part_Alpha.setTextureSize( 128, 64 );
        Part_Alpha.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Alpha.setRotationPoint( 8F, -65.6F, -44.5F );
        Part_Bravo = new ModelRenderer( this, 0, 0 );
        Part_Bravo.setTextureSize( 128, 64 );
        Part_Bravo.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Bravo.setRotationPoint( 11.5F, -65.6F, -41F );
        Part_Charlie = new ModelRenderer( this, 0, 0 );
        Part_Charlie.setTextureSize( 128, 64 );
        Part_Charlie.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Charlie.setRotationPoint( 4.5F, -65.6F, -41F );
        Part_Delta = new ModelRenderer( this, 0, 0 );
        Part_Delta.setTextureSize( 128, 64 );
        Part_Delta.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Delta.setRotationPoint( 8F, -65.6F, -37.5F );
        Connector_Disk_Bravo = new ModelRenderer( this, 4, 21 );
        Connector_Disk_Bravo.setTextureSize( 128, 64 );
        Connector_Disk_Bravo.addBox( -3F, -1F, -3F, 6, 2, 6);
        Connector_Disk_Bravo.setRotationPoint( 8F, -63.1F, -41F );
        Part_Alpha1 = new ModelRenderer( this, 0, 0 );
        Part_Alpha1.setTextureSize( 128, 64 );
        Part_Alpha1.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Alpha1.setRotationPoint( 8F, -63.1F, -44.5F );
        Part_Bravo1 = new ModelRenderer( this, 0, 0 );
        Part_Bravo1.setTextureSize( 128, 64 );
        Part_Bravo1.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Bravo1.setRotationPoint( 11.5F, -63.1F, -41F );
        Part_Charlie1 = new ModelRenderer( this, 0, 0 );
        Part_Charlie1.setTextureSize( 128, 64 );
        Part_Charlie1.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Charlie1.setRotationPoint( 4.5F, -63.1F, -41F );
        Part_Delta1 = new ModelRenderer( this, 0, 0 );
        Part_Delta1.setTextureSize( 128, 64 );
        Part_Delta1.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Delta1.setRotationPoint( 8F, -63.1F, -37.5F );
        Connector_Disk_Charlie = new ModelRenderer( this, 4, 21 );
        Connector_Disk_Charlie.setTextureSize( 128, 64 );
        Connector_Disk_Charlie.addBox( -3F, -1F, -3F, 6, 2, 6);
        Connector_Disk_Charlie.setRotationPoint( 8F, -60.6F, -41F );
        Part_Alpha2 = new ModelRenderer( this, 0, 0 );
        Part_Alpha2.setTextureSize( 128, 64 );
        Part_Alpha2.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Alpha2.setRotationPoint( 8F, -60.6F, -44.5F );
        Part_Bravo2 = new ModelRenderer( this, 0, 0 );
        Part_Bravo2.setTextureSize( 128, 64 );
        Part_Bravo2.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Bravo2.setRotationPoint( 11.5F, -60.6F, -41F );
        Part_Charlie2 = new ModelRenderer( this, 0, 0 );
        Part_Charlie2.setTextureSize( 128, 64 );
        Part_Charlie2.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Charlie2.setRotationPoint( 4.5F, -60.6F, -41F );
        Part_Delta2 = new ModelRenderer( this, 0, 0 );
        Part_Delta2.setTextureSize( 128, 64 );
        Part_Delta2.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Delta2.setRotationPoint( 8F, -60.6F, -37.5F );
        Connector_Disk_Delta = new ModelRenderer( this, 4, 21 );
        Connector_Disk_Delta.setTextureSize( 128, 64 );
        Connector_Disk_Delta.addBox( -3F, -1F, -3F, 6, 2, 6);
        Connector_Disk_Delta.setRotationPoint( 8F, -58.1F, -41F );
        Part_Alpha3 = new ModelRenderer( this, 0, 0 );
        Part_Alpha3.setTextureSize( 128, 64 );
        Part_Alpha3.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Alpha3.setRotationPoint( 8F, -58.1F, -44.5F );
        Part_Bravo3 = new ModelRenderer( this, 0, 0 );
        Part_Bravo3.setTextureSize( 128, 64 );
        Part_Bravo3.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Bravo3.setRotationPoint( 11.5F, -58.1F, -41F );
        Part_Charlie3 = new ModelRenderer( this, 0, 0 );
        Part_Charlie3.setTextureSize( 128, 64 );
        Part_Charlie3.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Charlie3.setRotationPoint( 4.5F, -58.1F, -41F );
        Part_Delta3 = new ModelRenderer( this, 0, 0 );
        Part_Delta3.setTextureSize( 128, 64 );
        Part_Delta3.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Delta3.setRotationPoint( 8F, -58.1F, -37.5F );
        Connector_Disk_Echo = new ModelRenderer( this, 4, 21 );
        Connector_Disk_Echo.setTextureSize( 128, 64 );
        Connector_Disk_Echo.addBox( -3F, -1F, -3F, 6, 2, 6);
        Connector_Disk_Echo.setRotationPoint( 8F, -55.6F, -41F );
        Part_Alpha4 = new ModelRenderer( this, 0, 0 );
        Part_Alpha4.setTextureSize( 128, 64 );
        Part_Alpha4.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Alpha4.setRotationPoint( 8F, -55.6F, -44.5F );
        Part_Bravo4 = new ModelRenderer( this, 0, 0 );
        Part_Bravo4.setTextureSize( 128, 64 );
        Part_Bravo4.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Bravo4.setRotationPoint( 11.5F, -55.6F, -41F );
        Part_Charlie4 = new ModelRenderer( this, 0, 0 );
        Part_Charlie4.setTextureSize( 128, 64 );
        Part_Charlie4.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Charlie4.setRotationPoint( 4.5F, -55.6F, -41F );
        Part_Delta4 = new ModelRenderer( this, 0, 0 );
        Part_Delta4.setTextureSize( 128, 64 );
        Part_Delta4.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Delta4.setRotationPoint( 8F, -55.6F, -37.5F );
        Foot_Plate_Bravo = new ModelRenderer( this, 32, 0 );
        Foot_Plate_Bravo.setTextureSize( 128, 64 );
        Foot_Plate_Bravo.addBox( -2F, -0.5F, -2F, 4, 1, 4);
        Foot_Plate_Bravo.setRotationPoint( -14F, 23.5F, 14F );
        Alpha_Upright1 = new ModelRenderer( this, 0, 9 );
        Alpha_Upright1.setTextureSize( 128, 64 );
        Alpha_Upright1.addBox( -1F, -48F, -1F, 2, 96, 2);
        Alpha_Upright1.setRotationPoint( -6F, -23F, 6F );
        Bravo_Upright1 = new ModelRenderer( this, 32, 21 );
        Bravo_Upright1.setTextureSize( 128, 64 );
        Bravo_Upright1.addBox( -1F, -8F, -1F, 2, 16, 2);
        Bravo_Upright1.setRotationPoint( 13.7F, -76.5F, -13.8F );
        Charlie_Upright1 = new ModelRenderer( this, 40, 21 );
        Charlie_Upright1.setTextureSize( 128, 64 );
        Charlie_Upright1.addBox( -1F, -4.5F, -1F, 2, 9, 2);
        Charlie_Upright1.setRotationPoint( 13.7F, -86.97F, -10.9F );
        Alpha_Crossmember1 = new ModelRenderer( this, 0, 13 );
        Alpha_Crossmember1.setTextureSize( 128, 64 );
        Alpha_Crossmember1.addBox( -21F, -1F, -1F, 42, 2, 2);
        Alpha_Crossmember1.setRotationPoint( -12.18F, 12.5F, -8F );
        Bravo_Crossmember1 = new ModelRenderer( this, 0, 17 );
        Bravo_Crossmember1.setTextureSize( 128, 64 );
        Bravo_Crossmember1.addBox( -12.5F, -1F, -1F, 25, 2, 2);
        Bravo_Crossmember1.setRotationPoint( -4F, -33.5F, -8F );
        Charlie_Crossmember1 = new ModelRenderer( this, 54, 17 );
        Charlie_Crossmember1.setTextureSize( 128, 64 );
        Charlie_Crossmember1.addBox( -6.5F, -1F, -1F, 13, 2, 2);
        Charlie_Crossmember1.setRotationPoint( 2.16F, -68.4F, -8F );
        Delta_Crossmember1 = new ModelRenderer( this, 84, 17 );
        Delta_Crossmember1.setTextureSize( 128, 64 );
        Delta_Crossmember1.addBox( -5F, -1F, -1F, 10, 2, 2);
        Delta_Crossmember1.setRotationPoint( 2.299999F, -83.5F, -8F );
        Foot_Plate_Charlie = new ModelRenderer( this, 32, 0 );
        Foot_Plate_Charlie.setTextureSize( 128, 64 );
        Foot_Plate_Charlie.addBox( -2F, -0.5F, -2F, 4, 1, 4);
        Foot_Plate_Charlie.setRotationPoint( 30F, 23.5F, 14F );
        Alpha_Upright2 = new ModelRenderer( this, 0, 9 );
        Alpha_Upright2.setTextureSize( 128, 64 );
        Alpha_Upright2.addBox( -1F, -48F, -1F, 2, 96, 2);
        Alpha_Upright2.setRotationPoint( 22F, -23F, 6F );
        Bravo_Upright2 = new ModelRenderer( this, 32, 21 );
        Bravo_Upright2.setTextureSize( 128, 64 );
        Bravo_Upright2.addBox( -1F, -8F, -1F, 2, 16, 2);
        Bravo_Upright2.setRotationPoint( 13.7F, -76.5F, -2.200001F );
        Charlie_Upright2 = new ModelRenderer( this, 40, 21 );
        Charlie_Upright2.setTextureSize( 128, 64 );
        Charlie_Upright2.addBox( -1F, -4.5F, -1F, 2, 9, 2);
        Charlie_Upright2.setRotationPoint( 13.7F, -86.97F, -5.08F );
        Alpha_Crossmember2 = new ModelRenderer( this, 0, 13 );
        Alpha_Crossmember2.setTextureSize( 128, 64 );
        Alpha_Crossmember2.addBox( -21F, -1F, -1F, 42, 2, 2);
        Alpha_Crossmember2.setRotationPoint( 8F, 12.5F, 12.18F );
        Bravo_Crossmember2 = new ModelRenderer( this, 0, 17 );
        Bravo_Crossmember2.setTextureSize( 128, 64 );
        Bravo_Crossmember2.addBox( -12.5F, -1F, -1F, 25, 2, 2);
        Bravo_Crossmember2.setRotationPoint( 8F, -33.5F, 4F );
        Charlie_Crossmember2 = new ModelRenderer( this, 54, 17 );
        Charlie_Crossmember2.setTextureSize( 128, 64 );
        Charlie_Crossmember2.addBox( -6.5F, -1F, -1F, 13, 2, 2);
        Charlie_Crossmember2.setRotationPoint( 8F, -68.4F, -2.02F );
        Delta_Crossmember2 = new ModelRenderer( this, 84, 17 );
        Delta_Crossmember2.setTextureSize( 128, 64 );
        Delta_Crossmember2.addBox( -5F, -1F, -1F, 10, 2, 2);
        Delta_Crossmember2.setRotationPoint( 8F, -83.5F, -2.200001F );
        Echo_Crossmember1 = new ModelRenderer( this, 108, 17 );
        Echo_Crossmember1.setTextureSize( 128, 64 );
        Echo_Crossmember1.addBox( -3.5F, -1F, -1F, 7, 2, 2);
        Echo_Crossmember1.setRotationPoint( 4.790001F, -89.9F, -8F );
        Foxtrot_Crossmember1 = new ModelRenderer( this, 88, 13 );
        Foxtrot_Crossmember1.setTextureSize( 128, 64 );
        Foxtrot_Crossmember1.addBox( -5F, -1F, -1F, 10, 2, 2);
        Foxtrot_Crossmember1.setRotationPoint( 8F, -68.6F, 25F );
        Connector_Bar1 = new ModelRenderer( this, 0, 21 );
        Connector_Bar1.setTextureSize( 128, 64 );
        Connector_Bar1.addBox( -0.5F, -8F, -0.5F, 1, 16, 1);
        Connector_Bar1.setRotationPoint( 8F, -59.6F, 25F );
        Connector_Disk_Alpha1 = new ModelRenderer( this, 4, 21 );
        Connector_Disk_Alpha1.setTextureSize( 128, 64 );
        Connector_Disk_Alpha1.addBox( -3F, -1F, -3F, 6, 2, 6);
        Connector_Disk_Alpha1.setRotationPoint( 8F, -65.6F, 25F );
        Part_Alpha5 = new ModelRenderer( this, 0, 0 );
        Part_Alpha5.setTextureSize( 128, 64 );
        Part_Alpha5.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Alpha5.setRotationPoint( 8F, -65.6F, 21.5F );
        Part_Bravo5 = new ModelRenderer( this, 0, 0 );
        Part_Bravo5.setTextureSize( 128, 64 );
        Part_Bravo5.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Bravo5.setRotationPoint( 11.5F, -65.6F, 25F );
        Part_Charlie5 = new ModelRenderer( this, 0, 0 );
        Part_Charlie5.setTextureSize( 128, 64 );
        Part_Charlie5.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Charlie5.setRotationPoint( 4.5F, -65.6F, 25F );
        Part_Delta5 = new ModelRenderer( this, 0, 0 );
        Part_Delta5.setTextureSize( 128, 64 );
        Part_Delta5.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Delta5.setRotationPoint( 8F, -65.6F, 28.5F );
        Connector_Disk_Bravo1 = new ModelRenderer( this, 4, 21 );
        Connector_Disk_Bravo1.setTextureSize( 128, 64 );
        Connector_Disk_Bravo1.addBox( -3F, -1F, -3F, 6, 2, 6);
        Connector_Disk_Bravo1.setRotationPoint( 8F, -63.1F, 25F );
        Part_Alpha6 = new ModelRenderer( this, 0, 0 );
        Part_Alpha6.setTextureSize( 128, 64 );
        Part_Alpha6.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Alpha6.setRotationPoint( 8F, -63.1F, 21.5F );
        Part_Bravo6 = new ModelRenderer( this, 0, 0 );
        Part_Bravo6.setTextureSize( 128, 64 );
        Part_Bravo6.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Bravo6.setRotationPoint( 11.5F, -63.1F, 25F );
        Part_Charlie6 = new ModelRenderer( this, 0, 0 );
        Part_Charlie6.setTextureSize( 128, 64 );
        Part_Charlie6.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Charlie6.setRotationPoint( 4.5F, -63.1F, 25F );
        Part_Delta6 = new ModelRenderer( this, 0, 0 );
        Part_Delta6.setTextureSize( 128, 64 );
        Part_Delta6.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Delta6.setRotationPoint( 8F, -63.1F, 28.5F );
        Connector_Disk_Charlie1 = new ModelRenderer( this, 4, 21 );
        Connector_Disk_Charlie1.setTextureSize( 128, 64 );
        Connector_Disk_Charlie1.addBox( -3F, -1F, -3F, 6, 2, 6);
        Connector_Disk_Charlie1.setRotationPoint( 8F, -60.6F, 25F );
        Part_Alpha7 = new ModelRenderer( this, 0, 0 );
        Part_Alpha7.setTextureSize( 128, 64 );
        Part_Alpha7.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Alpha7.setRotationPoint( 8F, -60.6F, 21.5F );
        Part_Bravo7 = new ModelRenderer( this, 0, 0 );
        Part_Bravo7.setTextureSize( 128, 64 );
        Part_Bravo7.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Bravo7.setRotationPoint( 11.5F, -60.6F, 25F );
        Part_Charlie7 = new ModelRenderer( this, 0, 0 );
        Part_Charlie7.setTextureSize( 128, 64 );
        Part_Charlie7.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Charlie7.setRotationPoint( 4.5F, -60.6F, 25F );
        Part_Delta7 = new ModelRenderer( this, 0, 0 );
        Part_Delta7.setTextureSize( 128, 64 );
        Part_Delta7.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Delta7.setRotationPoint( 8F, -60.6F, 28.5F );
        Connector_Disk_Delta1 = new ModelRenderer( this, 4, 21 );
        Connector_Disk_Delta1.setTextureSize( 128, 64 );
        Connector_Disk_Delta1.addBox( -3F, -1F, -3F, 6, 2, 6);
        Connector_Disk_Delta1.setRotationPoint( 8F, -58.1F, 25F );
        Part_Alpha8 = new ModelRenderer( this, 0, 0 );
        Part_Alpha8.setTextureSize( 128, 64 );
        Part_Alpha8.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Alpha8.setRotationPoint( 8F, -58.1F, 21.5F );
        Part_Bravo8 = new ModelRenderer( this, 0, 0 );
        Part_Bravo8.setTextureSize( 128, 64 );
        Part_Bravo8.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Bravo8.setRotationPoint( 11.5F, -58.1F, 25F );
        Part_Charlie8 = new ModelRenderer( this, 0, 0 );
        Part_Charlie8.setTextureSize( 128, 64 );
        Part_Charlie8.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Charlie8.setRotationPoint( 4.5F, -58.1F, 25F );
        Part_Delta8 = new ModelRenderer( this, 0, 0 );
        Part_Delta8.setTextureSize( 128, 64 );
        Part_Delta8.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Delta8.setRotationPoint( 8F, -58.1F, 28.5F );
        Connector_Disk_Echo1 = new ModelRenderer( this, 4, 21 );
        Connector_Disk_Echo1.setTextureSize( 128, 64 );
        Connector_Disk_Echo1.addBox( -3F, -1F, -3F, 6, 2, 6);
        Connector_Disk_Echo1.setRotationPoint( 8F, -55.6F, 25F );
        Part_Alpha9 = new ModelRenderer( this, 0, 0 );
        Part_Alpha9.setTextureSize( 128, 64 );
        Part_Alpha9.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Alpha9.setRotationPoint( 8F, -55.6F, 21.5F );
        Part_Bravo9 = new ModelRenderer( this, 0, 0 );
        Part_Bravo9.setTextureSize( 128, 64 );
        Part_Bravo9.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Bravo9.setRotationPoint( 11.5F, -55.6F, 25F );
        Part_Charlie9 = new ModelRenderer( this, 0, 0 );
        Part_Charlie9.setTextureSize( 128, 64 );
        Part_Charlie9.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Charlie9.setRotationPoint( 4.5F, -55.6F, 25F );
        Part_Delta9 = new ModelRenderer( this, 0, 0 );
        Part_Delta9.setTextureSize( 128, 64 );
        Part_Delta9.addBox( -3F, -0.5F, -0.5F, 6, 1, 1);
        Part_Delta9.setRotationPoint( 8F, -55.6F, 28.5F );
        Foot_Plate_Delta = new ModelRenderer( this, 32, 0 );
        Foot_Plate_Delta.setTextureSize( 128, 64 );
        Foot_Plate_Delta.addBox( -2F, -0.5F, -2F, 4, 1, 4);
        Foot_Plate_Delta.setRotationPoint( 30F, 23.5F, -30F );
        Alpha_Upright3 = new ModelRenderer( this, 0, 9 );
        Alpha_Upright3.setTextureSize( 128, 64 );
        Alpha_Upright3.addBox( -1F, -48F, -1F, 2, 96, 2);
        Alpha_Upright3.setRotationPoint( 22F, -23F, -22F );
        Bravo_Upright3 = new ModelRenderer( this, 32, 21 );
        Bravo_Upright3.setTextureSize( 128, 64 );
        Bravo_Upright3.addBox( -1F, -8F, -1F, 2, 16, 2);
        Bravo_Upright3.setRotationPoint( 2.299999F, -76.5F, -2.200001F );
        Charlie_Upright3 = new ModelRenderer( this, 40, 21 );
        Charlie_Upright3.setTextureSize( 128, 64 );
        Charlie_Upright3.addBox( -1F, -4.5F, -1F, 2, 9, 2);
        Charlie_Upright3.setRotationPoint( 2.299999F, -86.97F, -5.08F );
        Alpha_Crossmember3 = new ModelRenderer( this, 0, 13 );
        Alpha_Crossmember3.setTextureSize( 128, 64 );
        Alpha_Crossmember3.addBox( -21F, -1F, -1F, 42, 2, 2);
        Alpha_Crossmember3.setRotationPoint( 28.18F, 12.5F, -8F );
        Bravo_Crossmember3 = new ModelRenderer( this, 0, 17 );
        Bravo_Crossmember3.setTextureSize( 128, 64 );
        Bravo_Crossmember3.addBox( -12.5F, -1F, -1F, 25, 2, 2);
        Bravo_Crossmember3.setRotationPoint( 20F, -33.5F, -8F );
        Charlie_Crossmember3 = new ModelRenderer( this, 54, 17 );
        Charlie_Crossmember3.setTextureSize( 128, 64 );
        Charlie_Crossmember3.addBox( -6.5F, -1F, -1F, 13, 2, 2);
        Charlie_Crossmember3.setRotationPoint( 13.84F, -68.4F, -8F );
        Delta_Crossmember3 = new ModelRenderer( this, 84, 17 );
        Delta_Crossmember3.setTextureSize( 128, 64 );
        Delta_Crossmember3.addBox( -5F, -1F, -1F, 10, 2, 2);
        Delta_Crossmember3.setRotationPoint( 13.7F, -83.5F, -8F );
        Connecter_Arm_Alpha = new ModelRenderer( this, 0, 34 );
        Connecter_Arm_Alpha.setTextureSize( 128, 64 );
        Connecter_Arm_Alpha.addBox( -1F, -1F, -14F, 2, 2, 28);
        Connecter_Arm_Alpha.setRotationPoint( 13.71F, -68.6F, 12F );
        Connector_Helper_Arm = new ModelRenderer( this, 60, 30 );
        Connector_Helper_Arm.setTextureSize( 128, 64 );
        Connector_Helper_Arm.addBox( -1F, -1F, -16F, 2, 2, 32);
        Connector_Helper_Arm.setRotationPoint( 13.71F, -76.42F, 11.49F );
        Connecter_Arm_Bravo = new ModelRenderer( this, 0, 34 );
        Connecter_Arm_Bravo.setTextureSize( 128, 64 );
        Connecter_Arm_Bravo.addBox( -1F, -1F, -14F, 2, 2, 28);
        Connecter_Arm_Bravo.setRotationPoint( 2.3F, -68.6F, -28F );
        Connector_Helper_Arm1 = new ModelRenderer( this, 60, 30 );
        Connector_Helper_Arm1.setTextureSize( 128, 64 );
        Connector_Helper_Arm1.addBox( -1F, -1F, -16F, 2, 2, 32);
        Connector_Helper_Arm1.setRotationPoint( 2.3F, -76.42F, -27.49F );
        Connecter_Arm_Delta = new ModelRenderer( this, 0, 34 );
        Connecter_Arm_Delta.setTextureSize( 128, 64 );
        Connecter_Arm_Delta.addBox( -1F, -1F, -14F, 2, 2, 28);
        Connecter_Arm_Delta.setRotationPoint( 2.3F, -68.6F, 12F );
        Connector_Helper_Arm2 = new ModelRenderer( this, 60, 30 );
        Connector_Helper_Arm2.setTextureSize( 128, 64 );
        Connector_Helper_Arm2.addBox( -1F, -1F, -16F, 2, 2, 32);
        Connector_Helper_Arm2.setRotationPoint( 2.3F, -76.42F, 11.49F );
        Connecter_Arm_Charlie = new ModelRenderer( this, 0, 34 );
        Connecter_Arm_Charlie.setTextureSize( 128, 64 );
        Connecter_Arm_Charlie.addBox( -1F, -1F, -14F, 2, 2, 28);
        Connecter_Arm_Charlie.setRotationPoint( 13.7F, -68.6F, -28F );
        Connector_Helper_Arm3 = new ModelRenderer( this, 60, 30 );
        Connector_Helper_Arm3.setTextureSize( 128, 64 );
        Connector_Helper_Arm3.addBox( -1F, -1F, -16F, 2, 2, 32);
        Connector_Helper_Arm3.setRotationPoint( 13.7F, -76.42F, -27.49F );
    }

   public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
   {
        Base_part.rotateAngleX = 0F;
        Base_part.rotateAngleY = 0F;
        Base_part.rotateAngleZ = 0F;
        Base_part.renderWithRotation(par7);

        Foot_Plate_Alpha.rotateAngleX = 0F;
        Foot_Plate_Alpha.rotateAngleY = 0F;
        Foot_Plate_Alpha.rotateAngleZ = 0F;
        Foot_Plate_Alpha.renderWithRotation(par7);

        Alpha_Upright.rotateAngleX = -0.1745329F;
        Alpha_Upright.rotateAngleY = 1.337902E-09F;
        Alpha_Upright.rotateAngleZ = 0.1745329F;
        Alpha_Upright.renderWithRotation(par7);

        Bravo_Upright.rotateAngleX = 0F;
        Bravo_Upright.rotateAngleY = 0F;
        Bravo_Upright.rotateAngleZ = 0F;
        Bravo_Upright.renderWithRotation(par7);

        Charlie_Upright.rotateAngleX = -0.7853982F;
        Charlie_Upright.rotateAngleY = 0F;
        Charlie_Upright.rotateAngleZ = 0F;
        Charlie_Upright.renderWithRotation(par7);

        Alpha_Crossmember.rotateAngleX = -0.1745329F;
        Alpha_Crossmember.rotateAngleY = 0F;
        Alpha_Crossmember.rotateAngleZ = 0F;
        Alpha_Crossmember.renderWithRotation(par7);

        Bravo_Crossmember.rotateAngleX = -0.1745329F;
        Bravo_Crossmember.rotateAngleY = 0F;
        Bravo_Crossmember.rotateAngleZ = 0F;
        Bravo_Crossmember.renderWithRotation(par7);

        Charlie_Crossmember.rotateAngleX = -0.1745329F;
        Charlie_Crossmember.rotateAngleY = 0F;
        Charlie_Crossmember.rotateAngleZ = 0F;
        Charlie_Crossmember.renderWithRotation(par7);

        Delta_Crossmember.rotateAngleX = 0F;
        Delta_Crossmember.rotateAngleY = 0F;
        Delta_Crossmember.rotateAngleZ = 0F;
        Delta_Crossmember.renderWithRotation(par7);

        Echo_Crossmember.rotateAngleX = 0.7853982F;
        Echo_Crossmember.rotateAngleY = 0F;
        Echo_Crossmember.rotateAngleZ = 0F;
        Echo_Crossmember.renderWithRotation(par7);

        Foxtrot_Crossmember.rotateAngleX = 0F;
        Foxtrot_Crossmember.rotateAngleY = 0F;
        Foxtrot_Crossmember.rotateAngleZ = 0F;
        Foxtrot_Crossmember.renderWithRotation(par7);

        Connector_Bar.rotateAngleX = 0F;
        Connector_Bar.rotateAngleY = 0F;
        Connector_Bar.rotateAngleZ = 0F;
        Connector_Bar.renderWithRotation(par7);

        Connector_Disk_Alpha.rotateAngleX = 0F;
        Connector_Disk_Alpha.rotateAngleY = 0F;
        Connector_Disk_Alpha.rotateAngleZ = 0F;
        Connector_Disk_Alpha.renderWithRotation(par7);

        Part_Alpha.rotateAngleX = 0F;
        Part_Alpha.rotateAngleY = 0F;
        Part_Alpha.rotateAngleZ = 0F;
        Part_Alpha.renderWithRotation(par7);

        Part_Bravo.rotateAngleX = 0F;
        Part_Bravo.rotateAngleY = -1.570796F;
        Part_Bravo.rotateAngleZ = 0F;
        Part_Bravo.renderWithRotation(par7);

        Part_Charlie.rotateAngleX = 0F;
        Part_Charlie.rotateAngleY = -1.570796F;
        Part_Charlie.rotateAngleZ = 0F;
        Part_Charlie.renderWithRotation(par7);

        Part_Delta.rotateAngleX = 0F;
        Part_Delta.rotateAngleY = 0F;
        Part_Delta.rotateAngleZ = 0F;
        Part_Delta.renderWithRotation(par7);

        Connector_Disk_Bravo.rotateAngleX = 0F;
        Connector_Disk_Bravo.rotateAngleY = 0F;
        Connector_Disk_Bravo.rotateAngleZ = 0F;
        Connector_Disk_Bravo.renderWithRotation(par7);

        Part_Alpha1.rotateAngleX = 0F;
        Part_Alpha1.rotateAngleY = 0F;
        Part_Alpha1.rotateAngleZ = 0F;
        Part_Alpha1.renderWithRotation(par7);

        Part_Bravo1.rotateAngleX = 0F;
        Part_Bravo1.rotateAngleY = -1.570796F;
        Part_Bravo1.rotateAngleZ = 0F;
        Part_Bravo1.renderWithRotation(par7);

        Part_Charlie1.rotateAngleX = 0F;
        Part_Charlie1.rotateAngleY = -1.570796F;
        Part_Charlie1.rotateAngleZ = 0F;
        Part_Charlie1.renderWithRotation(par7);

        Part_Delta1.rotateAngleX = 0F;
        Part_Delta1.rotateAngleY = 0F;
        Part_Delta1.rotateAngleZ = 0F;
        Part_Delta1.renderWithRotation(par7);

        Connector_Disk_Charlie.rotateAngleX = 0F;
        Connector_Disk_Charlie.rotateAngleY = 0F;
        Connector_Disk_Charlie.rotateAngleZ = 0F;
        Connector_Disk_Charlie.renderWithRotation(par7);

        Part_Alpha2.rotateAngleX = 0F;
        Part_Alpha2.rotateAngleY = 0F;
        Part_Alpha2.rotateAngleZ = 0F;
        Part_Alpha2.renderWithRotation(par7);

        Part_Bravo2.rotateAngleX = 0F;
        Part_Bravo2.rotateAngleY = -1.570796F;
        Part_Bravo2.rotateAngleZ = 0F;
        Part_Bravo2.renderWithRotation(par7);

        Part_Charlie2.rotateAngleX = 0F;
        Part_Charlie2.rotateAngleY = -1.570796F;
        Part_Charlie2.rotateAngleZ = 0F;
        Part_Charlie2.renderWithRotation(par7);

        Part_Delta2.rotateAngleX = 0F;
        Part_Delta2.rotateAngleY = 0F;
        Part_Delta2.rotateAngleZ = 0F;
        Part_Delta2.renderWithRotation(par7);

        Connector_Disk_Delta.rotateAngleX = 0F;
        Connector_Disk_Delta.rotateAngleY = 0F;
        Connector_Disk_Delta.rotateAngleZ = 0F;
        Connector_Disk_Delta.renderWithRotation(par7);

        Part_Alpha3.rotateAngleX = 0F;
        Part_Alpha3.rotateAngleY = 0F;
        Part_Alpha3.rotateAngleZ = 0F;
        Part_Alpha3.renderWithRotation(par7);

        Part_Bravo3.rotateAngleX = 0F;
        Part_Bravo3.rotateAngleY = -1.570796F;
        Part_Bravo3.rotateAngleZ = 0F;
        Part_Bravo3.renderWithRotation(par7);

        Part_Charlie3.rotateAngleX = 0F;
        Part_Charlie3.rotateAngleY = -1.570796F;
        Part_Charlie3.rotateAngleZ = 0F;
        Part_Charlie3.renderWithRotation(par7);

        Part_Delta3.rotateAngleX = 0F;
        Part_Delta3.rotateAngleY = 0F;
        Part_Delta3.rotateAngleZ = 0F;
        Part_Delta3.renderWithRotation(par7);

        Connector_Disk_Echo.rotateAngleX = 0F;
        Connector_Disk_Echo.rotateAngleY = 0F;
        Connector_Disk_Echo.rotateAngleZ = 0F;
        Connector_Disk_Echo.renderWithRotation(par7);

        Part_Alpha4.rotateAngleX = 0F;
        Part_Alpha4.rotateAngleY = 0F;
        Part_Alpha4.rotateAngleZ = 0F;
        Part_Alpha4.renderWithRotation(par7);

        Part_Bravo4.rotateAngleX = 0F;
        Part_Bravo4.rotateAngleY = -1.570796F;
        Part_Bravo4.rotateAngleZ = 0F;
        Part_Bravo4.renderWithRotation(par7);

        Part_Charlie4.rotateAngleX = 0F;
        Part_Charlie4.rotateAngleY = -1.570796F;
        Part_Charlie4.rotateAngleZ = 0F;
        Part_Charlie4.renderWithRotation(par7);

        Part_Delta4.rotateAngleX = 0F;
        Part_Delta4.rotateAngleY = 0F;
        Part_Delta4.rotateAngleZ = 0F;
        Part_Delta4.renderWithRotation(par7);

        Foot_Plate_Bravo.rotateAngleX = 0F;
        Foot_Plate_Bravo.rotateAngleY = 0F;
        Foot_Plate_Bravo.rotateAngleZ = 0F;
        Foot_Plate_Bravo.renderWithRotation(par7);

        Alpha_Upright1.rotateAngleX = 0.1745329F;
        Alpha_Upright1.rotateAngleY = 1.270566E-09F;
        Alpha_Upright1.rotateAngleZ = 0.1745329F;
        Alpha_Upright1.renderWithRotation(par7);

        Bravo_Upright1.rotateAngleX = 0F;
        Bravo_Upright1.rotateAngleY = 0F;
        Bravo_Upright1.rotateAngleZ = 0F;
        Bravo_Upright1.renderWithRotation(par7);

        Charlie_Upright1.rotateAngleX = -0.7853982F;
        Charlie_Upright1.rotateAngleY = 0F;
        Charlie_Upright1.rotateAngleZ = 0F;
        Charlie_Upright1.renderWithRotation(par7);

        Alpha_Crossmember1.rotateAngleX = 0.1745329F;
        Alpha_Crossmember1.rotateAngleY = -1.570796F;
        Alpha_Crossmember1.rotateAngleZ = 0F;
        Alpha_Crossmember1.renderWithRotation(par7);

        Bravo_Crossmember1.rotateAngleX = 0.1745329F;
        Bravo_Crossmember1.rotateAngleY = -1.570796F;
        Bravo_Crossmember1.rotateAngleZ = 0F;
        Bravo_Crossmember1.renderWithRotation(par7);

        Charlie_Crossmember1.rotateAngleX = 0.1745329F;
        Charlie_Crossmember1.rotateAngleY = -1.570796F;
        Charlie_Crossmember1.rotateAngleZ = 0F;
        Charlie_Crossmember1.renderWithRotation(par7);

        Delta_Crossmember1.rotateAngleX = 0F;
        Delta_Crossmember1.rotateAngleY = -1.570796F;
        Delta_Crossmember1.rotateAngleZ = 0F;
        Delta_Crossmember1.renderWithRotation(par7);

        Foot_Plate_Charlie.rotateAngleX = 0F;
        Foot_Plate_Charlie.rotateAngleY = 0F;
        Foot_Plate_Charlie.rotateAngleZ = 0F;
        Foot_Plate_Charlie.renderWithRotation(par7);

        Alpha_Upright2.rotateAngleX = 0.1745329F;
        Alpha_Upright2.rotateAngleY = 1.337902E-09F;
        Alpha_Upright2.rotateAngleZ = -0.1745329F;
        Alpha_Upright2.renderWithRotation(par7);

        Bravo_Upright2.rotateAngleX = 0F;
        Bravo_Upright2.rotateAngleY = 0F;
        Bravo_Upright2.rotateAngleZ = 0F;
        Bravo_Upright2.renderWithRotation(par7);

        Charlie_Upright2.rotateAngleX = 0.7853982F;
        Charlie_Upright2.rotateAngleY = 0F;
        Charlie_Upright2.rotateAngleZ = 0F;
        Charlie_Upright2.renderWithRotation(par7);

        Alpha_Crossmember2.rotateAngleX = 0.1745329F;
        Alpha_Crossmember2.rotateAngleY = 0F;
        Alpha_Crossmember2.rotateAngleZ = 0F;
        Alpha_Crossmember2.renderWithRotation(par7);

        Bravo_Crossmember2.rotateAngleX = 0.1745329F;
        Bravo_Crossmember2.rotateAngleY = 0F;
        Bravo_Crossmember2.rotateAngleZ = 0F;
        Bravo_Crossmember2.renderWithRotation(par7);

        Charlie_Crossmember2.rotateAngleX = 0.1745329F;
        Charlie_Crossmember2.rotateAngleY = 0F;
        Charlie_Crossmember2.rotateAngleZ = 0F;
        Charlie_Crossmember2.renderWithRotation(par7);

        Delta_Crossmember2.rotateAngleX = 0F;
        Delta_Crossmember2.rotateAngleY = 0F;
        Delta_Crossmember2.rotateAngleZ = 0F;
        Delta_Crossmember2.renderWithRotation(par7);

        Echo_Crossmember1.rotateAngleX = 0.7853982F;
        Echo_Crossmember1.rotateAngleY = 0F;
        Echo_Crossmember1.rotateAngleZ = 0F;
        Echo_Crossmember1.renderWithRotation(par7);

        Foxtrot_Crossmember1.rotateAngleX = 0F;
        Foxtrot_Crossmember1.rotateAngleY = 0F;
        Foxtrot_Crossmember1.rotateAngleZ = 0F;
        Foxtrot_Crossmember1.renderWithRotation(par7);

        Connector_Bar1.rotateAngleX = 0F;
        Connector_Bar1.rotateAngleY = 0F;
        Connector_Bar1.rotateAngleZ = 0F;
        Connector_Bar1.renderWithRotation(par7);

        Connector_Disk_Alpha1.rotateAngleX = 0F;
        Connector_Disk_Alpha1.rotateAngleY = 0F;
        Connector_Disk_Alpha1.rotateAngleZ = 0F;
        Connector_Disk_Alpha1.renderWithRotation(par7);

        Part_Alpha5.rotateAngleX = 0F;
        Part_Alpha5.rotateAngleY = 0F;
        Part_Alpha5.rotateAngleZ = 0F;
        Part_Alpha5.renderWithRotation(par7);

        Part_Bravo5.rotateAngleX = 0F;
        Part_Bravo5.rotateAngleY = -1.570796F;
        Part_Bravo5.rotateAngleZ = 0F;
        Part_Bravo5.renderWithRotation(par7);

        Part_Charlie5.rotateAngleX = 0F;
        Part_Charlie5.rotateAngleY = -1.570796F;
        Part_Charlie5.rotateAngleZ = 0F;
        Part_Charlie5.renderWithRotation(par7);

        Part_Delta5.rotateAngleX = 0F;
        Part_Delta5.rotateAngleY = 0F;
        Part_Delta5.rotateAngleZ = 0F;
        Part_Delta5.renderWithRotation(par7);

        Connector_Disk_Bravo1.rotateAngleX = 0F;
        Connector_Disk_Bravo1.rotateAngleY = 0F;
        Connector_Disk_Bravo1.rotateAngleZ = 0F;
        Connector_Disk_Bravo1.renderWithRotation(par7);

        Part_Alpha6.rotateAngleX = 0F;
        Part_Alpha6.rotateAngleY = 0F;
        Part_Alpha6.rotateAngleZ = 0F;
        Part_Alpha6.renderWithRotation(par7);

        Part_Bravo6.rotateAngleX = 0F;
        Part_Bravo6.rotateAngleY = -1.570796F;
        Part_Bravo6.rotateAngleZ = 0F;
        Part_Bravo6.renderWithRotation(par7);

        Part_Charlie6.rotateAngleX = 0F;
        Part_Charlie6.rotateAngleY = -1.570796F;
        Part_Charlie6.rotateAngleZ = 0F;
        Part_Charlie6.renderWithRotation(par7);

        Part_Delta6.rotateAngleX = 0F;
        Part_Delta6.rotateAngleY = 0F;
        Part_Delta6.rotateAngleZ = 0F;
        Part_Delta6.renderWithRotation(par7);

        Connector_Disk_Charlie1.rotateAngleX = 0F;
        Connector_Disk_Charlie1.rotateAngleY = 0F;
        Connector_Disk_Charlie1.rotateAngleZ = 0F;
        Connector_Disk_Charlie1.renderWithRotation(par7);

        Part_Alpha7.rotateAngleX = 0F;
        Part_Alpha7.rotateAngleY = 0F;
        Part_Alpha7.rotateAngleZ = 0F;
        Part_Alpha7.renderWithRotation(par7);

        Part_Bravo7.rotateAngleX = 0F;
        Part_Bravo7.rotateAngleY = -1.570796F;
        Part_Bravo7.rotateAngleZ = 0F;
        Part_Bravo7.renderWithRotation(par7);

        Part_Charlie7.rotateAngleX = 0F;
        Part_Charlie7.rotateAngleY = -1.570796F;
        Part_Charlie7.rotateAngleZ = 0F;
        Part_Charlie7.renderWithRotation(par7);

        Part_Delta7.rotateAngleX = 0F;
        Part_Delta7.rotateAngleY = 0F;
        Part_Delta7.rotateAngleZ = 0F;
        Part_Delta7.renderWithRotation(par7);

        Connector_Disk_Delta1.rotateAngleX = 0F;
        Connector_Disk_Delta1.rotateAngleY = 0F;
        Connector_Disk_Delta1.rotateAngleZ = 0F;
        Connector_Disk_Delta1.renderWithRotation(par7);

        Part_Alpha8.rotateAngleX = 0F;
        Part_Alpha8.rotateAngleY = 0F;
        Part_Alpha8.rotateAngleZ = 0F;
        Part_Alpha8.renderWithRotation(par7);

        Part_Bravo8.rotateAngleX = 0F;
        Part_Bravo8.rotateAngleY = -1.570796F;
        Part_Bravo8.rotateAngleZ = 0F;
        Part_Bravo8.renderWithRotation(par7);

        Part_Charlie8.rotateAngleX = 0F;
        Part_Charlie8.rotateAngleY = -1.570796F;
        Part_Charlie8.rotateAngleZ = 0F;
        Part_Charlie8.renderWithRotation(par7);

        Part_Delta8.rotateAngleX = 0F;
        Part_Delta8.rotateAngleY = 0F;
        Part_Delta8.rotateAngleZ = 0F;
        Part_Delta8.renderWithRotation(par7);

        Connector_Disk_Echo1.rotateAngleX = 0F;
        Connector_Disk_Echo1.rotateAngleY = 0F;
        Connector_Disk_Echo1.rotateAngleZ = 0F;
        Connector_Disk_Echo1.renderWithRotation(par7);

        Part_Alpha9.rotateAngleX = 0F;
        Part_Alpha9.rotateAngleY = 0F;
        Part_Alpha9.rotateAngleZ = 0F;
        Part_Alpha9.renderWithRotation(par7);

        Part_Bravo9.rotateAngleX = 0F;
        Part_Bravo9.rotateAngleY = -1.570796F;
        Part_Bravo9.rotateAngleZ = 0F;
        Part_Bravo9.renderWithRotation(par7);

        Part_Charlie9.rotateAngleX = 0F;
        Part_Charlie9.rotateAngleY = -1.570796F;
        Part_Charlie9.rotateAngleZ = 0F;
        Part_Charlie9.renderWithRotation(par7);

        Part_Delta9.rotateAngleX = 0F;
        Part_Delta9.rotateAngleY = 0F;
        Part_Delta9.rotateAngleZ = 0F;
        Part_Delta9.renderWithRotation(par7);

        Foot_Plate_Delta.rotateAngleX = 0F;
        Foot_Plate_Delta.rotateAngleY = 0F;
        Foot_Plate_Delta.rotateAngleZ = 0F;
        Foot_Plate_Delta.renderWithRotation(par7);

        Alpha_Upright3.rotateAngleX = -0.1745329F;
        Alpha_Upright3.rotateAngleY = 1.270566E-09F;
        Alpha_Upright3.rotateAngleZ = -0.1745329F;
        Alpha_Upright3.renderWithRotation(par7);

        Bravo_Upright3.rotateAngleX = 0F;
        Bravo_Upright3.rotateAngleY = 0F;
        Bravo_Upright3.rotateAngleZ = 0F;
        Bravo_Upright3.renderWithRotation(par7);

        Charlie_Upright3.rotateAngleX = 0.7853982F;
        Charlie_Upright3.rotateAngleY = 0F;
        Charlie_Upright3.rotateAngleZ = 0F;
        Charlie_Upright3.renderWithRotation(par7);

        Alpha_Crossmember3.rotateAngleX = -0.1745329F;
        Alpha_Crossmember3.rotateAngleY = -1.570796F;
        Alpha_Crossmember3.rotateAngleZ = 0F;
        Alpha_Crossmember3.renderWithRotation(par7);

        Bravo_Crossmember3.rotateAngleX = -0.1745329F;
        Bravo_Crossmember3.rotateAngleY = -1.570796F;
        Bravo_Crossmember3.rotateAngleZ = 0F;
        Bravo_Crossmember3.renderWithRotation(par7);

        Charlie_Crossmember3.rotateAngleX = -0.1745329F;
        Charlie_Crossmember3.rotateAngleY = -1.570796F;
        Charlie_Crossmember3.rotateAngleZ = 0F;
        Charlie_Crossmember3.renderWithRotation(par7);

        Delta_Crossmember3.rotateAngleX = 0F;
        Delta_Crossmember3.rotateAngleY = -1.570796F;
        Delta_Crossmember3.rotateAngleZ = 0F;
        Delta_Crossmember3.renderWithRotation(par7);

        Connecter_Arm_Alpha.rotateAngleX = 0F;
        Connecter_Arm_Alpha.rotateAngleY = 0F;
        Connecter_Arm_Alpha.rotateAngleZ = 0F;
        Connecter_Arm_Alpha.renderWithRotation(par7);

        Connector_Helper_Arm.rotateAngleX = -0.5012586F;
        Connector_Helper_Arm.rotateAngleY = 0F;
        Connector_Helper_Arm.rotateAngleZ = 0F;
        Connector_Helper_Arm.renderWithRotation(par7);

        Connecter_Arm_Bravo.rotateAngleX = 0F;
        Connecter_Arm_Bravo.rotateAngleY = 0F;
        Connecter_Arm_Bravo.rotateAngleZ = 0F;
        Connecter_Arm_Bravo.renderWithRotation(par7);

        Connector_Helper_Arm1.rotateAngleX = 0.5012586F;
        Connector_Helper_Arm1.rotateAngleY = 0F;
        Connector_Helper_Arm1.rotateAngleZ = 0F;
        Connector_Helper_Arm1.renderWithRotation(par7);

        Connecter_Arm_Delta.rotateAngleX = 0F;
        Connecter_Arm_Delta.rotateAngleY = 0F;
        Connecter_Arm_Delta.rotateAngleZ = 0F;
        Connecter_Arm_Delta.renderWithRotation(par7);

        Connector_Helper_Arm2.rotateAngleX = -0.5012586F;
        Connector_Helper_Arm2.rotateAngleY = 0F;
        Connector_Helper_Arm2.rotateAngleZ = 0F;
        Connector_Helper_Arm2.renderWithRotation(par7);

        Connecter_Arm_Charlie.rotateAngleX = 0F;
        Connecter_Arm_Charlie.rotateAngleY = 0F;
        Connecter_Arm_Charlie.rotateAngleZ = 0F;
        Connecter_Arm_Charlie.renderWithRotation(par7);

        Connector_Helper_Arm3.rotateAngleX = 0.5012586F;
        Connector_Helper_Arm3.rotateAngleY = 0F;
        Connector_Helper_Arm3.rotateAngleZ = 0F;
        Connector_Helper_Arm3.renderWithRotation(par7);

    }

}
