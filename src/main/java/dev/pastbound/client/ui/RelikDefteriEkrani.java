package dev.pastbound.client.ui;

import dev.pastbound.ModId;
import dev.pastbound.relic.RelikMantigi;
import dev.pastbound.relic.RelikTanimi;
import dev.pastbound.registry.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

public final class RelikDefteriEkrani extends Screen {
    private static final Identifier DOKU = Identifier.parse("pastbound:textures/gui/relic_journal.png");

    public RelikDefteriEkrani() {
        super(Component.translatable("screen.pastbound.journal"));
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor cizim, int fareX, int fareY, float kismi) {
        extractBackground(cizim, fareX, fareY, kismi);
        Minecraft minecraft = Minecraft.getInstance();
        int panelGenislik = Math.min(680, cizim.guiWidth() - 24);
        int panelYukseklik = Math.min(430, cizim.guiHeight() - 24);
        int sol = (cizim.guiWidth() - panelGenislik) / 2;
        int ust = (cizim.guiHeight() - panelYukseklik) / 2;
        cizim.fill(sol - 4, ust - 4, sol + panelGenislik + 4, ust + panelYukseklik + 4, 0xB8111118);
        cizim.fill(sol, ust, sol + panelGenislik, ust + panelYukseklik, 0xE625202B);
        cizim.outline(sol, ust, panelGenislik, panelYukseklik, 0xFFB98B52);
        cizim.fill(sol + 10, ust + 10, sol + panelGenislik - 10, ust + 42, 0xB5352B37);
        cizim.centeredText(font, title, sol + panelGenislik / 2, ust + 17, 0xFFF1D19B);
        int bilinen = minecraft.player == null ? 0 : RelikMantigi.bilinenSayi(minecraft.player);
        cizim.text(font, Component.translatable("screen.pastbound.progress", bilinen, RelikTanimi.values().length), sol + 18, ust + 49, 0xFFC7D4D9);
        cizim.text(font, Component.translatable("screen.pastbound.help"), sol + panelGenislik - 220, ust + 49, 0xFFB98B52);

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
            cizim.fill(x, y, x + kartGenislik, y + kartYukseklik, biliniyor ? 0xB52C3D43 : 0xB521252D);
            cizim.outline(x, y, kartGenislik, kartYukseklik, biliniyor ? 0xFFB98B52 : 0xFF4A4F58);
            if (biliniyor) {
                cizim.item(new ItemStack(ModItems.RELIKLER.get(i).get()), x + 7, y + 12);
                cizim.text(font, Component.literal(tanim.ad()), x + 31, y + 10, 0xFFF4E5C4);
                cizim.text(font, Component.literal(tanim.tarihBasligi()), x + 31, y + 25, 0xFFAFC6CA);
                cizim.text(font, Component.translatable("screen.pastbound.ready"), x + 31, y + 40, 0xFF79C6A2);
            } else {
                cizim.fill(x + 10, y + 15, x + 24, y + 29, 0xFF555962);
                cizim.text(font, Component.translatable("screen.pastbound.unknown"), x + 31, y + 16, 0xFF9296A0);
                cizim.text(font, Component.translatable("screen.pastbound.riddle_hint"), x + 31, y + 32, 0xFF8E6C66);
            }
        }
        cizim.text(font, Component.translatable("screen.pastbound.close"), sol + 18, ust + panelYukseklik - 16, 0xFF7D8A92);
    }
}
