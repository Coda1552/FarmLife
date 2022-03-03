package teamdraco.farmlife.registry;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamdraco.farmlife.FarmLife;

public class FLItems {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, FarmLife.MOD_ID);

    public static final RegistryObject<Item> TRIBULL_SHANK = REGISTRY.register("tribull_shank", () -> new Item(new Item.Properties().tab(FarmLife.GROUP).food(new  FoodProperties.Builder().nutrition(4).saturationMod(0.2f).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 0), 0.25f).meat().build())));
    public static final RegistryObject<Item> COOKED_TRIBULL_SHANK = REGISTRY.register("cooked_tribull_shank", () -> new Item(new Item.Properties().tab(FarmLife.GROUP).food(new FoodProperties.Builder().nutrition(10).saturationMod(0.6f).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 0), 0.10f).meat().build())));
    public static final RegistryObject<Item> MUSIC_DISC_LIFE_ON_THE_FARM = REGISTRY.register("music_disc_life_on_the_farm", () -> new RecordItem(14, FLSounds.MUSIC_DISC_LIFE_ON_THE_FARM::get, new Item.Properties().tab(FarmLife.GROUP).rarity(Rarity.RARE).stacksTo(1)));

    public static final RegistryObject<Item> DOMESTIC_TRIBULL_SPAWN_EGG = REGISTRY.register("domestic_tribull_spawn_egg", () -> new ForgeSpawnEggItem(FLEntities.DOMESTIC_TRIBULL, 0x92b3b0, 0x738681, new Item.Properties().tab(FarmLife.GROUP)));
    public static final RegistryObject<Item> GALLIRAPTOR_SPAWN_EGG = REGISTRY.register("galliraptor_spawn_egg", () -> new ForgeSpawnEggItem(FLEntities.GALLIRAPTOR, 0xefa83e, 0x182749, new Item.Properties().tab(FarmLife.GROUP)));
}