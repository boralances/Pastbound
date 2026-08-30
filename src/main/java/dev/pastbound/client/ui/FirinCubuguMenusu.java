package dev.pastbound.client.ui;

import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import dev.pastbound.history.TarihBasarilari;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;

public final class FirinCubuguMenusu extends FurnaceMenu {
    private final SimpleContainer hazne;
    private final SimpleContainerData veri;
    private int yanmaSuresi;
    private int yanmaToplami;
    private int pisirmeSuresi;
    private int pisirmeToplami;
    private final boolean gelistirilmis;

    private FirinCubuguMenusu(int kimlik, Inventory envanter, SimpleContainer hazne, SimpleContainerData veri, boolean gelistirilmis) {
        super(kimlik, envanter, hazne, veri);
        this.hazne = hazne;
        this.veri = veri;
        this.gelistirilmis = gelistirilmis;
    }

    public static FirinCubuguMenusu ac(int kimlik, Inventory envanter, boolean gelistirilmis) {
        return new FirinCubuguMenusu(kimlik, envanter, new SimpleContainer(3), new SimpleContainerData(4), gelistirilmis);
    }

    public void tikle(ServerPlayer oyuncu) {
        if (!(oyuncu.level() instanceof net.minecraft.server.level.ServerLevel seviye)) {
            return;
        }
        ItemStack girdi = hazne.getItem(0);
        ItemStack yakit = hazne.getItem(1);
        ItemStack cikti = hazne.getItem(2);
        RecipeHolder<? extends AbstractCookingRecipe> tarif = null;
        if (!girdi.isEmpty()) {
            tarif = seviye.recipeAccess().getRecipeFor(RecipeType.SMELTING, new SingleRecipeInput(girdi), seviye).orElse(null);
        }
        ItemStack sonuc = tarif == null ? ItemStack.EMPTY : tarif.value().assemble(new SingleRecipeInput(girdi));
        boolean ciktiUygun = !sonuc.isEmpty() && (cikti.isEmpty() || ItemStack.isSameItemSameComponents(cikti, sonuc)) && (cikti.isEmpty() || cikti.getCount() + sonuc.getCount() <= Math.min(hazne.getMaxStackSize(), sonuc.getMaxStackSize()));
        if (!ciktiUygun) {
            pisirmeSuresi = 0;
            pisirmeToplami = 0;
        } else {
            pisirmeToplami = Math.max(1, tarif.value().cookingTime() / (gelistirilmis ? 2 : 1));
            if (yanmaSuresi <= 0 && !yakit.isEmpty()) {
                int yeniYanma = yakit.getBurnTime(RecipeType.SMELTING, seviye.fuelValues());
                if (yeniYanma > 0) {
                    yanmaSuresi = yeniYanma;
                    yanmaToplami = yeniYanma;
                    yakit.shrink(1);
                }
            }
            if (yanmaSuresi > 0) {
                yanmaSuresi--;
                pisirmeSuresi++;
                if (pisirmeSuresi >= pisirmeToplami) {
                    pisirmeSuresi = 0;
                    if (cikti.isEmpty()) {
                        hazne.setItem(2, sonuc.copy());
                    } else {
                        cikti.grow(sonuc.getCount());
                    }
                    girdi.shrink(1);
                    TarihBasarilari.ver(oyuncu, "workstations/portable_furnace");
                    if (gelistirilmis) {
                        TarihBasarilari.ver(oyuncu, "workstations/portable_furnace_master");
                    }
                }
            }
        }
        veri.set(0, yanmaSuresi);
        veri.set(1, yanmaToplami);
        veri.set(2, pisirmeSuresi);
        veri.set(3, pisirmeToplami);
        broadcastChanges();
    }

    @Override
    public boolean stillValid(Player oyuncu) {
        return oyuncu.containerMenu == this;
    }

    @Override
    public void removed(Player oyuncu) {
        super.removed(oyuncu);
        if (!oyuncu.level().isClientSide()) {
            Containers.dropContents(oyuncu.level(), oyuncu, hazne);
        }
    }

}
