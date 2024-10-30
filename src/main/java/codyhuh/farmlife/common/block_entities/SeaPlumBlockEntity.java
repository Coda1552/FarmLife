package codyhuh.farmlife.common.block_entities;

import codyhuh.farmlife.common.entities.item.SeaPlumFruitEntity;
import codyhuh.farmlife.registry.FLBlockEntities;
import codyhuh.farmlife.registry.FLEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.extensions.IForgeBlockEntity;

public class SeaPlumBlockEntity extends BlockEntity implements IForgeBlockEntity {
    public NonNullList<SeaPlumFruitEntity> fruitEntities = NonNullList.create();
    private final int maxFruits = 3;
    private int fruitCount = 0;

    public SeaPlumBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(FLBlockEntities.SEA_PLUM.get(), pPos, pBlockState);
    }

    public void addFruitEntity(Level level, BlockPos pos) {
        if (getFruitCount() <= getMaxFruit()) {
            SeaPlumFruitEntity plum = new SeaPlumFruitEntity(getBlockPos(), level);
            plum.moveTo(pos.getCenter().add(0.0D,-0.5D,0.0D));

            fruitEntities.add(plum);

            level.addFreshEntity(plum);
        }
    }

    public int getFruitEntityCount() {
        return fruitEntities.size();
    }

    public boolean hasFruitEntity() {
        return !fruitEntities.isEmpty();
    }

    public void addFruit(Level level, BlockPos pos, int amount) {
        setFruitCount(Math.min(getMaxFruit(), getFruitCount() + amount));
    }

    public void removeFruit(int amount) {
        setFruitCount(Math.max(0, getFruitCount() - amount));
    }

    public void setFruitCount(int count) {
        fruitCount = count;
    }

    public int getFruitCount() {
        return fruitCount;
    }

    public int getMaxFruit() {
        return maxFruits;
    }

    public boolean hasFruit() {
        return getFruitCount() > 0;
    }

    @Override
    public void load(CompoundTag tag) {
        setFruitCount(tag.getInt("FruitCount"));
        super.load(tag);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        tag.putInt("FruitCount", getFruitCount());
        super.saveAdditional(tag);
    }
}
