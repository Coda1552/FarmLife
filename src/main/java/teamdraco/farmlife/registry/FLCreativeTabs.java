package teamdraco.farmlife.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import teamdraco.farmlife.FarmLife;

public class FLCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FarmLife.MOD_ID);

    public static final RegistryObject<CreativeModeTab> FL_TAB = CREATIVE_TABS.register("fl_tab", () -> CreativeModeTab.builder().icon(() -> new ItemStack(FLItems.TRIBULL_CHEESE_WEDGE.get()))
            .title(Component.translatable("itemGroup.farmlife"))
            .displayItems((pParameters, pOutput) -> {
                for (var item : FLItems.ITEMS.getEntries()) {
                    pOutput.accept(item.get());
                }
            }).build());
}