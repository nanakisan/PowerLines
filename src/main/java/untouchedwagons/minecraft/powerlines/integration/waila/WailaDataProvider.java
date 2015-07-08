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
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.blocks.BlockBoundingBox;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityBoundingBox;
import cpw.mods.fml.common.Optional.Interface;
import cpw.mods.fml.common.Optional.Method;

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
        registrar.registerStackProvider(new WailaDataProvider(), BlockBoundingBox.class);
    }
}
