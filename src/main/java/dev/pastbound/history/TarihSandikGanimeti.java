package dev.pastbound.history;

import dev.pastbound.ModId;
import dev.pastbound.registry.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
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
        if (!(konteyner instanceof ChestBlockEntity sandik)) {
            return;
        }
        if (!(sandik instanceof RandomizableContainerBlockEntity rastgeleSandik)) {
            return;
        }
        if (!uygunSandikMi(rastgeleSandik)) {
            return;
        }
        CompoundTag veri = ((IBlockEntityExtension) sandik).getPersistentData();
        if (veri.getBooleanOr(DAGITILDI, false)) {
            return;
        }
        if (oyuncu.getRandom().nextFloat() > nadirlik(rastgeleSandik)) {
            return;
        }
        for (int i = 0; i < konteyner.getContainerSize(); i++) {
            if (konteyner.getItem(i).isEmpty()) {
                int sira = oyuncu.getRandom().nextInt(ModItems.RELIKLER.size());
                konteyner.setItem(i, new ItemStack(ModItems.RELIKLER.get(sira).get()));
                konteyner.setChanged();
                veri.putBoolean(DAGITILDI, true);
                oyuncu.sendSystemMessage(net.minecraft.network.chat.Component.translatable("message.pastbound.chest.relic_found"));
                return;
            }
        }
    }

    private static boolean uygunSandikMi(RandomizableContainerBlockEntity sandik) {
        if (sandik.getLootTable() == null) {
            return false;
        }
        String kimlik = sandik.getLootTable().identifier().toString();
        return kimlik.contains("chests/village/") || kimlik.contains("chests/trial_chambers/") || kimlik.contains("chests/ancient_city");
    }

    private static float nadirlik(RandomizableContainerBlockEntity sandik) {
        String kimlik = sandik.getLootTable().identifier().toString();
        if (kimlik.contains("ancient_city")) {
            return 0.018F;
        }
        if (kimlik.contains("trial_chambers")) {
            return 0.032F;
        }
        return 0.045F;
    }
}
