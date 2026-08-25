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
    private int seciliRelik = -1;
    private String hamleler = "";
    private String bilmeceCevabi = "";

    public RelikDefteriEkrani() {
        super(Component.translatable("screen.pastbound.journal"));
    }

    public static void bilmeceAc(RelikTanimi tanim) {
        RelikDefteriEkrani ekran = new RelikDefteriEkrani();
        ekran.seciliRelik = tanim.ordinal();
        Minecraft.getInstance().setScreenAndShow(ekran);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor cizim, int fareX, int fareY, float kismi) {
        extractBackground(cizim, fareX, fareY, kismi);
        if (seciliRelik >= 0) {
            modalCiz(cizim);
        } else {
            defterCiz(cizim);
        }
    }

    private void defterCiz(GuiGraphicsExtractor cizim) {
        Minecraft minecraft = Minecraft.getInstance();
        int panelGenislik = Math.min(680, cizim.guiWidth() - 24);
        int panelYukseklik = Math.min(430, cizim.guiHeight() - 24);
        int sol = (cizim.guiWidth() - panelGenislik) / 2;
        int ust = (cizim.guiHeight() - panelYukseklik) / 2;
        cizim.blit(DOKU, sol, ust, panelGenislik, panelYukseklik, 0.0F, 0.0F, 1.0F, 1.0F);
        cizim.fill(sol - 4, ust - 4, sol + panelGenislik + 4, ust + panelYukseklik + 4, 0xB8111118);
        cizim.fill(sol, ust, sol + panelGenislik, ust + panelYukseklik, 0xE625202B);
        cizim.outline(sol, ust, panelGenislik, panelYukseklik, 0xFFB98B52);
        cizim.fill(sol + 10, ust + 10, sol + panelGenislik - 10, ust + 42, 0xB5352B37);
        cizim.centeredText(font, title, sol + panelGenislik / 2, ust + 17, 0xFFF1D19B);
        int bilinen = minecraft.player == null ? 0 : RelikMantigi.bilinenSayi(minecraft.player);
        int yankilar = minecraft.player == null ? 0 : RelikMantigi.tamamlananYankiSayisi(minecraft.player);
        cizim.text(font, Component.translatable("screen.pastbound.progress", bilinen, RelikTanimi.values().length), sol + 18, ust + 49, 0xFFC7D4D9);
        cizim.text(font, Component.translatable("screen.pastbound.echo_progress", yankilar, TarihYankisi.values().length), sol + panelGenislik - 180, ust + 49, 0xFFB98B52);

        int kartGenislik = 158;
        int kartYukseklik = 56;
        int baslangicX = sol + 14;
        int baslangicY = ust + 70;
        for (int i = 0; i < RelikTanimi.values().length; i++) {
            RelikTanimi tanim = RelikTanimi.values()[i];
            int sutun = i % 4;
            int satir = i / 4;
            int x = baslangicX + sutun * (kartGenislik + 5);
            int y = baslangicY + satir * (kartYukseklik + 5);
            boolean biliniyor = minecraft.player != null && RelikMantigi.biliyorMu(minecraft.player, tanim);
            boolean yankisi = minecraft.player != null && yankiTamamlandi(minecraft.player, tanim);
            cizim.fill(x, y, x + kartGenislik, y + kartYukseklik, biliniyor ? 0xB52C3D43 : 0xB521252D);
            cizim.outline(x, y, kartGenislik, kartYukseklik, biliniyor ? 0xFFB98B52 : 0xFF4A4F58);
            if (biliniyor) {
                cizim.item(new ItemStack(ModItems.RELIKLER.get(i).get()), x + 7, y + 12);
                cizim.text(font, Component.literal(tanim.ad()), x + 31, y + 10, 0xFFF4E5C4);
                cizim.text(font, Component.literal(tanim.tarihBasligi()), x + 31, y + 25, 0xFFAFC6CA);
                cizim.text(font, Component.translatable(yankisi ? "screen.pastbound.echo_found" : "screen.pastbound.ready"), x + 31, y + 40, yankisi ? 0xFF79C6A2 : 0xFFE0B26B);
            } else {
                cizim.fill(x + 10, y + 15, x + 24, y + 29, 0xFF555962);
                cizim.text(font, Component.translatable("screen.pastbound.unknown"), x + 31, y + 16, 0xFF9296A0);
                cizim.text(font, Component.translatable("screen.pastbound.riddle_hint"), x + 31, y + 32, 0xFF8E6C66);
            }
        }
        cizim.text(font, Component.translatable("screen.pastbound.click_hint"), sol + 18, ust + panelYukseklik - 28, 0xFF7D8A92);
        cizim.text(font, Component.translatable("screen.pastbound.slot_hint"), sol + 18, ust + panelYukseklik - 16, 0xFFE0B26B);
    }

    private boolean yankiTamamlandi(net.minecraft.world.entity.player.Player oyuncu, RelikTanimi tanim) {
        TarihYankisi yanki = TarihYankilari.yankiBulRelik(tanim);
        return yanki != null && RelikMantigi.yankiTamamlandiMi(oyuncu, yanki);
    }

    private void modalCiz(GuiGraphicsExtractor cizim) {
        RelikTanimi tanim = RelikTanimi.values()[seciliRelik];
        TarihYankisi yanki = TarihYankilari.yankiBulRelik(tanim);
        int genislik = Math.min(500, cizim.guiWidth() - 24);
        int yukseklik = Math.min(350, cizim.guiHeight() - 24);
        int sol = (cizim.guiWidth() - genislik) / 2;
        int ust = (cizim.guiHeight() - yukseklik) / 2;
        cizim.fill(0, 0, cizim.guiWidth(), cizim.guiHeight(), 0xA6111118);
        cizim.blit(MODAL_DOKU, sol, ust, genislik, yukseklik, 0.0F, 0.0F, 1.0F, 1.0F);
        cizim.fill(sol, ust, sol + genislik, ust + yukseklik, 0xE621202A);
        cizim.outline(sol, ust, genislik, yukseklik, 0xFFC49B5C);
        cizim.fill(sol + 10, ust + 10, sol + genislik - 10, ust + 42, 0xC63B2D3B);
        cizim.centeredText(font, Component.translatable("screen.pastbound.modal_title"), sol + genislik / 2, ust + 18, 0xFFF4D6A3);
        cizim.item(new ItemStack(ModItems.RELIKLER.get(seciliRelik).get()), sol + 24, ust + 62);
        cizim.text(font, Component.literal(tanim.ad()), sol + 52, ust + 60, 0xFFF4E5C4);
        if (yanki != null) {
            cizim.text(font, Component.literal(yanki.baslik()), sol + 52, ust + 76, 0xFFADC8C7);
            cizim.textWithWordWrap(font, Component.literal(tanim.bilmece()), sol + 24, ust + 100, genislik - 48, 0xFFC7D4D9);
            cizim.textWithWordWrap(font, Component.literal(yanki.hamle()), sol + 24, ust + 124, genislik - 48, 0xFFE4B870);
            cizim.text(font, Component.translatable("screen.pastbound.riddle_input", bilmeceCevabi), sol + 24, ust + 166, 0xFFF4E5C4);
            cizim.text(font, Component.translatable("screen.pastbound.sequence", hamleler), sol + 24, ust + 190, 0xFF9FC6BE);
            cizim.text(font, Component.translatable("screen.pastbound.choose"), sol + 24, ust + 210, 0xFF9FC6BE);
            for (int i = 0; i < 3; i++) {
                int x = sol + 24 + i * 62;
                cizim.fill(x, ust + 232, x + 48, ust + 264, 0xB53C4B55);
                cizim.outline(x, ust + 232, 48, 32, 0xFFB98B52);
                cizim.centeredText(font, Integer.toString(i + 1), x + 24, ust + 243, 0xFFF4E5C4);
            }
            cizim.text(font, Component.translatable("screen.pastbound.solve_gui_hint"), sol + 24, ust + 282, 0xFF7D8A92);
            cizim.text(font, Component.translatable("screen.pastbound.close"), sol + 24, ust + 300, 0xFF7D8A92);
        }
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent olay, boolean ciftTik) {
        double fareX = olay.x();
        double fareY = olay.y();
        if (olay.button() != 0) {
            return true;
        }
        if (seciliRelik >= 0) {
            int genislik = Math.min(500, width - 24);
            int yukseklik = Math.min(350, height - 24);
            int sol = (width - genislik) / 2;
            int ust = (height - yukseklik) / 2;
            for (int i = 0; i < 3; i++) {
                int x = sol + 24 + i * 62;
                if (fareX >= x && fareX <= x + 48 && fareY >= ust + 232 && fareY <= ust + 264) {
                    if (hamleler.length() < 3) {
                        hamleler += Integer.toString(i + 1);
                    }
                    return true;
                }
            }
            return true;
        }
        int panelGenislik = Math.min(680, width - 24);
        int panelYukseklik = Math.min(430, height - 24);
        int sol = (width - panelGenislik) / 2;
        int ust = (height - panelYukseklik) / 2;
        int kartGenislik = 158;
        int kartYukseklik = 56;
        int baslangicX = sol + 14;
        int baslangicY = ust + 70;
        for (int i = 0; i < RelikTanimi.values().length; i++) {
            int x = baslangicX + i % 4 * (kartGenislik + 5);
            int y = baslangicY + i / 4 * (kartYukseklik + 5);
            if (fareX >= x && fareX <= x + kartGenislik && fareY >= y && fareY <= y + kartYukseklik) {
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
