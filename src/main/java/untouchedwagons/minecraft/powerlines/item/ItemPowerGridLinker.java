package untouchedwagons.minecraft.powerlines.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class ItemPowerGridLinker extends Item {
    public ItemPowerGridLinker() {
        this.setUnlocalizedName("grid-linker");
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.tabMisc);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List tooltip_list, boolean p_77624_4_) {
        // Put mini-tutorial information here
    }

    @Override
    public boolean doesSneakBypassUse(World world, int x, int y, int z, EntityPlayer player) {
        // Not sure if this is needed
        return super.doesSneakBypassUse(world, x, y, z, player);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        if (!world.isRemote)
        {
            /* If the block the linker is used on is a TileEntityPowerGridNode and the player is sneaking, save the grid ID
             * If the block the linker is used on is a TileEntityPowerGridNode and the player is not sneaking, set the grid ID, and connect the grid
             */
        }

        return super.onItemUse(stack, player, world, x, y, z, side, p_77648_8_, p_77648_9_, p_77648_10_);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (world.isRemote)
            return stack;

        if (player.isSneaking())
        {
            // Clear the saved data a la AE2's Memory Card
        }

        return super.onItemRightClick(stack, world, player);
    }
}
