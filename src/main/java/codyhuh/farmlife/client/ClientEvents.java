package codyhuh.farmlife.client;

import codyhuh.farmlife.FarmLife;
import codyhuh.farmlife.client.particle.StinkyParticle;
import codyhuh.farmlife.client.render.DomesticTribullRenderer;
import codyhuh.farmlife.client.render.GalliraptorRenderer;
import codyhuh.farmlife.client.render.PlatefishRenderer;
import codyhuh.farmlife.client.render.block_entities.PlatefishPlateBlockEntityRenderer;
import codyhuh.farmlife.registry.FLBlockEntities;
import codyhuh.farmlife.registry.FLEntities;
import codyhuh.farmlife.registry.FLParticles;
import net.minecraft.client.multiplayer.ClientRegistryLayer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.client.event.EntityRenderersEvent;
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
        BlockEntityRenderers.register(FLBlockEntities.PLATEFISH_PLATE.get(), PlatefishPlateBlockEntityRenderer::new);
    }

    @SubscribeEvent
    public static void registerParticleTypes(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(FLParticles.STINKY.get(), StinkyParticle.Provider::new);
    }
}