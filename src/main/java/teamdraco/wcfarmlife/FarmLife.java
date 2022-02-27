package teamdraco.wcfarmlife;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;
import teamdraco.wcfarmlife.client.ClientEventHandler;
import teamdraco.wcfarmlife.common.entities.DomesticTribullEntity;
import teamdraco.wcfarmlife.common.entities.GalliraptorEntity;
import teamdraco.wcfarmlife.registry.*;

@Mod.EventBusSubscriber
@Mod(FarmLife.MOD_ID)
public class FarmLife {
    public static final String MOD_ID = "wcfarmlife";
    public static final Logger LOGGER = LogManager.getLogger();

    public FarmLife() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        modEventBus.addListener(this::registerClient);
        modEventBus.addListener(this::registerCommon);
        modEventBus.addListener(this::registerEntityAttributes);

        FLBlocks.REGISTRY.register(modEventBus);
        FLItems.REGISTRY.register(modEventBus);
        FLEntities.REGISTRY.register(modEventBus);
        //FLStructures.REGISTRY.register(modEventBus);
        FLSounds.REGISTRY.register(modEventBus);

        GeckoLib.initialize();
    }

    private void registerCommon(FMLCommonSetupEvent event) {
        ComposterBlock.COMPOSTABLES.put(FLBlocks.PEACOCK_BURST_POPPY.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(FLBlocks.ELECTRIC_BURST_POPPY.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(FLBlocks.FANCY_BURST_POPPY.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(FLBlocks.OLIVE_BURST_POPPY.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(FLBlocks.RUSTY_BURST_POPPY.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(FLBlocks.SUNSTREAK_BURST_POPPY.get(), 0.3F);
    }

    private void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(FLEntities.DOMESTIC_TRIBULL.get(), DomesticTribullEntity.createAttributes().build());
        event.put(FLEntities.GALLIRAPTOR.get(), GalliraptorEntity.createAttributes().build());
    }

    private void registerClient(FMLClientSetupEvent event) {
        ClientEventHandler.init();
    }

    public final static CreativeModeTab GROUP = new CreativeModeTab(MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(FLItems.TRIBULL_SHANK.get());
        }
    };

//    public void biomeModification(final BiomeLoadingEvent event) {
//        if (event.getCategory() == Biome.Category.PLAINS) {
//            event.getGeneration().getStructures().add(() -> WCFarmLifeConfiguredStructures.CONFIGURED_TRIBULL_RANCH);
//        }
//        if (event.getCategory() == Biome.Category.FOREST) {
//            event.getGeneration().getStructures().add(() -> WCFarmLifeConfiguredStructures.CONFIGURED_GREENHOUSE);
//        }
//    }
}