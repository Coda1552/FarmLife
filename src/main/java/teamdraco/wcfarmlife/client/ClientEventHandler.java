package teamdraco.wcfarmlife.client;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;
import teamdraco.wcfarmlife.WCFarmLife;
import teamdraco.wcfarmlife.client.renderer.DomesticTribullRenderer;
import teamdraco.wcfarmlife.client.renderer.GalliraptorRenderer;
import teamdraco.wcfarmlife.registry.WCFarmLifeBlocks;
import teamdraco.wcfarmlife.registry.WCFarmLifeEntities;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = WCFarmLife.MOD_ID)
public class ClientEventHandler {

    @OnlyIn(Dist.CLIENT)
    public static void init() {
        RenderingRegistry.registerEntityRenderingHandler(WCFarmLifeEntities.DOMESTIC_TRIBULL.get(), DomesticTribullRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(WCFarmLifeEntities.GALLIRAPTOR.get(), GalliraptorRenderer::new);
        ItemBlockRenderTypes.setRenderLayer(WCFarmLifeBlocks.PEACOCK_BURST_POPPY.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WCFarmLifeBlocks.ELECTRIC_BURST_POPPY.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WCFarmLifeBlocks.FANCY_BURST_POPPY.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WCFarmLifeBlocks.OLIVE_BURST_POPPY.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WCFarmLifeBlocks.RUSTY_BURST_POPPY.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WCFarmLifeBlocks.SUNSTREAK_BURST_POPPY.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WCFarmLifeBlocks.POTTED_PEACOCK_BURST_POPPY.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WCFarmLifeBlocks.POTTED_ELECTRIC_BURST_POPPY.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WCFarmLifeBlocks.POTTED_FANCY_BURST_POPPY.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WCFarmLifeBlocks.POTTED_OLIVE_BURST_POPPY.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WCFarmLifeBlocks.POTTED_RUSTY_BURST_POPPY.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WCFarmLifeBlocks.POTTED_SUNSTREAK_BURST_POPPY.get(), RenderType.cutout());
    }
}