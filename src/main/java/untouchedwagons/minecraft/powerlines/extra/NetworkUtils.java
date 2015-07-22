package untouchedwagons.minecraft.powerlines.extra;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import untouchedwagons.minecraft.powerlines.PowerLinesMod;

import java.util.List;

public class NetworkUtils {
    private NetworkUtils() {

    }

    public static void broadcastToWorld(World world, IMessage... messages)
    {
        //noinspection unchecked
        for (EntityPlayer p : (List<EntityPlayer>)world.playerEntities)
        {
            for (IMessage message : messages) {
                PowerLinesMod.networking.sendTo(message, (EntityPlayerMP) p);
            }
        }
    }
}
