package codyhuh.farmlife.common.entities.item;

import codyhuh.farmlife.common.block_entities.SeaPlumBlockEntity;
import codyhuh.farmlife.registry.FLEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

// Some code adapted from Endergetic Expansion
public class SeaPlumFruitEntity extends Entity {
    private static final EntityDataAccessor<BlockPos> DATA_BLOCK_POS = SynchedEntityData.defineId(SeaPlumFruitEntity.class, EntityDataSerializers.BLOCK_POS);

    public SeaPlumFruitEntity(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        setNoGravity(true);
    }

    public SeaPlumFruitEntity(Level world, BlockPos pos, BlockPos origin) {
        this(FLEntities.SEA_PLUM_FRUIT.get(), world);
        float xPos = origin.getX();
        float zPos = origin.getZ();
        float yPos = origin.getY();

        this.setPos(xPos, yPos, zPos);
        this.setBlockPos(pos);

        this.xo = this.getX();
        this.yo = this.getY();
        this.zo = this.getZ();
    }

    @Override
    public void tick() {
        super.tick();

        if (level().getBlockEntity(getBlockPos()) instanceof SeaPlumBlockEntity be && be.getFruitEntityCount() == 0) {
            discard();
        }
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
