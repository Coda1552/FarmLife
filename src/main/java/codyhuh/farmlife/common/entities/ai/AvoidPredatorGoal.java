package codyhuh.farmlife.common.entities.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;

public class AvoidPredatorGoal<T extends LivingEntity> extends AvoidEntityGoal<T> {
    private final LivingEntity entity;

    public AvoidPredatorGoal(PathfinderMob goalOwner, Class<T> mobToAvoid, float distance, double speed0, double speed1) {
        super(goalOwner, mobToAvoid, distance, speed0, speed1);
        this.entity = goalOwner;
    }

    @Override
    public boolean canUse() {
        return entity.isBaby() && super.canUse();
    }
}
