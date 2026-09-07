from PIL import Image

SIZE = 16
pixels = [
    [0x8A, 0x7D, 0xA1], [0x9A, 0x8EB, 0xAF], [0xA9, 0x9BBB, 0xB8], [0x7C, 0x718F, 0x94],
]

# End-stone-inspired base with subtle purple variation.
base = [
    (154, 143, 171), (161, 151, 177), (145, 135, 164), (169, 157, 181),
    (137, 129, 157), (157, 146, 173), (175, 163, 184), (148, 137, 166),
]
img = Image.new("RGBA", (SIZE, SIZE))
for y in range(SIZE):
    for x in range(SIZE):
        r, g, b = base[(x * 3 + y * 5 + (x ^ y)) % len(base)]
        img.putpixel((x, y), (r, g, b, 255))

# Crystalline Ervanium veins: dark violet edges, cyan cores, and a few rose highlights.
veins = {
    (2, 2): (48, 42, 74), (3, 2): (73, 62, 103), (4, 2): (76, 190, 193),
    (3, 3): (58, 117, 151), (4, 3): (82, 218, 211), (5, 3): (63, 145, 171),
    (9, 1): (55, 48, 82), (10, 1): (67, 149, 170), (10, 2): (91, 220, 207),
    (11, 2): (68, 132, 160), (12, 2): (58, 49, 82),
    (12, 6): (54, 45, 78), (13, 6): (77, 189, 190), (13, 7): (94, 222, 207),
    (12, 7): (62, 133, 159), (11, 8): (60, 51, 85),
    (2, 10): (54, 45, 78), (3, 10): (77, 183, 187), (4, 10): (91, 219, 207),
    (3, 11): (62, 137, 163), (4, 11): (61, 53, 87),
    (8, 12): (51, 43, 76), (9, 12): (74, 176, 184), (10, 12): (91, 218, 207),
    (9, 13): (60, 132, 160), (10, 13): (64, 54, 88),
    (6, 6): (59, 49, 84), (7, 6): (76, 164, 177), (7, 7): (96, 223, 210),
    (8, 7): (69, 135, 163), (7, 8): (59, 51, 85),
    (14, 14): (71, 56, 95), (15, 14): (173, 102, 151), (15, 15): (83, 62, 108),
}
for pos, color in veins.items():
    img.putpixel(pos, (*color, 255))

# A few one-pixel rose glints make the ore distinct in the End palette.
for pos in ((5, 3), (13, 7), (10, 12), (15, 14)):
    r, g, b, _ = img.getpixel(pos)
    img.putpixel(pos, (min(255, r + 65), min(255, g + 20), min(255, b + 48), 255))

img.save("src/main/resources/assets/pastbound/textures/block/ervanium_ore.png")
print("wrote 16x16 Ervanium ore texture")
