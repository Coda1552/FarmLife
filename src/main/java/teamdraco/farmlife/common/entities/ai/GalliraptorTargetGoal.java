package teamdraco.farmlife.common.entities.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;

public class GalliraptorTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
    private final LivingEntity entity;

    public GalliraptorTargetGoal(Mob goalOwner, Class<T> target, boolean mustSee) {
        super(goalOwner, target, mustSee);
        this.entity = goalOwner;
    }

    @Override
    public boolean canUse() {
        return !entity.isBaby() && super.canUse();
    }
}
