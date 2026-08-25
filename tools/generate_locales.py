import json
from pathlib import Path

kok = Path(__file__).resolve().parents[1]
lang = kok / "src/main/resources/assets/pastbound/lang"
lang.mkdir(parents=True, exist_ok=True)

relicler = {
    "rossetta_tasi": "Rosetta Taşı",
    "gilgamesh_tableti": "Gılgamış Tableti",
    "anubis_ankhi": "Anubis Ankhı",
    "minos_labirent_muhru": "Minos Labirent Mührü",
    "roma_aureusu": "Roma Aureusu",
    "viking_gunes_pusulasi": "Viking Güneş Pusulası",
    "samuray_kabzasi": "Samuray Kabzası",
    "maya_gunes_carki": "Maya Güneş Çarkı",
    "inka_quipusu": "İnka Quipusu",
    "harappa_muhru": "Harappa Mührü",
    "song_porseleni": "Song Porseleni",
    "benin_bronzu": "Benin Bronz Levhası",
    "aztek_gunes_tasi": "Aztek Güneş Taşı",
    "abbasid_murekkebi": "Abbasi Mürekkebi",
    "ronesans_astrolabi": "Rönesans Astrolabı",
    "antikithera_duzenegi": "Antikythera Düzeneği",
    "catalhoyuk_boncugu": "Çatalhöyük Boncuğu",
    "bizans_mozaigi": "Bizans Mozaiği",
    "timbuktu_kalemi": "Timbuktu Kalemi",
    "apollo17_armasi": "Apollo 17 Arması",
    "ilhanli_madalyonu": "İlhanlı Madalyonu",
    "polinezya_yildiz_haritasi": "Polinezya Yıldız Haritası",
    "mali_tuz_muhru": "Mali Tuz Mührü",
    "iskandinav_runetasi": "İskandinav Rune Taşı"
}

ingilizce_relikler = {
    "rossetta_tasi": "Rosetta Stone",
    "gilgamesh_tableti": "Epic of Gilgamesh Tablet",
    "anubis_ankhi": "Anubis Ankh",
    "minos_labirent_muhru": "Minos Labyrinth Seal",
    "roma_aureusu": "Roman Aureus",
    "viking_gunes_pusulasi": "Viking Sun Compass",
    "samuray_kabzasi": "Samurai Hilt",
    "maya_gunes_carki": "Maya Sun Wheel",
    "inka_quipusu": "Inca Quipu",
    "harappa_muhru": "Harappan Seal",
    "song_porseleni": "Song Porcelain",
    "benin_bronzu": "Benin Bronze Plaque",
    "aztek_gunes_tasi": "Aztec Sun Stone",
    "abbasid_murekkebi": "Abbasid Ink",
    "ronesans_astrolabi": "Renaissance Astrolabe",
    "antikithera_duzenegi": "Antikythera Mechanism",
    "catalhoyuk_boncugu": "Çatalhöyük Bead",
    "bizans_mozaigi": "Byzantine Mosaic",
    "timbuktu_kalemi": "Timbuktu Quill",
    "apollo17_armasi": "Apollo 17 Mission Patch",
    "ilhanli_madalyonu": "Ilkhanid Medallion",
    "polinezya_yildiz_haritasi": "Polynesian Star Map",
    "mali_tuz_muhru": "Mali Salt Seal",
    "iskandinav_runetasi": "Scandinavian Rune Stone"
}

ingilizce_bilmeceler = {
    "rossetta_tasi": "Three languages fall silent; which stone remembers one voice?",
    "gilgamesh_tableti": "A king searched for immortality and found a friend; which story still lives?",
    "anubis_ankhi": "Death waits at the gate; which sign carries life onward?",
    "minos_labirent_muhru": "Walls turn and the center remains; what finds the way?",
    "roma_aureusu": "An empire falls, yet gold remains; whom does fortune choose?",
    "viking_gunes_pusulasi": "Behind the clouds, what can still reveal the direction?",
    "samuray_kabzasi": "Before the sword is drawn, where is the battle already won?",
    "maya_gunes_carki": "The day ends and the wheel turns; which light defeats the shadow?",
    "inka_quipusu": "A thousand knots hang on one cord; who remembers the number?",
    "harappa_muhru": "If the sign cannot be read, what does the city still tell us?",
    "song_porseleni": "Born in a kiln and carried across the sea; what does it preserve?",
    "benin_bronzu": "Born from fire, which face reveals the kingdom?",
    "aztek_gunes_tasi": "Four ages pass; which stone holds the fifth sky?",
    "abbasid_murekkebi": "Ink dries; in which night does an idea begin to shine?",
    "ronesans_astrolabi": "The stars are distant; where does the measuring hand rise?",
    "antikithera_duzenegi": "How can small gears remember the movement of the great sky?",
    "catalhoyuk_boncugu": "Homes join together; in which color does a community live?",
    "bizans_mozaigi": "A thousand fragments form one image; what does not break?",
    "timbuktu_kalemi": "The sands erase paths; which writing calls the road back?",
    "apollo17_armasi": "Earth is far away; in which silence does the first footprint remain?",
    "ilhanli_madalyonu": "Caravans pass; who seals trust along the road?",
    "polinezya_yildiz_haritasi": "With no horizon line, which star draws the island’s route?",
    "mali_tuz_muhru": "Protected like salt, along which road does fortune multiply?",
    "iskandinav_runetasi": "Words remain in stone; how do you step away from noise?"
}

turkce_bilmeceler = {
    "rossetta_tasi": "Üç dil susar; hangi taş tek bir sesi hatırlar?",
    "gilgamesh_tableti": "Bir kral ölümsüzlüğü aradı ve dost buldu; hangi hikâye hâlâ yaşar?",
    "anubis_ankhi": "Ölüm kapıda bekler; yaşamı hangi işaret taşır?",
    "minos_labirent_muhru": "Duvarlar döner, merkez kaybolmaz; yolu ne bulur?",
    "roma_aureusu": "Bir imparatorluk çöker, altın kalır; talih kimi seçer?",
    "viking_gunes_pusulasi": "Bulutların ardında yönü hâlâ ne gösterebilir?",
    "samuray_kabzasi": "Kılıç çekilmeden önce savaş nerede kazanılır?",
    "maya_gunes_carki": "Gün biter, çark döner; gölgeyi hangi ışık yener?",
    "inka_quipusu": "Bir ipte bin düğüm vardır; sayıyı kim hatırlar?",
    "harappa_muhru": "İşaret okunmazsa şehir bize ne anlatır?",
    "song_porseleni": "Fırında doğar, denizi geçer; neyi korur?",
    "benin_bronzu": "Ateşten çıkar; krallığı hangi yüz gösterir?",
    "aztek_gunes_tasi": "Dört çağ geçer; beşinci göğü hangi taş tutar?",
    "abbasid_murekkebi": "Mürekkep kurur; fikir hangi gecede parlar?",
    "ronesans_astrolabi": "Yıldızlar uzaktır; ölçüm yapan el nereye yükselir?",
    "antikithera_duzenegi": "Küçük dişliler büyük göğün hareketini nasıl hatırlar?",
    "catalhoyuk_boncugu": "Evler birleşir; topluluk hangi renkte yaşar?",
    "bizans_mozaigi": "Bin parça tek resim olur; kırılmayan neyi saklar?",
    "timbuktu_kalemi": "Kumlar yolları siler; yazı hangi yolu geri çağırır?",
    "apollo17_armasi": "Dünya uzaktadır; ilk ayak izi hangi sessizlikte kalır?",
    "ilhanli_madalyonu": "Kervan geçer; yol güvenini kim mühürler?",
    "polinezya_yildiz_haritasi": "Ufuk çizgisizdir; ada yolunu hangi yıldız çizer?",
    "mali_tuz_muhru": "Tuz gibi korunur; servet hangi yoldan çoğalır?",
    "iskandinav_runetasi": "Söz taşta kalır; gürültüden nasıl uzaklaşılır?"
}

