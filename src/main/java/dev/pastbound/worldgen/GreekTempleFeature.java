package dev.pastbound.worldgen;

import dev.pastbound.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class GreekTempleFeature extends Feature<NoneFeatureConfiguration> {
    public GreekTempleFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos surface = level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE_WG, context.origin());
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos check = new BlockPos.MutableBlockPos();

        for (int x = -4; x <= 4; x++) {
            for (int z = -3; z <= 3; z++) {
                pos.set(surface.getX() + x, surface.getY() - 1, surface.getZ() + z);
                if (!isSuitableFoundation(level, pos)) {
                    return false;
                }
            }
        }

        for (int y = 0; y < 3; y++) {
            for (int x = -4 + y; x <= 4 - y; x++) {
                for (int z = -3 + y; z <= 3 - y; z++) {
                    pos.set(surface.getX() + x, surface.getY() + y, surface.getZ() + z);
                    level.setBlock(pos, ModBlocks.GREEK_MARBLE.get().defaultBlockState(), 2);
                }
            }
        }

        for (int x : new int[]{-3, -1, 1, 3}) {
            for (int z : new int[]{-2, 2}) {
                for (int y = 3; y <= 6; y++) {
                    pos.set(surface.getX() + x, surface.getY() + y, surface.getZ() + z);
                    level.setBlock(pos, ModBlocks.GREEK_COLUMN.get().defaultBlockState(), 2);
                }
            }
        }

        for (int x = -3; x <= 3; x++) {
            for (int z = -2; z <= 2; z++) {
                pos.set(surface.getX() + x, surface.getY() + 7, surface.getZ() + z);
                level.setBlock(pos, ModBlocks.GREEK_MARBLE.get().defaultBlockState(), 2);
            }
        }
        pos.set(surface.getX(), surface.getY() + 3, surface.getZ());
        level.setBlock(pos, ModBlocks.GREEK_OLIVE_ALTAR.get().defaultBlockState(), 2);
        check.set(pos.getX(), pos.getY() + 1, pos.getZ());
        if (level.isEmptyBlock(check)) {
            level.setBlock(check, Blocks.LANTERN.defaultBlockState(), 2);
        }
        return true;
    }

    private static boolean isSuitableFoundation(WorldGenLevel level, BlockPos pos) {
        return level.getBlockState(pos).is(Blocks.GRASS_BLOCK)
                || level.getBlockState(pos).is(Blocks.DIRT)
                || level.getBlockState(pos).is(Blocks.SAND)
                || level.getBlockState(pos).is(Blocks.STONE);
    }
}
