package dev.pastbound.client.ui;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;

public final class TarihWikiEkrani extends Screen {
    private static final int PAGE_COUNT = 8;
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
        cizim.fill(left - 6, top - 6, left + panelW + 6, top + panelH + 6, 0xD0081018);
        cizim.fill(left, top, left + panelW, top + panelH, 0xEE171E29);
        cizim.outline(left, top, panelW, panelH, 0xFFE0B56B);
        cizim.outline(left + 3, top + 3, panelW - 6, panelH - 6, 0xFF41666A);
        cizim.fill(left + 12, top + 12, left + panelW - 12, top + 50, 0xD52B3B47);
        cizim.fill(left + 22, top + 25, left + 34, top + 37, 0xFFE6C37A);
        cizim.outline(left + 20, top + 23, 16, 16, 0xFF6DB9A5);
        cizim.centeredText(font, title, left + panelW / 2, top + 21, 0xFFFFE3AB);
        cizim.text(font, Component.translatable("screen.pastbound.wiki.archive_label"), left + 46, top + 36, 0xFF9FC8BC);
        int tabY = top + 56;
        int tabW = Math.max(32, (panelW - 40) / PAGE_COUNT - 4);
        for (int i = 0; i < PAGE_COUNT; i++) {
            int tabX = left + 20 + i * (tabW + 4);
            boolean active = i == page;
            cizim.fill(tabX, tabY, tabX + tabW, tabY + 8, active ? 0xFFE6C37A : 0xFF55636A);
            cizim.outline(tabX, tabY, tabW, 8, active ? 0xFFFFF1C4 : 0xFF73848A);
        }
        int textW = panelW - 40;
        cizim.fill(left + 20, top + 64, left + panelW - 20, top + 66, 0xA7D3A05A);
        int y = top + 76;
        cizim.textWithWordWrap(font, Component.translatable("screen.pastbound.wiki.page." + page + ".title"), left + 20, y, textW, 0xFFE6C37A);
        y += 24;
        for (var line : font.split(Component.translatable("screen.pastbound.wiki.page." + page + ".body"), textW)) {
            if (y > top + panelH - 62) {
                break;
            }
            cizim.text(font, line, left + 20, y, 0xFFD4DFDF);
            y += 12;
        }
        int buttonY = top + panelH - 50;
        cizim.fill(left + 20, buttonY, left + 92, buttonY + 18, page > 0 ? 0xB54C5C5E : 0xB52A343C);
        cizim.fill(left + panelW - 92, buttonY, left + panelW - 20, buttonY + 18, page < PAGE_COUNT - 1 ? 0xB54C5C5E : 0xB52A343C);
        cizim.outline(left + 20, buttonY, 72, 18, 0xFF68747A);
        cizim.outline(left + panelW - 92, buttonY, 72, 18, 0xFF68747A);
        cizim.centeredText(font, Component.translatable("screen.pastbound.wiki.previous"), left + 56, buttonY + 5, page > 0 ? 0xFFF4E5C4 : 0xFF6F7A7E);
        cizim.centeredText(font, Component.translatable("screen.pastbound.wiki.next"), left + panelW - 56, buttonY + 5, page < PAGE_COUNT - 1 ? 0xFFF4E5C4 : 0xFF6F7A7E);
        cizim.centeredText(font, Component.translatable("screen.pastbound.wiki.page_counter", page + 1, PAGE_COUNT), left + panelW / 2, buttonY + 5, 0xFFE6C37A);
        int progressW = panelW - 40;
        cizim.fill(left + 20, buttonY - 8, left + 20 + progressW, buttonY - 6, 0xFF3A4B52);
        cizim.fill(left + 20, buttonY - 8, left + 20 + progressW * (page + 1) / PAGE_COUNT, buttonY - 6, 0xFFE0B56B);
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
                if (olay.x() >= left + 20 && olay.x() <= left + 92) {
                    page = Math.max(0, page - 1);
                } else if (olay.x() >= left + panelW - 92 && olay.x() <= left + panelW - 20) {
                    page = Math.min(PAGE_COUNT - 1, page + 1);
                }
                return true;
            }
        }
        return super.mouseClicked(olay, ciftTik);
    }
}
