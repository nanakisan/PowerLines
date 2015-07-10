package untouchedwagons.minecraft.powerlines.network;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.grids.PowerGridWorldSavedData;

public class PowerGridSynchronizationMessage extends AbstractMessage<PowerGridSynchronizationMessage> {
    private PowerGridWorldSavedData grid_data;
    private NBTTagCompound grid_nbt_data;

    public PowerGridSynchronizationMessage() {
    }

    public PowerGridSynchronizationMessage(PowerGridWorldSavedData grid_data) {
        this.grid_data = grid_data;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.grid_nbt_data = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        NBTTagCompound grid_nbt_data = new NBTTagCompound();
        this.grid_data.writeToNBT(grid_nbt_data);

        ByteBufUtils.writeTag(buf, grid_nbt_data);
    }

    @Override
    public IMessage onMessage(PowerGridSynchronizationMessage message, MessageContext ctx) {
        World world = Minecraft.getMinecraft().theWorld;
        PowerGridWorldSavedData pgwsd = PowerGridWorldSavedData.get(world);

        pgwsd.readFromNBT(message.getGridNbtData());

        return null;
    }

    public NBTTagCompound getGridNbtData() {
        return grid_nbt_data;
    }
}
