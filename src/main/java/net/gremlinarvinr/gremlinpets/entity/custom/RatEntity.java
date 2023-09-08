package net.gremlinarvinr.gremlinpets.entity.custom;

import net.gremlinarvinr.gremlinpets.entity.ModEntities;
import net.gremlinarvinr.gremlinpets.entity.variant.RatEyeColor;
import net.gremlinarvinr.gremlinpets.entity.variant.RatVariant;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;
import net.minecraftforge.event.ForgeEventFactory;
import org.jetbrains.annotations.Nullable;

// implements VariantHolder
public class RatEntity extends TamableAnimal implements IEntityAdditionalSpawnData {
    // inventory, drop equipment
    // stats: health, strength for container ! speed ?
    // beg goal (custom)
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT =
            SynchedEntityData.defineId(RatEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_EYE_COLOR =
            SynchedEntityData.defineId(RatEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<String> DATA_GENETIC_CODE =
            SynchedEntityData.defineId(RatEntity.class, EntityDataSerializers.STRING);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS, Items.CARROT, Items.POTATO, Items.BAKED_POTATO, Items.COD, Items.SALMON, Items.COOKED_SALMON, Items.COOKED_COD, Items.APPLE);

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE_VARIANT, 0);
        this.entityData.define(DATA_ID_TYPE_EYE_COLOR, 0);
        this.entityData.define(DATA_GENETIC_CODE, "0"); // wild type here
    }
    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.entityData.set(DATA_ID_TYPE_VARIANT, pCompound.getInt("Variant"));
        this.entityData.set(DATA_ID_TYPE_EYE_COLOR, pCompound.getInt("EyeColor"));
        // genetic stuff here?
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("Variant", this.getTypeVariant());
        pCompound.putInt("EyeColor", this.getTypeEyeColor());
        // add genetic stuff here
    }

    // genetics
    private final String geneColor = "00";

    // data
    public RatEntity(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes().add(Attributes.MAX_HEALTH, 8)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.FOLLOW_RANGE, 24d)
                .add(Attributes.ARMOR_TOUGHNESS, 0d);
    }
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0d));
        this.goalSelector.addGoal(3, new FollowParentGoal(this, 1.1d));
        this.goalSelector.addGoal(5, new FollowOwnerGoal(this, 1.0d, 10.0f, 2.0f, false));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0d));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 4f));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        // beg goal
    }

    // look up camel class :3
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public final AnimationState sitAnimationState = new AnimationState();

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }

        if(this.isInSittingPose()) {
            sitAnimationState.startIfStopped(this.tickCount);
        } else {
            sitAnimationState.stop();
        }
    }

    protected void updateWalkAnimation(float v) {
        float f;
        if (this.getPose() == Pose.STANDING) {
            f = Math.min(v * 6.0F, 1.0F);
        } else {
            f = 0.0F;
        }

        this.walkAnimation.update(f, 0.2F);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }

    /* VARIANTS */
    public RatVariant getVariant() {
        return RatVariant.byId(this.getTypeVariant() & 255);
    }

    private int getTypeVariant() {
        return this.entityData.get(DATA_ID_TYPE_VARIANT);
    }

    private void setVariant(RatVariant variant) {
        this.entityData.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    /* EYE COLORS */
    public RatEyeColor getRatEyeColor() {
        return RatEyeColor.byId(this.getTypeEyeColor() & 255);
    }

    private int getTypeEyeColor() {
        return this.entityData.get(DATA_ID_TYPE_EYE_COLOR);
    }

    private void setEyeColor(RatEyeColor eyeColor) {
        this.entityData.set(DATA_ID_TYPE_EYE_COLOR, eyeColor.getId() & 255);
    }

    /* SPAWNING & BREEDING */

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason,
                                        @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        // stuff here

        RatVariant variant = Util.getRandom(RatVariant.values(), this.random);
        this.setVariant(variant);

        // eye color
        RatEyeColor eyeColor = Util.getRandom(RatEyeColor.values(), this.random);
        this.setEyeColor(eyeColor);

        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }


    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return ModEntities.RAT.get().create(pLevel);
        // genetics
    }

    @Override
    public void writeSpawnData(FriendlyByteBuf buffer) {

    }

    @Override
    public void readSpawnData(FriendlyByteBuf additionalData) {

    }

    /* TAMABLE */

    public InteractionResult interactAt(Player pPlayer, Vec3 pVec, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        Item item = itemstack.getItem();

        Item geneticsExaminer = Items.BOOK;
        Item generalExaminer = Items.BOOK;
        Item tameItem = Items.WHEAT_SEEDS;

        if (item == tameItem && !isTame()) {
            if(this.level().isClientSide()) {
                return InteractionResult.CONSUME;
            } else {
                if (!pPlayer.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }

                if (!ForgeEventFactory.onAnimalTame(this, pPlayer)) {
                    super.tame(pPlayer);
                    this.navigation.recomputePath();
                    this.setTarget(null);
                    this.level().broadcastEntityEvent(this, (byte)7);
                    spawnTamingParticles(true); // taming particles
                    setOrderedToSit(true);
                    this.setInSittingPose(true);
                }

                return InteractionResult.SUCCESS;
            }
        }

        if (isTame() && pHand == InteractionHand.MAIN_HAND && !isFood(itemstack)) {
            setOrderedToSit(!isOrderedToSit());
            setInSittingPose(!isOrderedToSit());

            return InteractionResult.SUCCESS;
        }

        // healing from other food items

        // examines the genetics
        if (pHand == InteractionHand.MAIN_HAND && item == geneticsExaminer) {
            // to string
            pPlayer.sendSystemMessage(Component.literal("Examined."));  // WIP sends twice BUG
            return InteractionResult.SUCCESS;
        }

        return super.interactAt(pPlayer, pVec, pHand);
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(Items.MELON_SLICE);
    }
}
