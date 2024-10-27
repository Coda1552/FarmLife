package codyhuh.farmlife.common.block_entities;

import codyhuh.farmlife.common.entities.item.SeaPlumFruitEntity;
import codyhuh.farmlife.registry.FLBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.world.entity.EntityType;
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
            plum.moveTo(plum.getBlockPos().getCenter().add(0.0D,-0.5D,0.0D));

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
    public void onLoad() {
        setFruitCount(data.getInt("FruitCount"));

        for (int i = 0; i < getFruitCount(); ++i) {
            ListTag list = data.getList("FruitEntities", 10);

            CompoundTag compound = list.getCompound(i);

            var entity = EntityType.create(compound, getLevel()).get();

            if (entity instanceof SeaPlumFruitEntity fruit) {
                fruitEntities.add(i, fruit);
                fruit.setBlockPos(getBlockPos());
                // todo - sea plums exist but are not "there" after relogging
            }
        }
    }

    @Override
    public void load(CompoundTag tag) {
        data = tag.copy();
        super.load(data);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        tag.putInt("FruitCount", getFruitCount());

        if (!tag.contains("FruitEntities")) {
            tag.put("FruitEntities", new ListTag());
        }

        ListTag listTag = tag.getList("FruitEntities", 10);

        for (int i = 0; i < getFruitCount(); i++) {
            listTag.add(i, fruitEntities.get(i).serializeNBT());
        }

        data = tag.copy();
        super.saveAdditional(data);
    }
}
