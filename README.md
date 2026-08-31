# Pastbound

**Pastbound** is a CurseForge ModJam 2026 entry for **“Echoes of the Past.”** The mod treats history as a playable force rather than a decorative background. Forgotten civilizations leave behind physical relics; players recover their stories through independent restoration fragments, place them in a Curios relic slot, and bring a historical ability into the present.

Pastbound targets **Minecraft 26.2**, **NeoForge 26.2.0.66**, and **Curios 16.0.0+26.2**. The Gradle project uses the official NeoGradle MDK structure and Java 25. The current release archive is named **`Pastbound-3.5.0.jar`**.

## Design pitch

> Every forgotten moment leaves an echo. The player does not merely collect an artefact; the player restores its fragments, learns its story, and decides when that history should shape the present.

The central loop begins with crafting or discovering an **Echo Shard**, a crystallized fragment of a recorded moment. Four shards complete an **Echo Archive**. A **Memory Lens** reads the completed archive and releases its witness memory through nearby **Resonance Pillars**, creating a temporary historical shrine with sound, light, particles, and a short afterimage effect.

The second layer is the relic hunt. Pastbound contains twenty-four history-inspired relics spanning writing systems, mathematics, navigation, astronomy, trade, statecraft, ritual, and exploration. A relic begins as unknown. The player can hold Shift and use it while paying its XP-level knowledge cost, or open its GUI restoration activity and recover five independent historical fragments. Once understood, the relic remembers the player permanently through NeoForge entity persistent data.

A known relic can be placed into the Curios **relic** slot. The **V** key awakens the first known relic equipped in that slot, while right-clicking the relic also activates it. Passive abilities pulse every two seconds while a known relic is worn. The **R** key opens the in-game Relic Journal, where the player can see the recovered-memory count, known relics, historical traces, activation status, and the controls needed to continue the expedition.

## Core content

| Content | Historical role | Gameplay role |
|---|---|---|
| Echo Archive | A reconstructed memory repository | Stores four Echo Shards, the first witness, and the recording time in a persistent BlockEntity. |
| Resonance Pillar | A relay for a recovered historical signal | Activates across a 5×3×5 shrine volume and fades after 120 ticks. |
| Echo Shard | A crystallized instant | Feeds the archive and is the shared catalyst for relic crafting. |
| Memory Lens | An interpretive optical instrument | Reads complete memories, consumes durability, and awakens the shrine. |
| Curios relic slot | A wearable museum case | Provides eight open `relic` slots at first and two more after the Netherite exchange, accepting the `curios:relic` item tag. |
| Relic Journal | A field notebook for the expedition | Opens with **R** and shows progression in a Minecraft-native dark parchment style. |
| Relic awakening | History made active | Uses **V** or right-click to trigger the learned relic’s ability. |
| Historical restoration | A playful interpretation layer | Makes identification possible through five independent memory fragments, while Shift-right-click remains the direct XP route. |
| Historical field missions | Active archival moments | Adds movement, perimeter exploration, monument breaking, steel mining, smelting, forging, NPC dialogue, XP rewards, and advancement unlocks. |
| Chronicle research materials | Reconstructed scholarship supplies | Adds Chronicle Scrap, History Ink, Time Stone, and Echo Seal as a four-step crafting chain. |
| Time Machine | A reconstructed historical observatory | Opens a GUI with twelve destinations covering writing, war, libraries, early electricity, mechanical astronomy, scholarship, trade, navigation, settlement, and lunar exploration. |
| Furnace on a Stick | Portable field equipment | Opens the native furnace GUI without placing a block. |
| Chest relic rewards | Museum-grade discoveries | Relics are never craftable and appear only as rare additions to Village, Trial Chamber, and Ancient City chest openings. |
| Relic slot progression | A Netherite-backed museum case | Eight relic slots begin open; ten Netherite Blocks exchanged through the journal open two more slots. |
| Global history pulse | A server-wide historical era | Rotates twelve historical periods and opens their echo trial for every connected player. |

## Relic catalogue

