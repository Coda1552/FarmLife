package codyhuh.farmlife.client.render;

import codyhuh.farmlife.FarmLife;
import codyhuh.farmlife.client.FLModelLayers;
import codyhuh.farmlife.client.model.PlatefishModel;
import codyhuh.farmlife.common.entities.Platefish;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PlatefishRenderer extends MobRenderer<Platefish, PlatefishModel<Platefish>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FarmLife.MOD_ID,"textures/entity/platefish/platefish.png");
    private static final ResourceLocation SHEARED_TEXTURE = new ResourceLocation(FarmLife.MOD_ID,"textures/entity/platefish/sheared_platefish.png");

    public PlatefishRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new PlatefishModel<>(ctx.bakeLayer(FLModelLayers.PLATEFISH)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(Platefish entity) {
        return entity.isSheared() ? SHEARED_TEXTURE : TEXTURE;
    }
}
