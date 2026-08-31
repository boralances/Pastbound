from pathlib import Path
import json

root = Path(__file__).resolve().parents[1] / "src/main/resources/assets/pastbound/lang"
for path in root.glob("*.json"):
    data = json.loads(path.read_text(encoding="utf-8"))
    data.setdefault("container.pastbound.resonance_pillar", "Resonance Pillar")
    path.write_text(json.dumps(data, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
print(f"PILLAR_CONTAINER_LOCALE_OK={len(list(root.glob('*.json')))}")