| Relic | Historical inspiration | Ability theme |
|---|---|---|
| Rosetta Taşı | Multilingual decipherment | Information and experience |
| Gılgamış Tableti | Ancient epic tradition | Strength |
| Anubis Ankhı | Nile symbolism | Regeneration |
| Minos Labirent Mührü | Minoan Crete | Jump and agility |
| Roma Aureusu | Roman imperial currency | Luck |
| Viking Güneş Pusulası | North Atlantic navigation | Night vision |
| Samuray Kabzası | Japanese warrior culture | Resistance |
| Maya Güneş Çarkı | Mesoamerican calendrical memory | Invisibility |
| İnka Quipusu | Knotted record keeping | Speed |
| Harappa Mührü | Indus Valley seals | Mining |
| Song Porseleni | Silk Road exchange | Water breathing |
| Benin Bronz Levhası | Courtly historical record | Resistance |
| Aztek Güneş Taşı | Cycles of time | Fire resistance |
| Abbasi Mürekkebi | Translation and scholarship | Strength |
| Rönesans Astrolabı | Renaissance astronomy | Slow falling |
| Antikythera Düzeneği | Mechanical celestial calculation | Dolphin’s Grace |
| Çatalhöyük Boncuğu | Early settled community | Health |
| Bizans Mozaiği | Layered imperial memory | Resistance |
| Timbuktu Kalemi | Manuscript scholarship | Experience |
| Apollo 17 Arması | Recent exploration history | Night vision and slow falling |
| İlhanlı Madalyonu | Eurasian caravan exchange | Hero of the Village |
| Polinezya Yıldız Haritası | Oceanic wayfinding | Dolphin’s Grace |
| Mali Tuz Mührü | Saharan trade | Luck |
| İskandinav Rune Taşı | Northern inscription tradition | Invisibility |

Each relic has its own 16×16 PNG, item model, item name, historical trace, restoration activity, knowledge cost, activation cooldown, active effect, passive pulse behavior, and chest-only acquisition rule. Relics intentionally have no crafting recipe; they are rare rewards added to Village, Trial Chamber, and Ancient City chest openings. Each relic is also paired with a historical echo trial, from the Papyrus Cipher and Uruk Seal to the Timbuktu Caravan, Apollo Moon Walk, and Rune Stone.

## Relic-specific abilities

The base RelikYetisi effect remains recognizable in Minecraft terms, while `ozelYankiUygula` adds a relic-specific historical action. At least ten relics now combine the classic effect with behavior that cannot be reduced to a potion icon: Rosetta reveals nearby living entities with a knowledge glow; Gilgamesh grants a heroic lift; Anubis heals and extinguishes fire; Minos gives a vaulting leap; Roma produces a trade nugget; Viking combines haste and night vision; Samurai creates a defensive buffer; Maya clears fire and invokes solar protection; Inka accelerates the player while awarding knowledge; Harappa returns clay research material; Song restores air; Benin shares a discovery glow; Renaissance and Apollo provide measured vertical movement; Timbuktu produces paper research; Ilhanlı grants a trade emerald; and Polynesia combines water travel with directional momentum.

These custom actions are applied after the classic effect and are encoded with Turkish method and variable names in the common gameplay layer. The first sixteen relics also apply the registered `tarih_yankisi` MobEffect, which periodically returns a small experience pulse and an enchantment-particle memory shimmer. They work from both right-click and the V shortcut, so Curios loadouts stay useful without introducing a second activation system.

## Global historical events

`KureselTarihOlaylari` listens to the global server tick and rotates twelve server-wide historical periods: Writing Revolution, Nile Canals, Star Voyage, Caravan Road, Calendar Council, Smiths’ Memory, Mosaic Peace, Silk Exchange, Quipu Count, Astrolabe Sky, Rune Watch, and Moon Mission. Each period broadcasts a world-level message, unlocks its related echo trial for every connected player, and can add context-aware effects or particles when the player carries a relevant vanilla object such as a book, map, ingot, string, or clock.

