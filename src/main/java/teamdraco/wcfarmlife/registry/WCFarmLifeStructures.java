package teamdraco.wcfarmlife.registry;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import teamdraco.wcfarmlife.WCFarmLife;
import teamdraco.wcfarmlife.common.world.features.GreenhouseStructure;
import teamdraco.wcfarmlife.common.world.features.TribullRanchStructure;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Locale;

@Mod.EventBusSubscriber(modid = WCFarmLife.MOD_ID)
public class WCFarmLifeStructures {
    public static IStructurePieceType TRIBULL_RANCH_PIECE = register(TribullRanchStructure.Piece::new, "TribullRanch");
    public static IStructurePieceType GREENHOUSE_PIECE = register(GreenhouseStructure.Piece::new, "Greenhouse");
    public static final DeferredRegister<Structure<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, WCFarmLife.MOD_ID);

    public static final RegistryObject<Structure<NoFeatureConfig>> TRIBULL_RANCH = REGISTRY.register("tribull_ranch", () -> (new TribullRanchStructure(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> GREENHOUSE = REGISTRY.register("greenhouse", () -> (new GreenhouseStructure(NoFeatureConfig.CODEC)));

    public static void setupStructures() {
        setupMapSpacingAndLand(TRIBULL_RANCH.get(), new StructureSeparationSettings(80, 35, 253895293), true);
        setupMapSpacingAndLand(GREENHOUSE.get(), new StructureSeparationSettings(60, 30, 523185382), true);
    }

    static IStructurePieceType register(IStructurePieceType type, String key) {
        return Registry.register(Registry.STRUCTURE_PIECE, key.toLowerCase(Locale.ROOT), type);
    }

    public static <F extends Structure<?>> void setupMapSpacingAndLand(F structure, StructureSeparationSettings structureSeparationSettings, boolean transformSurroundingLand) {
        Structure.STRUCTURES_REGISTRY.put(structure.getRegistryName().toString(), structure);

        if (transformSurroundingLand) {
            Structure.NOISE_AFFECTING_FEATURES = ImmutableList.<Structure<?>>builder().addAll(Structure.NOISE_AFFECTING_FEATURES).add(structure).build();
        }

        DimensionStructuresSettings.DEFAULTS = ImmutableMap.<Structure<?>, StructureSeparationSettings>builder().putAll(DimensionStructuresSettings.DEFAULTS).put(structure, structureSeparationSettings).build();
    }
}