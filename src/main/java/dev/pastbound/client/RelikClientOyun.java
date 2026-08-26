package dev.pastbound.client;

import dev.pastbound.ModId;
import dev.pastbound.network.PastboundPaketi;
import dev.pastbound.client.ui.RelikDefteriEkrani;
import dev.pastbound.client.ui.PastboundDilEkrani;
import dev.pastbound.client.ui.TarihCanlandirmaEkrani;
import dev.pastbound.client.ui.TarihKoyluKonusmaEkrani;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

@EventBusSubscriber(modid = ModId.MOD_ID, value = Dist.CLIENT)
public final class RelikClientOyun {
    private RelikClientOyun() {
    }

    public static void konusmaPaketiniIsle(String donem, int konusmaci) {
        TarihKoyluKonusmaEkrani.ac(donem, konusmaci);
    }

    public static void konusmaCevabiniIsle(String donem, int konusmaci, int secim) {
        TarihKoyluKonusmaEkrani.cevabiGeldi(donem, konusmaci, secim);
    }

    public static void canlandirmaPaketiniIsle(String donem, String sayac) {
        try {
            TarihCanlandirmaEkrani.guncelle(donem, Integer.parseInt(sayac));
        } catch (NumberFormatException hata) {
            TarihCanlandirmaEkrani.guncelle(donem, 0);
        }
    }

    @SubscribeEvent
    public static void istemciTik(ClientTickEvent.Post olay) {
        Minecraft minecraft = Minecraft.getInstance();
        LocalPlayer oyuncu = minecraft.player;
        if (oyuncu == null || minecraft.level == null) {
            return;
        }
        if (RelikClient.DEFTER_KISAYOLU.consumeClick()) {
            minecraft.setScreenAndShow(new RelikDefteriEkrani());
        }
        if (RelikClient.DIL_KISAYOLU.consumeClick()) {
            minecraft.setScreenAndShow(new PastboundDilEkrani());
        }
        if (RelikClient.AKTIFLESTIRME_KISAYOLU.consumeClick()) {
            oyuncu.connection.send(new ServerboundCustomPayloadPacket(PastboundPaketi.etkinlestir()));
        }
        if (RelikClient.TARIH_KONTROL_KISAYOLU.consumeClick()) {
            oyuncu.connection.send(new ServerboundCustomPayloadPacket(PastboundPaketi.kontroluAl()));
        }
        for (int i = 0; i < RelikClient.RELIK_YUVA_KISAYOLLARI.length; i++) {
            if (RelikClient.RELIK_YUVA_KISAYOLLARI[i].consumeClick()) {
                oyuncu.connection.send(new ServerboundCustomPayloadPacket(PastboundPaketi.yuvaEtkinlestir(i)));
            }
        }
    }
}
