# Pastbound Locale Quality Audit

Reference: en_us.json with 604 keys.

| Locale | Keys | Missing | Empty | Same as English | Native or custom keys |
|---|---:|---:|---:|---:|---:|
| `af_za` | 604 | 0 | 0 | 594 | 10 |
| `ar_sa` | 604 | 0 | 0 | 594 | 10 |
| `bg_bg` | 604 | 0 | 0 | 594 | 10 |
| `cs_cz` | 604 | 0 | 0 | 594 | 10 |
| `cy_gb` | 604 | 0 | 0 | 594 | 10 |
| `da_dk` | 604 | 0 | 0 | 594 | 10 |
| `de_de` | 604 | 0 | 0 | 594 | 10 |
| `el_gr` | 604 | 0 | 0 | 594 | 10 |
| `en_us` | 604 | 0 | 0 | 604 | 0 |
| `eo_uy` | 604 | 0 | 0 | 594 | 10 |
| `es_es` | 604 | 0 | 0 | 594 | 10 |
| `es_mx` | 604 | 0 | 0 | 594 | 10 |
| `fi_fi` | 604 | 0 | 0 | 594 | 10 |
| `fr_fr` | 604 | 0 | 0 | 594 | 10 |
| `ga_ie` | 604 | 0 | 0 | 594 | 10 |
| `he_il` | 604 | 0 | 0 | 594 | 10 |
| `hu_hu` | 604 | 0 | 0 | 594 | 10 |
| `id_id` | 604 | 0 | 0 | 594 | 10 |
| `it_it` | 604 | 0 | 0 | 594 | 10 |
| `ja_jp` | 604 | 0 | 0 | 594 | 10 |
| `ko_kr` | 604 | 0 | 0 | 594 | 10 |
| `nb_no` | 604 | 0 | 0 | 594 | 10 |
| `nl_nl` | 604 | 0 | 0 | 594 | 10 |
| `pl_pl` | 604 | 0 | 0 | 594 | 10 |
| `pt_br` | 604 | 0 | 0 | 594 | 10 |
| `pt_pt` | 604 | 0 | 0 | 594 | 10 |
| `ro_ro` | 604 | 0 | 0 | 594 | 10 |
| `ru_ru` | 604 | 0 | 0 | 594 | 10 |
| `sk_sk` | 604 | 0 | 0 | 594 | 10 |
| `sv_se` | 604 | 0 | 0 | 594 | 10 |
| `th_th` | 604 | 0 | 0 | 594 | 10 |
| `tr_tr` | 604 | 0 | 0 | 67 | 537 |
| `uk_ua` | 604 | 0 | 0 | 594 | 10 |
| `vi_vn` | 604 | 0 | 0 | 594 | 10 |
| `zh_cn` | 604 | 0 | 0 | 594 | 10 |
| `zh_tw` | 604 | 0 | 0 | 594 | 10 |

Quest and Compass key count: 21
Conclusion: all 36 locale files have complete 604-key coverage and no empty values. English is the safe fallback; English and Turkish contain the deepest native localization, while other locales retain translated/custom entries where available.
