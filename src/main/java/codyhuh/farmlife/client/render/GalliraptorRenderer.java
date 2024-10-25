package codyhuh.farmlife.client.render;

import codyhuh.farmlife.FarmLife;
import codyhuh.farmlife.client.FLModelLayers;
import codyhuh.farmlife.client.model.GalliraptorModel;
import codyhuh.farmlife.common.entities.Galliraptor;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class GalliraptorRenderer extends MobRenderer<Galliraptor, GalliraptorModel<Galliraptor>> {
    public static final Map<Integer, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
        hashMap.put(0, new ResourceLocation(FarmLife.MOD_ID, "textures/entity/galliraptor/galliraptor_1.png"));
        hashMap.put(1, new ResourceLocation(FarmLife.MOD_ID, "textures/entity/galliraptor/galliraptor_2.png"));
        hashMap.put(2, new ResourceLocation(FarmLife.MOD_ID, "textures/entity/galliraptor/galliraptor_3.png"));
        hashMap.put(3, new ResourceLocation(FarmLife.MOD_ID, "textures/entity/galliraptor/galliraptor_4.png"));
        hashMap.put(4, new ResourceLocation(FarmLife.MOD_ID, "textures/entity/galliraptor/galliraptor_5.png"));
    });
    private static final ResourceLocation BABY_GALLIRAPTOR_TEXTURE = new ResourceLocation(FarmLife.MOD_ID, "textures/entity/galliraptor/chick.png");
    private final GalliraptorModel<Galliraptor> adultModel = this.getModel();
    private final GalliraptorModel<Galliraptor> babyModel;

    public GalliraptorRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new GalliraptorModel<>(ctx.bakeLayer(FLModelLayers.GALLIRAPTOR)), 0.35F);
        this.babyModel = new GalliraptorModel<>(ctx.bakeLayer(FLModelLayers.GALLIRAPTOR_CHICK));
    }

    @Override
    public void render(Galliraptor entity, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        this.model = entity.isBaby() ? this.babyModel : this.adultModel;
        super.render(entity, f, g, poseStack, multiBufferSource, i);
    }

    @Override
    public ResourceLocation getTextureLocation(Galliraptor entity) {
        return entity.isBaby() ? BABY_GALLIRAPTOR_TEXTURE : TEXTURES.getOrDefault(entity.getVariant(), TEXTURES.get(0));
    }
}
