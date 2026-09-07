package dev.pastbound.block;

import dev.pastbound.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class OliveSaplingBlock extends Block {
    public OliveSaplingBlock(BlockBehaviour.Properties properties) {
        super(properties.randomTicks());
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextInt(7) != 0 || !level.getBlockState(pos.below()).is(net.minecraft.tags.BlockTags.DIRT)) {
            return;
        }
        growTree(level, pos);
    }

    private static void growTree(ServerLevel level, BlockPos pos) {
        for (int y = 0; y < 4; y++) {
            level.setBlock(pos.above(y), ModBlocks.OLIVE_LOG.get().defaultBlockState(), 3);
        }
        for (int dx = -2; dx <= 2; dx++) {
            for (int dz = -2; dz <= 2; dz++) {
                if (Math.abs(dx) + Math.abs(dz) <= 3) {
                    level.setBlock(pos.above(4).offset(dx, 0, dz), ModBlocks.OLIVE_LEAVES.get().defaultBlockState(), 3);
                }
            }
        }
        level.setBlock(pos.above(5), ModBlocks.OLIVE_LEAVES.get().defaultBlockState(), 3);
    }
}
