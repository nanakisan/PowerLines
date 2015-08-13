package untouchedwagons.minecraft.powerlines.extra;

import net.minecraft.entity.EntityLivingBase;

public interface IBoundingBlock {
    void onPlace(EntityLivingBase entity);

    void onBreak();
}
