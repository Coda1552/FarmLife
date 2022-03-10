package teamdraco.farmlife;

import net.minecraft.Util;
import net.minecraft.core.Position;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;
import teamdraco.farmlife.common.entities.DomesticTribullEntity;
import teamdraco.farmlife.common.entities.GalliraptorEntity;
import teamdraco.farmlife.common.entities.item.GalliraptorEggEntity;
import teamdraco.farmlife.common.items.GalliraptorEggItem;
import teamdraco.farmlife.registry.*;

@Mod.EventBusSubscriber
@Mod(FarmLife.MOD_ID)
public class FarmLife {
    public static final String MOD_ID = "farmlife";
    public static final Logger LOGGER = LogManager.getLogger();

    public FarmLife() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(this::registerCommon);
        bus.addListener(this::registerEntityAttributes);

        FLBlocks.REGISTRY.register(bus);
        FLItems.REGISTRY.register(bus);
        FLEntities.REGISTRY.register(bus);
        FLSounds.REGISTRY.register(bus);

        GeckoLib.initialize();
    }

    private void registerCommon(FMLCommonSetupEvent event) {
        ComposterBlock.COMPOSTABLES.put(FLBlocks.PEACOCK_BURST_POPPY.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(FLBlocks.ELECTRIC_BURST_POPPY.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(FLBlocks.FANCY_BURST_POPPY.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(FLBlocks.OLIVE_BURST_POPPY.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(FLBlocks.RUSTY_BURST_POPPY.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(FLBlocks.SUNSTREAK_BURST_POPPY.get(), 0.3F);

        DispenserBlock.registerBehavior(FLItems.GALLIRAPTOR_EGG.get(), new AbstractProjectileDispenseBehavior() {
            protected Projectile getProjectile(Level p_123468_, Position p_123469_, ItemStack p_123470_) {
                return Util.make(new GalliraptorEggEntity(p_123468_, p_123469_.x(), p_123469_.y(), p_123469_.z()), (p_123466_) -> {
                    p_123466_.setItem(p_123470_);
                });
            }
        });
    }

    private void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(FLEntities.DOMESTIC_TRIBULL.get(), DomesticTribullEntity.createAttributes().build());
        event.put(FLEntities.GALLIRAPTOR.get(), GalliraptorEntity.createAttributes().build());
    }

    public final static CreativeModeTab GROUP = new CreativeModeTab(MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(FLItems.TRIBULL_SHANK.get());
        }
    };
}