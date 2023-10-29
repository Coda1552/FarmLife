package codyhuh.farmlife.common;

import codyhuh.farmlife.common.entities.Galliraptor;
import codyhuh.farmlife.registry.FLBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import codyhuh.farmlife.FarmLife;
import codyhuh.farmlife.common.blocks.TribullMilkCauldronBlock;
import codyhuh.farmlife.registry.FLItems;

@Mod.EventBusSubscriber(modid = FarmLife.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class FLForgeEvents {

    @SubscribeEvent
    public static void cauldronInteract(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        InteractionHand hand = event.getHand();

        if (player.getItemInHand(hand).is(FLItems.TRIBULL_MILK.get()) && level.getBlockState(pos).is(Blocks.CAULDRON)) {
            level.setBlock(pos, FLBlocks.TRIBULL_MILK_CAULDRON.get().defaultBlockState(), 2);
            level.playSound(null, pos, SoundEvents.MOOSHROOM_MILK_SUSPICIOUSLY, SoundSource.BLOCKS, 1.0F, 1.0F);

            if (!player.isCreative()) {
                player.setItemInHand(hand, new ItemStack(Items.BUCKET));
            }
        }
        if (player.getItemInHand(hand).isEmpty() && level.getBlockState(pos).is(FLBlocks.TRIBULL_MILK_CAULDRON.get()) && level.getBlockState(pos).getValue(TribullMilkCauldronBlock.STAGE) == 3) {
            level.setBlock(pos, Blocks.CAULDRON.defaultBlockState(), 2);
            level.playSound(null, pos, SoundEvents.WET_GRASS_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);

            if (!player.getInventory().add(new ItemStack(FLBlocks.TRIBULL_CHEESE_WHEEL.get()))) {
                player.drop(new ItemStack(FLBlocks.TRIBULL_CHEESE_WHEEL.get()), false);
            }
            player.swing(hand);
        }
    }

    @SubscribeEvent
    public static void spawnEntity(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Ocelot ocelot) {
            ocelot.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>((PathfinderMob) entity, Galliraptor.class, true));
        }
        if (entity instanceof Fox fox) {
            fox.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>((PathfinderMob) entity, Galliraptor.class, true));
        }
    }

}
