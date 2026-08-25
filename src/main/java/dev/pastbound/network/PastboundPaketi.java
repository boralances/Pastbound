package dev.pastbound.network;

import dev.pastbound.PastboundMemory;
import dev.pastbound.client.RelikClientOyun;
import dev.pastbound.history.TarihiKesifDunyasi;
import dev.pastbound.history.ZamanMakinesiMantigi;
import dev.pastbound.relic.RelikMantigi;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record PastboundPaketi(int islem, String birinci, String ikinci) implements CustomPacketPayload {
    public static final Type<PastboundPaketi> TIP = new Type<>(Identifier.parse("pastbound:oyun_istegi"));
    public static final StreamCodec<FriendlyByteBuf, PastboundPaketi> KODLAYICI = StreamCodec.of(
            (buf, paket) -> {
                buf.writeVarInt(paket.islem());
                buf.writeUtf(paket.birinci(), 64);
                buf.writeUtf(paket.ikinci(), 64);
            },
            buf -> new PastboundPaketi(buf.readVarInt(), buf.readUtf(64), buf.readUtf(64)));

    public static PastboundPaketi bilmece(String relik, String cevap) {
        return new PastboundPaketi(1, relik, cevap);
    }

    public static PastboundPaketi yanki(String echo, String dizi) {
        return new PastboundPaketi(5, echo, dizi);
    }

    public static PastboundPaketi zaman(String donem) {
        return new PastboundPaketi(2, donem, "");
    }

    public static PastboundPaketi etkinlestir() {
        return new PastboundPaketi(3, "", "");
    }

    public static PastboundPaketi slotYukselt() {
        return new PastboundPaketi(4, "", "");
    }

    public static PastboundPaketi kontroluAl() {
        return new PastboundPaketi(6, "", "");
    }

    public static PastboundPaketi sahne(String donem, int sayac) {
        return new PastboundPaketi(7, donem, Integer.toString(sayac));
    }

    public static PastboundPaketi don() {
        return new PastboundPaketi(8, "", "");
    }

    public static PastboundPaketi yuvaEtkinlestir(int yuva) {
        return new PastboundPaketi(9, Integer.toString(yuva), "");
    }

    public static PastboundPaketi konusma(String donem, int konusmaci) {
        return new PastboundPaketi(10, donem, Integer.toString(konusmaci));
    }

    public static PastboundPaketi konusmaSecimi(String donem, int konusmaci, int secim) {
        return new PastboundPaketi(11, donem, konusmaci + ":" + secim);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TIP;
    }

    public static void eleAl(PastboundPaketi paket, IPayloadContext baglam) {
        baglam.enqueueWork(() -> {
            if (!(baglam.player() instanceof ServerPlayer oyuncu)) {
                return;
            }
            switch (paket.islem()) {
                case 1 -> RelikMantigi.bilmeceCevapla(oyuncu, paket.birinci(), paket.ikinci());
                case 2 -> ZamanMakinesiMantigi.donemeGit(oyuncu, paket.birinci());
                case 3 -> RelikMantigi.etkinlestirIlk(oyuncu);
                case 4 -> RelikMantigi.slotYukselt(oyuncu);
                case 5 -> RelikMantigi.yankiyiCoz(oyuncu, paket.birinci(), paket.ikinci());
                case 6 -> TarihiKesifDunyasi.kontroluAl(oyuncu);
                case 8 -> TarihiKesifDunyasi.don(oyuncu);
                case 9 -> yuvaIsteğiniAl(oyuncu, paket.birinci());
                case 11 -> konusmaSeciminiAl(oyuncu, paket.birinci(), paket.ikinci());
                default -> oyuncu.sendSystemMessage(net.minecraft.network.chat.Component.translatable("message.pastbound.packet.invalid"));
            }
        });
    }

    private static void konusmaSeciminiAl(ServerPlayer oyuncu, String donemKimligi, String secimMetni) {
        try {
            String[] parcalar = secimMetni.split(":", 2);
            if (parcalar.length != 2) {
                oyuncu.sendSystemMessage(net.minecraft.network.chat.Component.translatable("message.pastbound.packet.invalid"));
                return;
            }
            int konusmaci = Integer.parseInt(parcalar[0]);
            int secim = Integer.parseInt(parcalar[1]);
            TarihiKesifDunyasi.konusmaCevapla(oyuncu, donemKimligi, konusmaci, secim);
        } catch (NumberFormatException hata) {
            oyuncu.sendSystemMessage(net.minecraft.network.chat.Component.translatable("message.pastbound.packet.invalid"));
        }
    }

    private static void yuvaIsteğiniAl(ServerPlayer oyuncu, String yuvaMetni) {
        try {
            int yuva = Integer.parseInt(yuvaMetni);
            RelikMantigi.etkinlestirYuva(oyuncu, yuva);
        } catch (NumberFormatException hata) {
            oyuncu.sendSystemMessage(net.minecraft.network.chat.Component.translatable("message.pastbound.packet.invalid"));
        }
    }

    public static void istemciyeAl(PastboundPaketi paket, IPayloadContext baglam) {
        baglam.enqueueWork(() -> {
            if (paket.islem() == 7) {
                RelikClientOyun.canlandirmaPaketiniIsle(paket.birinci(), paket.ikinci());
            } else if (paket.islem() == 10) {
                try {
                    RelikClientOyun.konusmaPaketiniIsle(paket.birinci(), Integer.parseInt(paket.ikinci()));
                } catch (NumberFormatException hata) {
                    RelikClientOyun.konusmaPaketiniIsle(paket.birinci(), 0);
                }
            }
        });
    }
}
