package dev.pastbound.client.ui;

import org.lwjgl.glfw.GLFW;

import dev.pastbound.history.TarihYankisi;
import dev.pastbound.history.TarihYankilari;
import dev.pastbound.network.PastboundPaketi;
import dev.pastbound.relic.RelikMantigi;
import dev.pastbound.relic.RelikTanimi;
import dev.pastbound.registry.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.world.item.ItemStack;

public final class RelikDefteriEkrani extends Screen {
    private static final int SAYFADAKI_RELIK = 6;
    private int seciliRelik = -1;
    private int sayfa;
    private final boolean[] parcalar = new boolean[5];

    public RelikDefteriEkrani() {
        super(Component.translatable("screen.pastbound.journal"));
    }

    public static void etkinlikAc(RelikTanimi tanim) {
        RelikDefteriEkrani ekran = new RelikDefteriEkrani();
        ekran.seciliRelik = tanim.ordinal();
        ekran.sayfa = tanim.ordinal() / SAYFADAKI_RELIK;
        Minecraft.getInstance().setScreenAndShow(ekran);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor cizim, int fareX, int fareY, float kismi) {
        cizim.fill(0, 0, cizim.guiWidth(), cizim.guiHeight(), 0xB5101118);
        if (seciliRelik >= 0) {
            modalCiz(cizim);
        } else {
            defterCiz(cizim);
        }
    }

    private void defterCiz(GuiGraphicsExtractor cizim) {
        Minecraft minecraft = Minecraft.getInstance();
        int panelGenislik = Math.min(560, Math.max(260, cizim.guiWidth() - 16));
        int panelYukseklik = Math.min(380, Math.max(220, cizim.guiHeight() - 16));
        int sol = (cizim.guiWidth() - panelGenislik) / 2;
        int ust = (cizim.guiHeight() - panelYukseklik) / 2;
        cizim.fill(sol - 3, ust - 3, sol + panelGenislik + 3, ust + panelYukseklik + 3, 0xB8111118);
        cizim.fill(sol, ust, sol + panelGenislik, ust + panelYukseklik, 0xE625202B);
        cizim.outline(sol, ust, panelGenislik, panelYukseklik, 0xFFB98B52);
        cizim.fill(sol + 8, ust + 8, sol + panelGenislik - 8, ust + 40, 0xB5352B37);
        cizim.centeredText(font, title, sol + panelGenislik / 2, ust + 15, 0xFFF1D19B);
        int bilinen = minecraft.player == null ? 0 : RelikMantigi.bilinenSayi(minecraft.player);
        int yankilar = minecraft.player == null ? 0 : RelikMantigi.tamamlananYankiSayisi(minecraft.player);
        cizim.text(font, Component.translatable("screen.pastbound.progress", bilinen, RelikTanimi.values().length), sol + 14, ust + 47, 0xFFC7D4D9);
        cizim.text(font, Component.translatable("screen.pastbound.echo_progress", yankilar, TarihYankisi.values().length), sol + 14, ust + 61, 0xFFB98B52);
        int sutunSayisi = panelGenislik >= 410 ? 3 : 2;
        int kartGenislik = (panelGenislik - 28 - (sutunSayisi - 1) * 6) / sutunSayisi;
        int satirSayisi = (SAYFADAKI_RELIK + sutunSayisi - 1) / sutunSayisi;
        int kartYukseklik = Math.max(36, Math.min(58, (panelYukseklik - 116 - (satirSayisi - 1) * 6) / satirSayisi));
        int ilkRelik = sayfa * SAYFADAKI_RELIK;
        for (int i = ilkRelik; i < Math.min(RelikTanimi.values().length, ilkRelik + SAYFADAKI_RELIK); i++) {
            RelikTanimi tanim = RelikTanimi.values()[i];
            int yerel = i - ilkRelik;
            int x = sol + 14 + yerel % sutunSayisi * (kartGenislik + 6);
            int y = ust + 78 + yerel / sutunSayisi * (kartYukseklik + 6);
            boolean biliniyor = minecraft.player != null && RelikMantigi.biliyorMu(minecraft.player, tanim);
            boolean yankisi = minecraft.player != null && yankiTamamlandi(tanim);
            cizim.fill(x, y, x + kartGenislik, y + kartYukseklik, biliniyor ? 0xB52C3D43 : 0xB521252D);
            cizim.outline(x, y, kartGenislik, kartYukseklik, biliniyor ? 0xFFB98B52 : 0xFF4A4F58);
            if (biliniyor) {
                cizim.item(new ItemStack(ModItems.RELIKLER.get(i).get()), x + 5, y + Math.max(3, (kartYukseklik - 16) / 2));
                cizim.textWithWordWrap(font, tanim.adBileseni(), x + 26, y + 5, kartGenislik - 30, 0xFFF4E5C4);
                if (kartYukseklik >= 48) {
                    cizim.text(font, Component.translatable(yankisi ? "screen.pastbound.echo_found" : "screen.pastbound.ready"), x + 26, y + kartYukseklik - 15, yankisi ? 0xFF79C6A2 : 0xFFE0B26B);
                }
            } else {
                cizim.fill(x + 7, y + Math.max(4, (kartYukseklik - 14) / 2), x + 20, y + Math.max(4, (kartYukseklik - 14) / 2) + 13, 0xFF555962);
                cizim.centeredText(font, Component.literal("?"), x + 13, y + Math.max(5, (kartYukseklik - 14) / 2) + 2, 0xFFE2C58C);
                cizim.textWithWordWrap(font, Component.translatable("screen.pastbound.unknown"), x + 25, y + 5, kartGenislik - 30, 0xFF9296A0);
            }
        }
        cizim.centeredText(font, Component.translatable("screen.pastbound.page", sayfa + 1, toplamSayfa()), sol + panelGenislik / 2, ust + panelYukseklik - 32, 0xFFD7B878);
        cizim.centeredText(font, Component.translatable("screen.pastbound.page_hint"), sol + panelGenislik / 2, ust + panelYukseklik - 18, 0xFF7D8A92);
    }

