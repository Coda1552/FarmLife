package teamdraco.farmlife.common.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;
import teamdraco.farmlife.common.entities.ai.AvoidPredatorGoal;
import teamdraco.farmlife.common.entities.ai.GalliraptorTargetGoal;
import teamdraco.farmlife.registry.FLEntities;
import teamdraco.farmlife.registry.FLItems;
import teamdraco.farmlife.registry.FLSounds;

import javax.annotation.Nullable;

public class Galliraptor extends Animal implements IAnimatable {
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(Galliraptor.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> PECKING = SynchedEntityData.defineId(Galliraptor.class, EntityDataSerializers.BOOLEAN);
    private static final Ingredient TEMPTATION_ITEMS = Ingredient.of(Items.MELON, Items.MELON_SEEDS);
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    public int timeUntilNextEgg = this.random.nextInt(8000) + 8000;

    public Galliraptor(EntityType<? extends Galliraptor> type, Level worldIn) {
        super(type, worldIn);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.0D, TEMPTATION_ITEMS, false));
        this.goalSelector.addGoal(2, new AvoidPredatorGoal<>(this, Fox.class, 20.0F, 1.25D, 1.4D));
        this.goalSelector.addGoal(2, new AvoidPredatorGoal<>(this, Ocelot.class, 20.0F, 1.25D, 1.4D));
        this.goalSelector.addGoal(3, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.5D, true));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(0, new GalliraptorTargetGoal<>(this, Fox.class, false));
        this.targetSelector.addGoal(0, new GalliraptorTargetGoal<>(this, Ocelot.class, false));
    }

    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return this.isBaby() ? sizeIn.height * 0.85F : 0.72F;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 12.0D).add(Attributes.MOVEMENT_SPEED, 0.3D).add(Attributes.ATTACK_DAMAGE, 1.5D);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, 0);
    }

    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    public void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);
    }

    public void aiStep() {
        super.aiStep();
        if (!this.level.isClientSide && this.isAlive() && !this.isBaby() && --this.timeUntilNextEgg <= 0) {
            this.playSound(SoundEvents.CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            this.spawnAtLocation(FLItems.GALLIRAPTOR_EGG.get());
            this.timeUntilNextEgg = this.random.nextInt(8000) + 8000;
        }

    }

    public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source) {
        return false;
    }

    protected SoundEvent getAmbientSound() {
        return FLSounds.GALLIRAPTOR_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return FLSounds.GALLIRAPTOR_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return FLSounds.GALLIRAPTOR_DEATH.get();
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.CHICKEN_STEP, 0.15F, 1.0F);
    }

    public Galliraptor getBreedOffspring(ServerLevel world, AgeableMob ageable) {
        Galliraptor entity = FLEntities.GALLIRAPTOR.get().create(this.level);
        if (entity != null) {
            int i = this.getVariant();
            if (this.random.nextInt(5) != 0) {
                if (ageable instanceof Galliraptor && this.random.nextBoolean()) {
                    i = ((Galliraptor) ageable).getVariant();
                }
            }
            entity.setVariant(i);
        }
        return entity;
    }

    public boolean isFood(ItemStack stack) {
        return TEMPTATION_ITEMS.test(stack);
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("EggLayTime")) {
            this.timeUntilNextEgg = compound.getInt("EggLayTime");
        }
        setVariant(compound.getInt("Variant"));
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("EggLayTime", this.timeUntilNextEgg);
        compound.putInt("Variant", getVariant());
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        setVariant(random.nextInt(5));
        return spawnDataIn;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving() && isBaby()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.galliraptor_chick.walk", ILoopType.EDefaultLoopTypes.LOOP));
            event.getController().setAnimationSpeed(2.5);
        }
        else if (!event.isMoving() && isBaby()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.galliraptor_chick.idle", ILoopType.EDefaultLoopTypes.LOOP));
        }
        else if (event.isMoving() && !isBaby() && !isAggressive()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.galliraptor.walk", ILoopType.EDefaultLoopTypes.LOOP));
            event.getController().setAnimationSpeed(2.85);
        }
        else if (isAggressive() && !isBaby()) {
            event.getController().setAnimationSpeed(1.0);
            if (event.isMoving())
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.galliraptor.aggro_walk", ILoopType.EDefaultLoopTypes.LOOP));
            else if (!event.isMoving()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.galliraptor.aggro_idle", ILoopType.EDefaultLoopTypes.LOOP));
            }
        }
        else if (!isBaby() && random.nextInt(250) == 0 && !isAggressive()) {
            if (event.isMoving())
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.galliraptor.pecking_walk", ILoopType.EDefaultLoopTypes.PLAY_ONCE));
            else if (!event.isMoving()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.galliraptor.pecking_idle", ILoopType.EDefaultLoopTypes.PLAY_ONCE));
            }
        }
        else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.galliraptor.idle", ILoopType.EDefaultLoopTypes.LOOP));
        }

        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 2, this::predicate));
    }
}