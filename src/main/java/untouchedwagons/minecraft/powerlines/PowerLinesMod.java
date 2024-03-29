package untouchedwagons.minecraft.powerlines;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;
import untouchedwagons.minecraft.powerlines.blocks.Blocks;
import untouchedwagons.minecraft.powerlines.grids.PowerGridWorldSavedData;
import untouchedwagons.minecraft.powerlines.items.Items;
import untouchedwagons.minecraft.powerlines.items.blocks.ItemBlockLargePowerLine;
import untouchedwagons.minecraft.powerlines.items.blocks.ItemBlockMediumPowerLine;
import untouchedwagons.minecraft.powerlines.items.blocks.ItemBlockSubStation;
import untouchedwagons.minecraft.powerlines.network.*;
import untouchedwagons.minecraft.powerlines.network.grids.*;
import untouchedwagons.minecraft.powerlines.proxy.CommonProxy;
import untouchedwagons.minecraft.powerlines.recipes.CraftingRecipesHandler;
import untouchedwagons.minecraft.powerlines.recipes.FurnaceRecipesHandler;
import untouchedwagons.minecraft.powerlines.tileentity.*;

@Mod(modid = "powerlines", name = "Power Lines", version = "0.1.0", dependencies = "required-after:CoFHCore")
public class PowerLinesMod
{
    public static Blocks blocks;
    public static Items items;

    public static Configuration config;

    public static SimpleNetworkWrapper networking;

    @SidedProxy(serverSide = "untouchedwagons.minecraft.powerlines.proxy.ServerProxy", clientSide = "untouchedwagons.minecraft.powerlines.proxy.ClientProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        event.getModMetadata().version = "0.0.4";

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

        PowerLinesMod.blocks = new Blocks();
        PowerLinesMod.items = new Items();

        PowerLinesMod.networking = new SimpleNetworkWrapper("powerlines");
        PowerLinesMod.networking.registerMessage(PowerGridSynchronizationMessage.class, PowerGridSynchronizationMessage.class, 0, Side.CLIENT);
        PowerLinesMod.networking.registerMessage(PowerGridEnergyStateMessage.class, PowerGridEnergyStateMessage.class, 1, Side.CLIENT);
        PowerLinesMod.networking.registerMessage(PowerGridCreatedMessage.class, PowerGridCreatedMessage.class, 2, Side.CLIENT);
        PowerLinesMod.networking.registerMessage(PowerGridDestroyedMessage.class, PowerGridDestroyedMessage.class, 3, Side.CLIENT);
        PowerLinesMod.networking.registerMessage(PowerGridNodeConnectedMessage.class, PowerGridNodeConnectedMessage.class, 4, Side.CLIENT);
        PowerLinesMod.networking.registerMessage(PowerGridNodeDisconnectedMessage.class, PowerGridNodeDisconnectedMessage.class, 5, Side.CLIENT);
        PowerLinesMod.networking.registerMessage(PowerGridNodeNeighbourshipMessage.class, PowerGridNodeNeighbourshipMessage.class, 6, Side.CLIENT);
        PowerLinesMod.networking.registerMessage(PowerGridConnectionStateMessage.class, PowerGridConnectionStateMessage.class, 7, Side.CLIENT);
        PowerLinesMod.networking.registerMessage(NodeWrenchedMessage.class, NodeWrenchedMessage.class, 8, Side.CLIENT);
        PowerLinesMod.networking.registerMessage(SetNodeUUIDMessage.class, SetNodeUUIDMessage.class, 9, Side.CLIENT);
        PowerLinesMod.networking.registerMessage(SetGridUUIDMessage.class, SetGridUUIDMessage.class, 10, Side.CLIENT);

        GameRegistry.registerBlock(PowerLinesMod.blocks.boundingBox, "BoundingBox");
        GameRegistry.registerTileEntity(TileEntityBoundingBox.class, "BoundingBox");
        GameRegistry.registerTileEntity(TileEntityFluxedBoundingBox.class, "FluxedBoundingBox");
        GameRegistry.registerTileEntity(TileEntityDumbFluxedBoundingBox.class, "DumbFluxedBoundingBox");

        GameRegistry.registerBlock(PowerLinesMod.blocks.largePowerLine, ItemBlockLargePowerLine.class, "LargePowerLine");
        GameRegistry.registerTileEntity(TileEntityLargePowerLine.class, "LargePowerLine");

        GameRegistry.registerBlock(PowerLinesMod.blocks.mediumPowerLine, ItemBlockMediumPowerLine.class, "MediumPowerLine");
        GameRegistry.registerTileEntity(TileEntityMediumPowerLine.class, "MediumPowerLine");

        GameRegistry.registerBlock(PowerLinesMod.blocks.subStation, ItemBlockSubStation.class, "SubStation");
        GameRegistry.registerTileEntity(TileEntitySubStation.class, "SubStation");

        GameRegistry.registerItem(PowerLinesMod.items.powerGridLinker, "GridLinker", "powerlines");
        GameRegistry.registerItem(PowerLinesMod.items.debuggingStick, "DebuggingStick", "powerlines");
        GameRegistry.registerItem(PowerLinesMod.items.craftingAlpha, "CrafitingAlpha", "powerlines");
        GameRegistry.registerItem(PowerLinesMod.items.craftingBravo, "CrafitingBravo", "powerlines");

        CraftingRecipesHandler.registerRecipes();
        FurnaceRecipesHandler.registerRecipes();

        FMLInterModComms.sendMessage("Waila", "register", "untouchedwagons.minecraft.powerlines.integration.waila.WailaDataProvider.callbackRegister");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        PowerLinesMod.proxy.registerTileEntitiesRenderers();

        FMLCommonHandler.instance().bus().register(this);
    }

    @SubscribeEvent
    public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event)
    {
        this.sendPlayerPowerGridWorldSavedData(event);
    }

    @SubscribeEvent
    public void onPlayerSwitchDimension(PlayerEvent.PlayerChangedDimensionEvent event)
    {
        this.sendPlayerPowerGridWorldSavedData(event);
    }

    private void sendPlayerPowerGridWorldSavedData(PlayerEvent event)
    {
        World world = event.player.getEntityWorld();
        PowerGridWorldSavedData pgwsd = PowerGridWorldSavedData.get(world);
        PowerGridSynchronizationMessage message = new PowerGridSynchronizationMessage(pgwsd);

        PowerLinesMod.networking.sendTo(message, (EntityPlayerMP) event.player);
    }
}