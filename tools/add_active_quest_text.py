from pathlib import Path
import json

root = Path(__file__).resolve().parents[1]
en = {
    "message.pastbound.scene.waypoint_need_talk": "You reached the field station. Speak with its specialist before moving on.",
    "message.pastbound.scene.waypoint_locked": "Reach this field station before you can question the specialist.",
    "message.pastbound.scene.waypoint_talk": "Field station %s recorded: the evidence is added to your expedition log.",
    "entity.pastbound.scene.archaeologist": "Field Archaeologist",
    "entity.pastbound.scene.miner": "Ore Surveyor",
    "entity.pastbound.scene.engineer": "Power Engineer"
}
tr = {
    "message.pastbound.scene.waypoint_need_talk": "Saha istasyonuna ulaştın. İlerlemeden önce uzmanıyla konuş.",
    "message.pastbound.scene.waypoint_locked": "Uzmanı sorgulamak için önce bu saha istasyonuna ulaşmalısın.",
    "message.pastbound.scene.waypoint_talk": "Saha istasyonu %s kaydedildi: kanıt keşif günlüğüne eklendi.",
    "entity.pastbound.scene.archaeologist": "Saha Arkeoloğu",
    "entity.pastbound.scene.miner": "Cevher Araştırmacısı",
    "entity.pastbound.scene.engineer": "Güç Mühendisi"
}
for path in (root / "src/main/resources/assets/pastbound/lang").glob("*.json"):
    data = json.loads(path.read_text(encoding="utf-8"))
    data.update(tr if path.name == "tr_tr.json" else en)
    path.write_text(json.dumps(data, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
print("active_quest_text_added")
