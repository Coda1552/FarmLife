package teamdraco.wcfarmlife.common.world.features;

import com.mojang.serialization.Codec;
import teamdraco.wcfarmlife.WCFarmLife;
import teamdraco.wcfarmlife.common.entities.GalliraptorEntity;
import teamdraco.wcfarmlife.registry.WCFarmLifeBlocks;
import teamdraco.wcfarmlife.registry.WCFarmLifeEntities;
import teamdraco.wcfarmlife.registry.WCFarmLifeStructures;
import net.minecraft.block.Blocks;
import net.minecraft.entity.SpawnReason;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Random;

import net.minecraft.world.gen.feature.structure.Structure.IStartFactory;

public class GreenhouseStructure extends Structure<NoFeatureConfig> {

    public GreenhouseStructure(Codec<NoFeatureConfig> p_i231977_1_) {
        super(p_i231977_1_);
    }

    @Override
    public GenerationStage.Decoration step() {
        return GenerationStage.Decoration.SURFACE_STRUCTURES;
    }

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return GreenhouseStructure.Start::new;
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGen, BiomeProvider biomeSource, long seed, SharedSeedRandom rand, int chunkPosX, int chunkPosZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {
        return chunkPosX > 4 && chunkPosZ > 4;
    }

    public static class Start extends StructureStart<NoFeatureConfig>  {
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        @Override
        public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biome, NoFeatureConfig config) {
            Rotation rotation = Rotation.values()[this.random.nextInt(Rotation.values().length)];
            int x = (chunkX << 4) + 7;
            int z = (chunkZ << 4) + 7;
            int surfaceY = Math.max(generator.getFirstOccupiedHeight(x + 12, z + 12, Heightmap.Type.WORLD_SURFACE_WG), generator.getSpawnHeight());
            BlockPos blockpos = new BlockPos(x, surfaceY, z);
            Piece.start(templateManagerIn, blockpos, rotation, this.pieces, this.random);
            this.calculateBoundingBox();
        }
    }

    public static class Piece extends TemplateStructurePiece {
        private ResourceLocation resourceLocation;
        private Rotation rotation;

        public Piece(TemplateManager templateManagerIn, ResourceLocation resourceLocationIn, BlockPos pos, Rotation rotationIn) {
            super(WCFarmLifeStructures.GREENHOUSE_PIECE, 0);
            this.resourceLocation = resourceLocationIn;
            this.templatePosition = pos;
            this.rotation = rotationIn;
            this.setupPiece(templateManagerIn);
        }

        public Piece(TemplateManager templateManagerIn, CompoundNBT tagCompound) {
            super(WCFarmLifeStructures.GREENHOUSE_PIECE, tagCompound);
            this.resourceLocation = new ResourceLocation(tagCompound.getString("Template"));
            this.rotation = Rotation.valueOf(tagCompound.getString("Rot"));
            this.setupPiece(templateManagerIn);
        }

        public static void start(TemplateManager templateManager, BlockPos pos, Rotation rotation, List<StructurePiece> pieceList, Random random) {
            int x = pos.getX();
            int z = pos.getZ();
            BlockPos rotationOffSet = new BlockPos(0, 2, 0).rotate(rotation);
            BlockPos blockpos = rotationOffSet.offset(x, pos.getY(), z);
            pieceList.add(new Piece(templateManager, new ResourceLocation(WCFarmLife.MOD_ID, "greenhouse"), blockpos, rotation));
        }

        private void setupPiece(TemplateManager templateManager) {
            Template template = templateManager.getOrCreate(this.resourceLocation);
            PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE);
            this.setup(template, this.templatePosition, placementsettings);
        }

        @Override
        protected void addAdditionalSaveData(CompoundNBT tagCompound) {
            super.addAdditionalSaveData(tagCompound);
            tagCompound.putString("Template", this.resourceLocation.toString());
            tagCompound.putString("Rot", this.rotation.name());
        }

        @Override
        public boolean postProcess(ISeedReader seedReader, StructureManager structureManager, ChunkGenerator chunkGenerator, Random randomIn, MutableBoundingBox structureBoundingBoxIn, ChunkPos chunkPos, BlockPos pos) {
            PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_AND_AIR);
            BlockPos blockpos = BlockPos.ZERO;
            this.templatePosition.offset(Template.calculateRelativePosition(placementsettings, new BlockPos(-blockpos.getX(), 0, -blockpos.getZ())));
            return super.postProcess(seedReader, structureManager, chunkGenerator, randomIn, structureBoundingBoxIn, chunkPos, pos);
        }

        @Override
        protected void handleDataMarker(String function, BlockPos pos, IServerWorld worldIn, Random rand, MutableBoundingBox sbb) {
            if ("galliraptor".equals(function)) {
                worldIn.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
                GalliraptorEntity entity = WCFarmLifeEntities.GALLIRAPTOR.get().create(worldIn.getLevel());
                if (entity != null) {
                    if (rand.nextInt(5) == 0) entity.setAge(-24000);
                    entity.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                    entity.setVariant(rand.nextInt(5));
                    entity.finalizeSpawn(worldIn, worldIn.getCurrentDifficultyAt(pos), SpawnReason.STRUCTURE, null, null);
                    worldIn.addFreshEntity(entity);
                }
            }
            if ("flower".equals(function)) {
                if (rand.nextFloat() < 0.2F) worldIn.setBlock(pos, WCFarmLifeBlocks.PEACOCK_BURST_POPPY.get().defaultBlockState(), 2);
                else if (rand.nextFloat() < 0.2F) worldIn.setBlock(pos, WCFarmLifeBlocks.ELECTRIC_BURST_POPPY.get().defaultBlockState(), 2);
                else if (rand.nextFloat() < 0.2F) worldIn.setBlock(pos, WCFarmLifeBlocks.FANCY_BURST_POPPY.get().defaultBlockState(), 2);
                else if (rand.nextFloat() < 0.2F) worldIn.setBlock(pos, WCFarmLifeBlocks.OLIVE_BURST_POPPY.get().defaultBlockState(), 2);
                else if (rand.nextFloat() < 0.2F) worldIn.setBlock(pos, WCFarmLifeBlocks.RUSTY_BURST_POPPY.get().defaultBlockState(), 2);
                else if (rand.nextFloat() < 0.2F) worldIn.setBlock(pos, WCFarmLifeBlocks.SUNSTREAK_BURST_POPPY.get().defaultBlockState(), 2);
                else worldIn.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
            }
        }
    }
}