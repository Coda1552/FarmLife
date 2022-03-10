package teamdraco.farmlife.registry;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.RegistryObject;
import teamdraco.farmlife.FarmLife;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamdraco.farmlife.common.blocks.TribullMilkCauldronBlock;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class FLBlocks {
    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, FarmLife.MOD_ID);

    public static final RegistryObject<Block> PEACOCK_BURST_POPPY = register("peacock_burst_poppy", () -> new FlowerBlock(MobEffects.LUCK, 5, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> ELECTRIC_BURST_POPPY = register("electric_burst_poppy", () -> new FlowerBlock(MobEffects.HARM, 1, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> FANCY_BURST_POPPY = register("fancy_burst_poppy", () -> new FlowerBlock(MobEffects.REGENERATION, 5, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> OLIVE_BURST_POPPY = register("olive_burst_poppy", () -> new FlowerBlock(MobEffects.HUNGER, 5, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> RUSTY_BURST_POPPY = register("rusty_burst_poppy", () -> new FlowerBlock(MobEffects.WEAKNESS, 5, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> SUNSTREAK_BURST_POPPY = register("sunstreak_burst_poppy", () -> new FlowerBlock(MobEffects.FIRE_RESISTANCE, 5, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));

    public static final RegistryObject<Block> POTTED_PEACOCK_BURST_POPPY = REGISTRY.register("potted_peacock_burst_poppy", () -> new FlowerPotBlock(PEACOCK_BURST_POPPY.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> POTTED_ELECTRIC_BURST_POPPY = REGISTRY.register("potted_electric_burst_poppy", () -> new FlowerPotBlock(ELECTRIC_BURST_POPPY.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> POTTED_FANCY_BURST_POPPY = REGISTRY.register("potted_fancy_burst_poppy", () -> new FlowerPotBlock(FANCY_BURST_POPPY.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> POTTED_OLIVE_BURST_POPPY = REGISTRY.register("potted_olive_burst_poppy", () -> new FlowerPotBlock(OLIVE_BURST_POPPY.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> POTTED_RUSTY_BURST_POPPY = REGISTRY.register("potted_rusty_burst_poppy", () -> new FlowerPotBlock(RUSTY_BURST_POPPY.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> POTTED_SUNSTREAK_BURST_POPPY = REGISTRY.register("potted_sunstreak_burst_poppy", () -> new FlowerPotBlock(SUNSTREAK_BURST_POPPY.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));

    public static final RegistryObject<Block> TRIBULL_MILK_CAULDRON = REGISTRY.register("tribull_milk_cauldron", () -> new TribullMilkCauldronBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.STONE).requiresCorrectToolForDrops().strength(2.0F).noOcclusion().randomTicks()));

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
        return register(name, block, new Item.Properties().tab(FarmLife.GROUP));
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block, Item.Properties itemProperties) {
        return register(name, block, BlockItem::new, itemProperties);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block, BiFunction<Block, Item.Properties, BlockItem> item, Item.Properties itemProperties) {
        final RegistryObject<T> registryObject = REGISTRY.register(name, block);
        if (itemProperties != null) FLItems.REGISTRY.register(name, () -> item == null ? new BlockItem(registryObject.get(), itemProperties) : item.apply(registryObject.get(), itemProperties));
        return registryObject;
    }
}