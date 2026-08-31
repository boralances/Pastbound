from pathlib import Path
import json

root = Path(__file__).resolve().parents[1]
assets = root / 'src/main/resources/assets/pastbound'
for source in (assets / 'blockstates').glob('uruk_cedar_door.json'):
    data = json.loads(source.read_text())
    target = assets / 'blockstates/nether_wart_door.json'
    target.write_text(json.dumps(data, ensure_ascii=False, indent=2).replace('uruk_cedar_door', 'nether_wart_door') + '\n', encoding='utf-8')
for source in (assets / 'blockstates').glob('uruk_cedar_trapdoor.json'):
    data = json.loads(source.read_text())
    target = assets / 'blockstates/nether_wart_trapdoor.json'
    target.write_text(json.dumps(data, ensure_ascii=False, indent=2).replace('uruk_cedar_trapdoor', 'nether_wart_trapdoor') + '\n', encoding='utf-8')
for pattern in ('uruk_cedar_door_*.json', 'uruk_cedar_trapdoor.json'):
    for source in (assets / 'models/block').glob(pattern):
        target = assets / 'models/block' / source.name.replace('uruk_cedar', 'nether_wart')
        text = source.read_text(encoding='utf-8').replace('uruk_cedar_door', 'nether_wart_door').replace('uruk_cedar_trapdoor', 'nether_wart_trapdoor')
        text = text.replace('pastbound:block/nether_wart_door', 'minecraft:block/nether_wart_block').replace('pastbound:block/nether_wart_trapdoor', 'minecraft:block/nether_wart_block')
        target.write_text(text, encoding='utf-8')
for source_name in ('uruk_cedar_door.json', 'uruk_cedar_trapdoor.json'):
    source = assets / 'items' / source_name
    target = assets / 'items' / source_name.replace('uruk_cedar', 'nether_wart')
    text = source.read_text(encoding='utf-8').replace('uruk_cedar', 'nether_wart')
    target.write_text(text, encoding='utf-8')
recipes = root / 'src/main/resources/data/pastbound/recipes'
recipes.joinpath('nether_wart_door.json').write_text(json.dumps({'type': 'minecraft:crafting_shaped', 'category': 'redstone', 'group': 'nether_wart_door', 'key': {'#': {'item': 'minecraft:nether_wart_block'}}, 'pattern': ['##', '##', '##'], 'result': {'count': 3, 'id': 'pastbound:nether_wart_door'}}, indent=2) + '\n', encoding='utf-8')
recipes.joinpath('nether_wart_trapdoor.json').write_text(json.dumps({'type': 'minecraft:crafting_shaped', 'category': 'redstone', 'group': 'nether_wart_trapdoor', 'key': {'#': {'item': 'minecraft:nether_wart_block'}}, 'pattern': ['###', '###'], 'result': {'count': 2, 'id': 'pastbound:nether_wart_trapdoor'}}, indent=2) + '\n', encoding='utf-8')
print('NETHER_WART_ASSETS_OK')
