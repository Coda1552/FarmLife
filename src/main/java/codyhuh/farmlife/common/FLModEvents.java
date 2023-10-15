package codyhuh.farmlife.common;

import codyhuh.farmlife.FarmLife;
import codyhuh.farmlife.common.entities.DomesticTribull;
import codyhuh.farmlife.common.entities.Galliraptor;
import codyhuh.farmlife.common.entities.Platefish;
import codyhuh.farmlife.common.entities.item.GalliraptorEggEntity;
import codyhuh.farmlife.registry.FLBlocks;
import codyhuh.farmlife.registry.FLEntities;
import codyhuh.farmlife.registry.FLItems;
import net.minecraft.Util;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = FarmLife.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FLModEvents {

    @SubscribeEvent
    public static void registerCommon(FMLCommonSetupEvent event) {
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

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(FLEntities.DOMESTIC_TRIBULL.get(), DomesticTribull.createAttributes().build());
        event.put(FLEntities.GALLIRAPTOR.get(), Galliraptor.createAttributes().build());
        event.put(FLEntities.PLATEFISH.get(), Platefish.createAttributes().build());
    }
}
