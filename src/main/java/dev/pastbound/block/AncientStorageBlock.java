package dev.pastbound.block;

import dev.pastbound.block.entity.AncientStorageBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public final class AncientStorageBlock extends Block implements EntityBlock {
    public AncientStorageBlock(BlockBehaviour.Properties ozellikler) {
        super(ozellikler);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos konum, BlockState durum) {
        return new AncientStorageBlockEntity(konum, durum);
    }
}
