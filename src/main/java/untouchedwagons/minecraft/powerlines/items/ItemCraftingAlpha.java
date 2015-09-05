package untouchedwagons.minecraft.powerlines.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemCraftingAlpha extends Item {
    public static final int IRON_BASE_PLATE_DAMAGE = 0;
    public static final int DISSIPATION_GRID_DAMAGE = 1;
    public static final int DISSIPATION_PANEL_DAMAGE = 2;
    public static final int CONNECTOR_DAMAGE = 3;
    public static final int CERAMIC_DISK_DAMAGE = 4;
    public static final int UNFIRED_CERAMIC_DISK_DAMAGE = 5;
    public static final int LONG_IRON_BAR_DAMAGE = 6;
    public static final int SMALL_IRON_BASE_PLATE_DAMAGE = 7;

    public ItemCraftingAlpha() {
        this.setUnlocalizedName("crafting-alpha");
        this.setTextureName("powerlines:crafting-alpha");
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
        item_list.add(new ItemStack(item, 1, IRON_BASE_PLATE_DAMAGE));
        item_list.add(new ItemStack(item, 1, DISSIPATION_GRID_DAMAGE));
        item_list.add(new ItemStack(item, 1, DISSIPATION_PANEL_DAMAGE));
        item_list.add(new ItemStack(item, 1, CONNECTOR_DAMAGE));
        item_list.add(new ItemStack(item, 1, CERAMIC_DISK_DAMAGE));
        item_list.add(new ItemStack(item, 1, UNFIRED_CERAMIC_DISK_DAMAGE));
        item_list.add(new ItemStack(item, 1, LONG_IRON_BAR_DAMAGE));
        item_list.add(new ItemStack(item, 1, SMALL_IRON_BASE_PLATE_DAMAGE));
    }
}