yankilar = {
    "papirus_sifresi": "Papirüs Şifresi",
    "uruk_muhru": "Uruk Mührü",
    "nil_toreni": "Nil Töreni",
    "girit_ipi": "Girit İpi",
    "roma_forumu": "Roma Forumu",
    "kuzey_gunesi": "Kuzey Güneşi",
    "bushido_yemini": "Bushido Yemini",
    "maya_takvimi": "Maya Takvimi",
    "inka_dugumleri": "İnka Düğümleri",
    "harappa_drenaji": "Harappa Drenajı",
    "song_firini": "Song Fırını",
    "benin_dokumu": "Benin Dökümü",
    "aztek_bes_cag": "Aztek Beş Çağ",
    "abbasi_bilgi_evi": "Abbasi Bilgi Evi",
    "ronesans_atolyesi": "Rönesans Atölyesi",
    "antikithera_gok": "Antikythera Gök Hesabı",
    "catalhoyuk_evleri": "Çatalhöyük Evleri",
    "bizans_parca": "Bizans Parçası",
    "timbuktu_kervani": "Timbuktu Kervanı",
    "apollo_ay_yuruyusu": "Apollo Ay Yürüyüşü",
    "ilhanli_menzil": "İlhanlı Menzil Ağı",
    "polinezya_yildiz": "Polinezya Yıldız Rotası",
    "mali_sahra_tuzu": "Mali Sahra Tuzu",
    "rune_tasi": "Rune Taşı"
}

ingilizce_yankilar = {
    "papirus_sifresi": "Papyrus Cipher",
    "uruk_muhru": "Uruk Seal",
    "nil_toreni": "Nile Ceremony",
    "girit_ipi": "Cretan Thread",
    "roma_forumu": "Roman Forum",
    "kuzey_gunesi": "Northern Sun",
    "bushido_yemini": "Bushido Oath",
    "maya_takvimi": "Maya Calendar",
    "inka_dugumleri": "Inca Knots",
    "harappa_drenaji": "Harappan Drainage",
    "song_firini": "Song Kiln",
    "benin_dokumu": "Benin Casting",
    "aztek_bes_cag": "Aztec Five Suns",
    "abbasi_bilgi_evi": "House of Wisdom",
    "ronesans_atolyesi": "Renaissance Workshop",
    "antikithera_gok": "Antikythera Sky Calculation",
    "catalhoyuk_evleri": "Catalhoyuk Homes",
    "bizans_parca": "Byzantine Fragment",
    "timbuktu_kervani": "Timbuktu Caravan",
    "apollo_ay_yuruyusu": "Apollo Moon Walk",
    "ilhanli_menzil": "Ilkhanid Relay Network",
    "polinezya_yildiz": "Polynesian Star Route",
    "mali_sahra_tuzu": "Mali Sahara Salt",
    "rune_tasi": "Rune Stone"
}

time_duraklari = {
    "uruk_yazi_evi": ("Uruk Writing House", "The birth of writing", "Walk among the first clay records, where a mark for grain becomes a memory shared by an entire city."),
    "termopil_savasi": ("Thermopylae Pass", "War and strategy", "Stand in a narrow pass and listen to how discipline, terrain and courage turn a few moments into a story remembered for centuries."),
    "iskenderiye_kutuphanesi": ("Library of Alexandria", "The gathering of knowledge", "Follow scribes, sailors and translators as fragile scrolls gather into a living library that reaches beyond one language and one coast."),
    "bagdat_pili_atolyesi": ("Baghdad Battery Workshop", "Early electrical ideas", "Observe metal, liquid and careful curiosity in a workshop where an uncertain experiment becomes a question for future scientists."),
    "antikithera_limani": ("Antikythera Harbor", "Mechanical sky calculation", "Watch craftsmen align bronze gears with the sun and moon, making the movement of the heavens speak through a machine."),
    "bagdat_bilgi_evi": ("House of Wisdom", "Translation and science", "Enter a room where languages meet mathematics, medicine and astronomy, and a translated page becomes a new discovery."),
    "timbuktu_el_yazmalari": ("Timbuktu Manuscripts", "Caravans and education", "Protect pages carried across the Sahara and hear how schools, merchants and scholars keep knowledge alive at the edge of the desert."),
    "tenochtitlan_gecidi": ("Tenochtitlan Causeway", "The lake city and calendar", "Cross canals and markets while astronomers, farmers and builders connect the city’s daily rhythm to a turning calendar."),
    "polinezya_yildiz_yolu": ("Polynesian Star Route", "Ocean navigation", "Read stars, swells, winds and birds with navigators who cross an open ocean without drawing a conventional map."),
    "catalhoyuk_yerleskesi": ("Catalhoyuk Settlement", "The first shared homes", "Move through connected homes and shared spaces where food, craft, burial and memory shape one of humanity’s earliest communities."),
    "apollo_ay_istigi": ("Apollo Lunar Station", "The recent road to space", "Follow the quiet preparations behind a lunar journey and see how instruments, teamwork and a single footprint extend history beyond Earth."),
    "ipek_yolu_kervansarayi": ("Silk Road Caravanserai", "Exchange between cultures", "Listen to languages, fabrics, recipes and ideas meet in one courtyard as travelers transform trade into a network of shared memory.")
}

turkce_time_duraklari = {
    "uruk_yazi_evi": ("Uruk Yazı Evi", "Yazının doğuşu", "Kil tabletlerin ilk kayıtları arasında dolaş; bir tahıl işaretinin nasıl bütün şehrin ortak hafızasına dönüştüğünü gör."),
    "termopil_savasi": ("Termopil Savaş Geçidi", "Savaş ve strateji", "Dar geçitte disiplinin, arazinin ve cesaretin birkaç anı yüzyıllarca anlatılan bir hikâyeye nasıl çevirdiğini izle."),
    "iskenderiye_kutuphanesi": ("İskenderiye Kütüphanesi", "Bilginin toplanması", "Kâtipleri, denizcileri ve çevirmenleri izleyerek kırılgan tomarların tek bir kıyıyı aşan yaşayan bir kütüphaneye dönüşmesine tanık ol."),
    "bagdat_pili_atolyesi": ("Bağdat Pili Atölyesi", "Erken elektrik fikirleri", "Metal, sıvı ve dikkatli merakla yapılan belirsiz bir deneyin gelecek bilim insanlarına nasıl soru bıraktığını gör."),
    "antikithera_limani": ("Antikythera Limanı", "Mekanik gök hesabı", "Ustaların bronz dişlileri Güneş ve Ay’a hizalamasını, göğün hareketini bir makinenin diliyle anlatmasını izle."),
    "bagdat_bilgi_evi": ("Bağdat Bilgi Evi", "Çeviri ve bilim", "Dillerin matematik, tıp ve gökbilimle buluştuğu odaya gir; çevrilen bir sayfanın yeni bir keşfe dönüşmesini dinle."),
    "timbuktu_el_yazmalari": ("Timbuktu El Yazmaları", "Kervan ve eğitim", "Sahra’yı aşan sayfaları koru; okulların, tüccarların ve bilginlerin çölün kıyısında hafızayı nasıl yaşattığını dinle."),
    "tenochtitlan_gecidi": ("Tenochtitlan Geçidi", "Göl şehri ve takvim", "Kanallar ve pazarlar arasında ilerle; gökbilimcilerin, çiftçilerin ve ustaların şehrin ritmini takvime bağlayışını gör."),
    "polinezya_yildiz_yolu": ("Polinezya Yıldız Yolu", "Okyanus navigasyonu", "Geleneksel bir harita çizmeden açık okyanusu geçen denizcilerle yıldızları, dalgaları, rüzgârı ve kuşları oku."),
    "catalhoyuk_yerleskesi": ("Çatalhöyük Yerleşkesi", "İlk ortak yaşam", "Birbirine bağlanan evlerde dolaş; yiyecek, zanaat, gömü ve hatıranın ilk topluluklardan birini nasıl şekillendirdiğini izle."),
    "apollo_ay_istigi": ("Apollo Ay İstasyonu", "Uzaya giden yakın tarih yolu", "Ay yolculuğunun sessiz hazırlıklarını izle; araçların, ekip çalışmasının ve tek bir ayak izinin Dünya tarihini nasıl genişlettiğini gör."),
    "ipek_yolu_kervansarayi": ("İpek Yolu Kervansarayı", "Kültürler arası değişim", "Tek bir avluda dillerin, kumaşların, tariflerin ve fikirlerin buluşmasını dinle; ticaretin ortak hafızaya dönüşmesine tanık ol.")
}

