package dev.pastbound.network;

import dev.pastbound.PastboundMemory;
import dev.pastbound.client.RelikClientOyun;
import dev.pastbound.history.TarihiKesifDunyasi;
import dev.pastbound.history.ZamanMakinesiMantigi;
import dev.pastbound.relic.RelikMantigi;
import dev.pastbound.registry.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.FurnaceMenu;
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

    public static PastboundPaketi miniEtkinlik(String relik, int parca) {
        return new PastboundPaketi(5, relik, Integer.toString(parca));
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

    public static PastboundPaketi portableWorkstation(String tip) {
        return new PastboundPaketi(13, tip, "");
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

    public static PastboundPaketi konusmaCevabi(String donem, int konusmaci, int secim) {
        return new PastboundPaketi(12, donem, konusmaci + ":" + secim);
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
                case 2 -> ZamanMakinesiMantigi.donemeGit(oyuncu, paket.birinci());
                case 3 -> RelikMantigi.etkinlestirIlk(oyuncu);
                case 4 -> RelikMantigi.slotYukselt(oyuncu);
                case 5 -> miniEtkinligiAl(oyuncu, paket.birinci(), paket.ikinci());
                case 6 -> TarihiKesifDunyasi.kontroluAl(oyuncu);
                case 8 -> TarihiKesifDunyasi.don(oyuncu);
                case 9 -> yuvaIsteğiniAl(oyuncu, paket.birinci());
                case 11 -> konusmaSeciminiAl(oyuncu, paket.birinci(), paket.ikinci());
                case 13 -> portableWorkstationAl(oyuncu, paket.birinci());
                default -> oyuncu.sendSystemMessage(net.minecraft.network.chat.Component.translatable("message.pastbound.packet.invalid"));
            }
        });
    }

    private static void portableWorkstationAl(ServerPlayer oyuncu, String tip) {
        boolean furnace = tip.equals("furnace");
        boolean sahip = false;
        for (int i = 0; i < oyuncu.getInventory().getContainerSize(); i++) {
            net.minecraft.world.item.ItemStack yigin = oyuncu.getInventory().getItem(i);
            if (yigin.is(furnace ? ModItems.FIRIN_CUBUGU.get() : ModItems.CRAFTING_TABLE_CUBUGU.get()) || yigin.is(ModItems.GELISTIRILMIS_FIRIN_CUBUGU.get())) {
                sahip = true;
                break;
            }
        }
        if (!sahip) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.portable.missing"));
            return;
        }
        if (furnace) {
            oyuncu.openMenu(new SimpleMenuProvider((id, envanter, kullanici) -> new FurnaceMenu(id, envanter), Component.translatable("container.furnace")));
        } else {
            oyuncu.openMenu(new SimpleMenuProvider((id, envanter, kullanici) -> new CraftingMenu(id, envanter, net.minecraft.world.inventory.ContainerLevelAccess.NULL), Component.translatable("container.crafting")));
        }
    }

    private static void miniEtkinligiAl(ServerPlayer oyuncu, String relikKimligi, String parcaMetni) {
        try {
            int parca = Integer.parseInt(parcaMetni);
            if (parca < 1 || parca > 5) {
                oyuncu.sendSystemMessage(net.minecraft.network.chat.Component.translatable("message.pastbound.packet.invalid"));
                return;
            }
            RelikMantigi.miniEtkinligiTamamla(oyuncu, relikKimligi, parca);
        } catch (NumberFormatException hata) {
            oyuncu.sendSystemMessage(net.minecraft.network.chat.Component.translatable("message.pastbound.packet.invalid"));
        }
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
            } else if (paket.islem() == 12) {
                try {
                    String[] parcalar = paket.ikinci().split(":", 2);
                    RelikClientOyun.konusmaCevabiniIsle(paket.birinci(), Integer.parseInt(parcalar[0]), Integer.parseInt(parcalar[1]));
                } catch (RuntimeException hata) {
                    RelikClientOyun.konusmaCevabiniIsle(paket.birinci(), 0, 0);
                }
            }
        });
    }
}
