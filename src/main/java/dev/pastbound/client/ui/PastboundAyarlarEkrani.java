package dev.pastbound.client.ui;

import dev.pastbound.client.PastboundConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public final class PastboundAyarlarEkrani extends Screen {
    private final Screen onceki;
    private int sol;
    private int ust;
    public PastboundAyarlarEkrani(Screen onceki) {
        super(Component.translatable("screen.pastbound.settings"));
        this.onceki = onceki;
    }
    @Override
    protected void init() {
        sol = (width - 310) / 2;
        ust = (height - 190) / 2;
        addRenderableWidget(secim("screen.pastbound.settings.wiki", PastboundConfig.wikiGiristeAcilsin(), deger -> PastboundConfig.wikiGiristeAcilsin(deger), ust + 30));
        addRenderableWidget(secim("screen.pastbound.settings.markers", PastboundConfig.gorevIsaretleriAcik(), deger -> PastboundConfig.gorevIsaretleriAcik(deger), ust + 62));
        addRenderableWidget(secim("screen.pastbound.settings.events", PastboundConfig.olayDuyurulariAcik(), deger -> PastboundConfig.olayDuyurulariAcik(deger), ust + 94));
        addRenderableWidget(secim("screen.pastbound.settings.echoes", PastboundConfig.yankilarAcik(), deger -> PastboundConfig.yankilarAcik(deger), ust + 126));
        addRenderableWidget(Button.builder(Component.translatable("gui.done"), buton -> kapat()).bounds(sol, ust + 158, 150, 20).build());
        addRenderableWidget(Button.builder(Component.translatable("screen.pastbound.settings.reset"), buton -> varsayilanlaraDon()).bounds(sol + 160, ust + 158, 150, 20).build());
    }
    private Button secim(String anahtar, boolean acik, java.util.function.Consumer<Boolean> degistir, int y) {
        return Button.builder(metin(anahtar, acik), buton -> {
            degistir.accept(!acik);
            yenidenKur();
        }).bounds(sol, y, 310, 20).build();
    }
    private Component metin(String anahtar, boolean acik) {
        return Component.translatable(anahtar).copy().append(Component.literal("  ")).append(Component.translatable(acik ? "screen.pastbound.settings.on" : "screen.pastbound.settings.off"));
    }
    private void yenidenKur() {
        PastboundConfig.kaydet();
        clearWidgets();
        init();
    }
    private void varsayilanlaraDon() {
        PastboundConfig.wikiGiristeAcilsin(true);
        PastboundConfig.gorevIsaretleriAcik(true);
        PastboundConfig.olayDuyurulariAcik(true);
        PastboundConfig.yankilarAcik(true);
        yenidenKur();
    }
    private void kapat() {
        PastboundConfig.kaydet();
        Minecraft.getInstance().setScreenAndShow(onceki);
    }
    @Override
    public void extractRenderState(GuiGraphicsExtractor cizim, int fareX, int fareY, float delta) {
        cizim.fill(0, 0, width, height, 0xB5101118);
        cizim.fill(sol - 12, ust - 18, sol + 322, ust + 190, 0xE8101720);
        cizim.fill(sol - 8, ust - 14, sol + 318, ust - 12, 0xFFD9A441);
        cizim.centeredText(font, title, width / 2, ust, 0xFFFFD875);
        cizim.centeredText(font, Component.translatable("screen.pastbound.settings.subtitle"), width / 2, ust + 14, 0xFFB8C7D9);
        super.extractRenderState(cizim, fareX, fareY, delta);
    }
}
