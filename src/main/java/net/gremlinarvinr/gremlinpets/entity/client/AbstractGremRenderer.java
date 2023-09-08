package net.gremlinarvinr.gremlinpets.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.world.entity.Mob;

public abstract class AbstractGremRenderer<T extends Mob, M extends EntityModel<T>> extends MobRenderer<T, M> {
    private final float scale;

    public AbstractGremRenderer(EntityRendererProvider.Context pContext, M pModel, float pScale) {
        super(pContext, pModel, 0.75F);
        this.scale = pScale;
    }

    protected void scale(T pLivingEntity, PoseStack pMatrixStack, float pPartialTickTime) {
        pMatrixStack.scale(this.scale, this.scale, this.scale);
        super.scale(pLivingEntity, pMatrixStack, pPartialTickTime);
    }
}
