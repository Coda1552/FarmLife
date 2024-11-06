package codyhuh.farmlife.client.render.block_entities;

import codyhuh.farmlife.common.block_entities.PlatefishPlateBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class PlatefishPlateBlockEntityRenderer implements BlockEntityRenderer<PlatefishPlateBlockEntity> {

    public PlatefishPlateBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(PlatefishPlateBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        if (blockEntity.getItems().isEmpty()) {
            return;
        }

        Minecraft mc = Minecraft.getInstance();

        poseStack.pushPose();

        poseStack.translate(0.5D, 0.075D, 0.5D);
        poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(blockEntity.getRotation()));
        poseStack.scale(0.65F, 0.65F, 0.65F);

        ItemStack stack = blockEntity.getItem();
        BakedModel model = mc.getItemRenderer().getModel(stack, mc.level, null, 0);
        mc.getItemRenderer().render(stack, ItemDisplayContext.FIXED, true, poseStack, buffer, combinedLight, combinedOverlay, model);

        poseStack.popPose();
    }
}