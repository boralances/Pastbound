package dev.pastbound.history;

public enum KureselTarihOlayi {
    YAZI_DEVRIMI("message.pastbound.global.yazi_devrimi"),
    NIL_KANALLARI("message.pastbound.global.nil_kanallari"),
    YILDIZ_SEFERI("message.pastbound.global.yildiz_seferi"),
    KERVAN_YOLU("message.pastbound.global.kervan_yolu"),
    TAKVIM_MECLISI("message.pastbound.global.takvim_meclisi"),
    DEMIRCI_HAFIZASI("message.pastbound.global.demirci_hafizasi"),
    MOZAIK_BARISI("message.pastbound.global.mozaik_baris"),
    IPEK_DEGISIMI("message.pastbound.global.ipek_degisimi"),
    QUIPU_SAYIMI("message.pastbound.global.quipu_sayimi"),
    ASTROLAB_GOGU("message.pastbound.global.astrolab_gogu"),
    RUNE_NOBETI("message.pastbound.global.rune_nobeti"),
    AY_MISYONU("message.pastbound.global.ay_misyonu"),
    TARIM_DEVRIMI("message.pastbound.global.tarim_devrimi"),
    AKDENIZ_GEMICILIGI("message.pastbound.global.akdeniz_gemiciligi"),
    MATBAA_YANKISI("message.pastbound.global.matbaa_yankisi"),
    TIP_BAHCESI("message.pastbound.global.tip_bahcesi"),
    CAM_YOLU("message.pastbound.global.cam_yolu"),
    GOZLEM_EVI("message.pastbound.global.gozlem_evi"),
    HUKUK_MECLISI("message.pastbound.global.hukuk_meclisi"),
    ARKEOLOJI_KESFI("message.pastbound.global.arkeoloji_kesfi");

    private final String mesajAnahtari;

    KureselTarihOlayi(String mesajAnahtari) {
        this.mesajAnahtari = mesajAnahtari;
    }

    public String mesajAnahtari() {
        return mesajAnahtari;
    }
}
