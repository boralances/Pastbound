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
    private final NonNullList<ItemStack> esyalar = NonNullList.withSize(1, ItemStack.EMPTY);

    public ResonancePillarBlockEntity(BlockPos konum, BlockState durum) {
        super(ModBlockEntities.RESONANCE_PILLAR.get(), konum, durum);
    }

    @Override
    public int getContainerSize() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return esyalar.get(0).isEmpty();
    }

    @Override
    public ItemStack getItem(int slot) {
        return slot == 0 ? esyalar.get(0) : ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeItem(int slot, int miktar) {
        if (slot != 0) {
            return ItemStack.EMPTY;
        }
        ItemStack sonuc = net.minecraft.world.ContainerHelper.removeItem(esyalar, 0, miktar);
        if (!sonuc.isEmpty()) {
            setChanged();
        }
        return sonuc;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        if (slot != 0) {
            return ItemStack.EMPTY;
        }
        ItemStack sonuc = esyalar.get(0);
        esyalar.set(0, ItemStack.EMPTY);
        return sonuc;
    }

    @Override
    public void setItem(int slot, ItemStack yigin) {
        if (slot != 0 || !yigin.isEmpty() && !yigin.is(ModItems.TIME_STONE.get())) {
            return;
        }
        esyalar.set(0, yigin.copyWithCount(Math.min(1, yigin.getCount())));
        setChanged();
        if (level instanceof net.minecraft.server.level.ServerLevel seviye && !esyalar.get(0).isEmpty()) {
            seviye.setBlock(worldPosition, getBlockState().setValue(ResonancePillarBlock.CHARGED, true), 3);
            seviye.scheduleTick(worldPosition, getBlockState().getBlock(), ResonancePillarBlock.ACTIVE_TICKS);
        }
    }

    @Override
    public boolean stillValid(Player oyuncu) {
        return level != null && level.getBlockEntity(worldPosition) == this && oyuncu.distanceToSqr(worldPosition.getX() + 0.5D, worldPosition.getY() + 0.5D, worldPosition.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public void clearContent() {
        esyalar.set(0, ItemStack.EMPTY);
        setChanged();
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack yigin) {
        return slot == 0 && yigin.is(ModItems.TIME_STONE.get());
    }

    @Override
    public void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        input.read("resonance_stone", ItemStack.CODEC).ifPresent(yigin -> esyalar.set(0, yigin));
    }

    @Override
    public void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        if (!esyalar.get(0).isEmpty()) {
            output.store("resonance_stone", ItemStack.CODEC, esyalar.get(0));
        }
    }
}
