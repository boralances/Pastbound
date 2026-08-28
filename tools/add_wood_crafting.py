from pathlib import Path
import json

root = Path(__file__).resolve().parents[1]
recipe_root = root / "src/main/resources/data/pastbound/recipe"
for family in ("uruk_cedar", "chinampa_cypress"):
    plank = f"pastbound:{family}_planks"
    recipes = {
        f"{family}_crafting_table": {
            "type": "minecraft:crafting_shaped",
            "category": "building",
            "group": f"{family}_crafting_table",
            "key": {"#": plank},
            "pattern": ["##", "##"],
            "result": {"count": 1, "id": "minecraft:crafting_table"}
        },
        f"{family}_sticks": {
            "type": "minecraft:crafting_shaped",
            "category": "misc",
            "group": f"{family}_sticks",
            "key": {"#": plank},
            "pattern": ["#", "#"],
            "result": {"count": 4, "id": "minecraft:stick"}
        },
        f"{family}_wooden_sword": {
            "type": "minecraft:crafting_shaped",
            "category": "combat",
            "group": "wooden_sword",
            "key": {"#": plank, "|": "minecraft:stick"},
            "pattern": ["#", "#", "|"],
            "result": {"count": 1, "id": "minecraft:wooden_sword"}
        },
        f"{family}_wooden_pickaxe": {
            "type": "minecraft:crafting_shaped",
            "category": "tools",
            "group": "wooden_pickaxe",
            "key": {"#": plank, "|": "minecraft:stick"},
            "pattern": ["###", " | ", " | "],
            "result": {"count": 1, "id": "minecraft:wooden_pickaxe"}
        },
        f"{family}_wooden_axe": {
            "type": "minecraft:crafting_shaped",
            "category": "tools",
            "group": "wooden_axe",
            "key": {"#": plank, "|": "minecraft:stick"},
            "pattern": ["##", "#|", " |"],
            "result": {"count": 1, "id": "minecraft:wooden_axe"}
        },
        f"{family}_wooden_shovel": {
            "type": "minecraft:crafting_shaped",
            "category": "tools",
            "group": "wooden_shovel",
            "key": {"#": plank, "|": "minecraft:stick"},
            "pattern": ["#", "|", "|"],
            "result": {"count": 1, "id": "minecraft:wooden_shovel"}
        },
        f"{family}_wooden_hoe": {
            "type": "minecraft:crafting_shaped",
            "category": "tools",
            "group": "wooden_hoe",
            "key": {"#": plank, "|": "minecraft:stick"},
            "pattern": ["##", " |", " |"],
            "result": {"count": 1, "id": "minecraft:wooden_hoe"}
        }
    }
    for name, data in recipes.items():
        (recipe_root / f"{name}.json").write_text(json.dumps(data, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
print("wood_crafting_added")
