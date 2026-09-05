from pathlib import Path
from PIL import Image

path = Path('/home/ubuntu/Pastbound/src/main/resources/assets/pastbound/textures/entity/ancient_boat.png')
with Image.open(path) as image:
    image = image.convert('RGBA').resize((128, 64), Image.Resampling.NEAREST)
    image.save(path)
print('ANCIENT_BOAT_TEXTURE_OK', path, image.size)
