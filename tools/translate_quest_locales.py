import json
import re
from concurrent.futures import ThreadPoolExecutor, as_completed
from pathlib import Path
from openai import OpenAI

kok = Path(__file__).resolve().parents[1]
lang = kok / "src/main/resources/assets/pastbound/lang"
referans = json.loads((lang / "en_us.json").read_text(encoding="utf-8"))
anahtarlar = [anahtar for anahtar in referans if anahtar.startswith("message.pastbound.scene.") or anahtar.startswith("message.pastbound.mission.") or anahtar.startswith("message.pastbound.compass.") or anahtar.startswith("screen.pastbound.scene.task.") or anahtar.startswith("screen.pastbound.scene.ecosystem.") or anahtar == "item.pastbound.chronicle_compass"]
hedefler = {anahtar: referans[anahtar] for anahtar in anahtarlar}
modeller = {"af_za": "Afrikaans", "ar_sa": "Arabic", "bg_bg": "Bulgarian", "cs_cz": "Czech", "cy_gb": "Welsh", "da_dk": "Danish", "de_de": "German", "el_gr": "Greek", "eo_uy": "Esperanto", "es_es": "Spanish", "es_mx": "Mexican Spanish", "fi_fi": "Finnish", "fr_fr": "French", "ga_ie": "Irish", "he_il": "Hebrew", "hu_hu": "Hungarian", "id_id": "Indonesian", "it_it": "Italian", "ja_jp": "Japanese", "ko_kr": "Korean", "nb_no": "Norwegian Bokmål", "nl_nl": "Dutch", "pl_pl": "Polish", "pt_br": "Brazilian Portuguese", "pt_pt": "European Portuguese", "ro_ro": "Romanian", "ru_ru": "Russian", "sk_sk": "Slovak", "sv_se": "Swedish", "th_th": "Thai", "uk_ua": "Ukrainian", "vi_vn": "Vietnamese", "zh_cn": "Simplified Chinese", "zh_tw": "Traditional Chinese"}


def temizle(metin):
    metin = metin.strip()
    if metin.startswith("```"):
        metin = re.sub(r"^```(?:json)?", "", metin).strip()
        metin = re.sub(r"```$", "", metin).strip()
    return metin


def cevir(kod, dil):
    istemci = OpenAI(timeout=45.0, max_retries=1)
    sistem = "You are a professional Minecraft localization editor. Translate only the values into the target language. Preserve every JSON key exactly, preserve %s placeholders, keep item and biome proper names historically accurate, use natural concise UI wording, and return valid JSON only."
    kullanici = json.dumps({"target_language": dil, "translations": hedefler}, ensure_ascii=False)
    yanit = istemci.chat.completions.create(model="gpt-5-nano", messages=[{"role": "system", "content": sistem}, {"role": "user", "content": kullanici}], response_format={"type": "json_object"}, max_completion_tokens=5000)
    sonuc = json.loads(temizle(yanit.choices[0].message.content))
    if set(sonuc) != set(hedefler):
        raise ValueError(f"{kod}: key coverage mismatch")
    for anahtar, deger in sonuc.items():
        if not isinstance(deger, str) or not deger:
            raise ValueError(f"{kod}: empty translation")
        if deger.count("%s") != hedefler[anahtar].count("%s"):
            raise ValueError(f"{kod}: placeholder mismatch")
    dosya = lang / f"{kod}.json"
    veri = json.loads(dosya.read_text(encoding="utf-8"))
    veri.update(sonuc)
    dosya.write_text(json.dumps(veri, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
    return kod


with ThreadPoolExecutor(max_workers=8) as havuz:
    gelecekler = [havuz.submit(cevir, kod, dil) for kod, dil in modeller.items() if kod not in {"tr_tr"}]
    tamamlanan = [gelecek.result() for gelecek in as_completed(gelecekler)]
print(json.dumps({"translated_locales": sorted(tamamlanan), "key_count": len(hedefler)}, ensure_ascii=False))
