from pathlib import Path
import json

root = Path(__file__).resolve().parents[1]
for path in (root / "src/main/resources/assets/pastbound/lang").glob("*.json"):
    data = json.loads(path.read_text(encoding="utf-8"))
    turkce = path.name == "tr_tr.json"
    data["item.pastbound.crafting_table_cubugu"] = "Crafting Table on a Stick" if not turkce else "Çubukta Crafting Table"
    data["item.pastbound.gelistirilmis_firin_cubugu"] = "Upgraded Furnace on a Stick" if not turkce else "Geliştirilmiş Çubukta Fırın"
    data["screen.pastbound.relic.connect_points"] = "Connect the points in the correct order" if not turkce else "Noktaları doğru sırayla birleştir"
    data["screen.pastbound.relic.connect_progress"] = "Point sequence %s/%s" if not turkce else "Nokta sırası %s/%s"
    path.write_text(json.dumps(data, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
print("portable_locale_added")
