from pathlib import Path
from PIL import Image

root = Path(__file__).resolve().parents[1] / "src/main/resources/assets/pastbound/textures"
for path in sorted(root.rglob("*.png")):
    image = Image.open(path).convert("RGBA")
    pixels = list(image.getdata())
    bad = sum(1 for r, g, b, a in pixels if a > 0 and r > 100 and b > 100 and g < 40)
    black = sum(1 for r, g, b, a in pixels if a > 0 and r < 12 and g < 12 and b < 12)
    if bad + black >= max(8, len(pixels) * 0.35):
        print(path.relative_to(root), image.size, bad, black, len(pixels))
