package teamdraco.farmlife.client.model;

import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import teamdraco.farmlife.FarmLife;
import teamdraco.farmlife.common.entities.Galliraptor;

import java.util.Map;

public class GalliraptorModel extends AnimatedGeoModel<Galliraptor> {
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
    public void setCustomAnimations(Galliraptor entity, int uniqueID, AnimationEvent customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        if (!entity.isBaby()) {
            IBone head = this.getAnimationProcessor().getBone("head");
            EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);

            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }
}
