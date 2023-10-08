package teamdraco.farmlife.client.model;

import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;
import teamdraco.farmlife.FarmLife;
import teamdraco.farmlife.common.entities.Galliraptor;

import java.util.Map;

public class GalliraptorModel extends GeoModel<Galliraptor> {
    public static final Map<Integer, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
        hashMap.put(0, new ResourceLocation(FarmLife.MOD_ID, "textures/entity/galliraptor/galliraptor_1.png"));
        hashMap.put(1, new ResourceLocation(FarmLife.MOD_ID, "textures/entity/galliraptor/galliraptor_2.png"));
        hashMap.put(2, new ResourceLocation(FarmLife.MOD_ID, "textures/entity/galliraptor/galliraptor_3.png"));
        hashMap.put(3, new ResourceLocation(FarmLife.MOD_ID, "textures/entity/galliraptor/galliraptor_4.png"));
        hashMap.put(4, new ResourceLocation(FarmLife.MOD_ID, "textures/entity/galliraptor/galliraptor_5.png"));
    });
    private static final ResourceLocation CHILD_TEXTURE = new ResourceLocation(FarmLife.MOD_ID, "textures/entity/galliraptor/chick.png");

    @Override
    public ResourceLocation getModelResource(Galliraptor object) {
        return object.isBaby() ? new ResourceLocation(FarmLife.MOD_ID, "geo/entity/galliraptor_chick.geo.json") : new ResourceLocation(FarmLife.MOD_ID, "geo/entity/galliraptor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Galliraptor object) {
        return object.isBaby() ? CHILD_TEXTURE : TEXTURES.getOrDefault(object.getVariant(), TEXTURES.get(0));

    }

    @Override
    public ResourceLocation getAnimationResource(Galliraptor animatable) {
        return animatable.isBaby() ? new ResourceLocation(FarmLife.MOD_ID, "animations/entity/galliraptor_chick.animation.json") : new ResourceLocation(FarmLife.MOD_ID, "animations/entity/galliraptor.animation.json");
    }

    @Override
    public void setCustomAnimations(Galliraptor animatable, long instanceId, AnimationState<Galliraptor> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);

        if (!animatable.isBaby()) {
            CoreGeoBone head = this.getAnimationProcessor().getBone("head");
            EntityModelData extraData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(extraData.headPitch() * ((float) Math.PI / 180F));
            head.setRotY(extraData.netHeadYaw() * ((float) Math.PI / 180F));
        }
    }
}
