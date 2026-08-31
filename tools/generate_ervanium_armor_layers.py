from pathlib import Path
from PIL import Image, ImageDraw

root = Path('src/main/resources/assets/pastbound/textures/entity/equipment')
for layer_name in ('humanoid', 'humanoid_leggings'):
    out = root / layer_name / 'ervanium.png'
    out.parent.mkdir(parents=True, exist_ok=True)
    image = Image.new('RGBA', (64, 32), (0, 0, 0, 0))
    draw = ImageDraw.Draw(image)
    base = (39, 192, 184, 255)
    shadow = (13, 86, 105, 255)
    glow = (123, 255, 226, 255)
    dark = (8, 39, 58, 255)
    for x in range(0, 64, 2):
        draw.line((x, 0, x, 31), fill=shadow if (x // 2) % 3 == 0 else base, width=1)
    draw.rectangle((8, 8, 15, 15), outline=glow, fill=base)
    draw.rectangle((40, 8, 47, 15), outline=glow, fill=base)
    draw.rectangle((20, 8, 35, 15), outline=glow, fill=base)
    draw.rectangle((20, 0, 35, 7), outline=glow, fill=base)
    draw.rectangle((20, 16, 35, 23), outline=dark, fill=shadow)
    draw.rectangle((4, 16, 11, 31), outline=glow, fill=base)
    draw.rectangle((12, 16, 19, 31), outline=dark, fill=shadow)
    draw.rectangle((36, 16, 43, 31), outline=glow, fill=base)
    draw.rectangle((44, 16, 51, 31), outline=dark, fill=shadow)
    for x, y in ((22, 2), (30, 2), (10, 10), (42, 10), (24, 18), (32, 18)):
        draw.point((x, y), fill=glow)
    image.save(out)
    print(f'ARMOR_ASSET_OK {out}')
