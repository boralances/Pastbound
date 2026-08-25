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
    AY_MISYONU("message.pastbound.global.ay_misyonu");

    private final String mesajAnahtari;

    KureselTarihOlayi(String mesajAnahtari) {
        this.mesajAnahtari = mesajAnahtari;
    }

    public String mesajAnahtari() {
        return mesajAnahtari;
    }
}
