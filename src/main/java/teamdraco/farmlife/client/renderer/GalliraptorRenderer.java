package teamdraco.farmlife.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import teamdraco.farmlife.client.model.GalliraptorModel;
import teamdraco.farmlife.common.entities.Galliraptor;

public class GalliraptorRenderer extends GeoEntityRenderer<Galliraptor> {

    public GalliraptorRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new GalliraptorModel());
        this.shadowRadius = 0.4F;
    }
}