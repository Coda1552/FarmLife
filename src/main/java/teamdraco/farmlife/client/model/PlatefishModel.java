package teamdraco.farmlife.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import teamdraco.farmlife.FarmLife;
import teamdraco.farmlife.common.entities.Platefish;

public class PlatefishModel extends AnimatedGeoModel<Platefish> {

    @Override
    public ResourceLocation getModelResource(Platefish object) {
        return new ResourceLocation(FarmLife.MOD_ID, "geo/entity/platefish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Platefish object) {
        return object.isSheared() ? new ResourceLocation(FarmLife.MOD_ID, "textures/entity/platefish/naked.png") : new ResourceLocation(FarmLife.MOD_ID, "textures/entity/platefish/overgrown.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Platefish object) {
        return new ResourceLocation(FarmLife.MOD_ID, "animations/entity/platefish.animation.json");
    }

    @Override
    public void setCustomAnimations(Platefish entity, int uniqueID, AnimationEvent customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");
        IBone body = this.getAnimationProcessor().getBone("root");
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);

        body.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        body.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));

        if (entity.isBaby()) {
            body.setScaleX(0.5F);
            body.setScaleY(0.5F);
            body.setScaleZ(0.5F);
        }
    }
}
