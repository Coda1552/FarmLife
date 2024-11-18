package codyhuh.farmlife.common.blocks;

import codyhuh.farmlife.common.block_entities.PlatterBlockEntity;
import codyhuh.farmlife.registry.FLBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class PlatterBlock extends BaseEntityBlock {

    public PlatterBlock(Properties p_49224_) {
        super(p_49224_);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return FLBlockEntities.PLATTER.get().create(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack stack = pPlayer.getItemInHand(pHand);

        if (stack.getItem().isEdible() && pLevel.getBlockEntity(pPos) instanceof PlatterBlockEntity blockEntity) {
            if (blockEntity.getItem().isEmpty()) {
                pLevel.playSound(pPlayer, pPos, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS, 0.35F, 1.0F);
                blockEntity.setItem(stack.split(1));
                blockEntity.setRotation(pPlayer.getYRot());
            }

            return InteractionResult.SUCCESS;
        }
        else if (stack.isEmpty() && pLevel.getBlockEntity(pPos) instanceof PlatterBlockEntity blockEntity) {
            ItemStack toRemove = blockEntity.getItem();

            if (!blockEntity.getItem().isEmpty()) {
                if (!pPlayer.isSecondaryUseActive()) {
                    pPlayer.getInventory().add(toRemove);
                    pLevel.playSound(pPlayer, pPos, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 0.35F, 1.0F);
                    blockEntity.removeItem();
                }
                else if (pPlayer.getFoodData().getFoodLevel() < 20) {
                    // todo - particles
                    pLevel.playSound(pPlayer, pPos, SoundEvents.GENERIC_EAT, SoundSource.PLAYERS, 1.0F, 1.0F);
                    ItemEntity itemEntity = EntityType.ITEM.create(pLevel);
                    itemEntity.setItem(toRemove.finishUsingItem(pLevel, pPlayer));
                    itemEntity.moveTo(pPos.getX() + 0.5F, pPos.getY() + 0.5F, pPos.getZ() + 0.5F);

                    pPlayer.eat(pLevel, toRemove);

                    pLevel.addFreshEntity(itemEntity);
                    blockEntity.removeItem();
                }
                return InteractionResult.SUCCESS;

            }
            return InteractionResult.SUCCESS;
        }
        else {
            return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
        }
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return Shapes.box(0.125, 0, 0.125, 0.875, 0.0625, 0.875);
    }

    @Override
    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            BlockEntity be = worldIn.getBlockEntity(pos);
            if (be instanceof Container) {
                Containers.dropContents(worldIn, pos, (Container)be);
                worldIn.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(state, worldIn, pos, newState, isMoving);
        }
    }
}
