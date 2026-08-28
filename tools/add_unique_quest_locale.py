from pathlib import Path
import json

root = Path(__file__).resolve().parents[1]
for path in (root / "src/main/resources/assets/pastbound/lang").glob("*.json"):
    data = json.loads(path.read_text(encoding="utf-8"))
    if path.name == "tr_tr.json":
        data["message.pastbound.period.unique_complete"] = "%s döneminin özel saha görevi tamamlandı. Şimdi tüm tarih görev zincirini bitir."
    else:
        data["message.pastbound.period.unique_complete"] = "The unique field objective for %s is complete. Finish the full historical quest chain."
    path.write_text(json.dumps(data, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
print("unique_quest_locale_added")
