package dev.pastbound.history;

import dev.pastbound.relic.RelikTanimi;

public enum TarihYankisi {
    PAPIRUS_SIFRESI("papirus_sifresi", "Papirüs Şifresi", "Nil kıyısındaki ilk yazılı iz", "Suyun yönünü üç adımla izle.", "123", 3, RelikTanimi.ROSSETTA_TASI),
    URUK_MUHRU("uruk_muhru", "Uruk Mührü", "İlk şehirlerin kil hafızası", "Şehir kapısını kil ile mühürle.", "231", 4, RelikTanimi.GILGAMESH_TABLETI),
    NIL_TORENI("nil_toreni", "Nil Töreni", "Taşkın ve yaşam arasındaki ritim", "Nil’in yükselişini üç işaretle çağır.", "312", 5, RelikTanimi.ANUBIS_ANKHI),
    GIRIT_IPI("girit_ipi", "Girit İpi", "Labirentin merkezine uzanan yol", "Labirentin merkezini ip ile bul.", "213", 3, RelikTanimi.MINOS_LABIRENT_MUHRU),
    ROMA_FORUMU("roma_forumu", "Roma Forumu", "Forumda düzenlenen ticaret", "Üç ticaret izini doğru sıraya koy.", "132", 6, RelikTanimi.ROMA_AUREUSU),
    KUZEY_GUNESI("kuzey_gunesi", "Kuzey Güneşi", "Denizcilerin gökyüzü pusulası", "Gölgenin tersinin gösterdiği yönü bul.", "321", 4, RelikTanimi.VIKING_GUNES_PUSULASI),
    BUSHIDO_YEMINI("bushido_yemini", "Bushido Yemini", "Savaş öncesi sessiz disiplin", "Kılıçtan önce gelen üç sessiz adımı seç.", "111", 7, RelikTanimi.SAMURAY_KABZASI),
    MAYA_TAKVIMI("maya_takvimi", "Maya Takvimi", "Günleri sayan taş çark", "Günü beş taşla eşleştir.", "232", 5, RelikTanimi.MAYA_GUNES_CARKI),
    INKA_DUGUMLERI("inka_dugumleri", "İnka Düğümleri", "Kayıt tutan renkli ipler", "Düğümleri küçükten büyüğe çöz.", "123", 3, RelikTanimi.INKA_QUIPUSU),
    HARAPPA_DRENAJI("harappa_drenaji", "Harappa Drenajı", "Planlı şehirlerin su yolu", "Kanalları sessizce aç.", "213", 4, RelikTanimi.HARAPPA_MUHRU),
    SONG_FIRINI("song_firini", "Song Fırını", "Mavi-beyaz seramiğin ateşi", "Maviyi ateşten önce seç.", "321", 6, RelikTanimi.SONG_PORSELENI),
    BENIN_DOKUMU("benin_dokumu", "Benin Dökümü", "Bronz levhalarda saray hafızası", "Bronzun üç yüzünü döndür.", "132", 7, RelikTanimi.BENIN_BRONZU),
    AZTEK_BES_CAG("aztek_bes_cag", "Aztek Beş Çağ", "Güneş taşında zamanın katmanları", "Beşinci çağı güneş taşında bul.", "333", 8, RelikTanimi.AZTEK_GUNES_TASI),
    ABBASI_BILGI_EVI("abbasi_bilgi_evi", "Abbasi Bilgi Evi", "Çeviri ve gözlemle büyüyen miras", "Mürekkep, kâğıt, fikir.", "213", 5, RelikTanimi.ABBASID_MUREKKEBI),
    RONESANS_ATOLYESI("ronesans_atolyesi", "Rönesans Atölyesi", "Sanat ile bilimin aynı masası", "Çarkı göğe bakan yüzle hizala.", "312", 6, RelikTanimi.RONESANS_ASTROLABI),
    ANTIKITHERA_GOK("antikithera_gok", "Antikythera Gök Hesabı", "Dişli çarklarla hesaplanan gök", "Güneş, ay, dişli.", "231", 9, RelikTanimi.ANTIKITHERA_DUZENEĞI),
    CATALHOYUK_EVLERI("catalhoyuk_evleri", "Çatalhöyük Evleri", "Birbirine bağlanan ilk mahalleler", "Evleri birleştiren yolu seç.", "122", 2, RelikTanimi.CATALHOYUK_BONCUGU),
    BIZANS_PARCA("bizans_parca", "Bizans Parçası", "Mozaiklerde birleşen imparatorluk", "Parçaları ışıkla birleştir.", "321", 8, RelikTanimi.BIZANS_MOZAIGI),
    TIMBUKTU_KERVANI("timbuktu_kervani", "Timbuktu Kervanı", "Sahra’dan geçen el yazması yolu", "Kum, kalem, yıldız.", "132", 5, RelikTanimi.TIMBUKTU_KALEMI),
    APOLLO_AY_YURUYUSU("apollo_ay_yuruyusu", "Apollo Ay Yürüyüşü", "Yakın tarihin ay yüzeyindeki izi", "Dünyaya dönmeden önce üç iz bırak.", "213", 10, RelikTanimi.APOLLO17_ARMASI),
    ILHANLI_MENZIL("ilhanli_menzil", "İlhanlı Menzil Ağı", "Kervan duraklarını bağlayan düzen", "Kervan duraklarını bağla.", "312", 7, RelikTanimi.ILHANLI_MADALYONU),
    POLINEZYA_YILDIZ("polinezya_yildiz", "Polinezya Yıldız Rotası", "Ufukta yön bulan sözlü harita", "Yıldız, dalga, ada.", "123", 6, RelikTanimi.POLINEZYA_YILDIZ_HARITASI),
    MALI_SAHRA_TUZU("mali_sahra_tuzu", "Mali Sahra Tuzu", "Çöl ticaretinin beyaz hazinesi", "Tuzu kum fırtınasından koru.", "231", 4, RelikTanimi.MALI_TUZ_MUHRU),
    RUNE_TASI("rune_tasi", "Rune Taşı", "Kuzeyin taşa kazınmış sözü", "Sessiz taşı üç kez dinle.", "111", 5, RelikTanimi.ISKANDINAV_RUNETASI);

    private final String kimlik;
    private final String baslik;
    private final String tarihIzi;
    private final String hamle;
    private final String kod;
    private final int deneyim;
    private final RelikTanimi relik;

    TarihYankisi(String kimlik, String baslik, String tarihIzi, String hamle, String kod, int deneyim, RelikTanimi relik) {
        this.kimlik = kimlik;
        this.baslik = baslik;
        this.tarihIzi = tarihIzi;
        this.hamle = hamle;
        this.kod = kod;
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

    public String hamle() {
        return hamle;
    }

    public String kod() {
        return kod;
    }

    public int deneyim() {
        return deneyim;
    }

    public RelikTanimi relik() {
        return relik;
    }
}
