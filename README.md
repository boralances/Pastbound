# Pastbound

**Pastbound** is a CurseForge ModJam 2026 entry for **“Echoes of the Past.”** The mod treats history as a playable force rather than a decorative background. Forgotten civilizations leave behind physical relics; players recover their stories, solve the riddles attached to them, place them in a Curios relic slot, and bring a historical ability into the present.

Pastbound targets **Minecraft 26.2**, **NeoForge 26.2.0.66**, and **Curios 16.0.0+26.2**. The Gradle project uses the official NeoGradle MDK structure and Java 25. The final archive is named **`Pastbound-1.1.0.jar`**.

## Design pitch

> Every forgotten moment leaves an echo. The player does not merely collect an artefact; the player learns its story, answers the question it carries, and decides when that history should shape the present.

The central loop begins with crafting or discovering an **Echo Shard**, a crystallized fragment of a recorded moment. Four shards complete an **Echo Archive**. A **Memory Lens** reads the completed archive and releases its witness memory through nearby **Resonance Pillars**, creating a temporary historical shrine with sound, light, particles, and a short afterimage effect.

The second layer is the relic hunt. Pastbound contains twenty-four history-inspired relics spanning writing systems, mathematics, navigation, astronomy, trade, statecraft, ritual, and exploration. A relic begins as unknown. The player can hold Shift and use it while paying its XP-level knowledge cost, or answer its riddle with `/pastbound riddle <relic> <answer>`. Once understood, the relic remembers the player permanently through NeoForge entity persistent data.

A known relic can be placed into the Curios **relic** slot. The **V** key awakens the first known relic equipped in that slot, while right-clicking the relic also activates it. Passive abilities pulse every two seconds while a known relic is worn. The **R** key opens the in-game Relic Journal, where the player can see the recovered-memory count, known relics, historical traces, activation status, and the controls needed to continue the expedition.

## Core content

| Content | Historical role | Gameplay role |
|---|---|---|
| Echo Archive | A reconstructed memory repository | Stores four Echo Shards, the first witness, and the recording time in a persistent BlockEntity. |
| Resonance Pillar | A relay for a recovered historical signal | Activates across a 5×3×5 shrine volume and fades after 120 ticks. |
| Echo Shard | A crystallized instant | Feeds the archive and is the shared catalyst for relic crafting. |
| Memory Lens | An interpretive optical instrument | Reads complete memories, consumes durability, and awakens the shrine. |
| Curios relic slot | A wearable museum case | Adds five `relic` slots accepting the `curios:relic` item tag. |
| Relic Journal | A field notebook for the expedition | Opens with **R** and shows progression in a Minecraft-native dark parchment style. |
| Relic awakening | History made active | Uses **V** or right-click to trigger the learned relic’s ability. |
| Riddle knowledge | A playful interpretation layer | Makes identification possible without XP when the player solves the relic’s clue. |
| Historical Echo Trials | A sequence of playable archival moments | Adds twenty-four event-linked historical mini games with server-validated 1-2-3 sequences, XP rewards, and advancement unlocks. |
| Chronicle research materials | Reconstructed scholarship supplies | Adds Chronicle Scrap, History Ink, Time Stone, and Echo Seal as a four-step crafting chain. |
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

Each relic has its own 16×16 PNG, item model, item name, historical trace, riddle, knowledge cost, activation cooldown, active effect, passive pulse behavior, and shapeless recipe using an Echo Shard plus a related vanilla material. Each relic is also paired with a historical echo trial, from the Papyrus Cipher and Uruk Seal to the Timbuktu Caravan, Apollo Moon Walk, and Rune Stone.

## Relic-specific abilities

The base RelikYetisi effect remains recognizable in Minecraft terms, while `ozelYankiUygula` adds a relic-specific historical action. At least ten relics now combine the classic effect with behavior that cannot be reduced to a potion icon: Rosetta reveals nearby living entities with a knowledge glow; Gilgamesh grants a heroic lift; Anubis heals and extinguishes fire; Minos gives a vaulting leap; Roma produces a trade nugget; Viking combines haste and night vision; Samurai creates a defensive buffer; Maya clears fire and invokes solar protection; Inka accelerates the player while awarding knowledge; Harappa returns clay research material; Song restores air; Benin shares a discovery glow; Renaissance and Apollo provide measured vertical movement; Timbuktu produces paper research; Ilhanlı grants a trade emerald; and Polynesia combines water travel with directional momentum.

These custom actions are applied after the classic effect and are encoded with Turkish method and variable names in the common gameplay layer. They work from both right-click and the V shortcut, so Curios loadouts stay useful without introducing a second activation system.

## Global historical events

