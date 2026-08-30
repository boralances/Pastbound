package dev.pastbound.block.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

public final class AncientStorageBlockEntity extends BlockEntity implements Container {
    public static final int SLOT_SAYISI = 54;
    private static final int OYUNCU_ENVANTERISI_SONU = 36;
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
        for (ItemStack yigin : esyalar) {
            if (!yigin.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getItem(int slot) {
        return gecerliSlotMu(slot) ? esyalar.get(slot) : ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeItem(int slot, int miktar) {
        if (!gecerliSlotMu(slot) || miktar <= 0) {
            return ItemStack.EMPTY;
        }
        ItemStack sonuc = net.minecraft.world.ContainerHelper.removeItem(esyalar, slot, miktar);
        if (!sonuc.isEmpty()) {
            setChanged();
        }
        return sonuc;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        if (!gecerliSlotMu(slot)) {
            return ItemStack.EMPTY;
        }
        ItemStack sonuc = esyalar.get(slot);
        esyalar.set(slot, ItemStack.EMPTY);
        return sonuc;
    }

    @Override
    public void setItem(int slot, ItemStack yigin) {
        if (gecerliSlotMu(slot)) {
            ItemStack guvenliYigin = yigin == null ? ItemStack.EMPTY : yigin;
            if (guvenliYigin.getCount() > guvenliYigin.getMaxStackSize()) {
                guvenliYigin.setCount(guvenliYigin.getMaxStackSize());
            }
            esyalar.set(slot, guvenliYigin);
            setChanged();
        }
    }

    @Override
    public boolean stillValid(Player oyuncu) {
        return level != null && level.getBlockEntity(worldPosition) == this && oyuncu.distanceToSqr(worldPosition.getX() + 0.5D, worldPosition.getY() + 0.5D, worldPosition.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public void clearContent() {
        for (int i = 0; i < SLOT_SAYISI; i++) {
            esyalar.set(i, ItemStack.EMPTY);
        }
        setChanged();
    }

    public int tarihEsyaArsivle(Player oyuncu) {
        int tasinanToplam = 0;
        int envanterSonu = Math.min(OYUNCU_ENVANTERISI_SONU, oyuncu.getInventory().getContainerSize());
        for (int i = 0; i < envanterSonu; i++) {
            ItemStack kaynak = oyuncu.getInventory().getItem(i);
            if (!tarihEsyaMi(kaynak)) {
                continue;
            }
            tasinanToplam += yiginEkle(kaynak);
        }
        if (tasinanToplam > 0) {
            setChanged();
        }
        return tasinanToplam;
    }

    public void sirala() {
        List<ItemStack> birlesmis = new ArrayList<>();
        for (ItemStack yigin : esyalar) {
            if (yigin.isEmpty()) {
                continue;
            }
            int kalan = yigin.getCount();
            for (ItemStack hedef : birlesmis) {
                if (!ItemStack.isSameItemSameComponents(hedef, yigin) || hedef.getCount() >= hedef.getMaxStackSize()) {
                    continue;
                }
                int eklenecek = Math.min(kalan, hedef.getMaxStackSize() - hedef.getCount());
                hedef.grow(eklenecek);
                kalan -= eklenecek;
                if (kalan == 0) {
                    break;
                }
            }
            while (kalan > 0 && birlesmis.size() < SLOT_SAYISI) {
                int eklenecek = Math.min(kalan, yigin.getMaxStackSize());
                ItemStack yeni = yigin.copy();
                yeni.setCount(eklenecek);
                birlesmis.add(yeni);
                kalan -= eklenecek;
            }
        }
        birlesmis.sort(Comparator.comparing(yigin -> yigin.getHoverName().getString(), String.CASE_INSENSITIVE_ORDER));
        for (int i = 0; i < SLOT_SAYISI; i++) {
            esyalar.set(i, i < birlesmis.size() ? birlesmis.get(i) : ItemStack.EMPTY);
        }
        setChanged();
    }

    private int yiginEkle(ItemStack kaynak) {
        int baslangicMiktari = kaynak.getCount();
        int kalan = baslangicMiktari;
        for (int i = 0; i < SLOT_SAYISI && kalan > 0; i++) {
            ItemStack hedef = esyalar.get(i);
            if (!hedef.isEmpty() && ItemStack.isSameItemSameComponents(hedef, kaynak)) {
                int bosluk = hedef.getMaxStackSize() - hedef.getCount();
                int eklenecek = Math.min(kalan, Math.max(0, bosluk));
                if (eklenecek > 0) {
                    hedef.grow(eklenecek);
                    kalan -= eklenecek;
                }
            }
        }
        for (int i = 0; i < SLOT_SAYISI && kalan > 0; i++) {
            if (!esyalar.get(i).isEmpty()) {
                continue;
            }
            int eklenecek = Math.min(kalan, kaynak.getMaxStackSize());
            ItemStack yeniYigin = kaynak.copy();
            yeniYigin.setCount(eklenecek);
            esyalar.set(i, yeniYigin);
            kalan -= eklenecek;
        }
        int tasinan = baslangicMiktari - kalan;
        if (tasinan > 0) {
            kaynak.shrink(tasinan);
        }
        return tasinan;
    }

    private boolean tarihEsyaMi(ItemStack yigin) {
        if (yigin.isEmpty()) {
            return false;
        }
        if (yigin.is(ModItems.ECHO_SHARD.get()) || yigin.is(ModItems.MEMORY_LENS.get()) || yigin.is(ModItems.CHRONICLE_SCRAP.get()) || yigin.is(ModItems.HISTORY_INK.get()) || yigin.is(ModItems.TIME_STONE.get()) || yigin.is(ModItems.ECHO_SEAL.get()) || yigin.is(ModItems.CHRONICLE_COMPASS.get()) || yigin.is(ModItems.ZAMAN_MAKINESI.get()) || yigin.is(ModItems.RAW_STEEL.get()) || yigin.is(ModItems.STEEL_INGOT.get()) || yigin.is(ModItems.STEEL_PLATE.get())) {
            return true;
        }
        for (var relik : ModItems.RELIKLER) {
            if (yigin.is(relik.get())) {
                return true;
            }
        }
        return false;
    }

    private boolean gecerliSlotMu(int slot) {
        return slot >= 0 && slot < SLOT_SAYISI;
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
