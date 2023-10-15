package codyhuh.farmlife.registry;

import codyhuh.farmlife.FarmLife;
import codyhuh.farmlife.common.block_entities.PlatefishPlateBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FLBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, FarmLife.MOD_ID);

    public static final RegistryObject<BlockEntityType<PlatefishPlateBlockEntity>> PLATEFISH_PLATE = BLOCK_ENTITIES.register("platefish_plate", () -> BlockEntityType.Builder.of(PlatefishPlateBlockEntity::new, FLBlocks.PLATEFISH_PLATE.get()).build(null));
}

