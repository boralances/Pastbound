package dev.pastbound.client.ui;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;

public final class TarihWikiEkrani extends Screen {
    private static final int PAGE_COUNT = 5;
    private int page;

    private TarihWikiEkrani() {
        super(Component.translatable("screen.pastbound.wiki"));
    }

    public static void ac() {
        Minecraft.getInstance().setScreenAndShow(new TarihWikiEkrani());
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor cizim, int fareX, int fareY, float kismi) {
        int panelW = Math.min(720, Math.max(280, width - 20));
        int panelH = Math.min(420, Math.max(240, height - 20));
        int left = (width - panelW) / 2;
        int top = (height - panelH) / 2;
        cizim.fill(0, 0, width, height, 0xB5101118);
        cizim.fill(left - 4, top - 4, left + panelW + 4, top + panelH + 4, 0xB8111118);
        cizim.fill(left, top, left + panelW, top + panelH, 0xE51E242D);
        cizim.outline(left, top, panelW, panelH, 0xFFD1A55E);
        cizim.fill(left + 12, top + 12, left + panelW - 12, top + 48, 0xC63B2D3B);
        cizim.centeredText(font, title, left + panelW / 2, top + 24, 0xFFF4D6A3);
        int textW = panelW - 40;
        int y = top + 68;
        cizim.textWithWordWrap(font, Component.translatable("screen.pastbound.wiki.page." + page + ".title"), left + 20, y, textW, 0xFFE6C37A);
        y += 24;
        for (var line : font.split(Component.translatable("screen.pastbound.wiki.page." + page + ".body"), textW)) {
            if (y > top + panelH - 62) {
                break;
            }
            cizim.text(font, line, left + 20, y, 0xFFD4DFDF);
            y += 12;
        }
        cizim.centeredText(font, Component.literal((page + 1) + " / " + PAGE_COUNT), left + panelW / 2, top + panelH - 42, 0xFFE6C37A);
        cizim.centeredText(font, Component.translatable("screen.pastbound.wiki.controls"), left + panelW / 2, top + panelH - 24, 0xFF9EB4B8);
    }

    @Override
    public boolean keyPressed(KeyEvent olay) {
        if (olay.key() == GLFW.GLFW_KEY_ESCAPE) {
            onClose();
            return true;
        }
        if (olay.key() == GLFW.GLFW_KEY_RIGHT || olay.key() == GLFW.GLFW_KEY_D) {
            page = Math.min(PAGE_COUNT - 1, page + 1);
            return true;
        }
        if (olay.key() == GLFW.GLFW_KEY_LEFT || olay.key() == GLFW.GLFW_KEY_A) {
            page = Math.max(0, page - 1);
            return true;
        }
        return super.keyPressed(olay);
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent olay, boolean ciftTik) {
        if (olay.button() == 0) {
            int panelW = Math.min(720, Math.max(280, width - 20));
            int panelH = Math.min(420, Math.max(240, height - 20));
            int left = (width - panelW) / 2;
            int top = (height - panelH) / 2;
            if (olay.y() >= top + panelH - 58) {
                if (olay.x() < left + panelW / 3) {
                    page = Math.max(0, page - 1);
                } else if (olay.x() > left + panelW * 2 / 3) {
                    page = Math.min(PAGE_COUNT - 1, page + 1);
                }
                return true;
            }
        }
        return super.mouseClicked(olay, ciftTik);
    }
}
