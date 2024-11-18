package codyhuh.farmlife.client;

import codyhuh.farmlife.FarmLife;
import codyhuh.farmlife.client.model.DomesticTribullModel;
import codyhuh.farmlife.client.model.GalliraptorModel;
import codyhuh.farmlife.client.model.PlatefishModel;
import codyhuh.farmlife.client.model.SeaPlumModel;
import codyhuh.farmlife.client.particle.StinkyParticle;
import codyhuh.farmlife.client.render.DomesticTribullRenderer;
import codyhuh.farmlife.client.render.GalliraptorRenderer;
import codyhuh.farmlife.client.render.PlatefishRenderer;
import codyhuh.farmlife.client.render.block_entities.PlatterBlockEntityRenderer;
import codyhuh.farmlife.client.render.item.SeaPlumFruitRenderer;
import codyhuh.farmlife.registry.FLBlockEntities;
import codyhuh.farmlife.registry.FLEntities;
import codyhuh.farmlife.registry.FLParticles;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD, modid = FarmLife.MOD_ID)
public class ClientEvents {

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(FLEntities.DOMESTIC_TRIBULL.get(), DomesticTribullRenderer::new);
        event.registerEntityRenderer(FLEntities.GALLIRAPTOR.get(), GalliraptorRenderer::new);
        event.registerEntityRenderer(FLEntities.GALLIRAPTOR_EGG.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(FLEntities.PLATEFISH.get(), PlatefishRenderer::new);
        event.registerEntityRenderer(FLEntities.SEA_PLUM_FRUIT.get(), SeaPlumFruitRenderer::new);

        event.registerBlockEntityRenderer(FLBlockEntities.PLATTER.get(), PlatterBlockEntityRenderer::new);
    }

    @SubscribeEvent
    public static void registerModelLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(FLModelLayers.DOMESTIC_TRIBULL, DomesticTribullModel::createBodyLayer);
        event.registerLayerDefinition(FLModelLayers.GALLIRAPTOR, GalliraptorModel::createBodyLayer);
        event.registerLayerDefinition(FLModelLayers.GALLIRAPTOR_CHICK, GalliraptorModel::createChickBodyLayer);
        event.registerLayerDefinition(FLModelLayers.PLATEFISH, PlatefishModel::createBodyLayer);
        event.registerLayerDefinition(FLModelLayers.SEA_PLUM, SeaPlumModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerParticleTypes(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(FLParticles.STINKY.get(), StinkyParticle.Provider::new);
    }
}