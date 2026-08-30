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

ahsaplar = {
    "uruk_cedar_log": "Uruk Cedar Log",
    "uruk_cedar_stripped_log": "Stripped Uruk Cedar Log",
    "uruk_cedar_wood": "Uruk Cedar Wood",
    "uruk_cedar_stripped_wood": "Stripped Uruk Cedar Wood",
    "uruk_cedar_leaves": "Uruk Cedar Leaves",
    "uruk_cedar_sapling": "Uruk Cedar Sapling",
    "uruk_cedar_planks": "Uruk Cedar Planks",
    "uruk_cedar_slab": "Uruk Cedar Slab",
    "uruk_cedar_stairs": "Uruk Cedar Stairs",
    "uruk_cedar_door": "Uruk Cedar Door",
    "uruk_cedar_trapdoor": "Uruk Cedar Trapdoor",
    "uruk_cedar_fence": "Uruk Cedar Fence",
    "uruk_cedar_fence_gate": "Uruk Cedar Fence Gate",
    "uruk_cedar_pressure_plate": "Uruk Cedar Pressure Plate",
    "uruk_cedar_button": "Uruk Cedar Button",
    "chinampa_cypress_log": "Chinampa Cypress Log",
    "chinampa_cypress_stripped_log": "Stripped Chinampa Cypress Log",
    "chinampa_cypress_wood": "Chinampa Cypress Wood",
    "chinampa_cypress_stripped_wood": "Stripped Chinampa Cypress Wood",
    "chinampa_cypress_leaves": "Chinampa Cypress Leaves",
    "chinampa_cypress_sapling": "Chinampa Cypress Sapling",
    "chinampa_cypress_planks": "Chinampa Cypress Planks",
    "chinampa_cypress_slab": "Chinampa Cypress Slab",
    "chinampa_cypress_stairs": "Chinampa Cypress Stairs",
    "chinampa_cypress_door": "Chinampa Cypress Door",
    "chinampa_cypress_trapdoor": "Chinampa Cypress Trapdoor",
    "chinampa_cypress_fence": "Chinampa Cypress Fence",
    "chinampa_cypress_fence_gate": "Chinampa Cypress Fence Gate",
    "chinampa_cypress_pressure_plate": "Chinampa Cypress Pressure Plate",
    "chinampa_cypress_button": "Chinampa Cypress Button"
}

turkce_ahsaplar = {
    "uruk_cedar_log": "Uruk Sedir Kütüğü",
    "uruk_cedar_stripped_log": "Soyulmuş Uruk Sedir Kütüğü",
    "uruk_cedar_wood": "Uruk Sedir Ahşabı",
    "uruk_cedar_stripped_wood": "Soyulmuş Uruk Sedir Ahşabı",
    "uruk_cedar_leaves": "Uruk Sedir Yaprakları",
    "uruk_cedar_sapling": "Uruk Sedir Fidanı",
    "uruk_cedar_planks": "Uruk Sedir Tahtaları",
    "uruk_cedar_slab": "Uruk Sedir Basamağı",
    "uruk_cedar_stairs": "Uruk Sedir Merdiveni",
    "uruk_cedar_door": "Uruk Sedir Kapısı",
    "uruk_cedar_trapdoor": "Uruk Sedir Kapağı",
    "uruk_cedar_fence": "Uruk Sedir Çiti",
    "uruk_cedar_fence_gate": "Uruk Sedir Çit Kapısı",
    "uruk_cedar_pressure_plate": "Uruk Sedir Basınç Plakası",
    "uruk_cedar_button": "Uruk Sedir Düğmesi",
    "chinampa_cypress_log": "Chinampa Servi Kütüğü",
    "chinampa_cypress_stripped_log": "Soyulmuş Chinampa Servi Kütüğü",
    "chinampa_cypress_wood": "Chinampa Servi Ahşabı",
    "chinampa_cypress_stripped_wood": "Soyulmuş Chinampa Servi Ahşabı",
    "chinampa_cypress_leaves": "Chinampa Servi Yaprakları",
    "chinampa_cypress_sapling": "Chinampa Servi Fidanı",
    "chinampa_cypress_planks": "Chinampa Servi Tahtaları",
    "chinampa_cypress_slab": "Chinampa Servi Basamağı",
    "chinampa_cypress_stairs": "Chinampa Servi Merdiveni",
    "chinampa_cypress_door": "Chinampa Servi Kapısı",
    "chinampa_cypress_trapdoor": "Chinampa Servi Kapağı",
    "chinampa_cypress_fence": "Chinampa Servi Çiti",
    "chinampa_cypress_fence_gate": "Chinampa Servi Çit Kapısı",
    "chinampa_cypress_pressure_plate": "Chinampa Servi Basınç Plakası",
    "chinampa_cypress_button": "Chinampa Servi Düğmesi"
}

biome_adlari = {
    "uruk_floodplain": ("Uruk Floodplain", "Uruk Taşkın Ovası"),
    "tenochtitlan_chinampa": ("Tenochtitlan Chinampa Wetlands", "Tenochtitlan Chinampa Sulak Alanları")
}

celik_esyalari = {
    "raw_steel": ("Raw Steel", "Ham Çelik"),
    "steel_ingot": ("Steel Ingot", "Çelik Külçe"),
    "steel_plate": ("Steel Plate", "Çelik Levha")
}

celik_bloklari = {
    "steel_ore": ("Steel Ore", "Çelik Cevheri"),
    "deepslate_steel_ore": ("Deepslate Steel Ore", "Derin Kayrak Çelik Cevheri"),
    "steel_block": ("Steel Block", "Çelik Bloğu"),
    "historical_forge": ("Historical Forge", "Tarihî Ocak")
}

maden_bloklari = {
    "time_stone_ore": ("Time Stone Ore", "Zaman Taşı Cevheri"),
    "nether_time_stone_ore": ("Nether Time Stone Ore", "Nether Zaman Taşı Cevheri"),
    "chronicle_ore": ("Chronicle Ore", "Kronik Cevheri"),
    "ash_chronicle_ore": ("Ash Chronicle Ore", "Kül Kronik Cevheri"),
    "end_echo_ore": ("End Echo Ore", "End Yankı Cevheri"),
    "void_chronicle_ore": ("Void Chronicle Ore", "Boşluk Kronik Cevheri")
}

ingilizce_etkinlikler = {
    "rossetta_tasi": "Recover five clay fragments and align the three language marks.",
    "gilgamesh_tableti": "Rebuild the broken tablet and place the friend’s seal at its center.",
    "anubis_ankhi": "Light five river lamps and return the life symbol to the gate.",
    "minos_labirent_muhru": "Follow the changing corridor and restore the seal at the quiet center.",
    "roma_aureusu": "Gather the forum tokens and repair the merchant’s worn coin die.",
    "viking_gunes_pusulasi": "Track the sunlit stones and turn the compass toward the northern star.",
    "samuray_kabzasi": "Collect the fallen crest pieces and set the hilt beside the training banner.",
    "maya_gunes_carki": "Rotate the calendar stones until the four seasonal marks face the sun.",
    "inka_quipusu": "Restore five colored cords and hang them on the quipu counting frame.",
    "harappa_muhru": "Clear the drain markers and press the recovered seal into wet clay.",
    "song_porseleni": "Collect kiln shards and fire the missing blue-white rim in the workshop.",
    "benin_bronzu": "Recover five bronze faces and place them on the palace memory wall.",
    "aztek_gunes_tasi": "Carry the four age markers to the sun stone and awaken its fifth ring.",
    "abbasid_murekkebi": "Mix the ink, copy five lines and return the finished folio to the scribe.",
    "ronesans_astrolabi": "Fit the brass rings and calibrate the astrolabe against the evening star.",
    "antikithera_duzenegi": "Collect the loose gears and place the lunar wheel back in the mechanism.",
    "catalhoyuk_boncugu": "Restore five colored beads and return them to the shared hearth alcove.",
    "bizans_mozaigi": "Collect mosaic fragments and complete the border around the central figure.",
    "timbuktu_kalemi": "Recover five manuscript leaves and bind them beside the caravan map.",
    "apollo17_armasi": "Repair the mission patch display and mark the route beside the lunar module.",
    "ilhanli_madalyonu": "Restore the relay seals and place the medallion on the caravan ledger.",
    "polinezya_yildiz_haritasi": "Gather five star marks and set the island route on the canoe chart.",
    "mali_tuz_muhru": "Carry salt sacks to the caravan store and stamp the recovered trade seal.",
    "iskandinav_runetasi": "Brush the rune stone clean and restore the missing edge inscription.",
}

