package dev.pastbound.history;

public enum TarihDonemi {
    URUK_YAZI_EVI("uruk_yazi_evi", "Uruk Yazı Evi", "Yazının doğuşu", "Kil tabletlerin ilk kayıtları arasında dolaş."),
    TERMOPIL_SAVASI("termopil_savasi", "Termopil Savaş Geçidi", "Savaş ve strateji", "Bir dar geçidin disiplin ve cesaret hikâyesini izle."),
    ISKENDERIYE_KUTUPHANESI("iskenderiye_kutuphanesi", "İskenderiye Kütüphanesi", "Bilginin toplanması", "Kayıp tomarların ve denizcilerin ortak hafızasını araştır."),
    BAGDAT_PILI_ATOLYESI("bagdat_pili_atolyesi", "Bağdat Pili Atölyesi", "Elektrik fikrinin izleri", "Metal, sıvı ve merakın erken elektrik deneyini incele."),
    ANTIKITHERA_LIMANI("antikithera_limani", "Antikythera Limanı", "Mekanik gök hesabı", "Dişli çarklarla göğün ritmini ölçen ustaları takip et."),
    BAGDAT_BILGI_EVI("bagdat_bilgi_evi", "Bağdat Bilgi Evi", "Çeviri ve bilim", "Diller arasında kurulan bilim köprüsünün yankısını dinle."),
    TIMBUKTU_EL_YAZMALARI("timbuktu_el_yazmalari", "Timbuktu El Yazmaları", "Kervan ve eğitim", "Sahra kervanının taşıdığı sayfaları koru ve çoğalt."),
    TENOKTITLAN_GECIDI("tenochtitlan_gecidi", "Tenochtitlan Geçidi", "Göl şehri ve takvim", "Kanallar, pazarlar ve dönen güneş hesabı arasında yol bul."),
    POLINEZYA_YILDIZ_YOLU("polinezya_yildiz_yolu", "Polinezya Yıldız Yolu", "Okyanus navigasyonu", "Haritasız denizde yıldız, dalga ve kuş rotalarını oku."),
    CATALHOYUK_YERLESKESI("catalhoyuk_yerleskesi", "Çatalhöyük Yerleşkesi", "İlk ortak yaşam", "Evlerin ve boncukların topluluk hafızasını keşfet."),
    APOLLO_AY_ISTIGI("apollo_ay_istigi", "Apollo Ay İstasyonu", "Yakın geçmişte uzay", "Ay yüzeyinde bırakılan izlerin sessiz tanığı ol."),
    IPEK_YOLU_KERVANSARAYI("ipek_yolu_kervansarayi", "İpek Yolu Kervansarayı", "Kültürler arası değişim", "Dillerin, kumaşların ve fikirlerin aynı avluda buluşmasını gör.");

    private final String kimlik;
    private final String ad;
    private final String odak;
    private final String aciklama;

    TarihDonemi(String kimlik, String ad, String odak, String aciklama) {
        this.kimlik = kimlik;
        this.ad = ad;
        this.odak = odak;
        this.aciklama = aciklama;
    }

    public String kimlik() {
        return kimlik;
    }

    public String ad() {
        return ad;
    }

    public String odak() {
        return odak;
    }

    public String aciklama() {
        return aciklama;
    }
}
