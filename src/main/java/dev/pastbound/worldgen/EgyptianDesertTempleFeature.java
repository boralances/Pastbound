package dev.pastbound.worldgen;

import dev.pastbound.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class EgyptianDesertTempleFeature extends Feature<NoneFeatureConfiguration> {
    public EgyptianDesertTempleFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos surface = level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE_WG, context.origin());
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        for (int x = -15; x <= 15; x++) {
            for (int z = -12; z <= 12; z++) {
                pos.set(surface.getX() + x, surface.getY() - 1, surface.getZ() + z);
                if (!isDesertFoundation(level, pos)) {
                    return false;
                }
            }
        }

        int floorY = surface.getY();
        for (int x = -15; x <= 15; x++) {
            for (int z = -12; z <= 12; z++) {
                set(level, pos, surface, x, floorY, z, ModBlocks.EGYPTIAN_SANDSTONE.get().defaultBlockState());
            }
        }

        for (int y = 1; y <= 7; y++) {
            for (int x = -15; x <= 15; x++) {
                wall(level, pos, surface, x, floorY + y, -12);
                wall(level, pos, surface, x, floorY + y, 12);
            }
            for (int z = -11; z <= 11; z++) {
                wall(level, pos, surface, -15, floorY + y, z);
                wall(level, pos, surface, 15, floorY + y, z);
            }
        }

        for (int x = -15; x <= 15; x++) {
            wall(level, pos, surface, x, floorY + 8, -12);
            wall(level, pos, surface, x, floorY + 8, 12);
        }
        for (int z = -11; z <= 11; z++) {
            wall(level, pos, surface, -15, floorY + 8, z);
            wall(level, pos, surface, 15, floorY + 8, z);
        }

        for (int x = -14; x <= 14; x++) {
            for (int z = -11; z <= 11; z++) {
                if (Math.abs(x) < 2 || Math.abs(z) < 2) {
                    set(level, pos, surface, x, floorY + 1, z, ModBlocks.EGYPTIAN_GOLD_INLAY.get().defaultBlockState());
                }
            }
        }

        for (int z = -9; z <= 9; z += 6) {
            for (int x : new int[] {-12, 12}) {
                column(level, pos, surface, x, floorY + 1, z);
            }
        }
        for (int x : new int[] {-9, 0, 9}) {
            for (int z : new int[] {-10, 10}) {
                column(level, pos, surface, x, floorY + 1, z);
            }
        }

        for (int x = -10; x <= 10; x += 4) {
            set(level, pos, surface, x, floorY + 3, -12, ModBlocks.EGYPTIAN_HIEROGLYPH.get().defaultBlockState());
            set(level, pos, surface, x, floorY + 3, 12, ModBlocks.EGYPTIAN_HIEROGLYPH.get().defaultBlockState());
        }

        for (int x : new int[] {-8, 8}) {
            for (int z : new int[] {-7, 7}) {
                obelisk(level, pos, surface, x, floorY + 1, z);
            }
        }

        for (int x = -3; x <= 3; x++) {
            for (int z = -3; z <= 3; z++) {
                set(level, pos, surface, x, floorY + 1, z, ModBlocks.EGYPTIAN_SANDSTONE.get().defaultBlockState());
            }
        }
        set(level, pos, surface, 0, floorY + 1, 0, ModBlocks.EGYPTIAN_GOLD_INLAY.get().defaultBlockState());
        set(level, pos, surface, 0, floorY + 2, 0, ModBlocks.EGYPTIAN_OBELISK.get().defaultBlockState());

        for (int y = 1; y <= 4; y++) {
            set(level, pos, surface, 0, floorY + y, -12, Blocks.AIR.defaultBlockState());
            set(level, pos, surface, 1, floorY + y, -12, Blocks.AIR.defaultBlockState());
            set(level, pos, surface, -1, floorY + y, -12, Blocks.AIR.defaultBlockState());
        }
        set(level, pos, surface, 0, floorY, -14, ModBlocks.EGYPTIAN_SPHINX.get().defaultBlockState());
        set(level, pos, surface, -4, floorY, -14, ModBlocks.EGYPTIAN_SPHINX.get().defaultBlockState());
        return true;
    }

    private static void wall(WorldGenLevel level, BlockPos.MutableBlockPos pos, BlockPos origin, int x, int y, int z) {
        set(level, pos, origin, x, y, z, ModBlocks.EGYPTIAN_SANDSTONE.get().defaultBlockState());
    }

    private static void column(WorldGenLevel level, BlockPos.MutableBlockPos pos, BlockPos origin, int x, int y, int z) {
        for (int i = 0; i < 6; i++) {
            set(level, pos, origin, x, y + i, z, ModBlocks.EGYPTIAN_OBELISK.get().defaultBlockState());
        }
        set(level, pos, origin, x, y + 6, z, ModBlocks.EGYPTIAN_GOLD_INLAY.get().defaultBlockState());
    }

    private static void obelisk(WorldGenLevel level, BlockPos.MutableBlockPos pos, BlockPos origin, int x, int y, int z) {
        for (int i = 0; i < 4; i++) {
            set(level, pos, origin, x, y + i, z, ModBlocks.EGYPTIAN_OBELISK.get().defaultBlockState());
        }
        set(level, pos, origin, x, y + 4, z, ModBlocks.EGYPTIAN_GOLD_INLAY.get().defaultBlockState());
    }

    private static void set(WorldGenLevel level, BlockPos.MutableBlockPos pos, BlockPos origin, int x, int y, int z, net.minecraft.world.level.block.state.BlockState state) {
        pos.set(origin.getX() + x, y, origin.getZ() + z);
        level.setBlock(pos, state, 2);
    }

    private static boolean isDesertFoundation(WorldGenLevel level, BlockPos pos) {
        return level.getBlockState(pos).is(Blocks.SAND)
                || level.getBlockState(pos).is(Blocks.RED_SAND)
                || level.getBlockState(pos).is(Blocks.SANDSTONE)
                || level.getBlockState(pos).is(Blocks.TERRACOTTA);
    }
}
