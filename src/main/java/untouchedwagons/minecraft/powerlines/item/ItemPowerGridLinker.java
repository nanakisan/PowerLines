package untouchedwagons.minecraft.powerlines.item;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.blocks.BlockPowerLine;
import untouchedwagons.minecraft.powerlines.grids.PowerGrid;
import untouchedwagons.minecraft.powerlines.grids.PowerGridNode;
import untouchedwagons.minecraft.powerlines.grids.PowerGridWorldSavedData;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityPowerGridNode;

import java.util.List;
import java.util.UUID;

public class ItemPowerGridLinker extends Item {
    public ItemPowerGridLinker() {
        this.setUnlocalizedName("grid-linker");
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.tabMisc);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List tooltip_list, boolean p_77624_4_) {
        if (stack.getTagCompound() != null && stack.getTagCompound().hasKey("grid-uuid"))
        {
            tooltip_list.add(StatCollector.translateToLocal("text.grid-linker-link-ready"));
            tooltip_list.add(String.format("%s: %s%s%s",
                            StatCollector.translateToLocal("text.grid"),
                            EnumChatFormatting.AQUA,
                            stack.getTagCompound().getString("grid-uuid"),
                            EnumChatFormatting.RESET)
            );
            tooltip_list.add(StatCollector.translateToLocal("text.grid-linker-link-info.1"));
            tooltip_list.add(StatCollector.translateToLocal("text.grid-linker-link-info.2"));
            tooltip_list.add(StatCollector.translateToLocal("text.grid-linker-link-info.3"));
        }
        else
        {
            tooltip_list.add(StatCollector.translateToLocal("text.grid-linker-pre-link-info.1"));
            tooltip_list.add(StatCollector.translateToLocal("text.grid-linker-pre-link-info.2"));
        }
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
            TileEntity te = world.getTileEntity(x, y, z);

            if (!(te instanceof TileEntityPowerGridNode))
            {
                player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("text.grid-linker-not-tile-entity")));
                return false;
            }

            TileEntityPowerGridNode tepgn = (TileEntityPowerGridNode)te;
            UUID grid_uuid = tepgn.getPowerGridUUID();

            if (player.isSneaking())
            {
                NBTTagCompound data = stack.getTagCompound() != null ? stack.getTagCompound() : new NBTTagCompound();
                data.setString("grid-uuid", grid_uuid.toString());

                stack.setTagCompound(data);
            }
            else
            {
                if (stack.getTagCompound() == null || !stack.getTagCompound().hasKey("grid-uuid"))
                {
                    player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("text.grid-linker-pre-link-error")));
                    return false;
                }

                PowerGrid old_grid = PowerGridWorldSavedData.get(world).getGridByUUID(grid_uuid);
                old_grid.disconnectGridNode(x, y, z);
                old_grid.connectGrid();

                UUID new_grid_uuid = UUID.fromString(stack.getTagCompound().getString("grid-uuid"));
                PowerGrid new_grid = PowerGridWorldSavedData.get(world).getGridByUUID(new_grid_uuid);

                BlockPowerLine power_line = (BlockPowerLine) world.getBlock(x, y, z);

                tepgn.setGridUUID(new_grid_uuid);
                new_grid.connectGridNode(new PowerGridNode(x, y, z, power_line.isSubStation(), false, power_line.getNodeIdentifier()));
                new_grid.connectGrid();

                player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("text.grid-linker-pre-link-success")));
            }
        }

        return false;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (world.isRemote)
            return stack;

        if (player.isSneaking())
        {
            stack.setTagCompound(null);
        }

        return super.onItemRightClick(stack, world, player);
    }
}
