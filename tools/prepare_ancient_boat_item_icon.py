from pathlib import Path
from PIL import Image

source = Path('/home/ubuntu/Pastbound/src/main/resources/assets/pastbound/textures/entity/ancient_boat.png')
target = Path('/home/ubuntu/Pastbound/src/main/resources/assets/pastbound/textures/item/ancient_boat.png')
with Image.open(source) as image:
    image = image.convert('RGBA').crop((0, 0, 128, 64)).resize((16, 16), Image.Resampling.NEAREST)
    image.save(target)
print('ANCIENT_BOAT_ITEM_ICON_OK', target, image.size)
