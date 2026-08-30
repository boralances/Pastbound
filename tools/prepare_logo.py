from pathlib import Path
from PIL import Image

kaynak = Path("pastbound_logo.png")
logo = Image.open(kaynak).convert("RGBA")
logo.thumbnail((512, 512), Image.Resampling.LANCZOS)
for hedef in [Path("src/main/resources/logo.png"), Path("src/main/resources/assets/pastbound/textures/gui/pastbound_logo.png")]:
    logo.save(hedef, format="PNG", optimize=True)
print(f"LOGO_READY {logo.width}x{logo.height}")
