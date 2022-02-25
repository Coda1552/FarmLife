package teamdraco.wcfarmlife.registry;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;
import teamdraco.wcfarmlife.WCFarmLife;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class WCFarmLifeBlocks {
    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, WCFarmLife.MOD_ID);

    public static final RegistryObject<Block> PEACOCK_BURST_POPPY = REGISTRY.register("peacock_burst_poppy", () -> new FlowerBlock(MobEffects.LUCK, 5, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> ELECTRIC_BURST_POPPY = REGISTRY.register("electric_burst_poppy", () -> new FlowerBlock(MobEffects.HARM, 1, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> FANCY_BURST_POPPY = REGISTRY.register("fancy_burst_poppy", () -> new FlowerBlock(MobEffects.REGENERATION, 5, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> OLIVE_BURST_POPPY = REGISTRY.register("olive_burst_poppy", () -> new FlowerBlock(MobEffects.HUNGER, 5, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> RUSTY_BURST_POPPY = REGISTRY.register("rusty_burst_poppy", () -> new FlowerBlock(MobEffects.WEAKNESS, 5, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> SUNSTREAK_BURST_POPPY = REGISTRY.register("sunstreak_burst_poppy", () -> new FlowerBlock(MobEffects.FIRE_RESISTANCE, 5, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));

    public static final RegistryObject<Block> POTTED_PEACOCK_BURST_POPPY = REGISTRY.register("potted_peacock_burst_poppy", () -> new FlowerPotBlock(PEACOCK_BURST_POPPY.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> POTTED_ELECTRIC_BURST_POPPY = REGISTRY.register("potted_electric_burst_poppy", () -> new FlowerPotBlock(ELECTRIC_BURST_POPPY.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> POTTED_FANCY_BURST_POPPY = REGISTRY.register("potted_fancy_burst_poppy", () -> new FlowerPotBlock(FANCY_BURST_POPPY.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> POTTED_OLIVE_BURST_POPPY = REGISTRY.register("potted_olive_burst_poppy", () -> new FlowerPotBlock(OLIVE_BURST_POPPY.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> POTTED_RUSTY_BURST_POPPY = REGISTRY.register("potted_rusty_burst_poppy", () -> new FlowerPotBlock(RUSTY_BURST_POPPY.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> POTTED_SUNSTREAK_BURST_POPPY = REGISTRY.register("potted_sunstreak_burst_poppy", () -> new FlowerPotBlock(SUNSTREAK_BURST_POPPY.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
}