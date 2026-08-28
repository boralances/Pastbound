from pathlib import Path
import json

root = Path(__file__).resolve().parents[1]
base = {
    "history.pastbound.period.epidaurum_tiyatrosu.name": "Epidaurum Theater",
    "history.pastbound.period.epidaurum_tiyatrosu.focus": "Stone, stage and healing",
    "history.pastbound.period.epidaurum_tiyatrosu.description": "Explore a living echo of theater, harbor life and the healing tradition of Asklepios on the Adriatic coast.",
    "screen.pastbound.scene.task.epidaurum_tiyatrosu": "Speak with the actor, cross the theater tiers, return to the beacon and break the calcite healing-stage monument.",
    "screen.pastbound.scene.ecosystem.epidaurum_tiyatrosu": "Adriatic archaeology: Chronicle ore marks the theater foundations and healing courtyards of Epidaurum.",
    "advancement.pastbound.time_machine.epidaurum_tiyatrosu.title": "Epidaurum Theater",
    "advancement.pastbound.time_machine.epidaurum_tiyatrosu.description": "Explore the Adriatic theater, harbor and healing tradition of Epidaurum.",
    "history.pastbound.period.epidaurum_tiyatrosu.response_1": "A harbor made the stage a meeting place for travelers, healers and citizens.",
    "history.pastbound.period.epidaurum_tiyatrosu.response_2": "The stone tiers carried voices farther than any single lifetime.",
    "history.pastbound.period.epidaurum_tiyatrosu.response_3": "Healing and performance both asked a community to remember together."
}
tr = {
    "history.pastbound.period.epidaurum_tiyatrosu.name": "Epidaurum Tiyatrosu",
    "history.pastbound.period.epidaurum_tiyatrosu.focus": "Taş, sahne ve şifa",
    "history.pastbound.period.epidaurum_tiyatrosu.description": "Adriyatik kıyısında tiyatronun, liman yaşamının ve Asklepios geleneğinin yaşayan yankısını araştır.",
    "screen.pastbound.scene.task.epidaurum_tiyatrosu": "Oyuncu ile konuş, tiyatro basamaklarını geç, işarete dön ve kalsit şifa-sahne anıtını kır.",
    "screen.pastbound.scene.ecosystem.epidaurum_tiyatrosu": "Adriyatik arkeolojisi: Kronik cevheri Epidaurum tiyatrosunun ve şifa avlularının temellerini işaretler.",
    "advancement.pastbound.time_machine.epidaurum_tiyatrosu.title": "Epidaurum Tiyatrosu",
    "advancement.pastbound.time_machine.epidaurum_tiyatrosu.description": "Epidaurum’un Adriyatik tiyatrosunu, limanını ve şifa geleneğini keşfet.",
    "history.pastbound.period.epidaurum_tiyatrosu.response_1": "Liman, sahneyi gezginler, şifacılar ve yurttaşlar için buluşma yerine dönüştürdü.",
    "history.pastbound.period.epidaurum_tiyatrosu.response_2": "Taş basamaklar sesleri tek bir ömürden daha uzağa taşıdı.",
    "history.pastbound.period.epidaurum_tiyatrosu.response_3": "Şifa ve gösteri, topluluktan birlikte hatırlamasını istedi."
}

dimension = {"type":"minecraft:overworld","generator":{"type":"minecraft:flat","settings":{"biome":"pastbound:uruk_floodplain","features":False,"lakes":False,"layers":[{"block":"minecraft:bedrock","height":1},{"block":"minecraft:stone","height":3},{"block":"minecraft:dirt","height":3},{"block":"minecraft:grass_block","height":1}],"structure_overrides":[]}}}
(root / "src/main/resources/data/pastbound/dimension/tarih_epidaurum_tiyatrosu.json").write_text(json.dumps(dimension, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
advancement = {"parent":"pastbound:time_machine/ipek_yolu_kervansarayi","display":{"icon":{"id":"pastbound:zaman_makinesi"},"title":{"translate":"advancement.pastbound.time_machine.epidaurum_tiyatrosu.title"},"description":{"translate":"advancement.pastbound.time_machine.epidaurum_tiyatrosu.description"},"frame":"task","show_toast":True,"announce_to_chat":False,"hidden":False},"criteria":{"kesif":{"trigger":"minecraft:impossible"}}}
(root / "src/main/resources/data/pastbound/advancements/time_machine/epidaurum_tiyatrosu.json").write_text(json.dumps(advancement, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
for path in (root / "src/main/resources/assets/pastbound/lang").glob("*.json"):
    data = json.loads(path.read_text(encoding="utf-8"))
    data.update(tr if path.name == "tr_tr.json" else base)
    path.write_text(json.dumps(data, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
print("epidaurum_finalized")