ortak = {
    "block.pastbound.echo_archive": "Echo Archive",
    "block.pastbound.resonance_pillar": "Resonance Pillar",
    "item.pastbound.echo_shard": "Echo Shard",
    "item.pastbound.memory_lens": "Memory Lens",
    "tooltip.pastbound.relic.power": "Ability: %s",
    "tooltip.pastbound.relic.riddle": "Riddle: %s",
    "tooltip.pastbound.relic.identify": "Knowledge cost: %s XP levels",
    "tooltip.pastbound.relic.xp": "Shift-right-click to spend %s XP levels and identify",
    "tooltip.pastbound.relic.shortcut": "V: awaken while equipped",
    "message.pastbound.archive_complete": "The archive is full.",
    "message.pastbound.archive_incomplete": "The archive holds %s/%s echoes.",
    "message.pastbound.archive_progress": "Echo recorded: %s/%s",
    "message.pastbound.memory_read": "Memory read from %s. Pillars awakened: %s",
    "message.pastbound.relic.bad_name": "That relic is not in the historical record.",
    "message.pastbound.relic.already_known": "You already know the story of %s.",
    "message.pastbound.relic.cooldown": "The relic is still gathering an echo.",
    "message.pastbound.relic.no_curios": "Curios is not providing a relic inventory.",
    "message.pastbound.relic.no_known": "No known relic is equipped in a Curios relic slot.",
    "message.pastbound.relic.unknown": "The relic remains unknown. Riddle: %s",
    "message.pastbound.relic.riddle_wrong": "The past stays silent. That answer does not fit.",
    "message.pastbound.relic.riddle_right": "The riddle opens the memory of %s.",
    "message.pastbound.relic.knowledge": "Historical memory added: %s",
    "message.pastbound.relic.identified": "You spent experience and identified %s.",
    "message.pastbound.relic.xp_hint": "Hold Shift and use it with at least %s XP levels to identify it.",
    "message.pastbound.relic.activated": "%s answers your call.",
    "message.pastbound.journal_count": "Historical memories recovered: %s/%s",
    "screen.pastbound.progress": "Memories recovered: %s/%s",
    "screen.pastbound.help": "R journal   V awaken",
    "screen.pastbound.ready": "KNOWN",
    "screen.pastbound.unknown": "UNKNOWN",
    "screen.pastbound.riddle_hint": "Solve the riddle",
    "screen.pastbound.close": "Esc to close",
    "screen.pastbound.page": "Page %s/%s",
    "screen.pastbound.page_hint": "Left/Right: change page   Click: inspect relic",
    "key.pastbound.journal": "Open Relic Journal",
    "key.pastbound.activate": "Awaken equipped relic",
    "key.pastbound.take_control": "Take control of the historical scene",
    "key.pastbound.relic_slot_1": "Activate relic slot 1",
    "key.pastbound.relic_slot_2": "Activate relic slot 2",
    "key.pastbound.relic_slot_3": "Activate relic slot 3",
    "key.pastbound.relic_slot_4": "Activate relic slot 4",
    "key.pastbound.relic_slot_5": "Activate relic slot 5",
    "key.pastbound.relic_slot_6": "Activate relic slot 6",
    "key.pastbound.relic_slot_7": "Activate relic slot 7",
    "key.pastbound.relic_slot_8": "Activate relic slot 8",
    "key.pastbound.relic_slot_9": "Activate relic slot 9",
    "key.pastbound.relic_slot_10": "Activate relic slot 10",
    "tooltip.pastbound.relic.era": "Historical trace: %s",
    "tooltip.pastbound.relic.echo": "Discovery echo: %s",
    "item.pastbound.chronicle_scrap": "Chronicle Scrap",
    "item.pastbound.history_ink": "History Ink",
    "item.pastbound.time_stone": "Time Stone",
    "item.pastbound.echo_seal": "Echo Seal",
    "item.pastbound.zaman_makinesi": "Time Machine",
    "item.pastbound.firin_cubugu": "Furnace on a Stick",
    "effect.pastbound.tarih_yankisi": "Historical Echo",
    "screen.pastbound.echo_progress": "Echoes: %s/%s",
    "screen.pastbound.echo_found": "ECHO FOUND",
    "screen.pastbound.click_hint": "Click a relic to open its historical trial",
    "screen.pastbound.modal_title": "Historical Echo Trial",
    "screen.pastbound.sequence": "Sequence: %s",
    "screen.pastbound.choose": "Choose 1, 2, or 3",
    "screen.pastbound.solve_hint": "Enter to submit   Esc to return",
    "screen.pastbound.riddle_input": "Riddle answer: %s",
    "screen.pastbound.solve_gui_hint": "Type the answer or choose 1-2-3, then press Enter",
    "screen.pastbound.slot_hint": "U: exchange 10 Netherite Blocks for 2 relic slots",
    "screen.pastbound.time_machine": "Time Machine",
    "screen.pastbound.time_machine.subtitle": "Choose a historical place to explore its living echo.",
    "screen.pastbound.time_machine.hint": "Click a destination or press 1-9",
    "screen.pastbound.time_machine.sent": "Temporal route prepared",
    "screen.pastbound.time_machine.cost": "Travel costs 1 Time Stone",
    "message.pastbound.echo.discovered": "Historical echo recovered: %s",
    "message.pastbound.echo.trace": "Trace recorded: %s",
    "message.pastbound.echo.bad_name": "That echo is not in the chronicle.",
    "message.pastbound.echo.already": "The echo of %s is already recorded.",
    "message.pastbound.echo.wrong": "The sequence fades. Hint: %s",
    "message.pastbound.echo_count": "Historical echoes recovered: %s/%s",
    "message.pastbound.echo.stirs": "A historical echo stirs: %s",
    "message.pastbound.echo.open_journal": "Press R to open the Relic Journal and attempt its historical trial.",
    "message.pastbound.echo.locked": "The historical trial for %s has not been awakened yet. Trigger its related action first.",
    "message.pastbound.global.yazi_devrimi": "The Writing Revolution echoes across the server: clay marks become memory, and memory becomes a bridge between generations.",
    "message.pastbound.global.nil_kanallari": "The Nile Canals open a route through history: floodwater, grain and careful measurement turn a river into a shared calendar.",
    "message.pastbound.global.yildiz_seferi": "The Star Voyage marks every horizon: sailors read stars, waves and distant birds to turn uncertainty into a route home.",
    "message.pastbound.global.kervan_yolu": "The Caravan Road connects distant memories: salt, silk, stories and instruments cross deserts because knowledge travels with people.",
    "message.pastbound.global.takvim_meclisi": "The Calendar Council aligns the living world: planting, harvest, ceremony and travel all depend on a common measure of time.",
    "message.pastbound.global.demirci_hafizasi": "The Smiths’ Memory rings beneath the earth: ore becomes tools, tools become cities, and every hammer strike leaves a human signature.",
    "message.pastbound.global.mozaik_baris": "The Mosaic Peace gathers scattered fragments: different colors and traditions form a single image without losing their own shape.",
    "message.pastbound.global.ipek_degisimi": "The Silk Exchange carries knowledge between shores: languages, dyes, medicines and mathematical ideas travel farther than any single merchant.",
    "message.pastbound.global.quipu_sayimi": "The Quipu Count records the world’s rhythm: knots preserve quantities, promises and journeys when ink is not available.",
    "message.pastbound.global.astrolab_gogu": "The Astrolabe Sky reveals a measured path: a small instrument joins mathematics, astronomy and the dangerous freedom of the open sea.",
    "message.pastbound.global.rune_nobeti": "The Rune Watch keeps the northern night: a carved sign carries a name, a warning and the memory of the hand that made it.",
    "message.pastbound.global.ay_misyonu": "The Moon Mission leaves a new echo beyond the world: footprints, instruments and a quiet promise that curiosity can change the horizon.",
    "message.pastbound.global.tarim_devrimi": "The Farming Revolution changes the rhythm of human life: seeds, irrigation and stored grain turn a seasonal camp into a lasting settlement.",
    "message.pastbound.global.akdeniz_gemiciligi": "Mediterranean navigation links harbors and cultures: a wind, a current and a familiar constellation can carry a story across the sea.",
    "message.pastbound.global.matbaa_yankisi": "The Printing Press Echo multiplies memory: one page can travel through many hands and give an idea a life beyond its author.",
    "message.pastbound.global.tip_bahcesi": "The Healing Garden preserves patient observation: roots, leaves and careful notes transform experience into a tradition of care.",
    "message.pastbound.global.cam_yolu": "The Glass Road glows with craft: sand, heat and patience turn fragile material into vessels that preserve light and daily life.",
    "message.pastbound.global.gozlem_evi": "The Observatory House gathers the night sky: repeated observations reveal patterns that belong to everyone who looks upward.",
    "message.pastbound.global.hukuk_meclisi": "The Law Assembly gives memory a public voice: rules become meaningful when a community explains, records and questions them together.",
    "message.pastbound.global.arkeoloji_kesfi": "The Archaeology Discovery begins beneath the dust: a broken shard can outlive a kingdom and still tell us how ordinary people lived.",
    "message.pastbound.packet.invalid": "The archive rejected that request.",
    "message.pastbound.slot.max": "All ten relic slots are already open.",
    "message.pastbound.slot.cost": "You need %s Netherite Blocks to open two relic slots.",
    "message.pastbound.slot.unlocked": "Two relic slots opened. Total capacity: %s.",
    "message.pastbound.time_machine.unknown": "That historical destination is not in the machine.",
    "message.pastbound.time_machine.cooldown": "The time machine is still stabilising.",
    "message.pastbound.time_machine.time_stone_needed": "You need 1 Time Stone to travel through time.",
    "message.pastbound.time_machine.arrived": "Arrived at %s: %s",
    "message.pastbound.time_machine.dimension_unavailable": "The historical dimension is not available in this world.",
    "message.pastbound.scene.enter": "Entering the living history of %s.",
    "message.pastbound.scene.press_d": "Press D when you want to take control.",
    "message.pastbound.scene.narration_intro": "%s begins: %s",
    "message.pastbound.scene.narration_focus": "Historical focus: %s",
    "message.pastbound.scene.narration_detail": "The living scene reveals more: %s",
    "message.pastbound.scene.focus": "Historical focus: %s",
    "message.pastbound.scene.observe": "The reconstruction reveals scene %s.",
    "message.pastbound.scene.ready": "The scene is ready. Press D to become part of it.",
    "message.pastbound.scene.control": "You are now part of the historical scene.",
    "message.pastbound.scene.returned": "You returned from the historical reconstruction.",
    "message.pastbound.dialogue.too_far": "Move closer to the historical speaker.",
    "screen.pastbound.scene.title": "Living History",
    "screen.pastbound.scene.phase_one": "The first memory is taking shape...",
    "screen.pastbound.scene.phase_two": "Voices and movement return to the past...",
    "screen.pastbound.scene.phase_three": "The scene is waiting for your choice...",
    "screen.pastbound.scene.press_d": "D: take control of the scene",
    "screen.pastbound.scene.escape_hint": "Esc: close the narration window",
    "screen.pastbound.dialogue.title": "A Conversation with History",
    "screen.pastbound.dialogue.choose": "Choose a question with 1, 2 or 3",
    "entity.pastbound.scene.narrator": "Historical Narrator",
    "entity.pastbound.scene.craftsman": "Craftsperson",
    "entity.pastbound.scene.witness": "Witness",
    "entity.pastbound.scene.scribe": "Scribe",
}

