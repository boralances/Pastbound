from pathlib import Path
import json

root = Path(__file__).resolve().parents[1] / "src/main/resources/data/pastbound/recipes"
changed = 0
for path in sorted(root.glob("*.json")):
    data = json.loads(path.read_text(encoding="utf-8"))
    before = json.dumps(data, ensure_ascii=False, sort_keys=True)
    if data.get("type") == "minecraft:crafting_shaped":
        for key, value in list(data.get("key", {}).items()):
            if isinstance(value, str):
                data["key"][key] = {"item": value} if ":" in value else {"tag": f"minecraft:{value}"}
    if data.get("type") in {"minecraft:crafting_shapeless", "minecraft:smelting", "minecraft:blasting"}:
        if isinstance(data.get("ingredient"), str):
            value = data["ingredient"]
            data["ingredient"] = {"item": value} if ":" in value else {"item": f"minecraft:{value}"}
        if isinstance(data.get("ingredients"), list):
            yeni = []
            for value in data["ingredients"]:
                if isinstance(value, str):
                    yeni.append({"item": value} if ":" in value else {"item": f"minecraft:{value}"})
                else:
                    yeni.append(value)
            data["ingredients"] = yeni
    after = json.dumps(data, ensure_ascii=False, sort_keys=True)
    if before != after:
        path.write_text(json.dumps(data, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
        changed += 1
print(f"RECIPES_REPAIRED={changed}")

