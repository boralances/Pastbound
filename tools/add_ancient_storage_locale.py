from pathlib import Path
import json

root = Path(__file__).resolve().parents[1]
for path in (root / "src/main/resources/assets/pastbound/lang").glob("*.json"):
    data = json.loads(path.read_text(encoding="utf-8"))
    if path.name == "tr_tr.json":
        data["block.pastbound.ancient_storage"] = "Kadim Depolama"
        data["item.pastbound.ancient_storage"] = "Kadim Depolama"
        data["container.pastbound.ancient_storage"] = "Kadim Depolama Arşivi"
    else:
        data["block.pastbound.ancient_storage"] = "Ancient Storage"
        data["item.pastbound.ancient_storage"] = "Ancient Storage"
        data["container.pastbound.ancient_storage"] = "Ancient Storage Archive"
    path.write_text(json.dumps(data, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
print("ancient_storage_locale_added")