metinler = {
    "en_us": {"itemGroup.pastbound": "Pastbound: Echoes of History", "screen.pastbound.journal": "Relic Journal"},
    "tr_tr": {"itemGroup.pastbound": "Pastbound: Tarihin Yankıları", "screen.pastbound.journal": "Relik Günlüğü", "key.pastbound.take_control": "Tarihî sahnenin kontrolünü al", "key.pastbound.relic_slot_1": "Relic yuvası 1’i etkinleştir", "key.pastbound.relic_slot_2": "Relic yuvası 2’yi etkinleştir", "key.pastbound.relic_slot_3": "Relic yuvası 3’ü etkinleştir", "key.pastbound.relic_slot_4": "Relic yuvası 4’ü etkinleştir", "key.pastbound.relic_slot_5": "Relic yuvası 5’i etkinleştir", "key.pastbound.relic_slot_6": "Relic yuvası 6’yı etkinleştir", "key.pastbound.relic_slot_7": "Relic yuvası 7’yi etkinleştir", "key.pastbound.relic_slot_8": "Relic yuvası 8’i etkinleştir", "key.pastbound.relic_slot_9": "Relic yuvası 9’u etkinleştir", "key.pastbound.relic_slot_10": "Relic yuvası 10’u etkinleştir", "block.pastbound.echo_archive": "Yankı Arşivi", "block.pastbound.resonance_pillar": "Rezonans Sütunu", "item.pastbound.echo_shard": "Yankı Parçası", "item.pastbound.memory_lens": "Hafıza Merceği", "item.pastbound.chronicle_scrap": "Kronik Parçası", "item.pastbound.history_ink": "Tarih Mürekkebi", "item.pastbound.time_stone": "Zaman Taşı", "item.pastbound.echo_seal": "Yankı Mührü", "item.pastbound.zaman_makinesi": "Zaman Makinesi", "item.pastbound.firin_cubugu": "Çubukta Fırın", "effect.pastbound.tarih_yankisi": "Tarih Yankısı", "screen.pastbound.riddle_input": "Bilmece cevabı: %s", "screen.pastbound.solve_gui_hint": "Cevabı yaz veya 1-2-3 seç, sonra Enter’a bas", "screen.pastbound.slot_hint": "U: 10 Netherite Block karşılığında 2 relic yuvası aç", "screen.pastbound.time_machine": "Zaman Makinesi", "screen.pastbound.time_machine.subtitle": "Yaşayan yankısını keşfetmek için tarihî bir durak seç.", "screen.pastbound.time_machine.hint": "Bir durağa tıkla veya 1-9’a bas", "screen.pastbound.time_machine.sent": "Zamansal rota hazırlandı", "screen.pastbound.time_machine.cost": "Yolculuk için 1 Zaman Taşı gerekir", "tooltip.pastbound.relic.power": "Yetenek: %s", "tooltip.pastbound.relic.riddle": "Bilmece: %s", "tooltip.pastbound.relic.identify": "Bilgi bedeli: %s XP seviyesi", "tooltip.pastbound.relic.xp": "Tanımak için Shift ile sağ tıkla ve %s XP seviyesi harca", "tooltip.pastbound.relic.shortcut": "V: takılıyken uyandır", "message.pastbound.relic.riddle_wrong": "Geçmiş susuyor. Bu cevap uymuyor.", "message.pastbound.relic.riddle_right": "Bilmece %s anısını açtı.", "message.pastbound.relic.identified": "XP harcanarak tanındı: %s.", "message.pastbound.relic.xp_hint": "%s XP seviyesine ihtiyacın var.", "message.pastbound.relic.knowledge": "Tarihî hafızaya eklendi: %s", "screen.pastbound.progress": "Kazanılan anılar: %s/%s", "screen.pastbound.help": "R günlük   V uyandır", "screen.pastbound.ready": "BİLİNİYOR", "screen.pastbound.unknown": "BİLİNMİYOR", "screen.pastbound.riddle_hint": "Bilmeceyi çöz", "screen.pastbound.close": "Kapatmak için Esc", "screen.pastbound.page": "Sayfa %s/%s", "screen.pastbound.page_hint": "Sol/Sağ: sayfa değiştir   Tıkla: relic’i incele", "screen.pastbound.echo_progress": "Yankılar: %s/%s", "screen.pastbound.echo_found": "YANKI BULUNDU", "screen.pastbound.click_hint": "Tarihî sınavı açmak için bir relic’e tıkla", "screen.pastbound.modal_title": "Tarihî Yankı Sınavı", "screen.pastbound.sequence": "Dizi: %s", "screen.pastbound.choose": "1, 2 veya 3 seç", "screen.pastbound.solve_hint": "Göndermek için Enter   Geri dönmek için Esc", "message.pastbound.echo.discovered": "Tarihî yankı bulundu: %s", "message.pastbound.echo.trace": "İz kaydedildi: %s", "message.pastbound.echo.bad_name": "Bu yankı kronikte yok.", "message.pastbound.echo.already": "%s yankısı zaten kaydedildi.", "message.pastbound.echo.wrong": "Dizi dağıldı. İpucu: %s", "message.pastbound.echo_count": "Bulunan tarihî yankılar: %s/%s", "message.pastbound.echo.stirs": "Bir tarihî yankı uyandı: %s", "message.pastbound.echo.open_journal": "Tarihî sınava girmek için R’ye basıp Relik Günlüğü’nü aç.", "message.pastbound.echo.locked": "%s için tarihî sınav henüz uyanmadı. Önce ilgili aksiyonu gerçekleştir.", "message.pastbound.global.yazi_devrimi": "Yazı Devrimi sunucuda yankılanıyor: kil üzerindeki işaretler hafızaya, hafıza da kuşaklar arasında bir köprüye dönüşüyor.", "message.pastbound.global.nil_kanallari": "Nil Kanalları tarihte yeni bir yol açıyor: taşkın, tahıl ve dikkatli ölçüm nehri ortak bir takvime dönüştürüyor.", "message.pastbound.global.yildiz_seferi": "Yıldız Seferi her ufku işaretliyor: denizciler yıldızları, dalgaları ve kuşları okuyarak belirsizliği eve dönüş yoluna çeviriyor.", "message.pastbound.global.kervan_yolu": "Kervan Yolu uzak anıları bağlıyor: tuz, ipek, hikâyeler ve aletler çölleri aşarken bilgi de insanlarla birlikte yol alıyor.", "message.pastbound.global.takvim_meclisi": "Takvim Meclisi yaşayan dünyayı hizalıyor: ekim, hasat, tören ve yolculuk ortak bir zaman ölçüsüne bağlanıyor.", "message.pastbound.global.demirci_hafizasi": "Demircilerin Hafızası yerin altında çınlıyor: cevher alete, alet şehre dönüşüyor ve her çekiç darbesi insan izini koruyor.", "message.pastbound.global.mozaik_baris": "Mozaik Barışı dağınık parçaları topluyor: farklı renkler ve gelenekler kendi biçimini kaybetmeden tek bir görüntü oluşturuyor.", "message.pastbound.global.ipek_degisimi": "İpek Değişimi kıyılar arasında bilgi taşıyor: diller, boyalar, ilaçlar ve matematik fikirleri tek bir tüccardan çok daha uzağa ulaşıyor.", "message.pastbound.global.quipu_sayimi": "Quipu Sayımı dünyanın ritmini kaydediyor: düğümler, mürekkep bulunmadığında miktarları, sözleri ve yolculukları koruyor.", "message.pastbound.global.astrolab_gogu": "Astrolab Göğü ölçülü bir yol gösteriyor: küçük bir alet matematiği, gökbilimi ve açık denizin tehlikeli özgürlüğünü birleştiriyor.", "message.pastbound.global.rune_nobeti": "Rune Nöbeti kuzey gecesini koruyor: taşa kazınmış işaret bir adı, uyarıyı ve onu işleyen elin hatırasını taşıyor.", "message.pastbound.global.ay_misyonu": "Ay Misyonu dünyanın ötesine yeni bir yankı bırakıyor: ayak izleri, araçlar ve merakın ufku değiştirebileceğine dair sessiz bir söz.", "message.pastbound.global.tarim_devrimi": "Tarım Devrimi insan yaşamının ritmini değiştiriyor: tohum, sulama ve depolanan tahıl mevsimlik kampı kalıcı yerleşime dönüştürüyor.", "message.pastbound.global.akdeniz_gemiciligi": "Akdeniz denizciliği limanları ve kültürleri bağlıyor: rüzgâr, akıntı ve tanıdık bir takımyıldız hikâyeyi denizin ötesine taşıyor.", "message.pastbound.global.matbaa_yankisi": "Matbaa Yankısı hafızayı çoğaltıyor: tek bir sayfa birçok elden geçerek fikre yazarından daha uzun bir hayat veriyor.", "message.pastbound.global.tip_bahcesi": "Şifa Bahçesi sabırlı gözlemi koruyor: kökler, yapraklar ve dikkatli notlar deneyimi bakım geleneğine dönüştürüyor.", "message.pastbound.global.cam_yolu": "Cam Yolu zanaatla parlıyor: kum, ısı ve sabır kırılgan malzemeyi ışığı ve günlük yaşamı koruyan kaplara çeviriyor.", "message.pastbound.global.gozlem_evi": "Gözlem Evi gece göğünü bir araya getiriyor: tekrar edilen gözlemler yukarı bakan herkesin paylaşabileceği örüntüler açığa çıkarıyor.", "message.pastbound.global.hukuk_meclisi": "Hukuk Meclisi hafızaya kamusal bir ses veriyor: kurallar topluluk onları birlikte açıkladığında, kaydettiğinde ve sorguladığında anlam kazanıyor.", "message.pastbound.global.arkeoloji_kesfi": "Arkeoloji Keşfi tozun altında başlıyor: kırık bir parça bir krallıktan uzun yaşayarak sıradan insanların nasıl yaşadığını anlatabiliyor.", "message.pastbound.packet.invalid": "Arşiv bu isteği reddetti.", "message.pastbound.slot.max": "On relic yuvasının tamamı zaten açık.", "message.pastbound.slot.cost": "İki relic yuvası açmak için %s Netherite Block gerekiyor.", "message.pastbound.slot.unlocked": "İki relic yuvası açıldı. Toplam kapasite: %s.", "message.pastbound.time_machine.unknown": "Bu tarihî durak makinede yok.", "message.pastbound.time_machine.cooldown": "Zaman makinesi hâlâ dengeleniyor.", "message.pastbound.time_machine.time_stone_needed": "Zamanda yolculuk için 1 Zaman Taşı gerekir.", "message.pastbound.time_machine.arrived": "%s durağına ulaşıldı: %s", "message.pastbound.time_machine.dimension_unavailable": "Tarihî keşif boyutu bu dünyada kullanılamıyor.", "message.pastbound.scene.enter": "%s tarihinin yaşayan sahnesine giriyorsun.", "message.pastbound.scene.press_d": "Kontrolü almak istediğinde D’ye bas.", "message.pastbound.scene.narration_intro": "%s başlıyor: %s", "message.pastbound.scene.narration_focus": "Tarihî odak: %s", "message.pastbound.scene.narration_detail": "Yaşayan sahne daha fazlasını gösteriyor: %s", "message.pastbound.scene.focus": "Tarihî odak: %s", "message.pastbound.scene.observe": "Canlandırma %s. sahneyi gösteriyor.", "message.pastbound.scene.ready": "Sahne hazır. İçine girmek için D’ye bas.", "message.pastbound.scene.control": "Artık tarihî sahnenin bir parçasısın.", "message.pastbound.scene.returned": "Tarihî canlandırmadan geri döndün.", "screen.pastbound.scene.title": "Yaşayan Tarih", "screen.pastbound.scene.phase_one": "İlk anı şekilleniyor...", "screen.pastbound.scene.phase_two": "Sesler ve hareket geçmişe dönüyor...", "screen.pastbound.scene.phase_three": "Sahne seçimini bekliyor...", "screen.pastbound.scene.press_d": "D: sahnenin kontrolünü al", "screen.pastbound.scene.escape_hint": "Esc: anlatım penceresini kapat", "screen.pastbound.dialogue.title": "Tarihle Konuşma", "screen.pastbound.dialogue.choose": "1, 2 veya 3 ile soru seç", "message.pastbound.dialogue.too_far": "Tarihî konuşmacıya yaklaş.", "entity.pastbound.scene.narrator": "Tarih Anlatıcısı", "entity.pastbound.scene.craftsman": "Zanaatkâr", "entity.pastbound.scene.witness": "Tanık", "entity.pastbound.scene.scribe": "Kâtip", "tooltip.pastbound.relic.echo": "Keşif yankısı: %s"},
    "de_de": {"itemGroup.pastbound": "Pastbound: Echos der Geschichte", "screen.pastbound.journal": "Relikt-Tagebuch", "screen.pastbound.progress": "Geborgene Erinnerungen: %s/%s", "screen.pastbound.help": "R Tagebuch   V erwecken", "screen.pastbound.ready": "BEKANNT", "screen.pastbound.unknown": "UNBEKANNT", "screen.pastbound.riddle_hint": "Rätsel lösen", "screen.pastbound.close": "Esc zum Schließen"},
    "fr_fr": {"itemGroup.pastbound": "Pastbound : Échos de l’histoire", "screen.pastbound.journal": "Journal des reliques", "screen.pastbound.progress": "Souvenirs retrouvés : %s/%s", "screen.pastbound.help": "R journal   V éveiller", "screen.pastbound.ready": "CONNU", "screen.pastbound.unknown": "INCONNU", "screen.pastbound.riddle_hint": "Résoudre l’énigme", "screen.pastbound.close": "Esc pour fermer"},
    "es_es": {"itemGroup.pastbound": "Pastbound: Ecos de la historia", "screen.pastbound.journal": "Diario de reliquias", "screen.pastbound.progress": "Memorias recuperadas: %s/%s", "screen.pastbound.help": "R diario   V despertar", "screen.pastbound.ready": "CONOCIDA", "screen.pastbound.unknown": "DESCONOCIDA", "screen.pastbound.riddle_hint": "Resuelve el acertijo", "screen.pastbound.close": "Esc para cerrar"},
    "es_mx": {"itemGroup.pastbound": "Pastbound: Ecos de la historia", "screen.pastbound.journal": "Diario de reliquias", "screen.pastbound.progress": "Memorias recuperadas: %s/%s", "screen.pastbound.help": "R diario   V despertar", "screen.pastbound.ready": "CONOCIDA", "screen.pastbound.unknown": "DESCONOCIDA", "screen.pastbound.riddle_hint": "Resuelve el acertijo", "screen.pastbound.close": "Esc para cerrar"},
    "it_it": {"itemGroup.pastbound": "Pastbound: Echi della storia", "screen.pastbound.journal": "Diario delle reliquie", "screen.pastbound.progress": "Memorie recuperate: %s/%s", "screen.pastbound.help": "R diario   V risveglia", "screen.pastbound.ready": "NOTA", "screen.pastbound.unknown": "IGNOTA", "screen.pastbound.riddle_hint": "Risolvi l’enigma", "screen.pastbound.close": "Esc per chiudere"},
    "pt_br": {"itemGroup.pastbound": "Pastbound: Ecos da história", "screen.pastbound.journal": "Diário de relíquias", "screen.pastbound.progress": "Memórias recuperadas: %s/%s", "screen.pastbound.help": "R diário   V despertar", "screen.pastbound.ready": "CONHECIDA", "screen.pastbound.unknown": "DESCONHECIDA", "screen.pastbound.riddle_hint": "Resolva o enigma", "screen.pastbound.close": "Esc para fechar"},
    "pt_pt": {"itemGroup.pastbound": "Pastbound: Ecos da história", "screen.pastbound.journal": "Diário de relíquias", "screen.pastbound.progress": "Memórias recuperadas: %s/%s", "screen.pastbound.help": "R diário   V despertar", "screen.pastbound.ready": "CONHECIDA", "screen.pastbound.unknown": "DESCONHECIDA", "screen.pastbound.riddle_hint": "Resolve o enigma", "screen.pastbound.close": "Esc para fechar"},
    "ru_ru": {"itemGroup.pastbound": "Pastbound: Эхо истории", "screen.pastbound.journal": "Дневник реликвий", "screen.pastbound.progress": "Возвращено воспоминаний: %s/%s", "screen.pastbound.help": "R дневник   V пробудить", "screen.pastbound.ready": "ИЗВЕСТНО", "screen.pastbound.unknown": "НЕИЗВЕСТНО", "screen.pastbound.riddle_hint": "Решите загадку", "screen.pastbound.close": "Esc — закрыть"},
    "uk_ua": {"itemGroup.pastbound": "Pastbound: Відлуння історії", "screen.pastbound.journal": "Щоденник реліквій", "screen.pastbound.progress": "Відновлено спогадів: %s/%s", "screen.pastbound.help": "R щоденник   V пробудити", "screen.pastbound.ready": "ВІДОМО", "screen.pastbound.unknown": "НЕВІДОМО", "screen.pastbound.riddle_hint": "Розв’яжіть загадку", "screen.pastbound.close": "Esc — закрити"},
    "pl_pl": {"itemGroup.pastbound": "Pastbound: Echa historii", "screen.pastbound.journal": "Dziennik relikwii", "screen.pastbound.progress": "Odzyskane wspomnienia: %s/%s", "screen.pastbound.help": "R dziennik   V przebudź", "screen.pastbound.ready": "ZNANA", "screen.pastbound.unknown": "NIEZNANA", "screen.pastbound.riddle_hint": "Rozwiąż zagadkę", "screen.pastbound.close": "Esc, aby zamknąć"},
    "nl_nl": {"itemGroup.pastbound": "Pastbound: Echo’s van de geschiedenis", "screen.pastbound.journal": "Reliekenjournaal", "screen.pastbound.progress": "Herstelde herinneringen: %s/%s", "screen.pastbound.help": "R journaal   V wekken", "screen.pastbound.ready": "BEKEND", "screen.pastbound.unknown": "ONBEKEND", "screen.pastbound.riddle_hint": "Los het raadsel op", "screen.pastbound.close": "Esc om te sluiten"},
    "sv_se": {"itemGroup.pastbound": "Pastbound: Historiens ekon", "screen.pastbound.journal": "Relikviedagbok", "screen.pastbound.progress": "Återfunna minnen: %s/%s", "screen.pastbound.help": "R dagbok   V väck", "screen.pastbound.ready": "KÄND", "screen.pastbound.unknown": "OKÄND", "screen.pastbound.riddle_hint": "Lös gåtan", "screen.pastbound.close": "Esc för att stänga"},
    "da_dk": {"itemGroup.pastbound": "Pastbound: Historiens ekko", "screen.pastbound.journal": "Relikviedagbog", "screen.pastbound.progress": "Genfundne minder: %s/%s", "screen.pastbound.help": "R dagbog   V væk", "screen.pastbound.ready": "KENDT", "screen.pastbound.unknown": "UKENDT", "screen.pastbound.riddle_hint": "Løs gåden", "screen.pastbound.close": "Esc for at lukke"},
    "nb_no": {"itemGroup.pastbound": "Pastbound: Historiens ekko", "screen.pastbound.journal": "Relikviedagbok", "screen.pastbound.progress": "Gjenfunne minner: %s/%s", "screen.pastbound.help": "R dagbok   V vekk", "screen.pastbound.ready": "KJENT", "screen.pastbound.unknown": "UKJENT", "screen.pastbound.riddle_hint": "Løs gåten", "screen.pastbound.close": "Esc for å lukke"},
    "fi_fi": {"itemGroup.pastbound": "Pastbound: Historian kaiut", "screen.pastbound.journal": "Reliikkipäiväkirja", "screen.pastbound.progress": "Palautetut muistot: %s/%s", "screen.pastbound.help": "R päiväkirja   V herätä", "screen.pastbound.ready": "TUNNETTU", "screen.pastbound.unknown": "TUNTEMATON", "screen.pastbound.riddle_hint": "Ratkaise arvoitus", "screen.pastbound.close": "Esc sulkee"},
    "cs_cz": {"itemGroup.pastbound": "Pastbound: Ozvěny dějin", "screen.pastbound.journal": "Deník relikvií", "screen.pastbound.progress": "Obnovené vzpomínky: %s/%s", "screen.pastbound.help": "R deník   V probudit", "screen.pastbound.ready": "ZNÁMÁ", "screen.pastbound.unknown": "NEZNÁMÁ", "screen.pastbound.riddle_hint": "Vyřeš hádanku", "screen.pastbound.close": "Esc pro zavření"},
    "sk_sk": {"itemGroup.pastbound": "Pastbound: Ozveny dejín", "screen.pastbound.journal": "Denník relikvií", "screen.pastbound.progress": "Obnovené spomienky: %s/%s", "screen.pastbound.help": "R denník   V prebudiť", "screen.pastbound.ready": "ZNÁMA", "screen.pastbound.unknown": "NEZNÁMA", "screen.pastbound.riddle_hint": "Vyrieš hádanku", "screen.pastbound.close": "Esc na zatvorenie"},
    "hu_hu": {"itemGroup.pastbound": "Pastbound: A történelem visszhangjai", "screen.pastbound.journal": "Ereklyenapló", "screen.pastbound.progress": "Visszaszerzett emlékek: %s/%s", "screen.pastbound.help": "R napló   V felébreszt", "screen.pastbound.ready": "ISMERT", "screen.pastbound.unknown": "ISMERETLEN", "screen.pastbound.riddle_hint": "Fejtsd meg a rejtvényt", "screen.pastbound.close": "Esc a bezáráshoz"},
    "ro_ro": {"itemGroup.pastbound": "Pastbound: Ecourile istoriei", "screen.pastbound.journal": "Jurnalul relicvelor", "screen.pastbound.progress": "Amintiri recuperate: %s/%s", "screen.pastbound.help": "R jurnal   V trezește", "screen.pastbound.ready": "CUNOSCUTĂ", "screen.pastbound.unknown": "NECUNOSCUTĂ", "screen.pastbound.riddle_hint": "Rezolvă ghicitoarea", "screen.pastbound.close": "Esc pentru închidere"},
    "bg_bg": {"itemGroup.pastbound": "Pastbound: Ехото на историята", "screen.pastbound.journal": "Дневник на реликвите", "screen.pastbound.progress": "Възстановени спомени: %s/%s", "screen.pastbound.help": "R дневник   V събуди", "screen.pastbound.ready": "ПОЗНАТА", "screen.pastbound.unknown": "НЕПОЗНАТА", "screen.pastbound.riddle_hint": "Реши загадката", "screen.pastbound.close": "Esc за затваряне"},
    "el_gr": {"itemGroup.pastbound": "Pastbound: Ηχώ της ιστορίας", "screen.pastbound.journal": "Ημερολόγιο κειμηλίων", "screen.pastbound.progress": "Ανακτημένες μνήμες: %s/%s", "screen.pastbound.help": "R ημερολόγιο   V αφύπνιση", "screen.pastbound.ready": "ΓΝΩΣΤΟ", "screen.pastbound.unknown": "ΑΓΝΩΣΤΟ", "screen.pastbound.riddle_hint": "Λύσε το αίνιγμα", "screen.pastbound.close": "Esc για κλείσιμο"},
    "zh_cn": {"itemGroup.pastbound": "Pastbound：历史回声", "screen.pastbound.journal": "遗物日志", "screen.pastbound.progress": "找回的记忆：%s/%s", "screen.pastbound.help": "R 日志   V 唤醒", "screen.pastbound.ready": "已知", "screen.pastbound.unknown": "未知", "screen.pastbound.riddle_hint": "解开谜语", "screen.pastbound.close": "按 Esc 关闭"},
    "zh_tw": {"itemGroup.pastbound": "Pastbound：歷史迴響", "screen.pastbound.journal": "遺物日誌", "screen.pastbound.progress": "找回的記憶：%s/%s", "screen.pastbound.help": "R 日誌   V 喚醒", "screen.pastbound.ready": "已知", "screen.pastbound.unknown": "未知", "screen.pastbound.riddle_hint": "解開謎語", "screen.pastbound.close": "按 Esc 關閉"},
    "ja_jp": {"itemGroup.pastbound": "Pastbound：歴史のこだま", "screen.pastbound.journal": "レリック日誌", "screen.pastbound.progress": "回収した記憶：%s/%s", "screen.pastbound.help": "R 日誌   V 覚醒", "screen.pastbound.ready": "既知", "screen.pastbound.unknown": "未知", "screen.pastbound.riddle_hint": "なぞなぞを解く", "screen.pastbound.close": "Escで閉じる"},
    "ko_kr": {"itemGroup.pastbound": "Pastbound: 역사의 메아리", "screen.pastbound.journal": "유물 일지", "screen.pastbound.progress": "되찾은 기억: %s/%s", "screen.pastbound.help": "R 일지   V 각성", "screen.pastbound.ready": "알려짐", "screen.pastbound.unknown": "미지", "screen.pastbound.riddle_hint": "수수께끼 풀기", "screen.pastbound.close": "Esc로 닫기"},
    "vi_vn": {"itemGroup.pastbound": "Pastbound: Tiếng vọng lịch sử", "screen.pastbound.journal": "Nhật ký di vật", "screen.pastbound.progress": "Ký ức phục hồi: %s/%s", "screen.pastbound.help": "R nhật ký   V đánh thức", "screen.pastbound.ready": "ĐÃ BIẾT", "screen.pastbound.unknown": "CHƯA BIẾT", "screen.pastbound.riddle_hint": "Giải câu đố", "screen.pastbound.close": "Esc để đóng"},
    "th_th": {"itemGroup.pastbound": "Pastbound: เสียงสะท้อนแห่งประวัติศาสตร์", "screen.pastbound.journal": "บันทึกโบราณวัตถุ", "screen.pastbound.progress": "ความทรงจำที่กู้คืน: %s/%s", "screen.pastbound.help": "R บันทึก   V ปลุก", "screen.pastbound.ready": "รู้แล้ว", "screen.pastbound.unknown": "ไม่รู้จัก", "screen.pastbound.riddle_hint": "ไขปริศนา", "screen.pastbound.close": "กด Esc เพื่อปิด"},
    "id_id": {"itemGroup.pastbound": "Pastbound: Gema Sejarah", "screen.pastbound.journal": "Jurnal Relik", "screen.pastbound.progress": "Kenangan dipulihkan: %s/%s", "screen.pastbound.help": "R jurnal   V bangunkan", "screen.pastbound.ready": "DIKENAL", "screen.pastbound.unknown": "TAK DIKENAL", "screen.pastbound.riddle_hint": "Pecahkan teka-teki", "screen.pastbound.close": "Esc untuk menutup"},
    "ar_sa": {"itemGroup.pastbound": "Pastbound: أصداء التاريخ", "screen.pastbound.journal": "سجل الآثار", "screen.pastbound.progress": "الذكريات المستعادة: %s/%s", "screen.pastbound.help": "R السجل   V إيقاظ", "screen.pastbound.ready": "معروف", "screen.pastbound.unknown": "مجهول", "screen.pastbound.riddle_hint": "حل اللغز", "screen.pastbound.close": "اضغط Esc للإغلاق"},
    "he_il": {"itemGroup.pastbound": "Pastbound: הדי ההיסטוריה", "screen.pastbound.journal": "יומן השרידים", "screen.pastbound.progress": "זיכרונות ששוחזרו: %s/%s", "screen.pastbound.help": "R יומן   V להעיר", "screen.pastbound.ready": "ידוע", "screen.pastbound.unknown": "לא ידוע", "screen.pastbound.riddle_hint": "פתור את החידה", "screen.pastbound.close": "Esc לסגירה"},
    "af_za": {"itemGroup.pastbound": "Pastbound: Eggo's van die geskiedenis", "screen.pastbound.journal": "Relikwiejoernaal", "screen.pastbound.progress": "Herwonne herinneringe: %s/%s", "screen.pastbound.help": "R joernaal   V wek", "screen.pastbound.ready": "BEKEND", "screen.pastbound.unknown": "ONBEKEND", "screen.pastbound.riddle_hint": "Los die raaisel op", "screen.pastbound.close": "Esc om te sluit"},
    "eo_uy": {"itemGroup.pastbound": "Pastbound: Eĥoj de historio", "screen.pastbound.journal": "Relikva taglibro", "screen.pastbound.progress": "Retrovitaj memoroj: %s/%s", "screen.pastbound.help": "R taglibro   V veki", "screen.pastbound.ready": "KONATA", "screen.pastbound.unknown": "NEKONATA", "screen.pastbound.riddle_hint": "Solvu la enigmon", "screen.pastbound.close": "Esc por fermi"},
    "ga_ie": {"itemGroup.pastbound": "Pastbound: Macalla na staire", "screen.pastbound.journal": "Dialann iarsmaí", "screen.pastbound.progress": "Cuimhní aimsithe: %s/%s", "screen.pastbound.help": "R dialann   V múscail", "screen.pastbound.ready": "AITHNITHE", "screen.pastbound.unknown": "ANAITHNID", "screen.pastbound.riddle_hint": "Réitigh an tomhas", "screen.pastbound.close": "Esc le dúnadh"},
    "cy_gb": {"itemGroup.pastbound": "Pastbound: Adleisiau hanes", "screen.pastbound.journal": "Dyddiadur creiriau", "screen.pastbound.progress": "Atgofion a adferwyd: %s/%s", "screen.pastbound.help": "R dyddiadur   V deffro", "screen.pastbound.ready": "GWYBOD", "screen.pastbound.unknown": "ANHYSBYS", "screen.pastbound.riddle_hint": "Datrys y pos", "screen.pastbound.close": "Esc i gau"}
}

