package untouchedwagons.minecraft.powerlines.render.items;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import untouchedwagons.minecraft.powerlines.models.ModelSubStation;

public class SubStationItemRenderer implements IItemRenderer {
    private final ModelSubStation model = new ModelSubStation();
    private final ResourceLocation texture = new ResourceLocation("powerlines", "render/subStation.png");

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

        if (type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON)
        {
            GL11.glTranslatef(0.75F, 0.4F, 0.75F);
        }

        GL11.glRotatef(180, 0F, 0F, 1F);
        GL11.glScalef(0.5F, 0.5F, 0.5F);

        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        model.render(null, 0, 0, 0, 0, 0, 0.0625F);

        GL11.glPopMatrix();
    }
}
