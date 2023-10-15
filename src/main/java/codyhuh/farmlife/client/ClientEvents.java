package codyhuh.farmlife.client;

import codyhuh.farmlife.FarmLife;
import codyhuh.farmlife.client.particle.StinkyParticle;
import codyhuh.farmlife.client.renderer.DomesticTribullRenderer;
import codyhuh.farmlife.client.renderer.GalliraptorRenderer;
import codyhuh.farmlife.client.renderer.PlatefishRenderer;
import codyhuh.farmlife.registry.FLEntities;
import codyhuh.farmlife.registry.FLParticles;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

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