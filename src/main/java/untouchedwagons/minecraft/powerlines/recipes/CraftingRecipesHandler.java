package untouchedwagons.minecraft.powerlines.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import untouchedwagons.minecraft.powerlines.PowerLinesMod;
import untouchedwagons.minecraft.powerlines.items.ItemCraftingAlpha;
import untouchedwagons.minecraft.powerlines.items.ItemCraftingBravo;

public class CraftingRecipesHandler {
    private CraftingRecipesHandler()
    {

    }

    public static void registerRecipes()
    {
        CraftingManager.getInstance().addRecipe(
                new ItemStack(PowerLinesMod.items.craftingAlpha, 1, ItemCraftingAlpha.DISSIPATION_PANEL_DAMAGE),
                "xyx",
                'x',
                new ItemStack(Blocks.iron_bars),
                'y',
                new ItemStack(Items.iron_ingot)
        );

        CraftingManager.getInstance().addRecipe(
                new ItemStack(PowerLinesMod.items.craftingAlpha, 1, ItemCraftingAlpha.DISSIPATION_GRID_DAMAGE),
                "xxx",
                "xxx",
                "xxx",
                'x',
                new ItemStack(PowerLinesMod.items.craftingAlpha, 1, ItemCraftingAlpha.DISSIPATION_PANEL_DAMAGE)
        );

        CraftingManager.getInstance().addRecipe(
                new ItemStack(PowerLinesMod.items.craftingAlpha, 8, ItemCraftingAlpha.UNFIRED_CERAMIC_DISK_DAMAGE),
                "xxx",
                "xyx",
                "xxx",
                'x',
                new ItemStack(Items.clay_ball),
                'y',
                new ItemStack(Items.water_bucket)
        );

        CraftingManager.getInstance().addRecipe(
                new ItemStack(PowerLinesMod.items.craftingAlpha, 1, ItemCraftingAlpha.CONNECTOR_DAMAGE),
                " y ",
                " x ",
                " x ",
                'y',
                new ItemStack(Items.redstone),
                'x',
                new ItemStack(PowerLinesMod.items.craftingAlpha, 1, ItemCraftingAlpha.CERAMIC_DISK_DAMAGE)
        );

        CraftingManager.getInstance().addRecipe(
                new ItemStack(PowerLinesMod.blocks.subStation),
                "vvv",
                "xyx",
                "zzz",
                'v',
                new ItemStack(PowerLinesMod.items.craftingAlpha, 1, ItemCraftingAlpha.CONNECTOR_DAMAGE),
                'x',
                new ItemStack(PowerLinesMod.items.craftingAlpha, 1, ItemCraftingAlpha.DISSIPATION_GRID_DAMAGE),
                'y',
                new ItemStack(Blocks.iron_block),
                'z',
                new ItemStack(PowerLinesMod.items.craftingAlpha, 1, ItemCraftingAlpha.IRON_BASE_PLATE_DAMAGE)
        );

        CraftingManager.getInstance().addRecipe(
                new ItemStack(PowerLinesMod.items.powerGridLinker),
                "vxy",
                "zzz",
                'v',
                new ItemStack(Items.redstone),
                'x',
                new ItemStack(Items.writable_book),
                'y',
                new ItemStack(Items.gold_ingot),
                'z',
                new ItemStack(Items.iron_ingot)
        );

        CraftingManager.getInstance().addRecipe(
                new ItemStack(PowerLinesMod.items.craftingAlpha, 6, ItemCraftingAlpha.LONG_IRON_BAR_DAMAGE),
                "  x",
                " x ",
                "x  ",
                'x',
                new ItemStack(Items.iron_ingot)
        );

        CraftingManager.getInstance().addRecipe(
                new ItemStack(PowerLinesMod.items.craftingBravo, 1, ItemCraftingBravo.LARGE_POWERLINE_LEG_DAMAGE),
                "  y",
                " y ",
                "x  ",
                'x',
                new ItemStack(PowerLinesMod.items.craftingBravo, 1, ItemCraftingBravo.LARGE_POWERLINE_LEG_DAMAGE),
                'y',
                new ItemStack(PowerLinesMod.items.craftingAlpha, 1, ItemCraftingAlpha.LONG_IRON_BAR_DAMAGE)
        );

        CraftingManager.getInstance().addRecipe(
                new ItemStack(PowerLinesMod.items.craftingBravo, 1, ItemCraftingBravo.LARGE_POWERLINE_BODY_DAMAGE),
                "x x",
                "xxx",
                "x x",
                'x',
                new ItemStack(PowerLinesMod.items.craftingAlpha, 1, ItemCraftingAlpha.LONG_IRON_BAR_DAMAGE)
        );

        CraftingManager.getInstance().addRecipe(
                new ItemStack(PowerLinesMod.items.craftingBravo, 1, ItemCraftingBravo.LARGE_POWERLINE_ARM_DAMAGE),
                "  x",
                " x ",
                "xxx",
                'x',
                new ItemStack(PowerLinesMod.items.craftingAlpha, 1, ItemCraftingAlpha.LONG_IRON_BAR_DAMAGE)
        );

        CraftingManager.getInstance().addRecipe(
                new ItemStack(PowerLinesMod.items.craftingBravo, 1, ItemCraftingBravo.LARGE_POWERLINE_CAP_DAMAGE),
                " x ",
                "x x",
                'x',
                new ItemStack(PowerLinesMod.items.craftingAlpha, 1, ItemCraftingAlpha.LONG_IRON_BAR_DAMAGE)
        );

        CraftingManager.getInstance().addRecipe(
                new ItemStack(PowerLinesMod.blocks.largePowerLine),
                "uvu",
                "wxw",
                "y y",
                'u',
                new ItemStack(PowerLinesMod.items.craftingBravo, 1, ItemCraftingBravo.LARGE_POWERLINE_ARM_DAMAGE),
                'v',
                new ItemStack(PowerLinesMod.items.craftingBravo, 1, ItemCraftingBravo.LARGE_POWERLINE_CAP_DAMAGE),
                'w',
                new ItemStack(PowerLinesMod.items.craftingAlpha, 1, ItemCraftingAlpha.CONNECTOR_DAMAGE),
                'x',
                new ItemStack(PowerLinesMod.items.craftingBravo, 1, ItemCraftingBravo.LARGE_POWERLINE_BODY_DAMAGE),
                'y',
                new ItemStack(PowerLinesMod.items.craftingBravo, 1, ItemCraftingBravo.LARGE_POWERLINE_LEG_DAMAGE)
        );
    }
}
