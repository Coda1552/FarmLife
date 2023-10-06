package teamdraco.farmlife.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import teamdraco.farmlife.client.model.PlatefishModel;
import teamdraco.farmlife.common.entities.Platefish;

public class PlatefishRenderer extends GeoEntityRenderer<Platefish> {

    public PlatefishRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new PlatefishModel());
        this.shadowRadius = 0.3F;
    }
}