package untouchedwagons.minecraft.powerlines.render.items;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class MediumPowerLineItemRenderer implements IItemRenderer {
    private final ResourceLocation texture;
    private final ResourceLocation obj_model;
    private final IModelCustom model;

    public MediumPowerLineItemRenderer() {
        texture = new ResourceLocation("powerlines", "render/mediumPowerLine.png");
        obj_model = new ResourceLocation("powerlines", "models/MediumPowerLine.obj");
        model = AdvancedModelLoader.loadModel(obj_model);
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return false;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        GL11.glPushMatrix();

        if (type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON)
        {
            GL11.glTranslatef(0.75F, 0F, 0.75F);
        } else if (type == ItemRenderType.INVENTORY)
        {
            GL11.glTranslatef(0.75F, 0.125F, 0.75F);
        }

        GL11.glRotatef(180, 0F, 0F, 1F);
        GL11.glScalef(0.20F, 0.20F, 0.20F);

        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        model.renderAll();

        GL11.glPopMatrix();
    }
}
