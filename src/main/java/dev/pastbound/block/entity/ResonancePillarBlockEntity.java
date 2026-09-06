package dev.pastbound.block.entity;

import dev.pastbound.block.ResonancePillarBlock;
import dev.pastbound.registry.ModBlockEntities;
import dev.pastbound.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public final class ResonancePillarBlockEntity extends BlockEntity implements Container {
    private final NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);

    public ResonancePillarBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.RESONANCE_PILLAR.get(), pos, state);
    }

    @Override
    public int getContainerSize() {
        return 3;
    }

    @Override
    public boolean isEmpty() {
        return items.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getItem(int slot) {
        return slot >= 0 && slot < items.size() ? items.get(slot) : ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        if (slot < 0 || slot >= items.size()) {
            return ItemStack.EMPTY;
        }
        ItemStack result = net.minecraft.world.ContainerHelper.removeItem(items, slot, amount);
        if (!result.isEmpty()) {
            setChanged();
            refreshPillar();
        }
        return result;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        if (slot < 0 || slot >= items.size()) {
            return ItemStack.EMPTY;
        }
        ItemStack result = items.get(slot);
        items.set(slot, ItemStack.EMPTY);
        refreshPillar();
        return result;
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        if (!canPlaceItem(slot, stack)) {
            return;
        }
        items.set(slot, stack.copyWithCount(Math.min(1, stack.getCount())));
        setChanged();
        refreshPillar();
    }

    private void refreshPillar() {
        if (!(level instanceof net.minecraft.server.level.ServerLevel serverLevel)) {
            return;
        }
        int level = resonanceLevel();
        BlockState state = getBlockState()
                .setValue(ResonancePillarBlock.RESONANCE_LEVEL, level)
                .setValue(ResonancePillarBlock.CHARGED, !items.get(0).isEmpty());
        serverLevel.setBlock(worldPosition, state, 3);
        if (!items.get(0).isEmpty()) {
            serverLevel.scheduleTick(worldPosition, state.getBlock(), ResonancePillarBlock.activeTicks(state));
        }
    }

    private int resonanceLevel() {
        int level = items.get(0).isEmpty() ? 0 : 1;
        if (!items.get(1).isEmpty()) {
            level++;
        }
        if (!items.get(2).isEmpty()) {
            level++;
        }
        return level;
    }

    @Override
    public boolean stillValid(Player player) {
        return level != null && level.getBlockEntity(worldPosition) == this
                && player.distanceToSqr(worldPosition.getX() + 0.5D, worldPosition.getY() + 0.5D, worldPosition.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public void clearContent() {
        items.replaceAll(ignored -> ItemStack.EMPTY);
        setChanged();
        refreshPillar();
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack) {
        if (stack.isEmpty()) {
            return slot >= 0 && slot < items.size();
        }
        return slot == 0 && stack.is(ModItems.TIME_STONE.get())
                || slot == 1 && stack.is(ModItems.ECHO_SHARD.get())
                || slot == 2 && stack.is(ModItems.STEEL_PLATE.get());
    }

    @Override
    public void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        input.read("time_stone", ItemStack.CODEC).ifPresent(stack -> items.set(0, stack));
        input.read("resonance_stone", ItemStack.CODEC).ifPresent(stack -> items.set(0, stack));
        input.read("echo_shard", ItemStack.CODEC).ifPresent(stack -> items.set(1, stack));
        input.read("steel_plate", ItemStack.CODEC).ifPresent(stack -> items.set(2, stack));
    }

    @Override
    public void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        if (!items.get(0).isEmpty()) {
            output.store("time_stone", ItemStack.CODEC, items.get(0));
        }
        if (!items.get(1).isEmpty()) {
            output.store("echo_shard", ItemStack.CODEC, items.get(1));
        }
        if (!items.get(2).isEmpty()) {
            output.store("steel_plate", ItemStack.CODEC, items.get(2));
        }
    }
}
