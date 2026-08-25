package dev.pastbound.client.ui;

import org.lwjgl.glfw.GLFW;

import dev.pastbound.history.TarihDonemi;
import dev.pastbound.network.PastboundPaketi;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;

public final class ZamanMakinesiEkrani extends Screen {
    private int seciliDonem = -1;
    private int mesajSayaci;

    public ZamanMakinesiEkrani() {
        super(Component.translatable("screen.pastbound.time_machine"));
    }

    public static void ac() {
        Minecraft.getInstance().setScreenAndShow(new ZamanMakinesiEkrani());
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void tick() {
        if (mesajSayaci > 0) {
            mesajSayaci--;
        }
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor cizim, int fareX, int fareY, float kismi) {
        int genislik = panelGenislik();
        int yukseklik = panelYukseklik();
        int sol = (width - genislik) / 2;
        int ust = (height - yukseklik) / 2;
        cizim.fill(0, 0, width, height, 0xB5101118);
        cizim.fill(sol - 4, ust - 4, sol + genislik + 4, ust + yukseklik + 4, 0xB8111118);
        cizim.fill(sol, ust, sol + genislik, ust + yukseklik, 0xE51E242D);
        cizim.outline(sol, ust, genislik, yukseklik, 0xFFD1A55E);
        cizim.fill(sol + 10, ust + 10, sol + genislik - 10, ust + 44, 0xC63B2D3B);
        cizim.centeredText(font, title, sol + genislik / 2, ust + 18, 0xFFF4D6A3);
        cizim.textWithWordWrap(font, Component.translatable("screen.pastbound.time_machine.subtitle"), sol + 18, ust + 52, genislik - 36, 0xFFC7D4D9);

        int kartGenislik = kartGenislik();
        int kartYukseklik = kartYukseklik();
        int sutunSayisi = sutunSayisi();
        int baslangicX = sol + 16;
        int baslangicY = ust + 86;
        for (int i = 0; i < TarihDonemi.values().length; i++) {
            TarihDonemi donem = TarihDonemi.values()[i];
            int sutun = i % sutunSayisi;
            int satir = i / sutunSayisi;
            int x = baslangicX + sutun * (kartGenislik + 8);
            int y = baslangicY + satir * (kartYukseklik + 8);
            boolean secili = i == seciliDonem;
            cizim.fill(x, y, x + kartGenislik, y + kartYukseklik, secili ? 0xD64C5C5E : 0xB52A343C);
            cizim.outline(x, y, kartGenislik, kartYukseklik, secili ? 0xFFE6C37A : 0xFF68747A);
            cizim.text(font, Component.literal((i + 1) + ". " + donem.ad()), x + 8, y + 7, 0xFFF4E5C4);
            if (kartYukseklik >= 44) {
                cizim.textWithWordWrap(font, Component.literal(donem.odak()), x + 8, y + 23, kartGenislik - 16, 0xFFE1B56C);
            }
            if (kartYukseklik >= 64) {
                cizim.textWithWordWrap(font, Component.literal(donem.aciklama()), x + 8, y + 40, kartGenislik - 16, 0xFFB9C8C9);
            }
        }
        if (mesajSayaci > 0) {
            cizim.centeredText(font, Component.translatable("screen.pastbound.time_machine.sent"), sol + genislik / 2, ust + yukseklik - 32, 0xFF7ED0A8);
        } else {
            cizim.centeredText(font, Component.translatable("screen.pastbound.time_machine.cost"), sol + genislik / 2, ust + yukseklik - 32, 0xFFE0B26B);
        }
        cizim.centeredText(font, Component.translatable("screen.pastbound.close"), sol + genislik / 2, ust + yukseklik - 16, 0xFF7D8A92);
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent olay, boolean ciftTik) {
        if (olay.button() != 0) {
            return true;
        }
        int genislik = panelGenislik();
        int yukseklik = panelYukseklik();
        int sol = (width - genislik) / 2;
        int ust = (height - yukseklik) / 2;
        int kartGenislik = kartGenislik();
        int kartYukseklik = kartYukseklik();
        int sutunSayisi = sutunSayisi();
        int baslangicX = sol + 16;
        int baslangicY = ust + 86;
        for (int i = 0; i < TarihDonemi.values().length; i++) {
            int x = baslangicX + i % sutunSayisi * (kartGenislik + 8);
            int y = baslangicY + i / sutunSayisi * (kartYukseklik + 8);
            if (olay.x() >= x && olay.x() <= x + kartGenislik && olay.y() >= y && olay.y() <= y + kartYukseklik) {
                seciliDonem = i;
                yolculukBaslat(i);
                return true;
            }
        }
        return true;
    }

    @Override
    public boolean keyPressed(KeyEvent olay) {
        if (olay.key() == GLFW.GLFW_KEY_ESCAPE) {
            onClose();
            return true;
        }
        if (olay.key() >= GLFW.GLFW_KEY_1 && olay.key() <= GLFW.GLFW_KEY_9) {
            int index = olay.key() - GLFW.GLFW_KEY_1;
            if (index < TarihDonemi.values().length) {
                seciliDonem = index;
                yolculukBaslat(index);
                return true;
            }
        }
        return super.keyPressed(olay);
    }

    private void yolculukBaslat(int index) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player != null) {
            minecraft.player.connection.send(new ServerboundCustomPayloadPacket(PastboundPaketi.zaman(TarihDonemi.values()[index].kimlik())));
            mesajSayaci = 50;
        }
    }

    private int panelGenislik() {
        return Math.min(700, Math.max(260, width - 16));
    }

    private int panelYukseklik() {
        return Math.min(450, Math.max(220, height - 16));
    }

    private int sutunSayisi() {
        int genislik = panelGenislik();
        if (genislik >= 560) {
            return 3;
        }
        if (genislik >= 360) {
            return 2;
        }
        return 1;
    }

    private int kartGenislik() {
        return (panelGenislik() - 32 - (sutunSayisi() - 1) * 8) / sutunSayisi();
    }

    private int kartYukseklik() {
        int satir = (TarihDonemi.values().length + sutunSayisi() - 1) / sutunSayisi();
        return Math.max(30, Math.min(70, (panelYukseklik() - 132 - (satir - 1) * 8) / satir));
    }
}
