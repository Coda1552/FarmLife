package codyhuh.farmlife.registry;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import codyhuh.farmlife.FarmLife;

public class FLParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, FarmLife.MOD_ID);

    public static final RegistryObject<SimpleParticleType> STINKY = PARTICLES.register("stinky", () -> new SimpleParticleType(true));
}