The player-facing world actions include writing with books, reading routes with maps and compasses, carrying metal underground, trading with gold, travelling over high ground, entering water, surviving the End, walking at night, and following the server-wide era rotation. These are global history signals rather than arbitrary fantasy triggers, keeping the mod centered on historical technologies, travel, records, craft, and exchange.

Version 1.4.0 adds a natural-world archaeology layer. New chunks can reveal rare Overworld historical biome pockets with Uruk floodplain or Tenochtitlan chinampa ambience, cedar or cypress growth, water channels, archaeology markers, passive wildlife and dimension-aware hostile spawns. Time Stone and Chronicle ores now form multi-block veins in the Overworld, while Nether Time Stone and Ash Chronicle veins form in the Nether and End Echo and Void Chronicle veins form in the End. Nether observation monuments and End sky-watch platforms turn each dimension into a distinct historical field site. These materials connect the present world to the same writing, electricity, trade, and astronomical history carried by the twelve time-machine destinations.

The living-history quest layer now requires a complete field expedition instead of a single conversation. A Memory Lens inspection step now reveals the hidden layer of each era’s archive monument before the monument can be broken. Every destination gives the player a period-specific objective: speak with all four witnesses, cross the scene perimeter, return to the central beacon and break that era’s own archive monument. Baghdad adds the full workshop chain of mining three steel veins, smelting, crafting a steel plate and repairing the historical forge. The time-machine screen previews the active mission before travel, and the scene HUD shows both the mission and the destination’s ecosystem clue.

## Controls and GUI actions

The **R** key opens the Relic Journal. Clicking an unknown relic opens a custom restoration modal with five independent fragment actions; fragments can be selected in any order and no order-dependent puzzle remains. The new **Chronicle Compass** is a craftable field tool with a **32-block horizontal radius** and a **24-block vertical radius**. Right-clicking scans the 65 × 49 × 65 search volume, selects the nearest historical ore by three-dimensional distance, reports its localized name, distance and coordinates, and then applies a short 60-tick cooldown to prevent spam. If no historical ore is present in that volume, it gives a localized no-discovery message. This turns exploration into a deliberate archaeological hunt rather than a passive search.
The **V** key sends a server-authoritative custom payload rather than a chat command. The first sixteen relics also apply the custom `tarih_yankisi` effect, while every relic keeps its own historical ability branch. Shift-right-click remains the direct XP identification route. The **U** key in the journal offers the server-validated Netherite slot exchange. Registry IDs such as `zaman_makinesi`, `chronicle_compass` and `time_stone_ore` remain stable technical identifiers for commands and data packs; their player-facing names are English by default and translate when the client language is changed. The locale generator now covers 36 Minecraft locale files, including the requested 32-language set, with dedicated Chronicle Compass names and messages in each supported locale and safe English fallback for future keys. The final gameplay loop uses GUI screens and common custom payloads for restoration activities, field missions, dialogue, activation, time travel, and slot upgrades; no chat command is required for discovery.

## Curios integration

Curios is a required runtime dependency. The project declares the Curios Maven repository, compiles against the `curios-neoforge:16.0.0+26.2:api` classifier, includes the full Curios runtime in `localRuntime`, and provides the data-driven slot file at `data/curios/curios/slots/relic.json`. The slot exposes ten physical positions with native Curios GUI behavior, a cosmetic companion slot, a custom empty-slot icon, and an item validator based on `data/curios/tags/item/relic.json`. Eight positions are usable by every player from the start; the final two are unlocked together by exchanging ten Netherite Blocks through the journal’s U action.

## Localization

The project includes **36 locale JSON files**, exceeding the requested 32-language target. Every locale contains the Pastbound UI strings, keybind labels, archive feedback, relic activation messages, the complete relic catalogue, historical trial labels, modal instructions, time machine labels, slot progression text, chest-loot feedback, event progress, and advancement keys. The generated set includes English, Turkish, German, French, Spanish variants, Italian, Portuguese variants, Russian, Ukrainian, Polish, Dutch, Nordic locales, Central European locales, Chinese variants, Japanese, Korean, Vietnamese, Thai, Indonesian, Arabic, Hebrew, Afrikaans, Esperanto, Irish, and Welsh.

