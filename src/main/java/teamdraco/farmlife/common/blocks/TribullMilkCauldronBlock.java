package teamdraco.farmlife.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.HitResult;

public class TribullMilkCauldronBlock extends AbstractCauldronBlock {
    public static final IntegerProperty STAGE = IntegerProperty.create("stage", 1, 3);

    public TribullMilkCauldronBlock(BlockBehaviour.Properties p_153498_) {
        super(p_153498_, CauldronInteraction.EMPTY);
        this.registerDefaultState(this.stateDefinition.any().setValue(STAGE, 1));
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        return new ItemStack(Items.CAULDRON);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_57799_) {
        p_57799_.add(STAGE);
    }

    protected double getContentHeight(BlockState p_153500_) {
        return 0.9375D;
    }

    public boolean isFull(BlockState p_153511_) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
        if (state.getValue(STAGE) < 3 && rand.nextFloat() > 0.65F) {
            level.setBlock(pos, state.setValue(STAGE, state.getValue(STAGE) + 1), 2);
            level.playSound(null, pos, SoundEvents.MOOSHROOM_MILK_SUSPICIOUSLY, SoundSource.BLOCKS, 1.0F, 1.0F);
        }
    }

    @Override
    public boolean isRandomlyTicking(BlockState p_49921_) {
        return p_49921_.getValue(STAGE) < 3;
    }
}