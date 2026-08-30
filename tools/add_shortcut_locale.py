from pathlib import Path
import json

root = Path(__file__).resolve().parents[1]
for path in (root / "src/main/resources/assets/pastbound/lang").glob("*.json"):
    data = json.loads(path.read_text(encoding="utf-8"))
    tr = path.name == "tr_tr.json"
    data["key.pastbound.crafting_table_stick"] = "Open Crafting Table on a Stick" if not tr else "Crafting Table on a Stick aç"
    data["key.pastbound.furnace_stick"] = "Open Furnace on a Stick" if not tr else "Furnace on a Stick aç"
    data["message.pastbound.portable.missing"] = "You need the workstation item in your inventory." if not tr else "Workstation itemi envanterinde olmalı."
    for i in range(1, 9):
        data[f"key.pastbound.relic_slot_{i}"] = f"Activate Relic {i} with NumPad {i}" if not tr else f"Relic {i} NumPad {i} ile etkinleştir"
    path.write_text(json.dumps(data, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
print("shortcut_locale_added")
