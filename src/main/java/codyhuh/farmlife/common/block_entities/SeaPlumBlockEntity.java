package codyhuh.farmlife.common.block_entities;

import codyhuh.farmlife.common.entities.item.SeaPlumFruitEntity;
import codyhuh.farmlife.registry.FLBlockEntities;
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
    private CompoundTag data = new CompoundTag();

    public SeaPlumBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(FLBlockEntities.SEA_PLUM.get(), pPos, pBlockState);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, SeaPlumBlockEntity be) {
    }

    public void addFruitEntity(Level level, BlockPos pos) {
        if (getFruitCount() <= getMaxFruit()) {
            SeaPlumFruitEntity plum = new SeaPlumFruitEntity(level, pos, pos);

            //fruitEntities.add(plum);

            plum.moveTo(pos.getCenter().add(0.5D, -0.5D, 0.5D));

            level.addFreshEntity(plum);
        }
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
    public void onLoad() {
        setFruitCount(data.getInt("FruitCount"));

        for(int i = 0; i < data.getInt("FruitCount"); ++i) {
            addFruitEntity(getLevel(), getBlockPos());
        }

        /*for (int i = 0; i < getFruitCount(); ++i) {
            ListTag list = tag.getList("FruitEntities", 10);

            CompoundTag compound = list.getCompound(i);

            addFruitEntity(compound, i, getBlockPos());
        }*/
    }

    @Override
    public void load(CompoundTag tag) {
        //setFruitCount(tag.getInt("FruitCount"));

        data = tag.copy();
        super.load(data);

        // todo- fix sea plums not saving data. maybe bc getLevel() is returning null?
        //for(int i = 0; i < getFruitCount(); ++i) {
        //    addFruitEntity(getLevel(), getBlockPos());
        //}

        /*for (int i = 0; i < getFruitCount(); ++i) {
            ListTag list = tag.getList("FruitEntities", 10);

            CompoundTag compound = list.getCompound(i);

            addFruitEntity(compound, i, getBlockPos());
        }*/
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        tag.putInt("FruitCount", getFruitCount());
        data = tag.copy();

        super.saveAdditional(data);
/*        if (!tag.contains("FruitEntities")) {
            tag.put("FruitEntities", new ListTag());
        }

        ListTag listTag = tag.getList("FruitEntities", 10);

        for (int i = 0; i < getFruitCount(); i++) {
            listTag.add(i, fruitEntities.get(i).serializeNBT());
        }*/
    }
}
