from pathlib import Path
import json

root = Path(__file__).resolve().parents[1]
for path in (root / "src/main/resources/assets/pastbound/lang").glob("*.json"):
    data = json.loads(path.read_text(encoding="utf-8"))
    if path.name == "tr_tr.json":
        data["message.pastbound.period.unique_progress"] = "Dönem özel kanıtı %s/%s tamamlandı. Sahadaki diğer kanıtı bulmaya devam et."
    else:
        data["message.pastbound.period.unique_progress"] = "Period-specific evidence %s/%s complete. Keep searching for the next field clue."
    path.write_text(json.dumps(data, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
print("unique_progress_locale_added")
