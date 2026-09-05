from pathlib import Path
from PIL import Image, ImageDraw

root = Path(__file__).resolve().parents[1] / "src/main/resources/assets/pastbound/textures/block"


def save(name, draw_fn):
    image = Image.new("RGBA", (16, 16), (214, 173, 92, 255))
    draw = ImageDraw.Draw(image)
    draw_fn(draw)
    image.save(root / f"{name}.png")


def sandstone(draw):
    draw.rectangle((0, 0, 15, 15), fill=(191, 145, 70, 255))
    for y in (3, 8, 13):
        draw.line((0, y, 15, y), fill=(224, 183, 103, 255))
    for x, y in ((3, 3), (11, 8), (6, 13)):
        draw.rectangle((x, y, x + 1, y + 1), fill=(151, 106, 49, 255))
    draw.rectangle((0, 0, 15, 1), fill=(235, 199, 124, 255))


def gold_inlay(draw):
    draw.rectangle((0, 0, 15, 15), fill=(176, 131, 60, 255))
    draw.rectangle((0, 0, 15, 2), fill=(248, 205, 70, 255))
    draw.rectangle((0, 13, 15, 15), fill=(118, 79, 37, 255))
    for x in (2, 7, 12):
        draw.rectangle((x, 3, x + 1, 12), fill=(225, 169, 43, 255))
        draw.point((x + 1, 5), fill=(255, 232, 120, 255))
    draw.line((0, 7, 15, 7), fill=(112, 72, 30, 255))


def obelisk(draw):
    draw.rectangle((0, 0, 15, 15), fill=(203, 160, 81, 255))
    draw.rectangle((2, 0, 13, 15), fill=(187, 139, 65, 255))
    draw.rectangle((4, 0, 11, 15), fill=(220, 180, 99, 255))
    draw.line((7, 0, 7, 15), fill=(245, 207, 124, 255))
    draw.line((8, 2, 8, 13), fill=(147, 101, 45, 255))
    draw.polygon([(5, 0), (10, 0), (8, 3)], fill=(245, 203, 107, 255))

save("egyptian_sandstone", sandstone)
save("egyptian_gold_inlay", gold_inlay)
save("egyptian_obelisk", obelisk)
