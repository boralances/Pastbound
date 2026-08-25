package dev.pastbound.block;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.server.level.ServerLevel;

public final class ResonancePillarBlock extends Block {
    public static final BooleanProperty CHARGED = BooleanProperty.create("charged");
    public static final int ACTIVE_TICKS = 120;

    public ResonancePillarBlock(BlockBehaviour.Properties ozellikler) {
        super(ozellikler);
        registerDefaultState(defaultBlockState().setValue(CHARGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> kurucu) {
        kurucu.add(CHARGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext baglam) {
        return defaultBlockState();
    }

    @Override
    public void tick(BlockState durum, ServerLevel seviye, BlockPos konum, RandomSource rastgele) {
        if (durum.getValue(CHARGED)) {
            seviye.setBlock(konum, durum.setValue(CHARGED, false), 3);
        }
    }
}
