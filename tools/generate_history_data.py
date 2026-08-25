import json
from pathlib import Path

kok = Path(__file__).resolve().parents[1]
veri = kok / "src/main/resources/data/pastbound"
ilerlemeler = veri / "advancements/history"
tarifler = veri / "recipes"
ilerlemeler.mkdir(parents=True, exist_ok=True)
tarifler.mkdir(parents=True, exist_ok=True)

yankilar = [
    ("papirus_sifresi", "rossetta_tasi", "Papirüs Şifresi"),
    ("uruk_muhru", "gilgamesh_tableti", "Uruk Mührü"),
    ("nil_toreni", "anubis_ankhi", "Nil Töreni"),
    ("girit_ipi", "minos_labirent_muhru", "Girit İpi"),
    ("roma_forumu", "roma_aureusu", "Roma Forumu"),
    ("kuzey_gunesi", "viking_gunes_pusulasi", "Kuzey Güneşi"),
    ("bushido_yemini", "samuray_kabzasi", "Bushido Yemini"),
    ("maya_takvimi", "maya_gunes_carki", "Maya Takvimi"),
    ("inka_dugumleri", "inka_quipusu", "İnka Düğümleri"),
    ("harappa_drenaji", "harappa_muhru", "Harappa Drenajı"),
    ("song_firini", "song_porseleni", "Song Fırını"),
    ("benin_dokumu", "benin_bronzu", "Benin Dökümü"),
    ("aztek_bes_cag", "aztek_gunes_tasi", "Aztek Beş Çağ"),
    ("abbasi_bilgi_evi", "abbasid_murekkebi", "Abbasi Bilgi Evi"),
    ("ronesans_atolyesi", "ronesans_astrolabi", "Rönesans Atölyesi"),
    ("antikithera_gok", "antikithera_duzenegi", "Antikythera Gök Hesabı"),
    ("catalhoyuk_evleri", "catalhoyuk_boncugu", "Çatalhöyük Evleri"),
    ("bizans_parca", "bizans_mozaigi", "Bizans Parçası"),
    ("timbuktu_kervani", "timbuktu_kalemi", "Timbuktu Kervanı"),
    ("apollo_ay_yuruyusu", "apollo17_armasi", "Apollo Ay Yürüyüşü"),
    ("ilhanli_menzil", "ilhanli_madalyonu", "İlhanlı Menzil Ağı"),
    ("polinezya_yildiz", "polinezya_yildiz_haritasi", "Polinezya Yıldız Rotası"),
    ("mali_sahra_tuzu", "mali_tuz_muhru", "Mali Sahra Tuzu"),
    ("rune_tasi", "iskandinav_runetasi", "Rune Taşı")
]

for kimlik, relik, baslik in yankilar:
    ilerleme = {
        "display": {
            "icon": {"id": f"pastbound:{relik}"},
            "title": {"translate": f"advancement.pastbound.{kimlik}.title"},
            "description": {"translate": f"advancement.pastbound.{kimlik}.description"},
            "frame": "task",
            "show_toast": True,
            "announce_to_chat": False,
            "hidden": False
        },
        "criteria": {"kesif": {"trigger": "minecraft:impossible"}}
    }
    (ilerlemeler / f"{kimlik}.json").write_text(json.dumps(ilerleme, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")

koleksiyon = {
    "parent": "pastbound:history/papirus_sifresi",
    "display": {
        "icon": {"id": "pastbound:echo_seal"},
        "title": {"translate": "advancement.pastbound.complete_collection.title"},
        "description": {"translate": "advancement.pastbound.complete_collection.description"},
        "frame": "challenge",
        "show_toast": True,
        "announce_to_chat": True,
        "hidden": False
    },
    "criteria": {"kesif": {"trigger": "minecraft:impossible"}}
}
(ilerlemeler / "complete_collection.json").write_text(json.dumps(koleksiyon, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")

def shapeless(cikti, malzemeler, adet=1):
    return {
        "type": "minecraft:crafting_shapeless",
        "category": "misc",
        "ingredients": [{"item": malzeme} for malzeme in malzemeler],
        "result": {"id": f"pastbound:{cikti}", "count": adet}
    }

tarifler_yeni = {
    "chronicle_scrap": shapeless("chronicle_scrap", ["pastbound:echo_shard", "minecraft:paper"], 2),
    "history_ink": shapeless("history_ink", ["pastbound:chronicle_scrap", "minecraft:ink_sac", "minecraft:glass_bottle"]),
    "time_stone": shapeless("time_stone", ["pastbound:echo_shard", "minecraft:amethyst_shard", "minecraft:clock"]),
    "echo_seal": shapeless("echo_seal", ["pastbound:history_ink", "pastbound:time_stone", "minecraft:gold_nugget"])
}
for kimlik, icerik in tarifler_yeni.items():
    (tarifler / f"{kimlik}.json").write_text(json.dumps(icerik, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
