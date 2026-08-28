from pathlib import Path
import json

root = Path(__file__).resolve().parents[1]
for path in (root / "src/main/resources/assets/pastbound/lang").glob("*.json"):
    data = json.loads(path.read_text(encoding="utf-8"))
    if path.name == "tr_tr.json":
        data["message.pastbound.mission.steel_start"] = "Atölye görevi: altı çelik damarını kaz, ham çeliği fırında külçeye erit, çelik levha üret ve tarihî ocağı onar."
        data["message.pastbound.mission.steel_mined"] = "Altı çelik damarı açıldı. Ham çeliği bu tarihî atölyedeki fırında erit."
        data["screen.pastbound.scene.task_steel"] = "Atölye görevi: altı çelik damarını kaz, külçe erit, levha üret ve tarihî ocağı onar."
    else:
        data["message.pastbound.mission.steel_start"] = "Workshop mission: mine six steel veins, smelt raw steel into an ingot, craft a steel plate, then repair the historical forge."
        data["message.pastbound.mission.steel_mined"] = "The six steel veins are open. Smelt the raw steel in a furnace inside this historical workshop."
        data["screen.pastbound.scene.task_steel"] = "Workshop task: mine six steel veins, smelt an ingot, craft a plate, and repair the historical forge."
    path.write_text(json.dumps(data, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
print("steel_task_updated")
