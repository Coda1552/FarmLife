package teamdraco.farmlife.client;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import teamdraco.farmlife.FarmLife;
import teamdraco.farmlife.client.particle.StinkyParticle;
import teamdraco.farmlife.client.renderer.DomesticTribullRenderer;
import teamdraco.farmlife.client.renderer.GalliraptorRenderer;
import teamdraco.farmlife.client.renderer.PlatefishRenderer;
import teamdraco.farmlife.registry.FLEntities;
import teamdraco.farmlife.registry.FLParticles;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = FarmLife.MOD_ID)
public class ClientEvents {

    @SubscribeEvent
    public static void registerClient(FMLClientSetupEvent event) {
        EntityRenderers.register(FLEntities.DOMESTIC_TRIBULL.get(), DomesticTribullRenderer::new);
        EntityRenderers.register(FLEntities.GALLIRAPTOR.get(), GalliraptorRenderer::new);
        EntityRenderers.register(FLEntities.GALLIRAPTOR_EGG.get(), ThrownItemRenderer::new);
        EntityRenderers.register(FLEntities.PLATEFISH.get(), PlatefishRenderer::new);
    }

    @SubscribeEvent
    public static void registerParticleTypes(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(FLParticles.STINKY.get(), StinkyParticle.Provider::new);
    }
}