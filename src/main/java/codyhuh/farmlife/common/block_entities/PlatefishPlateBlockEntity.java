package codyhuh.farmlife.common.block_entities;

import codyhuh.farmlife.FarmLife;
import codyhuh.farmlife.registry.FLBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class PlatefishPlateBlockEntity extends BlockEntity {
    private final NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);
    private ItemStack item = ItemStack.EMPTY;
    private float rotation = 0.0F;

    public PlatefishPlateBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(FLBlockEntities.PLATEFISH_PLATE.get(), p_155229_, p_155230_);
    }

    public float getRotation() {
        return rotation;
    }

    public int countItems(List<ItemStack> stacks) {
        List<ItemStack> currentStacks = new ArrayList<>();

        for (ItemStack stack : stacks) {
            if (!stack.isEmpty()) {
                currentStacks.add(stack);
            }
        }

        return currentStacks.size();
    }

    public NonNullList<ItemStack> getItems() {
        return items;
    }

    public ItemStack getItem() {
        return item;
    }

    public void removeItem() {
        rotation = 0.0F;
        item = ItemStack.EMPTY;
    }

    public void setItem(ItemStack stack) {
        item = stack;
    }

    public void setRotation(float rot) {
        this.rotation = rot;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        setRotation(tag.getFloat("Rotation"));
        if (tag.contains("Item")) {
            setItem(ItemStack.of(tag.getCompound("Item")));
        }
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("Item", this.item.save(new CompoundTag()));
        tag.putFloat("Rotation", rotation);
    }
}
