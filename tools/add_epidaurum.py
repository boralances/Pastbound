from pathlib import Path
import json

root = Path(__file__).resolve().parents[1]

def replace(path, old, new):
    file = root / path
    text = file.read_text(encoding="utf-8")
    if old not in text:
        raise RuntimeError(f"missing pattern: {path}")
    file.write_text(text.replace(old, new, 1), encoding="utf-8")

replace(
    "src/main/java/dev/pastbound/history/TarihDonemi.java",
    '    IPEK_YOLU_KERVANSARAYI("ipek_yolu_kervansarayi", "İpek Yolu Kervansarayı", "Kültürler arası değişim", "Dillerin, kumaşların ve fikirlerin aynı avluda buluşmasını gör.");',
    '    IPEK_YOLU_KERVANSARAYI("ipek_yolu_kervansarayi", "İpek Yolu Kervansarayı", "Kültürler arası değişim", "Dillerin, kumaşların ve fikirlerin aynı avluda buluşmasını gör."),\n    EPIDAURUM_TİYATROSU("epidaurum_tiyatrosu", "Epidaurum Tiyatrosu", "Taş, sahne ve şifa", "Adriyatik kıyısında tiyatro, liman ve Asklepios geleneğinin yaşayan yankısını araştır.");'
)
replace(
    "src/main/java/dev/pastbound/history/TarihiKesifDunyasi.java",
    "            case IPEK_YOLU_KERVANSARAYI -> Blocks.RED_SANDSTONE;\n        };",
    "            case IPEK_YOLU_KERVANSARAYI -> Blocks.RED_SANDSTONE;\n            case EPIDAURUM_TİYATROSU -> Blocks.CALCITE;\n        };"
)
replace(
    "src/main/java/dev/pastbound/history/TarihiKesifDunyasi.java",
    "            case IPEK_YOLU_KERVANSARAYI -> Blocks.RED_SANDSTONE;\n        };\n    }\n}",
    "            case IPEK_YOLU_KERVANSARAYI -> Blocks.RED_SANDSTONE;\n            case EPIDAURUM_TİYATROSU -> Blocks.CALCITE;\n        };\n    }\n}"
)
replace(
    "src/main/java/dev/pastbound/history/ZamanMakinesiMantigi.java",
    "            case IPEK_YOLU_KERVANSARAYI -> {\n                efekt(oyuncu, MobEffects.SPEED, 220, 0);\n                efekt(oyuncu, MobEffects.HERO_OF_THE_VILLAGE, 220, 0);\n                oyuncu.giveExperiencePoints(3);\n                yankipar(oyuncu, seviye, ParticleTypes.HAPPY_VILLAGER);\n            }\n        }",
    "            case IPEK_YOLU_KERVANSARAYI -> {\n                efekt(oyuncu, MobEffects.SPEED, 220, 0);\n                efekt(oyuncu, MobEffects.HERO_OF_THE_VILLAGE, 220, 0);\n                oyuncu.giveExperiencePoints(3);\n                yankipar(oyuncu, seviye, ParticleTypes.HAPPY_VILLAGER);\n            }\n            case EPIDAURUM_TİYATROSU -> {\n                efekt(oyuncu, MobEffects.REGENERATION, 220, 0);\n                efekt(oyuncu, MobEffects.NIGHT_VISION, 220, 0);\n                oyuncu.giveExperiencePoints(6);\n                yankipar(oyuncu, seviye, ParticleTypes.END_ROD);\n            }\n        }"
)
replace(
    "src/main/java/dev/pastbound/history/ZamanMakinesiMantigi.java",
    '            case IPEK_YOLU_KERVANSARAYI -> "song_firini";\n        };',
    '            case IPEK_YOLU_KERVANSARAYI -> "song_firini";\n            case EPIDAURUM_TİYATROSU -> "roma_forumu";\n        };'
)
replace(
    "tools/generate_locales.py",
    '    "ipek_yolu_kervansarayi": ("Silk Road Caravanserai", "Exchange between cultures", "Listen to languages, fabrics, recipes and ideas meet in one courtyard as travelers transform trade into a network of shared memory.")\n}',
    '    "ipek_yolu_kervansarayi": ("Silk Road Caravanserai", "Exchange between cultures", "Listen to languages, fabrics, recipes and ideas meet in one courtyard as travelers transform trade into a network of shared memory."),\n    "epidaurum_tiyatrosu": ("Epidaurum Theater", "Stone, stage and healing", "Explore a living echo of theater, harbor life and the healing tradition of Asklepios on the Adriatic coast.")\n}'
)
replace(
    "tools/generate_locales.py",
    '    "ipek_yolu_kervansarayi": ("İpek Yolu Kervansarayı", "Kültürler arası değişim", "Tek bir avluda dillerin, kumaşların, tariflerin ve fikirlerin buluşmasını dinle; ticaretin ortak hafızaya dönüşmesine tanık ol.")\n}',
    '    "ipek_yolu_kervansarayi": ("İpek Yolu Kervansarayı", "Kültürler arası değişim", "Tek bir avluda dillerin, kumaşların, tariflerin ve fikirlerin buluşmasını dinle; ticaretin ortak hafızaya dönüşmesine tanık ol."),\n    "epidaurum_tiyatrosu": ("Epidaurum Tiyatrosu", "Taş, sahne ve şifa", "Adriyatik kıyısında tiyatronun, liman yaşamının ve Asklepios geleneğinin yaşayan yankısını araştır.")\n}'
)
replace(
    "tools/generate_locales.py",
    '    "ipek_yolu_kervansarayi": "Speak with the merchant, cross the caravan yard, return to the beacon and break the red sandstone exchange monument."\n}',
    '    "ipek_yolu_kervansarayi": "Speak with the merchant, cross the caravan yard, return to the beacon and break the red sandstone exchange monument.",\n    "epidaurum_tiyatrosu": "Speak with the actor, cross the theater tiers, return to the beacon and break the calcite healing-stage monument."\n}'
)
replace(
    "tools/generate_locales.py",
    '    "ipek_yolu_kervansarayi": "Tüccar ile konuş, kervan avlusunu geç, işarete dön ve kızıl kumtaşı değişim anıtını kır."\n}',
    '    "ipek_yolu_kervansarayi": "Tüccar ile konuş, kervan avlusunu geç, işarete dön ve kızıl kumtaşı değişim anıtını kır.",\n    "epidaurum_tiyatrosu": "Oyuncu ile konuş, tiyatro basamaklarını geç, işarete dön ve kalsit şifa-sahne anıtını kır."\n}'
)
replace(
    "tools/generate_locales.py",
    '    "ipek_yolu_kervansarayi": "Exchange ecology: rare chronicle veins cross the routes that carried ideas."\n}',
    '    "ipek_yolu_kervansarayi": "Exchange ecology: rare chronicle veins cross the routes that carried ideas.",\n    "epidaurum_tiyatrosu": "Adriatic archaeology: Chronicle ore marks the theater foundations and healing courtyards of Epidaurum."\n}'
)
replace(
    "tools/generate_locales.py",
    '    "ipek_yolu_kervansarayi": "Değişim ekolojisi: Nadir kronik damarları fikirleri taşıyan yolları keser."\n}',
    '    "ipek_yolu_kervansarayi": "Değişim ekolojisi: Nadir kronik damarları fikirleri taşıyan yolları keser.",\n    "epidaurum_tiyatrosu": "Adriyatik arkeolojisi: Kronik cevheri Epidaurum tiyatrosunun ve şifa avlularının temellerini işaretler."\n}'
)
replace(
    "tools/generate_locales.py",
    '    "ipek_yolu_kervansarayi": ("Who can rest in this courtyard?", "Merchants exchange languages, goods and techniques as well as silver.", "How does a road become a shared memory?")\n}',
    '    "ipek_yolu_kervansarayi": ("Who can rest in this courtyard?", "Merchants exchange languages, goods and techniques as well as silver.", "How does a road become a shared memory?"),\n    "epidaurum_tiyatrosu": ("Why build a theater beside a harbor?", "Performance, healing and public life can turn stone into a shared civic memory.", "What survives when the audience is gone?")\n}'
)
replace(
    "tools/generate_locales.py",
    '    "ipek_yolu_kervansarayi": ("Kim bu avluda dinlenebilir?", "Tüccarlar gümüşün yanı sıra dilleri, malları ve teknikleri de değiş tokuş eder.", "Bir yol nasıl ortak hafızaya dönüşür?")\n}',
    '    "ipek_yolu_kervansarayi": ("Kim bu avluda dinlenebilir?", "Tüccarlar gümüşün yanı sıra dilleri, malları ve teknikleri de değiş tokuş eder.", "Bir yol nasıl ortak hafızaya dönüşür?"),\n    "epidaurum_tiyatrosu": ("Limanın yanına neden tiyatro kuruldu?", "Gösteri, şifa ve kamusal yaşam taşı ortak bir kent hafızasına dönüştürebilir.", "Seyirci gittikten sonra ne kalır?")\n}'
)

