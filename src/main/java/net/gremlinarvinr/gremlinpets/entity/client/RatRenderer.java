package net.gremlinarvinr.gremlinpets.entity.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.gremlinarvinr.gremlinpets.GremlinPets;
import net.gremlinarvinr.gremlinpets.entity.custom.RatEntity;
import net.gremlinarvinr.gremlinpets.entity.layers.ModModelLayers;
import net.gremlinarvinr.gremlinpets.entity.layers.RatEyeColorLayer;
import net.gremlinarvinr.gremlinpets.entity.variant.RatVariant;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public final class RatRenderer extends AbstractGremRenderer<RatEntity, RatModel<RatEntity>> {
    private static final Map<RatVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(RatVariant.class), map -> {
                map.put(RatVariant.DEFAULT,
                        new ResourceLocation(GremlinPets.MOD_ID, "textures/entity/rat/variant/rat.png"));
                map.put(RatVariant.BEIGE,
                        new ResourceLocation(GremlinPets.MOD_ID, "textures/entity/rat/variant/beige_rat.png"));
                map.put(RatVariant.ALBINO,
                        new ResourceLocation(GremlinPets.MOD_ID, "textures/entity/rat/variant/albino_rat.png"));
            });

    public RatRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new RatModel<>(pContext.bakeLayer(ModModelLayers.RAT_LAYER)), .8f);
        this.addLayer(new RatEyeColorLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(RatEntity pEntity) {
        return LOCATION_BY_VARIANT.get(pEntity.getVariant());
    }

    @Override
    public void render(RatEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(.8f, .8f, .8f);

        if(entity.isBaby()) { // IF BABY, SCALE DOWN ITS SIZE ALL DIRECTIONS !!
            poseStack.scale(.3f, .3f, .3f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
