from pathlib import Path
import json

root = Path(__file__).resolve().parents[1]
for path in (root / "src/main/resources/assets/pastbound/lang").glob("*.json"):
    data = json.loads(path.read_text(encoding="utf-8"))
    if path.name == "tr_tr.json":
        data["message.pastbound.scene.door_opened"] = "Tarihî kapı açıldı. Sahneye giriş yolu hazır."
        data["message.pastbound.scene.door_closed"] = "Tarihî kapı kapandı. Anı korunuyor."
    else:
        data["message.pastbound.scene.door_opened"] = "The historical door opens. The route into the scene is clear."
        data["message.pastbound.scene.door_closed"] = "The historical door closes. The memory is protected."
    path.write_text(json.dumps(data, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
print("door_locale_added")
