from pathlib import Path
from PIL import Image

root = Path(__file__).resolve().parents[1]
source_root = root / "src/main/resources/assets/pastbound/textures/item/relics"
target_root = root / "src/main/resources/assets/pastbound/textures/mob_effect"
icons = {
    "rossetta_bilgisi": "rossetta_tasi",
    "gilgamesh_dayanikliligi": "gilgamesh_tableti",
    "anubis_arindirmasi": "anubis_ankhi",
    "minos_sicramasi": "minos_labirent_muhru",
    "roma_aureusu": "roma_aureusu",
    "viking_gece_gorusu": "viking_gunes_pusulasi",
    "samuray_korumasi": "samuray_kabzasi",
    "maya_takvimi": "maya_gunes_carki",
    "inka_baglari": "inka_quipusu",
    "harappa_kil_tabletleri": "harappa_muhru",
}

target_root.mkdir(parents=True, exist_ok=True)
for effect_id, relic_id in icons.items():
    source = Image.open(source_root / f"{relic_id}.png").convert("RGBA")
    icon = source.resize((18, 18), Image.Resampling.NEAREST)
    icon.save(target_root / f"{effect_id}.png", format="PNG", optimize=True)
print(f"RELIC_EFFECT_TEXTURES_READY count={len(icons)}")
