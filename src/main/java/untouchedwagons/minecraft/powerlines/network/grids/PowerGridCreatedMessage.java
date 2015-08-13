package untouchedwagons.minecraft.powerlines.network.grids;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.grids.PowerGridWorldSavedData;

import java.util.UUID;

public class PowerGridCreatedMessage extends AbstractPowerGridMessage<PowerGridCreatedMessage> {
    public PowerGridCreatedMessage() {
    }

    public PowerGridCreatedMessage(UUID grid_uuid) {
        super(grid_uuid);
    }

    @Override
    public IMessage onMessage(PowerGridCreatedMessage message, MessageContext ctx) {
        World world = Minecraft.getMinecraft().theWorld;
        PowerGridWorldSavedData.get(world).getGridByUUID(message.getGridUUID());

        return null;
    }
}