turkce_etkinlikler = {
    "rossetta_tasi": "Beş kil parçasını bul ve üç dil işaretini hizala.",
    "gilgamesh_tableti": "Kırık tableti onar ve dostun mührünü merkezine yerleştir.",
    "anubis_ankhi": "Beş nehir kandilini yak ve yaşam işaretini kapıya geri götür.",
    "minos_labirent_muhru": "Değişen koridoru izle ve sessiz merkezdeki mührü onar.",
    "roma_aureusu": "Forum jetonlarını topla ve tüccarın yıpranmış sikke kalıbını onar.",
    "viking_gunes_pusulasi": "Güneş taşlarını takip et ve pusulayı kuzey yıldızına çevir.",
    "samuray_kabzasi": "Düşen arma parçalarını topla ve kabzayı talim sancağının yanına koy.",
    "maya_gunes_carki": "Takvim taşlarını dört mevsim işareti Güneş’e bakana kadar döndür.",
    "inka_quipusu": "Beş renkli ipi onar ve quipu sayım çerçevesine as.",
    "harappa_muhru": "Drenaj işaretlerini temizle ve bulunan mührü yaş kile bastır.",
    "song_porseleni": "Fırın parçalarını topla ve eksik mavi-beyaz kenarı atölyede pişir.",
    "benin_bronzu": "Beş bronz yüzü bul ve saray hafıza duvarına yerleştir.",
    "aztek_gunes_tasi": "Dört çağ işaretini Güneş taşına taşı ve beşinci halkayı uyandır.",
    "abbasid_murekkebi": "Mürekkebi karıştır, beş satırı kopyala ve bitmiş sayfayı kâtibe götür.",
    "ronesans_astrolabi": "Pirinç halkaları tak ve astrolabı akşam yıldızına göre ayarla.",
    "antikithera_duzenegi": "Gevşek dişlileri topla ve Ay çarkını düzeneğe geri yerleştir.",
    "catalhoyuk_boncugu": "Beş renkli boncuğu onar ve ortak ocak nişine geri koy.",
    "bizans_mozaigi": "Mozaik parçalarını topla ve merkez figürün çevresindeki bordürü tamamla.",
    "timbuktu_kalemi": "Beş el yazması yaprağını bul ve kervan haritasının yanında ciltle.",
    "apollo17_armasi": "Görev arması sergisini onar ve Ay aracının yanındaki rotayı işaretle.",
    "ilhanli_madalyonu": "Menzil mühürlerini onar ve madalyonu kervan defterine koy.",
    "polinezya_yildiz_haritasi": "Beş yıldız işaretini topla ve ada rotasını kano haritasına yerleştir.",
    "mali_tuz_muhru": "Tuz çuvallarını kervan ambarına taşı ve ticaret mührünü bas.",
    "iskandinav_runetasi": "Rune taşını temizle ve eksik kenar yazısını onar."
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
    "ipek_yolu_kervansarayi": ("Silk Road Caravanserai", "Exchange between cultures", "Listen to languages, fabrics, recipes and ideas meet in one courtyard as travelers transform trade into a network of shared memory."),
    "epidaurum_tiyatrosu": ("Epidaurum Theater", "Stone, stage and healing", "Explore a living echo of theater, harbor life and the healing tradition of Asklepios on the Adriatic coast.")
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
    "ipek_yolu_kervansarayi": ("İpek Yolu Kervansarayı", "Kültürler arası değişim", "Tek bir avluda dillerin, kumaşların, tariflerin ve fikirlerin buluşmasını dinle; ticaretin ortak hafızaya dönüşmesine tanık ol."),
    "epidaurum_tiyatrosu": ("Epidaurum Tiyatrosu", "Taş, sahne ve şifa", "Adriyatik kıyısında tiyatronun, liman yaşamının ve Asklepios geleneğinin yaşayan yankısını araştır.")
}

gorevler_en = {
    "uruk_yazi_evi": "Speak with the scribe, cross the canal markers, return to the beacon and break the clay ledger monument.",
    "termopil_savasi": "Speak with the scout, cross both ridge markers, return to the beacon and break the stone oath monument.",
    "iskenderiye_kutuphanesi": "Speak with the librarian, walk the scroll gallery, return to the beacon and break the bookshelf catalogue monument.",
    "bagdat_pili_atolyesi": "Speak with the craftsperson, mine three steel veins, smelt an ingot, craft a plate and repair the forge.",
    "antikithera_limani": "Speak with the navigator, cross the harbor markers, return to the beacon and break the polished sky-calculation monument.",
    "bagdat_bilgi_evi": "Speak with the translator, cross the study courtyard, return to the beacon and break the chiseled knowledge monument.",
    "timbuktu_el_yazmalari": "Speak with the scholar, cross the caravan markers, return to the beacon and break the sandstone manuscript monument.",
    "tenochtitlan_gecidi": "Speak with the farmer, cross the canal path, return to the beacon and break the prismarine calendar monument.",
    "polinezya_yildiz_yolu": "Speak with the navigator, cross the star markers, return to the beacon and break the lapis route monument.",
    "catalhoyuk_yerleskesi": "Speak with the elder, cross the shared-house markers, return to the beacon and break the terracotta hearth monument.",
    "apollo_ay_istigi": "Speak with the astronaut, cross the lunar markers, return to the beacon and break the iron mission monument.",
    "ipek_yolu_kervansarayi": "Speak with the merchant, cross the caravan yard, return to the beacon and break the red sandstone exchange monument.",
    "epidaurum_tiyatrosu": "Speak with the actor, cross the theater tiers, return to the beacon and break the calcite healing-stage monument."
}

gorevler_tr = {
    "uruk_yazi_evi": "Kâtip ile konuş, kanal işaretlerini geç, işarete dön ve kil kayıt anıtını kır.",
    "termopil_savasi": "Gözcü ile konuş, iki sırt işaretini geç, işarete dön ve taş yemin anıtını kır.",
    "iskenderiye_kutuphanesi": "Kütüphaneci ile konuş, tomar galerisini geç, işarete dön ve kitaplık katalog anıtını kır.",
    "bagdat_pili_atolyesi": "Zanaatkâr ile konuş, üç çelik damarını kaz, külçe erit, levha üret ve ocağı onar.",
    "antikithera_limani": "Denizci ile konuş, liman işaretlerini geç, işarete dön ve cilalı gök hesabı anıtını kır.",
    "bagdat_bilgi_evi": "Çevirmen ile konuş, çalışma avlusunu geç, işarete dön ve yontulmuş bilgi anıtını kır.",
    "timbuktu_el_yazmalari": "Bilgin ile konuş, kervan işaretlerini geç, işarete dön ve kumtaşı el yazması anıtını kır.",
    "tenochtitlan_gecidi": "Çiftçi ile konuş, kanal yolunu geç, işarete dön ve prizmarin takvim anıtını kır.",
    "polinezya_yildiz_yolu": "Denizci ile konuş, yıldız işaretlerini geç, işarete dön ve lapis rota anıtını kır.",
    "catalhoyuk_yerleskesi": "Yaşlı ile konuş, ortak ev işaretlerini geç, işarete dön ve pişmiş toprak ocak anıtını kır.",
    "apollo_ay_istigi": "Astronot ile konuş, Ay işaretlerini geç, işarete dön ve demir görev anıtını kır.",
    "ipek_yolu_kervansarayi": "Tüccar ile konuş, kervan avlusunu geç, işarete dön ve kızıl kumtaşı değişim anıtını kır.",
    "epidaurum_tiyatrosu": "Oyuncu ile konuş, tiyatro basamaklarını geç, işarete dön ve kalsit şifa-sahne anıtını kır."
}

ekosistem_en = {
    "uruk_yazi_evi": "Floodplain archaeology: Time Stone glints under stone near cedar groves.",
    "termopil_savasi": "Mountain archaeology: Chronicle ore hides in dark stone beneath the pass.",
    "iskenderiye_kutuphanesi": "Harbor archaeology: Chronicle ore marks old foundations near water.",
    "bagdat_pili_atolyesi": "Workshop ecology: steel veins and Time Stone connect metal to early electricity.",
    "antikithera_limani": "Island archaeology: End Echo lore is mirrored by bronze and sea routes.",
    "bagdat_bilgi_evi": "Scholarship ecology: Chronicle ore records the cost of preserving a page.",
    "timbuktu_el_yazmalari": "Caravan ecology: ash-colored chronicle traces follow trade routes.",
    "tenochtitlan_gecidi": "Chinampa ecology: cypress wetlands and Time Stone channels share one calendar.",
    "polinezya_yildiz_yolu": "Ocean ecology: star routes point toward rare chronicle deposits.",
    "catalhoyuk_yerleskesi": "Settlement ecology: earth, hearths and buried chronicle traces preserve community life.",
    "apollo_ay_istigi": "Lunar ecology: End Echo ore glows where the sky becomes a destination.",
    "ipek_yolu_kervansarayi": "Exchange ecology: rare chronicle veins cross the routes that carried ideas.",
    "epidaurum_tiyatrosu": "Adriatic archaeology: Chronicle ore marks the theater foundations and healing courtyards of Epidaurum."
}

ekosistem_tr = {
    "uruk_yazi_evi": "Taşkın ovası arkeolojisi: Sedir kümelerinin yakınındaki taşlarda Zaman Taşı parlar.",
    "termopil_savasi": "Dağ arkeolojisi: Geçidin altındaki karanlık taşlarda Kronik Cevheri saklanır.",
    "iskenderiye_kutuphanesi": "Liman arkeolojisi: Suya yakın eski temeller Kronik Cevheri taşır.",
    "bagdat_pili_atolyesi": "Atölye ekolojisi: Çelik damarları ve Zaman Taşı erken elektrik fikrini bağlar.",
    "antikithera_limani": "Ada arkeolojisi: End Yankısı, bronz ve deniz rotalarında karşılık bulur.",
    "bagdat_bilgi_evi": "Bilgi ekolojisi: Kronik Cevheri bir sayfayı korumanın bedelini kaydeder.",
    "timbuktu_el_yazmalari": "Kervan ekolojisi: Kül renkli kronik izleri ticaret yollarını takip eder.",
    "tenochtitlan_gecidi": "Chinampa ekolojisi: Servi sulak alanları ve Zaman Taşı kanalları aynı takvimi paylaşır.",
    "polinezya_yildiz_yolu": "Okyanus ekolojisi: Yıldız rotaları nadir kronik yataklarını gösterir.",
    "catalhoyuk_yerleskesi": "Yerleşim ekolojisi: Toprak, ocak ve gömülü kronik izleri ortak yaşamı korur.",
    "apollo_ay_istigi": "Ay ekolojisi: Gökyüzü hedefe dönüştüğünde End Yankısı cevheri parlar.",
    "ipek_yolu_kervansarayi": "Değişim ekolojisi: Nadir kronik damarları fikirleri taşıyan yolları keser.",
    "epidaurum_tiyatrosu": "Adriyatik arkeolojisi: Kronik cevheri Epidaurum tiyatrosunun ve şifa avlularının temellerini işaretler."
}

