package dev.pastbound.worldgen;

import dev.pastbound.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class EgyptianRuinFeature extends Feature<NoneFeatureConfiguration> {
    public EgyptianRuinFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos surface = level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE_WG, context.origin());
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        int height = 4 + context.random().nextInt(4);

        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                if (Math.abs(x) == 2 || Math.abs(z) == 2 || (Math.abs(x) == 1 && Math.abs(z) == 1)) {
                    pos.set(surface.getX() + x, surface.getY() - 1, surface.getZ() + z);
                    if (level.getBlockState(pos).is(Blocks.SAND) || level.getBlockState(pos).is(Blocks.RED_SAND)
                            || level.getBlockState(pos).is(Blocks.TERRACOTTA) || level.getBlockState(pos).is(Blocks.RED_SANDSTONE)) {
                        level.setBlock(pos, ModBlocks.EGYPTIAN_SANDSTONE.get().defaultBlockState(), 2);
                    }
                }
            }
        }

        for (int y = 0; y < height; y++) {
            pos.set(surface.getX(), surface.getY() + y, surface.getZ());
            level.setBlock(pos, ModBlocks.EGYPTIAN_OBELISK.get().defaultBlockState(), 2);
            if (y == height - 1) {
                pos.set(surface.getX(), surface.getY() + y + 1, surface.getZ());
                level.setBlock(pos, ModBlocks.EGYPTIAN_GOLD_INLAY.get().defaultBlockState(), 2);
            }
        }

        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                if (Math.abs(x) + Math.abs(z) == 1) {
                    pos.set(surface.getX() + x, surface.getY(), surface.getZ() + z);
                    level.setBlock(pos, ModBlocks.EGYPTIAN_GOLD_INLAY.get().defaultBlockState(), 2);
                }
            }
        }

        for (int x = -2; x <= 2; x++) {
            pos.set(surface.getX() + x, surface.getY() + 1, surface.getZ() - 2);
            level.setBlock(pos, ModBlocks.EGYPTIAN_HIEROGLYPH.get().defaultBlockState(), 2);
        }
        pos.set(surface.getX(), surface.getY(), surface.getZ() + 3);
        level.setBlock(pos, ModBlocks.EGYPTIAN_SPHINX.get().defaultBlockState(), 2);
        return true;
    }
}
