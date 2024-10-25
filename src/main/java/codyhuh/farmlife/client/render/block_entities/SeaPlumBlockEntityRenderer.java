package codyhuh.farmlife.client.render.block_entities;

import codyhuh.farmlife.FarmLife;
import codyhuh.farmlife.common.block_entities.SeaPlumBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class SeaPlumBlockEntityRenderer implements BlockEntityRenderer<SeaPlumBlockEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FarmLife.MOD_ID, "textures/entity/sea_plum/sea_plum.png");

    public SeaPlumBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(SeaPlumBlockEntity blockEntity, float partialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int combinedLight, int combinedOverlay) {
        VertexConsumer vertexConsumer = pBuffer.getBuffer(RenderType.entityCutout(TEXTURE));
    }
}