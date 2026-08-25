from pathlib import Path

kok = Path(__file__).resolve().parents[1]
recipe_klasoru = kok / "src" / "main" / "resources" / "data" / "pastbound" / "recipes"
relic_kimlikleri = {
    "rossetta_tasi", "gilgamesh_tableti", "anubis_ankhi", "minos_labirent_muhru",
    "roma_aureusu", "viking_gunes_pusulasi", "samuray_kabzasi", "maya_gunes_carki",
    "inka_quipusu", "harappa_muhru", "song_porseleni", "benin_bronzu",
    "aztek_gunes_tasi", "abbasid_murekkebi", "ronesans_astrolabi", "antikithera_duzenegi",
    "catalhoyuk_boncugu", "bizans_mozaigi", "timbuktu_kalemi", "apollo17_armasi",
    "ilhanli_madalyonu", "polinezya_yildiz_haritasi", "mali_tuz_muhru", "iskandinav_runetasi"
}

for kimlik in relic_kimlikleri:
    (recipe_klasoru / f"{kimlik}.json").unlink(missing_ok=True)

print(f"Chest-only relic policy active: {len(relic_kimlikleri)} relic recipes removed.")
