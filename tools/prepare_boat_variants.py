from pathlib import Path
from PIL import Image

root = Path('/home/ubuntu/Pastbound/src/main/resources/assets/pastbound/textures')
for name in ('egyptian_boat', 'viking_boat'):
    entity = root / 'entity' / f'{name}.png'
    item = root / 'item' / f'{name}.png'
    with Image.open(entity) as image:
        atlas = image.convert('RGBA').resize((128, 64), Image.Resampling.NEAREST)
        atlas.save(entity)
        item.parent.mkdir(parents=True, exist_ok=True)
        atlas.crop((0, 0, 128, 64)).resize((16, 16), Image.Resampling.NEAREST).save(item)
        print(name, 'entity', atlas.size, 'item', (16, 16))