    private boolean yankiTamamlandi(RelikTanimi tanim) {
        TarihYankisi yanki = TarihYankilari.yankiBulRelik(tanim);
        return yanki != null && Minecraft.getInstance().player != null && RelikMantigi.yankiTamamlandiMi(Minecraft.getInstance().player, yanki);
    }

    private int toplamSayfa() {
        return (RelikTanimi.values().length + SAYFADAKI_RELIK - 1) / SAYFADAKI_RELIK;
    }

    private void modalCiz(GuiGraphicsExtractor cizim) {
        RelikTanimi tanim = RelikTanimi.values()[seciliRelik];
        TarihYankisi yanki = TarihYankilari.yankiBulRelik(tanim);
        int genislik = Math.min(390, Math.max(230, cizim.guiWidth() - 12));
        int yukseklik = Math.min(230, Math.max(180, cizim.guiHeight() - 12));
        int sol = (cizim.guiWidth() - genislik) / 2;
        int ust = (cizim.guiHeight() - yukseklik) / 2;
        cizim.fill(sol - 3, ust - 3, sol + genislik + 3, ust + yukseklik + 3, 0xB8111118);
        cizim.fill(sol, ust, sol + genislik, ust + yukseklik, 0xE621202A);
        cizim.outline(sol, ust, genislik, yukseklik, 0xFFC49B5C);
        cizim.fill(sol + 8, ust + 8, sol + genislik - 8, ust + 36, 0xC63B2D3B);
        cizim.centeredText(font, Component.translatable("screen.pastbound.modal_title"), sol + genislik / 2, ust + 14, 0xFFF4D6A3);
        cizim.item(new ItemStack(ModItems.RELIKLER.get(seciliRelik).get()), sol + 14, ust + 44);
        cizim.textWithWordWrap(font, tanim.adBileseni(), sol + 40, ust + 45, genislik - 54, 0xFFF4E5C4);
        if (yanki != null) {
            cizim.textWithWordWrap(font, Component.translatable("history.pastbound.echo." + yanki.kimlik() + ".name"), sol + 14, ust + 70, genislik - 28, 0xFFADC8C7);
        }
        cizim.textWithWordWrap(font, Component.translatable("screen.pastbound.relic.activity"), sol + 14, ust + 88, genislik - 28, 0xFFC7D4D9);
        cizim.text(font, Component.translatable("screen.pastbound.relic.fragments", parcaSayisi(), 5), sol + 14, ust + 122, 0xFFE4B870);
        int kolon = genislik >= 310 ? 5 : 3;
        int butonGenislik = Math.min(54, Math.max(42, (genislik - 28 - (kolon - 1) * 5) / kolon));
        for (int i = 0; i < 5; i++) {
            int x = sol + 14 + (i % kolon) * (butonGenislik + 5);
            int y = ust + 142 + (i / kolon) * 25;
            cizim.fill(x, y, x + butonGenislik, y + 21, parcalar[i] ? 0xB54D8B72 : 0xB53C4B55);
            cizim.outline(x, y, butonGenislik, 21, parcalar[i] ? 0xFFE0C17C : 0xFFB98B52);
            cizim.centeredText(font, Component.translatable(parcalar[i] ? "screen.pastbound.relic.fragment_done" : "screen.pastbound.relic.fragment", i + 1), x + butonGenislik / 2, y + 6, 0xFFF4E5C4);
        }
        cizim.text(font, Component.translatable("screen.pastbound.relic.activity_hint"), sol + 14, ust + yukseklik - 24, 0xFF7D8A92);
    }

