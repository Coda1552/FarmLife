package codyhuh.farmlife.common.entities.item;

import codyhuh.farmlife.common.block_entities.SeaPlumBlockEntity;
import codyhuh.farmlife.registry.FLEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ClientboundAddExperienceOrbPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

// Some code adapted from Endergetic Expansion
public class SeaPlumFruitEntity extends LivingEntity {
    private static final EntityDataAccessor<BlockPos> DATA_BLOCK_POS = SynchedEntityData.defineId(SeaPlumFruitEntity.class, EntityDataSerializers.BLOCK_POS);

    public SeaPlumFruitEntity(EntityType<? extends LivingEntity> pEntityType, Level pLevel) {
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

    public static AttributeSupplier.Builder createAttributes() {
        return createLivingAttributes().add(Attributes.MAX_HEALTH, 1.0D);
    }

    @Override
    public void tick() {
        super.tick();

        if (level().getBlockEntity(getBlockPos()) instanceof SeaPlumBlockEntity be) {
            if (be.getFruitCount() == 0) discard();
            if (isRemoved()) be.removeFruit(1);
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_BLOCK_POS, BlockPos.ZERO);
    }

    public void setBlockPos(BlockPos pPos) {
        this.entityData.set(DATA_BLOCK_POS, pPos);
    }

    public BlockPos getBlockPos() {
        return this.entityData.get(DATA_BLOCK_POS);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        int i = pCompound.getInt("BlockPosX");
        int j = pCompound.getInt("BlockPosY");
        int k = pCompound.getInt("BlockPosZ");
        this.setBlockPos(new BlockPos(i, j, k));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("BlockPosX", this.getBlockPos().getX());
        pCompound.putInt("BlockPosY", this.getBlockPos().getY());
        pCompound.putInt("BlockPosZ", this.getBlockPos().getZ());
    }

    @Override
    public Iterable<ItemStack> getArmorSlots() {
        return NonNullList.withSize(0, ItemStack.EMPTY);
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        return false;
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    public ItemStack getItemBySlot(EquipmentSlot pSlot) {
        return ItemStack.EMPTY;
    }

    @Override
    public void setItemSlot(EquipmentSlot pSlot, ItemStack pStack) {
    }

    @Override
    public HumanoidArm getMainArm() {
        return HumanoidArm.RIGHT;
    }

    @Override
    public boolean attackable() {
        return false;
    }

    @Override
    public boolean skipAttackInteraction(Entity pEntity) {
        return true;
    }
}
