package dev.pastbound.block;

import dev.pastbound.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class OliveLeavesBlock extends Block {
    public OliveLeavesBlock(BlockBehaviour.Properties properties) {
        super(properties.randomTicks());
    }

    @Override
    public void randomTick(net.minecraft.world.level.block.state.BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextInt(48) == 0) {
            popResource(level, pos, new ItemStack(ModItems.OLIVE.get()));
        }
    }
}
