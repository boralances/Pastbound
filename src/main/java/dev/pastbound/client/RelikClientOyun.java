package dev.pastbound.client;

import dev.pastbound.ModId;
import dev.pastbound.network.PastboundPaketi;
import dev.pastbound.client.ui.RelikDefteriEkrani;
import dev.pastbound.client.ui.TarihCanlandirmaEkrani;
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
        if (RelikClient.AKTIFLESTIRME_KISAYOLU.consumeClick()) {
            oyuncu.connection.send(new ServerboundCustomPayloadPacket(PastboundPaketi.etkinlestir()));
        }
        if (RelikClient.TARIH_KONTROL_KISAYOLU.consumeClick()) {
            oyuncu.connection.send(new ServerboundCustomPayloadPacket(PastboundPaketi.kontroluAl()));
        }
    }
}
