package teamdraco.wcfarmlife.client;

import teamdraco.wcfarmlife.WCFarmLife;
import teamdraco.wcfarmlife.client.renderer.DomesticTribullRenderer;
import teamdraco.wcfarmlife.client.renderer.GalliraptorRenderer;
import teamdraco.wcfarmlife.registry.WCFarmLifeBlocks;
import teamdraco.wcfarmlife.registry.WCFarmLifeEntities;
import teamdraco.wcfarmlife.items.WCFarmLifeSpawnEggItem;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = WCFarmLife.MOD_ID)
public class ClientEventHandler {

    @OnlyIn(Dist.CLIENT)
    public static void init() {
        RenderingRegistry.registerEntityRenderingHandler(WCFarmLifeEntities.DOMESTIC_TRIBULL.get(), DomesticTribullRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(WCFarmLifeEntities.GALLIRAPTOR.get(), GalliraptorRenderer::new);
        RenderTypeLookup.setRenderLayer(WCFarmLifeBlocks.PEACOCK_BURST_POPPY.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(WCFarmLifeBlocks.ELECTRIC_BURST_POPPY.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(WCFarmLifeBlocks.FANCY_BURST_POPPY.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(WCFarmLifeBlocks.OLIVE_BURST_POPPY.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(WCFarmLifeBlocks.RUSTY_BURST_POPPY.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(WCFarmLifeBlocks.SUNSTREAK_BURST_POPPY.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(WCFarmLifeBlocks.POTTED_PEACOCK_BURST_POPPY.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(WCFarmLifeBlocks.POTTED_ELECTRIC_BURST_POPPY.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(WCFarmLifeBlocks.POTTED_FANCY_BURST_POPPY.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(WCFarmLifeBlocks.POTTED_OLIVE_BURST_POPPY.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(WCFarmLifeBlocks.POTTED_RUSTY_BURST_POPPY.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(WCFarmLifeBlocks.POTTED_SUNSTREAK_BURST_POPPY.get(), RenderType.cutout());
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void itemColors(ColorHandlerEvent.Item event) {
        ItemColors handler = event.getItemColors();
        IItemColor eggColor = (stack, tintIndex) -> ((WCFarmLifeSpawnEggItem) stack.getItem()).getColor(tintIndex);
        for (WCFarmLifeSpawnEggItem e : WCFarmLifeSpawnEggItem.UNADDED_EGGS) handler.register(eggColor, e);
    }
}