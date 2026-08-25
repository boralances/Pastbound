import json
from pathlib import Path

kok = Path(__file__).resolve().parents[1]
tarifler = kok / "src/main/resources/data/pastbound/recipes"
tarifler.mkdir(parents=True, exist_ok=True)

malzemeler = {
    "rossetta_tasi": "minecraft:paper",
    "gilgamesh_tableti": "minecraft:clay_ball",
    "anubis_ankhi": "minecraft:gold_nugget",
    "minos_labirent_muhru": "minecraft:compass",
    "roma_aureusu": "minecraft:gold_ingot",
    "viking_gunes_pusulasi": "minecraft:prismarine_crystals",
    "samuray_kabzasi": "minecraft:iron_sword",
    "maya_gunes_carki": "minecraft:clock",
    "inka_quipusu": "minecraft:string",
    "harappa_muhru": "minecraft:terracotta",
    "song_porseleni": "minecraft:quartz",
    "benin_bronzu": "minecraft:copper_ingot",
    "aztek_gunes_tasi": "minecraft:redstone",
    "abbasid_murekkebi": "minecraft:ink_sac",
    "ronesans_astrolabi": "minecraft:spyglass",
    "antikithera_duzenegi": "minecraft:copper_block",
    "catalhoyuk_boncugu": "minecraft:clay",
    "bizans_mozaigi": "minecraft:lapis_lazuli",
    "timbuktu_kalemi": "minecraft:book",
    "apollo17_armasi": "minecraft:iron_ingot",
    "ilhanli_madalyonu": "minecraft:emerald",
    "polinezya_yildiz_haritasi": "minecraft:nautilus_shell",
    "mali_tuz_muhru": "minecraft:sugar",
    "iskandinav_runetasi": "minecraft:stone"
}

for kimlik, malzeme in malzemeler.items():
    veri = {
        "type": "minecraft:crafting_shapeless",
        "category": "misc",
        "ingredients": [
            {"item": "pastbound:echo_shard"},
            {"item": malzeme}
        ],
        "result": {
            "id": f"pastbound:{kimlik}",
            "count": 1
        }
    }
    (tarifler / f"{kimlik}.json").write_text(json.dumps(veri, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
