package codyhuh.farmlife.client;

import codyhuh.farmlife.FarmLife;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class FLModelLayers {
    public static final ModelLayerLocation DOMESTIC_TRIBULL = create("domestic_tribull");
    public static final ModelLayerLocation GALLIRAPTOR = create("galliraptor");
    public static final ModelLayerLocation GALLIRAPTOR_CHICK = create("galliraptor_chick");
    public static final ModelLayerLocation SEA_PLUM = create("sea_plum");

    private static ModelLayerLocation create(String name) {
        return new ModelLayerLocation(new ResourceLocation(FarmLife.MOD_ID, name), name);
    }
}
