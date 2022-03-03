package teamdraco.farmlife.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import teamdraco.farmlife.FarmLife;
import teamdraco.farmlife.common.entities.DomesticTribullEntity;

public class DomesticTribullModel extends AnimatedTickingGeoModel<DomesticTribullEntity> {

    @Override
    public ResourceLocation getModelLocation(DomesticTribullEntity object) {
        return new ResourceLocation(FarmLife.MOD_ID, "geo/entity/domestic_tribull.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(DomesticTribullEntity object) {
        return new ResourceLocation(FarmLife.MOD_ID, "textures/entity/domestic_tribull.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(DomesticTribullEntity animatable) {
        return new ResourceLocation(FarmLife.MOD_ID, "animations/entity/domestic_tribull.animation.json");
    }

    @Override
    public void setLivingAnimations(DomesticTribullEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);

        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }
}