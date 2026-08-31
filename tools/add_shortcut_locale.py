from pathlib import Path
import json

root = Path(__file__).resolve().parents[1]
for path in (root / "src/main/resources/assets/pastbound/lang").glob("*.json"):
    data = json.loads(path.read_text(encoding="utf-8"))
    tr = path.name == "tr_tr.json"
    data["key.pastbound.crafting_table_stick"] = "Open Crafting Table on a Stick" if not tr else "Crafting Table on a Stick aç"
    data["key.pastbound.furnace_stick"] = "Open Furnace on a Stick" if not tr else "Furnace on a Stick aç"
    data["message.pastbound.portable.missing"] = "You need the workstation item in your inventory." if not tr else "Workstation itemi envanterinde olmalı."
    data["message.pastbound.world.structure_missing"] = "No generated village and mineshaft were found nearby. Explore farther and try again." if not tr else "Yakında oluşturulmuş köy ve maden bulunamadı. Daha uzağa keşfe çıkıp tekrar dene."
    data["message.pastbound.expedition.reset"] = "Expedition failed. This historical world has been reset and your expedition has returned safely." if not tr else "Keşif başarısız oldu. Bu tarihî dünya sıfırlandı ve keşfin güvenle geri döndü."
    data["message.pastbound.expedition.reward"] = "%s completed. Expedition reward claimed: 4 Chronicle Scraps, 1 Time Stone, and bonus experience. Worlds completed: %s/%s." if not tr else "%s tamamlandı. Keşif ödülü alındı: 4 Chronicle Scrap, 1 Time Stone ve bonus deneyim. Tamamlanan dünyalar: %s/%s."
    data["message.pastbound.expedition.master_complete"] = "All historical worlds completed. The Master Chronicle Compass has been awarded." if not tr else "Tüm tarihî dünyalar tamamlandı. Usta Chronicle Compass ödülü verildi."
    for i in range(1, 9):
        data[f"key.pastbound.relic_slot_{i}"] = f"Activate Relic {i} with NumPad {i}" if not tr else f"Relic {i} NumPad {i} ile etkinleştir"
    path.write_text(json.dumps(data, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
print("shortcut_locale_added")