konusmalar_en = {
    "uruk_yazi_evi": ("Why are the clay marks counted?", "A grain mark becomes a promise when a whole city can read it.", "What should the first scribes teach us?"),
    "termopil_savasi": ("Why does the pass matter?", "A narrow road can make terrain part of a strategy.", "What remains after the battle is remembered?"),
    "iskenderiye_kutuphanesi": ("Why gather so many scrolls?", "A harbor brings languages together before ideas can travel farther.", "How does a fragile page outlive a palace?"),
    "bagdat_pili_atolyesi": ("What is inside the vessel?", "Copper, iron and an acidic liquid produce a small but intriguing experiment; the evidence deserves careful questions.", "Why repair a device whose purpose is uncertain?"),
    "antikithera_limani": ("What do the gears follow?", "The makers turn observations of the sky into a compact mechanical calendar.", "What does patient craft add to astronomy?"),
    "bagdat_bilgi_evi": ("Who is translating this page?", "Greek, Persian, Syriac and Arabic learning meet through patient translation and debate.", "How does a question become science?"),
    "timbuktu_el_yazmalari": ("Why protect these manuscripts?", "A caravan can carry mathematics, law and poetry across a desert.", "What keeps a school alive when the road moves?"),
    "tenochtitlan_gecidi": ("How do the chinampas feed the city?", "Raised fields, canals and careful calendars turn lake water into food and time.", "What do the causeways connect besides neighborhoods?"),
    "polinezya_yildiz_yolu": ("How do you steer without a drawn map?", "Stars, swells, winds and birds form a living chart remembered by navigators.", "What makes a distant island recognizable?"),
    "catalhoyuk_yerleskesi": ("Why are the homes joined together?", "Shared roofs, hearths and rituals make a settlement a network of memory.", "What does an ordinary room reveal about a community?"),
    "apollo_ay_istigi": ("What was the first task on the surface?", "A checklist, a partner and a sample turned a short walk into lasting science.", "Why does one footprint matter to history?"),
    "ipek_yolu_kervansarayi": ("Who can rest in this courtyard?", "Merchants exchange languages, goods and techniques as well as silver.", "How does a road become a shared memory?"),
    "epidaurum_tiyatrosu": ("Why build a theater beside a harbor?", "Performance, healing and public life can turn stone into a shared civic memory.", "What survives when the audience is gone?")
}

konusmalar_tr = {
    "uruk_yazi_evi": ("Kil işaretleri neden sayılıyor?", "Bir tahıl işareti bütün şehir okuyabildiğinde söze ve vaade dönüşür.", "İlk kâtipler bize ne öğretmeli?"),
    "termopil_savasi": ("Bu geçit neden önemli?", "Dar bir yol, araziyi stratejinin bir parçasına dönüştürebilir.", "Savaştan sonra geriye ne kalır?"),
    "iskenderiye_kutuphanesi": ("Neden bu kadar tomar toplanıyor?", "Bir liman, fikirler daha uzağa gitmeden önce dilleri bir araya getirir.", "Kırılgan bir sayfa saraydan nasıl uzun yaşar?"),
    "bagdat_pili_atolyesi": ("Kabın içinde ne var?", "Bakır, demir ve asitli bir sıvı küçük ama ilginç bir deney oluşturuyor; kanıt dikkatli soruları hak ediyor.", "Amacı belirsiz bir düzeneği neden onarmalıyız?"),
    "antikithera_limani": ("Dişli çarklar neyi izliyor?", "Ustalar gök gözlemlerini küçük bir mekanik takvime dönüştürüyor.", "Sabırlı zanaat gökbilime ne katar?"),
    "bagdat_bilgi_evi": ("Bu sayfayı kim çeviriyor?", "Yunanca, Farsça, Süryanice ve Arapça bilgi sabırlı çeviri ve tartışmayla buluşuyor.", "Bir soru nasıl bilime dönüşür?"),
    "timbuktu_el_yazmalari": ("Bu el yazmaları neden korunuyor?", "Bir kervan matematiği, hukuku ve şiiri çölün ötesine taşıyabilir.", "Yol değişirken bir okul nasıl yaşar?"),
    "tenochtitlan_gecidi": ("Chinampa tarlaları şehri nasıl besliyor?", "Yükseltilmiş tarlalar, kanallar ve dikkatli takvim göl suyunu yiyeceğe ve zamana çeviriyor.", "Geçitler mahallelerden başka neyi bağlıyor?"),
    "polinezya_yildiz_yolu": ("Çizilmiş harita olmadan nasıl yön bulursun?", "Yıldızlar, dalgalar, rüzgârlar ve kuşlar denizcilerin hatırladığı canlı bir harita oluşturur.", "Uzak bir ada nasıl tanınır?"),
    "catalhoyuk_yerleskesi": ("Evler neden birbirine bağlı?", "Ortak çatılar, ocaklar ve törenler yerleşimi bir hafıza ağına dönüştürür.", "Sıradan bir oda topluluk hakkında ne anlatır?"),
    "apollo_ay_istigi": ("Yüzeydeki ilk görev neydi?", "Bir kontrol listesi, bir yol arkadaşı ve bir örnek kısa yürüyüşü kalıcı bilime dönüştürdü.", "Tek bir ayak izi tarih için neden önemli?"),
    "ipek_yolu_kervansarayi": ("Bu avluda kim dinlenebilir?", "Tüccarlar gümüşün yanında dilleri, malları ve teknikleri de değiş tokuş eder.", "Bir yol nasıl ortak hafızaya dönüşür?"),
    "epidaurum_tiyatrosu": ("Limanın yanına neden tiyatro kuruldu?", "Gösteri, şifa ve kamusal yaşam taşı ortak bir kent hafızasına dönüştürebilir.", "Seyirci gittikten sonra ne kalır?")
}

ortak = {
    "block.pastbound.echo_archive": "Echo Archive",
    "advancement.pastbound.portable_furnace.title": "A Hearth in the Field",
    "advancement.pastbound.portable_furnace.description": "Complete a real smelt with the Furnace on a Stick.",
    "advancement.pastbound.portable_furnace_master.title": "The Fast Kiln",
    "advancement.pastbound.portable_furnace_master.description": "Complete a smelt with the enhanced Furnace on a Stick.",
    "advancement.pastbound.portable_crafting.title": "Workshop Without Walls",
    "advancement.pastbound.portable_crafting.description": "Open a full 3×3 crafting table wherever your expedition leads.",
    "advancement.pastbound.connected_archive.title": "The Archive Network",
    "advancement.pastbound.connected_archive.description": "Send historical materials into a connected Ancient Storage archive.",
    "block.pastbound.resonance_pillar": "Resonance Pillar",
    "item.pastbound.echo_shard": "Echo Shard",
    "item.pastbound.memory_lens": "Memory Lens",
    "item.pastbound.chronicle_compass": "Chronicle Compass",
    "message.pastbound.compass.none": "The Chronicle Compass finds no historical ore nearby.",
    "message.pastbound.compass.found": "%s is %s blocks away at %s, %s, %s.",
    "tooltip.pastbound.relic.power": "Ability: %s",
    "tooltip.pastbound.portable_furnace": "Portable smelting: insert an ingredient and fuel, then close the menu to return unfinished stacks.",
    "tooltip.pastbound.portable_furnace_master": "Enhanced tier: cooks at twice the normal portable-furnace speed.",
    "tooltip.pastbound.portable_crafting": "Portable 3×3 workshop for field fabrication.",
    "tooltip.pastbound.relic.activity": "Restoration activity: %s",
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
    "message.pastbound.relic.unknown": "The relic remains unknown. Restoration activity: %s",
    "message.pastbound.relic.fragment_already": "That echo fragment is already restored.",
    "message.pastbound.relic.fragment_progress": "Echo restoration: %s/%s fragments recovered.",
    "message.pastbound.relic.fragment_complete": "Relic fully restored: %s.",
    "message.pastbound.relic.knowledge": "Historical memory added: %s",
    "message.pastbound.relic.identified": "You spent experience and identified %s.",
    "message.pastbound.relic.xp_hint": "Hold Shift and use it with at least %s XP levels to identify it.",
    "message.pastbound.relic.activated": "%s answers your call.",
    "message.pastbound.journal_count": "Historical memories recovered: %s/%s",
    "screen.pastbound.progress": "Memories recovered: %s/%s",
    "screen.pastbound.help": "R journal   V awaken",
    "screen.pastbound.ready": "KNOWN",
    "screen.pastbound.unknown": "UNKNOWN",
    "screen.pastbound.close": "Esc to close",
    "screen.pastbound.page": "Page %s/%s",
    "screen.pastbound.page_hint": "Left/Right: change page   Click: inspect relic",
    "key.pastbound.journal": "Open Relic Journal",
    "key.pastbound.activate": "Awaken equipped relic",
    "key.pastbound.take_control": "Take control of the historical scene",
    "key.pastbound.language": "Open Pastbound language menu",
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
    "screen.pastbound.slot_hint": "U: exchange 10 Netherite Blocks for 2 relic slots",
    "screen.pastbound.time_machine": "Time Machine",
    "screen.pastbound.time_machine.subtitle": "Choose a historical place to explore its living echo.",
    "screen.pastbound.time_machine.hint": "Click a destination or press 1-9",
    "screen.pastbound.time_machine.sent": "Temporal route prepared",
    "screen.pastbound.time_machine.cost": "Travel costs 1 Time Stone",
    "screen.pastbound.time_machine.preview": "Mission preview: %s",
    "screen.pastbound.time_machine.destinations": "Destinations",
    "screen.pastbound.time_machine.selected": "Selected destination",
    "screen.pastbound.time_machine.mission": "Mission",
    "screen.pastbound.time_machine.route": "ROUTE %s",
    "screen.pastbound.time_machine.inventory": "Time Stones: %s",
    "screen.pastbound.time_machine.archive": "Chronicle route %s/%s",
    "screen.pastbound.time_machine.page": "Page %s/%s",
    "screen.pastbound.time_machine.previous": "Previous",
    "screen.pastbound.time_machine.next": "Next",
    "screen.pastbound.time_machine.travel": "Prepare route",
    "screen.pastbound.time_machine.confirm": "Confirm travel",
    "screen.pastbound.time_machine.confirm_hint": "Review the mission, then confirm to enter the living reconstruction.",
    "screen.pastbound.time_machine.close_hint": "Esc: cancel or close",
    "screen.pastbound.time_machine.time_stone_missing": "No Time Stone in inventory",
    "message.pastbound.ancient_storage.deposited": "Archived %s historical items.",
    "message.pastbound.ancient_storage.nothing_to_archive": "No Pastbound research items were ready to archive.",
    "screen.pastbound.ancient_storage.hint": "Sneak-right-click with an empty hand to archive research items and sort the vault.",
    "message.pastbound.echo.discovered": "Historical echo recovered: %s",
    "message.pastbound.echo.trace": "Trace recorded: %s",
    "message.pastbound.echo.bad_name": "That echo is not in the chronicle.",
    "message.pastbound.echo.already": "The echo of %s is already recorded.",
    "message.pastbound.echo.wrong": "The echo drifts away. Hint: %s",
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
    "screen.pastbound.relic.activity": "Restore the echo: recover five fragments by exploring its historical memory.",
    "screen.pastbound.relic.fragments": "Restored fragments: %s/%s",
    "screen.pastbound.relic.fragment": "Fragment %s",
    "screen.pastbound.relic.fragment_done": "Restored",
    "screen.pastbound.relic.activity_hint": "Click fragments or press 1-5. Each fragment is independent.",
    "screen.pastbound.language.title": "Pastbound Language",
    "screen.pastbound.language.choose": "Choose the language for Pastbound interfaces",
    "screen.pastbound.language.english": "English",
    "screen.pastbound.language.turkish": "Türkçe",
    "screen.pastbound.language.default": "Default language: English",
    "screen.pastbound.language.close": "Esc: close",
    "message.pastbound.scene.quest": "Active mission: speak with one witness, walk beyond the outer ring, return to the beacon, then break the marked archive monument.",
    "message.pastbound.scene.quest_period": "Active mission: %s",
    "message.pastbound.scene.quest_artifact": "The marked archive monument breaks open. Its memory is now part of the historical record.",
    "message.pastbound.scene.quest_inspect": "The Memory Lens reveals the monument’s hidden layer. Its story is ready to be restored.",
    "message.pastbound.mission.steel_start": "Workshop mission: mine the three steel veins, smelt raw steel into an ingot, craft a steel plate, then repair the historical forge.",
    "message.pastbound.mission.steel_mined": "The three steel veins are open. Smelt the raw steel in a furnace inside this historical workshop.",
    "message.pastbound.mission.steel_vein": "Steel vein %s/%s recovered. Keep mining the marked veins.",
    "message.pastbound.mission.already_repaired": "This historical forge is already repaired.",
    "message.pastbound.mission.steel_smelted": "The ingot holds. Craft a steel plate and bring it to the historical forge.",
    "message.pastbound.mission.plate_ready": "The plate is ready. Use it on the orange-marked historical forge to restore the device.",
    "message.pastbound.mission.forge_locked": "The forge is cold. Mine steel, smelt an ingot and craft a steel plate first.",
    "message.pastbound.mission.plate_needed": "Bring one Steel Plate to repair this historical forge.",
    "message.pastbound.mission.forge_repaired": "The Baghdad Battery workshop hums again. The repaired device records a new echo.",
    "message.pastbound.scene.quest_explore": "Mission active: speak with every witness, walk beyond the outer ring, and return to the central beacon.",
    "message.pastbound.scene.quest_talk": "Witness %s has shared a memory. Continue the conversation route.",
    "message.pastbound.scene.waypoint_need_talk": "You reached the field station. Speak with its specialist before moving on.",
    "message.pastbound.scene.waypoint_locked": "Reach this field station before you can question the specialist.",
    "message.pastbound.scene.waypoint_talk": "Field station %s recorded: the evidence is added to your expedition log.",
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
    "message.pastbound.world.power_active": "The electrical circuit is alive. The village echo has been recorded in the archive.",
    "block.pastbound.ancient_storage": "Ancient Storage",
    "item.pastbound.ancient_storage": "Ancient Storage",
    "container.pastbound.ancient_storage": "Ancient Storage Archive",
    "message.pastbound.period.unique_progress": "Period-specific evidence %s/%s complete. Keep searching for the next field clue.",
    "message.pastbound.period.unique_complete": "The unique field objective for %s is complete. Finish the full historical quest chain.",
    "message.pastbound.world.mine_reached": "You reached the mine. The steel veins are here; recover three of them.",
    "message.pastbound.world.steel_found": "Steel ore %s/%s recovered.",
    "entity.pastbound.village.archivist": "Village Archivist",
    "message.pastbound.scene.station_used": "Field station %s used. Speak with its specialist before moving to the next station.",
    "entity.pastbound.scene.archaeologist": "Field Archaeologist",
    "entity.pastbound.scene.miner": "Ore Surveyor",
    "entity.pastbound.scene.engineer": "Power Engineer",
    "message.pastbound.scene.quest_perimeter": "Perimeter discovered. The landscape preserves another piece of the story.",
    "message.pastbound.scene.quest_return": "You returned to the beacon. The scene is ready to reveal its final memory.",
    "message.pastbound.scene.quest_complete": "Historical mission complete. Two Chronicle Scraps were added to your archive.",
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
    "screen.pastbound.scene.door_open": "The cedar gate opens for the living scene",
    "screen.pastbound.scene.door_closed": "The cedar gate closes around the preserved memory",
    "screen.pastbound.scene.task_active": "Active field task: talk to a witness, cross the outer ring, return to the beacon, then break the marked archive monument.",
    "screen.pastbound.scene.task_steel": "Workshop task: mine three steel veins, smelt an ingot, craft a plate, and repair the historical forge.",
    "screen.pastbound.dialogue.title": "A Conversation with History",
    "screen.pastbound.dialogue.choose": "Choose a question with 1, 2, or 3",
    "screen.pastbound.dialogue.waiting": "The witness is remembering...",
    "screen.pastbound.dialogue.response": "Witness response",
    "entity.pastbound.scene.narrator": "Historical Narrator",
    "entity.pastbound.scene.craftsman": "Craftsperson",
    "entity.pastbound.scene.witness": "Witness",
    "entity.pastbound.scene.scribe": "Scribe",
}

