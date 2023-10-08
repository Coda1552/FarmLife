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
import teamdraco.farmlife.common.entities.Platefish;

public class PlatefishRenderer extends GeoEntityRenderer<Platefish> {

    public PlatefishRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DefaultedEntityGeoModel<>(new ResourceLocation(FarmLife.MOD_ID, "platefish")));
        this.shadowRadius = 0.3F;
    }

    @Override
    public ResourceLocation getTextureLocation(Platefish animatable) {
        return animatable.isSheared() ? new ResourceLocation(FarmLife.MOD_ID, "textures/entity/platefish/naked.png") : new ResourceLocation(FarmLife.MOD_ID, "textures/entity/platefish/overgrown.png");
    }

    @Override
    public void preRender(PoseStack poseStack, Platefish animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);

        if (animatable.isBaby()) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
        }
    }
}