package untouchedwagons.minecraft.powerlines.network.grids;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.grids.PowerGridWorldSavedData;

public class PowerGridDestroyedMessage extends AbstractPowerGridMessage<PowerGridDestroyedMessage> {
    @Override
    public IMessage onMessage(PowerGridDestroyedMessage message, MessageContext ctx) {
        World world = Minecraft.getMinecraft().theWorld;
        PowerGridWorldSavedData.get(world).removePowerGrid(this.getGridUUID());

        return null;
    }
}
