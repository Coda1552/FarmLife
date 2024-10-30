package codyhuh.farmlife.common.blocks;

import codyhuh.farmlife.registry.FLBlocks;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;

import java.util.List;
import java.util.Map;

public class BurstPoppySproutBlock extends CropBlock {
    protected static final VoxelShape SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 10.0D, 11.0D);

    public BurstPoppySproutBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (!pLevel.isAreaLoaded(pPos, 1))
            return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        if (pLevel.getRawBrightness(pPos, 0) >= 9) {
            int i = this.getAge(pState);
            if (i < this.getMaxAge()) {
                float f = getGrowthSpeed(this, pLevel, pPos);

                if (ForgeHooks.onCropsGrowPre(pLevel, pPos, pState, pRandom.nextInt((int) (25.0F / f) + 1) == 0)) {

                    if (pState.getValue(AGE) == 1) {
                        pLevel.setBlock(pPos, pickState(pLevel, pPos), 2);
                        return;
                    }

                    pLevel.setBlock(pPos, this.getStateForAge(i + 1), 2);
                    ForgeHooks.onCropsGrowPost(pLevel, pPos, pState);
                }
            }
        }
    }

    private BlockState pickState(Level level, BlockPos pos) {
        List<BlockState> immatureStates = List.of(
                FLBlocks.IMMATURE_FANCY_BURST_POPPY.get().defaultBlockState(), FLBlocks.IMMATURE_PEACOCK_BURST_POPPY.get().defaultBlockState(),
                FLBlocks.IMMATURE_OLIVE_BURST_POPPY.get().defaultBlockState(), FLBlocks.IMMATURE_ELECTRIC_BURST_POPPY.get().defaultBlockState(),
                FLBlocks.IMMATURE_SUNSTREAK_BURST_POPPY.get().defaultBlockState(), FLBlocks.IMMATURE_RUSTY_BURST_POPPY.get().defaultBlockState()
        );
        Map<BlockState, BlockState> statesWithImmature = ImmutableMap.of(
                FLBlocks.FANCY_BURST_POPPY.get().defaultBlockState(), FLBlocks.IMMATURE_FANCY_BURST_POPPY.get().defaultBlockState(),
                FLBlocks.PEACOCK_BURST_POPPY.get().defaultBlockState(), FLBlocks.IMMATURE_PEACOCK_BURST_POPPY.get().defaultBlockState(),
                FLBlocks.OLIVE_BURST_POPPY.get().defaultBlockState(), FLBlocks.IMMATURE_OLIVE_BURST_POPPY.get().defaultBlockState(),
                FLBlocks.ELECTRIC_BURST_POPPY.get().defaultBlockState(), FLBlocks.IMMATURE_ELECTRIC_BURST_POPPY.get().defaultBlockState(),
                FLBlocks.SUNSTREAK_BURST_POPPY.get().defaultBlockState(), FLBlocks.IMMATURE_SUNSTREAK_BURST_POPPY.get().defaultBlockState(),
                FLBlocks.RUSTY_BURST_POPPY.get().defaultBlockState(), FLBlocks.IMMATURE_RUSTY_BURST_POPPY.get().defaultBlockState()
        );

        BlockPos.MutableBlockPos surrounding = new BlockPos.MutableBlockPos();

        for(int x = -2; x < 2; x++) {
            for(int y = -1; y < 1; y++) {
                for(int z = -2; z <= 2; z++) {
                    surrounding.setWithOffset(pos, x, y, z);

                    System.out.println(surrounding);
                    if (statesWithImmature.containsKey(level.getBlockState(surrounding))) {
                        return statesWithImmature.get(level.getBlockState(surrounding));
                    }
                }
            }
        }

        return immatureStates.get(level.random.nextInt(immatureStates.size()));
    }

    public void growCrops(Level pLevel, BlockPos pPos, BlockState pState) {
        int i = this.getAge(pState) + 1;

        if (pState.getValue(AGE) == 1) {
            pLevel.setBlock(pPos, FLBlocks.IMMATURE_ELECTRIC_BURST_POPPY.get().defaultBlockState(), 2);
            return;
        }

        pLevel.setBlock(pPos, this.getStateForAge(i), 2);
    }

    @Override
    public int getMaxAge() {
        return 2;
    }
}
