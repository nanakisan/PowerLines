package untouchedwagons.minecraft.powerlines.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import untouchedwagons.minecraft.powerlines.PowerLinesMod;
import untouchedwagons.minecraft.powerlines.render.blocks.LargePowerLineRenderer;
import untouchedwagons.minecraft.powerlines.render.blocks.MediumPowerLineRenderer;
import untouchedwagons.minecraft.powerlines.render.blocks.SubStationRenderer;
import untouchedwagons.minecraft.powerlines.render.items.LargePowerLineItemRenderer;
import untouchedwagons.minecraft.powerlines.render.items.MediumPowerLineItemRenderer;
import untouchedwagons.minecraft.powerlines.render.items.SubStationItemRenderer;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityLargePowerLine;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntityMediumPowerLine;
import untouchedwagons.minecraft.powerlines.tileentity.TileEntitySubStation;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerTileEntitiesRenderers() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLargePowerLine.class, new LargePowerLineRenderer());
        MinecraftForgeClient.registerItemRenderer(
                Item.getItemFromBlock(PowerLinesMod.blocks.largePowerLine),
                new LargePowerLineItemRenderer()
            );

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySubStation.class, new SubStationRenderer());
        MinecraftForgeClient.registerItemRenderer(
                Item.getItemFromBlock(PowerLinesMod.blocks.subStation),
                new SubStationItemRenderer()
        );

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMediumPowerLine.class, new MediumPowerLineRenderer());
        MinecraftForgeClient.registerItemRenderer(
                Item.getItemFromBlock(PowerLinesMod.blocks.mediumPowerLine),
                new MediumPowerLineItemRenderer()
        );
    }
}
