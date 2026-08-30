package dev.pastbound.history;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;

public final class TarihBasarilari {
    private TarihBasarilari() {
    }

    public static void ver(ServerPlayer oyuncu, String yol) {
        AdvancementHolder basari = ((net.minecraft.server.level.ServerLevel) oyuncu.level()).getServer().getAdvancements().get(Identifier.fromNamespaceAndPath("pastbound", yol));
        if (basari != null) {
            oyuncu.getAdvancements().award(basari, "eylem");
        }
    }
}
