package teamdraco.farmlife.common.items;

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
        entity.curePotionEffects(stack);

        return super.finishUsingItem(stack, level, entity);
    }
}
