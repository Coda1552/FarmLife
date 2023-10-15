package codyhuh.farmlife.registry;

import codyhuh.farmlife.common.entities.DomesticTribull;
import codyhuh.farmlife.common.entities.Galliraptor;
import codyhuh.farmlife.common.entities.Platefish;
import codyhuh.farmlife.common.entities.item.GalliraptorEggEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import codyhuh.farmlife.FarmLife;

public class FLEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, FarmLife.MOD_ID);

    public static final RegistryObject<EntityType<DomesticTribull>> DOMESTIC_TRIBULL = create("domestic_tribull", EntityType.Builder.of(DomesticTribull::new, MobCategory.CREATURE).sized(1.1f, 1.2f));
    public static final RegistryObject<EntityType<Galliraptor>> GALLIRAPTOR = create("galliraptor", EntityType.Builder.of(Galliraptor::new, MobCategory.CREATURE).sized(0.6f, 0.8f));
    public static final RegistryObject<EntityType<Platefish>> PLATEFISH = create("platefish", EntityType.Builder.of(Platefish::new, MobCategory.WATER_CREATURE).sized(0.9f, 0.4f));

    public static final RegistryObject<EntityType<GalliraptorEggEntity>> GALLIRAPTOR_EGG = create("galliraptor_egg", EntityType.Builder.<GalliraptorEggEntity>of(GalliraptorEggEntity::new, MobCategory.MISC).sized(0.25f, 0.25f));

    private static <T extends Entity> RegistryObject<EntityType<T>> create(String name, EntityType.Builder<T> builder) {
        return ENTITIES.register(name, () -> builder.build(FarmLife.MOD_ID + "." + name));
    }
}