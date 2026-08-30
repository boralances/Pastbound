from pathlib import Path

from PIL import Image, ImageDraw

root = Path(__file__).resolve().parents[1] / "src/main/resources/assets/pastbound/textures/block"
root.mkdir(parents=True, exist_ok=True)

def create(name, base, accent, rune):
    image = Image.new("RGBA", (16, 16), base + (255,))
    draw = ImageDraw.Draw(image)
    for x in range(16):
        for y in range(16):
            if (x * 5 + y * 3) % 11 == 0:
                draw.point((x, y), fill=accent + (255,))
    draw.rectangle((1, 1, 14, 14), outline=accent + (255,))
    draw.rectangle((3, 3, 12, 12), outline=rune + (255,))
    draw.point((7, 7), fill=rune + (255,))
    draw.point((8, 7), fill=rune + (255,))
    draw.point((7, 8), fill=rune + (255,))
    draw.point((8, 8), fill=rune + (255,))
    image.save(root / f"{name}.png")

create("ancient_storage_side", (46, 32, 58), (122, 76, 151), (223, 177, 72))
create("ancient_storage_top", (66, 43, 75), (168, 107, 184), (235, 203, 104))
create("ancient_storage_bottom", (26, 22, 34), (73, 51, 82), (111, 83, 126))
gui = Image.new("RGBA", (176, 222), (19, 14, 27, 255))
gdraw = ImageDraw.Draw(gui)
gdraw.rectangle((0, 0, 175, 221), fill=(42, 27, 53, 255), outline=(223, 177, 72, 255), width=2)
gdraw.rectangle((4, 4, 171, 127), fill=(28, 19, 37, 255), outline=(122, 76, 151, 255))
gdraw.rectangle((4, 132, 171, 217), fill=(31, 22, 40, 255), outline=(122, 76, 151, 255))
for row in range(6):
    for col in range(9):
        x = 8 + col * 18
        y = 18 + row * 18
        gdraw.rectangle((x, y, x + 16, y + 16), outline=(88, 57, 105, 255))
for row in range(3):
    for col in range(9):
        x = 8 + col * 18
        y = 140 + row * 18
        gdraw.rectangle((x, y, x + 16, y + 16), outline=(88, 57, 105, 255))
for col in range(9):
    x = 8 + col * 18
    gdraw.rectangle((x, 198, x + 16, 214), outline=(168, 107, 184, 255))
gui.save(root.parent / "gui/ancient_storage.png")
print("ancient_storage_textures_created")
