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
    return goruntu.resize((512, 384), Image.Resampling.LANCZOS)


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
def kronik_pusulasi():
    goruntu = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    ciz = ImageDraw.Draw(goruntu)
    ciz.ellipse((2, 2, 13, 13), fill=(39, 45, 53, 255), outline=(207, 164, 77, 255), width=1)
    ciz.ellipse((4, 4, 11, 11), fill=(44, 102, 116, 255), outline=(126, 210, 191, 255), width=1)
    ciz.polygon([(8, 3), (10, 8), (8, 13), (7, 8)], fill=(230, 192, 103, 255))
    ciz.polygon([(8, 3), (7, 8), (8, 13), (9, 8)], fill=(190, 67, 55, 255))
    ciz.point((8, 8), fill=(250, 239, 170, 255))
    ciz.point((3, 8), fill=(164, 230, 202, 255))
    ciz.point((12, 8), fill=(164, 230, 202, 255))
    ciz.point((8, 2), fill=(248, 226, 139, 255))
    return goruntu


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

kronik_pusulasi().save(varlik / "textures/item/chronicle_compass.png")
model = {"parent": "minecraft:item/generated", "textures": {"layer0": "pastbound:item/chronicle_compass"}}
(model_dizini / "chronicle_compass.json").write_text(json.dumps(model, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
item_tanimi_yaz("chronicle_compass", "pastbound:item/chronicle_compass")

for kimlik, kok_model in (("zaman_makinesi", "minecraft:item/generated"), ("firin_cubugu", "minecraft:item/handheld")):
    model = {"parent": kok_model, "textures": {"layer0": f"pastbound:item/{kimlik}"}}
    (model_dizini / f"{kimlik}.json").write_text(json.dumps(model, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
    item_tanimi_yaz(kimlik, f"pastbound:item/{kimlik}")

for kimlik in ("echo_archive", "resonance_pillar"):
    item_tanimi_yaz(kimlik, f"pastbound:item/{kimlik}")

blockstate_dizini = varlik / "blockstates"
blockstate_dizini.mkdir(parents=True, exist_ok=True)


def ahsap_doku(renk, vurgu, yatay=False, yaprak=False):
    goruntu = Image.new("RGBA", (16, 16), (0, 0, 0, 0) if yaprak else renk + (255,))
    ciz = ImageDraw.Draw(goruntu)
    koyu = karistir(renk, -45)
    orta = karistir(renk, -15)
    acik = karistir(renk, 42)
    if yaprak:
        for y in range(16):
            for x in range(16):
                if (x * 5 + y * 3) % 7 not in (0, 1):
                    ton = orta if (x + y) % 4 else acik
                    ciz.point((x, y), fill=ton + (235,))
        ciz.point((4, 3), fill=vurgu + (255,))
        ciz.point((11, 8), fill=acik + (255,))
        ciz.point((7, 13), fill=koyu + (230,))
        return goruntu
    for i in range(4):
        if yatay:
            y = 2 + i * 4
            ciz.line((0, y, 15, y), fill=koyu + (255,), width=1)
            ciz.line((0, y + 1, 15, y + 1), fill=acik + (120,), width=1)
        else:
            x = 2 + i * 4
            ciz.line((x, 0, x, 15), fill=koyu + (255,), width=1)
            ciz.line((x + 1, 0, x + 1, 15), fill=acik + (120,), width=1)
    ciz.point((3, 5), fill=vurgu + (255,))
    ciz.point((12, 11), fill=vurgu + (210,))
    return goruntu


def ahsap_ust_doku(renk, vurgu):
    goruntu = Image.new("RGBA", (16, 16), renk + (255,))
    ciz = ImageDraw.Draw(goruntu)
    koyu = karistir(renk, -48)
    acik = karistir(renk, 38)
    ciz.rectangle((1, 1, 14, 14), outline=koyu + (255,), width=1)
    for yaricap in (3, 5, 7):
        ciz.ellipse((8 - yaricap, 8 - yaricap, 8 + yaricap, 8 + yaricap), outline=koyu + (210,), width=1)
    ciz.point((8, 8), fill=vurgu + (255,))
    ciz.point((5, 5), fill=acik + (220,))
    return goruntu


def blok_modeli_yaz(kimlik, parent, dokular):
    model = {"parent": parent, "textures": dokular}
    (varlik / "models/block" / f"{kimlik}.json").write_text(json.dumps(model, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")


def blok_durumu_yaz(kimlik, veri):
    (blockstate_dizini / f"{kimlik}.json").write_text(json.dumps(veri, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")


def item_blok_modeli_yaz(kimlik):
    item_tanimi_yaz(kimlik, f"pastbound:block/{kimlik}")
    model = {"parent": f"pastbound:block/{kimlik}"}
    (model_dizini / f"{kimlik}.json").write_text(json.dumps(model, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")


def kapi_dokusu(renk, vurgu):
    goruntu = Image.new("RGBA", (16, 16), karistir(renk, 8) + (255,))
    ciz = ImageDraw.Draw(goruntu)
    koyu = karistir(renk, -48)
    acik = karistir(renk, 35)
    orta = karistir(renk, -8)
    ciz.rectangle((0, 0, 15, 15), outline=koyu + (255,), width=1)
    ciz.line((5, 1, 5, 14), fill=koyu + (255,), width=1)
    ciz.line((6, 1, 6, 14), fill=acik + (180,), width=1)
    ciz.line((10, 1, 10, 14), fill=koyu + (255,), width=1)
    ciz.line((11, 1, 11, 14), fill=acik + (180,), width=1)
    ciz.rectangle((2, 3, 13, 5), fill=orta + (255,))
    ciz.rectangle((2, 10, 13, 12), fill=orta + (255,))
    ciz.rectangle((3, 4, 12, 4), fill=acik + (200,))
    ciz.rectangle((3, 11, 12, 11), fill=acik + (200,))
    ciz.rectangle((1, 2, 2, 4), fill=vurgu + (255,))
    ciz.rectangle((13, 11, 14, 13), fill=vurgu + (255,))
    ciz.rectangle((12, 7, 14, 8), fill=koyu + (255,))
    ciz.point((13, 7), fill=acik + (255,))
    return goruntu


def kapak_dokusu(renk, vurgu):
    goruntu = Image.new("RGBA", (16, 16), karistir(renk, 3) + (255,))
    ciz = ImageDraw.Draw(goruntu)
    koyu = karistir(renk, -52)
    acik = karistir(renk, 30)
    ciz.rectangle((0, 0, 15, 15), outline=koyu + (255,), width=1)
    for y in (4, 8, 12):
        ciz.line((1, y, 14, y), fill=koyu + (255,), width=1)
        ciz.line((1, y + 1, 14, y + 1), fill=acik + (170,), width=1)
    ciz.rectangle((2, 2, 5, 3), fill=vurgu + (255,))
    ciz.rectangle((10, 10, 13, 11), fill=vurgu + (230,))
    ciz.rectangle((6, 6, 9, 9), outline=acik + (220,), width=1)
    ciz.point((7, 7), fill=vurgu + (255,))
    return goruntu


def ahsap_familiasi_uret(prefix, renk, vurgu):
    log = f"{prefix}_log"
    stripped_log = f"{prefix}_stripped_log"
    wood = f"{prefix}_wood"
    stripped_wood = f"{prefix}_stripped_wood"
    leaves = f"{prefix}_leaves"
    sapling = f"{prefix}_sapling"
    planks = f"{prefix}_planks"
    slab = f"{prefix}_slab"
    stairs = f"{prefix}_stairs"
    door = f"{prefix}_door"
    trapdoor = f"{prefix}_trapdoor"
    fence = f"{prefix}_fence"
    gate = f"{prefix}_fence_gate"
    plate = f"{prefix}_pressure_plate"
    button = f"{prefix}_button"
    ahsap_doku(renk, vurgu).save(blok_dizini / f"{log}.png")
    ahsap_ust_doku(renk, vurgu).save(blok_dizini / f"{log}_top.png")
    ahsap_doku(karistir(renk, 18), vurgu).save(blok_dizini / f"{stripped_log}.png")
    ahsap_ust_doku(karistir(renk, 18), vurgu).save(blok_dizini / f"{stripped_log}_top.png")
    ahsap_doku(renk, vurgu).save(blok_dizini / f"{wood}.png")
    ahsap_doku(karistir(renk, 18), vurgu).save(blok_dizini / f"{stripped_wood}.png")
    ahsap_doku(karistir(renk, -8), vurgu, yaprak=True).save(blok_dizini / f"{leaves}.png")
    fidan = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    ciz = ImageDraw.Draw(fidan)
    ciz.line((8, 14, 8, 6), fill=karistir(renk, -40) + (255,), width=1)
    ciz.line((8, 9, 4, 5), fill=vurgu + (255,), width=1)
    ciz.line((8, 8, 12, 3), fill=vurgu + (230,), width=1)
    ciz.rectangle((5, 4, 7, 7), fill=karistir(vurgu, 15) + (235,))
    ciz.rectangle((10, 3, 12, 6), fill=vurgu + (235,))
    fidan.save(blok_dizini / f"{sapling}.png")
    ahsap_doku(karistir(renk, 12), vurgu, yatay=True).save(blok_dizini / f"{planks}.png")
    ahsap_doku(karistir(renk, 12), vurgu, yatay=True).save(blok_dizini / f"{slab}.png")
    ahsap_doku(karistir(renk, 12), vurgu, yatay=True).save(blok_dizini / f"{stairs}.png")
    kapi_dokusu(renk, vurgu).save(blok_dizini / f"{door}.png")
    kapak_dokusu(renk, vurgu).save(blok_dizini / f"{trapdoor}.png")
    for kimlik in (fence, gate, plate, button):
        ahsap_doku(karistir(renk, 12), vurgu, yatay=True).save(blok_dizini / f"{kimlik}.png")
    blok_modeli_yaz(log, "minecraft:block/cube_column", {"end": f"pastbound:block/{log}_top", "side": f"pastbound:block/{log}"})
    blok_modeli_yaz(stripped_log, "minecraft:block/cube_column", {"end": f"pastbound:block/{stripped_log}_top", "side": f"pastbound:block/{stripped_log}"})
    for kimlik in (wood, stripped_wood, planks):
        blok_modeli_yaz(kimlik, "minecraft:block/cube_all", {"all": f"pastbound:block/{kimlik}"})
    blok_modeli_yaz(leaves, "minecraft:block/cube_all", {"all": f"pastbound:block/{leaves}"})
    blok_modeli_yaz(sapling, "minecraft:block/cross", {"cross": f"pastbound:block/{sapling}"})
    blok_modeli_yaz(slab, "minecraft:block/slab", {"bottom": f"pastbound:block/{planks}", "top": f"pastbound:block/{planks}", "side": f"pastbound:block/{planks}"})
    blok_modeli_yaz(f"{slab}_top", "minecraft:block/slab_top", {"bottom": f"pastbound:block/{planks}", "top": f"pastbound:block/{planks}", "side": f"pastbound:block/{planks}"})
    blok_modeli_yaz(f"{slab}_double", "minecraft:block/cube_all", {"all": f"pastbound:block/{planks}"})
    blok_modeli_yaz(stairs, "minecraft:block/stairs", {"side": f"pastbound:block/{planks}", "bottom": f"pastbound:block/{planks}", "top": f"pastbound:block/{planks}"})
    blok_modeli_yaz(f"{stairs}_inner", "minecraft:block/inner_stairs", {"side": f"pastbound:block/{planks}", "bottom": f"pastbound:block/{planks}", "top": f"pastbound:block/{planks}"})
    blok_modeli_yaz(f"{stairs}_outer", "minecraft:block/outer_stairs", {"side": f"pastbound:block/{planks}", "bottom": f"pastbound:block/{planks}", "top": f"pastbound:block/{planks}"})
    blok_modeli_yaz(f"{door}_bottom_left", "minecraft:block/door_bottom_left", {"bottom": f"pastbound:block/{door}", "top": f"pastbound:block/{door}"})
    blok_modeli_yaz(f"{door}_bottom_left_open", "minecraft:block/door_bottom_left_open", {"bottom": f"pastbound:block/{door}", "top": f"pastbound:block/{door}"})
    blok_modeli_yaz(f"{door}_bottom_right", "minecraft:block/door_bottom_right", {"bottom": f"pastbound:block/{door}", "top": f"pastbound:block/{door}"})
    blok_modeli_yaz(f"{door}_bottom_right_open", "minecraft:block/door_bottom_right_open", {"bottom": f"pastbound:block/{door}", "top": f"pastbound:block/{door}"})
    blok_modeli_yaz(f"{door}_top_left", "minecraft:block/door_top_left", {"bottom": f"pastbound:block/{door}", "top": f"pastbound:block/{door}"})
    blok_modeli_yaz(f"{door}_top_left_open", "minecraft:block/door_top_left_open", {"bottom": f"pastbound:block/{door}", "top": f"pastbound:block/{door}"})
    blok_modeli_yaz(f"{door}_top_right", "minecraft:block/door_top_right", {"bottom": f"pastbound:block/{door}", "top": f"pastbound:block/{door}"})
    blok_modeli_yaz(f"{door}_top_right_open", "minecraft:block/door_top_right_open", {"bottom": f"pastbound:block/{door}", "top": f"pastbound:block/{door}"})
    blok_modeli_yaz(trapdoor, "minecraft:block/template_trapdoor", {"texture": f"pastbound:block/{trapdoor}"})
    blok_modeli_yaz(f"{trapdoor}_open", "minecraft:block/template_orientable_trapdoor_open", {"texture": f"pastbound:block/{trapdoor}"})
    blok_modeli_yaz(f"{trapdoor}_top", "minecraft:block/template_trapdoor_top", {"texture": f"pastbound:block/{trapdoor}"})
    blok_modeli_yaz(fence, "minecraft:block/fence_post", {"texture": f"pastbound:block/{fence}"})
    blok_modeli_yaz(f"{fence}_side", "minecraft:block/fence_side", {"texture": f"pastbound:block/{fence}"})
    blok_modeli_yaz(gate, "minecraft:block/template_fence_gate", {"texture": f"pastbound:block/{gate}"})
    blok_modeli_yaz(f"{gate}_open", "minecraft:block/template_fence_gate_open", {"texture": f"pastbound:block/{gate}"})
    blok_modeli_yaz(f"{gate}_wall", "minecraft:block/template_fence_gate_wall", {"texture": f"pastbound:block/{gate}"})
    blok_modeli_yaz(f"{gate}_wall_open", "minecraft:block/template_fence_gate_wall_open", {"texture": f"pastbound:block/{gate}"})
    blok_modeli_yaz(plate, "minecraft:block/pressure_plate_up", {"texture": f"pastbound:block/{plate}"})
    blok_modeli_yaz(f"{plate}_down", "minecraft:block/pressure_plate_down", {"texture": f"pastbound:block/{plate}"})
    blok_modeli_yaz(button, "minecraft:block/button", {"texture": f"pastbound:block/{button}"})
    blok_modeli_yaz(f"{button}_pressed", "minecraft:block/button_pressed", {"texture": f"pastbound:block/{button}"})
    blok_durumu_yaz(log, {"variants": {"axis=x": {"model": f"pastbound:block/{log}", "x": 90}, "axis=y": {"model": f"pastbound:block/{log}"}, "axis=z": {"model": f"pastbound:block/{log}", "x": 90, "y": 90}}})
    blok_durumu_yaz(stripped_log, {"variants": {"axis=x": {"model": f"pastbound:block/{stripped_log}", "x": 90}, "axis=y": {"model": f"pastbound:block/{stripped_log}"}, "axis=z": {"model": f"pastbound:block/{stripped_log}", "x": 90, "y": 90}}})
    blok_durumu_yaz(wood, {"variants": {"axis=x": {"model": f"pastbound:block/{wood}", "x": 90}, "axis=y": {"model": f"pastbound:block/{wood}"}, "axis=z": {"model": f"pastbound:block/{wood}", "x": 90, "y": 90}}})
    blok_durumu_yaz(stripped_wood, {"variants": {"axis=x": {"model": f"pastbound:block/{stripped_wood}", "x": 90}, "axis=y": {"model": f"pastbound:block/{stripped_wood}"}, "axis=z": {"model": f"pastbound:block/{stripped_wood}", "x": 90, "y": 90}}})
    blok_durumu_yaz(leaves, {"variants": {"": {"model": f"pastbound:block/{leaves}"}}})
    blok_durumu_yaz(sapling, {"variants": {"": {"model": f"pastbound:block/{sapling}"}}})
    blok_durumu_yaz(planks, {"variants": {"": {"model": f"pastbound:block/{planks}"}}})
    blok_durumu_yaz(slab, {"variants": {"type=bottom": {"model": f"pastbound:block/{slab}"}, "type=top": {"model": f"pastbound:block/{slab}_top"}, "type=double": {"model": f"pastbound:block/{slab}_double"}}})
    stairs_variants = {}
    for facing, donus in (("east", 0), ("south", 90), ("west", 180), ("north", 270)):
        for half, y in (("bottom", 0), ("top", 180)):
            for shape in ("straight", "inner_left", "inner_right", "outer_left", "outer_right"):
                model = stairs if shape == "straight" else f"{stairs}_{'inner' if shape.startswith('inner') else 'outer'}"
                stairs_variants[f"facing={facing},half={half},shape={shape}"] = {"model": f"pastbound:block/{model}", "y": (donus + (90 if shape.endswith("right") else 270 if shape.endswith("left") else 0)) % 360, "x": y}
    blok_durumu_yaz(stairs, {"variants": stairs_variants})
    door_variants = {}
    for facing, y in (("east", 90), ("south", 0), ("west", 270), ("north", 180)):
        for half in ("lower", "upper"):
            for hinge in ("left", "right"):
                for open_state in ("false", "true"):
                    suffix = "_open" if open_state == "true" else ""
                    side = "left" if hinge == "left" else "right"
                    model = f"{door}_{'bottom' if half == 'lower' else 'top'}_{side}{suffix}"
                    door_variants[f"facing={facing},half={half},hinge={hinge},open={open_state}"] = {"model": f"pastbound:block/{model}", "y": y}
    blok_durumu_yaz(door, {"variants": door_variants})
    trapdoor_variants = {}
    for facing, y in (("east", 90), ("south", 0), ("west", 270), ("north", 180)):
        for half in ("bottom", "top"):
            for open_state in ("false", "true"):
                model = f"{trapdoor}_open" if open_state == "true" else (f"{trapdoor}_top" if half == "top" else trapdoor)
                trapdoor_variants[f"facing={facing},half={half},open={open_state}"] = {"model": f"pastbound:block/{model}", "y": y}
    blok_durumu_yaz(trapdoor, {"variants": trapdoor_variants})
    blok_durumu_yaz(fence, {"multipart": [{"apply": {"model": f"pastbound:block/{fence}"}}, {"when": {"north": "true"}, "apply": {"model": f"pastbound:block/{fence}_side", "uvlock": True}}, {"when": {"east": "true"}, "apply": {"model": f"pastbound:block/{fence}_side", "uvlock": True, "y": 90}}, {"when": {"south": "true"}, "apply": {"model": f"pastbound:block/{fence}_side", "uvlock": True, "y": 180}}, {"when": {"west": "true"}, "apply": {"model": f"pastbound:block/{fence}_side", "uvlock": True, "y": 270}}]})
    gate_variants = {}
    for facing, y in (("east", 90), ("south", 0), ("west", 270), ("north", 180)):
        for open_state in ("false", "true"):
            for in_wall in ("false", "true"):
                model = gate + ("_open" if open_state == "true" else "") + ("_wall" if in_wall == "true" else "")
                gate_variants[f"facing={facing},in_wall={in_wall},open={open_state},powered=false"] = {"model": f"pastbound:block/{model}", "y": y}
                gate_variants[f"facing={facing},in_wall={in_wall},open={open_state},powered=true"] = {"model": f"pastbound:block/{model}", "y": y}
    blok_durumu_yaz(gate, {"variants": gate_variants})
    blok_durumu_yaz(plate, {"variants": {"powered=false": {"model": f"pastbound:block/{plate}"}, "powered=true": {"model": f"pastbound:block/{plate}_down"}}})
    blok_durumu_yaz(button, {"variants": {"face=floor,facing=east,powered=false": {"model": f"pastbound:block/{button}", "y": 90}, "face=floor,facing=south,powered=false": {"model": f"pastbound:block/{button}"}, "face=floor,facing=west,powered=false": {"model": f"pastbound:block/{button}", "y": 270}, "face=floor,facing=north,powered=false": {"model": f"pastbound:block/{button}", "y": 180}, "face=floor,facing=east,powered=true": {"model": f"pastbound:block/{button}_pressed", "y": 90}, "face=floor,facing=south,powered=true": {"model": f"pastbound:block/{button}_pressed"}, "face=floor,facing=west,powered=true": {"model": f"pastbound:block/{button}_pressed", "y": 270}, "face=floor,facing=north,powered=true": {"model": f"pastbound:block/{button}_pressed", "y": 180}}})
    for kimlik in (log, stripped_log, wood, stripped_wood, leaves, sapling, planks, slab, stairs, door, trapdoor, fence, gate, plate, button):
        item_blok_modeli_yaz(kimlik)


ahsap_familiasi_uret("uruk_cedar", (132, 88, 54), (201, 166, 91))
ahsap_familiasi_uret("chinampa_cypress", (83, 111, 78), (86, 183, 157))


def tarif_semasini_duzelt(veri):
    tarif_tipi = veri.get("type")
    if tarif_tipi == "minecraft:crafting_shaped":
        for anahtar, malzeme in list(veri.get("key", {}).items()):
            if isinstance(malzeme, str):
                veri["key"][anahtar] = {"item": malzeme} if ":" in malzeme else {"tag": f"minecraft:{malzeme}"}
    if tarif_tipi in {"minecraft:crafting_shapeless", "minecraft:smelting", "minecraft:blasting"}:
        if isinstance(veri.get("ingredient"), str):
            malzeme = veri["ingredient"]
            veri["ingredient"] = {"item": malzeme} if ":" in malzeme else {"item": f"minecraft:{malzeme}"}
        if isinstance(veri.get("ingredients"), list):
            veri["ingredients"] = [
                ({"item": malzeme} if ":" in malzeme else {"item": f"minecraft:{malzeme}"})
                if isinstance(malzeme, str) else malzeme
                for malzeme in veri["ingredients"]
            ]
    return veri


def veri_dosyasi_yaz(kok_yolu, kimlik, veri):
    veri = tarif_semasini_duzelt(veri)
    yol = kok_yolu / f"{kimlik}.json"
    yol.parent.mkdir(parents=True, exist_ok=True)
    yol.write_text(json.dumps(veri, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")


def agac_verileri_uret(prefix):
    veri_koku = kok / "src/main/resources/data/pastbound"
    log = f"pastbound:{prefix}_log"
    stripped_log = f"pastbound:{prefix}_stripped_log"
    wood = f"pastbound:{prefix}_wood"
    stripped_wood = f"pastbound:{prefix}_stripped_wood"
    leaves = f"pastbound:{prefix}_leaves"
    sapling = f"pastbound:{prefix}_sapling"
    planks = f"pastbound:{prefix}_planks"
    slab = f"pastbound:{prefix}_slab"
    stairs = f"pastbound:{prefix}_stairs"
    door = f"pastbound:{prefix}_door"
    trapdoor = f"pastbound:{prefix}_trapdoor"
    fence = f"pastbound:{prefix}_fence"
    gate = f"pastbound:{prefix}_fence_gate"
    plate = f"pastbound:{prefix}_pressure_plate"
    button = f"pastbound:{prefix}_button"
    recipes = veri_koku / "recipes"
    loot = veri_koku / "loot_table/blocks"
    veri_dosyasi_yaz(recipes, f"{prefix}_planks", {"type": "minecraft:crafting_shaped", "category": "building", "group": "historical_wood_planks", "key": {"#": log}, "pattern": ["#"], "result": {"count": 4, "id": planks}})
    veri_dosyasi_yaz(recipes, f"{prefix}_stripped_planks", {"type": "minecraft:crafting_shaped", "category": "building", "group": "historical_wood_planks", "key": {"#": stripped_log}, "pattern": ["#"], "result": {"count": 4, "id": planks}})
    veri_dosyasi_yaz(recipes, f"{prefix}_slab", {"type": "minecraft:crafting_shaped", "category": "building", "group": "historical_wood_slab", "key": {"#": planks}, "pattern": ["###"], "result": {"count": 6, "id": slab}})
    veri_dosyasi_yaz(recipes, f"{prefix}_stairs", {"type": "minecraft:crafting_shaped", "category": "building", "group": "historical_wood_stairs", "key": {"#": planks}, "pattern": ["#  ", "## ", "###"], "result": {"count": 4, "id": stairs}})
    veri_dosyasi_yaz(recipes, f"{prefix}_door", {"type": "minecraft:crafting_shaped", "category": "redstone", "group": "historical_wood_door", "key": {"#": planks}, "pattern": ["##", "##", "##"], "result": {"count": 3, "id": door}})
    veri_dosyasi_yaz(recipes, f"{prefix}_trapdoor", {"type": "minecraft:crafting_shaped", "category": "redstone", "group": "historical_wood_trapdoor", "key": {"#": planks}, "pattern": ["###", "###"], "result": {"count": 2, "id": trapdoor}})
    veri_dosyasi_yaz(recipes, f"{prefix}_fence", {"type": "minecraft:crafting_shaped", "group": "historical_wood_fence", "key": {"#": "minecraft:stick", "W": planks}, "pattern": ["W#W", "W#W"], "result": {"count": 3, "id": fence}})
    veri_dosyasi_yaz(recipes, f"{prefix}_fence_gate", {"type": "minecraft:crafting_shaped", "category": "redstone", "group": "historical_wood_fence_gate", "key": {"#": "minecraft:stick", "W": planks}, "pattern": ["#W#", "#W#"], "result": {"id": gate}})
    veri_dosyasi_yaz(recipes, f"{prefix}_button", {"type": "minecraft:crafting_shapeless", "category": "redstone", "group": "historical_wood_button", "ingredients": [planks], "result": {"id": button}})
    veri_dosyasi_yaz(recipes, f"{prefix}_pressure_plate", {"type": "minecraft:crafting_shaped", "category": "redstone", "group": "historical_wood_pressure_plate", "key": {"#": planks}, "pattern": ["##"], "result": {"id": plate}})
    for kimlik in (f"{prefix}_log", f"{prefix}_stripped_log", f"{prefix}_wood", f"{prefix}_stripped_wood", f"{prefix}_leaves", f"{prefix}_sapling", f"{prefix}_planks", f"{prefix}_slab", f"{prefix}_stairs", f"{prefix}_door", f"{prefix}_trapdoor", f"{prefix}_fence", f"{prefix}_fence_gate", f"{prefix}_pressure_plate", f"{prefix}_button"):
        veri_dosyasi_yaz(loot, kimlik, {"type": "minecraft:block", "pools": [{"conditions": [{"condition": "minecraft:survives_explosion"}], "entries": [{"type": "minecraft:item", "name": f"pastbound:{kimlik}"}], "rolls": 1.0}]})


agac_verileri_uret("uruk_cedar")
agac_verileri_uret("chinampa_cypress")


def celik_blok_dokusu(renk, vurgu, benekli=False):
    goruntu = Image.new("RGBA", (16, 16), renk + (255,))
    ciz = ImageDraw.Draw(goruntu)
    koyu = karistir(renk, -48)
    derin = karistir(renk, -25)
    orta = karistir(renk, -8)
    acik = karistir(renk, 38)
    ciz.rectangle((0, 0, 15, 15), fill=derin + (255,), outline=koyu + (255,), width=1)
    for y in range(2, 15, 4):
        ciz.line((1, y, 14, y - 1), fill=orta + (255,), width=1)
        ciz.line((2, y + 1, 13, y), fill=acik + (90,), width=1)
    ciz.line((2, 2, 12, 13), fill=koyu + (170,), width=1)
    ciz.line((5, 1, 14, 10), fill=acik + (100,), width=1)
    for x, y in ((3, 5), (10, 4), (6, 11), (12, 12)):
        ciz.point((x, y), fill=acik + (255,))
    ciz.rectangle((4, 6, 5, 7), fill=vurgu + (255,))
    ciz.rectangle((10, 9, 11, 10), fill=vurgu + (255,))
    ciz.point((5, 6), fill=karistir(vurgu, 52) + (255,))
    ciz.point((11, 9), fill=karistir(vurgu, 52) + (255,))
    if benekli:
        for x, y in ((2, 12), (7, 3), (13, 5), (8, 8), (3, 2), (12, 14)):
            ciz.rectangle((x, y, min(15, x + 1), min(15, y + 1)), fill=vurgu + (255,))
            ciz.point((x, y), fill=karistir(vurgu, 58) + (255,))
    return goruntu


def celik_varliklari_uret():
    itemler = {
        "raw_steel": ((151, 119, 75), 0),
        "steel_ingot": ((119, 132, 139), 1),
        "steel_plate": ((91, 108, 119), 2)
    }
    for kimlik, (renk, sira) in itemler.items():
        tarih_esyasi(renk, sira).save(varlik / "textures/item" / f"{kimlik}.png")
        model = {"parent": "minecraft:item/generated", "textures": {"layer0": f"pastbound:item/{kimlik}"}}
        (model_dizini / f"{kimlik}.json").write_text(json.dumps(model, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
        item_tanimi_yaz(kimlik, f"pastbound:item/{kimlik}")
    bloklar = {
        "steel_ore": ((74, 83, 87), (151, 119, 75), True),
        "deepslate_steel_ore": ((53, 58, 63), (151, 119, 75), True),
        "steel_block": ((91, 108, 119), (176, 192, 195), False),
        "historical_forge": ((63, 68, 73), (194, 137, 60), False),
        "time_stone_ore": ((49, 68, 78), (76, 156, 151), True),
        "nether_time_stone_ore": ((82, 42, 35), (76, 156, 151), True),
        "chronicle_ore": ((58, 64, 79), (181, 148, 91), True),
        "ash_chronicle_ore": ((72, 48, 45), (181, 148, 91), True),
        "end_echo_ore": ((64, 58, 91), (119, 207, 190), True),
        "void_chronicle_ore": ((40, 34, 61), (181, 148, 91), True)
    }
    for kimlik, (renk, vurgu, benekli) in bloklar.items():
        celik_blok_dokusu(renk, vurgu, benekli).save(blok_dizini / f"{kimlik}.png")
        blok_modeli_yaz(kimlik, "minecraft:block/cube_all", {"all": f"pastbound:block/{kimlik}"})
        blok_durumu_yaz(kimlik, {"variants": {"": {"model": f"pastbound:block/{kimlik}"}}})
        item_blok_modeli_yaz(kimlik)
    veri_koku = kok / "src/main/resources/data/pastbound"
    recipes = veri_koku / "recipes"
    loot = veri_koku / "loot_table/blocks"
    veri_dosyasi_yaz(recipes, "steel_ingot_from_smelting", {"type": "minecraft:smelting", "category": "misc", "cookingtime": 200, "experience": 0.7, "ingredient": {"item": "pastbound:raw_steel"}, "result": {"id": "pastbound:steel_ingot"}})
    veri_dosyasi_yaz(recipes, "steel_ingot_from_blasting", {"type": "minecraft:blasting", "category": "misc", "cookingtime": 100, "experience": 0.7, "ingredient": {"item": "pastbound:raw_steel"}, "result": {"id": "pastbound:steel_ingot"}})
    veri_dosyasi_yaz(recipes, "steel_plate", {"type": "minecraft:crafting_shaped", "category": "misc", "group": "pastbound_steel", "key": {"#": {"item": "pastbound:steel_ingot"}}, "pattern": ["###"], "result": {"count": 2, "id": "pastbound:steel_plate"}})
    veri_dosyasi_yaz(recipes, "steel_block", {"type": "minecraft:crafting_shaped", "category": "building", "group": "pastbound_steel", "key": {"#": {"item": "pastbound:steel_ingot"}}, "pattern": ["###", "###", "###"], "result": {"id": "pastbound:steel_block"}})
    veri_dosyasi_yaz(recipes, "historical_forge", {"type": "minecraft:crafting_shaped", "category": "building", "group": "pastbound_historical_forge", "key": {"#": {"item": "pastbound:steel_plate"}, "F": {"item": "minecraft:blast_furnace"}, "I": {"item": "minecraft:iron_ingot"}}, "pattern": ["#F#", " I ", "###"], "result": {"id": "pastbound:historical_forge"}})
    veri_dosyasi_yaz(loot, "steel_ore", {"type": "minecraft:block", "pools": [{"conditions": [{"condition": "minecraft:survives_explosion"}], "entries": [{"type": "minecraft:item", "name": "pastbound:raw_steel", "functions": [{"function": "minecraft:set_count", "count": {"type": "minecraft:uniform", "min": 1.0, "max": 2.0}}, {"function": "minecraft:apply_bonus", "enchantment": "minecraft:fortune", "formula": "minecraft:ore_drops"}]}], "rolls": 1.0}]})
    veri_dosyasi_yaz(loot, "deepslate_steel_ore", {"type": "minecraft:block", "pools": [{"conditions": [{"condition": "minecraft:survives_explosion"}], "entries": [{"type": "minecraft:item", "name": "pastbound:raw_steel", "functions": [{"function": "minecraft:set_count", "count": {"type": "minecraft:uniform", "min": 1.0, "max": 2.0}}, {"function": "minecraft:apply_bonus", "enchantment": "minecraft:fortune", "formula": "minecraft:ore_drops"}]}], "rolls": 1.0}]})
    veri_dosyasi_yaz(loot, "steel_block", {"type": "minecraft:block", "pools": [{"conditions": [{"condition": "minecraft:survives_explosion"}], "entries": [{"type": "minecraft:item", "name": "pastbound:steel_block"}], "rolls": 1.0}]})
    veri_dosyasi_yaz(loot, "historical_forge", {"type": "minecraft:block", "pools": [{"conditions": [{"condition": "minecraft:survives_explosion"}], "entries": [{"type": "minecraft:item", "name": "pastbound:historical_forge"}], "rolls": 1.0}]})
    maden_dusumleri = {
        "time_stone_ore": "pastbound:time_stone",
        "nether_time_stone_ore": "pastbound:time_stone",
        "chronicle_ore": "pastbound:chronicle_scrap",
        "ash_chronicle_ore": "pastbound:chronicle_scrap",
        "end_echo_ore": "pastbound:echo_shard",
        "void_chronicle_ore": "pastbound:echo_seal"
    }
    for kimlik, dusum in maden_dusumleri.items():
        veri_dosyasi_yaz(loot, kimlik, {"type": "minecraft:block", "pools": [{"conditions": [{"condition": "minecraft:survives_explosion"}], "entries": [{"type": "minecraft:item", "name": dusum, "functions": [{"function": "minecraft:set_count", "count": {"type": "minecraft:uniform", "min": 1.0, "max": 2.0}}, {"function": "minecraft:apply_bonus", "enchantment": "minecraft:fortune", "formula": "minecraft:ore_drops"}]}], "rolls": 1.0}]})
    vanilla_etiket_koku = kok / "src/main/resources/data/minecraft/tags/block"
    maden_tag_degerleri = ["pastbound:steel_ore", "pastbound:deepslate_steel_ore", "pastbound:steel_block", "pastbound:historical_forge", "pastbound:time_stone_ore", "pastbound:nether_time_stone_ore", "pastbound:chronicle_ore", "pastbound:ash_chronicle_ore", "pastbound:end_echo_ore", "pastbound:void_chronicle_ore"]
    veri_dosyasi_yaz(vanilla_etiket_koku, "mineable/pickaxe", {"replace": False, "values": maden_tag_degerleri})
    veri_dosyasi_yaz(vanilla_etiket_koku, "needs_iron_tool", {"replace": False, "values": maden_tag_degerleri})


celik_varliklari_uret()
