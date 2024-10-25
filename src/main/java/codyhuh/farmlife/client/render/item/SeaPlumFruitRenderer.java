package codyhuh.farmlife.client.render.item;

import codyhuh.farmlife.FarmLife;
import codyhuh.farmlife.client.FLModelLayers;
import codyhuh.farmlife.client.model.SeaPlumModel;
import codyhuh.farmlife.common.entities.item.SeaPlumFruit;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class SeaPlumFruitRenderer extends EntityRenderer<SeaPlumFruit> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FarmLife.MOD_ID, "textures/entity/sea_plum/sea_plum.png");
    private final SeaPlumModel<SeaPlumFruit> model;

    public SeaPlumFruitRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager);
        model = new SeaPlumModel<>(renderManager.bakeLayer(FLModelLayers.SEA_PLUM));
        shadowRadius = 0.0F;
    }

    @Override
    public void render(SeaPlumFruit entity, float pEntityYaw, float pPartialTick, PoseStack poseStack, MultiBufferSource buffer, int pPackedLight) {
        super.render(entity, pEntityYaw, pPartialTick, poseStack, buffer, pPackedLight);

        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityTranslucent(TEXTURE));

        poseStack.pushPose();

        poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
        poseStack.translate(0.0D, -1.5D,0.0D);

        model.setupAnim(entity, entity.tickCount * 0.5F, 0.3F, entity.tickCount * 0.5F, 0.0F, 0.0F);
        model.renderToBuffer(poseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        poseStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(SeaPlumFruit pEntity) {
        return TEXTURE;
    }
}