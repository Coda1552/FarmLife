package teamdraco.farmlife.client.renderer;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import teamdraco.farmlife.client.model.GalliraptorModel;
import teamdraco.farmlife.common.entities.Galliraptor;

public class GalliraptorRenderer extends GeoEntityRenderer<Galliraptor> {

    public GalliraptorRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new GalliraptorModel());
        this.shadowRadius = 0.4F;
    }

    @Override
    public RenderType getRenderType(Galliraptor animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityCutout(texture);
    }
}