package dev.pastbound.client.ui;

import org.lwjgl.glfw.GLFW;

import dev.pastbound.history.TarihDonemi;
import dev.pastbound.network.PastboundPaketi;
import dev.pastbound.registry.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.world.item.ItemStack;

public final class ZamanMakinesiEkrani extends Screen {
    private static final int SAYFADAKI_DURAK = 4;
    private static final Identifier PASTBOUND_LOGOSU = Identifier.parse("pastbound:textures/gui/pastbound_logo.png");
    private int seciliDonem;
    private int sayfa;
    private int mesajSayaci;
    private boolean onayBekleniyor;

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
        cizim.fill(sol + 10, ust + 10, sol + genislik - 10, ust + 48, 0xC63B2D3B);
        cizim.blit(PASTBOUND_LOGOSU, sol + 14, ust + 14, sol + 42, ust + 42, 0.0F, 1.0F, 0.0F, 1.0F);
        cizim.centeredText(font, title, sol + genislik / 2 + 16, ust + 16, 0xFFF4D6A3);
        cizim.textWithWordWrap(font, Component.translatable("screen.pastbound.time_machine.subtitle"), sol + 18, ust + 31, genislik - 36, 0xFFC7D4D9);
        cizim.text(font, Component.translatable("screen.pastbound.time_machine.inventory", zamanTasiSayisi()), sol + 16, ust + 59, zamanTasiSayisi() > 0 ? 0xFF9AD4B8 : 0xFFE0B26B);
        cizim.text(font, Component.translatable("screen.pastbound.time_machine.archive", seciliDonem + 1, TarihDonemi.values().length), sol + genislik - 150, ust + 59, 0xFFB98B52);
        kartlariCiz(cizim, fareX, fareY, sol, ust, genislik, yukseklik);
        ayrintiCiz(cizim, fareX, fareY, sol, ust, genislik, yukseklik);
        altKontrolleriCiz(cizim, fareX, fareY, sol, ust, genislik, yukseklik);
    }

    private void kartlariCiz(GuiGraphicsExtractor cizim, int fareX, int fareY, int sol, int ust, int genislik, int yukseklik) {
        int listeSol = sol + 16;
        int listeUst = ust + 78;
        int listeGenislik = listeGenislik(genislik);
        int sutun = kartSutunSayisi(listeGenislik);
        int kartGenislik = (listeGenislik - (sutun - 1) * 7) / sutun;
        int kartYukseklik = kartYukseklik(yukseklik);
        int baslangic = sayfa * SAYFADAKI_DURAK;
        cizim.text(font, Component.translatable("screen.pastbound.time_machine.destinations"), listeSol, ust + 69, 0xFFE0C17C);
        for (int i = 0; i < SAYFADAKI_DURAK; i++) {
            int index = baslangic + i;
            if (index >= TarihDonemi.values().length) {
                break;
            }
            TarihDonemi donem = TarihDonemi.values()[index];
            int x = listeSol + i % sutun * (kartGenislik + 7);
            int y = listeUst + i / sutun * (kartYukseklik + 7);
            boolean secili = index == seciliDonem;
            boolean uzerinde = fareX >= x && fareX <= x + kartGenislik && fareY >= y && fareY <= y + kartYukseklik;
            int arkaPlan = secili ? 0xD64C5C5E : uzerinde ? 0xC23B4A50 : 0xB52A343C;
            int cerceve = secili ? 0xFFE6C37A : uzerinde ? 0xFFB98B52 : 0xFF68747A;
            cizim.fill(x, y, x + kartGenislik, y + kartYukseklik, arkaPlan);
            cizim.outline(x, y, kartGenislik, kartYukseklik, cerceve);
            cizim.text(font, Component.literal(Integer.toString(index + 1)), x + 7, y + 7, 0xFFE0C17C);
            cizim.textWithWordWrap(font, donem.adBileseni(), x + 23, y + 6, kartGenislik - 29, 0xFFF4E5C4);
            if (kartYukseklik >= 58) {
                cizim.textWithWordWrap(font, donem.odakBileseni(), x + 8, y + 26, kartGenislik - 16, 0xFFE1B56C);
                cizim.text(font, Component.translatable("screen.pastbound.time_machine.route", index + 1), x + 8, y + kartYukseklik - 15, 0xFF9EC4BD);
            }
        }
        cizim.centeredText(font, Component.translatable("screen.pastbound.time_machine.page", sayfa + 1, toplamSayfa()), listeSol + listeGenislik / 2, ust + yukseklik - 39, 0xFFD7B878);
    }

    private void ayrintiCiz(GuiGraphicsExtractor cizim, int fareX, int fareY, int sol, int ust, int genislik, int yukseklik) {
        TarihDonemi donem = TarihDonemi.values()[seciliDonem];
        int listeGenislik = listeGenislik(genislik);
        int ayrintiSol = sol + 16 + listeGenislik + 12;
        int ayrintiUst = ust + 78;
        int ayrintiGenislik = sol + genislik - 16 - ayrintiSol;
        int ayrintiYukseklik = yukseklik - 130;
        cizim.fill(ayrintiSol, ayrintiUst, ayrintiSol + ayrintiGenislik, ayrintiUst + ayrintiYukseklik, 0xB5213037);
        cizim.outline(ayrintiSol, ayrintiUst, ayrintiGenislik, ayrintiYukseklik, 0xFF68747A);
        cizim.text(font, Component.translatable("screen.pastbound.time_machine.selected"), ayrintiSol + 12, ayrintiUst + 10, 0xFF9EC4BD);
        cizim.textWithWordWrap(font, donem.adBileseni(), ayrintiSol + 12, ayrintiUst + 25, ayrintiGenislik - 24, 0xFFF4D6A3);
        cizim.textWithWordWrap(font, donem.odakBileseni(), ayrintiSol + 12, ayrintiUst + 47, ayrintiGenislik - 24, 0xFFE1B56C);
        cizim.textWithWordWrap(font, donem.aciklamaBileseni(), ayrintiSol + 12, ayrintiUst + 68, ayrintiGenislik - 24, 0xFFC7D4D9);
        int gorevUst = ayrintiUst + Math.min(124, Math.max(100, ayrintiYukseklik / 2));
        cizim.fill(ayrintiSol + 9, gorevUst - 5, ayrintiSol + ayrintiGenislik - 9, gorevUst - 4, 0xB5B98B52);
        cizim.text(font, Component.translatable("screen.pastbound.time_machine.mission"), ayrintiSol + 12, gorevUst + 5, 0xFFE0C17C);
        cizim.textWithWordWrap(font, Component.translatable("screen.pastbound.scene.task." + donem.kimlik()), ayrintiSol + 12, gorevUst + 20, ayrintiGenislik - 24, 0xFFB9C8C9);
        cizim.textWithWordWrap(font, Component.translatable("screen.pastbound.time_machine.confirm_hint"), ayrintiSol + 12, ayrintiUst + ayrintiYukseklik - 31, ayrintiGenislik - 24, 0xFF7D8A92);
    }

    private void altKontrolleriCiz(GuiGraphicsExtractor cizim, int fareX, int fareY, int sol, int ust, int genislik, int yukseklik) {
        int butonY = ust + yukseklik - 29;
        int ayrintiSol = sol + 16 + listeGenislik(genislik) + 12;
        int ayrintiGenislik = sol + genislik - 16 - ayrintiSol;
        cizim.fill(sol + 16, butonY, sol + 82, butonY + 20, 0xB53C4B55);
        cizim.outline(sol + 16, butonY, 66, 20, 0xFF68747A);
        cizim.centeredText(font, Component.translatable("screen.pastbound.time_machine.previous"), sol + 49, butonY + 6, 0xFFF4E5C4);
        cizim.fill(sol + 88, butonY, sol + 154, butonY + 20, 0xB53C4B55);
        cizim.outline(sol + 88, butonY, 66, 20, 0xFF68747A);
        cizim.centeredText(font, Component.translatable("screen.pastbound.time_machine.next"), sol + 121, butonY + 6, 0xFFF4E5C4);
        int seyahatSol = ayrintiSol + Math.max(0, (ayrintiGenislik - 124) / 2);
        int seyahatRenk = onayBekleniyor ? 0xC26E5749 : 0xC24D796C;
        cizim.fill(seyahatSol, butonY, seyahatSol + 124, butonY + 20, seyahatRenk);
        cizim.outline(seyahatSol, butonY, 124, 20, onayBekleniyor ? 0xFFE0B26B : 0xFF9FD2B9);
        cizim.centeredText(font, Component.translatable(onayBekleniyor ? "screen.pastbound.time_machine.confirm" : "screen.pastbound.time_machine.travel"), seyahatSol + 62, butonY + 6, 0xFFF4E5C4);
        cizim.centeredText(font, Component.translatable("screen.pastbound.time_machine.close_hint"), sol + genislik - 64, ust + yukseklik - 43, 0xFF7D8A92);
        if (mesajSayaci > 0) {
            cizim.centeredText(font, Component.translatable("screen.pastbound.time_machine.sent"), sol + genislik - 80, butonY + 6, 0xFF7ED0A8);
        } else if (zamanTasiSayisi() == 0) {
            cizim.centeredText(font, Component.translatable("screen.pastbound.time_machine.time_stone_missing"), sol + genislik / 2, ust + yukseklik - 43, 0xFFE0B26B);
        }
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
        int listeGenislik = listeGenislik(genislik);
        int sutun = kartSutunSayisi(listeGenislik);
        int kartGenislik = (listeGenislik - (sutun - 1) * 7) / sutun;
        int kartYukseklik = kartYukseklik(yukseklik);
        int listeSol = sol + 16;
        int listeUst = ust + 78;
        for (int i = 0; i < SAYFADAKI_DURAK; i++) {
            int index = sayfa * SAYFADAKI_DURAK + i;
            if (index >= TarihDonemi.values().length) {
                break;
            }
            int x = listeSol + i % sutun * (kartGenislik + 7);
            int y = listeUst + i / sutun * (kartYukseklik + 7);
            if (olay.x() >= x && olay.x() <= x + kartGenislik && olay.y() >= y && olay.y() <= y + kartYukseklik) {
                seciliDonem = index;
                onayBekleniyor = false;
                return true;
            }
        }
        int butonY = ust + yukseklik - 29;
        if (olay.y() >= butonY && olay.y() <= butonY + 20) {
            if (olay.x() >= sol + 16 && olay.x() <= sol + 82) {
                sayfaDegistir(-1);
                return true;
            }
            if (olay.x() >= sol + 88 && olay.x() <= sol + 154) {
                sayfaDegistir(1);
                return true;
            }
            int ayrintiSol = sol + 16 + listeGenislik + 12;
            int ayrintiGenislik = sol + genislik - 16 - ayrintiSol;
            int seyahatSol = ayrintiSol + Math.max(0, (ayrintiGenislik - 124) / 2);
            if (olay.x() >= seyahatSol && olay.x() <= seyahatSol + 124) {
                if (onayBekleniyor) {
                    yolculukBaslat();
                } else {
                    onayBekleniyor = true;
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public boolean keyPressed(KeyEvent olay) {
        int kod = olay.key();
        if (kod == GLFW.GLFW_KEY_ESCAPE) {
            if (onayBekleniyor) {
                onayBekleniyor = false;
            } else {
                onClose();
            }
            return true;
        }
        if (kod == GLFW.GLFW_KEY_ENTER || kod == GLFW.GLFW_KEY_KP_ENTER) {
            if (onayBekleniyor) {
                yolculukBaslat();
            } else {
                onayBekleniyor = true;
            }
            return true;
        }
        if (kod == GLFW.GLFW_KEY_LEFT) {
            sayfaDegistir(-1);
            return true;
        }
        if (kod == GLFW.GLFW_KEY_RIGHT) {
            sayfaDegistir(1);
            return true;
        }
        if (kod == GLFW.GLFW_KEY_UP || kod == GLFW.GLFW_KEY_DOWN) {
            int yon = kod == GLFW.GLFW_KEY_UP ? -1 : 1;
            int yeni = Math.max(0, Math.min(TarihDonemi.values().length - 1, seciliDonem + yon * kartSutunSayisi(listeGenislik(panelGenislik()))));
            seciliDonem = yeni;
            sayfa = yeni / SAYFADAKI_DURAK;
            onayBekleniyor = false;
            return true;
        }
        if (kod >= GLFW.GLFW_KEY_1 && kod <= GLFW.GLFW_KEY_4) {
            int index = sayfa * SAYFADAKI_DURAK + kod - GLFW.GLFW_KEY_1;
            if (index < TarihDonemi.values().length) {
                seciliDonem = index;
                onayBekleniyor = false;
            }
            return true;
        }
        return super.keyPressed(olay);
    }

    private void sayfaDegistir(int yon) {
        sayfa = Math.max(0, Math.min(toplamSayfa() - 1, sayfa + yon));
        seciliDonem = Math.min(TarihDonemi.values().length - 1, sayfa * SAYFADAKI_DURAK);
        onayBekleniyor = false;
    }

    private void yolculukBaslat() {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player != null) {
            minecraft.player.connection.send(new ServerboundCustomPayloadPacket(PastboundPaketi.zaman(TarihDonemi.values()[seciliDonem].kimlik())));
            mesajSayaci = 50;
            onayBekleniyor = false;
        }
    }

    private int zamanTasiSayisi() {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null) {
            return 0;
        }
        int sayi = 0;
        for (int i = 0; i < minecraft.player.getInventory().getContainerSize(); i++) {
            ItemStack yigin = minecraft.player.getInventory().getItem(i);
            if (yigin.is(ModItems.TIME_STONE.get())) {
                sayi += yigin.getCount();
            }
        }
        return sayi;
    }

    private int toplamSayfa() {
        return (TarihDonemi.values().length + SAYFADAKI_DURAK - 1) / SAYFADAKI_DURAK;
    }

    private int panelGenislik() {
        return Math.min(900, Math.max(300, width - 16));
    }

    private int panelYukseklik() {
        return Math.min(500, Math.max(260, height - 16));
    }

    private int listeGenislik(int genislik) {
        return genislik >= 560 ? Math.min(310, (genislik - 44) * 40 / 100) : Math.max(112, (genislik - 44) * 40 / 100);
    }

    private int kartSutunSayisi(int genislik) {
        return genislik >= 230 ? 2 : 1;
    }

    private int kartYukseklik(int yukseklik) {
        int satir = kartSutunSayisi(listeGenislik(panelGenislik())) == 2 ? 2 : 4;
        return Math.max(46, Math.min(86, (yukseklik - 164 - (satir - 1) * 7) / satir));
    }
}