`KureselTarihOlaylari` listens to the global server tick and rotates twelve server-wide historical periods: Writing Revolution, Nile Canals, Star Voyage, Caravan Road, Calendar Council, Smiths’ Memory, Mosaic Peace, Silk Exchange, Quipu Count, Astrolabe Sky, Rune Watch, and Moon Mission. Each period broadcasts a world-level message, unlocks its related echo trial for every connected player, and can add context-aware effects or particles when the player carries a relevant vanilla object such as a book, map, ingot, string, or clock.

The player-facing world actions include writing with books, reading routes with maps and compasses, carrying metal underground, trading with gold, travelling over high ground, entering water, surviving the End, walking at night, and following the server-wide era rotation. These are global history signals rather than arbitrary fantasy triggers, keeping the mod centered on historical technologies, travel, records, craft, and exchange.

## Controls and commands

The **R** key opens the Relic Journal. Clicking a relic card opens a custom historical trial modal with a visual sequence puzzle; choose three symbols with **1**, **2**, or **3**, then press **Enter** to submit. The **V** key sends a server-authoritative `pastbound activate` command. Right-clicking an unknown relic displays its riddle and XP cost; holding Shift while using it pays the cost and identifies it. The commands `/pastbound journal`, `/pastbound codex`, `/pastbound activate`, `/pastbound echo <echo> <sequence>`, and `/pastbound riddle <relic> <answer>` expose the full discovery loop.

## Curios integration

Curios is a required runtime dependency. The project declares the Curios Maven repository, compiles against the `curios-neoforge:16.0.0+26.2:api` classifier, includes the full Curios runtime in `localRuntime`, and provides the data-driven slot file at `data/curios/curios/slots/relic.json`. The slot has five positions, native Curios GUI behavior, a cosmetic companion slot, a custom empty-slot icon, and an item validator based on `data/curios/tags/items/relic.json`.

## Localization

The project includes **36 locale JSON files**, exceeding the requested 32-language target. Every locale contains the Pastbound UI strings, keybind labels, archive feedback, relic activation messages, the complete relic catalogue, historical trial labels, modal instructions, event progress, and advancement keys. The generated set includes English, Turkish, German, French, Spanish variants, Italian, Portuguese variants, Russian, Ukrainian, Polish, Dutch, Nordic locales, Central European locales, Chinese variants, Japanese, Korean, Vietnamese, Thai, Indonesian, Arabic, Hebrew, Afrikaans, Esperanto, Irish, and Welsh.

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

The original compact texture generator remains available for the core five assets:

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
│   └── generate_textures.py
└── src/main/
    ├── java/dev/pastbound/
    │   ├── ModId.java
    │   ├── PastboundMemory.java
    │   ├── block/
    │   ├── block/entity/
    │   ├── history/
    │   │   ├── KureselTarihOlayi.java
    │   │   ├── KureselTarihOlaylari.java
    │   │   ├── TarihYankisi.java
    │   │   └── TarihYankilari.java
    │   ├── client/
    │   │   ├── RelikClient.java
    │   │   ├── RelikClientOyun.java
    │   │   └── ui/RelikDefteriEkrani.java
    │   ├── command/PastboundKomutlari.java
    │   ├── item/
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
            ├── loot_table/
            ├── recipes/
            └── tags/
```

## Build instructions

Install a **64-bit Java 25 JDK** and verify that Gradle sees it. From the project root, run:

```bash
cd Pastbound
java -version
./gradlew --version
./gradlew clean build
```

The final file is created at:

```text
Pastbound/build/libs/Pastbound-1.1.0.jar
```

For Windows PowerShell, use:

```powershell
cd Pastbound
java -version
.\gradlew.bat clean build
```

The development client and server tasks are:

```bash
./gradlew runClient
./gradlew runServer
```

If a local Gradle cache is incomplete, use:

```bash
./gradlew --refresh-dependencies clean build
```

The repository also contains a sandbox-validated 1.1.0 JAR built from the manually verified mapped 26.2 classpath. On a low-memory environment where the NeoForm decompiler is killed before `compileJava`, regenerate textures and locales, compile the Java sources with a Java 25 compiler, stage the resulting classes under `build/classes/java/main`, and run the normal Gradle `jar` task while excluding only `compileJava` and `neoFormDecompile`. A normal development machine with sufficient memory should use the standard `./gradlew clean build` command above.

## License

Pastbound source code is released under the MIT License. Minecraft, NeoForge, and Curios remain the property of their respective owners. Pastbound is a fan-made contest project and is not affiliated with Mojang, NeoForged, or Illusive Soulworks.

## References

[1]: https://docs.neoforged.net/docs/gettingstarted/ "Getting Started with NeoForge"

[2]: https://github.com/NeoForgeMDKs/MDK-26.2-NeoGradle "NeoForge 26.2 NeoGradle MDK"

[3]: https://docs.illusivesoulworks.com/curios/ "Curios documentation"

[4]: https://github.com/TheIllusiveC4/Curios "Curios source repository"
