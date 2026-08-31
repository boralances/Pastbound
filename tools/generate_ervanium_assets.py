from pathlib import Path
import json
from PIL import Image, ImageDraw

ROOT = Path(__file__).resolve().parents[1]
ASSETS = ROOT / "src/main/resources/assets/pastbound"
RECIPES = ROOT / "src/main/resources/data/pastbound/recipes"

items = {
    "ervanium_ore": (69, 174, 166),
    "ervanium_smithing_template": (176, 232, 212),
    "ervanium_ingot": (89, 226, 205),
    "ervanium_helmet": (126, 242, 215),
    "ervanium_chestplate": (76, 205, 190),
    "ervanium_leggings": (67, 178, 169),
    "ervanium_boots": (52, 151, 153),
    "ervanium_sword": (211, 255, 234),
}

for name, color in items.items():
    image = Image.new("RGBA", (64, 64), (0, 0, 0, 0))
    draw = ImageDraw.Draw(image)
    dark = tuple(max(0, c - 70) for c in color)
    light = tuple(min(255, c + 35) for c in color)
    draw.rounded_rectangle((8, 8, 55, 55), radius=8, fill=dark + (255,), outline=(25, 58, 68, 255), width=3)
    draw.rounded_rectangle((13, 13, 50, 50), radius=6, fill=color + (255,), outline=light + (255,), width=3)
    draw.line((18, 42, 45, 20), fill=(231, 255, 241, 220), width=3)
    draw.line((22, 47, 49, 25), fill=(33, 118, 119, 180), width=2)
    image.resize((16, 16), Image.Resampling.LANCZOS).save(ASSETS / "textures/item" / f"{name}.png")
    model = {"parent": "minecraft:item/generated", "textures": {"layer0": f"pastbound:item/{name}"}}
    (ASSETS / "models/item" / f"{name}.json").write_text(json.dumps(model, indent=2) + "\n", encoding="utf-8")
    definition = {"model": {"type": "minecraft:model", "model": f"pastbound:item/{name}"}}
    (ASSETS / "items" / f"{name}.json").write_text(json.dumps(definition, indent=2) + "\n", encoding="utf-8")

def smithing(name, base):
    data = {
        "type": "minecraft:smithing_transform",
        "template": "pastbound:ervanium_smithing_template",
        "base": f"minecraft:{base}",
        "addition": "pastbound:ervanium_ingot",
        "result": {"id": f"pastbound:{name}"},
    }
    (RECIPES / f"{name}.json").write_text(json.dumps(data, indent=2) + "\n", encoding="utf-8")

smithing("ervanium_helmet", "diamond_helmet")
smithing("ervanium_chestplate", "diamond_chestplate")
smithing("ervanium_leggings", "diamond_leggings")
smithing("ervanium_boots", "diamond_boots")
smithing("ervanium_sword", "diamond_sword")

smelt = {
    "type": "minecraft:smelting",
    "category": "misc",
    "ingredient": {"item": "pastbound:ervanium_ore"},
    "result": {"id": "pastbound:ervanium_ingot"},
    "experience": 3.0,
    "cookingtime": 200,
}
(RECIPES / "ervanium_ingot_from_smelting.json").write_text(json.dumps(smelt, indent=2) + "\n", encoding="utf-8")

