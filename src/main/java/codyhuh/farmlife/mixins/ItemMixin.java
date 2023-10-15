package codyhuh.farmlife.mixins;

import codyhuh.farmlife.registry.FLItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {

    @Inject(method = "isValidRepairItem(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;)Z", at = @At(value = "HEAD", target = "Lnet/minecraft/world/item/Item;isValidRepairItem(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;)Z"), cancellable = true)
    private void isValidRepairItemMixin(ItemStack stack, ItemStack repairItemStack, CallbackInfoReturnable<Boolean> cir) {
        if (repairItemStack.is(FLItems.PLATEFISH_PLATE.get())) {
            cir.setReturnValue(true);
        }
    }
}
