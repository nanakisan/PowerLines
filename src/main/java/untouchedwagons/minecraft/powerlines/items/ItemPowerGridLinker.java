package untouchedwagons.minecraft.powerlines.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import untouchedwagons.math.MathHelper;
import untouchedwagons.minecraft.powerlines.PowerLinesMod;
import untouchedwagons.minecraft.powerlines.blocks.BlockPowerLine;
import untouchedwagons.minecraft.powerlines.grids.PowerGrid;
import untouchedwagons.minecraft.powerlines.grids.PowerGridNode;
import untouchedwagons.minecraft.powerlines.grids.PowerGridWorldSavedData;
import untouchedwagons.minecraft.powerlines.network.PowerGridSynchronizationMessage;
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
        tooltip_list.add(StatCollector.translateToLocal("text.grid-linker-info.1"));
        tooltip_list.add(StatCollector.translateToLocal("text.grid-linker-info.2"));
        tooltip_list.add(StatCollector.translateToLocal("text.grid-linker-info.3"));
        tooltip_list.add(StatCollector.translateToLocal("text.grid-linker-info.4"));
        tooltip_list.add(StatCollector.translateToLocal("text.grid-linker-info.5"));
        tooltip_list.add(StatCollector.translateToLocal("text.grid-linker-info.6"));
        tooltip_list.add(StatCollector.translateToLocal("text.grid-linker-info.7"));
        tooltip_list.add(StatCollector.translateToLocal("text.grid-linker-info.8"));
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        if (world.isRemote)
            return false;

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
                player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("text.grid-linker-no-grid-error")));
                return false;
            }

            NBTTagCompound linker_data = new NBTTagCompound();
            linker_data.setString("grid-uuid", tepgn.getPowerGridUUID().toString());
            linker_data.setInteger("x", tepgn.xCoord);
            linker_data.setInteger("y", tepgn.yCoord);
            linker_data.setInteger("z", tepgn.zCoord);

            stack.setTagCompound(linker_data);

            player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("text.grid-linker-saved")));

            return false;
        }
        else // Setting node data
        {
            if (stack.getTagCompound() == null || !stack.getTagCompound().hasKey("grid-uuid"))
            {
                player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("text.grid-linker-no-data-error")));
                return false;
            }

            NBTTagCompound stack_tag = stack.getTagCompound();
            UUID grid_uuid = UUID.fromString(stack_tag.getString("grid-uuid"));
            int orig_x = stack_tag.getInteger("x"), orig_y = stack_tag.getInteger("y"), orig_z = stack_tag.getInteger("z");

            // Has the player gone so far that the chunk has unloaded?
            if (!world.blockExists(orig_x, orig_y, orig_z))
            {
                player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("text.grid-linker-no-block-error")));
                return false;
            }

            // Has the player gone to another dimension or destroyed the original power line?
            if (!(world.getBlock(orig_x, orig_y, orig_z) instanceof BlockPowerLine))
            {
                player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("text.grid-linker-missing-block-error")));
                return false;
            }

            BlockPowerLine that_block =(BlockPowerLine) world.getBlock(orig_x, orig_y, orig_z);
            BlockPowerLine this_block = (BlockPowerLine) world.getBlock(x, y, z);

            int max_distance = that_block.getPoleInfo().max_distance > this_block.getPoleInfo().max_distance ? this_block.getPoleInfo().max_distance : that_block.getPoleInfo().max_distance;
            double max_angle = that_block.getPoleInfo().max_angle > this_block.getPoleInfo().max_angle ? this_block.getPoleInfo().max_angle : that_block.getPoleInfo().max_angle;

            double distance = MathHelper.calculateDistance(x, y, z, orig_x, orig_y, orig_z);
            double angle = MathHelper.calculateAngle(x, y, z, orig_x, orig_y, orig_z);

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

            PowerGrid grid = PowerGridWorldSavedData.get(world).getGridByUUID(grid_uuid);

            PowerGridNode that_node = grid.getGridNode(orig_x, orig_y, orig_z);
            PowerGridNode this_node = null;

            if (tepgn.getPowerGridUUID() == null) // If this node isn't already part of a grid
            {
                this_node = new PowerGridNode(x, y, z, this_block.isSubStation(), false, this_block.getNodeIdentifier());
            }
            else
            {
                PowerGrid old_grid = PowerGridWorldSavedData.get(world).getGridByUUID(tepgn.getPowerGridUUID());
                this_node = old_grid.getGridNode(tepgn.xCoord, tepgn.yCoord, tepgn.zCoord);

                old_grid.disconnectGridNode(this_node);
                old_grid.connectGrid();
            }

            // Establish connections between the two nodes
            that_node.getNeighbours().add(this_node);
            this_node.getNeighbours().add(that_node);

            // Inform the grid about the new node
            grid.connectGridNode(this_node);
            grid.connectGrid();

            // Inform everyone in the world of the changes.
            PowerGridSynchronizationMessage message = new PowerGridSynchronizationMessage(PowerGridWorldSavedData.get(world));

            //noinspection unchecked
            for (EntityPlayer player_in_world : (List<EntityPlayer>)world.playerEntities)
            {
                PowerLinesMod.networking.sendTo(message, (EntityPlayerMP) player_in_world);
            }

            player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("text.grid-linker-link-success")));
            return true;
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (world.isRemote)
            return stack;

        if (player.isSneaking())
        {
            stack.setTagCompound(null);
            player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("text.grid-linker-clear")));
        }

        return super.onItemRightClick(stack, world, player);
    }
}
