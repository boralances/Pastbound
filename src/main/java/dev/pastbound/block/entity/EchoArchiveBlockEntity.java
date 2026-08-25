package dev.pastbound.block.entity;

import dev.pastbound.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public final class EchoArchiveBlockEntity extends BlockEntity {
    public static final int SHARDS_REQUIRED = 4;

    private int echoCount;
    private String memoryOwner = "";
    private long recordedAt;

    public EchoArchiveBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ECHO_ARCHIVE.get(), pos, state);
    }

    public boolean recordEcho(Player player) {
        if (isComplete()) {
            return false;
        }

        if (echoCount == 0) {
            memoryOwner = player.getName().getString();
            Level level = getLevel();
            recordedAt = level == null ? 0L : level.getGameTime();
        }

        echoCount++;
        setChanged();
        notifyClients();
        return true;
    }

    public boolean isComplete() {
        return echoCount >= SHARDS_REQUIRED;
    }

    public int getEchoCount() {
        return echoCount;
    }

    public String getMemoryOwner() {
        return memoryOwner.isBlank() ? "Unknown witness" : memoryOwner;
    }

    public long getRecordedAt() {
        return recordedAt;
    }

    public void clearMemory() {
        echoCount = 0;
        memoryOwner = "";
        recordedAt = 0L;
        setChanged();
        notifyClients();
    }

    private void notifyClients() {
        Level level = getLevel();
        if (level != null) {
            BlockState state = getBlockState();
            level.sendBlockUpdated(worldPosition, state, state, 3);
        }
    }

    @Override
    public void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        echoCount = Math.min(input.getIntOr("echo_count", 0), SHARDS_REQUIRED);
        memoryOwner = input.getStringOr("memory_owner", "");
        recordedAt = input.getLongOr("recorded_at", 0L);
    }

    @Override
    public void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("echo_count", echoCount);
        output.putString("memory_owner", memoryOwner);
        output.putLong("recorded_at", recordedAt);
    }
}