dim = root / "src/main/resources/data/pastbound/dimension/tarih_epidaurum_tiyatrosu.json"
dim.write_text(json.dumps({"type":"minecraft:overworld","generator":{"type":"minecraft:flat","settings":{"biome":"pastbound:uruk_floodplain","features":False,"lakes":False,"layers":[{"block":"minecraft:bedrock","height":1},{"block":"minecraft:stone","height":3},{"block":"minecraft:dirt","height":3},{"block":"minecraft:grass_block","height":1}],"structure_overrides":[]}}}, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")

adv = root / "src/main/resources/data/pastbound/advancements/time_machine/epidaurum_tiyatrosu.json"
adv.write_text(json.dumps({"parent":"pastbound:time_machine/ipek_yolu_kervansarayi","display":{"icon":{"id":"pastbound:zaman_makinesi"},"title":{"translate":"advancement.pastbound.time_machine.epidaurum_tiyatrosu.title"},"description":{"translate":"advancement.pastbound.time_machine.epidaurum_tiyatrosu.description"},"frame":"task","show_toast":True,"announce_to_chat":False,"hidden":False},"criteria":{"kesif":{"trigger":"minecraft:impossible"}}}, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")

for locale in (root / "src/main/resources/assets/pastbound/lang").glob("*.json"):
    data = json.loads(locale.read_text(encoding="utf-8"))
    english = locale.name == "en_us.json"
    values = {
        "history.pastbound.period.epidaurum_tiyatrosu.name": "Epidaurum Theater",
        "history.pastbound.period.epidaurum_tiyatrosu.focus": "Stone, stage and healing",
        "history.pastbound.period.epidaurum_tiyatrosu.description": "Explore a living echo of theater, harbor life and the healing tradition of Asklepios on the Adriatic coast.",
        "screen.pastbound.scene.task.epidaurum_tiyatrosu": "Speak with the actor, cross the theater tiers, return to the beacon and break the calcite healing-stage monument.",
        "screen.pastbound.scene.ecosystem.epidaurum_tiyatrosu": "Adriatic archaeology: Chronicle ore marks the theater foundations and healing courtyards of Epidaurum.",
        "advancement.pastbound.time_machine.epidaurum_tiyatrosu.title": "Epidaurum Theater",
        "advancement.pastbound.time_machine.epidaurum_tiyatrosu.description": "Explore the Adriatic theater, harbor and healing tradition of Epidaurum.",
        "history.pastbound.period.epidaurum_tiyatrosu.response_1": "A harbor made the stage a meeting place for travelers, healers and citizens.",
        "history.pastbound.period.epidaurum_tiyatrosu.response_2": "The stone tiers carried voices farther than any single lifetime.",
        "history.pastbound.period.epidaurum_tiyatrosu.response_3": "Healing and performance both asked a community to remember together.",
    }
    if locale.name == "tr_tr.json":
        values.update({
            "history.pastbound.period.epidaurum_tiyatrosu.name": "Epidaurum Tiyatrosu",
            "history.pastbound.period.epidaurum_tiyatrosu.focus": "Taş, sahne ve şifa",
            "history.pastbound.period.epidaurum_tiyatrosu.description": "Adriyatik kıyısında tiyatronun, liman yaşamının ve Asklepios geleneğinin yaşayan yankısını araştır.",
            "screen.pastbound.scene.task.epidaurum_tiyatrosu": "Oyuncu ile konuş, tiyatro basamaklarını geç, işarete dön ve kalsit şifa-sahne anıtını kır.",
            "screen.pastbound.scene.ecosystem.epidaurum_tiyatrosu": "Adriyatik arkeolojisi: Kronik cevheri Epidaurum tiyatrosunun ve şifa avlularının temellerini işaretler.",
            "advancement.pastbound.time_machine.epidaurum_tiyatrosu.title": "Epidaurum Tiyatrosu",
            "advancement.pastbound.time_machine.epidaurum_tiyatrosu.description": "Epidaurum’un Adriyatik tiyatrosunu, limanını ve şifa geleneğini keşfet.",
            "history.pastbound.period.epidaurum_tiyatrosu.response_1": "Liman, sahneyi gezginler, şifacılar ve yurttaşlar için buluşma yerine dönüştürdü.",
            "history.pastbound.period.epidaurum_tiyatrosu.response_2": "Taş basamaklar sesleri tek bir ömürden daha uzağa taşıdı.",
            "history.pastbound.period.epidaurum_tiyatrosu.response_3": "Şifa ve gösteri, topluluktan birlikte hatırlamasını istedi.",
        })
    data.update(values)
    locale.write_text(json.dumps(data, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")

for path in (root / "src/main/resources/assets/pastbound/lang").glob("*.json"):
    json.loads(path.read_text(encoding="utf-8"))
print("epidaurum_added")
