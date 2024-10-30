package codyhuh.farmlife.registry;

import codyhuh.farmlife.common.items.CheeseWedgeItem;
import codyhuh.farmlife.common.items.GalliraptorEggItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import codyhuh.farmlife.FarmLife;

public class FLItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FarmLife.MOD_ID);

    public static final RegistryObject<Item> TRIBULL_SHANK = ITEMS.register("tribull_shank", () -> new Item(new Item.Properties().food(new  FoodProperties.Builder().nutrition(4).saturationMod(0.3f).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 0), 0.25f).meat().build())));
    public static final RegistryObject<Item> COOKED_TRIBULL_SHANK = ITEMS.register("cooked_tribull_shank", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(10).saturationMod(0.75f).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 0), 0.10f).meat().build())));
    public static final RegistryObject<Item> TRIBULL_MILK = ITEMS.register("tribull_milk", () -> new MilkBucketItem(new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> TRIBULL_CHEESE_WEDGE = ITEMS.register("tribull_cheese_wedge", () -> new CheeseWedgeItem(new Item.Properties().food(new FoodProperties.Builder().saturationMod(0.25F).nutrition(3).build())));
    public static final RegistryObject<Item> TRIBULL_CHEESE_WHEEL = ITEMS.register("tribull_cheese_wheel", () -> new BlockItem(FLBlocks.TRIBULL_CHEESE_WHEEL.get(), new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> GALLIRAPTOR = ITEMS.register("galliraptor", () -> new Item(new Item.Properties().food(new  FoodProperties.Builder().nutrition(2).saturationMod(0.2f).effect(new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F).meat().build())));
    public static final RegistryObject<Item> COOKED_GALLIRAPTOR = ITEMS.register("cooked_galliraptor", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationMod(0.5f).meat().build())));
    public static final RegistryObject<Item> GALLIRAPTOR_EGG = ITEMS.register("galliraptor_egg", () -> new GalliraptorEggItem(new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> SEA_PLUM = ITEMS.register("sea_plum", () -> new BlockItem(FLBlocks.SEA_PLUM.get(), new Item.Properties().food(new FoodProperties.Builder().saturationMod(0.1F).nutrition(2).build())));
    public static final RegistryObject<Item> FULI = ITEMS.register("fuli", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationMod(0.15F).nutrition(2).build())));
    public static final RegistryObject<Item> COOKED_FULI = ITEMS.register("cooked_fuli", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationMod(0.375F).nutrition(7).build())));
    public static final RegistryObject<Item> PLATEFISH_PLATE = ITEMS.register("platefish_plate", () -> new BlockItem(FLBlocks.PLATEFISH_PLATE.get(), new Item.Properties()));
    public static final RegistryObject<Item> DISC_FRAGMENT_LIFE_ON_THE_FARM = ITEMS.register("disc_fragment_life_on_the_farm", () -> new DiscFragmentItem(new Item.Properties()));
    public static final RegistryObject<Item> MUSIC_DISC_LIFE_ON_THE_FARM = ITEMS.register("music_disc_life_on_the_farm", () -> new RecordItem(14, FLSounds.MUSIC_DISC_LIFE_ON_THE_FARM, new Item.Properties().rarity(Rarity.RARE).stacksTo(1),  2223));
    public static final RegistryObject<Item> BURST_POPPY_SEEDS = ITEMS.register("burst_poppy_seeds", () -> new BlockItem(FLBlocks.BURST_POPPY_SPROUT.get(), new Item.Properties()));
    public static final RegistryObject<Item> PEACOCK_BURST_POPPY = ITEMS.register("peacock_burst_poppy", () -> new BlockItem(FLBlocks.PEACOCK_BURST_POPPY.get(), new Item.Properties()));
    public static final RegistryObject<Item> ELECTRIC_BURST_POPPY = ITEMS.register("electric_burst_poppy", () -> new BlockItem(FLBlocks.ELECTRIC_BURST_POPPY.get(), new Item.Properties()));
    public static final RegistryObject<Item> FANCY_BURST_POPPY = ITEMS.register("fancy_burst_poppy", () -> new BlockItem(FLBlocks.FANCY_BURST_POPPY.get(), new Item.Properties()));
    public static final RegistryObject<Item> OLIVE_BURST_POPPY = ITEMS.register("olive_burst_poppy", () -> new BlockItem(FLBlocks.OLIVE_BURST_POPPY.get(), new Item.Properties()));
    public static final RegistryObject<Item> RUSTY_BURST_POPPY = ITEMS.register("rusty_burst_poppy", () -> new BlockItem(FLBlocks.RUSTY_BURST_POPPY.get(), new Item.Properties()));
    public static final RegistryObject<Item> SUNSTREAK_BURST_POPPY = ITEMS.register("sunstreak_burst_poppy", () -> new BlockItem(FLBlocks.SUNSTREAK_BURST_POPPY.get(), new Item.Properties()));

    // Spawn Eggs
    public static final RegistryObject<Item> DOMESTIC_TRIBULL_SPAWN_EGG = ITEMS.register("domestic_tribull_spawn_egg", () -> new ForgeSpawnEggItem(FLEntities.DOMESTIC_TRIBULL, 0x92b3b0, 0xce4e4e, new Item.Properties()));
    public static final RegistryObject<Item> GALLIRAPTOR_SPAWN_EGG = ITEMS.register("galliraptor_spawn_egg", () -> new ForgeSpawnEggItem(FLEntities.GALLIRAPTOR, 0xefa83e, 0x182749, new Item.Properties()));
    public static final RegistryObject<Item> PLATEFISH_SPAWN_EGG = ITEMS.register("platefish_spawn_egg", () -> new ForgeSpawnEggItem(FLEntities.PLATEFISH, 0xc68f19, 0x63351a, new Item.Properties()));
}