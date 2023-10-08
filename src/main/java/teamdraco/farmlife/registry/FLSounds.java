package teamdraco.farmlife.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.RegistryObject;
import teamdraco.farmlife.FarmLife;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FLSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, FarmLife.MOD_ID);

    public static final RegistryObject<SoundEvent> DOMESTIC_TRIBULL_AMBIENT = create("domestic_tribull.ambient");
    public static final RegistryObject<SoundEvent> DOMESTIC_TRIBULL_HURT = create("domestic_tribull.hurt");
    public static final RegistryObject<SoundEvent> DOMESTIC_TRIBULL_DEATH = create("domestic_tribull.death");

    public static final RegistryObject<SoundEvent> GALLIRAPTOR_AMBIENT = create("galliraptor.ambient");
    public static final RegistryObject<SoundEvent> GALLIRAPTOR_HURT = create("galliraptor.hurt");
    public static final RegistryObject<SoundEvent> GALLIRAPTOR_DEATH = create("galliraptor.death");

    public static final RegistryObject<SoundEvent> MUSIC_DISC_LIFE_ON_THE_FARM = create("music_disc.life_on_the_farm");

    private static RegistryObject<SoundEvent> create(String name) {
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(FarmLife.MOD_ID, name)));
    }
}

