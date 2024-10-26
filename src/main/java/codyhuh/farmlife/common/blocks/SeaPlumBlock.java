package codyhuh.farmlife.common.blocks;

import codyhuh.farmlife.common.block_entities.SeaPlumBlockEntity;
import codyhuh.farmlife.registry.FLBlockEntities;
import codyhuh.farmlife.registry.FLItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;

import javax.annotation.Nullable;

public class SeaPlumBlock extends BushBlock implements EntityBlock, BonemealableBlock, SimpleWaterloggedBlock, LiquidBlockContainer {
    private static final VoxelShape SAPLING_SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D);
    private static final VoxelShape MID_GROWTH_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
    public static final IntegerProperty AGE = BlockStateProperties.AGE_1;

    public SeaPlumBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState p_153213_, BlockEntityType<T> type) {
        return seaPlumTicker(level, type, FLBlockEntities.SEA_PLUM.get());
    }

    public static <T extends BlockEntity> BlockEntityTicker<T> seaPlumTicker(Level p_151988_, BlockEntityType<T> p_151989_, BlockEntityType<? extends SeaPlumBlockEntity> p_151990_) {
        return createTickerHelper(p_151989_, p_151990_, SeaPlumBlockEntity::serverTick);
    }

    @Nullable
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> pServerType, BlockEntityType<E> pClientType, BlockEntityTicker<? super E> pTicker) {
        return pClientType == pServerType ? (BlockEntityTicker<A>)pTicker : null;
    }

    public ItemStack getCloneItemStack(BlockGetter pLevel, BlockPos pPos, BlockState pState) {
        return new ItemStack(FLItems.SEA_PLUM.get());
    }

    @Override
    public boolean triggerEvent(BlockState pState, Level pLevel, BlockPos pPos, int pId, int pParam) {
        BlockEntity blockentity = pLevel.getBlockEntity(pPos);
        return blockentity != null && blockentity.triggerEvent(pId, pParam);
    }

    protected boolean mayPlaceOn(BlockState p_154539_, BlockGetter p_154540_, BlockPos p_154541_) {
        return p_154539_.isFaceSturdy(p_154540_, p_154541_, Direction.UP) && !p_154539_.is(Blocks.MAGMA_BLOCK);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext p_154503_) {
        FluidState fluidstate = p_154503_.getLevel().getFluidState(p_154503_.getClickedPos());
        return fluidstate.is(FluidTags.WATER) && fluidstate.getAmount() == 8 ? super.getStateForPlacement(p_154503_) : null;
    }

    public boolean canPlaceLiquid(BlockGetter p_154505_, BlockPos p_154506_, BlockState p_154507_, Fluid p_154508_) {
        return false;
    }

    public boolean placeLiquid(LevelAccessor p_154520_, BlockPos p_154521_, BlockState p_154522_, FluidState p_154523_) {
        return false;
    }

    public FluidState getFluidState(BlockState p_154537_) {
        return Fluids.WATER.getSource(false);
    }

    public BlockState updateShape(BlockState p_154530_, Direction p_154531_, BlockState p_154532_, LevelAccessor p_154533_, BlockPos p_154534_, BlockPos p_154535_) {
        BlockState blockstate = super.updateShape(p_154530_, p_154531_, p_154532_, p_154533_, p_154534_, p_154535_);
        if (!blockstate.isAir()) {
            p_154533_.scheduleTick(p_154534_, Fluids.WATER, Fluids.WATER.getTickDelay(p_154533_));
        }

        return blockstate;
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return pState.getValue(AGE) == 0 ? SAPLING_SHAPE : MID_GROWTH_SHAPE;
    }

    public boolean isRandomlyTicking(BlockState pState) {
        return pState.getValue(AGE) < 1;
    }

    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        int i = pState.getValue(AGE);
        if (i < 1 && pLevel.getRawBrightness(pPos.above(), 0) >= 9 && ForgeHooks.onCropsGrowPre(pLevel, pPos, pState, pRandom.nextInt(5) == 0)) {
            BlockState blockstate = pState.setValue(AGE, Integer.valueOf(i + 1));
            pLevel.setBlock(pPos, blockstate, 2);
            pLevel.gameEvent(GameEvent.BLOCK_CHANGE, pPos, GameEvent.Context.of(blockstate));
            ForgeHooks.onCropsGrowPost(pLevel, pPos, pState);
        }
    }

    private boolean canGrow(LevelReader level, BlockPos pos) {
        return level.getFluidState(pos.above()).is(FluidTags.WATER);
    }

    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pos, Player player, InteractionHand pHand, BlockHitResult pHit) {
        BlockEntity te = pLevel.getBlockEntity(pos);
        ItemStack held = player.getItemInHand(player.getUsedItemHand());

        if (canGrow(pLevel, pos) && pState.getValue(AGE) > 0 && te instanceof SeaPlumBlockEntity plum) {
            if (held.is(Items.BONE_MEAL) && plum.getFruitCount() < plum.getMaxFruit()) {
                plum.addFruit(pLevel, pos, 1);
                BoneMealItem.applyBonemeal(held, pLevel, pos, player);
                BoneMealItem.addGrowthParticles(pLevel, pos, 3);
                plum.addFruitEntity(pLevel, pos);

                if (!player.getAbilities().instabuild) {
                    held.shrink(1);
                }
            }
            else if (plum.hasFruit() && plum.hasFruitEntity()) {
                pLevel.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + pLevel.random.nextFloat() * 0.4F);
                double d0 = (double) EntityType.ITEM.getHeight() / 2.0D;
                double d1 = (double)pos.getX() + 0.5D + Mth.nextDouble(pLevel.random, -0.25D, 0.25D);
                double d2 = (double)pos.getY() + 0.5D + Mth.nextDouble(pLevel.random, -0.25D, 0.25D) - d0;
                double d3 = (double)pos.getZ() + 0.5D + Mth.nextDouble(pLevel.random, -0.25D, 0.25D);

                for (int i = 0; i < plum.getFruitCount(); i++) {
                    ItemStack plumStack = new ItemStack(FLItems.SEA_PLUM.get());

                    ItemEntity itementity = EntityType.ITEM.create(pLevel);

                    itementity.setItem(plumStack);
                    itementity.moveTo(d1, d2, d3);

                    pLevel.addFreshEntity(itementity);
                }

                plum.fruitEntities.forEach(Entity::discard);
                plum.fruitEntities.clear();
                plum.setFruitCount(0);
            }
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE);
    }

    public boolean isValidBonemealTarget(LevelReader pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        return pState.getValue(AGE) < 1;
    }

    public boolean isBonemealSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        return canGrow(pLevel, pPos);
    }

    public void performBonemeal(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        if (pState.getValue(AGE) > 0) {
            BlockEntity te = pLevel.getBlockEntity(pPos);

            if (te instanceof SeaPlumBlockEntity plum) {
                plum.addFruit(pLevel, pPos, 1);
            }
        }
        else {
            int i = Math.min(1, pState.getValue(AGE) + 1);
            pLevel.setBlock(pPos, pState.setValue(AGE, i), 2);
        }
    }

    @Override
    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            BlockEntity be = worldIn.getBlockEntity(pos);
            if (be instanceof SeaPlumBlockEntity plum && plum.hasFruitEntity()) {
                plum.fruitEntities.forEach(Entity::discard);
                plum.fruitEntities.clear();
            }

            super.onRemove(state, worldIn, pos, newState, isMoving);
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return FLBlockEntities.SEA_PLUM.get().create(pPos, pState);
    }
}
