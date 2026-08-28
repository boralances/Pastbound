from pathlib import Path
import json

root = Path(__file__).resolve().parents[1]
for path in (root / "src/main/resources/assets/pastbound/lang").glob("*.json"):
    data = json.loads(path.read_text(encoding="utf-8"))
    if path.name == "tr_tr.json":
        values = {
            "message.pastbound.world.quest_title": "Görev: Köyün Elektrik Yankısını Geri Getir",
            "message.pastbound.world.go_village": "Köye git ve arşivci köylüyü bul. Sadece cevap vermek yetmez; onun işaretli alanına ulaşmalısın.",
            "message.pastbound.world.village_reached": "Köydeki arşivci seni görevlendirdi. Şimdi güneydeki maden ocağına git.",
            "message.pastbound.world.archivist_spoke": "Arşivci kömür izlerini ve eski devre planını verdi. Maden ocağına git.",
            "message.pastbound.world.go_mine": "Maden ocağına gerçekten git. Üç işaretli çelik cevherini kaz.",
            "message.pastbound.world.mine_reached": "Maden ocağına ulaştın. Çelik damarları burada; üç tanesini çıkar.",
            "message.pastbound.world.mine_steel": "Üç çelik cevherini kaz ve köye geri taşı.",
            "message.pastbound.world.steel_found": "Çelik cevheri %s/%s çıkarıldı.",
            "message.pastbound.world.carry_steel": "Çelikleri köy meydanındaki rezonans sütununa taşı.",
            "message.pastbound.world.returned_with_steel": "Çelikleri geri getirdin. Şimdi elektrik düzeneğini kur.",
            "message.pastbound.world.build_power": "Rezonans sütununu bakır, redstone ve çelik levha ile çalıştır.",
            "message.pastbound.world.power_needs": "Düzeneği çalıştırmak için 2 bakır külçe, 4 redstone ve 1 çelik levha gerekiyor.",
            "message.pastbound.world.power_active": "Elektrik devresi çalıştı. Köyün geçmişten kalan yankısı arşive kaydedildi."
        }
    else:
        values = {
            "message.pastbound.world.quest_title": "Quest: Restore the Village Power Echo",
            "message.pastbound.world.go_village": "Travel to the village and find the archivist villager. A dialogue answer is not enough; reach the marked site.",
            "message.pastbound.world.village_reached": "The village archivist has assigned the job. Travel south to the mine.",
            "message.pastbound.world.archivist_spoke": "The archivist gave you a coal trace and an old circuit plan. Go to the mine.",
            "message.pastbound.world.go_mine": "Travel to the mine. Break the three marked steel ore blocks.",
            "message.pastbound.world.mine_reached": "You reached the mine. The steel veins are here; recover three of them.",
            "message.pastbound.world.mine_steel": "Mine three steel ores and carry them back to the village.",
            "message.pastbound.world.steel_found": "Steel ore %s/%s recovered.",
            "message.pastbound.world.carry_steel": "Carry the steel to the resonance pillar in the village square.",
            "message.pastbound.world.returned_with_steel": "You brought the steel back. Now build the electrical device.",
            "message.pastbound.world.build_power": "Activate the resonance pillar with copper, redstone and a steel plate.",
            "message.pastbound.world.power_needs": "The device needs 2 copper ingots, 4 redstone and 1 steel plate.",
            "message.pastbound.world.power_active": "The electrical circuit is alive. The village echo has been recorded in the archive."
        }
    data.update(values)
    path.write_text(json.dumps(data, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
print("world_quest_locale_added")
