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
        poseStack.translate(0.5D, 0.05D, 0.625D);
        poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));

        for (int i = 0; i < blockEntity.countItems(blockEntity.getItems()); i++) {
            ItemStack stack = blockEntity.getItem(i);

            RandomSource rand = RandomSource.create();

            poseStack.translate(0.0D, 0.0D, 0.0305D);
            poseStack.scale(0.985F, 0.985F, 1.0F);

            BakedModel model = mc.getItemRenderer().getModel(stack, mc.level, null, 0);

            mc.getItemRenderer().render(stack, ItemDisplayContext.GROUND, true, poseStack, buffer, combinedLight, combinedOverlay, model);
        }

        poseStack.popPose();
    }
}