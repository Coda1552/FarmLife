package codyhuh.farmlife.common.blocks;

import codyhuh.farmlife.registry.FLParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TribullCheeseWheelBlock extends Block {
   public static final IntegerProperty BITES = IntegerProperty.create("bites", 0, 4);
   protected static final VoxelShape[] SHAPE_BY_BITE = new VoxelShape[]{Block.box(2.0D, 0.0D, 2.0D, 14.0D, 5.0D, 14.0D), Block.box(4.0D, 0.0D, 2.0D, 14.0D, 5.0D, 14.0D), Block.box(6.0D, 0.0D, 2.0D, 14.0D, 5.0D, 14.0D), Block.box(8.0D, 0.0D, 2.0D, 14.0D, 5.0D, 14.0D), Block.box(10.0D, 0.0D, 2.0D, 14.0D, 5.0D, 14.0D)};

   public TribullCheeseWheelBlock(BlockBehaviour.Properties p_51184_) {
      super(p_51184_);
      this.registerDefaultState(this.stateDefinition.any().setValue(BITES, 0));
   }

   @Override
   public VoxelShape getShape(BlockState p_51222_, BlockGetter p_51223_, BlockPos p_51224_, CollisionContext p_51225_) {
      return SHAPE_BY_BITE[p_51222_.getValue(BITES)];
   }

   @Override
   public InteractionResult use(BlockState p_51202_, Level p_51203_, BlockPos p_51204_, Player p_51205_, InteractionHand p_51206_, BlockHitResult p_51207_) {
      ItemStack itemstack = p_51205_.getItemInHand(p_51206_);

      if (p_51203_.isClientSide) {
         if (eat(p_51203_, p_51204_, p_51202_, p_51205_).consumesAction()) {
            return InteractionResult.SUCCESS;
         }

         if (itemstack.isEmpty()) {
            return InteractionResult.CONSUME;
         }
      }

      return eat(p_51203_, p_51204_, p_51202_, p_51205_);
   }

   protected static InteractionResult eat(LevelAccessor p_51186_, BlockPos p_51187_, BlockState p_51188_, Player p_51189_) {
      if (!p_51189_.canEat(false)) {
         return InteractionResult.PASS;
      } else {
         p_51189_.awardStat(Stats.EAT_CAKE_SLICE);
         p_51189_.getFoodData().eat(3, 0.2F);

         if (!p_51189_.level().isClientSide) {
            if (p_51189_.getActiveEffects().stream().findFirst().isPresent()) {
               p_51189_.removeEffect(p_51189_.getActiveEffects().stream().findFirst().get().getEffect());
            }
         }

         int i = p_51188_.getValue(BITES);
         p_51186_.gameEvent(p_51189_, GameEvent.EAT, p_51187_);
         if (i < 4) {
            p_51186_.setBlock(p_51187_, p_51188_.setValue(BITES, i + 1), 3);
         } else {
            p_51186_.removeBlock(p_51187_, false);
            p_51186_.gameEvent(p_51189_, GameEvent.BLOCK_DESTROY, p_51187_);
         }

         return InteractionResult.SUCCESS;
      }
   }

   @Override
   public BlockState updateShape(BlockState p_51213_, Direction p_51214_, BlockState p_51215_, LevelAccessor p_51216_, BlockPos p_51217_, BlockPos p_51218_) {
      return p_51214_ == Direction.DOWN && !p_51213_.canSurvive(p_51216_, p_51217_) ? Blocks.AIR.defaultBlockState() : super.updateShape(p_51213_, p_51214_, p_51215_, p_51216_, p_51217_, p_51218_);
   }

   @Override
   public boolean canSurvive(BlockState p_51209_, LevelReader p_51210_, BlockPos p_51211_) {
      return p_51210_.getBlockState(p_51211_.below()).isSolid();
   }

   @Override
   protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_51220_) {
      p_51220_.add(BITES);
   }

   @Override
   public int getAnalogOutputSignal(BlockState p_51198_, Level p_51199_, BlockPos p_51200_) {
      return getOutputSignal(p_51198_.getValue(BITES));
   }

   public static int getOutputSignal(int p_152747_) {
      return (7 - p_152747_) * 2;
   }

   @Override
   public boolean hasAnalogOutputSignal(BlockState p_51191_) {
      return true;
   }

   @Override
   public boolean isPathfindable(BlockState p_51193_, BlockGetter p_51194_, BlockPos p_51195_, PathComputationType p_51196_) {
      return false;
   }

   @Override
   public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
      if (rand.nextBoolean()) {
         level.addParticle(FLParticles.STINKY.get(), pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, 0.0, 0.0, 0.0);
      }
   }
}