    private int parcaSayisi() {
        int sayi = 0;
        for (boolean parca : parcalar) {
            if (parca) {
                sayi++;
            }
        }
        return sayi;
    }

    private void parcaTikla(int parca) {
        if (parca < 0 || parca >= parcalar.length || parcalar[parca]) {
            return;
        }
        parcalar[parca] = true;
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player != null) {
            minecraft.player.connection.send(new ServerboundCustomPayloadPacket(PastboundPaketi.miniEtkinlik(RelikTanimi.values()[seciliRelik].kimlik(), parca + 1)));
        }
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent olay, boolean ciftTik) {
        if (olay.button() != 0) {
            return true;
        }
        if (seciliRelik >= 0) {
            int genislik = Math.min(390, Math.max(230, width - 12));
            int yukseklik = Math.min(230, Math.max(180, height - 12));
            int sol = (width - genislik) / 2;
            int ust = (height - yukseklik) / 2;
            int kolon = genislik >= 310 ? 5 : 3;
            int butonGenislik = Math.min(54, Math.max(42, (genislik - 28 - (kolon - 1) * 5) / kolon));
            for (int i = 0; i < 5; i++) {
                int x = sol + 14 + (i % kolon) * (butonGenislik + 5);
                int y = ust + 142 + (i / kolon) * 25;
                if (olay.x() >= x && olay.x() <= x + butonGenislik && olay.y() >= y && olay.y() <= y + 21) {
                    parcaTikla(i);
                    return true;
                }
            }
            return true;
        }
        int panelGenislik = Math.min(560, Math.max(260, width - 16));
        int panelYukseklik = Math.min(380, Math.max(220, height - 16));
        int sol = (width - panelGenislik) / 2;
        int ust = (height - panelYukseklik) / 2;
        int sutunSayisi = panelGenislik >= 410 ? 3 : 2;
        int kartGenislik = (panelGenislik - 28 - (sutunSayisi - 1) * 6) / sutunSayisi;
        int satirSayisi = (SAYFADAKI_RELIK + sutunSayisi - 1) / sutunSayisi;
        int kartYukseklik = Math.max(36, Math.min(58, (panelYukseklik - 116 - (satirSayisi - 1) * 6) / satirSayisi));
        int ilkRelik = sayfa * SAYFADAKI_RELIK;
        for (int i = ilkRelik; i < Math.min(RelikTanimi.values().length, ilkRelik + SAYFADAKI_RELIK); i++) {
            int yerel = i - ilkRelik;
            int x = sol + 14 + yerel % sutunSayisi * (kartGenislik + 6);
            int y = ust + 78 + yerel / sutunSayisi * (kartYukseklik + 6);
            if (olay.x() >= x && olay.x() <= x + kartGenislik && olay.y() >= y && olay.y() <= y + kartYukseklik) {
                seciliRelik = i;
                for (int parca = 0; parca < parcalar.length; parca++) {
                    parcalar[parca] = false;
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public boolean keyPressed(KeyEvent olay) {
        int kod = olay.key();
        if (seciliRelik >= 0) {
            if (kod == GLFW.GLFW_KEY_ESCAPE) {
                seciliRelik = -1;
                return true;
            }
            if (kod >= GLFW.GLFW_KEY_1 && kod <= GLFW.GLFW_KEY_5) {
                parcaTikla(kod - GLFW.GLFW_KEY_1);
                return true;
            }
            return true;
        }
        if (kod == GLFW.GLFW_KEY_LEFT) {
            sayfa = Math.max(0, sayfa - 1);
            return true;
        }
        if (kod == GLFW.GLFW_KEY_RIGHT) {
            sayfa = Math.min(toplamSayfa() - 1, sayfa + 1);
            return true;
        }
        if (kod == GLFW.GLFW_KEY_ESCAPE) {
            onClose();
            return true;
        }
        return super.keyPressed(olay);
    }
}
