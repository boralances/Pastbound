import json
from pathlib import Path
from PIL import Image, ImageDraw

kok = Path(__file__).resolve().parents[1]
varlik = kok / "src/main/resources/assets/pastbound"
item_dizini = varlik / "textures/item/relics"
slot_dizini = varlik / "textures/slot"
blok_dizini = varlik / "textures/block"
gui_dizini = varlik / "textures/gui"
mob_effect_dizini = varlik / "textures/mob_effect"
model_dizini = varlik / "models/item"
item_tanim_dizini = varlik / "items"
for dizin in (item_dizini, slot_dizini, blok_dizini, gui_dizini, mob_effect_dizini, model_dizini, item_tanim_dizini):
    dizin.mkdir(parents=True, exist_ok=True)


def item_tanimi_yaz(kimlik, model):
    tanim = {"model": {"type": "minecraft:model", "model": model}}
    (item_tanim_dizini / f"{kimlik}.json").write_text(json.dumps(tanim, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")


renkler = {
    "rossetta_tasi": (231, 214, 164),
    "gilgamesh_tableti": (180, 122, 77),
    "anubis_ankhi": (211, 178, 74),
    "minos_labirent_muhru": (118, 149, 166),
    "roma_aureusu": (224, 167, 46),
    "viking_gunes_pusulasi": (156, 200, 212),
    "samuray_kabzasi": (179, 53, 53),
    "maya_gunes_carki": (214, 107, 50),
    "inka_quipusu": (167, 94, 62),
    "harappa_muhru": (109, 142, 114),
    "song_porseleni": (117, 165, 212),
    "benin_bronzu": (182, 91, 45),
    "aztek_gunes_tasi": (198, 69, 46),
    "abbasid_murekkebi": (49, 45, 73),
    "ronesans_astrolabi": (217, 184, 115),
    "antikithera_duzenegi": (92, 110, 115),
    "catalhoyuk_boncugu": (192, 107, 86),
    "bizans_mozaigi": (78, 121, 166),
    "timbuktu_kalemi": (139, 90, 60),
    "apollo17_armasi": (201, 217, 231),
    "ilhanli_madalyonu": (176, 141, 87),
    "polinezya_yildiz_haritasi": (65, 107, 131),
    "mali_tuz_muhru": (231, 225, 199),
    "iskandinav_runetasi": (121, 128, 125)
}


def karistir(renk, miktar):
    return tuple(max(0, min(255, deger + miktar)) for deger in renk)


def kare_cizimi(renk, sira):
    koyu = karistir(renk, -65)
    acik = karistir(renk, 50)
    orta = karistir(renk, -12)
    goruntu = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    ciz = ImageDraw.Draw(goruntu)
    ciz.rectangle((3, 2, 12, 13), fill=koyu + (255,))
    ciz.rectangle((4, 2, 11, 12), fill=orta + (255,))
    ciz.rectangle((5, 3, 10, 11), fill=renk + (255,))
    ciz.point((5, 3), fill=acik + (255,))
    ciz.point((6, 3), fill=acik + (255,))
    ciz.point((10, 4), fill=koyu + (255,))
    ciz.point((5, 10), fill=karistir(renk, -35) + (255,))
    ciz.point((10, 9), fill=acik + (255,))
    desenler = [
        [(7, 5), (8, 6), (9, 5), (8, 7)],
        [(6, 5), (9, 5), (6, 8), (9, 8), (7, 10)],
        [(8, 4), (7, 6), (8, 8), (9, 10)],
        [(6, 6), (7, 7), (8, 6), (9, 7), (8, 9)],
        [(7, 5), (8, 5), (9, 6), (7, 8), (8, 9)],
        [(6, 5), (8, 6), (10, 5), (8, 8), (6, 9)],
    ]
    desen = desenler[sira % len(desenler)]
    for x, y in desen:
        ciz.point((x, y), fill=acik + (255,))
    ciz.point((4 + sira % 8, 13), fill=karistir(renk, -28) + (255,))
    return goruntu


def duz_kare(renk, parilti=False):
    koyu = karistir(renk, -62)
    orta = karistir(renk, -18)
    acik = karistir(renk, 48)
    goruntu = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    ciz = ImageDraw.Draw(goruntu)
    ciz.rectangle((0, 0, 15, 15), fill=koyu + (255,))
    for y in range(2, 14):
        for x in range(2, 14):
            sec = orta if (x + y) % 5 else acik
            ciz.point((x, y), fill=sec + (255,))
    if parilti:
        ciz.rectangle((6, 1, 9, 14), fill=acik + (150,))
        ciz.point((7, 7), fill=(255, 244, 180, 255))
        ciz.point((8, 8), fill=(255, 244, 180, 255))
    return goruntu


def echo_shard():
    goruntu = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    ciz = ImageDraw.Draw(goruntu)
    ciz.polygon([(7, 1), (12, 6), (10, 14), (6, 11), (4, 5)], fill=(39, 69, 83, 255))
    ciz.polygon([(7, 2), (10, 6), (8, 12), (6, 10)], fill=(86, 194, 193, 255))
    ciz.line([(7, 2), (8, 12)], fill=(202, 246, 225, 255), width=1)
    ciz.point((5, 6), fill=(133, 222, 210, 255))
    ciz.point((10, 7), fill=(45, 108, 125, 255))
    return goruntu


def memory_lens():
    goruntu = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    ciz = ImageDraw.Draw(goruntu)
    ciz.rectangle((3, 3, 12, 12), fill=(55, 45, 59, 255))
    ciz.rectangle((4, 4, 11, 11), fill=(187, 140, 76, 255))
    ciz.rectangle((5, 5, 10, 10), fill=(65, 117, 135, 255))
    ciz.rectangle((6, 6, 9, 9), fill=(113, 210, 194, 255))
    ciz.point((6, 6), fill=(236, 241, 190, 255))
    ciz.line([(12, 12), (15, 15)], fill=(219, 182, 104, 255), width=2)
    return goruntu


def echo_archive():
    goruntu = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    ciz = ImageDraw.Draw(goruntu)
    ciz.rectangle((1, 2, 14, 14), fill=(47, 39, 48, 255))
    ciz.rectangle((2, 3, 13, 13), fill=(102, 73, 62, 255))
    ciz.rectangle((3, 4, 12, 12), fill=(145, 105, 71, 255))
    ciz.rectangle((4, 5, 11, 11), fill=(53, 82, 86, 255))
    ciz.rectangle((5, 6, 10, 10), fill=(69, 128, 124, 255))
    ciz.point((6, 6), fill=(198, 234, 180, 255))
    ciz.point((9, 9), fill=(198, 234, 180, 255))
    ciz.line([(5, 8), (10, 8)], fill=(135, 207, 188, 255), width=1)
    return goruntu


def relic_slot():
    goruntu = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    ciz = ImageDraw.Draw(goruntu)
    ciz.rectangle((1, 1, 14, 14), fill=(34, 30, 40, 230))
    ciz.rectangle((2, 2, 13, 13), outline=(185, 138, 78, 255), width=1)
    ciz.rectangle((5, 5, 10, 10), outline=(103, 172, 160, 180), width=1)
    ciz.point((7, 7), fill=(226, 211, 153, 220))
    ciz.point((8, 8), fill=(226, 211, 153, 220))
    return goruntu


def journal_texture():
    goruntu = Image.new("RGBA", (256, 256), (29, 25, 31, 255))
    ciz = ImageDraw.Draw(goruntu)
    for y in range(256):
        ton = int(25 + y * 0.06)
        ciz.line((0, y, 255, y), fill=(ton + 12, ton + 8, ton + 18, 255))
    for i in range(22):
        x = (i * 71) % 256
        y = (i * 113) % 256
        ciz.rectangle((x, y, min(255, x + 18), min(255, y + 1)), fill=(181, 132, 79, 50))
    ciz.rectangle((7, 7, 248, 248), outline=(185, 138, 78, 180), width=2)
    ciz.rectangle((12, 12, 243, 243), outline=(83, 137, 134, 90), width=1)
    return goruntu


def history_modal():
    goruntu = Image.new("RGBA", (256, 192), (25, 24, 32, 255))
    ciz = ImageDraw.Draw(goruntu)
    for y in range(192):
        ton = int(26 + y * 0.12)
        ciz.line((0, y, 255, y), fill=(ton + 12, ton + 8, ton + 16, 255))
    ciz.rectangle((5, 5, 250, 186), outline=(196, 153, 87, 255), width=2)
    ciz.rectangle((10, 10, 245, 181), outline=(79, 133, 128, 180), width=1)
    ciz.rectangle((18, 32, 238, 34), fill=(177, 128, 75, 160))
    ciz.rectangle((18, 158, 238, 160), fill=(177, 128, 75, 160))
    ciz.polygon([(128, 52), (151, 76), (142, 109), (114, 109), (105, 76)], fill=(75, 137, 132, 210))
    ciz.line([(128, 52), (128, 109)], fill=(238, 208, 135, 255), width=2)
    ciz.line([(105, 76), (151, 76)], fill=(238, 208, 135, 255), width=2)
    ciz.point((128, 76), fill=(255, 242, 185, 255))
    return goruntu


def zaman_makinesi():
    goruntu = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    ciz = ImageDraw.Draw(goruntu)
    ciz.rectangle((2, 3, 13, 12), fill=(47, 38, 46, 255))
    ciz.rectangle((4, 2, 11, 13), fill=(137, 91, 60, 255))
    ciz.rectangle((5, 4, 10, 11), fill=(65, 116, 122, 255))
    ciz.ellipse((5, 5, 10, 10), outline=(226, 185, 92, 255), width=1)
    ciz.line([(8, 6), (8, 9), (10, 10)], fill=(242, 218, 153, 255), width=1)
    ciz.point((6, 5), fill=(181, 237, 205, 255))
    ciz.point((11, 11), fill=(181, 237, 205, 255))
    return goruntu


def firin_cubugu():
    goruntu = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    ciz = ImageDraw.Draw(goruntu)
    ciz.line([(3, 13), (12, 3)], fill=(75, 45, 34, 255), width=3)
    ciz.point((3, 13), fill=(194, 137, 77, 255))
    ciz.rectangle((8, 2, 13, 7), fill=(42, 36, 40, 255))
    ciz.rectangle((9, 3, 12, 6), fill=(104, 67, 55, 255))
    ciz.rectangle((10, 4, 11, 5), fill=(237, 117, 48, 255))
    ciz.point((12, 3), fill=(214, 184, 118, 255))
    return goruntu


def tarih_yankisi_efekti():
    goruntu = Image.new("RGBA", (18, 18), (0, 0, 0, 0))
    ciz = ImageDraw.Draw(goruntu)
    ciz.ellipse((2, 2, 15, 15), fill=(34, 67, 79, 255), outline=(112, 218, 192, 255), width=1)
    ciz.polygon([(9, 3), (12, 7), (11, 9), (15, 10), (11, 12), (10, 15), (7, 12), (4, 13), (6, 9), (3, 7), (7, 7)], fill=(99, 198, 179, 255))
    ciz.point((8, 6), fill=(237, 244, 186, 255))
    ciz.point((10, 8), fill=(237, 244, 186, 255))
    ciz.point((8, 10), fill=(237, 244, 186, 255))
    return goruntu


def zaman_gui():
    goruntu = Image.new("RGBA", (256, 192), (22, 28, 35, 255))
    ciz = ImageDraw.Draw(goruntu)
    for y in range(192):
        ton = int(25 + y * 0.14)
        ciz.line((0, y, 255, y), fill=(ton + 8, ton + 11, ton + 16, 255))
    ciz.rectangle((5, 5, 250, 186), outline=(209, 160, 85, 255), width=2)
    ciz.rectangle((11, 11, 244, 180), outline=(86, 147, 143, 180), width=1)
    ciz.ellipse((103, 47, 153, 97), outline=(199, 145, 75, 220), width=3)
    ciz.ellipse((111, 55, 145, 89), outline=(93, 179, 170, 240), width=2)
    ciz.line([(128, 35), (128, 109)], fill=(228, 196, 120, 220), width=2)
    ciz.line([(92, 72), (164, 72)], fill=(228, 196, 120, 220), width=2)
    ciz.polygon([(128, 25), (134, 39), (122, 39)], fill=(226, 191, 112, 255))
    ciz.rectangle((20, 127, 236, 129), fill=(179, 130, 74, 170))
    return goruntu


def tarih_esyasi(renk, sira):
    koyu = karistir(renk, -70)
    acik = karistir(renk, 55)
    goruntu = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    ciz = ImageDraw.Draw(goruntu)
    if sira == 0:
        ciz.rectangle((3, 3, 12, 12), fill=koyu + (255,))
        ciz.rectangle((4, 4, 11, 11), fill=renk + (255,))
        ciz.line([(6, 6), (10, 6), (6, 8), (10, 8), (6, 10), (10, 10)], fill=acik + (255,))
    elif sira == 1:
        ciz.rectangle((4, 2, 11, 13), fill=koyu + (255,))
        ciz.rectangle((5, 3, 10, 12), fill=renk + (255,))
        ciz.point((7, 5), fill=acik + (255,))
        ciz.point((8, 7), fill=acik + (255,))
        ciz.point((7, 9), fill=acik + (255,))
    elif sira == 2:
        ciz.ellipse((3, 3, 12, 12), fill=koyu + (255,))
        ciz.ellipse((4, 4, 11, 11), outline=renk + (255,), width=2)
        ciz.line([(8, 5), (8, 8), (10, 10)], fill=acik + (255,), width=1)
    else:
        ciz.polygon([(8, 2), (13, 7), (8, 14), (3, 7)], fill=koyu + (255,))
        ciz.polygon([(8, 4), (11, 7), (8, 12), (5, 7)], fill=renk + (255,))
        ciz.point((8, 7), fill=acik + (255,))
    return goruntu

for sira, (kimlik, renk) in enumerate(renkler.items()):
    kare_cizimi(renk, sira).save(item_dizini / f"{kimlik}.png")
    model = {"parent": "minecraft:item/generated", "textures": {"layer0": f"pastbound:item/relics/{kimlik}"}}
    (model_dizini / f"{kimlik}.json").write_text(json.dumps(model, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
    item_tanimi_yaz(kimlik, f"pastbound:item/{kimlik}")

echo_shard().save(varlik / "textures/item/echo_shard.png")
memory_lens().save(varlik / "textures/item/memory_lens.png")
item_tanimi_yaz("echo_shard", "pastbound:item/echo_shard")
item_tanimi_yaz("memory_lens", "pastbound:item/memory_lens")
echo_archive().save(varlik / "textures/block/echo_archive.png")
duz_kare((91, 72, 75)).save(varlik / "textures/block/resonance_pillar.png")
duz_kare((72, 119, 111), True).save(varlik / "textures/block/resonance_pillar_charged.png")
relic_slot().save(slot_dizini / "empty_relic_slot.png")
journal_texture().save(gui_dizini / "relic_journal.png")
history_modal().save(gui_dizini / "history_modal.png")
zaman_makinesi().save(varlik / "textures/item/zaman_makinesi.png")
firin_cubugu().save(varlik / "textures/item/firin_cubugu.png")
zaman_gui().save(gui_dizini / "time_machine.png")
tarih_yankisi_efekti().save(mob_effect_dizini / "tarih_yankisi.png")
yeni_esyalar = {
    "chronicle_scrap": ((181, 148, 91), 0),
    "history_ink": ((62, 66, 104), 1),
    "time_stone": ((76, 156, 151), 2),
    "echo_seal": ((201, 142, 64), 3)
}
for kimlik, (renk, sira) in yeni_esyalar.items():
    tarih_esyasi(renk, sira).save(varlik / "textures/item" / f"{kimlik}.png")
    model = {"parent": "minecraft:item/generated", "textures": {"layer0": f"pastbound:item/{kimlik}"}}
    (model_dizini / f"{kimlik}.json").write_text(json.dumps(model, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
    item_tanimi_yaz(kimlik, f"pastbound:item/{kimlik}")

for kimlik, kok_model in (("zaman_makinesi", "minecraft:item/generated"), ("firin_cubugu", "minecraft:item/handheld")):
    model = {"parent": kok_model, "textures": {"layer0": f"pastbound:item/{kimlik}"}}
    (model_dizini / f"{kimlik}.json").write_text(json.dumps(model, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
    item_tanimi_yaz(kimlik, f"pastbound:item/{kimlik}")

for kimlik in ("echo_archive", "resonance_pillar"):
    item_tanimi_yaz(kimlik, f"pastbound:item/{kimlik}")
