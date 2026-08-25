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
import net.minecraft.client.input.CharacterEvent;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

public final class RelikDefteriEkrani extends Screen {
    private static final Identifier DOKU = Identifier.parse("pastbound:textures/gui/relic_journal.png");
    private static final Identifier MODAL_DOKU = Identifier.parse("pastbound:textures/gui/history_modal.png");
    private static final int SAYFADAKI_RELIK = 6;
    private int seciliRelik = -1;
    private int sayfa;
    private String hamleler = "";
    private String bilmeceCevabi = "";

    public RelikDefteriEkrani() {
        super(Component.translatable("screen.pastbound.journal"));
    }

    public static void bilmeceAc(RelikTanimi tanim) {
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

        int sutunSayisi = sutunSayisi(panelGenislik);
        int kartGenislik = (panelGenislik - 28 - (sutunSayisi - 1) * 6) / sutunSayisi;
        int satirSayisi = (SAYFADAKI_RELIK + sutunSayisi - 1) / sutunSayisi;
        int kartYukseklik = Math.max(36, Math.min(58, (panelYukseklik - 116 - (satirSayisi - 1) * 6) / satirSayisi));
        int baslangicX = sol + 14;
        int baslangicY = ust + 78;
        int ilkRelik = sayfa * SAYFADAKI_RELIK;
        int sonRelik = Math.min(RelikTanimi.values().length, ilkRelik + SAYFADAKI_RELIK);
        for (int i = ilkRelik; i < sonRelik; i++) {
            RelikTanimi tanim = RelikTanimi.values()[i];
            int yerel = i - ilkRelik;
            int x = baslangicX + yerel % sutunSayisi * (kartGenislik + 6);
            int y = baslangicY + yerel / sutunSayisi * (kartYukseklik + 6);
            boolean biliniyor = minecraft.player != null && RelikMantigi.biliyorMu(minecraft.player, tanim);
            boolean yankisi = minecraft.player != null && yankiTamamlandi(minecraft.player, tanim);
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
        int toplamSayfa = toplamSayfa();
        cizim.centeredText(font, Component.translatable("screen.pastbound.page", sayfa + 1, toplamSayfa), sol + panelGenislik / 2, ust + panelYukseklik - 32, 0xFFD7B878);
        cizim.centeredText(font, Component.translatable("screen.pastbound.page_hint"), sol + panelGenislik / 2, ust + panelYukseklik - 18, 0xFF7D8A92);
    }

    private int sutunSayisi(int panelGenislik) {
        return panelGenislik >= 410 ? 3 : 2;
    }

    private int toplamSayfa() {
        return (RelikTanimi.values().length + SAYFADAKI_RELIK - 1) / SAYFADAKI_RELIK;
    }

    private boolean yankiTamamlandi(net.minecraft.world.entity.player.Player oyuncu, RelikTanimi tanim) {
        TarihYankisi yanki = TarihYankilari.yankiBulRelik(tanim);
        return yanki != null && RelikMantigi.yankiTamamlandiMi(oyuncu, yanki);
    }

    private void modalCiz(GuiGraphicsExtractor cizim) {
        RelikTanimi tanim = RelikTanimi.values()[seciliRelik];
        TarihYankisi yanki = TarihYankilari.yankiBulRelik(tanim);
        int genislik = Math.min(460, Math.max(240, cizim.guiWidth() - 16));
        int yukseklik = Math.min(300, Math.max(220, cizim.guiHeight() - 16));
        int sol = (cizim.guiWidth() - genislik) / 2;
        int ust = (cizim.guiHeight() - yukseklik) / 2;
        cizim.fill(sol - 3, ust - 3, sol + genislik + 3, ust + yukseklik + 3, 0xB8111118);
        cizim.fill(sol, ust, sol + genislik, ust + yukseklik, 0xE621202A);
        cizim.outline(sol, ust, genislik, yukseklik, 0xFFC49B5C);
        cizim.fill(sol + 8, ust + 8, sol + genislik - 8, ust + 38, 0xC63B2D3B);
        cizim.centeredText(font, Component.translatable("screen.pastbound.modal_title"), sol + genislik / 2, ust + 16, 0xFFF4D6A3);
        cizim.item(new ItemStack(ModItems.RELIKLER.get(seciliRelik).get()), sol + 16, ust + 48);
        cizim.textWithWordWrap(font, tanim.adBileseni(), sol + 42, ust + 49, genislik - 58, 0xFFF4E5C4);
        int alt = ust + yukseklik;
        if (yanki != null) {
            cizim.textWithWordWrap(font, Component.translatable("history.pastbound.echo." + yanki.kimlik() + ".name"), sol + 42, ust + 66, genislik - 58, 0xFFADC8C7);
            cizim.textWithWordWrap(font, Component.translatable("history.pastbound.relic." + tanim.kimlik() + ".riddle"), sol + 16, ust + 88, genislik - 32, 0xFFC7D4D9);
            cizim.textWithWordWrap(font, Component.translatable("history.pastbound.echo." + yanki.kimlik() + ".hint"), sol + 16, ust + 112, genislik - 32, 0xFFE4B870);
            int butonY = alt - 70;
            int metinY = Math.min(ust + 138, butonY - 28);
            cizim.text(font, Component.translatable("screen.pastbound.riddle_input", bilmeceCevabi), sol + 16, metinY, 0xFFF4E5C4);
            cizim.text(font, Component.translatable("screen.pastbound.sequence", hamleler), sol + 16, metinY + 16, 0xFF9FC6BE);
            for (int i = 0; i < 3; i++) {
                int x = sol + 16 + i * Math.min(58, (genislik - 48) / 3);
                cizim.fill(x, butonY, x + 44, butonY + 26, 0xB53C4B55);
                cizim.outline(x, butonY, 44, 26, 0xFFB98B52);
                cizim.centeredText(font, Integer.toString(i + 1), x + 22, butonY + 8, 0xFFF4E5C4);
            }
            cizim.text(font, Component.translatable("screen.pastbound.solve_gui_hint"), sol + 16, alt - 48, 0xFF7D8A92);
        }
        cizim.text(font, Component.translatable("screen.pastbound.close"), sol + 16, alt - 26, 0xFF7D8A92);
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent olay, boolean ciftTik) {
        if (olay.button() != 0) {
            return true;
        }
        if (seciliRelik >= 0) {
            int genislik = Math.min(460, Math.max(240, width - 16));
            int yukseklik = Math.min(300, Math.max(220, height - 16));
            int sol = (width - genislik) / 2;
            int ust = (height - yukseklik) / 2;
            int butonY = ust + yukseklik - 70;
            for (int i = 0; i < 3; i++) {
                int x = sol + 16 + i * Math.min(58, (genislik - 48) / 3);
                if (olay.x() >= x && olay.x() <= x + 44 && olay.y() >= butonY && olay.y() <= butonY + 26) {
                    if (hamleler.length() < 3) {
                        hamleler += Integer.toString(i + 1);
                    }
                    return true;
                }
            }
            return true;
        }
        int panelGenislik = Math.min(560, Math.max(260, width - 16));
        int panelYukseklik = Math.min(380, Math.max(220, height - 16));
        int sol = (width - panelGenislik) / 2;
        int ust = (height - panelYukseklik) / 2;
        int sutunSayisi = sutunSayisi(panelGenislik);
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
                hamleler = "";
                bilmeceCevabi = "";
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
                hamleler = "";
                bilmeceCevabi = "";
                return true;
            }
            if (kod == GLFW.GLFW_KEY_BACKSPACE && !bilmeceCevabi.isEmpty()) {
                bilmeceCevabi = bilmeceCevabi.substring(0, bilmeceCevabi.length() - 1);
                return true;
            }
            if (kod == GLFW.GLFW_KEY_1 || kod == GLFW.GLFW_KEY_2 || kod == GLFW.GLFW_KEY_3) {
                if (hamleler.length() < 3) {
                    hamleler += Integer.toString(kod - GLFW.GLFW_KEY_0);
                }
                return true;
            }
            if (kod == GLFW.GLFW_KEY_ENTER) {
                Minecraft minecraft = Minecraft.getInstance();
                if (minecraft.player != null) {
                    if (!bilmeceCevabi.isBlank()) {
                        minecraft.player.connection.send(new ServerboundCustomPayloadPacket(PastboundPaketi.bilmece(RelikTanimi.values()[seciliRelik].kimlik(), bilmeceCevabi)));
                    } else if (hamleler.length() == 3) {
                        TarihYankisi yanki = TarihYankilari.yankiBulRelik(RelikTanimi.values()[seciliRelik]);
                        if (yanki != null) {
                            minecraft.player.connection.send(new ServerboundCustomPayloadPacket(PastboundPaketi.yanki(yanki.kimlik(), hamleler)));
                        }
                    }
                }
                seciliRelik = -1;
                hamleler = "";
                bilmeceCevabi = "";
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
        if (kod == GLFW.GLFW_KEY_U) {
            Minecraft minecraft = Minecraft.getInstance();
            if (minecraft.player != null) {
                minecraft.player.connection.send(new ServerboundCustomPayloadPacket(PastboundPaketi.slotYukselt()));
            }
            return true;
        }
        return super.keyPressed(olay);
    }

    @Override
    public boolean charTyped(CharacterEvent olay) {
        if (seciliRelik >= 0 && olay.isAllowedChatCharacter() && bilmeceCevabi.length() < 48) {
            bilmeceCevabi += olay.codepointAsString();
            return true;
        }
        return super.charTyped(olay);
    }
}
