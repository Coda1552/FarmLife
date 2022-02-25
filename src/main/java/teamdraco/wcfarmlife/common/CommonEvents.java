package teamdraco.wcfarmlife.common;

import teamdraco.wcfarmlife.WCFarmLife;
import teamdraco.wcfarmlife.common.entities.GalliraptorEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WCFarmLife.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonEvents {

    @SubscribeEvent
    public static void spawnEntity(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof OcelotEntity) {
            ((OcelotEntity) entity).targetSelector.addGoal(0, new NearestAttackableTargetGoal<>((MobEntity) entity, GalliraptorEntity.class, true));
        }
        if (entity instanceof FoxEntity) {
            ((FoxEntity) entity).targetSelector.addGoal(0, new NearestAttackableTargetGoal<>((MobEntity) entity, GalliraptorEntity.class, true));
        }
    }
}