metinler = {
    "en_us": {"itemGroup.pastbound": "Pastbound: Echoes of History", "screen.pastbound.journal": "Relic Journal"},
    "tr_tr": {"itemGroup.pastbound": "Pastbound: Tarihin Yankıları", "screen.pastbound.journal": "Relik Günlüğü", "key.pastbound.take_control": "Tarihî sahnenin kontrolünü al", "key.pastbound.language": "Pastbound dil menüsünü aç", "key.pastbound.relic_slot_1": "Relic yuvası 1’i etkinleştir", "key.pastbound.relic_slot_2": "Relic yuvası 2’yi etkinleştir", "key.pastbound.relic_slot_3": "Relic yuvası 3’ü etkinleştir", "key.pastbound.relic_slot_4": "Relic yuvası 4’ü etkinleştir", "key.pastbound.relic_slot_5": "Relic yuvası 5’i etkinleştir", "key.pastbound.relic_slot_6": "Relic yuvası 6’yı etkinleştir", "key.pastbound.relic_slot_7": "Relic yuvası 7’yi etkinleştir", "key.pastbound.relic_slot_8": "Relic yuvası 8’i etkinleştir", "key.pastbound.relic_slot_9": "Relic yuvası 9’u etkinleştir", "key.pastbound.relic_slot_10": "Relic yuvası 10’u etkinleştir", "block.pastbound.echo_archive": "Yankı Arşivi", "advancement.pastbound.portable_furnace.title": "Sahada Bir Ocak", "advancement.pastbound.portable_furnace.description": "Çubukta Fırın ile gerçekten bir eşya erit.", "advancement.pastbound.portable_furnace_master.title": "Hızlı Ocak", "advancement.pastbound.portable_furnace_master.description": "Geliştirilmiş Çubukta Fırın ile bir eşya erit.", "advancement.pastbound.portable_crafting.title": "Duvarsız Atölye", "advancement.pastbound.portable_crafting.description": "Keşif yolun nereye giderse gitsin tam 3×3 üretim masasını aç.", "advancement.pastbound.connected_archive.title": "Arşiv Ağı", "advancement.pastbound.connected_archive.description": "Tarihî malzemeleri bağlı bir Kadim Depo arşivine gönder.", "block.pastbound.resonance_pillar": "Rezonans Sütunu", "item.pastbound.echo_shard": "Yankı Parçası", "item.pastbound.memory_lens": "Hafıza Merceği", "item.pastbound.chronicle_scrap": "Kronik Parçası", "item.pastbound.history_ink": "Tarih Mürekkebi", "item.pastbound.time_stone": "Zaman Taşı", "item.pastbound.echo_seal": "Yankı Mührü", "item.pastbound.zaman_makinesi": "Zaman Makinesi", "item.pastbound.firin_cubugu": "Çubukta Fırın", "effect.pastbound.tarih_yankisi": "Tarih Yankısı",   "screen.pastbound.slot_hint": "U: 10 Netherite Block karşılığında 2 relic yuvası aç", "screen.pastbound.time_machine": "Zaman Makinesi", "screen.pastbound.time_machine.subtitle": "Yaşayan yankısını keşfetmek için tarihî bir durak seç.", "screen.pastbound.time_machine.hint": "Bir durağa tıkla veya 1-9’a bas", "screen.pastbound.time_machine.sent": "Zamansal rota hazırlandı", "screen.pastbound.time_machine.cost": "Yolculuk için 1 Zaman Taşı gerekir", "screen.pastbound.time_machine.preview": "Görev önizlemesi: %s", "screen.pastbound.time_machine.destinations": "Duraklar", "screen.pastbound.time_machine.selected": "Seçili durak", "screen.pastbound.time_machine.mission": "Görev", "screen.pastbound.time_machine.route": "ROTA %s", "screen.pastbound.time_machine.inventory": "Zaman Taşı: %s", "screen.pastbound.time_machine.archive": "Kronik rotası %s/%s", "screen.pastbound.time_machine.page": "Sayfa %s/%s", "screen.pastbound.time_machine.previous": "Önceki", "screen.pastbound.time_machine.next": "Sonraki", "screen.pastbound.time_machine.travel": "Rotayı hazırla", "screen.pastbound.time_machine.confirm": "Yolculuğu onayla", "screen.pastbound.time_machine.confirm_hint": "Görevi incele, ardından yaşayan canlandırmaya girmek için onayla.", "screen.pastbound.time_machine.close_hint": "Esc: iptal veya kapat", "screen.pastbound.time_machine.time_stone_missing": "Envanterde Zaman Taşı yok", "message.pastbound.ancient_storage.deposited": "%s tarihî eşya arşivlendi.", "message.pastbound.ancient_storage.nothing_to_archive": "Arşivlenecek Pastbound araştırma eşyası yok.", "screen.pastbound.ancient_storage.hint": "Boş elle Shift-sağ tıkla: araştırma eşyalarını arşivle ve depoyu düzenle.", "tooltip.pastbound.relic.power": "Yetenek: %s", "tooltip.pastbound.portable_furnace": "Taşınabilir eritme: malzeme ve yakıt koy; yarım kalan yığınlar kapanışta geri döner.", "tooltip.pastbound.portable_furnace_master": "Geliştirilmiş seviye: normal taşınabilir fırın hızının iki katında çalışır.", "tooltip.pastbound.portable_crafting": "Sahada üretim için taşınabilir 3×3 atölye.", "tooltip.pastbound.relic.activity": "Restorasyon etkinliği: %s", "tooltip.pastbound.relic.identify": "Bilgi bedeli: %s XP seviyesi", "tooltip.pastbound.relic.xp": "Tanımak için Shift ile sağ tıkla ve %s XP seviyesi harca", "tooltip.pastbound.relic.shortcut": "V: takılıyken uyandır", "message.pastbound.relic.fragment_already": "Bu yankı parçası zaten onarıldı.", "message.pastbound.relic.fragment_progress": "Yankı onarımı: %s/%s parça bulundu.", "message.pastbound.relic.fragment_complete": "Relic tamamen onarıldı: %s.", "message.pastbound.relic.identified": "XP harcanarak tanındı: %s.", "message.pastbound.relic.xp_hint": "%s XP seviyesine ihtiyacın var.", "message.pastbound.relic.knowledge": "Tarihî hafızaya eklendi: %s", "screen.pastbound.progress": "Kazanılan anılar: %s/%s", "screen.pastbound.help": "R günlük   V uyandır", "screen.pastbound.ready": "BİLİNİYOR", "screen.pastbound.unknown": "BİLİNMİYOR", "screen.pastbound.close": "Kapatmak için Esc", "screen.pastbound.page": "Sayfa %s/%s", "screen.pastbound.page_hint": "Sol/Sağ: sayfa değiştir   Tıkla: relic’i incele", "screen.pastbound.echo_progress": "Yankılar: %s/%s", "screen.pastbound.echo_found": "YANKI BULUNDU", "screen.pastbound.click_hint": "Tarihî sınavı açmak için bir relic’e tıkla", "screen.pastbound.modal_title": "Tarihî Yankı Restorasyonu",    "message.pastbound.echo.discovered": "Tarihî yankı bulundu: %s", "message.pastbound.echo.trace": "İz kaydedildi: %s", "message.pastbound.echo.bad_name": "Bu yankı kronikte yok.", "message.pastbound.echo.already": "%s yankısı zaten kaydedildi.", "message.pastbound.echo.wrong": "Yankı uzaklaşıyor. İpucu: %s", "message.pastbound.echo_count": "Bulunan tarihî yankılar: %s/%s", "message.pastbound.echo.stirs": "Bir tarihî yankı uyandı: %s", "message.pastbound.echo.open_journal": "Tarihî sınava girmek için R’ye basıp Relik Günlüğü’nü aç.", "message.pastbound.echo.locked": "%s için tarihî sınav henüz uyanmadı. Önce ilgili aksiyonu gerçekleştir.", "message.pastbound.global.yazi_devrimi": "Yazı Devrimi sunucuda yankılanıyor: kil üzerindeki işaretler hafızaya, hafıza da kuşaklar arasında bir köprüye dönüşüyor.", "message.pastbound.global.nil_kanallari": "Nil Kanalları tarihte yeni bir yol açıyor: taşkın, tahıl ve dikkatli ölçüm nehri ortak bir takvime dönüştürüyor.", "message.pastbound.global.yildiz_seferi": "Yıldız Seferi her ufku işaretliyor: denizciler yıldızları, dalgaları ve kuşları okuyarak belirsizliği eve dönüş yoluna çeviriyor.", "message.pastbound.global.kervan_yolu": "Kervan Yolu uzak anıları bağlıyor: tuz, ipek, hikâyeler ve aletler çölleri aşarken bilgi de insanlarla birlikte yol alıyor.", "message.pastbound.global.takvim_meclisi": "Takvim Meclisi yaşayan dünyayı hizalıyor: ekim, hasat, tören ve yolculuk ortak bir zaman ölçüsüne bağlanıyor.", "message.pastbound.global.demirci_hafizasi": "Demircilerin Hafızası yerin altında çınlıyor: cevher alete, alet şehre dönüşüyor ve her çekiç darbesi insan izini koruyor.", "message.pastbound.global.mozaik_baris": "Mozaik Barışı dağınık parçaları topluyor: farklı renkler ve gelenekler kendi biçimini kaybetmeden tek bir görüntü oluşturuyor.", "message.pastbound.global.ipek_degisimi": "İpek Değişimi kıyılar arasında bilgi taşıyor: diller, boyalar, ilaçlar ve matematik fikirleri tek bir tüccardan çok daha uzağa ulaşıyor.", "message.pastbound.global.quipu_sayimi": "Quipu Sayımı dünyanın ritmini kaydediyor: düğümler, mürekkep bulunmadığında miktarları, sözleri ve yolculukları koruyor.", "message.pastbound.global.astrolab_gogu": "Astrolab Göğü ölçülü bir yol gösteriyor: küçük bir alet matematiği, gökbilimi ve açık denizin tehlikeli özgürlüğünü birleştiriyor.", "message.pastbound.global.rune_nobeti": "Rune Nöbeti kuzey gecesini koruyor: taşa kazınmış işaret bir adı, uyarıyı ve onu işleyen elin hatırasını taşıyor.", "message.pastbound.global.ay_misyonu": "Ay Misyonu dünyanın ötesine yeni bir yankı bırakıyor: ayak izleri, araçlar ve merakın ufku değiştirebileceğine dair sessiz bir söz.", "message.pastbound.global.tarim_devrimi": "Tarım Devrimi insan yaşamının ritmini değiştiriyor: tohum, sulama ve depolanan tahıl mevsimlik kampı kalıcı yerleşime dönüştürüyor.", "message.pastbound.global.akdeniz_gemiciligi": "Akdeniz denizciliği limanları ve kültürleri bağlıyor: rüzgâr, akıntı ve tanıdık bir takımyıldız hikâyeyi denizin ötesine taşıyor.", "message.pastbound.global.matbaa_yankisi": "Matbaa Yankısı hafızayı çoğaltıyor: tek bir sayfa birçok elden geçerek fikre yazarından daha uzun bir hayat veriyor.", "message.pastbound.global.tip_bahcesi": "Şifa Bahçesi sabırlı gözlemi koruyor: kökler, yapraklar ve dikkatli notlar deneyimi bakım geleneğine dönüştürüyor.", "message.pastbound.global.cam_yolu": "Cam Yolu zanaatla parlıyor: kum, ısı ve sabır kırılgan malzemeyi ışığı ve günlük yaşamı koruyan kaplara çeviriyor.", "message.pastbound.global.gozlem_evi": "Gözlem Evi gece göğünü bir araya getiriyor: tekrar edilen gözlemler yukarı bakan herkesin paylaşabileceği örüntüler açığa çıkarıyor.", "message.pastbound.global.hukuk_meclisi": "Hukuk Meclisi hafızaya kamusal bir ses veriyor: kurallar topluluk onları birlikte açıkladığında, kaydettiğinde ve sorguladığında anlam kazanıyor.", "message.pastbound.global.arkeoloji_kesfi": "Arkeoloji Keşfi tozun altında başlıyor: kırık bir parça bir krallıktan uzun yaşayarak sıradan insanların nasıl yaşadığını anlatabiliyor.", "message.pastbound.packet.invalid": "Arşiv bu isteği reddetti.", "message.pastbound.slot.max": "On relic yuvasının tamamı zaten açık.", "message.pastbound.slot.cost": "İki relic yuvası açmak için %s Netherite Block gerekiyor.", "message.pastbound.slot.unlocked": "İki relic yuvası açıldı. Toplam kapasite: %s.", "message.pastbound.time_machine.unknown": "Bu tarihî durak makinede yok.", "message.pastbound.time_machine.cooldown": "Zaman makinesi hâlâ dengeleniyor.", "message.pastbound.time_machine.time_stone_needed": "Zamanda yolculuk için 1 Zaman Taşı gerekir.", "message.pastbound.time_machine.arrived": "%s durağına ulaşıldı: %s", "message.pastbound.time_machine.dimension_unavailable": "Tarihî keşif boyutu bu dünyada kullanılamıyor.", "message.pastbound.scene.enter": "%s tarihinin yaşayan sahnesine giriyorsun.", "message.pastbound.scene.press_d": "Kontrolü almak istediğinde D’ye bas.", "message.pastbound.scene.narration_intro": "%s başlıyor: %s", "message.pastbound.scene.narration_focus": "Tarihî odak: %s", "message.pastbound.scene.narration_detail": "Yaşayan sahne daha fazlasını gösteriyor: %s", "screen.pastbound.relic.activity": "Yankıyı onar: tarihî hafızayı keşfederek beş parçayı bul.", "screen.pastbound.relic.fragments": "Onarılan parçalar: %s/%s", "screen.pastbound.relic.fragment": "%s. parça", "screen.pastbound.relic.fragment_done": "Onarıldı", "screen.pastbound.relic.activity_hint": "Parçalara tıkla veya 1-5’e bas. Her parça bağımsızdır.", "screen.pastbound.language.title": "Pastbound Dil Seçimi", "screen.pastbound.language.choose": "Pastbound arayüzleri için dil seç", "screen.pastbound.language.english": "English", "screen.pastbound.language.turkish": "Türkçe", "screen.pastbound.language.default": "Varsayılan dil: English", "screen.pastbound.language.close": "Esc: kapat", "message.pastbound.scene.quest": "Aktif görev: bir tanıkla konuş, dış halkayı aş, merkeze dön ve işaretli arşiv anıtını kır.", "message.pastbound.scene.quest_period": "Aktif görev: %s", "message.pastbound.scene.quest_artifact": "İşaretli arşiv anıtı açıldı. Hafızası artık tarihî kaydın bir parçası.", "message.pastbound.mission.steel_start": "Atölye görevi: üç çelik damarını kaz, ham çeliği fırında külçeye erit, çelik levha üret ve tarihî ocağı onar.", "message.pastbound.mission.steel_mined": "Üç çelik damarı açıldı. Ham çeliği bu tarihî atölyedeki fırında erit.", "message.pastbound.mission.steel_vein": "Çelik damarı %s/%s bulundu. İşaretli damarları kazmaya devam et.", "message.pastbound.mission.already_repaired": "Bu tarihî ocak zaten onarıldı.", "message.pastbound.mission.steel_smelted": "Külçe hazır. Bir çelik levha üret ve tarihî ocağa götür.", "message.pastbound.mission.plate_ready": "Levha hazır. Onarım için turuncu işaretli tarihî ocağı kullan.", "message.pastbound.mission.forge_locked": "Ocak soğuk. Önce çelik kaz, külçe erit ve çelik levha üret.", "message.pastbound.mission.plate_needed": "Bu tarihî ocağı onarmak için bir Çelik Levha getir.", "message.pastbound.mission.forge_repaired": "Bağdat Pili Atölyesi yeniden uğulduyor. Onarılan düzenek yeni bir yankı kaydetti.", "message.pastbound.scene.quest_explore": "Görev başladı: her tanıkla konuş, dış halkayı aş ve merkezdeki işarete geri dön.", "message.pastbound.scene.quest_talk": "%s numaralı tanık bir anı paylaştı. Konuşma yoluna devam et.", "message.pastbound.scene.quest_perimeter": "Dış halka keşfedildi. Manzara hikâyenin başka bir parçasını saklıyor.", "message.pastbound.scene.quest_return": "İşarete geri döndün. Sahne son anısını göstermeye hazır.", "message.pastbound.scene.quest_complete": "Tarihî mini görev tamamlandı. Arşivine iki Kronik Parçası eklendi.", "message.pastbound.scene.focus": "Tarihî odak: %s", "message.pastbound.scene.observe": "Canlandırma %s. sahneyi gösteriyor.", "message.pastbound.scene.ready": "Sahne hazır. İçine girmek için D’ye bas.", "message.pastbound.scene.control": "Artık tarihî sahnenin bir parçasısın.", "message.pastbound.scene.returned": "Tarihî canlandırmadan geri döndün.", "screen.pastbound.scene.title": "Yaşayan Tarih", "screen.pastbound.scene.phase_one": "İlk anı şekilleniyor...", "screen.pastbound.scene.phase_two": "Sesler ve hareket geçmişe dönüyor...", "screen.pastbound.scene.phase_three": "Sahne seçimini bekliyor...", "screen.pastbound.scene.press_d": "D: sahnenin kontrolünü al", "screen.pastbound.scene.escape_hint": "Esc: anlatım penceresini kapat", "screen.pastbound.scene.door_open": "Sedir kapısı yaşayan sahne için açılıyor", "screen.pastbound.scene.door_closed": "Sedir kapısı korunan hafızanın çevresinde kapanıyor", "screen.pastbound.scene.task_active": "Aktif saha görevi: bir tanıkla konuş, dış halkayı aş, merkeze dön ve işaretli arşiv anıtını kır.", "screen.pastbound.scene.task_steel": "Atölye görevi: üç çelik damarını kaz, külçe erit, levha üret ve tarihî ocağı onar.", "screen.pastbound.dialogue.title": "Tarihle Konuşma", "screen.pastbound.dialogue.choose": "1, 2 veya 3 ile soru seç", "screen.pastbound.dialogue.waiting": "Tanık hatırlıyor...", "screen.pastbound.dialogue.response": "Tanığın cevabı", "message.pastbound.dialogue.too_far": "Tarihî konuşmacıya yaklaş.", "entity.pastbound.scene.narrator": "Tarih Anlatıcısı", "entity.pastbound.scene.craftsman": "Zanaatkâr", "entity.pastbound.scene.witness": "Tanık", "entity.pastbound.scene.scribe": "Kâtip", "tooltip.pastbound.relic.echo": "Keşif yankısı: %s"},
    "de_de": {"itemGroup.pastbound": "Pastbound: Echos der Geschichte", "screen.pastbound.journal": "Relikt-Tagebuch", "screen.pastbound.progress": "Geborgene Erinnerungen: %s/%s", "screen.pastbound.help": "R Tagebuch   V erwecken", "screen.pastbound.ready": "BEKANNT", "screen.pastbound.unknown": "UNBEKANNT", "screen.pastbound.close": "Esc zum Schließen"},
    "fr_fr": {"itemGroup.pastbound": "Pastbound : Échos de l’histoire", "screen.pastbound.journal": "Journal des reliques", "screen.pastbound.progress": "Souvenirs retrouvés : %s/%s", "screen.pastbound.help": "R journal   V éveiller", "screen.pastbound.ready": "CONNU", "screen.pastbound.unknown": "INCONNU", "screen.pastbound.close": "Esc pour fermer"},
    "es_es": {"itemGroup.pastbound": "Pastbound: Ecos de la historia", "screen.pastbound.journal": "Diario de reliquias", "screen.pastbound.progress": "Memorias recuperadas: %s/%s", "screen.pastbound.help": "R diario   V despertar", "screen.pastbound.ready": "CONOCIDA", "screen.pastbound.unknown": "DESCONOCIDA", "screen.pastbound.close": "Esc para cerrar"},
    "es_mx": {"itemGroup.pastbound": "Pastbound: Ecos de la historia", "screen.pastbound.journal": "Diario de reliquias", "screen.pastbound.progress": "Memorias recuperadas: %s/%s", "screen.pastbound.help": "R diario   V despertar", "screen.pastbound.ready": "CONOCIDA", "screen.pastbound.unknown": "DESCONOCIDA", "screen.pastbound.close": "Esc para cerrar"},
    "it_it": {"itemGroup.pastbound": "Pastbound: Echi della storia", "screen.pastbound.journal": "Diario delle reliquie", "screen.pastbound.progress": "Memorie recuperate: %s/%s", "screen.pastbound.help": "R diario   V risveglia", "screen.pastbound.ready": "NOTA", "screen.pastbound.unknown": "IGNOTA", "screen.pastbound.close": "Esc per chiudere"},
    "pt_br": {"itemGroup.pastbound": "Pastbound: Ecos da história", "screen.pastbound.journal": "Diário de relíquias", "screen.pastbound.progress": "Memórias recuperadas: %s/%s", "screen.pastbound.help": "R diário   V despertar", "screen.pastbound.ready": "CONHECIDA", "screen.pastbound.unknown": "DESCONHECIDA", "screen.pastbound.close": "Esc para fechar"},
    "pt_pt": {"itemGroup.pastbound": "Pastbound: Ecos da história", "screen.pastbound.journal": "Diário de relíquias", "screen.pastbound.progress": "Memórias recuperadas: %s/%s", "screen.pastbound.help": "R diário   V despertar", "screen.pastbound.ready": "CONHECIDA", "screen.pastbound.unknown": "DESCONHECIDA", "screen.pastbound.close": "Esc para fechar"},
    "ru_ru": {"itemGroup.pastbound": "Pastbound: Эхо истории", "screen.pastbound.journal": "Дневник реликвий", "screen.pastbound.progress": "Возвращено воспоминаний: %s/%s", "screen.pastbound.help": "R дневник   V пробудить", "screen.pastbound.ready": "ИЗВЕСТНО", "screen.pastbound.unknown": "НЕИЗВЕСТНО", "screen.pastbound.close": "Esc — закрыть"},
    "uk_ua": {"itemGroup.pastbound": "Pastbound: Відлуння історії", "screen.pastbound.journal": "Щоденник реліквій", "screen.pastbound.progress": "Відновлено спогадів: %s/%s", "screen.pastbound.help": "R щоденник   V пробудити", "screen.pastbound.ready": "ВІДОМО", "screen.pastbound.unknown": "НЕВІДОМО", "screen.pastbound.close": "Esc — закрити"},
    "pl_pl": {"itemGroup.pastbound": "Pastbound: Echa historii", "screen.pastbound.journal": "Dziennik relikwii", "screen.pastbound.progress": "Odzyskane wspomnienia: %s/%s", "screen.pastbound.help": "R dziennik   V przebudź", "screen.pastbound.ready": "ZNANA", "screen.pastbound.unknown": "NIEZNANA", "screen.pastbound.close": "Esc, aby zamknąć"},
    "nl_nl": {"itemGroup.pastbound": "Pastbound: Echo’s van de geschiedenis", "screen.pastbound.journal": "Reliekenjournaal", "screen.pastbound.progress": "Herstelde herinneringen: %s/%s", "screen.pastbound.help": "R journaal   V wekken", "screen.pastbound.ready": "BEKEND", "screen.pastbound.unknown": "ONBEKEND", "screen.pastbound.close": "Esc om te sluiten"},
    "sv_se": {"itemGroup.pastbound": "Pastbound: Historiens ekon", "screen.pastbound.journal": "Relikviedagbok", "screen.pastbound.progress": "Återfunna minnen: %s/%s", "screen.pastbound.help": "R dagbok   V väck", "screen.pastbound.ready": "KÄND", "screen.pastbound.unknown": "OKÄND", "screen.pastbound.close": "Esc för att stänga"},
    "da_dk": {"itemGroup.pastbound": "Pastbound: Historiens ekko", "screen.pastbound.journal": "Relikviedagbog", "screen.pastbound.progress": "Genfundne minder: %s/%s", "screen.pastbound.help": "R dagbog   V væk", "screen.pastbound.ready": "KENDT", "screen.pastbound.unknown": "UKENDT", "screen.pastbound.close": "Esc for at lukke"},
    "nb_no": {"itemGroup.pastbound": "Pastbound: Historiens ekko", "screen.pastbound.journal": "Relikviedagbok", "screen.pastbound.progress": "Gjenfunne minner: %s/%s", "screen.pastbound.help": "R dagbok   V vekk", "screen.pastbound.ready": "KJENT", "screen.pastbound.unknown": "UKJENT", "screen.pastbound.close": "Esc for å lukke"},
    "fi_fi": {"itemGroup.pastbound": "Pastbound: Historian kaiut", "screen.pastbound.journal": "Reliikkipäiväkirja", "screen.pastbound.progress": "Palautetut muistot: %s/%s", "screen.pastbound.help": "R päiväkirja   V herätä", "screen.pastbound.ready": "TUNNETTU", "screen.pastbound.unknown": "TUNTEMATON", "screen.pastbound.close": "Esc sulkee"},
    "cs_cz": {"itemGroup.pastbound": "Pastbound: Ozvěny dějin", "screen.pastbound.journal": "Deník relikvií", "screen.pastbound.progress": "Obnovené vzpomínky: %s/%s", "screen.pastbound.help": "R deník   V probudit", "screen.pastbound.ready": "ZNÁMÁ", "screen.pastbound.unknown": "NEZNÁMÁ", "screen.pastbound.close": "Esc pro zavření"},
    "sk_sk": {"itemGroup.pastbound": "Pastbound: Ozveny dejín", "screen.pastbound.journal": "Denník relikvií", "screen.pastbound.progress": "Obnovené spomienky: %s/%s", "screen.pastbound.help": "R denník   V prebudiť", "screen.pastbound.ready": "ZNÁMA", "screen.pastbound.unknown": "NEZNÁMA", "screen.pastbound.close": "Esc na zatvorenie"},
    "hu_hu": {"itemGroup.pastbound": "Pastbound: A történelem visszhangjai", "screen.pastbound.journal": "Ereklyenapló", "screen.pastbound.progress": "Visszaszerzett emlékek: %s/%s", "screen.pastbound.help": "R napló   V felébreszt", "screen.pastbound.ready": "ISMERT", "screen.pastbound.unknown": "ISMERETLEN", "screen.pastbound.close": "Esc a bezáráshoz"},
    "ro_ro": {"itemGroup.pastbound": "Pastbound: Ecourile istoriei", "screen.pastbound.journal": "Jurnalul relicvelor", "screen.pastbound.progress": "Amintiri recuperate: %s/%s", "screen.pastbound.help": "R jurnal   V trezește", "screen.pastbound.ready": "CUNOSCUTĂ", "screen.pastbound.unknown": "NECUNOSCUTĂ", "screen.pastbound.close": "Esc pentru închidere"},
    "bg_bg": {"itemGroup.pastbound": "Pastbound: Ехото на историята", "screen.pastbound.journal": "Дневник на реликвите", "screen.pastbound.progress": "Възстановени спомени: %s/%s", "screen.pastbound.help": "R дневник   V събуди", "screen.pastbound.ready": "ПОЗНАТА", "screen.pastbound.unknown": "НЕПОЗНАТА", "screen.pastbound.close": "Esc за затваряне"},
    "el_gr": {"itemGroup.pastbound": "Pastbound: Ηχώ της ιστορίας", "screen.pastbound.journal": "Ημερολόγιο κειμηλίων", "screen.pastbound.progress": "Ανακτημένες μνήμες: %s/%s", "screen.pastbound.help": "R ημερολόγιο   V αφύπνιση", "screen.pastbound.ready": "ΓΝΩΣΤΟ", "screen.pastbound.unknown": "ΑΓΝΩΣΤΟ", "screen.pastbound.close": "Esc για κλείσιμο"},
    "zh_cn": {"itemGroup.pastbound": "Pastbound：历史回声", "screen.pastbound.journal": "遗物日志", "screen.pastbound.progress": "找回的记忆：%s/%s", "screen.pastbound.help": "R 日志   V 唤醒", "screen.pastbound.ready": "已知", "screen.pastbound.unknown": "未知", "screen.pastbound.close": "按 Esc 关闭"},
    "zh_tw": {"itemGroup.pastbound": "Pastbound：歷史迴響", "screen.pastbound.journal": "遺物日誌", "screen.pastbound.progress": "找回的記憶：%s/%s", "screen.pastbound.help": "R 日誌   V 喚醒", "screen.pastbound.ready": "已知", "screen.pastbound.unknown": "未知", "screen.pastbound.close": "按 Esc 關閉"},
    "ja_jp": {"itemGroup.pastbound": "Pastbound：歴史のこだま", "screen.pastbound.journal": "レリック日誌", "screen.pastbound.progress": "回収した記憶：%s/%s", "screen.pastbound.help": "R 日誌   V 覚醒", "screen.pastbound.ready": "既知", "screen.pastbound.unknown": "未知", "screen.pastbound.close": "Escで閉じる"},
    "ko_kr": {"itemGroup.pastbound": "Pastbound: 역사의 메아리", "screen.pastbound.journal": "유물 일지", "screen.pastbound.progress": "되찾은 기억: %s/%s", "screen.pastbound.help": "R 일지   V 각성", "screen.pastbound.ready": "알려짐", "screen.pastbound.unknown": "미지", "screen.pastbound.close": "Esc로 닫기"},
    "vi_vn": {"itemGroup.pastbound": "Pastbound: Tiếng vọng lịch sử", "screen.pastbound.journal": "Nhật ký di vật", "screen.pastbound.progress": "Ký ức phục hồi: %s/%s", "screen.pastbound.help": "R nhật ký   V đánh thức", "screen.pastbound.ready": "ĐÃ BIẾT", "screen.pastbound.unknown": "CHƯA BIẾT", "screen.pastbound.close": "Esc để đóng"},
    "th_th": {"itemGroup.pastbound": "Pastbound: เสียงสะท้อนแห่งประวัติศาสตร์", "screen.pastbound.journal": "บันทึกโบราณวัตถุ", "screen.pastbound.progress": "ความทรงจำที่กู้คืน: %s/%s", "screen.pastbound.help": "R บันทึก   V ปลุก", "screen.pastbound.ready": "รู้แล้ว", "screen.pastbound.unknown": "ไม่รู้จัก", "screen.pastbound.close": "กด Esc เพื่อปิด"},
    "id_id": {"itemGroup.pastbound": "Pastbound: Gema Sejarah", "screen.pastbound.journal": "Jurnal Relik", "screen.pastbound.progress": "Kenangan dipulihkan: %s/%s", "screen.pastbound.help": "R jurnal   V bangunkan", "screen.pastbound.ready": "DIKENAL", "screen.pastbound.unknown": "TAK DIKENAL", "screen.pastbound.close": "Esc untuk menutup"},
    "ar_sa": {"itemGroup.pastbound": "Pastbound: أصداء التاريخ", "screen.pastbound.journal": "سجل الآثار", "screen.pastbound.progress": "الذكريات المستعادة: %s/%s", "screen.pastbound.help": "R السجل   V إيقاظ", "screen.pastbound.ready": "معروف", "screen.pastbound.unknown": "مجهول", "screen.pastbound.close": "اضغط Esc للإغلاق"},
    "he_il": {"itemGroup.pastbound": "Pastbound: הדי ההיסטוריה", "screen.pastbound.journal": "יומן השרידים", "screen.pastbound.progress": "זיכרונות ששוחזרו: %s/%s", "screen.pastbound.help": "R יומן   V להעיר", "screen.pastbound.ready": "ידוע", "screen.pastbound.unknown": "לא ידוע", "screen.pastbound.close": "Esc לסגירה"},
    "af_za": {"itemGroup.pastbound": "Pastbound: Eggo's van die geskiedenis", "screen.pastbound.journal": "Relikwiejoernaal", "screen.pastbound.progress": "Herwonne herinneringe: %s/%s", "screen.pastbound.help": "R joernaal   V wek", "screen.pastbound.ready": "BEKEND", "screen.pastbound.unknown": "ONBEKEND", "screen.pastbound.close": "Esc om te sluit"},
    "eo_uy": {"itemGroup.pastbound": "Pastbound: Eĥoj de historio", "screen.pastbound.journal": "Relikva taglibro", "screen.pastbound.progress": "Retrovitaj memoroj: %s/%s", "screen.pastbound.help": "R taglibro   V veki", "screen.pastbound.ready": "KONATA", "screen.pastbound.unknown": "NEKONATA", "screen.pastbound.close": "Esc por fermi"},
    "ga_ie": {"itemGroup.pastbound": "Pastbound: Macalla na staire", "screen.pastbound.journal": "Dialann iarsmaí", "screen.pastbound.progress": "Cuimhní aimsithe: %s/%s", "screen.pastbound.help": "R dialann   V múscail", "screen.pastbound.ready": "AITHNITHE", "screen.pastbound.unknown": "ANAITHNID", "screen.pastbound.close": "Esc le dúnadh"},
    "cy_gb": {"itemGroup.pastbound": "Pastbound: Adleisiau hanes", "screen.pastbound.journal": "Dyddiadur creiriau", "screen.pastbound.progress": "Atgofion a adferwyd: %s/%s", "screen.pastbound.help": "R dyddiadur   V deffro", "screen.pastbound.ready": "GWYBOD", "screen.pastbound.unknown": "ANHYSBYS", "screen.pastbound.close": "Esc i gau"}
}

