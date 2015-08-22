package untouchedwagons.minecraft.powerlines.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.grids.PowerGrid;
import untouchedwagons.minecraft.powerlines.grids.PowerGridNode;
import untouchedwagons.minecraft.powerlines.grids.PowerGridWorldSavedData;

import java.util.List;

public class ItemDebuggingStick extends Item {
    public ItemDebuggingStick() {
        this.setUnlocalizedName("debugging-stick");
        this.setTextureName("stick");
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.tabMisc);
    }

    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack stack, EntityPlayer player, List tooltip_list, boolean p_77624_4_)
    {
        tooltip_list.add("Sneak+Right click to view server-side power grid data");
        tooltip_list.add("Right click to view client-side power grid data");
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
        if (player.isSneaking() && world.isRemote)
        {
            return false;
        }

        if (!player.isSneaking() && !world.isRemote)
        {
            return false;
        }

        if (world.isRemote)
            player.addChatComponentMessage(new ChatComponentText(String.format("Showing Grid data for %sClient%s side", EnumChatFormatting.GREEN, EnumChatFormatting.RESET)));
        else
            player.addChatComponentMessage(new ChatComponentText(String.format("Showing Grid data for %sServer%s side", EnumChatFormatting.RED, EnumChatFormatting.RESET)));

        PowerGridWorldSavedData pgwsd = PowerGridWorldSavedData.get(world);

        player.addChatComponentMessage(new ChatComponentText(String.format("Grid count: %d", pgwsd.getGrids().size())));

        for (PowerGrid grid : pgwsd.getGrids())
        {
            player.addChatComponentMessage(new ChatComponentText(String.format("-Grid UUID: %s", grid.getGridUUID().toString())));

            player.addChatComponentMessage(new ChatComponentText(String.format("-Node count: %d", grid.getNodes().size())));

            for (PowerGridNode node : grid.getNodes())
            {
                player.addChatComponentMessage(new ChatComponentText(String.format("--Node UUID: %s", node.getNodeUUID())));
                player.addChatComponentMessage(new ChatComponentText(String.format("--Node coords: %d, %d, %d", node.getX(), node.getY(), node.getZ())));
                player.addChatComponentMessage(new ChatComponentText(String.format("--Node neighbour count: %d", node.getNeighbours().size())));

                for (PowerGridNode neighbour : node.getNeighbours())
                {
                    player.addChatComponentMessage(new ChatComponentText(String.format("---Neighbour UUID: %s", neighbour.getNodeUUID())));
                    player.addChatComponentMessage(new ChatComponentText(String.format("---Neighbour coords: %d, %d, %d", neighbour.getX(), neighbour.getY(), neighbour.getZ())));
                }
            }
        }

        return false;
    }
}
