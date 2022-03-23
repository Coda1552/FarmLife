package teamdraco.farmlife.common.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CheeseWedgeItem extends Item {

    public CheeseWedgeItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide) {
            if (!entity.getActiveEffects().isEmpty()) {
                for (MobEffectInstance instance : entity.getActiveEffects()) {
                    entity.removeEffect(instance.getEffect());
                }
            }
        }
        return super.finishUsingItem(stack, level, entity);
    }
}
