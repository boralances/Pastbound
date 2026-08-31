from pathlib import Path
from PIL import Image, ImageDraw

root = Path(__file__).resolve().parents[1]
assets = root / "src/main/resources/assets/pastbound"
(assets / "items").mkdir(parents=True, exist_ok=True)
(assets / "textures/item").mkdir(parents=True, exist_ok=True)

image = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
draw = ImageDraw.Draw(image)
draw.rectangle((2, 1, 13, 14), fill=(92, 50, 28, 255), outline=(35, 20, 14, 255))
draw.rectangle((4, 3, 12, 13), fill=(229, 214, 169, 255))
draw.rectangle((5, 4, 11, 11), fill=(245, 233, 194, 255))
draw.rectangle((5, 4, 6, 11), fill=(255, 248, 220, 255))
draw.point((8, 6), fill=(78, 108, 105, 255))
draw.point((9, 7), fill=(78, 108, 105, 255))
draw.point((7, 8), fill=(78, 108, 105, 255))
draw.point((8, 9), fill=(78, 108, 105, 255))
draw.line((7, 12, 10, 12), fill=(126, 86, 46, 255), width=1)
image.save(assets / "textures/item/tarih_wiki.png")

model = {
    "parent": "minecraft:item/generated",
    "textures": {"layer0": "pastbound:item/tarih_wiki"},
}
(assets / "models/item/tarih_wiki.json").write_text(__import__("json").dumps(model, indent=2) + "\n", encoding="utf-8")
definition = {"model": {"type": "minecraft:model", "model": "pastbound:item/tarih_wiki"}}
(assets / "items/tarih_wiki.json").write_text(__import__("json").dumps(definition, indent=2) + "\n", encoding="utf-8")
print("WIKI_ASSET_OK")
