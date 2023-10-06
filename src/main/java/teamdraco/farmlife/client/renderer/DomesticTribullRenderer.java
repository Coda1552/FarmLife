package teamdraco.farmlife.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import teamdraco.farmlife.client.model.DomesticTribullModel;
import teamdraco.farmlife.common.entities.DomesticTribull;

public class DomesticTribullRenderer extends GeoEntityRenderer<DomesticTribull> {

    public DomesticTribullRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DomesticTribullModel());
        this.shadowRadius = 0.7F;
    }
}