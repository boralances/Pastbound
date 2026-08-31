package dev.pastbound.history;

import dev.pastbound.ModId;
import dev.pastbound.registry.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.extensions.IBlockEntityExtension;
import net.neoforged.neoforge.event.entity.player.PlayerContainerEvent;

@EventBusSubscriber(modid = ModId.MOD_ID)
public final class TarihSandikGanimeti {
    private static final String DAGITILDI = "pastbound_tarih_religi_dagitildi";

    private TarihSandikGanimeti() {
    }

    @SubscribeEvent
    public static void sandikAcildi(PlayerContainerEvent.Open olay) {
        if (!(olay.getEntity() instanceof ServerPlayer oyuncu)) {
            return;
        }
        if (!(olay.getContainer() instanceof ChestMenu sandikMenusu)) {
            return;
        }
        Container konteyner = sandikMenusu.getContainer();
        RandomizableContainerBlockEntity rastgeleSandik = konteyner instanceof RandomizableContainerBlockEntity lootContainer ? lootContainer : null;
        if (rastgeleSandik == null) {
            BlockPos merkez = oyuncu.blockPosition();
            for (BlockPos konum : BlockPos.betweenClosed(merkez.offset(-2, -1, -2), merkez.offset(2, 2, 2))) {
                if (oyuncu.level().getBlockEntity(konum) instanceof RandomizableContainerBlockEntity aday && aday.getLootTable() != null) {
                    rastgeleSandik = aday;
                    break;
                }
            }
        }
        if (rastgeleSandik == null || !uygunSandikMi(rastgeleSandik)) {
            return;
        }
        CompoundTag veri = ((IBlockEntityExtension) rastgeleSandik).getPersistentData();
        if (veri.getBooleanOr(DAGITILDI, false)) {
            return;
        }
        boolean relicCikti = oyuncu.getRandom().nextFloat() < 0.10F;
        veri.putBoolean(DAGITILDI, true);
        if (!relicCikti) {
            return;
        }
        for (int i = 0; i < konteyner.getContainerSize(); i++) {
            if (konteyner.getItem(i).isEmpty()) {
                int sira = oyuncu.getRandom().nextInt(ModItems.RELIKLER.size());
                konteyner.setItem(i, new ItemStack(ModItems.RELIKLER.get(sira).get()));
                konteyner.setChanged();
                oyuncu.sendSystemMessage(net.minecraft.network.chat.Component.translatable("message.pastbound.chest.relic_found"));
                return;
            }
        }
    }

    private static boolean uygunSandikMi(RandomizableContainerBlockEntity sandik) {
        return sandik.getLootTable() != null;
    }
}
