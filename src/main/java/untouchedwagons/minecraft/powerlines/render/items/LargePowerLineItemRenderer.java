package untouchedwagons.minecraft.powerlines.render.items;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import untouchedwagons.minecraft.powerlines.models.ModelLargePowerLine;

public class LargePowerLineItemRenderer implements IItemRenderer {
    private final ModelLargePowerLine model = new ModelLargePowerLine();
    private final ResourceLocation texture = new ResourceLocation("powerlines", "render/largePowerLine.png");

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        GL11.glPushMatrix();
        GL11.glTranslatef(0, 0, 0);
        GL11.glRotatef(180, 0F, 0F, 1F);
        GL11.glScalef(0.5F, 0.5F, 0.5F);

        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        model.render(null, 0, 0, 0, 0, 0, 0.0625F);

        GL11.glPopMatrix();
    }
}
