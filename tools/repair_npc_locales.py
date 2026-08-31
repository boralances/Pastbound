from pathlib import Path
import json

root = Path(__file__).resolve().parents[1] / 'src/main/resources/assets/pastbound/lang'
keys = {
    'entity.pastbound.village.archivist': 'Archivist',
    'entity.pastbound.power.engineer': 'Power Engineer',
    'entity.pastbound.scene.archaeologist': 'Archaeologist',
    'entity.pastbound.scene.craftsman': 'Craftsman',
    'entity.pastbound.scene.engineer': 'Engineer',
    'entity.pastbound.scene.miner': 'Miner',
    'entity.pastbound.scene.narrator': 'Narrator',
    'entity.pastbound.scene.scribe': 'Scribe',
    'entity.pastbound.scene.witness': 'Witness',
    'message.pastbound.dialogue.too_far': 'Move closer to the historical speaker.',
    'message.pastbound.mod_menu.summary': 'Historical exploration, relics and living echoes.',
}
for path in root.glob('*.json'):
    data = json.loads(path.read_text(encoding='utf-8'))
    for key, value in keys.items():
        data.setdefault(key, value)
    path.write_text(json.dumps(data, ensure_ascii=False, indent=2) + '\n', encoding='utf-8')
print(f'NPC_LOCALE_OK={len(list(root.glob("*.json")))}')
