from pathlib import Path
from PIL import Image, ImageDraw

ROOT = Path(__file__).resolve().parents[1] / "src/main/resources/assets/pastbound/textures"


def save_texture(path: str, draw_fn, size=(16, 16)) -> None:
    image = Image.new("RGBA", size, (0, 0, 0, 0))
    draw_fn(ImageDraw.Draw(image), size)
    image.save(ROOT / path)


def block_texture(base, accent, highlight, glyph=None):
    def draw(canvas, size):
        width, height = size
        canvas.rectangle((0, 0, width - 1, height - 1), fill=base)
        for y in range(0, height, 4):
            canvas.line((0, y, width - 1, y), fill=accent)
        for x in range(2, width, 5):
            canvas.line((x, 0, x, height - 1), fill=highlight)
        canvas.point((2, 2), fill=highlight)
        canvas.point((width - 3, height - 4), fill=accent)
        if glyph:
            glyph(canvas, size)
    return draw


def hieroglyph(canvas, size):
    canvas.line((4, 3, 4, 12), fill=(104, 65, 39, 255), width=1)
    canvas.line((4, 5, 10, 5), fill=(104, 65, 39, 255), width=1)
    canvas.line((8, 5, 8, 9), fill=(104, 65, 39, 255), width=1)
    canvas.line((8, 9, 12, 9), fill=(104, 65, 39, 255), width=1)
    canvas.point((12, 9), fill=(255, 214, 91, 255))


def sphinx(canvas, size):
    canvas.rectangle((3, 9, 12, 12), fill=(113, 68, 37, 255))
    canvas.rectangle((5, 6, 10, 9), fill=(183, 122, 59, 255))
    canvas.rectangle((6, 4, 9, 6), fill=(215, 157, 74, 255))
    canvas.point((7, 5), fill=(255, 215, 92, 255))
    canvas.point((9, 5), fill=(255, 215, 92, 255))


def marble(canvas, size):
    canvas.line((1, 12, 6, 8), fill=(129, 139, 157, 255), width=1)
    canvas.line((6, 8, 10, 9), fill=(129, 139, 157, 255), width=1)
    canvas.line((10, 9, 14, 3), fill=(129, 139, 157, 255), width=1)
    canvas.line((3, 2, 5, 5), fill=(225, 230, 235, 255), width=1)


def column(canvas, size):
    canvas.rectangle((5, 1, 10, 14), fill=(225, 226, 218, 255))
    canvas.rectangle((3, 2, 12, 4), fill=(190, 194, 188, 255))
    canvas.rectangle((4, 12, 11, 14), fill=(190, 194, 188, 255))
    canvas.line((6, 5, 6, 11), fill=(161, 168, 164, 255), width=1)
    canvas.line((9, 5, 9, 11), fill=(244, 242, 229, 255), width=1)


def altar(canvas, size):
    canvas.rectangle((3, 10, 12, 13), fill=(177, 177, 166, 255))
    canvas.rectangle((5, 6, 10, 10), fill=(209, 202, 171, 255))
    canvas.rectangle((6, 3, 9, 6), fill=(82, 128, 75, 255))
    canvas.rectangle((5, 4, 10, 5), fill=(108, 159, 88, 255))
    canvas.point((8, 2), fill=(192, 224, 113, 255))


def greek_boat_item(canvas, size):
    canvas.rectangle((2, 9, 13, 12), fill=(111, 54, 26, 255))
    canvas.polygon([(1, 9), (14, 9), (11, 13), (4, 13)], fill=(170, 90, 36, 255))
    canvas.rectangle((3, 7, 11, 9), fill=(215, 173, 77, 255))
    canvas.rectangle((6, 4, 8, 7), fill=(244, 227, 151, 255))
    canvas.line((7, 1, 7, 5), fill=(78, 47, 29, 255), width=1)
    canvas.line((7, 2, 11, 3), fill=(255, 237, 186, 255), width=1)
    canvas.point((3, 10), fill=(247, 205, 89, 255))
    canvas.point((12, 10), fill=(247, 205, 89, 255))


def greek_boat_entity(canvas, size):
    # Vanilla BoatModel uses a 128x64 atlas. This texture keeps every major atlas
    # region covered while giving the renderer a Greek bronze, cedar, and sail palette.
    canvas.rectangle((0, 0, 127, 63), fill=(0, 0, 0, 0))
    canvas.rectangle((0, 0, 127, 63), outline=(92, 42, 22, 255))
    canvas.rectangle((4, 4, 44, 15), fill=(128, 64, 27, 255))
    canvas.rectangle((4, 16, 44, 27), fill=(177, 96, 33, 255))
    canvas.rectangle((4, 28, 44, 39), fill=(218, 162, 61, 255))
    canvas.rectangle((48, 4, 88, 15), fill=(110, 49, 22, 255))
    canvas.rectangle((48, 16, 88, 27), fill=(153, 74, 24, 255))
    canvas.rectangle((48, 28, 88, 39), fill=(195, 121, 39, 255))
    canvas.rectangle((92, 4, 123, 18), fill=(224, 209, 155, 255))
    canvas.rectangle((92, 19, 123, 33), fill=(238, 224, 177, 255))
    canvas.rectangle((92, 34, 123, 48), fill=(204, 189, 132, 255))
    for x in range(0, 128, 8):
        canvas.line((x, 52, min(x + 5, 127), 52), fill=(216, 176, 67, 255), width=1)
    for x in range(6, 44, 8):
        canvas.line((x, 5, x, 38), fill=(86, 39, 22, 255), width=1)


save_texture("block/egyptian_hieroglyph.png", block_texture((201, 158, 94, 255), (171, 116, 63, 255), (235, 198, 124, 255), hieroglyph))
save_texture("block/egyptian_sphinx.png", block_texture((194, 137, 68, 255), (142, 91, 42, 255), (231, 179, 89, 255), sphinx))
save_texture("block/greek_marble.png", block_texture((205, 211, 216, 255), (153, 163, 174, 255), (234, 238, 239, 255), marble))
save_texture("block/greek_column.png", block_texture((215, 217, 209, 255), (154, 161, 157, 255), (240, 240, 227, 255), column))
save_texture("block/greek_olive_altar.png", block_texture((177, 177, 164, 255), (127, 130, 122, 255), (225, 219, 185, 255), altar))
save_texture("item/greek_boat.png", greek_boat_item)
save_texture("entity/greek_boat.png", greek_boat_entity, (128, 64))
