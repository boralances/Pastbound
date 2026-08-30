from pathlib import Path
import json

root = Path(__file__).resolve().parents[1]
assets = root / "src/main/resources/assets/pastbound"
for name in ("uruk_cedar", "chinampa_cypress"):
    state = assets / f"blockstates/{name}_trapdoor.json"
    data = json.loads(state.read_text(encoding="utf-8"))
    for key, value in data["variants"].items():
        if "half=top,open=true" in key:
            value["model"] = f"pastbound:block/{name}_trapdoor_top_open"
    state.write_text(json.dumps(data, indent=2) + "\n", encoding="utf-8")
    model = {
        "parent": "minecraft:block/template_orientable_trapdoor_open",
        "textures": {"texture": f"pastbound:block/{name}_trapdoor"}
    }
    (assets / f"models/block/{name}_trapdoor_top_open.json").write_text(json.dumps(model, indent=2) + "\n", encoding="utf-8")
print("trapdoor_top_models_fixed")
