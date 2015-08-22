package untouchedwagons.minecraft.powerlines.items;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import untouchedwagons.math.MathHelper;
import untouchedwagons.minecraft.powerlines.blocks.BlockPowerLine;
import untouchedwagons.minecraft.powerlines.extra.NetworkUtils;
import untouchedwagons.minecraft.powerlines.grids.PowerGrid;
import untouchedwagons.minecraft.powerlines.grids.PowerGridNode;
import untouchedwagons.minecraft.powerlines.grids.PowerGridWorldSavedData;
import untouchedwagons.minecraft.powerlines.network.grids.*;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityBoundingBox;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityPowerGridNode;

import java.util.List;
import java.util.UUID;

public class ItemPowerGridLinker extends Item {
    public ItemPowerGridLinker() {
        this.setUnlocalizedName("grid-linker");
        this.setTextureName("powerlines:grid-linker");
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.tabMisc);
    }

    @SuppressWarnings("unchecked")
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List tooltip_list, boolean p_77624_4_) {
        if (player.isSneaking())
        {
            tooltip_list.add(StatCollector.translateToLocal("text.grid-linker-sneak-info.1"));
            tooltip_list.add(StatCollector.translateToLocal("text.grid-linker-sneak-info.2"));
            tooltip_list.add(StatCollector.translateToLocal("text.grid-linker-sneak-info.3"));
            tooltip_list.add(StatCollector.translateToLocal("text.grid-linker-sneak-info.4"));
            tooltip_list.add(StatCollector.translateToLocal("text.grid-linker-sneak-info.5"));
            tooltip_list.add(StatCollector.translateToLocal("text.grid-linker-sneak-info.6"));
            tooltip_list.add(StatCollector.translateToLocal("text.grid-linker-sneak-info.7"));
            tooltip_list.add(StatCollector.translateToLocal("text.grid-linker-sneak-info.8"));
        }
        else
        {
            tooltip_list.add(StatCollector.translateToLocal("text.grid-linker-info.1"));

            if (stack.hasTagCompound())
            {
                if (stack.getTagCompound().hasKey("grid-uuid") &&
                    stack.getTagCompound().hasKey("node-uuid"))
                {
                    tooltip_list.add(String.format(StatCollector.translateToLocal("text.grid-linker-info.2"), stack.getTagCompound().getString("node-uuid")));
                    tooltip_list.add(String.format(StatCollector.translateToLocal("text.grid-linker-info.3"), stack.getTagCompound().getString("grid-uuid")));
                }
            }
        }
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        if (world.isRemote)
            return true;

        TileEntity te = world.getTileEntity(x, y, z);
        TileEntityPowerGridNode tepgn = null;

        if (!(te instanceof TileEntityPowerGridNode) && !(te instanceof TileEntityBoundingBox))
        {
            player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("text.grid-linker-not-tile-entity")));
            return false;
        }

        if (te instanceof TileEntityBoundingBox)
        {
            TileEntityBoundingBox tebb = (TileEntityBoundingBox) te;
            tepgn = (TileEntityPowerGridNode) world.getTileEntity(tebb.orig_x, tebb.orig_y, tebb.orig_z);
        }

        if (te instanceof TileEntityPowerGridNode)
            tepgn = (TileEntityPowerGridNode) te;

        if (player.isSneaking()) // Saving node data
        {
            if (tepgn.getPowerGridUUID() == null) // Player is shift-right clicking a power line that's not linked to a power grid
            {
                player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("text.grid-linker-no-grid-set-error")));
                return false;
            }

            NBTTagCompound linker_data = new NBTTagCompound();
            linker_data.setString("grid-uuid", tepgn.getPowerGridUUID().toString());
            linker_data.setString("node-uuid", tepgn.getNodeUUID().toString());

            stack.setTagCompound(linker_data);

            player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("text.grid-linker-saved")));

            return true;
        }
        else // Setting node data
        {
            if (stack.getTagCompound() == null || !stack.getTagCompound().hasKey("grid-uuid") || !stack.getTagCompound().hasKey("node-uuid"))
            {
                player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("text.grid-linker-no-data-error")));
                return false;
            }

            NBTTagCompound stack_tag = stack.getTagCompound();
            UUID grid_uuid = UUID.fromString(stack_tag.getString("grid-uuid"));
            UUID node_uuid = UUID.fromString(stack_tag.getString("node-uuid"));
            PowerGridWorldSavedData pgwsd = PowerGridWorldSavedData.get(world);
            PowerGrid grid = pgwsd.hasPowerGrid(grid_uuid) ? pgwsd.getGridByUUID(grid_uuid) : null;

            // Has the player gone to another dimension?
            if (grid == null)
            {
                player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("text.grid-linker-no-grid-error")));
                return false;
            }

            PowerGridNode that_node = grid.getGridNode(node_uuid);
            PowerGridNode this_node = null;

            // Has the original node been connected to a different grid?
            if (that_node == null)
            {
                player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("text.grid-linker-no-node-error")));
                return false;
            }

            // Has the player gone so far that the chunk has unloaded?
            if (!world.blockExists(that_node.getX(), that_node.getY(), that_node.getZ()))
            {
                player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("text.grid-linker-no-block-error")));
                return false;
            }

            // Has the player destroyed the original power line?
            if (!(world.getBlock(that_node.getX(), that_node.getY(), that_node.getZ()) instanceof BlockPowerLine))
            {
                player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("text.grid-linker-missing-block-error")));
                return false;
            }

            BlockPowerLine that_block = (BlockPowerLine) world.getBlock(that_node.getX(), that_node.getY(), that_node.getZ());
            BlockPowerLine this_block = (BlockPowerLine) world.getBlock(tepgn.xCoord, tepgn.yCoord, tepgn.zCoord);

            int max_distance = that_block.getPoleInfo().max_distance > this_block.getPoleInfo().max_distance ? this_block.getPoleInfo().max_distance : that_block.getPoleInfo().max_distance;
            double max_angle = that_block.getPoleInfo().max_angle > this_block.getPoleInfo().max_angle ? this_block.getPoleInfo().max_angle : that_block.getPoleInfo().max_angle;

            double distance = MathHelper.calculateDistance(tepgn.xCoord, tepgn.yCoord, tepgn.zCoord, that_node.getX(), that_node.getY(), that_node.getZ());
            double angle = MathHelper.calculateAngle(tepgn.xCoord, tepgn.yCoord, tepgn.zCoord, that_node.getX(), that_node.getY(), that_node.getZ());

            IMessage message;

            // Too far apart?
            if (distance > max_distance)
            {
                player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("text.grid-linker-too-far-error")));
                return false;
            }

            // Angle too steep?
            if (angle > max_angle)
            {
                player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("text.grid-linker-too-steep-error")));
                return false;
            }

            // The TE is connected to a different power grid
            if (tepgn.getPowerGridUUID() != null && !tepgn.getPowerGridUUID().equals(grid_uuid))
            {
                PowerGrid old_grid = PowerGridWorldSavedData.get(world).getGridByUUID(tepgn.getPowerGridUUID());

                this_node = old_grid.getGridNode(tepgn.getNodeUUID());

                old_grid.disconnectGridNode(this_node);

                message = new PowerGridNodeDisconnectedMessage(tepgn.getPowerGridUUID(), tepgn.getNodeUUID(), tepgn.xCoord, tepgn.yCoord, tepgn.zCoord);
                NetworkUtils.broadcastToWorld(world, message);

                old_grid.connectGrid();

                tepgn.setGridUUID(null);
            }

            if (tepgn.getPowerGridUUID() == null) // If this node isn't already part of a grid or has been disconnected from one previously
            {
                this_node = new PowerGridNode(tepgn.getNodeUUID(), tepgn.xCoord, tepgn.yCoord, tepgn.zCoord, this_block.isSubStation(), this_block.getNodeIdentifier());

                // Add the node to the grid
                grid.connectGridNode(this_node);
                tepgn.setGridUUID(grid_uuid);

                message = new SetGridUUIDMessage(tepgn.xCoord, tepgn.yCoord, tepgn.zCoord, grid_uuid);
                NetworkUtils.broadcastToWorld(world, message);

                message = new PowerGridNodeConnectedMessage(grid.getGridUUID(), tepgn.getNodeUUID(), tepgn.xCoord, tepgn.yCoord, tepgn.zCoord, this_block.isSubStation(), this_block.getNodeIdentifier());
                NetworkUtils.broadcastToWorld(world, message);
            }

            // this_node will be null if this node was on the same grid to begin with
            if (this_node == null)
            {
                this_node = grid.getGridNode(tepgn.getNodeUUID());
            }

            // Establish connections between the two nodes
            that_node.getNeighbours().add(this_node);
            this_node.getNeighbours().add(that_node);

            message = new PowerGridNodeNeighbourshipMessage(grid_uuid, this_node.getNodeUUID(), that_node.getNodeUUID());
            NetworkUtils.broadcastToWorld(world, message);

            grid.connectGrid();

            player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("text.grid-linker-link-success")));
            return true;
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (world.isRemote)
            return stack;

        if (player.isSneaking() && stack.getTagCompound() != null)
        {
            if (stack.getTagCompound().hasKey("grid-uuid")) stack.getTagCompound().removeTag("grid-uuid");
            if (stack.getTagCompound().hasKey("node-uuid")) stack.getTagCompound().removeTag("node-uuid");

            player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("text.grid-linker-clear")));
        }

        return super.onItemRightClick(stack, world, player);
    }
}
