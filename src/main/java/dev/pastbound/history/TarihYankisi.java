package dev.pastbound.history;

import dev.pastbound.relic.RelikTanimi;

public enum TarihYankisi {
    PAPIRUS_SIFRESI("papirus_sifresi", "Papirüs Şifresi", "Nil kıyısındaki ilk yazılı iz", 3, RelikTanimi.ROSSETTA_TASI),
    URUK_MUHRU("uruk_muhru", "Uruk Mührü", "İlk şehirlerin kil hafızası", 4, RelikTanimi.GILGAMESH_TABLETI),
    NIL_TORENI("nil_toreni", "Nil Töreni", "Taşkın ve yaşam arasındaki ritim", 5, RelikTanimi.ANUBIS_ANKHI),
    GIRIT_IPI("girit_ipi", "Girit İpi", "Labirentin merkezine uzanan yol", 3, RelikTanimi.MINOS_LABIRENT_MUHRU),
    ROMA_FORUMU("roma_forumu", "Roma Forumu", "Forumda düzenlenen ticaret", 6, RelikTanimi.ROMA_AUREUSU),
    KUZEY_GUNESI("kuzey_gunesi", "Kuzey Güneşi", "Denizcilerin gökyüzü pusulası", 4, RelikTanimi.VIKING_GUNES_PUSULASI),
    BUSHIDO_YEMINI("bushido_yemini", "Bushido Yemini", "Savaş öncesi sessiz disiplin", 7, RelikTanimi.SAMURAY_KABZASI),
    MAYA_TAKVIMI("maya_takvimi", "Maya Takvimi", "Günleri sayan taş çark", 5, RelikTanimi.MAYA_GUNES_CARKI),
    INKA_DUGUMLERI("inka_dugumleri", "İnka Düğümleri", "Kayıt tutan renkli ipler", 3, RelikTanimi.INKA_QUIPUSU),
    HARAPPA_DRENAJI("harappa_drenaji", "Harappa Drenajı", "Planlı şehirlerin su yolu", 4, RelikTanimi.HARAPPA_MUHRU),
    SONG_FIRINI("song_firini", "Song Fırını", "Mavi-beyaz seramiğin ateşi", 6, RelikTanimi.SONG_PORSELENI),
    BENIN_DOKUMU("benin_dokumu", "Benin Dökümü", "Bronz levhalarda saray hafızası", 7, RelikTanimi.BENIN_BRONZU),
    AZTEK_BES_CAG("aztek_bes_cag", "Aztek Beş Çağ", "Güneş taşında zamanın katmanları", 8, RelikTanimi.AZTEK_GUNES_TASI),
    ABBASI_BILGI_EVI("abbasi_bilgi_evi", "Abbasi Bilgi Evi", "Çeviri ve gözlemle büyüyen miras", 5, RelikTanimi.ABBASID_MUREKKEBI),
    RONESANS_ATOLYESI("ronesans_atolyesi", "Rönesans Atölyesi", "Sanat ile bilimin aynı masası", 6, RelikTanimi.RONESANS_ASTROLABI),
    ANTIKITHERA_GOK("antikithera_gok", "Antikythera Gök Hesabı", "Dişli çarklarla hesaplanan gök", 9, RelikTanimi.ANTIKITHERA_DUZENEĞI),
    CATALHOYUK_EVLERI("catalhoyuk_evleri", "Çatalhöyük Evleri", "Birbirine bağlanan ilk mahalleler", 2, RelikTanimi.CATALHOYUK_BONCUGU),
    BIZANS_PARCA("bizans_parca", "Bizans Parçası", "Mozaiklerde birleşen imparatorluk", 8, RelikTanimi.BIZANS_MOZAIGI),
    TIMBUKTU_KERVANI("timbuktu_kervani", "Timbuktu Kervanı", "Sahra’dan geçen el yazması yolu", 5, RelikTanimi.TIMBUKTU_KALEMI),
    APOLLO_AY_YURUYUSU("apollo_ay_yuruyusu", "Apollo Ay Yürüyüşü", "Yakın tarihin ay yüzeyindeki izi", 10, RelikTanimi.APOLLO17_ARMASI),
    ILHANLI_MENZIL("ilhanli_menzil", "İlhanlı Menzil Ağı", "Kervan duraklarını bağlayan düzen", 7, RelikTanimi.ILHANLI_MADALYONU),
    POLINEZYA_YILDIZ("polinezya_yildiz", "Polinezya Yıldız Rotası", "Ufukta yön bulan sözlü harita", 6, RelikTanimi.POLINEZYA_YILDIZ_HARITASI),
    MALI_SAHRA_TUZU("mali_sahra_tuzu", "Mali Sahra Tuzu", "Çöl ticaretinin beyaz hazinesi", 4, RelikTanimi.MALI_TUZ_MUHRU),
    RUNE_TASI("rune_tasi", "Rune Taşı", "Kuzeyin taşa kazınmış sözü", 5, RelikTanimi.ISKANDINAV_RUNETASI);

    private final String kimlik;
    private final String baslik;
    private final String tarihIzi;
    private final int deneyim;
    private final RelikTanimi relik;

    TarihYankisi(String kimlik, String baslik, String tarihIzi, int deneyim, RelikTanimi relik) {
        this.kimlik = kimlik;
        this.baslik = baslik;
        this.tarihIzi = tarihIzi;
        this.deneyim = deneyim;
        this.relik = relik;
    }

    public String kimlik() {
        return kimlik;
    }

    public String baslik() {
        return baslik;
    }

    public String tarihIzi() {
        return tarihIzi;
    }

    public int deneyim() {
        return deneyim;
    }

    public RelikTanimi relik() {
        return relik;
    }
}
