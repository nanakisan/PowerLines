package untouchedwagons.minecraft.powerlines.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import untouchedwagons.minecraft.powerlines.PowerLinesMod;
import untouchedwagons.minecraft.powerlines.items.ItemCraftingAlpha;

public class FurnaceRecipesHandler {
    private FurnaceRecipesHandler()
    {

    }

    public static void registerRecipes()
    {
        FurnaceRecipes.smelting().func_151393_a(
                Blocks.iron_block,
                new ItemStack(PowerLinesMod.items.craftingAlpha, 1, ItemCraftingAlpha.IRON_BASE_PLATE_DAMAGE),
                0
        );

        FurnaceRecipes.smelting().func_151394_a(
                new ItemStack(PowerLinesMod.items.craftingAlpha, 1, ItemCraftingAlpha.UNFIRED_CERAMIC_DISK_DAMAGE),
                new ItemStack(PowerLinesMod.items.craftingAlpha, 1, ItemCraftingAlpha.CERAMIC_DISK_DAMAGE),
                0
        );

        FurnaceRecipes.smelting().func_151394_a(
                new ItemStack(Blocks.light_weighted_pressure_plate),
                new ItemStack(PowerLinesMod.items.craftingAlpha, 1, ItemCraftingAlpha.SMALL_IRON_BASE_PLATE_DAMAGE),
                0
        );
    }
}
