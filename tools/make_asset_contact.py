from pathlib import Path
from PIL import Image, ImageDraw

root = Path(__file__).resolve().parents[1]
paths = [
    root / "src/main/resources/assets/pastbound/textures/block/uruk_cedar_door.png",
    root / "src/main/resources/assets/pastbound/textures/block/uruk_cedar_trapdoor.png",
    root / "src/main/resources/assets/pastbound/textures/block/uruk_cedar_fence.png",
    root / "src/main/resources/assets/pastbound/textures/block/uruk_cedar_planks.png",
    root / "src/main/resources/assets/pastbound/textures/block/time_stone_ore.png",
    root / "src/main/resources/assets/pastbound/textures/item/chronicle_compass.png",
]
canvas = Image.new("RGB", (768, 330), (25, 25, 25))
draw = ImageDraw.Draw(canvas)
for i, path in enumerate(paths):
    image = Image.open(path).convert("RGBA").resize((96, 96), Image.Resampling.NEAREST)
    x = 16 + (i % 6) * 126
    y = 36
    canvas.paste(image, (x, y), image)
    draw.text((x, 12), path.stem[:18], fill=(240, 240, 240))
canvas.save(root / "asset_contact.png")
