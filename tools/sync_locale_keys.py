from pathlib import Path
import json

root = Path(__file__).resolve().parents[1]
lang = root / "src/main/resources/assets/pastbound/lang"
reference = json.loads((lang / "en_us.json").read_text(encoding="utf-8"))
for path in lang.glob("*.json"):
    if path.name == "en_us.json":
        continue
    data = json.loads(path.read_text(encoding="utf-8"))
    for key, value in reference.items():
        data.setdefault(key, value)
    path.write_text(json.dumps(data, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
print(f"synced_locales={len(list(lang.glob('*.json')))} keys={len(reference)}")
