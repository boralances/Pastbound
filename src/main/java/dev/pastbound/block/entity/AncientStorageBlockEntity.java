package dev.pastbound.block.entity;

import dev.pastbound.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public final class AncientStorageBlockEntity extends BlockEntity implements Container {
    public static final int SLOT_SAYISI = 54;
    private final NonNullList<ItemStack> esyalar = NonNullList.withSize(SLOT_SAYISI, ItemStack.EMPTY);

    public AncientStorageBlockEntity(BlockPos konum, BlockState durum) {
        super(ModBlockEntities.ANCIENT_STORAGE.get(), konum, durum);
    }

    @Override
    public int getContainerSize() {
        return SLOT_SAYISI;
    }

    @Override
    public boolean isEmpty() {
        return esyalar.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getItem(int slot) {
        return slot < 0 || slot >= SLOT_SAYISI ? ItemStack.EMPTY : esyalar.get(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int miktar) {
        ItemStack sonuc = net.minecraft.world.ContainerHelper.removeItem(esyalar, slot, miktar);
        if (!sonuc.isEmpty()) {
            setChanged();
        }
        return sonuc;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        ItemStack sonuc = esyalar.get(slot);
        esyalar.set(slot, ItemStack.EMPTY);
        return sonuc;
    }

    @Override
    public void setItem(int slot, ItemStack yigin) {
        if (slot >= 0 && slot < SLOT_SAYISI) {
            esyalar.set(slot, yigin);
            if (yigin.getCount() > yigin.getMaxStackSize()) {
                yigin.setCount(yigin.getMaxStackSize());
            }
            setChanged();
        }
    }

    @Override
    public boolean stillValid(Player oyuncu) {
        return level != null && level.getBlockEntity(worldPosition) == this && oyuncu.distanceToSqr(worldPosition.getX() + 0.5D, worldPosition.getY() + 0.5D, worldPosition.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public void clearContent() {
        esyalar.clear();
        setChanged();
    }

    @Override
    public void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        clearContent();
        for (int i = 0; i < SLOT_SAYISI; i++) {
            final int slot = i;
            input.read("slot_" + i, ItemStack.CODEC).ifPresent(yigin -> esyalar.set(slot, yigin));
        }
    }

    @Override
    public void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        for (int i = 0; i < SLOT_SAYISI; i++) {
            ItemStack yigin = esyalar.get(i);
            if (!yigin.isEmpty()) {
                output.store("slot_" + i, ItemStack.CODEC, yigin);
            }
        }
    }
}
