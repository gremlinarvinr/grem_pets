package net.gremlinarvinr.gremlinpets.entity.layers;

import net.gremlinarvinr.gremlinpets.GremlinPets;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    public static final ModelLayerLocation RHINO_LAYER = new ModelLayerLocation(
            new ResourceLocation(GremlinPets.MOD_ID, "rhino_layer"), "rhino_layer");

    public static final ModelLayerLocation RAT_LAYER = new ModelLayerLocation(
            new ResourceLocation(GremlinPets.MOD_ID, "rat_layer"), "rat_layer");
}
