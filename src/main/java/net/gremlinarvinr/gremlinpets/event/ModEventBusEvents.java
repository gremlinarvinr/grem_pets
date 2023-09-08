package net.gremlinarvinr.gremlinpets.event;

import net.gremlinarvinr.gremlinpets.GremlinPets;
import net.gremlinarvinr.gremlinpets.entity.ModEntities;
import net.gremlinarvinr.gremlinpets.entity.client.RatModel;
import net.gremlinarvinr.gremlinpets.entity.client.RhinoModel;
import net.gremlinarvinr.gremlinpets.entity.custom.RatEntity;
import net.gremlinarvinr.gremlinpets.entity.custom.RhinoEntity;
import net.gremlinarvinr.gremlinpets.entity.layers.ModModelLayers;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GremlinPets.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.RHINO_LAYER, RhinoModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.RAT_LAYER, RatModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.RHINO.get(), RhinoEntity.createAttributes().build());
        event.put(ModEntities.RAT.get(), RatEntity.createAttributes().build());
    }
}
