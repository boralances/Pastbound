package dev.pastbound.block;

import dev.pastbound.block.entity.EchoArchiveBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public final class EchoArchiveBlock extends Block implements EntityBlock {
    public EchoArchiveBlock(BlockBehaviour.Properties ozellikler) {
        super(ozellikler);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos konum, BlockState durum) {
        return new EchoArchiveBlockEntity(konum, durum);
    }
}
