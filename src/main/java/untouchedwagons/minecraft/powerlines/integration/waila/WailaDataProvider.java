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
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.blocks.BlockBoundingBox;
import untouchedwagons.minecraft.powerlines.blocks.BlockPowerLine;
import untouchedwagons.minecraft.powerlines.grids.PowerGrid;
import untouchedwagons.minecraft.powerlines.grids.PowerGridNode;
import untouchedwagons.minecraft.powerlines.grids.PowerGridWorldSavedData;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityBoundingBox;
import cpw.mods.fml.common.Optional.Interface;
import cpw.mods.fml.common.Optional.Method;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityPowerGridNode;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntitySubStation;

import java.util.List;
import java.util.UUID;

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
        TileEntityPowerGridNode tepgn;

        if (te instanceof TileEntityBoundingBox) {
            TileEntityBoundingBox tebb = (TileEntityBoundingBox) te;
            tepgn = (TileEntityPowerGridNode) accessor.getWorld().getTileEntity(tebb.orig_x, tebb.orig_y, tebb.orig_z);
        }
        else
        {
            tepgn = (TileEntityPowerGridNode) te;
        }

        if(accessor.getPlayer().isSneaking())
        {
            currenttip.add(
                    String.format(
                            "%sNode UUID%s: %s%s%s",
                            EnumChatFormatting.AQUA,
                            EnumChatFormatting.RESET,
                            EnumChatFormatting.GREEN,
                            tepgn.getNodeUUID(),
                            EnumChatFormatting.RESET
                    )
            );

            UUID grid_uuid = tepgn.getPowerGridUUID();

            if (grid_uuid != null)
            {
                currenttip.add(
                        String.format(
                                "%sGrid UUID%s: %s%s%s",
                                EnumChatFormatting.AQUA,
                                EnumChatFormatting.RESET,
                                EnumChatFormatting.GREEN,
                                tepgn.getPowerGridUUID(),
                                EnumChatFormatting.RESET
                        )
                );
            }
            else
            {
                currenttip.add(
                        String.format(
                                "%sGrid UUID%s: %s%s%s",
                                EnumChatFormatting.AQUA,
                                EnumChatFormatting.RESET,
                                EnumChatFormatting.RED,
                                StatCollector.translateToLocal("text.waila.grid-uuid-none"),
                                EnumChatFormatting.RESET
                        )
                );
            }
        }
        else
        {
            currenttip.add("Sneak to view extra info");
        }

        if (tepgn instanceof TileEntitySubStation)
        {
            PowerGrid grid = PowerGridWorldSavedData.get(accessor.getWorld()).getGridByUUID(tepgn.getPowerGridUUID());

            currenttip.add(
                    String.format(
                            "%sIs Connected:%s %s%b%s",
                            EnumChatFormatting.AQUA,
                            EnumChatFormatting.RESET,
                            grid.isConnected() ? EnumChatFormatting.GREEN : EnumChatFormatting.RED,
                            grid.isConnected(),
                            EnumChatFormatting.RESET
                    )
            );

            String mode_text = "";

            switch (((TileEntitySubStation) tepgn).getEnergyMode())
            {
                case INPUT:
                    mode_text = String.format("%s%s%s", EnumChatFormatting.BLUE, StatCollector.translateToLocal("text.substation.mode.input"), EnumChatFormatting.RESET);
                    break;
                case OUTPUT:
                    mode_text = String.format("%s%s%s", EnumChatFormatting.GOLD, StatCollector.translateToLocal("text.substation.mode.output"), EnumChatFormatting.RESET);
                    break;
                case UNKNOWN:
                    mode_text = String.format("%s%s%s", EnumChatFormatting.GRAY, StatCollector.translateToLocal("text.substation.mode.unknown"), EnumChatFormatting.RESET);
                    break;
            }

            currenttip.add(String.format("%s%s%s: %s", EnumChatFormatting.AQUA, StatCollector.translateToLocal("text.substation.mode.text"), EnumChatFormatting.RESET, mode_text));
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

        registrar.registerBodyProvider(provider, BlockBoundingBox.class);
        registrar.registerBodyProvider(provider, BlockPowerLine.class);
    }
}
