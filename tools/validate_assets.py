import json
from pathlib import Path
from PIL import Image

kok = Path(__file__).resolve().parents[1]
varlik = kok / "src/main/resources/assets/pastbound"
hatalar = []
for dosya in kok.rglob("*.json"):
    try:
        json.loads(dosya.read_text(encoding="utf-8"))
    except Exception as hata:
        hatalar.append(f"JSON {dosya}: {hata}")
for klasor in (varlik / "textures" / "block", varlik / "textures" / "item"):
    for dosya in klasor.rglob("*.png"):
        try:
            with Image.open(dosya) as goruntu:
                if goruntu.size != (16, 16):
                    hatalar.append(f"PNG {dosya}: {goruntu.size}")
        except Exception as hata:
            hatalar.append(f"PNG {dosya}: {hata}")
for dosya in (varlik / "models").rglob("*.json"):
    try:
        veri = json.loads(dosya.read_text(encoding="utf-8"))
    except Exception:
        continue
    metin = json.dumps(veri, ensure_ascii=False)
    for tur in ("block", "item"):
        baslangic = f"pastbound:{tur}/"
        kalan = metin
        while baslangic in kalan:
            kalan = kalan.split(baslangic, 1)[1]
            kimlik = kalan.split('"', 1)[0]
            texture = varlik / "textures" / tur / f"{kimlik}.png"
            if not texture.exists():
                hatalar.append(f"Texture {dosya}: {tur}/{kimlik}.png bulunamadı")
            kalan = kalan[1:] if kalan else ""
if hatalar:
    print("ASSET_ERRORS")
    print("\n".join(hatalar))
    raise SystemExit(1)
print("ASSET_OK")
print(f"json={len(list(kok.rglob('*.json')))}")
print(f"png={len(list((varlik / 'textures').rglob('*.png')))}")
