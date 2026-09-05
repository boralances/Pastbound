from pathlib import Path
from PIL import Image, ImageDraw

root = Path(__file__).resolve().parents[1] / "src/main/resources/assets/pastbound/textures/block"


def make_texture(name: str, trapdoor: bool) -> None:
    image = Image.new("RGBA", (16, 16), (48, 10, 18, 255))
    draw = ImageDraw.Draw(image)
    base = (91, 20, 35, 255)
    dark = (55, 9, 20, 255)
    bright = (148, 38, 48, 255)
    vein = (184, 65, 52, 255)
    draw.rectangle((0, 0, 15, 15), fill=base)
    if trapdoor:
        draw.rectangle((0, 0, 15, 2), fill=dark)
        draw.rectangle((0, 13, 15, 15), fill=dark)
        for x in (3, 7, 11):
            draw.rectangle((x, 3, x + 1, 12), fill=dark)
            draw.line((x + 1, 4, x + 2, 11), fill=bright)
    else:
        for x in (2, 6, 10, 14):
            draw.rectangle((x, 0, x + 1, 15), fill=dark)
            draw.line((x + 1, 1, x + 2, 14), fill=bright)
    draw.line((1, 5, 4, 4), fill=vein, width=1)
    draw.line((9, 10, 13, 8), fill=vein, width=1)
    draw.point((4, 12), fill=(222, 94, 57, 255))
    draw.point((13, 3), fill=(222, 94, 57, 255))
    image.save(root / f"{name}.png")

make_texture("nether_wart_door", trapdoor=False)
make_texture("nether_wart_trapdoor", trapdoor=True)
