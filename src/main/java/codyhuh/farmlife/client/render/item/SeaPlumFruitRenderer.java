package codyhuh.farmlife.client.render.item;

import codyhuh.farmlife.client.model.SeaPlumFruitModel;
import codyhuh.farmlife.common.entities.item.SeaPlumFruit;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SeaPlumFruitRenderer extends GeoEntityRenderer<SeaPlumFruit> {

    public SeaPlumFruitRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SeaPlumFruitModel());
        this.shadowRadius = 0.0F;
    }

    @Override
    public RenderType getRenderType(SeaPlumFruit animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityCutout(texture);
    }
}