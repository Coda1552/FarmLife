package codyhuh.farmlife.client.model;

import codyhuh.farmlife.FarmLife;
import codyhuh.farmlife.common.entities.Galliraptor;
import codyhuh.farmlife.common.entities.item.SeaPlumFruit;
import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

import java.util.Map;

public class SeaPlumFruitModel extends GeoModel<SeaPlumFruit> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FarmLife.MOD_ID, "textures/entity/sea_plum/sea_plum.png");
    private static final ResourceLocation ANIMATION = new ResourceLocation(FarmLife.MOD_ID, "animations/entity/sea_plum.animation.json");
    private static final ResourceLocation MODEL = new ResourceLocation(FarmLife.MOD_ID, "geo/entity/sea_plum.geo.json");

    @Override
    public ResourceLocation getModelResource(SeaPlumFruit object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(SeaPlumFruit object) {
        return TEXTURE;

    }

    @Override
    public ResourceLocation getAnimationResource(SeaPlumFruit object) {
        return ANIMATION;
    }

    @Override
    public void setCustomAnimations(SeaPlumFruit animatable, long instanceId, AnimationState<SeaPlumFruit> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);

        CoreGeoBone fruit = this.getAnimationProcessor().getBone("fruit");
        EntityModelData extraData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

        fruit.setRotX(animationState.getPartialTick() * ((float) Math.PI / 180F));
        fruit.setRotY(animationState.getPartialTick() * ((float) Math.PI / 180F));
    }
}
