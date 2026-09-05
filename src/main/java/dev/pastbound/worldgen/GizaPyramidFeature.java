package dev.pastbound.worldgen;

import dev.pastbound.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class GizaPyramidFeature extends Feature<NoneFeatureConfiguration> {
    public GizaPyramidFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos surface = level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE_WG, context.origin());
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        for (int x = -7; x <= 7; x++) {
            for (int z = -7; z <= 7; z++) {
                pos.set(surface.getX() + x, surface.getY() - 1, surface.getZ() + z);
                if (!isDesertFoundation(level, pos)) {
                    return false;
                }
            }
        }

        for (int y = 0; y < 8; y++) {
            int radius = 7 - y;
            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    if (Math.abs(x) == radius || Math.abs(z) == radius || y == 0) {
                        pos.set(surface.getX() + x, surface.getY() + y, surface.getZ() + z);
                        level.setBlock(pos, ModBlocks.EGYPTIAN_SANDSTONE.get().defaultBlockState(), 2);
                    }
                }
            }
        }

        for (int y = 1; y < 7; y++) {
            pos.set(surface.getX(), surface.getY() + y, surface.getZ());
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
        }
        pos.set(surface.getX(), surface.getY() + 8, surface.getZ());
        level.setBlock(pos, ModBlocks.EGYPTIAN_GOLD_INLAY.get().defaultBlockState(), 2);

        for (int x = -3; x <= 3; x++) {
            pos.set(surface.getX() + x, surface.getY() + 1, surface.getZ() - 7);
            level.setBlock(pos, ModBlocks.EGYPTIAN_HIEROGLYPH.get().defaultBlockState(), 2);
        }
        pos.set(surface.getX(), surface.getY(), surface.getZ() + 9);
        level.setBlock(pos, ModBlocks.EGYPTIAN_SPHINX.get().defaultBlockState(), 2);
        return true;
    }

    private static boolean isDesertFoundation(WorldGenLevel level, BlockPos pos) {
        return level.getBlockState(pos).is(Blocks.SAND)
                || level.getBlockState(pos).is(Blocks.RED_SAND)
                || level.getBlockState(pos).is(Blocks.SANDSTONE)
                || level.getBlockState(pos).is(Blocks.TERRACOTTA);
    }
}
