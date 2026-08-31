from pathlib import Path
import json

root = Path(__file__).resolve().parents[1]
biomes = root / "src/main/resources/data/pastbound/worldgen/biome"
for path in biomes.glob("*.json"):
    data = json.loads(path.read_text(encoding="utf-8"))
    data.pop("attributes", None)
    effects = data.setdefault("effects", {})
    renkler = {
        "sky_color": 7907327,
        "fog_color": 12638463,
        "water_color": 4159204,
        "water_fog_color": 329011,
    }
    for key, value in renkler.items():
        effects[key] = value
    data.setdefault("carvers", [])
    data.setdefault("features", [[] for _ in range(11)])
    if len(data["features"]) < 11:
        data["features"] += [[] for _ in range(11 - len(data["features"]))]
    path.write_text(json.dumps(data, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")

assets = root / "src/main/resources/assets/pastbound"
for path in (assets / "models/item").glob("*.json"):
    data = json.loads(path.read_text(encoding="utf-8"))
    if "parent" not in data and "textures" not in data:
        data["parent"] = "pastbound:block/" + path.stem
        path.write_text(json.dumps(data, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
print("registry_assets_repaired")
