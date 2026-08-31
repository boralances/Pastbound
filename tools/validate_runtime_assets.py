from pathlib import Path
import json

root = Path(__file__).resolve().parents[1] / "src/main/resources/assets/pastbound"
missing = []
for path in root.rglob("*.json"):
    try:
        data = json.loads(path.read_text(encoding="utf-8"))
    except Exception as error:
        raise SystemExit(f"invalid json: {path}: {error}")
    textures = data.get("textures", {}) if isinstance(data, dict) else {}
    for value in textures.values():
        if not isinstance(value, str) or value.startswith("#"):
            continue
        if value.startswith("minecraft:"):
            continue
        value = value.removeprefix("pastbound:")
        if value.startswith("block/") or value.startswith("item/") or value.startswith("gui/") or value.startswith("mob_effect/") or value.startswith("slot/"):
            target = root / "textures" / f"{value}.png"
        else:
            target = root / "textures" / f"{value}.png"
        if not target.exists():
            missing.append(f"{path.relative_to(root)} -> {value}")
if missing:
    print("MISSING_TEXTURES")
    print("\n".join(sorted(set(missing))))
    raise SystemExit(1)
print(f"RUNTIME_ASSETS_OK models={len(list((root / 'models').rglob('*.json')))} textures={len(list((root / 'textures').rglob('*.png')))}")
