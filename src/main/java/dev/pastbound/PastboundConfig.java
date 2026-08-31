package dev.pastbound;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public final class PastboundConfig {
    public static final PastboundConfig DEGERLER;
    public static final ModConfigSpec SPEC;
    public final ModConfigSpec.BooleanValue wikiGiris;
    public final ModConfigSpec.BooleanValue gorevIsaretleri;
    public final ModConfigSpec.BooleanValue olayDuyurulari;
    public final ModConfigSpec.BooleanValue ortamYankilari;
    public final ModConfigSpec.DoubleValue sandikOdulSansı;
    public final ModConfigSpec.IntValue olayDuyuruAraligi;
    static {
        Pair<PastboundConfig, ModConfigSpec> pair = new ModConfigSpec.Builder().configure(PastboundConfig::new);
        DEGERLER = pair.getLeft();
        SPEC = pair.getRight();
    }
    private PastboundConfig(ModConfigSpec.Builder builder) {
        builder.push("oyun");
        wikiGiris = builder.translation("config.pastbound.wiki_giris").define("wiki_giris", true);
        gorevIsaretleri = builder.translation("config.pastbound.gorev_isaretleri").define("gorev_isaretleri", true);
        olayDuyurulari = builder.translation("config.pastbound.olay_duyurulari").define("olay_duyurulari", true);
        ortamYankilari = builder.translation("config.pastbound.ortam_yankilari").define("ortam_yankilari", true);
        builder.pop();
        builder.push("denge");
        sandikOdulSansı = builder.translation("config.pastbound.sandik_odul_sansi").defineInRange("sandik_odul_sansi", 0.10D, 0.0D, 1.0D);
        olayDuyuruAraligi = builder.translation("config.pastbound.olay_duyuru_araligi").defineInRange("olay_duyuru_araligi", 1200, 100, 72000);
        builder.pop();
    }
    private PastboundConfig() {
        throw new IllegalStateException();
    }
}
