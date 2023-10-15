package codyhuh.farmlife.common.items;

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
            if (entity.getActiveEffects().stream().findFirst().isPresent()) {
                entity.removeEffect(entity.getActiveEffects().stream().findFirst().get().getEffect());
            }
        }
        return super.finishUsingItem(stack, level, entity);
    }
}
