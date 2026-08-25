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

public final class TarihKoyluKonusmaEkrani extends Screen {
    private static TarihKoyluKonusmaEkrani acikEkran;
    private TarihDonemi donem;
    private int konusmaci;

    private TarihKoyluKonusmaEkrani(TarihDonemi donem, int konusmaci) {
        super(Component.translatable("screen.pastbound.dialogue.title"));
        this.donem = donem;
        this.konusmaci = konusmaci;
    }

    public static void ac(String donemKimligi, int konusmaci) {
        TarihDonemi donem = donemBul(donemKimligi);
        if (donem == null) {
            return;
        }
        TarihKoyluKonusmaEkrani ekran = new TarihKoyluKonusmaEkrani(donem, Math.max(0, Math.min(3, konusmaci)));
        acikEkran = ekran;
        Minecraft.getInstance().setScreenAndShow(ekran);
    }

    private static TarihDonemi donemBul(String kimlik) {
        for (TarihDonemi donem : TarihDonemi.values()) {
            if (donem.kimlik().equalsIgnoreCase(kimlik)) {
                return donem;
            }
        }
        return null;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor cizim, int fareX, int fareY, float kismi) {
        cizim.fill(0, 0, cizim.guiWidth(), cizim.guiHeight(), 0xB5101118);
        int genislik = Math.min(520, Math.max(260, cizim.guiWidth() - 18));
        int yukseklik = Math.min(300, Math.max(220, cizim.guiHeight() - 18));
        int sol = (cizim.guiWidth() - genislik) / 2;
        int ust = (cizim.guiHeight() - yukseklik) / 2;
        cizim.fill(sol - 3, ust - 3, sol + genislik + 3, ust + yukseklik + 3, 0xB8111118);
        cizim.fill(sol, ust, sol + genislik, ust + yukseklik, 0xE621202A);
        cizim.outline(sol, ust, genislik, yukseklik, 0xFFC49B5C);
        cizim.fill(sol + 10, ust + 10, sol + genislik - 10, ust + 42, 0xC63B2D3B);
        cizim.centeredText(font, title, sol + genislik / 2, ust + 17, 0xFFF4D6A3);
        cizim.centeredText(font, Component.translatable("entity.pastbound.scene." + konusmaciAdi()), sol + genislik / 2, ust + 54, 0xFFF4E5C4);
        cizim.centeredText(font, donem.adBileseni(), sol + genislik / 2, ust + 70, 0xFFE4B870);
        cizim.textWithWordWrap(font, donem.odakBileseni(), sol + 18, ust + 91, genislik - 36, 0xFFC7D4D9);
        int butonY = ust + yukseklik - 112;
        int butonGenislik = Math.max(70, (genislik - 42) / 3);
        for (int i = 0; i < 3; i++) {
            int x = sol + 12 + i * (butonGenislik + 9);
            cizim.fill(x, butonY, x + butonGenislik, butonY + 54, 0xB53C4B55);
            cizim.outline(x, butonY, butonGenislik, 54, 0xFFB98B52);
            cizim.text(font, Component.literal((i + 1) + "."), x + 8, butonY + 8, 0xFFE0B26B);
            cizim.textWithWordWrap(font, Component.translatable("history.pastbound.period." + donem.kimlik() + ".dialogue_" + (i + 1)), x + 8, butonY + 23, butonGenislik - 16, 0xFFF0E1C3);
        }
        cizim.centeredText(font, Component.translatable("screen.pastbound.dialogue.choose"), sol + genislik / 2, ust + yukseklik - 42, 0xFF9FC6BE);
        cizim.centeredText(font, Component.translatable("screen.pastbound.close"), sol + genislik / 2, ust + yukseklik - 24, 0xFF7D8A92);
    }

    private String konusmaciAdi() {
        return switch (konusmaci) {
            case 1 -> "craftsman";
            case 2 -> "witness";
            case 3 -> "scribe";
            default -> "narrator";
        };
    }

    private void secimiGonder(int secim) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player != null) {
            minecraft.player.connection.send(new ServerboundCustomPayloadPacket(PastboundPaketi.konusmaSecimi(donem.kimlik(), konusmaci, secim)));
        }
        onClose();
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent olay, boolean ciftTik) {
        if (olay.button() != 0) {
            return true;
        }
        int genislik = Math.min(520, Math.max(260, width - 18));
        int yukseklik = Math.min(300, Math.max(220, height - 18));
        int sol = (width - genislik) / 2;
        int ust = (height - yukseklik) / 2;
        int butonY = ust + yukseklik - 112;
        int butonGenislik = Math.max(70, (genislik - 42) / 3);
        for (int i = 0; i < 3; i++) {
            int x = sol + 12 + i * (butonGenislik + 9);
            if (olay.x() >= x && olay.x() <= x + butonGenislik && olay.y() >= butonY && olay.y() <= butonY + 54) {
                secimiGonder(i + 1);
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
        if (olay.key() >= GLFW.GLFW_KEY_1 && olay.key() <= GLFW.GLFW_KEY_3) {
            secimiGonder(olay.key() - GLFW.GLFW_KEY_0);
            return true;
        }
        return true;
    }

    @Override
    public void onClose() {
        acikEkran = null;
        super.onClose();
    }
}