pusula_dilleri = {
    "af_za": ("Kroniekkompas", "Die Kroniekkompas vind geen historiese erts naby nie.", "%s is %s blokke weg by %s, %s, %s."),
    "ar_sa": ("بوصلة السجل الزمني", "لا تعثر بوصلة السجل الزمني على خام تاريخي قريب.", "تم العثور على %s على بعد %s كتلة عند %s، %s، %s."),
    "bg_bg": ("Хроникална компас", "Хроникалният компас не открива историческа руда наблизо.", "%s е на %s блока при %s, %s, %s."),
    "cs_cz": ("Kronikový kompas", "Kronikový kompas poblíž nenachází historickou rudu.", "%s je %s bloků daleko na souřadnicích %s, %s, %s."),
    "cy_gb": ("Cwmpawd Cronicl", "Nid yw Cwmpawd y Cronicl yn dod o hyd i fwyn hanesyddol gerllaw.", "Mae %s %s bloc i ffwrdd yn %s, %s, %s."),
    "da_dk": ("Krønikekompas", "Krønikekompasset finder ingen historisk malm i nærheden.", "%s er %s blokke væk ved %s, %s, %s."),
    "de_de": ("Chronik-Kompass", "Der Chronik-Kompass findet kein historisches Erz in der Nähe.", "%s ist %s Blöcke entfernt bei %s, %s, %s."),
    "el_gr": ("Πυξίδα Χρονικού", "Η Πυξίδα Χρονικού δεν βρίσκει ιστορικό μετάλλευμα κοντά.", "Το %s απέχει %s μπλοκ στις συντεταγμένες %s, %s, %s."),
    "en_us": ("Chronicle Compass", "The Chronicle Compass finds no historical ore nearby.", "%s is %s blocks away at %s, %s, %s."),
    "eo_uy": ("Kronika Kompaso", "La Kronika Kompaso ne trovas historian ercon proksime.", "%s estas %s blokojn for ĉe %s, %s, %s."),
    "es_es": ("Brújula de Crónicas", "La Brújula de Crónicas no encuentra mineral histórico cerca.", "%s está a %s bloques, en %s, %s, %s."),
    "es_mx": ("Brújula de Crónicas", "La Brújula de Crónicas no encuentra mineral histórico cerca.", "%s está a %s bloques, en %s, %s, %s."),
    "fi_fi": ("Kroniikkakompassi", "Kroniikkakompassi ei löydä historiallista malmia läheltä.", "%s on %s korttelin päässä koordinaateissa %s, %s, %s."),
    "fr_fr": ("Boussole des chroniques", "La Boussole des chroniques ne trouve aucun minerai historique à proximité.", "%s se trouve à %s blocs aux coordonnées %s, %s, %s."),
    "ga_ie": ("Compás na Croinice", "Ní aimsíonn Compás na Croinice méine stairiúil in aice láimhe.", "Tá %s %s bloc ar shiúl ag %s, %s, %s."),
    "he_il": ("מצפן הכרוניקה", "מצפן הכרוניקה לא מצא עפרה היסטורית בקרבת מקום.", "%s נמצא במרחק של %s בלוקים ב־%s, %s, %s."),
    "hu_hu": ("Krónikairánytű", "A Krónikairánytű nem talál történelmi ércet a közelben.", "A(z) %s %s blokkal arrébb található: %s, %s, %s."),
    "id_id": ("Kompas Kronik", "Kompas Kronik tidak menemukan bijih sejarah di dekat sini.", "%s berjarak %s blok di %s, %s, %s."),
    "it_it": ("Bussola delle cronache", "La Bussola delle cronache non trova minerale storico nelle vicinanze.", "%s si trova a %s blocchi alle coordinate %s, %s, %s."),
    "ja_jp": ("年代記コンパス", "年代記コンパスは近くに歴史の鉱石を見つけられません。", "%s は座標 %s, %s, %s にある %s ブロック先です。"),
    "ko_kr": ("연대기 나침반", "연대기 나침반이 근처에서 역사 광석을 찾지 못했습니다.", "%s은(는) %s블록 거리의 %s, %s, %s에 있습니다."),
    "nb_no": ("Krønike-kompass", "Krønike-kompasset finner ingen historisk malm i nærheten.", "%s er %s blokker unna ved %s, %s, %s."),
    "nl_nl": ("Kroniekkompas", "Het Kroniekkompas vindt geen historisch erts in de buurt.", "%s ligt %s blokken verderop bij %s, %s, %s."),
    "pl_pl": ("Kompas Kronik", "Kompas Kronik nie znajduje w pobliżu historycznej rudy.", "%s znajduje się %s bloków dalej na %s, %s, %s."),
    "pt_br": ("Bússola da Crônica", "A Bússola da Crônica não encontra minério histórico por perto.", "%s está a %s blocos em %s, %s, %s."),
    "pt_pt": ("Bússola da Crónica", "A Bússola da Crónica não encontra minério histórico por perto.", "%s está a %s blocos em %s, %s, %s."),
    "ro_ro": ("Busola Cronicii", "Busola Cronicii nu găsește minereu istoric în apropiere.", "%s se află la %s blocuri, la %s, %s, %s."),
    "ru_ru": ("Компас хроники", "Компас хроники не находит поблизости историческую руду.", "%s находится в %s блоках по координатам %s, %s, %s."),
    "sk_sk": ("Kronikový kompas", "Kronikový kompas nenašiel v okolí historickú rudu.", "%s je vzdialená %s blokov na súradniciach %s, %s, %s."),
    "sv_se": ("Krönikkompass", "Krönikkompassen hittar ingen historisk malm i närheten.", "%s är %s block bort vid %s, %s, %s."),
    "th_th": ("เข็มทิศพงศาวดาร", "เข็มทิศพงศาวดารไม่พบแร่ประวัติศาสตร์ใกล้เคียง", "พบ %s อยู่ห่างออกไป %s บล็อกที่ %s, %s, %s"),
    "tr_tr": ("Kronik Pusulası", "Kronik Pusulası yakında tarihî cevher bulamıyor.", "%s, %s blok ötede bulundu: %s, %s, %s."),
    "uk_ua": ("Компас хроніки", "Компас хроніки не знаходить поблизу історичної руди.", "%s знаходиться за %s блоків на координатах %s, %s, %s."),
    "vi_vn": ("La bàn Biên niên", "La bàn Biên niên không tìm thấy quặng lịch sử gần đây.", "%s cách %s khối tại %s, %s, %s."),
    "zh_cn": ("编年史指南针", "编年史指南针没有在附近找到历史矿石。", "%s 位于 %s 格之外，坐标为 %s、%s、%s。"),
    "zh_tw": ("編年史羅盤", "編年史羅盤在附近找不到歷史礦石。", "%s 位於 %s 格之外，座標為 %s、%s、%s。")
}

