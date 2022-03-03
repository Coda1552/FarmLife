package teamdraco.farmlife.client;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import teamdraco.farmlife.FarmLife;
import teamdraco.farmlife.client.renderer.DomesticTribullRenderer;
import teamdraco.farmlife.client.renderer.GalliraptorRenderer;
import teamdraco.farmlife.registry.FLBlocks;
import teamdraco.farmlife.registry.FLEntities;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = FarmLife.MOD_ID)
public class ClientEvents {

    @SubscribeEvent
    public static void registerClient(FMLClientSetupEvent event) {
        EntityRenderers.register(FLEntities.DOMESTIC_TRIBULL.get(), DomesticTribullRenderer::new);
        EntityRenderers.register(FLEntities.GALLIRAPTOR.get(), GalliraptorRenderer::new);
        EntityRenderers.register(FLEntities.GALLIRAPTOR_EGG.get(), ThrownItemRenderer::new);

        ItemBlockRenderTypes.setRenderLayer(FLBlocks.PEACOCK_BURST_POPPY.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(FLBlocks.ELECTRIC_BURST_POPPY.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(FLBlocks.FANCY_BURST_POPPY.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(FLBlocks.OLIVE_BURST_POPPY.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(FLBlocks.RUSTY_BURST_POPPY.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(FLBlocks.SUNSTREAK_BURST_POPPY.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(FLBlocks.POTTED_PEACOCK_BURST_POPPY.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(FLBlocks.POTTED_ELECTRIC_BURST_POPPY.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(FLBlocks.POTTED_FANCY_BURST_POPPY.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(FLBlocks.POTTED_OLIVE_BURST_POPPY.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(FLBlocks.POTTED_RUSTY_BURST_POPPY.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(FLBlocks.POTTED_SUNSTREAK_BURST_POPPY.get(), RenderType.cutout());
    }
}