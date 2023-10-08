package teamdraco.farmlife.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.RegistryObject;
import teamdraco.farmlife.FarmLife;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FLSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, FarmLife.MOD_ID);

    public static final RegistryObject<SoundEvent> DOMESTIC_TRIBULL_AMBIENT = SOUNDS.register("domestic_tribull_ambient", () -> new SoundEvent(new ResourceLocation(FarmLife.MOD_ID, "domestic_tribull.ambient")));
    public static final RegistryObject<SoundEvent> DOMESTIC_TRIBULL_HURT = SOUNDS.register("domestic_tribull_hurt", () -> new SoundEvent(new ResourceLocation(FarmLife.MOD_ID, "domestic_tribull.hurt")));
    public static final RegistryObject<SoundEvent> DOMESTIC_TRIBULL_DEATH = SOUNDS.register("domestic_tribull_death", () -> new SoundEvent(new ResourceLocation(FarmLife.MOD_ID, "domestic_tribull.death")));

    public static final RegistryObject<SoundEvent> GALLIRAPTOR_AMBIENT = SOUNDS.register("galliraptor_ambient", () -> new SoundEvent(new ResourceLocation(FarmLife.MOD_ID, "galliraptor.ambient")));
    public static final RegistryObject<SoundEvent> GALLIRAPTOR_HURT = SOUNDS.register("galliraptor_hurt", () -> new SoundEvent(new ResourceLocation(FarmLife.MOD_ID, "galliraptor.hurt")));
    public static final RegistryObject<SoundEvent> GALLIRAPTOR_DEATH = SOUNDS.register("galliraptor_death", () -> new SoundEvent(new ResourceLocation(FarmLife.MOD_ID, "galliraptor.death")));

    public static final RegistryObject<SoundEvent> MUSIC_DISC_LIFE_ON_THE_FARM = SOUNDS.register("music_disc_life_on_the_farm", () -> new SoundEvent(new ResourceLocation(FarmLife.MOD_ID, "music_disc.life_on_the_farm")));
}

