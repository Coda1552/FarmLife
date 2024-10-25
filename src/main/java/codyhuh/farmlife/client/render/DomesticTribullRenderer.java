package codyhuh.farmlife.client.render;

import codyhuh.farmlife.FarmLife;
import codyhuh.farmlife.client.FLModelLayers;
import codyhuh.farmlife.client.model.DomesticTribullModel;
import codyhuh.farmlife.common.entities.DomesticTribull;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DomesticTribullRenderer extends MobRenderer<DomesticTribull, DomesticTribullModel<DomesticTribull>> {
    private static final ResourceLocation TRIBULL_TEXTURE = new ResourceLocation(FarmLife.MOD_ID,"textures/entity/domestic_tribull/adult_domestic_tribull.png");
    private static final ResourceLocation BABY_TRIBULL_TEXTURE = new ResourceLocation(FarmLife.MOD_ID,"textures/entity/domestic_tribull/baby_domestic_tribull.png");

    public DomesticTribullRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new DomesticTribullModel<>(ctx.bakeLayer(FLModelLayers.DOMESTIC_TRIBULL)), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(DomesticTribull entity) {
        return entity.isBaby() ? BABY_TRIBULL_TEXTURE : TRIBULL_TEXTURE;
    }
}
