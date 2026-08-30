from pathlib import Path
import json

from PIL import Image

root = Path(__file__).resolve().parents[1]
texture_root = root / "src/main/resources/assets/pastbound/textures/block"
for path in texture_root.glob("*.png"):
    if any(part in path.stem for part in ("leaves", "sapling")):
        continue
    image = Image.open(path).convert("RGBA")
    pixels = []
    for red, green, blue, alpha in image.getdata():
        pixels.append((red, green, blue, 255))
    image.putdata(pixels)
    image.save(path)

model_root = root / "src/main/resources/assets/pastbound/models/item"
wood = {
    "uruk_cedar": "uruk_cedar",
    "chinampa_cypress": "chinampa_cypress"
}
for family, texture in wood.items():
    models = {
        f"{family}_door.json": {
            "parent": "minecraft:item/generated",
            "textures": {"layer0": f"pastbound:block/{texture}_door"}
        },
        f"{family}_trapdoor.json": {
            "parent": "minecraft:block/trapdoor_bottom",
            "textures": {"texture": f"pastbound:block/{texture}_trapdoor"}
        },
        f"{family}_fence.json": {
            "parent": "minecraft:block/fence_inventory",
            "textures": {"texture": f"pastbound:block/{texture}_planks"}
        },
        f"{family}_fence_gate.json": {
            "parent": "minecraft:block/template_fence_gate",
            "textures": {"texture": f"pastbound:block/{texture}_planks"}
        }
    }
    for name, data in models.items():
        (model_root / name).write_text(json.dumps(data, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
print("visual_assets_fixed")
