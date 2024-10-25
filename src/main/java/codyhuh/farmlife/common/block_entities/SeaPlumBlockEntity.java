package codyhuh.farmlife.common.block_entities;

import codyhuh.farmlife.common.blocks.SeaPlumBlock;
import codyhuh.farmlife.common.entities.item.SeaPlumFruit;
import codyhuh.farmlife.registry.FLBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;

public class SeaPlumBlockEntity extends BlockEntity {
    private final int maxFruits = 3;
    private int fruitCount = 0;
    public NonNullList<SeaPlumFruit> fruitEntities = NonNullList.create();

    public SeaPlumBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(FLBlockEntities.SEA_PLUM.get(), pPos, pBlockState);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, SeaPlumBlockEntity be) {
        
    }

    public void addFruitEntity(int amount, BlockPos pos) {
        if (getFruitEntityCount() < getMaxFruit()) {
            for (int i = 0; i < amount; i++) {
                RandomSource rand = RandomSource.create();

                SeaPlumFruit plum = new SeaPlumFruit(level, pos, pos, rand.nextFloat());
                plum.moveTo(plum.position().add(0.5D,0.0D,0.5D));

                level.addFreshEntity(plum);

                fruitEntities.add(plum);
            }
        }
    }

    public int getFruitEntityCount() {
        return fruitEntities.size();
    }

    public boolean hasFruitEntity() {
        return !fruitEntities.isEmpty();
    }

    public void addFruit(int amount) {
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
        super.load(tag);
        setFruitCount(tag.getInt("FruitCount"));

        if (tag.contains("FruitEntities", 10)) {
            ListTag listTag = tag.getList("FruitEntities", 10);

            for (int i = 0; i < getFruitEntityCount(); i++) {
                fruitEntities.get(i).deserializeNBT(tag);
            }
        }
    }

    @Override
    public boolean isRemoved() {
        return super.isRemoved();
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("FruitCount", getFruitCount());

        if (!tag.contains("FruitEntities")) {
            tag.put("FruitEntities", new ListTag());
        }

        ListTag listTag = tag.getList("FruitEntities", 10);

        for (int i = 0; i < getFruitEntityCount(); i++) {
            listTag.add(i, fruitEntities.get(i).serializeNBT());
        }
    }
}
