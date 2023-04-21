package teamdraco.farmlife.registry;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamdraco.farmlife.FarmLife;
import teamdraco.farmlife.common.items.CheeseWedgeItem;
import teamdraco.farmlife.common.items.GalliraptorEggItem;

public class FLItems {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, FarmLife.MOD_ID);

    // Food
    public static final RegistryObject<Item> TRIBULL_SHANK = REGISTRY.register("tribull_shank", () -> new Item(new Item.Properties().tab(FarmLife.GROUP).food(new  FoodProperties.Builder().nutrition(4).saturationMod(0.3f).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 0), 0.25f).meat().build())));
    public static final RegistryObject<Item> COOKED_TRIBULL_SHANK = REGISTRY.register("cooked_tribull_shank", () -> new Item(new Item.Properties().tab(FarmLife.GROUP).food(new FoodProperties.Builder().nutrition(10).saturationMod(0.75f).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 0), 0.10f).meat().build())));
    public static final RegistryObject<Item> GALLIRAPTOR = REGISTRY.register("galliraptor", () -> new Item(new Item.Properties().tab(FarmLife.GROUP).food(new  FoodProperties.Builder().nutrition(2).saturationMod(0.2f).effect(new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F).meat().build())));
    public static final RegistryObject<Item> COOKED_GALLIRAPTOR = REGISTRY.register("cooked_galliraptor", () -> new Item(new Item.Properties().tab(FarmLife.GROUP).food(new FoodProperties.Builder().nutrition(6).saturationMod(0.5f).meat().build())));
    public static final RegistryObject<Item> TRIBULL_MILK = REGISTRY.register("tribull_milk", () -> new MilkBucketItem(new Item.Properties().tab(FarmLife.GROUP).craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> TRIBULL_CHEESE_WEDGE = REGISTRY.register("tribull_cheese_wedge", () -> new CheeseWedgeItem(new Item.Properties().tab(FarmLife.GROUP).food(new FoodProperties.Builder().saturationMod(0.25F).nutrition(3).build())));

    // Misc.
    public static final RegistryObject<Item> GALLIRAPTOR_EGG = REGISTRY.register("galliraptor_egg", () -> new GalliraptorEggItem(new Item.Properties().tab(FarmLife.GROUP).stacksTo(16)));
    public static final RegistryObject<Item> MUSIC_DISC_LIFE_ON_THE_FARM = REGISTRY.register("music_disc_life_on_the_farm", () -> new RecordItem(14, FLSounds.MUSIC_DISC_LIFE_ON_THE_FARM, new Item.Properties().tab(FarmLife.GROUP).rarity(Rarity.RARE).stacksTo(1),  2223));

    // Spawn Eggs
    public static final RegistryObject<Item> DOMESTIC_TRIBULL_SPAWN_EGG = REGISTRY.register("domestic_tribull_spawn_egg", () -> new ForgeSpawnEggItem(FLEntities.DOMESTIC_TRIBULL, 0x92b3b0, 0xce4e4e, new Item.Properties().tab(FarmLife.GROUP)));
    public static final RegistryObject<Item> GALLIRAPTOR_SPAWN_EGG = REGISTRY.register("galliraptor_spawn_egg", () -> new ForgeSpawnEggItem(FLEntities.GALLIRAPTOR, 0xefa83e, 0x182749, new Item.Properties().tab(FarmLife.GROUP)));
}