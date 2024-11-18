package codyhuh.farmlife.registry;

import codyhuh.farmlife.FarmLife;
import codyhuh.farmlife.common.block_entities.PlatterBlockEntity;
import codyhuh.farmlife.common.block_entities.SeaPlumBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FLBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, FarmLife.MOD_ID);

    public static final RegistryObject<BlockEntityType<PlatterBlockEntity>> PLATTER = BLOCK_ENTITIES.register("platter", () -> BlockEntityType.Builder.of(PlatterBlockEntity::new, FLBlocks.PLATTER.get()).build(null));
    public static final RegistryObject<BlockEntityType<SeaPlumBlockEntity>> SEA_PLUM = BLOCK_ENTITIES.register("sea_plum", () -> BlockEntityType.Builder.of(SeaPlumBlockEntity::new, FLBlocks.SEA_PLUM.get()).build(null));
}

