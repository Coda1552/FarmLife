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
        
        // rotation, x, y, z
        final float[][] TRANSFORMATIONS = new float[][]{
                {45.0f, 0.0f, 0.4f, 0.0295f},
                {135.0f, 0.4f, 0.0f, 0.0f},
                {-135.0f, 0.0f, -0.4f, 0.0f},
                {-45.0f, -0.4f, 0.0f, 0.0f},

                {0.1f, 0.65f, 0.1f, 0.0305f},
                {0.2f, 0.45f, 0.5f, 0.0305f},
                {0.3f, 0.50f, 0.4f, 0.0305f},
                {0.4f, 0.55f, 0.3f, 0.0305f},

                {0.1f, 0.60f, 0.2f, 0.0305f},
                {0.2f, 0.65f, 0.1f, 0.0305f},
                {0.3f, 0.45f, 0.5f, 0.0305f},
                {0.4f, 0.50f, 0.4f, 0.0305f},

                {0.1f, 0.55f, 0.3f, 0.0305f},
                {0.2f, 0.60f, 0.2f, 0.0305f},
                {0.3f, 0.65f, 0.1f, 0.0305f},
                {0.4f, 0.65f, 0.5f, 0.0305f},
        };

        Minecraft mc = Minecraft.getInstance();

        poseStack.pushPose();
        poseStack.translate(0.3D, 0.05D, 0.825D);
        poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));

        for (int i = 0; i < blockEntity.countItems(blockEntity.getItems()); i++) {
            ItemStack stack = blockEntity.getItem(i);
            final float[] transformation = TRANSFORMATIONS[i];

            poseStack.pushPose();
            poseStack.mulPose(Axis.ZP.rotationDegrees(transformation[0]));
            poseStack.popPose();
            poseStack.translate(transformation[1], transformation[2], transformation[3]);
            //poseStack.scale(0.985F, 0.985F, 1.0F);


            BakedModel model = mc.getItemRenderer().getModel(stack, mc.level, null, 0);

            mc.getItemRenderer().render(stack, ItemDisplayContext.GROUND, true, poseStack, buffer, combinedLight, combinedOverlay, model);
        }

        poseStack.popPose();
    }
}