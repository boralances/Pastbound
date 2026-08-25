import json
from pathlib import Path

kok = Path(__file__).resolve().parents[1]
klasor = kok / "src" / "main" / "resources" / "data" / "pastbound" / "advancements" / "time_machine"
klasor.mkdir(parents=True, exist_ok=True)

konumlar = [
    ("uruk_yazi_evi", "Uruk Yazı Evi", "Yazının doğuşunu ve kil tabletlerin ilk kayıtlarını keşfet."),
    ("termopil_savasi", "Termopil Savaş Geçidi", "Dar geçidin savaş ve strateji hafızasını keşfet."),
    ("iskenderiye_kutuphanesi", "İskenderiye Kütüphanesi", "Kayıp tomarların bilgi mirasını keşfet."),
    ("bagdat_pili_atolyesi", "Bağdat Pili Atölyesi", "Erken elektrik fikrinin metal ve sıvı izlerini keşfet."),
    ("antikithera_limani", "Antikythera Limanı", "Dişli çarklarla gök hesabının tarihini keşfet."),
    ("bagdat_bilgi_evi", "Bağdat Bilgi Evi", "Çeviri ve bilimin ortak hafızasını keşfet."),
    ("timbuktu_el_yazmalari", "Timbuktu El Yazmaları", "Sahra kervanlarının eğitim mirasını keşfet."),
    ("tenochtitlan_gecidi", "Tenochtitlan Geçidi", "Göl şehri, kanallar ve takvim hafızasını keşfet."),
    ("polinezya_yildiz_yolu", "Polinezya Yıldız Yolu", "Okyanus navigasyonunun yıldız rotasını keşfet."),
    ("catalhoyuk_yerleskesi", "Çatalhöyük Yerleşkesi", "İlk ortak yaşamın ev ve boncuk hafızasını keşfet."),
    ("apollo_ay_istigi", "Apollo Ay İstasyonu", "Ay yüzeyindeki yakın tarih izlerini keşfet."),
    ("ipek_yolu_kervansarayi", "İpek Yolu Kervansarayı", "Kültürler arası ticaretin ortak avlusunu keşfet.")
]

onceki = None
for kimlik, ad, aciklama in konumlar:
    veri = {}
    if onceki is not None:
        veri["parent"] = f"pastbound:time_machine/{onceki}"
    veri["display"] = {
        "icon": {"id": "pastbound:zaman_makinesi"},
        "title": {"translate": f"advancement.pastbound.time_machine.{kimlik}.title"},
        "description": {"translate": f"advancement.pastbound.time_machine.{kimlik}.description"},
        "frame": "task",
        "show_toast": True,
        "announce_to_chat": False,
        "hidden": False
    }
    veri["criteria"] = {"kesif": {"trigger": "minecraft:impossible"}}
    (klasor / f"{kimlik}.json").write_text(json.dumps(veri, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
    onceki = kimlik

tamamlama = {
    "parent": f"pastbound:time_machine/{konumlar[-1][0]}",
    "display": {
        "icon": {"id": "pastbound:time_stone"},
        "title": {"translate": "advancement.pastbound.time_machine.complete_expedition.title"},
        "description": {"translate": "advancement.pastbound.time_machine.complete_expedition.description"},
        "frame": "challenge",
        "show_toast": True,
        "announce_to_chat": True,
        "hidden": False
    },
    "criteria": {"kesif": {"trigger": "minecraft:impossible"}}
}
(klasor / "complete_expedition.json").write_text(json.dumps(tamamlama, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
print(f"Time machine advancements generated: {len(konumlar) + 1}")
