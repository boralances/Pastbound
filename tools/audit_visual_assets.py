import json
from pathlib import Path
from PIL import Image

root = Path(__file__).resolve().parents[1]
assets = root / "src/main/resources/assets/pastbound"

for name in ["egyptian_hieroglyph", "egyptian_sphinx", "greek_olive_altar", "egyptian_sandstone", "egyptian_gold_inlay", "egyptian_obelisk", "ancient_boat", "egyptian_boat", "greek_boat", "viking_boat"]:
    candidates = [assets / "textures/block" / f"{name}.png", assets / "textures/item" / f"{name}.png", assets / "textures/entity" / f"{name}.png"]
    for path in candidates:
        if path.exists():
            image = Image.open(path).convert("RGBA")
            alpha = image.getchannel("A")
            extrema = alpha.getextrema()
            print(f"TEXTURE {path.relative_to(root)} size={image.size} alpha={extrema}")

for name in ["egyptian_sandstone", "egyptian_gold_inlay", "egyptian_obelisk", "ancient_boat", "egyptian_boat", "greek_boat", "viking_boat"]:
    path = assets / "items" / f"{name}.json"
    print(f"ITEM_DEF {name} {'OK' if path.exists() else 'MISSING'}")
