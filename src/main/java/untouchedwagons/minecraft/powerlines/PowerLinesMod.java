package untouchedwagons.minecraft.powerlines;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.config.Configuration;
import untouchedwagons.minecraft.powerlines.blocks.BlockLargePowerLine;
import untouchedwagons.minecraft.powerlines.blocks.BlockPowerLine;
import untouchedwagons.minecraft.powerlines.proxy.CommonProxy;

@Mod(modid = "powerlines", name = "Power Lines", version = "0.0.1")
public class PowerLinesMod
{
    public static BlockPowerLine large_power_line;

    public static Configuration config;

    public static SimpleNetworkWrapper networking;

    @SidedProxy(serverSide = "untouchedwagons.minecraft.powerlines.proxy.ServerProxy", clientSide = "untouchedwagons.minecraft.powerlines.proxy.ClientProxy")
    public static CommonProxy proxy;


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        event.getModMetadata().version = "0.0.1";

        PowerLinesMod.config = new Configuration(event.getSuggestedConfigurationFile());

        PowerLinesMod.config.get("large-power-line", "max-energy", 1000000, "The maximum amount of RF a large power line can hold.");
        PowerLinesMod.config.get("large-power-line", "max-io", 100000, "The maximum amount of RF a large power line can transfer per tick.");
        PowerLinesMod.config.get("large-power-line", "max-distance", 48, "The maximum distance between two large power lines in Euclidian space.");
        PowerLinesMod.config.get("large-power-line", "max-angle", 45, "The maximum angle between two large power lines, measured in degrees. The default is 45.");

        if (PowerLinesMod.config.hasChanged()) PowerLinesMod.config.save();

        PowerLinesMod.large_power_line = new BlockLargePowerLine();

        PowerLinesMod.networking = new SimpleNetworkWrapper("powerlines");
    }

    @Mod.EventHandler
    public void load(FMLInitializationEvent event) {
        GameRegistry.registerBlock(PowerLinesMod.large_power_line, "LargePowerLine");

        PowerLinesMod.proxy.registerSpecialTileEntities();
    }
}