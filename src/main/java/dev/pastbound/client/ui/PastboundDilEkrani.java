package dev.pastbound.client.ui;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;

public final class PastboundDilEkrani extends Screen {
    public PastboundDilEkrani() {
        super(Component.translatable("screen.pastbound.language.title"));
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor cizim, int fareX, int fareY, float kismi) {
        cizim.fill(0, 0, cizim.guiWidth(), cizim.guiHeight(), 0xB5101118);
        int genislik = Math.min(420, Math.max(240, cizim.guiWidth() - 20));
        int yukseklik = Math.min(190, Math.max(150, cizim.guiHeight() - 20));
        int sol = (cizim.guiWidth() - genislik) / 2;
        int ust = (cizim.guiHeight() - yukseklik) / 2;
        cizim.fill(sol - 3, ust - 3, sol + genislik + 3, ust + yukseklik + 3, 0xB8111118);
        cizim.fill(sol, ust, sol + genislik, ust + yukseklik, 0xE621202A);
        cizim.outline(sol, ust, genislik, yukseklik, 0xFFC49B5C);
        cizim.centeredText(font, title, sol + genislik / 2, ust + 16, 0xFFF4D6A3);
        cizim.centeredText(font, Component.translatable("screen.pastbound.language.choose"), sol + genislik / 2, ust + 37, 0xFFC7D4D9);
        int butonGenislik = Math.min(170, (genislik - 30) / 2);
        int butonY = ust + 70;
        dilButonu(cizim, sol + 10, butonY, butonGenislik, Component.translatable("screen.pastbound.language.english"));
        dilButonu(cizim, sol + genislik - butonGenislik - 10, butonY, butonGenislik, Component.translatable("screen.pastbound.language.turkish"));
        cizim.centeredText(font, Component.translatable("screen.pastbound.language.default"), sol + genislik / 2, ust + yukseklik - 36, 0xFFE0B26B);
        cizim.centeredText(font, Component.translatable("screen.pastbound.language.close"), sol + genislik / 2, ust + yukseklik - 20, 0xFF7D8A92);
    }

    private void dilButonu(GuiGraphicsExtractor cizim, int x, int y, int genislik, Component metin) {
        cizim.fill(x, y, x + genislik, y + 30, 0xB53C4B55);
        cizim.outline(x, y, genislik, 30, 0xFFB98B52);
        cizim.centeredText(font, metin, x + genislik / 2, y + 10, 0xFFF4E5C4);
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent olay, boolean ciftTik) {
        if (olay.button() != 0) {
            return true;
        }
        int genislik = Math.min(420, Math.max(240, width - 20));
        int yukseklik = Math.min(190, Math.max(150, height - 20));
        int sol = (width - genislik) / 2;
        int ust = (height - yukseklik) / 2;
        int butonGenislik = Math.min(170, (genislik - 30) / 2);
        int butonY = ust + 70;
        if (olay.y() >= butonY && olay.y() <= butonY + 30) {
            if (olay.x() >= sol + 10 && olay.x() <= sol + 10 + butonGenislik) {
                diliUygula("en_us");
                return true;
            }
            if (olay.x() >= sol + genislik - butonGenislik - 10 && olay.x() <= sol + genislik - 10) {
                diliUygula("tr_tr");
                return true;
            }
        }
        return true;
    }

    @Override
    public boolean keyPressed(KeyEvent olay) {
        if (olay.key() == GLFW.GLFW_KEY_1) {
            diliUygula("en_us");
            return true;
        }
        if (olay.key() == GLFW.GLFW_KEY_2) {
            diliUygula("tr_tr");
            return true;
        }
        if (olay.key() == GLFW.GLFW_KEY_ESCAPE) {
            onClose();
            return true;
        }
        return true;
    }

    private void diliUygula(String dil) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.options.languageCode = dil;
        minecraft.options.save();
        minecraft.setScreenAndShow(null);
        minecraft.reloadResourcePacks();
    }
}
