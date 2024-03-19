package codyhuh.farmlife;

import codyhuh.farmlife.registry.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(FarmLife.MOD_ID)
public class FarmLife {
    public static final String MOD_ID = "farmlife";

    public FarmLife() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        FLBlocks.BLOCKS.register(bus);
        FLItems.ITEMS.register(bus);
        FLEntities.ENTITIES.register(bus);
        FLSounds.SOUNDS.register(bus);
        FLParticles.PARTICLES.register(bus);
        FLBlockEntities.BLOCK_ENTITIES.register(bus);
        FLCreativeTabs.CREATIVE_TABS.register(bus);
    }
}