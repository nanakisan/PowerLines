package untouchedwagons.minecraft.powerlines;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.config.Configuration;
import untouchedwagons.minecraft.powerlines.blocks.BlockBoundingBox;
import untouchedwagons.minecraft.powerlines.blocks.BlockLargePowerLine;
import untouchedwagons.minecraft.powerlines.blocks.BlockPowerLine;
import untouchedwagons.minecraft.powerlines.blocks.BlockSubStation;
import untouchedwagons.minecraft.powerlines.item.ItemBlockLargePowerLine;
import untouchedwagons.minecraft.powerlines.item.ItemBlockSubStation;
import untouchedwagons.minecraft.powerlines.proxy.CommonProxy;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityBoundingBox;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityFluxedBoundingBox;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityLargePowerLine;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntitySubStation;

@Mod(modid = "powerlines", name = "Power Lines", version = "0.0.2", dependencies = "required-after:CoFHCore")
public class PowerLinesMod
{
    public static BlockBoundingBox bounding_box;
    public static BlockLargePowerLine large_power_line;
    public static BlockSubStation substation;

    public static Configuration config;

    public static SimpleNetworkWrapper networking;

    @SidedProxy(serverSide = "untouchedwagons.minecraft.powerlines.proxy.ServerProxy", clientSide = "untouchedwagons.minecraft.powerlines.proxy.ClientProxy")
    public static CommonProxy proxy;


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        event.getModMetadata().version = "0.0.2";

        PowerLinesMod.config = new Configuration(event.getSuggestedConfigurationFile());

        PowerLinesMod.config.get("power-grid", "max-energy", 1000000, "The maximum amount of RF a power grid can store.");

        PowerLinesMod.config.get("large-power-line", "max-distance", 64, "The maximum distance between two large power lines in Euclidian space.");
        PowerLinesMod.config.get("large-power-line", "max-angle", 45, "The maximum angle between two large power lines, measured in degrees. The default is 45.");

        PowerLinesMod.config.get("medium-power-line", "max-distance", 32, "The maximum distance between two medium power lines in Euclidian space.");
        PowerLinesMod.config.get("medium-power-line", "max-angle", 35, "The maximum angle between two medium power lines, measured in degrees. The default is 35.");

        PowerLinesMod.config.get("small-power-line", "max-distance", 16, "The maximum distance between two small power lines in Euclidian space.");
        PowerLinesMod.config.get("small-power-line", "max-angle", 25, "The maximum angle between two small power lines, measured in degrees. The default is 25.");

        PowerLinesMod.config.get("substation", "max-distance", 8, "The maximum distance between a substation and a power line in Euclidian space.");
        PowerLinesMod.config.get("substation", "max-angle", 45, "The maximum angle between a substation and a power line, measured in degrees. The default is 45.");

        if (PowerLinesMod.config.hasChanged()) PowerLinesMod.config.save();

        PowerLinesMod.networking = new SimpleNetworkWrapper("powerlines");

        PowerLinesMod.bounding_box = new BlockBoundingBox();
        PowerLinesMod.large_power_line = new BlockLargePowerLine();
        PowerLinesMod.substation = new BlockSubStation();

        GameRegistry.registerBlock(PowerLinesMod.bounding_box, "BoundingBox");
        GameRegistry.registerTileEntity(TileEntityBoundingBox.class, "BoundingBox");
        GameRegistry.registerTileEntity(TileEntityFluxedBoundingBox.class, "FluxedBoundingBox");

        GameRegistry.registerBlock(PowerLinesMod.large_power_line, ItemBlockLargePowerLine.class, "LargePowerLine");
        GameRegistry.registerTileEntity(TileEntityLargePowerLine.class, "LargePowerLine");

        GameRegistry.registerBlock(PowerLinesMod.substation, ItemBlockSubStation.class, "SubStation");
        GameRegistry.registerTileEntity(TileEntitySubStation.class, "SubStation");

        FMLInterModComms.sendMessage("Waila", "register", "untouchedwagons.minecraft.powerlines.integration.waila.WailaDataProvider.callbackRegister");
    }

    @Mod.EventHandler
    public void load(FMLInitializationEvent event) {
        PowerLinesMod.proxy.registerTileEntitiesRenderers();
    }
}