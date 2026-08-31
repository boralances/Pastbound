from pathlib import Path
import json
import re

root = Path(__file__).resolve().parents[1]
recipes = root / "src/main/resources/data/pastbound/recipes"
registered = set()
for path in (root / "src/main/java/dev/pastbound/registry").glob("*.java"):
    registered.update(re.findall(r'register\("([a-z0-9_]+)"', path.read_text(encoding="utf-8")))
vanilla = {"stick", "chest", "amethyst_block", "copper_ingot", "redstone", "iron_ingot", "gold_ingot", "diamond", "netherite_block", "stone", "furnace", "crafting_table"}
errors = []
for path in sorted(recipes.glob("*.json")):
    try:
        data = json.loads(path.read_text(encoding="utf-8"))
    except Exception as error:
        errors.append(f"{path.name}: invalid json {error}")
        continue
    if data.get("type") not in {"minecraft:crafting_shaped", "minecraft:crafting_shapeless", "minecraft:smelting", "minecraft:blasting"}:
        errors.append(f"{path.name}: unsupported type {data.get('type')}")
    result = data.get("result", {})
    result_id = result.get("id") or result.get("item")
    if not result_id:
        errors.append(f"{path.name}: missing result id")
    elif result_id.startswith("pastbound:") and result_id.removeprefix("pastbound:") not in registered:
        errors.append(f"{path.name}: unknown result {result_id}")
    key = data.get("key", {})
    values = list(key.values())
    if isinstance(data.get("ingredient"), dict):
        values.append(data["ingredient"])
    for value in values:
        ids = []
        if isinstance(value, str): ids = [value]
        elif isinstance(value, dict):
            if "item" in value: ids = [value["item"]]
            if "tag" in value: continue
        for item_id in ids:
            if item_id.startswith("pastbound:") and item_id.removeprefix("pastbound:") not in registered:
                errors.append(f"{path.name}: unknown ingredient {item_id}")
print(f"RECIPE_COUNT={len(list(recipes.glob('*.json')))}")
if errors:
    print("\n".join(errors))
    raise SystemExit(1)
print("RECIPES_VALID")
