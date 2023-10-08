package teamdraco.farmlife.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import teamdraco.farmlife.FarmLife;
import teamdraco.farmlife.common.entities.DomesticTribull;

public class DomesticTribullRenderer extends GeoEntityRenderer<DomesticTribull> {

    public DomesticTribullRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DefaultedEntityGeoModel<>(new ResourceLocation(FarmLife.MOD_ID, "domestic_tribull")));
        this.shadowRadius = 0.7F;
    }

    @Override
    public ResourceLocation getTextureLocation(DomesticTribull animatable) {
        return animatable.isBaby() ? new ResourceLocation(FarmLife.MOD_ID, "textures/entity/domestic_tribull/baby.png") : new ResourceLocation(FarmLife.MOD_ID, "textures/entity/domestic_tribull/adult.png");
    }

    @Override
    public void preRender(PoseStack poseStack, DomesticTribull animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);

        if (animatable.isBaby()) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
        }
    }
}