The locale generator is deterministic and can be rerun with:

```bash
python3 tools/generate_locales.py
python3 tools/generate_history_data.py
```

## Texture production

All relic sprites and supporting assets are deterministic 16×16 pixel art generated from explicit RGB palette data. The blueprint intentionally uses uneven highlights, dark edge pixels, small asymmetries, material-specific palettes, and distinct hand-drawn glyph patterns instead of a single recoloured placeholder. The archive, pillar, charged pillar, Curios slot icon, 256×256 journal texture, 256×192 historical trial modal texture, and four research-material sprites are generated by the same source-controlled script.

```bash
python3 tools/generate_relic_assets.py
```

The generator also produces the Time Machine item, Furnace on a Stick item, dedicated time-machine GUI texture, separate wood door/trapdoor sprites with visible hardware, and distinct historical ore textures for Overworld, Nether, and End materials. The original compact texture generator remains available for the core five assets:

```bash
python3 tools/generate_textures.py
```

## Project tree

```text
Pastbound/
├── build.gradle
├── gradle.properties
├── gradlew
├── gradlew.bat
├── settings.gradle
├── README.md
├── LICENSE
├── docs/
│   └── design.md
├── tools/
│   ├── generate_locales.py
│   ├── generate_relic_assets.py
│   ├── generate_relic_recipes.py
│   ├── generate_history_data.py
│   ├── generate_time_machine_data.py
│   ├── generate_textures.py
│   └── validate_assets.py
└── src/main/
    ├── java/dev/pastbound/
    │   ├── ModId.java
    │   ├── PastboundMemory.java
    │   ├── effect/TarihYankisiEtkisi.java
    │   ├── block/
    │   ├── block/entity/
    │   ├── history/
    │   │   ├── KureselTarihOlayi.java
    │   │   ├── KureselTarihOlaylari.java
    │   │   ├── TarihDonemi.java
    │   │   ├── TarihSandikGanimeti.java
    │   │   ├── TarihYankisi.java
│   │   ├── TarihYankilari.java
│   │   ├── TarihMadenleri.java
│   │   └── ZamanMakinesiMantigi.java

    │   ├── client/
    │   │   ├── RelikClient.java
    │   │   ├── RelikClientOyun.java
    │   │   └── ui/
    │   │       ├── RelikDefteriEkrani.java
│   │       ├── TarihCanlandirmaEkrani.java
│   │       ├── TarihKoyluKonusmaEkrani.java
│   │       └── ZamanMakinesiEkrani.java


    │   ├── item/
    │   │   ├── FirinCubuguItem.java
    │   │   └── ZamanMakinesiItem.java
    │   ├── network/
    │   │   ├── PastboundAg.java
    │   │   └── PastboundPaketi.java
    │   ├── relic/
    │   │   ├── RelikItem.java
    │   │   ├── RelikMantigi.java
    │   │   ├── RelikTanimi.java
    │   │   └── RelikYetisi.java
    │   └── registry/
    └── resources/
        ├── META-INF/neoforge.mods.toml
        ├── pack.mcmeta
        ├── assets/pastbound/
        │   ├── blockstates/
        │   ├── lang/
        │   ├── models/
        │   └── textures/
        ├── data/curios/
        │   ├── curios/slots/relic.json
        │   └── tags/items/relic.json
        └── data/pastbound/
            ├── advancements/history/
            ├── advancements/time_machine/
            ├── loot_table/
            ├── recipe/
            └── tags/
```

## License

Pastbound source code is released under the MIT License. Minecraft, NeoForge, and Curios remain the property of their respective owners. Pastbound is a fan-made contest project and is not affiliated with Mojang, NeoForged, or Illusive Soulworks.

## References

[1]: https://docs.neoforged.net/docs/gettingstarted/ "Getting Started with NeoForge"

[2]: https://github.com/NeoForgeMDKs/MDK-26.2-NeoGradle "NeoForge 26.2 NeoGradle MDK"

[3]: https://docs.illusivesoulworks.com/curios/ "Curios documentation"

[4]: https://github.com/TheIllusiveC4/Curios "Curios source repository"
