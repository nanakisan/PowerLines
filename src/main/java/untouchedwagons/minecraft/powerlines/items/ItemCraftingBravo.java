package untouchedwagons.minecraft.powerlines.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public class ItemCraftingBravo extends Item {
    public static final int LARGE_POWERLINE_LEG_DAMAGE = 0;
    public static final int LARGE_POWERLINE_BODY_DAMAGE = 1;
    public static final int LARGE_POWERLINE_ARM_DAMAGE = 2;
    public static final int LARGE_POWERLINE_CAP_DAMAGE = 3;
    private IIcon[] icons;

    public ItemCraftingBravo() {
        this.setUnlocalizedName("crafting-bravo");
        this.setHasSubtypes(true);
        this.setCreativeTab(CreativeTabs.tabMisc);
    }

    @Override
    public String getUnlocalizedName(ItemStack p_77667_1_) {
        return String.format("%s.%d", super.getUnlocalizedName(p_77667_1_), p_77667_1_.getItemDamage());
    }

    @SuppressWarnings("unchecked")
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List item_list) {
        item_list.add(new ItemStack(item, 1, LARGE_POWERLINE_LEG_DAMAGE));
        item_list.add(new ItemStack(item, 1, LARGE_POWERLINE_BODY_DAMAGE));
        item_list.add(new ItemStack(item, 1, LARGE_POWERLINE_ARM_DAMAGE));
        item_list.add(new ItemStack(item, 1, LARGE_POWERLINE_CAP_DAMAGE));
    }

    @Override
    public IIcon getIconFromDamage(int meta) {
        return this.icons[meta];
    }

    @Override
    public void registerIcons(IIconRegister icon_registrar) {
        this.icons = new IIcon[4];

        this.icons[LARGE_POWERLINE_LEG_DAMAGE] = icon_registrar.registerIcon("powerlines:large-powerline-leg");
        this.icons[LARGE_POWERLINE_BODY_DAMAGE] = icon_registrar.registerIcon("powerlines:large-powerline-body");
        this.icons[LARGE_POWERLINE_ARM_DAMAGE] = icon_registrar.registerIcon("powerlines:large-powerline-arm");
        this.icons[LARGE_POWERLINE_CAP_DAMAGE] = icon_registrar.registerIcon("powerlines:large-powerline-cap");
    }
}
