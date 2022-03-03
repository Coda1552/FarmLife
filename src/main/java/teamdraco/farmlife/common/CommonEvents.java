package teamdraco.farmlife.common;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamdraco.farmlife.FarmLife;
import teamdraco.farmlife.common.entities.GalliraptorEntity;

@Mod.EventBusSubscriber(modid = FarmLife.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonEvents {

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
