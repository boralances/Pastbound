from pathlib import Path
import json

root = Path(__file__).resolve().parents[1]
periods = [
    "uruk_yazi_evi", "termopil_savasi", "iskenderiye_kutuphanesi", "bagdat_pili_atolyesi", "antikithera_limani", "bagdat_bilgi_evi", "timbuktu_el_yazmalari", "tenochtitlan_gecidi", "polinezya_yildiz_yolu", "catalhoyuk_yerleskesi", "apollo_ay_istigi", "ipek_yolu_kervansarayi", "epidaurum_tiyatrosu"
]
en = "Travel to all three marked field stations, speak with each specialist, walk at least 55 blocks through the site, return to the beacon, inspect the marked monument with the Memory Lens, then break it."
tr = "İşaretli üç saha istasyonuna git, her uzmanla konuş, alanda en az 55 blok yürü, işarete dön, Hafıza Merceği ile anıtı incele ve ardından kır."
for path in (root / "src/main/resources/assets/pastbound/lang").glob("*.json"):
    data = json.loads(path.read_text(encoding="utf-8"))
    turkce = path.name == "tr_tr.json"
    metin = tr if turkce else en
    data["message.pastbound.scene.quest"] = "Active mission: " + metin if not turkce else "Aktif görev: " + metin
    data["message.pastbound.scene.quest_explore"] = "Mission active: reach all three field stations and speak with their specialists." if not turkce else "Görev aktif: üç saha istasyonuna ulaş ve uzmanlarıyla konuş."
    data["screen.pastbound.scene.task_active"] = metin
    for period in periods:
        data[f"screen.pastbound.scene.task.{period}"] = metin
    path.write_text(json.dumps(data, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
print("active_quests_upgraded")
