from pathlib import Path
from PIL import Image

root = Path(__file__).resolve().parents[1]
source = root / "src/main/resources/assets/pastbound/textures/item/zaman_makinesi.png"
logo = Image.open(source).convert("RGBA")
canvas = Image.new("RGBA", (512, 512), (0, 0, 0, 0))
scale = min(512 // logo.width, 512 // logo.height)
scaled = logo.resize((logo.width * scale, logo.height * scale), Image.Resampling.NEAREST)
left = (512 - scaled.width) // 2
top = (512 - scaled.height) // 2
canvas.alpha_composite(scaled, (left, top))
for target in [root / "pastbound_logo.png", root / "src/main/resources/logo.png", root / "src/main/resources/assets/pastbound/textures/gui/pastbound_logo.png"]:
    target.parent.mkdir(parents=True, exist_ok=True)
    canvas.save(target, format="PNG", optimize=True)
print(f"TIME_MACHINE_LOGO_READY source={logo.width}x{logo.height} output=512x512 scale={scale}")
