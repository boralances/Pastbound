import json
from pathlib import Path
from PIL import Image

root = Path(__file__).resolve().parents[1]
assets = root / "src/main/resources/assets/pastbound"
errors = []
model_paths = set()

for path in assets.rglob("*.json"):
    try:
        data = json.loads(path.read_text(encoding="utf-8"))
    except Exception as exc:
        errors.append(f"invalid_json:{path.relative_to(root)}:{exc}")
        continue
    if "/models/" in path.as_posix():
        model_paths.add(path.relative_to(assets).with_suffix("").as_posix().removeprefix("models/"))
        for value in data.get("textures", {}).values():
            if not isinstance(value, str) or not value.startswith("pastbound:"):
                continue
            resource = value.removeprefix("pastbound:")
            texture_path = assets / "textures" / f"{resource}.png"
            if not texture_path.exists():
                errors.append(f"missing_texture:{path.relative_to(root)}:{value}")

for path in (assets / "blockstates").glob("*.json"):
    try:
        data = json.loads(path.read_text(encoding="utf-8"))
    except Exception:
        continue
    references = []
    for variant in data.get("variants", {}).values():
        references.append(variant.get("model"))
    for part in data.get("multipart", []):
        apply = part.get("apply", {})
        references.append(apply.get("model"))
    for reference in references:
        if isinstance(reference, str) and reference.startswith("pastbound:"):
            model = reference.removeprefix("pastbound:")
            if model not in model_paths:
                errors.append(f"missing_model:{path.relative_to(root)}:{reference}")

for path in (assets / "textures").rglob("*.png"):
    try:
        with Image.open(path) as image:
            if image.size != (16, 16) and "gui" not in path.parts and "mob_effect" not in path.parts:
                errors.append(f"unexpected_dimensions:{path.relative_to(root)}:{image.size[0]}x{image.size[1]}")
            if "block" in path.parts and image.mode == "RGBA":
                alpha = {pixel[3] for pixel in image.getdata()}
                if not alpha.issubset({0, 255}):
                    errors.append(f"partial_block_alpha:{path.relative_to(root)}:{sorted(alpha)}")
    except Exception as exc:
        errors.append(f"unreadable_png:{path.relative_to(root)}:{exc}")

if errors:
    print("ASSET_AUDIT_FAILED")
    print("\n".join(sorted(errors)))
    raise SystemExit(1)
print(f"ASSET_AUDIT_OK models={len(model_paths)} textures={len(list((assets / 'textures').rglob('*.png')))}")
