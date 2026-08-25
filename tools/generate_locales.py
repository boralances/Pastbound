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

ortak = {
    "block.pastbound.echo_archive": "Echo Archive",
    "block.pastbound.resonance_pillar": "Resonance Pillar",
    "item.pastbound.echo_shard": "Echo Shard",
    "item.pastbound.memory_lens": "Memory Lens",
    "tooltip.pastbound.relic.power": "Ability: %s",
    "tooltip.pastbound.relic.riddle": "Riddle: %s",
    "tooltip.pastbound.relic.identify": "Knowledge cost: %s XP levels",
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
    "key.pastbound.journal": "Open Relic Journal",
    "key.pastbound.activate": "Awaken equipped relic",
    "tooltip.pastbound.relic.era": "Historical trace: %s",
    "tooltip.pastbound.relic.echo": "Discovery echo: %s",
    "item.pastbound.chronicle_scrap": "Chronicle Scrap",
    "item.pastbound.history_ink": "History Ink",
    "item.pastbound.time_stone": "Time Stone",
    "item.pastbound.echo_seal": "Echo Seal",
    "screen.pastbound.echo_progress": "Echoes: %s/%s",
    "screen.pastbound.echo_found": "ECHO FOUND",
    "screen.pastbound.click_hint": "Click a relic to open its historical trial",
    "screen.pastbound.modal_title": "Historical Echo Trial",
    "screen.pastbound.sequence": "Sequence: %s",
    "screen.pastbound.choose": "Choose 1, 2, or 3",
    "screen.pastbound.solve_hint": "Enter to submit   Esc to return",
    "message.pastbound.echo.discovered": "Historical echo recovered: %s",
    "message.pastbound.echo.trace": "Trace recorded: %s",
    "message.pastbound.echo.bad_name": "That echo is not in the chronicle.",
    "message.pastbound.echo.already": "The echo of %s is already recorded.",
    "message.pastbound.echo.wrong": "The sequence fades. Hint: %s",
    "message.pastbound.echo_count": "Historical echoes recovered: %s/%s",
    "message.pastbound.echo.stirs": "A historical echo stirs: %s",
    "message.pastbound.echo.open_journal": "Open the journal to attempt its historical trial.",
    "message.pastbound.echo.locked": "The historical trial for %s has not been awakened yet. Trigger its related action first."
}

metinler = {
    "en_us": {"itemGroup.pastbound": "Pastbound: Echoes of History", "screen.pastbound.journal": "Relic Journal"},
    "tr_tr": {"itemGroup.pastbound": "Pastbound: Tarihin Yankıları", "screen.pastbound.journal": "Relik Günlüğü", "tooltip.pastbound.relic.power": "Yetenek: %s", "tooltip.pastbound.relic.riddle": "Bilmece: %s", "tooltip.pastbound.relic.identify": "Bilgi bedeli: %s XP seviyesi", "tooltip.pastbound.relic.shortcut": "V: takılıyken uyandır", "message.pastbound.relic.riddle_wrong": "Geçmiş susuyor. Bu cevap uymuyor.", "message.pastbound.relic.riddle_right": "Bilmece %s anısını açtı.", "message.pastbound.relic.knowledge": "Tarihî hafızaya eklendi: %s", "screen.pastbound.progress": "Kazanılan anılar: %s/%s", "screen.pastbound.help": "R günlük   V uyandır", "screen.pastbound.ready": "BİLİNİYOR", "screen.pastbound.unknown": "BİLİNMİYOR", "screen.pastbound.riddle_hint": "Bilmeceyi çöz", "screen.pastbound.close": "Kapatmak için Esc", "screen.pastbound.echo_progress": "Yankılar: %s/%s", "screen.pastbound.echo_found": "YANKI BULUNDU", "screen.pastbound.click_hint": "Tarihî sınavı açmak için bir relic’e tıkla", "screen.pastbound.modal_title": "Tarihî Yankı Sınavı", "screen.pastbound.sequence": "Dizi: %s", "screen.pastbound.choose": "1, 2 veya 3 seç", "screen.pastbound.solve_hint": "Göndermek için Enter   Geri dönmek için Esc", "message.pastbound.echo.discovered": "Tarihî yankı bulundu: %s", "message.pastbound.echo.trace": "İz kaydedildi: %s", "message.pastbound.echo.bad_name": "Bu yankı kronikte yok.", "message.pastbound.echo.already": "%s yankısı zaten kaydedildi.", "message.pastbound.echo.wrong": "Dizi dağıldı. İpucu: %s", "message.pastbound.echo_count": "Bulunan tarihî yankılar: %s/%s", "message.pastbound.echo.stirs": "Bir tarihî yankı uyandı: %s", "message.pastbound.echo.open_journal": "Tarihî sınavı denemek için günlüğü aç.", "message.pastbound.echo.locked": "%s için tarihî sınav henüz uyanmadı. Önce ilgili aksiyonu gerçekleştir.", "tooltip.pastbound.relic.echo": "Keşif yankısı: %s"},
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
    for kimlik, ad in relicler.items():
        veri[f"item.pastbound.{kimlik}"] = ad
        veri[f"tooltip.pastbound.{kimlik}"] = ad
    for kimlik, ad in yankilar.items():
        veri[f"advancement.pastbound.{kimlik}.title"] = ad
        veri[f"advancement.pastbound.{kimlik}.description"] = "Solve the historical echo trial to recover its memory."
    (lang / f"{locale}.json").write_text(json.dumps(veri, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
