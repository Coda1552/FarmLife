package codyhuh.farmlife.common.entities.item;

import codyhuh.farmlife.registry.FLEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.apache.commons.lang3.ObjectUtils;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

// Some code adapted from Endergetic Expansion
public class SeaPlumFruit extends Entity {
    private static final EntityDataAccessor<BlockPos> DATA_BLOCK_POS = SynchedEntityData.defineId(SeaPlumFruit.class, EntityDataSerializers.BLOCK_POS);
    public float factor = 0.5F;

    public SeaPlumFruit(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        setNoGravity(true);
    }

    public SeaPlumFruit(Level world, BlockPos pos, BlockPos origin, float factor) {
        this(FLEntities.SEA_PLUM_FRUIT.get(), world);
        float xPos = origin.getX();
        float zPos = origin.getZ();
        float yPos = origin.getY();

        this.setPos(xPos, yPos, zPos);
        this.setBlockPos(pos);

        this.xo = this.getX();
        this.yo = this.getY();
        this.zo = this.getZ();
        this.factor = factor;
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(DATA_BLOCK_POS, BlockPos.ZERO);
    }

    public void setBlockPos(BlockPos pPos) {
        this.entityData.set(DATA_BLOCK_POS, pPos);
    }

    public BlockPos getBlockPos() {
        return this.entityData.get(DATA_BLOCK_POS);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        int i = pCompound.getInt("BlockPosX");
        int j = pCompound.getInt("BlockPosY");
        int k = pCompound.getInt("BlockPosZ");
        this.setBlockPos(new BlockPos(i, j, k));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putInt("BlockPosX", this.getBlockPos().getX());
        pCompound.putInt("BlockPosY", this.getBlockPos().getY());
        pCompound.putInt("BlockPosZ", this.getBlockPos().getZ());
    }
}
