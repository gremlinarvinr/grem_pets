package net.gremlinarvinr.gremlinpets.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.gremlinarvinr.gremlinpets.GremlinPets;
import net.gremlinarvinr.gremlinpets.entity.custom.RhinoEntity;
import net.gremlinarvinr.gremlinpets.entity.layers.ModModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RhinoRenderer extends MobRenderer<RhinoEntity, RhinoModel<RhinoEntity>> {

    private static final ResourceLocation RHINO_LOCATION = new ResourceLocation(GremlinPets.MOD_ID, "textures/entity/rhino.png");

    public RhinoRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new RhinoModel<>(pContext.bakeLayer(ModModelLayers.RHINO_LAYER)), 2f);
    }

    @Override
    public ResourceLocation getTextureLocation(RhinoEntity pEntity) {
        return RHINO_LOCATION;
    }

    @Override
    public void render(RhinoEntity pEntity, float pEntityYaw, float pPartialTicks,
                       PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) { // IF BABY, SCALE DOWN ITS SIZE ALL DIRECTIONS !!
            pMatrixStack.scale(0.45f, 0.45f, 0.45f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
