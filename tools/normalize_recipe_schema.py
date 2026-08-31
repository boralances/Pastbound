from pathlib import Path
import json

root = Path('src/main/resources/data/pastbound/recipe')
changed = 0
for path in sorted(root.glob('*.json')):
    data = json.loads(path.read_text())
    dirty = False
    key = data.get('key')
    if isinstance(key, dict):
        for symbol, ingredient in list(key.items()):
            if isinstance(ingredient, dict) and set(ingredient) == {'item'}:
                key[symbol] = ingredient['item']
                dirty = True
    ingredients = data.get('ingredients')
    if isinstance(ingredients, list):
        for index, ingredient in enumerate(ingredients):
            if isinstance(ingredient, dict) and set(ingredient) == {'item'}:
                ingredients[index] = ingredient['item']
                dirty = True
    ingredient = data.get('ingredient')
    if isinstance(ingredient, dict) and set(ingredient) == {'item'}:
        data['ingredient'] = ingredient['item']
        dirty = True
    if dirty:
        path.write_text(json.dumps(data, ensure_ascii=False, indent=2) + '\n')
        changed += 1
print(f'NORMALIZED_RECIPES {changed}')
