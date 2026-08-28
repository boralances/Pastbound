from pathlib import Path
import json

root = Path(__file__).resolve().parents[1]
for path in (root / "src/main/resources/assets/pastbound/lang").glob("*.json"):
    data = json.loads(path.read_text(encoding="utf-8"))
    if path.name == "tr_tr.json":
        data["message.pastbound.scene.station_used"] = "Saha durağı %s kullanıldı. Şimdi uzmanıyla konuş ve sonraki durağa ilerle."
    else:
        data["message.pastbound.scene.station_used"] = "Field station %s used. Speak with its specialist before moving to the next station."
    path.write_text(json.dumps(data, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
print("station_locale_added")
