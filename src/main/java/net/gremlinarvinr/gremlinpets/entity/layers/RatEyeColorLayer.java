package net.gremlinarvinr.gremlinpets.entity.layers;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.gremlinarvinr.gremlinpets.entity.client.RatModel;
import net.gremlinarvinr.gremlinpets.entity.custom.RatEntity;
import net.gremlinarvinr.gremlinpets.entity.variant.RatEyeColor;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class RatEyeColorLayer extends RenderLayer<RatEntity, RatModel<RatEntity>> {
    private static final Map<RatEyeColor, ResourceLocation> LOCATION_BY_EYE_COLORS = Util.make(Maps.newEnumMap(RatEyeColor.class), map -> {
        map.put(RatEyeColor.DEFAULT, null);
        map.put(RatEyeColor.BLACK, new ResourceLocation("textures/entity/rat/eyes/black_eyes.png"));
        map.put(RatEyeColor.PINK, new ResourceLocation("textures/entity/rat/eyes/pink_eyes.png"));
    });

    public RatEyeColorLayer(RenderLayerParent<RatEntity, RatModel<RatEntity>> pRenderer) {
        super(pRenderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource pBuffer, int pPackedLight, RatEntity entity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        ResourceLocation resourceLocation = LOCATION_BY_EYE_COLORS.get(entity.getRatEyeColor());
        if (resourceLocation != null && !entity.isInvisible()) {
            VertexConsumer vertexConsumer = pBuffer.getBuffer(RenderType.entityTranslucent(resourceLocation));
            this.getParentModel().renderToBuffer(poseStack, vertexConsumer, pPackedLight, LivingEntityRenderer.getOverlayCoords(entity,0.0f), 1.0f, 1.0f, 1.0f, 1.0f);
        }
    }
}
