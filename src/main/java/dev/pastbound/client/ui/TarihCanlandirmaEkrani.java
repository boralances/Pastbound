package dev.pastbound.client.ui;

import org.lwjgl.glfw.GLFW;

import dev.pastbound.client.RelikClient;
import dev.pastbound.history.TarihDonemi;
import dev.pastbound.network.PastboundPaketi;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;

public final class TarihCanlandirmaEkrani extends Screen {
    private static TarihCanlandirmaEkrani acikEkran;
    private TarihDonemi donem;
    private int sayac;
    private boolean kontrolAcik;

    public TarihCanlandirmaEkrani(TarihDonemi donem) {
        super(Component.translatable("screen.pastbound.scene.title"));
        this.donem = donem;
    }

    public static void guncelle(String kimlik, int yeniSayac) {
        Minecraft minecraft = Minecraft.getInstance();
        if (yeniSayac < 0) {
            if (acikEkran != null) {
                acikEkran.onClose();
                acikEkran = null;
            }
            return;
        }
        TarihDonemi donem = donemBul(kimlik);
        if (donem == null) {
            return;
        }
        TarihCanlandirmaEkrani ekran = acikEkran;
        if (ekran == null) {
            ekran = new TarihCanlandirmaEkrani(donem);
            acikEkran = ekran;
            minecraft.setScreenAndShow(ekran);
        }
        ekran.donem = donem;
        ekran.sayac = yeniSayac;
        ekran.kontrolAcik = yeniSayac < 0;
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
        cizim.fill(0, 0, cizim.guiWidth(), cizim.guiHeight(), 0xC6111722);
        int genislik = Math.min(560, Math.max(240, cizim.guiWidth() - 24));
        int yukseklik = Math.min(340, Math.max(220, cizim.guiHeight() - 24));
        int sol = (cizim.guiWidth() - genislik) / 2;
        int ust = (cizim.guiHeight() - yukseklik) / 2;
        cizim.fill(sol - 5, ust - 5, sol + genislik + 5, ust + yukseklik + 5, 0xB815111F);
        cizim.fill(sol, ust, sol + genislik, ust + yukseklik, 0xED202A39);
        cizim.outline(sol, ust, genislik, yukseklik, 0xFFD0A25F);
        cizim.fill(sol + 12, ust + 12, sol + genislik - 12, ust + 48, 0xC93D3041);
        cizim.centeredText(font, title, sol + genislik / 2, ust + 22, 0xFFF3D59D);
        if (donem != null) {
            cizim.centeredText(font, donem.adBileseni(), sol + genislik / 2, ust + 64, 0xFFF4E5C4);
            cizim.centeredText(font, donem.odakBileseni(), sol + genislik / 2, ust + 83, 0xFFE4B870);
            cizim.textWithWordWrap(font, donem.aciklamaBileseni(), sol + 34, ust + 110, genislik - 68, 0xFFC7D4D9);
        }
        String asama = sayac < 80 ? "screen.pastbound.scene.phase_one" : sayac < 160 ? "screen.pastbound.scene.phase_two" : "screen.pastbound.scene.phase_three";
        cizim.centeredText(font, Component.translatable(asama), sol + genislik / 2, ust + 180, 0xFF9FC6BE);
        String hedef = donem == TarihDonemi.BAGDAT_PILI_ATOLYESI ? "screen.pastbound.scene.task_steel" : "screen.pastbound.scene.task." + donem.kimlik();
        cizim.textWithWordWrap(font, Component.translatable(hedef), sol + 28, ust + 202, genislik - 56, 0xFFE4B870);
        if (donem != null) {
            cizim.textWithWordWrap(font, Component.translatable("screen.pastbound.scene.ecosystem." + donem.kimlik()), sol + 28, ust + 236, genislik - 56, 0xFF9FC6BE);
        }
        if (!kontrolAcik && sayac > 0) {
            String kapi = (sayac / 50) % 2 == 1 ? "screen.pastbound.scene.door_open" : "screen.pastbound.scene.door_closed";
            cizim.centeredText(font, Component.translatable(kapi), sol + genislik / 2, ust + 270, 0xFF9FC6BE);
        }
        if (!kontrolAcik) {
            cizim.centeredText(font, Component.translatable("screen.pastbound.scene.press_d"), sol + genislik / 2, ust + yukseklik - 44, 0xFFE0B26B);
        }
        cizim.centeredText(font, Component.translatable("screen.pastbound.scene.escape_hint"), sol + genislik / 2, ust + yukseklik - 24, 0xFF7D8A92);
    }

    @Override
    public boolean keyPressed(KeyEvent olay) {
        if (olay.key() == GLFW.GLFW_KEY_D) {
            Minecraft minecraft = Minecraft.getInstance();
            if (minecraft.player != null) {
                minecraft.player.connection.send(new ServerboundCustomPayloadPacket(PastboundPaketi.kontroluAl()));
            }
            kontrolAcik = true;
            acikEkran = null;
            onClose();
            return true;
        }
        if (olay.key() == GLFW.GLFW_KEY_ESCAPE && kontrolAcik) {
            onClose();
            return true;
        }
        return true;
    }

    @Override
    public boolean mouseClicked(net.minecraft.client.input.MouseButtonEvent olay, boolean ciftTik) {
        return true;
    }

    @Override
    public void onClose() {
        acikEkran = null;
        super.onClose();
    }
}
