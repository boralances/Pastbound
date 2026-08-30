from pathlib import Path
from PIL import Image, ImageDraw

root = Path(__file__).resolve().parents[1] / "src/main/resources/assets/pastbound/textures/item"
root.mkdir(parents=True, exist_ok=True)

def texture(name, wood, metal, glow):
    image = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    draw = ImageDraw.Draw(image)
    draw.rectangle((6, 2, 9, 13), fill=wood + (255,))
    draw.rectangle((4, 4, 11, 8), fill=metal + (255,))
    draw.rectangle((5, 5, 10, 7), fill=glow + (255,))
    draw.rectangle((3, 10, 12, 12), fill=metal + (255,))
    draw.point((4, 4), fill=(255, 236, 173, 255))
    draw.point((11, 8), fill=(26, 18, 22, 255))
    image.save(root / f"{name}.png")

texture("crafting_table_cubugu", (153, 94, 44), (92, 54, 32), (205, 153, 73))
texture("gelistirilmis_firin_cubugu", (66, 54, 62), (135, 143, 153), (255, 131, 46))
print("portable_workstation_textures_created")
