package codyhuh.farmlife.common.blocks;

import codyhuh.farmlife.registry.FLItems;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.RegistryObject;

public class ImmatureBurstPoppyBlock extends FlowerBlock {
    private final RegistryObject<Block> matureFlower;
    private final Item dye1;
    private final Item dye2;

    public ImmatureBurstPoppyBlock(RegistryObject<Block> matureFlower, Item dye1, Item dye2, Properties pProperties) {
        super(() -> MobEffects.HARM, 1, pProperties);
        this.matureFlower = matureFlower;
        this.dye1 = dye1;
        this.dye2 = dye2;
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        pLevel.playLocalSound(pPos, SoundEvents.FIREWORK_ROCKET_BLAST, SoundSource.BLOCKS, 1.0F, 1.0F, false);

        popResource(pLevel, pPos, new ItemStack(dye1, pLevel.random.nextIntBetweenInclusive(1, 2)));
        popResource(pLevel, pPos, new ItemStack(dye2, pLevel.random.nextIntBetweenInclusive(1, 2)));
        popResource(pLevel, pPos, new ItemStack(FLItems.BURST_POPPY_SEEDS.get(), pLevel.random.nextIntBetweenInclusive(1, 3)));

        if (pLevel instanceof ServerLevel server) {
            double d2 = pLevel.random.nextGaussian() * 0.2D;
            double d3 = pLevel.random.nextGaussian() * 0.2D;
            double d4 = pLevel.random.nextGaussian() * 0.2D;
            Vec3 pos = pPos.getCenter();

            double d6 = pos.x();
            double d7 = pos.y() + 0.3D;
            double d8 = pos.z();
            server.sendParticles(ParticleTypes.SMOKE, d6, d7, d8, 100, d2, d3, d4, 0.25D);
        }

        pLevel.setBlock(pPos, matureFlower.get().defaultBlockState(), 2);

        return InteractionResult.SUCCESS;
    }
}
