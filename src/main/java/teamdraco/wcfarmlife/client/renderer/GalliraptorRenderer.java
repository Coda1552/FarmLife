package teamdraco.wcfarmlife.client.renderer;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.matrix.MatrixStack;
import teamdraco.wcfarmlife.FarmLife;
import teamdraco.wcfarmlife.client.model.GalliraptorModel;
import teamdraco.wcfarmlife.common.entities.GalliraptorEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class GalliraptorRenderer extends MobRenderer<GalliraptorEntity, GalliraptorModel<GalliraptorEntity>> {
    public static final Map<Integer, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
        hashMap.put(0, new ResourceLocation(FarmLife.MOD_ID, "textures/entity/galliraptor_1.png"));
        hashMap.put(1, new ResourceLocation(FarmLife.MOD_ID, "textures/entity/galliraptor_2.png"));
        hashMap.put(2, new ResourceLocation(FarmLife.MOD_ID, "textures/entity/galliraptor_3.png"));
        hashMap.put(3, new ResourceLocation(FarmLife.MOD_ID, "textures/entity/galliraptor_4.png"));
        hashMap.put(4, new ResourceLocation(FarmLife.MOD_ID, "textures/entity/galliraptor_5.png"));
    });
    private static final ResourceLocation CHILD_TEXTURE = new ResourceLocation(FarmLife.MOD_ID, "textures/entity/galliraptor_chick.png");
    private final GalliraptorModel adult;
    private final GalliraptorModel child;

    @Override
    public void render(GalliraptorEntity entity, float yaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        model = entity.isBaby() ? child : adult;
        super.render(entity, yaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    public GalliraptorRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GalliraptorModel.Adult(), 0.4F);
        adult = model;
        child = new GalliraptorModel.Child();
    }

    public ResourceLocation getTextureLocation(GalliraptorEntity entity) {
        if (entity.isBaby()) {
            return CHILD_TEXTURE;
        }
        return TEXTURES.getOrDefault(entity.getVariant(), TEXTURES.get(0));
    }
}