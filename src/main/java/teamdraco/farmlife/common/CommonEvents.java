package teamdraco.farmlife.common;

import net.minecraft.core.BlockPos;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamdraco.farmlife.FarmLife;
import teamdraco.farmlife.common.blocks.TribullMilkCauldronBlock;
import teamdraco.farmlife.common.entities.GalliraptorEntity;
import teamdraco.farmlife.registry.FLBlocks;
import teamdraco.farmlife.registry.FLItems;

@Mod.EventBusSubscriber(modid = FarmLife.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonEvents {

    @SubscribeEvent
    public static void cauldronInteract(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getPlayer();
        Level level = event.getWorld();
        BlockPos pos = event.getPos();
        InteractionHand hand = event.getHand();

        if (player.getItemInHand(hand).is(FLItems.TRIBULL_MILK.get()) && level.getBlockState(pos).is(Blocks.CAULDRON)) {
            level.setBlock(pos, FLBlocks.TRIBULL_MILK_CAULDRON.get().defaultBlockState(), 2);
            if (!player.isCreative()) {
                player.setItemInHand(hand, new ItemStack(Items.BUCKET));
            }
        }
        if (player.getItemInHand(hand).isEmpty() && level.getBlockState(pos).is(FLBlocks.TRIBULL_MILK_CAULDRON.get()) && level.getBlockState(pos).getValue(TribullMilkCauldronBlock.STAGE) == 3) {
            level.setBlock(pos, Blocks.CAULDRON.defaultBlockState(), 2);

            if (!player.getInventory().add(new ItemStack(FLBlocks.TRIBULL_CHEESE_WHEEL.get()))) {
                player.drop(new ItemStack(FLBlocks.TRIBULL_CHEESE_WHEEL.get()), false);
            }
            player.swing(hand);
        }
    }

    @SubscribeEvent
    public static void spawnEntity(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Ocelot ocelot) {
            ocelot.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>((PathfinderMob) entity, GalliraptorEntity.class, true));
        }
        if (entity instanceof Fox fox) {
            fox.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>((PathfinderMob) entity, GalliraptorEntity.class, true));
        }
    }
}
