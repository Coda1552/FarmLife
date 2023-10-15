package codyhuh.farmlife.common.blocks;

import codyhuh.farmlife.common.block_entities.PlatefishPlateBlockEntity;
import codyhuh.farmlife.registry.FLBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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

public class PlatefishPlateBlock extends BaseEntityBlock {

    public PlatefishPlateBlock(Properties p_49224_) {
        super(p_49224_);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return FLBlockEntities.PLATEFISH_PLATE.get().create(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack stack = pPlayer.getItemInHand(pHand);

        if (stack.getItem().isEdible() && pLevel.getBlockEntity(pPos) instanceof PlatefishPlateBlockEntity blockEntity) {
            int itemCount = blockEntity.countItems(blockEntity.getItems());

            if (itemCount < blockEntity.getContainerSize()) {
                pLevel.playSound(pPlayer, pPos, SoundEvents.ITEM_FRAME_PLACE, SoundSource.BLOCKS, 0.35F, 1.0F);

                blockEntity.setItem(itemCount, stack.split(1));

                return InteractionResult.SUCCESS;
            }
            return InteractionResult.SUCCESS;
        }

        // todo - account for soup
        else if (stack.isEmpty() && pLevel.getBlockEntity(pPos) instanceof PlatefishPlateBlockEntity blockEntity) {
            int itemCount = blockEntity.countItems(blockEntity.getItems());

            if (itemCount > 0 && pPlayer.getFoodData().getFoodLevel() < 20) {
                pLevel.playSound(pPlayer, pPos, SoundEvents.GENERIC_EAT, SoundSource.PLAYERS, 1.0F, 1.0F);

                ItemStack toRemove = blockEntity.getItem(itemCount - 1);

                pPlayer.eat(pLevel, toRemove);
                blockEntity.removeItem(itemCount, 1);

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
}