for locale, add in metinler.items():
    veri = dict(ortak)
    veri.update(add)
    pusula = pusula_dilleri.get(locale, pusula_dilleri["en_us"])
    veri["item.pastbound.chronicle_compass"] = pusula[0]
    veri["message.pastbound.compass.none"] = pusula[1]
    veri["message.pastbound.compass.found"] = pusula[2]
    if locale == "tr_tr":
        veri["message.pastbound.scene.quest_inspect"] = "Hafıza Merceği anıtın gizli katmanını açığa çıkardı. Hikâyesi artık onarılmaya hazır."
    ahsap_kaynagi = turkce_ahsaplar if locale == "tr_tr" else ahsaplar
    for kimlik, ad in ahsap_kaynagi.items():
        veri[f"block.pastbound.{kimlik}"] = ad
        veri[f"item.pastbound.{kimlik}"] = ad
    for kimlik, (ingilizce_ad, turkce_ad) in biome_adlari.items():
        veri[f"biome.pastbound.{kimlik}"] = turkce_ad if locale == "tr_tr" else ingilizce_ad
    for kimlik, (ingilizce_ad, turkce_ad) in celik_esyalari.items():
        veri[f"item.pastbound.{kimlik}"] = turkce_ad if locale == "tr_tr" else ingilizce_ad
    for kimlik, (ingilizce_ad, turkce_ad) in celik_bloklari.items():
        veri[f"block.pastbound.{kimlik}"] = turkce_ad if locale == "tr_tr" else ingilizce_ad
        veri[f"item.pastbound.{kimlik}"] = turkce_ad if locale == "tr_tr" else ingilizce_ad
    for kimlik, (ingilizce_ad, turkce_ad) in maden_bloklari.items():
        veri[f"block.pastbound.{kimlik}"] = turkce_ad if locale == "tr_tr" else ingilizce_ad
        veri[f"item.pastbound.{kimlik}"] = turkce_ad if locale == "tr_tr" else ingilizce_ad
    ad_kaynagi = relicler if locale == "tr_tr" else ingilizce_relikler
    for kimlik, ad in ad_kaynagi.items():
        veri[f"item.pastbound.{kimlik}"] = ad
        veri[f"tooltip.pastbound.{kimlik}"] = ad
        veri[f"history.pastbound.relic.{kimlik}.title"] = ad
        veri[f"history.pastbound.relic.{kimlik}.activity"] = ingilizce_etkinlikler[kimlik]
        if locale == "tr_tr":
            veri[f"history.pastbound.relic.{kimlik}.title"] = relicler[kimlik]
            veri[f"history.pastbound.relic.{kimlik}.activity"] = turkce_etkinlikler[kimlik]
    for kimlik, ad in yankilar.items():
        yankı_adı = ingilizce_yankilar[kimlik]
        if locale == "tr_tr":
            yankı_adı = ad
        veri[f"history.pastbound.echo.{kimlik}.name"] = yankı_adı
        veri[f"history.pastbound.echo.{kimlik}.hint"] = yankı_adı
        veri[f"advancement.pastbound.{kimlik}.title"] = yankı_adı
        veri[f"advancement.pastbound.{kimlik}.description"] = "Complete the restoration activity to recover its historical memory."
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
        konusma = konusmalar_tr[kimlik] if locale == "tr_tr" else konusmalar_en[kimlik]
        veri[f"history.pastbound.period.{kimlik}.dialogue_1"] = konusma[0]
        veri[f"history.pastbound.period.{kimlik}.dialogue_2"] = konusma[1]
        veri[f"history.pastbound.period.{kimlik}.dialogue_3"] = konusma[2]
        veri[f"screen.pastbound.scene.task.{kimlik}"] = gorevler_tr[kimlik] if locale == "tr_tr" else gorevler_en[kimlik]
        veri[f"screen.pastbound.scene.ecosystem.{kimlik}"] = ekosistem_tr[kimlik] if locale == "tr_tr" else ekosistem_en[kimlik]
        if locale == "tr_tr":
            veri[f"history.pastbound.period.{kimlik}.response_1"] = konusma[1]
            veri[f"history.pastbound.period.{kimlik}.response_2"] = f"Konuşmacı, {anlatim_ad} izlerinin arşivde korunmasını istiyor."
            veri[f"history.pastbound.period.{kimlik}.response_3"] = f"Bu tanıklık, {anlatim_odak.lower()} bilgisinin yaşayan bir emanetçisi olarak kayda geçiyor."
        else:
            veri[f"history.pastbound.period.{kimlik}.response_1"] = konusma[1]
            veri[f"history.pastbound.period.{kimlik}.response_2"] = f"The speaker asks the archive to preserve the surviving traces of {anlatim_ad}."
            veri[f"history.pastbound.period.{kimlik}.response_3"] = f"This testimony becomes a living record of {anlatim_odak.lower()}."
        veri[f"advancement.pastbound.time_machine.{kimlik}.title"] = ad
        veri[f"advancement.pastbound.time_machine.{kimlik}.description"] = aciklama
    veri["advancement.pastbound.time_machine.complete_expedition.title"] = "Time Cartographer"
    veri["advancement.pastbound.time_machine.complete_expedition.description"] = "Explore every historical destination in the time machine."
    if locale == "tr_tr":
        veri["advancement.pastbound.time_machine.curators_seal.title"] = "Küratör Mührü"
        veri["advancement.pastbound.time_machine.curators_seal.description"] = "Her tarihî keşif rotasını tamamla ve arşivi koru."
        veri["message.pastbound.time_machine.curators_seal"] = "Küratör Mührü artık senin. Her tarihî rota arşivde korunmuş bir yankıya dönüştü."
    else:
        veri["advancement.pastbound.time_machine.curators_seal.title"] = "Curator's Seal"
        veri["advancement.pastbound.time_machine.curators_seal.description"] = "Complete every historical expedition and preserve the archive."
        veri["message.pastbound.time_machine.curators_seal"] = "The Curator's Seal is yours. Every historical route now has a preserved echo."
    (lang / f"{locale}.json").write_text(json.dumps(veri, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
