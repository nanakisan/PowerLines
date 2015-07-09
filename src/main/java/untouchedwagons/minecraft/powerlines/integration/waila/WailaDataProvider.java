package untouchedwagons.minecraft.powerlines.integration.waila;

import cpw.mods.fml.common.FMLLog;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.blocks.BlockBoundingBox;
import untouchedwagons.minecraft.powerlines.blocks.BlockSubStation;
import untouchedwagons.minecraft.powerlines.grids.PowerGrid;
import untouchedwagons.minecraft.powerlines.grids.PowerGridNode;
import untouchedwagons.minecraft.powerlines.grids.PowerGridWorldSavedData;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityBoundingBox;
import cpw.mods.fml.common.Optional.Interface;
import cpw.mods.fml.common.Optional.Method;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityPowerLine;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntitySubStation;

import java.util.List;

@Interface(iface = "mcp.mobius.waila.api.IWailaDataProvider", modid = "Waila")
public class WailaDataProvider implements IWailaDataProvider
{
    @Override
    @Method(modid = "Waila")
    public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
        TileEntityBoundingBox tile = (TileEntityBoundingBox) accessor.getTileEntity();

        Block b = tile.getWorldObj().getBlock(tile.orig_x, tile.orig_y, tile.orig_z);

        return new ItemStack(b);
    }

    @Override
    @Method(modid = "Waila")
    public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return currenttip;
    }

    @Override
    @Method(modid = "Waila")
    public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        TileEntity te = accessor.getTileEntity();

        if (te instanceof TileEntityBoundingBox)
        {
            TileEntityBoundingBox tebb = (TileEntityBoundingBox)te;
            TileEntityPowerLine tepl = (TileEntityPowerLine) accessor.getWorld().getTileEntity(tebb.orig_x, tebb.orig_y, tebb.orig_z);

            if(accessor.getPlayer().isSneaking())
            {
                currenttip.add(String.format("%sNode UUID%s: %s%s%s", EnumChatFormatting.RED, EnumChatFormatting.RESET, EnumChatFormatting.AQUA, tepl.getNodeUUID(), EnumChatFormatting.RESET));
                currenttip.add(String.format("%sGrid UUID%s: %s%s%s", EnumChatFormatting.RED, EnumChatFormatting.RESET, EnumChatFormatting.AQUA, tepl.getPowerGridUUID(), EnumChatFormatting.RESET));
            }
            else
            {
                currenttip.add("Sneak to view extra info");
            }

            if (tepl instanceof TileEntitySubStation)
            {
                PowerGrid grid = PowerGridWorldSavedData.get(accessor.getWorld()).getGridByUUID(tepl.getPowerGridUUID());
                PowerGridNode node = grid.getGridNode(tebb.orig_x, tebb.orig_y, tebb.orig_z);

                currenttip.add(String.format("Is Connected: %b", node.isConnected()));
            }
        }

        return currenttip;
    }

    @Override
    @Method(modid = "Waila")
    public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return currenttip;
    }

    @Override
    @Method(modid = "Waila")
    public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, int x, int y, int z) {
        return tag;
    }

    @Method(modid = "Waila")
    public static void callbackRegister(IWailaRegistrar registrar)
    {
        FMLLog.info("Registering WAILA data provider");
        WailaDataProvider provider = new WailaDataProvider();
        registrar.registerStackProvider(provider, BlockBoundingBox.class);

        registrar.registerBodyProvider(provider, BlockSubStation.class);
        registrar.registerBodyProvider(provider, BlockBoundingBox.class);
    }
}