for locale, add in metinler.items():
    veri = dict(ortak)
    veri.update(add)
    ad_kaynagi = relicler if locale == "tr_tr" else ingilizce_relikler
    for kimlik, ad in ad_kaynagi.items():
        veri[f"item.pastbound.{kimlik}"] = ad
        veri[f"tooltip.pastbound.{kimlik}"] = ad
        veri[f"history.pastbound.relic.{kimlik}.title"] = ad
        veri[f"history.pastbound.relic.{kimlik}.riddle"] = ingilizce_bilmeceler[kimlik]
        if locale == "tr_tr":
            veri[f"history.pastbound.relic.{kimlik}.title"] = relicler[kimlik]
            veri[f"history.pastbound.relic.{kimlik}.riddle"] = turkce_bilmeceler[kimlik]
    for kimlik, ad in yankilar.items():
        yankı_adı = ingilizce_yankilar[kimlik]
        if locale == "tr_tr":
            yankı_adı = ad
        veri[f"history.pastbound.echo.{kimlik}.name"] = yankı_adı
        veri[f"history.pastbound.echo.{kimlik}.hint"] = yankı_adı
        veri[f"history.pastbound.relic.{kimlik}.riddle"] = ad
        veri[f"advancement.pastbound.{kimlik}.title"] = yankı_adı
        veri[f"advancement.pastbound.{kimlik}.description"] = "Solve the historical echo trial to recover its memory."
    for kimlik, (ad, odak, aciklama) in time_duraklari.items():
        veri[f"history.pastbound.period.{kimlik}.name"] = ad
        veri[f"history.pastbound.period.{kimlik}.focus"] = odak
        veri[f"history.pastbound.period.{kimlik}.description"] = aciklama
        turkce_ad, turkce_odak, turkce_aciklama = turkce_time_duraklari[kimlik]
        if locale == "tr_tr":
            veri[f"history.pastbound.period.{kimlik}.name"] = turkce_ad
            veri[f"history.pastbound.period.{kimlik}.focus"] = turkce_odak
            veri[f"history.pastbound.period.{kimlik}.description"] = turkce_aciklama
        anlatim_ad = turkce_ad if locale == "tr_tr" else ad
        anlatim_odak = turkce_odak if locale == "tr_tr" else odak
        veri[f"history.pastbound.period.{kimlik}.dialogue_1"] = f"Ask about the first memory of {anlatim_ad}." if locale != "tr_tr" else f"{anlatim_ad} tarihinin ilk anısını sor."
        veri[f"history.pastbound.period.{kimlik}.dialogue_2"] = f"Ask how {anlatim_odak.lower()} changed the world." if locale != "tr_tr" else f"{anlatim_odak} dünyayı nasıl değiştirdiğini sor."
        veri[f"history.pastbound.period.{kimlik}.dialogue_3"] = "Ask what should be remembered today." if locale != "tr_tr" else "Bugün neyin hatırlanması gerektiğini sor."
        veri[f"advancement.pastbound.time_machine.{kimlik}.title"] = ad
        veri[f"advancement.pastbound.time_machine.{kimlik}.description"] = aciklama
    veri["advancement.pastbound.time_machine.complete_expedition.title"] = "Time Cartographer"
    veri["advancement.pastbound.time_machine.complete_expedition.description"] = "Explore every historical destination in the time machine."
    (lang / f"{locale}.json").write_text(json.dumps(veri, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
