from pathlib import Path

from PIL import Image


ROOT = Path(__file__).resolve().parents[1] / "src/main/resources/assets/pastbound/textures"
BLOCK_DIR = ROOT / "block"
ITEM_DIR = ROOT / "item"


def solid(color):
    return [[color for _ in range(16)] for _ in range(16)]


def put(grid, x, y, color):
    if 0 <= x < 16 and 0 <= y < 16:
        grid[y][x] = color


def rect(grid, x0, y0, x1, y1, color):
    for y in range(y0, y1 + 1):
        for x in range(x0, x1 + 1):
            put(grid, x, y, color)


def save_grid(grid, path, transparent=False):
    mode = "RGBA" if transparent else "RGB"
    image = Image.new(mode, (16, 16), (0, 0, 0, 0) if transparent else grid[0][0])
    for y, row in enumerate(grid):
        for x, value in enumerate(row):
            if transparent and value is None:
                image.putpixel((x, y), (0, 0, 0, 0))
            else:
                rgb = tuple(int(value[i : i + 2], 16) for i in (1, 3, 5))
                image.putpixel((x, y), (*rgb, 255) if transparent else rgb)
    path.parent.mkdir(parents=True, exist_ok=True)
    image.save(path)


def archive():
    grid = solid("#211B36")
    rect(grid, 1, 1, 14, 14, "#2E2450")
    rect(grid, 2, 2, 13, 13, "#3A2B61")
    rect(grid, 3, 3, 12, 12, "#4F397B")
    rect(grid, 4, 4, 11, 11, "#2B224B")
    for x, y in [(1, 1), (14, 1), (1, 14), (14, 14), (3, 3), (12, 3), (3, 12), (12, 12)]:
        put(grid, x, y, "#9D79C5")
    for x, y in [(7, 4), (8, 4), (6, 5), (9, 5), (5, 6), (10, 6), (4, 7), (11, 7),
                 (4, 8), (11, 8), (5, 9), (10, 9), (6, 10), (9, 10), (7, 11), (8, 11)]:
        put(grid, x, y, "#D8C2EF")
    rect(grid, 7, 6, 8, 9, "#C79BEA")
    put(grid, 7, 7, "#F4E8FF")
    put(grid, 8, 8, "#7A5BA8")
    for x, y in [(2, 6), (13, 9), (5, 2), (10, 13), (8, 2), (2, 10)]:
        put(grid, x, y, "#6C529A")
    save_grid(grid, BLOCK_DIR / "echo_archive.png")


def pillar(charged=False):
    grid = solid("#0B1F2A")
    rect(grid, 2, 0, 13, 15, "#123B49")
    rect(grid, 3, 0, 12, 15, "#1E6670")
    rect(grid, 5, 0, 10, 15, "#174C5A")
    rect(grid, 6, 0, 9, 15, "#236F76")
    for y in range(1, 15, 3):
        put(grid, 4, y, "#4A9A96")
        put(grid, 11, y + 1 if y + 1 < 16 else y, "#4A9A96")
    for x, y in [(7, 2), (8, 2), (6, 3), (9, 3), (5, 4), (10, 4), (6, 5), (9, 5),
                 (7, 6), (8, 6), (7, 7), (8, 7), (6, 8), (9, 8), (5, 9), (10, 9),
                 (6, 10), (9, 10), (7, 11), (8, 11), (7, 12), (8, 12)]:
        put(grid, x, y, "#42F5D0" if charged else "#78BDB4")
    if charged:
        for x, y in [(2, 2), (13, 5), (2, 12), (13, 9), (4, 0), (11, 15)]:
            put(grid, x, y, "#C5FFF3")
    save_grid(grid, BLOCK_DIR / ("resonance_pillar_charged.png" if charged else "resonance_pillar.png"))


def shard():
    grid = [[None for _ in range(16)] for _ in range(16)]
    outline = "#241B3A"
    shadow = "#6848A0"
    mid = "#9C72D0"
    light = "#E2C7FF"
    bright = "#FFF2FF"
    cells = {
        (7, 2): outline, (8, 2): outline, (6, 3): outline, (7, 3): shadow, (8, 3): mid, (9, 3): outline,
        (5, 4): outline, (6, 4): shadow, (7, 4): mid, (8, 4): light, (9, 4): mid, (10, 4): outline,
        (4, 5): outline, (5, 5): shadow, (6, 5): mid, (7, 5): light, (8, 5): bright, (9, 5): light, (10, 5): mid, (11, 5): outline,
        (4, 6): outline, (5, 6): shadow, (6, 6): mid, (7, 6): light, (8, 6): bright, (9, 6): mid, (10, 6): outline,
        (3, 7): outline, (4, 7): shadow, (5, 7): mid, (6, 7): light, (7, 7): bright, (8, 7): mid, (9, 7): outline,
        (3, 8): outline, (4, 8): shadow, (5, 8): mid, (6, 8): light, (7, 8): mid, (8, 8): outline,
        (4, 9): outline, (5, 9): shadow, (6, 9): mid, (7, 9): outline,
        (5, 10): outline, (6, 10): shadow, (7, 10): outline,
        (6, 11): outline,
    }
    for (x, y), color in cells.items():
        put(grid, x, y, color)
    save_grid(grid, ITEM_DIR / "echo_shard.png", transparent=True)


def lens():
    grid = [[None for _ in range(16)] for _ in range(16)]
    dark = "#1D1A2C"
    metal = "#A8B1C4"
    highlight = "#E6F4FF"
    glass = "#4CD6D4"
    glow = "#C5FFF3"
    for x, y in [(6, 2), (7, 2), (8, 2), (9, 2), (4, 3), (5, 3), (10, 3), (11, 3),
                 (3, 4), (12, 4), (2, 5), (13, 5), (2, 6), (13, 6), (1, 7), (14, 7),
                 (1, 8), (14, 8), (2, 9), (13, 9), (2, 10), (13, 10), (3, 11), (12, 11),
                 (4, 12), (5, 12), (10, 12), (11, 12), (6, 13), (7, 13), (8, 13), (9, 13)]:
        put(grid, x, y, dark)
    for x, y in [(6, 3), (7, 3), (8, 3), (9, 3), (4, 4), (5, 4), (10, 4), (11, 4),
                 (3, 5), (12, 5), (3, 6), (12, 6), (2, 7), (13, 7), (2, 8), (13, 8),
                 (3, 9), (12, 9), (3, 10), (12, 10), (4, 11), (5, 11), (10, 11), (11, 11),
                 (6, 12), (7, 12), (8, 12), (9, 12)]:
        put(grid, x, y, metal)
    rect(grid, 5, 5, 10, 10, glass)
    rect(grid, 6, 6, 9, 9, "#267E91")
    rect(grid, 7, 6, 8, 7, glow)
    put(grid, 6, 5, highlight)
    put(grid, 5, 6, highlight)
    put(grid, 9, 10, "#14546B")
    put(grid, 8, 8, "#E8FFFF")
    save_grid(grid, ITEM_DIR / "memory_lens.png", transparent=True)


if __name__ == "__main__":
    archive()
    pillar(charged=False)
    pillar(charged=True)
    shard()
    lens()
    print(f"Generated textures under {ROOT}")
