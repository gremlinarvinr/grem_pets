// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
package net.gremlinarvinr.gremlinpets.entity.client;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.gremlinarvinr.gremlinpets.entity.animations.ModAnimationDefinitions;
import net.gremlinarvinr.gremlinpets.entity.custom.RatEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class RatModel<T extends RatEntity> extends HierarchicalModel<T> {
	private final ModelPart rat;
	private final ModelPart head;
	public RatModel(ModelPart root) {
		this.rat = root.getChild("rat");
		this.head = rat.getChild("body").getChild("torso").getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition rat = partdefinition.addOrReplaceChild("rat", CubeListBuilder.create(), PartPose.offset(2.75F, 21.0F, -1.0F));

		PartDefinition body = rat.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(2.75F, -1.0F, -1.0F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 0).addBox(-11.5F, -13.0F, 0.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(34, 34).addBox(-11.5F, -11.0F, -8.0F, 12.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 2.0F));

		PartDefinition head = torso.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 42).addBox(-10.5F, -13.0F, -12.0F, 10.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(26, 52).addBox(-9.0F, -10.2F, -18.0F, 7.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(37, 62).addBox(-6.5F, -9.6F, -19.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(46, 52).addBox(-8.5F, -10.6F, -15.0F, 6.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(48, 58).addBox(-8.0F, -11.9F, -14.3F, 5.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(56, 16).addBox(-9.5F, -12.5F, -13.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_eye = head.addOrReplaceChild("right_eye", CubeListBuilder.create().texOffs(62, 30).addBox(-11.1F, -13.2F, -13.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4F, 1.0F, 0.0F));

		PartDefinition left_eye = head.addOrReplaceChild("left_eye", CubeListBuilder.create().texOffs(26, 42).addBox(-3.3F, -12.2F, -13.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_ear = head.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(0, 6).addBox(-13.0F, -16.0F, -9.0F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_ear = head.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -16.0F, -9.0F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail = rat.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 56).addBox(-8.5F, -2.8F, 14.0F, 6.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 24).addBox(-8.0F, -1.3F, 14.2F, 5.0F, 2.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(2.75F, -1.0F, -1.0F));

		PartDefinition right_back_leg = rat.addOrReplaceChild("right_back_leg", CubeListBuilder.create().texOffs(48, 0).addBox(-13.0F, -6.0F, 5.0F, 4.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(2.75F, -1.0F, -1.0F));

		PartDefinition right_back_ankle = right_back_leg.addOrReplaceChild("right_back_ankle", CubeListBuilder.create().texOffs(13, 58).addBox(-12.5F, 1.0F, 8.5F, 3.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_back_foot = right_back_ankle.addOrReplaceChild("right_back_foot", CubeListBuilder.create().texOffs(36, 0).addBox(-12.0F, 3.0F, 4.9F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_back_leg = rat.addOrReplaceChild("left_back_leg", CubeListBuilder.create().texOffs(40, 16).addBox(9.0F, -6.0F, 5.0F, 4.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.25F, -1.0F, -1.0F));

		PartDefinition left_back_ankle = left_back_leg.addOrReplaceChild("left_back_ankle", CubeListBuilder.create().texOffs(26, 33).addBox(9.5F, 1.0F, 8.5F, 3.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_back_foot = left_back_ankle.addOrReplaceChild("left_back_foot", CubeListBuilder.create().texOffs(26, 25).addBox(10.0F, 3.0F, 4.9F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_front_leg = rat.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(0, 32).addBox(-10.0F, -5.2F, -5.7F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -1.0F));

		PartDefinition right_front_arm = right_front_leg.addOrReplaceChild("right_front_arm", CubeListBuilder.create().texOffs(29, 62).addBox(-9.75F, -3.0F, -3.5F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_front_paw = right_front_arm.addOrReplaceChild("right_front_paw", CubeListBuilder.create().texOffs(56, 20).addBox(-9.75F, 2.0F, -5.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_front_leg = rat.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(0, 24).addBox(6.0F, -5.2F, -5.7F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.5F, 0.0F, -1.0F));

		PartDefinition left_front_arm = left_front_leg.addOrReplaceChild("left_front_arm", CubeListBuilder.create().texOffs(47, 0).addBox(7.75F, -3.0F, -3.5F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_front_paw = left_front_arm.addOrReplaceChild("left_front_paw", CubeListBuilder.create().texOffs(36, 8).addBox(7.75F, 2.0F, -5.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(RatEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(entity, netHeadYaw, headPitch, ageInTicks);
		// fix?
		this.animateWalk(ModAnimationDefinitions.RAT_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(entity.idleAnimationState, ModAnimationDefinitions.RAT_IDLE, ageInTicks, 1f);
	}

	private void applyHeadRotation(RatEntity pEntity, float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		rat.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return rat;
	}
}