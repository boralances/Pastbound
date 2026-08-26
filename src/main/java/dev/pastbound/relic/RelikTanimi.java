package dev.pastbound.relic;

import net.minecraft.network.chat.Component;

public enum RelikTanimi {
    ROSSETTA_TASI("rossetta_tasi", "Rosetta Taşı", "Antik dillerin anahtarı", 2, 120, 0xE7D6A4, RelikYetisi.BILGI),
    GILGAMESH_TABLETI("gilgamesh_tableti", "Gılgamış Tableti", "Ölümlülüğe meydan okuyan destan", 4, 180, 0xB47A4D, RelikYetisi.GUC),
    ANUBIS_ANKHI("anubis_ankhi", "Anubis Ankhı", "Nil kıyısının yaşam işareti", 5, 240, 0xD3B24A, RelikYetisi.YENILENME),
    MINOS_LABIRENT_MUHRU("minos_labirent_muhru", "Minos Labirent Mührü", "Girit saraylarının yankısı", 3, 150, 0x7695A6, RelikYetisi.CEVIKLIK),
    ROMA_AUREUSU("roma_aureusu", "Roma Aureusu", "İmparatorluğun altın günü", 6, 260, 0xE0A72E, RelikYetisi.SANS),
    VIKING_GUNES_PUSULASI("viking_gunes_pusulasi", "Viking Güneş Pusulası", "Kuzey denizlerinin gökyüzü sırrı", 4, 170, 0x9CC8D4, RelikYetisi.GORUS),
    SAMURAY_KABZASI("samuray_kabzasi", "Samuray Kabzası", "Onur ve disiplinin yadigârı", 7, 320, 0xB33535, RelikYetisi.SAVUNMA),
    MAYA_GUNES_CARKI("maya_gunes_carki", "Maya Güneş Çarkı", "Takvimlerin dönen hafızası", 5, 220, 0xD66B32, RelikYetisi.GECE),
    INKA_QUIPUSU("inka_quipusu", "İnka Quipusu", "Düğümlere yazılmış kayıt", 3, 140, 0xA75E3E, RelikYetisi.HIZ),
    HARAPPA_MUHRU("harappa_muhru", "Harappa Mührü", "İndus vadisinin sessiz yazısı", 4, 190, 0x6D8E72, RelikYetisi.MADEN),
    SONG_PORSELENI("song_porseleni", "Song Porseleni", "İpek Yolu’nun mavi-beyaz izi", 6, 280, 0x75A5D4, RelikYetisi.SU),
    BENIN_BRONZU("benin_bronzu", "Benin Bronz Levhası", "Sarayın dökümle saklanan tarihi", 7, 300, 0xB65B2D, RelikYetisi.ZIRH),
    AZTEK_GUNES_TASI("aztek_gunes_tasi", "Aztek Güneş Taşı", "Beş çağın ağır takvimi", 8, 360, 0xC6452E, RelikYetisi.ATES),
    ABBASID_MUREKKEBI("abbasid_murekkebi", "Abbasi Mürekkebi", "Bilginin kâğıda düşen gölgesi", 5, 230, 0x312D49, RelikYetisi.KESKINLIK),
    RONESANS_ASTROLABI("ronesans_astrolabi", "Rönesans Astrolabı", "Göğün ölçüsünü dünyaya indiren alet", 6, 310, 0xD9B873, RelikYetisi.YUKSELIS),
    ANTIKITHERA_DUZENEĞI("antikithera_duzenegi", "Antikythera Düzeneği", "Dişli çarklarla hesaplanan gök", 9, 420, 0x5C6E73, RelikYetisi.ZAMAN),
    CATALHOYUK_BONCUGU("catalhoyuk_boncugu", "Çatalhöyük Boncuğu", "İlk yerleşimlerin renkli tanığı", 2, 100, 0xC06B56, RelikYetisi.KALP),
    BIZANS_MOZAIGI("bizans_mozaigi", "Bizans Mozaiği", "Taş parçalarından kurulan imparatorluk", 8, 340, 0x4E79A6, RelikYetisi.DIRENC),
    TIMBUKTU_KALEMI("timbuktu_kalemi", "Timbuktu Kalemi", "Sahra’nın el yazması hafızası", 5, 210, 0x8B5A3C, RelikYetisi.TECRUBE),
    APOLLO17_ARMASI("apollo17_armasi", "Apollo 17 Arması", "Ay toprağında bırakılan yakın geçmiş", 10, 500, 0xC9D9E7, RelikYetisi.KOZMIK),
    ILHANLI_MADALYONU("ilhanli_madalyonu", "İlhanlı Madalyonu", "Doğu ile batı arasında bir mühür", 7, 330, 0xB08D57, RelikYetisi.TICARET),
    POLINEZYA_YILDIZ_HARITASI("polinezya_yildiz_haritasi", "Polinezya Yıldız Haritası", "Okyanusta yön bulan sözlü harita", 6, 270, 0x416B83, RelikYetisi.DENIZ),
    MALI_TUZ_MUHRU("mali_tuz_muhru", "Mali Tuz Mührü", "Sahra ticaretinin beyaz hazinesi", 4, 160, 0xE7E1C7, RelikYetisi.BEREKET),
    ISKANDINAV_RUNETASI("iskandinav_runetasi", "İskandinav Rune Taşı", "Kuzeyin taşa kazınmış sözü", 5, 200, 0x79807D, RelikYetisi.SESSIZLIK),
    ;

    private final String kimlik;
    private final String ad;
    private final String tarihBasligi;
    private final int bilmeSeviyesi;
    private final int beklemeSuresi;
    private final int renk;
    private final RelikYetisi yeti;

    RelikTanimi(String kimlik, String ad, String tarihBasligi, int bilmeSeviyesi, int beklemeSuresi, int renk, RelikYetisi yeti) {
        this.kimlik = kimlik;
        this.ad = ad;
        this.tarihBasligi = tarihBasligi;
        this.bilmeSeviyesi = bilmeSeviyesi;
        this.beklemeSuresi = beklemeSuresi;
        this.renk = renk;
        this.yeti = yeti;
    }

    public String kimlik() {
        return kimlik;
    }

    public String ad() {
        return ad;
    }

    public String adAnahtari() {
        return "item.pastbound." + kimlik;
    }

    public Component adBileseni() {
        return Component.translatable(adAnahtari());
    }

    public String tarihBasligi() {
        return tarihBasligi;
    }

    public Component tarihBasligiBileseni() {
        return Component.translatable("history.pastbound.relic." + kimlik + ".title");
    }

    public int bilmeSeviyesi() {
        return bilmeSeviyesi;
    }

    public int beklemeSuresi() {
        return beklemeSuresi;
    }

    public int renk() {
        return renk;
    }

    public RelikYetisi yeti() {
        return yeti;
    }

    public Component etkinlikBileseni() {
        return Component.translatable("history.pastbound.relic." + kimlik + ".activity");
    }
}
