# Pastbound Design Document

## Theme interpretation

Pastbound interprets **“Echoes of the Past”** as a system of recoverable historical memory. Forgotten civilisations leave physical traces; the player recovers their stories, interprets their riddles, and lets the learned relic shape the present.

The catalogue moves across writing and translation, oral and knotted records, calendars, navigation, astronomy, trade routes, court records, ritual symbols, mechanical calculation, manuscript culture, early settlement, imperial mosaics, and twentieth-century exploration. The theme is therefore history-specific rather than a generic ancient-art aesthetic.

## Player fantasy

The player is a field historian rather than a conventional treasure hunter. Every discovery answers three questions: **Who left this behind? What problem did it solve? What does its memory allow the player to do now?** The gameplay loop makes the answers visible through the archive, the journal, and the relic ability.

## Core loop

| Stage | Player action | Historical echo |
|---|---|---|
| 1 | Craft or find an Echo Shard | A moment exists as an unstable fragment. |
| 2 | Feed four shards into the Echo Archive | The archive records a witness and a world timestamp. |
| 3 | Build Resonance Pillars around the archive | A shrine becomes a historical relay. |
| 4 | Read the archive with a Memory Lens | The past is released through light, sound, particles, and a witness afterimage. |
| 5 | Craft or recover an unknown relic | The player encounters a civilisation-inspired memory. |
| 6 | Solve its riddle or pay its XP knowledge cost | Interpretation converts an artefact into understanding. |
| 7 | Equip the known relic in a Curios relic slot | History becomes a persistent loadout choice. |
| 8 | Press V or right-click to awaken it | The player deliberately invokes one historical capability. |
| 9 | Open the journal with R | The expedition becomes legible and collectible. |
| 10 | Trigger an ordinary historical action | Mining clay, reading an archive, crafting a clock, entering a dimension, meeting a villager, smelting metal, navigating by night, or descending into water wakes a themed echo. |
| 11 | Open a relic card | A custom modal turns the echo into a short visual 1-2-3 sequence trial. |
| 12 | Submit the sequence | The server validates the trial, records the echo, grants XP, and awards its advancement. |
| 13 | Complete the codex | Twenty-four echo advancements culminate in the Complete Collection challenge. |

## Relic system

Pastbound contains twenty-four relics. Every relic has a stable registry ID, a standalone 16×16 texture, a model, a historical trace, a knowledge cost, a riddle, an activation cooldown, an active ability, an optional passive pulse, and a paired historical echo trial. Trial data includes its historical action, narrative clue, three-step solution sequence, XP reward, and advancement key. The knowledge record is stored in NeoForge entity persistent data and survives ordinary world saves.

The riddles accept several thematic keywords so that the feature feels like a playful museum interpretation challenge rather than a brittle trivia exam. The XP path remains available for players who want a direct progression route or are playing inside a larger modpack.

## Relic-specific historical abilities

The standard `RelikYetisi` layer uses familiar Minecraft effects such as strength, regeneration, haste, resistance, night vision, water breathing, luck, slow falling, and speed. A second Turkish-named `ozelYankiUygula` layer turns those effects into relic-specific actions. Rosetta reveals nearby living beings with a knowledge glow; Gilgamesh grants an upward heroic lift; Anubis heals and clears fire; Minos performs a vaulting leap; Roma returns a trade nugget; Viking combines navigation vision with haste; Samurai establishes an absorption guard; Maya resets fire and invokes solar protection; Inka accelerates and rewards experience; Harappa returns clay research; Song restores air; Benin shares a discovery glow; Renaissance and Apollo add measured vertical movement; Timbuktu returns manuscript paper; Ilhanlı produces an emerald trade token; and Polynesia combines water travel with directional momentum.

This layered design keeps the result readable in a modpack while ensuring that at least ten relics do something more memorable than applying a generic potion. Every custom action is executed in common logic and remains compatible with both right-click activation and the V shortcut.

| Relic family | Examples | Gameplay language |
|---|---|---|
| Writing and scholarship | Rosetta Taşı, Abbasi Mürekkebi, Timbuktu Kalemi | Knowledge, experience, haste |
| Ritual and statecraft | Anubis Ankhı, Benin Bronz Levhası, Bizans Mozaiği | Regeneration, resistance, health |
| Navigation and exploration | Viking Güneş Pusulası, Polinezya Yıldız Haritası, Apollo 17 Arması | Vision, water breathing, slow falling |
| Time and mathematics | Maya Güneş Çarkı, Antikythera Düzeneği, Aztek Güneş Taşı | Invisibility, movement, fire resistance |
| Trade and material culture | Roma Aureusu, Song Porseleni, Mali Tuz Mührü | Luck, village reputation, environmental safety |

## Curios equipment

Pastbound defines a data-driven Curios slot named `relic`. It provides ten physical positions and accepts the `curios:relic` item tag. Each relic implements `ICurioItem`, validates placement into relic-like slots, applies passive effects from equipped known relics, and exposes active actions to the common gameplay layer.

The V shortcut searches Curios inventory for the first known relic and activates it. Ten physical positions support themed loadouts such as navigation, scholarship, defence, ceremony, and exploration. Eight positions accept relics from the start; the final two positions are gated by a server-validated exchange of ten Netherite Blocks through the journal’s U action.

## GUI-only discovery

Unknown relics never require a chat command. Right-clicking an unknown relic opens the Relik Journal directly. The modal presents the historical riddle as a typed answer field and also offers a visual three-step 1-2-3 trial. Enter submits a custom server payload; the server validates the answer or sequence, records the relic memory, gives the reward, and closes the discovery loop without registering riddle or echo commands.

## Time machine and historical places

The Zaman Makinesi is a craftable historical observatory item that opens a non-pausing GUI. It contains twelve destinations: Uruk Yazı Evi for writing, Termopil Savaş Geçidi for war and strategy, İskenderiye Kütüphanesi for knowledge preservation, Bağdat Pili Atölyesi for early electricity experiments, Antikythera Limanı for mechanical astronomy, Bağdat Bilgi Evi for translation and science, Timbuktu El Yazmaları for caravan scholarship, Tenochtitlan Geçidi for canals and calendars, Polinezya Yıldız Yolu for ocean navigation, Çatalhöyük Yerleşkesi for early community life, Apollo Ay İstasyonu for recent exploration, and İpek Yolu Kervansarayı for cultural exchange.

Selecting a destination sends a common custom payload rather than a command. The server applies a restrained contextual effect and particle vignette, unlocks the associated historical echo, stores the destination in persistent player data, and awards its time-machine advancement. Twelve destination advancements culminate in `complete_expedition`.

## Chest-only relic acquisition and field tools

Relics are not craftable. `TarihSandikGanimeti` observes Village, Trial Chamber, and Ancient City chest openings and adds at most one random relic with low location-sensitive probability, using a persistent chest marker to prevent duplicates. The Time Machine and Furnace on a Stick remain craftable tools because they are expedition equipment rather than historical relics. Furnace on a Stick opens the native furnace menu without placing a block, making it useful for travelling historians.

## Global historical events

`KureselTarihOlaylari` subscribes to the global NeoForge server tick. Every twenty seconds the server rotates one of twelve shared historical periods: Writing Revolution, Nile Canals, Star Voyage, Caravan Road, Calendar Council, Smiths’ Memory, Mosaic Peace, Silk Exchange, Quipu Count, Astrolabe Sky, Rune Watch, and Moon Mission. The event is global rather than local: it broadcasts a server-wide message and prepares the matching historical echo trial for every connected player.

Contextual world signals enrich the period without changing the theme. Books and writable books evoke records, maps and compasses evoke navigation, metal underground evokes metallurgy, gold evokes trade, string evokes textile exchange and knotted accounting, clocks evoke calendars, high ground evokes star observation, water evokes river civilisation, night evokes rune watch, and the End provides a restrained Apollo-era frontier. These event actions provide effects, particles, experience, and trial unlocks without adding unrelated fantasy systems.

## Knowledge and balance

Active effects are short and thematic. Knowledge costs range from two to ten XP levels, and cooldowns range from one hundred to five hundred ticks. Passive pulses occur every forty ticks and are intentionally weaker than the corresponding active ability. The strongest relics have the highest knowledge costs.

| Ability family | Active example | Passive example |
|---|---|---|
| Knowledge | Experience burst | One experience point per pulse |
| Navigation | Night vision | Short night-vision refresh |
| Scholarship | Haste or experience | Minor mining or experience support |
| Defence | Resistance or absorption | Small resistance refresh |
| Exploration | Water breathing or slow falling | Environmental safety refresh |
| Ceremony | Hero of the Village or luck | No passive effect, preserving rarity |

## Archive shrine

The Echo Archive stores four shards, the first witness name, and the game time of the first contribution. A complete archive cannot accept more shards until a Memory Lens reads it. Reading resets the archive, allowing the shrine to become a repeatable expedition site.

Resonance Pillars within the archive’s five-by-three-by-five volume are charged together. The charged blockstate has a dedicated model, a brighter texture, and a higher light level, then fades automatically after 120 ticks. A completed memory therefore changes the shrine in a way that is obvious in ordinary gameplay and screenshots.

## Relic Journal UI

The journal is a non-pausing Minecraft screen with a dark parchment and oxidised-metal palette. It presents a four-column card grid, recovered-memory and echo counters, known and unknown states, historical traces, activation status, slot progression, and the controls needed to continue the expedition. Clicking a card opens a custom texture-backed modal with a typed riddle field and three-step trial; unknown cards remain useful instead of becoming empty placeholders. The separate Time Machine screen uses a twelve-card historical route map and custom time-machine texture.

The R and V keys use a dedicated Pastbound key category. V sends a server-authoritative common custom payload, so single-player and dedicated-server behavior remain consistent without a chat command.

## Art direction

The palette combines aged paper, copper, amethyst, teal echo light, carved stone, oxidised bronze, and material-specific accents. Relic silhouettes differ deliberately: tablets, seals, beads, coins, tools, lenses, maps, badges, and rune stones. The texture generator uses asymmetrical highlights, dark one-pixel outlines, material palettes, and hand-placed glyph patterns instead of a single recoloured icon.

No new biome or stone family is introduced in this version. The project consequently does not create a partial wood or stone set with missing planks, doors, trapdoors, slabs, stairs, vertical stairs, polished blocks, or tools.

## Packaging

The project includes 36 locale files, 26 non-relic recipes including the Time Machine and Furnace on a Stick, 4 research-material recipes, 25 historical echo advancement definitions, 13 time-machine advancement definitions, chest relic distribution logic, block loot tables, blockstates, block models, item models, ten-position Curios slot data with eight-starting/two-upgrade gating, a Curios item tag, source-controlled texture, locale, historical-data, and time-machine generators, a global event engine, custom ability layer, complete README, and an MIT license.

## Acceptance criteria

| Criterion | Verification |
|---|---|
| NeoForge target | `gradle.properties` uses Minecraft 26.2 and NeoForge 26.2.0.66. |
| Curios support | Gradle dependency, metadata dependency, slot JSON, item tag, and `ICurioItem` implementation exist. |
| Relic count | `RelikTanimi` contains twenty-four entries and the registry generates twenty-four items. |
| Historical events | `TarihYankisi` contains twenty-four event/action trials and `TarihYankilari` binds them to NeoForge actions. |
| Custom relic abilities | `ozelYankiUygula` adds relic-specific movement, healing, reveal, trade, resource, air, fire, and aura behavior on top of classic effects. |
| Global historical events | `KureselTarihOlaylari` rotates twelve server-wide periods and prepares shared trials for connected players. |
| Mini games | The journal modal accepts typed riddle answers or three symbol choices and server-validates them through a common custom payload. |
| Advancement progression | Each historical echo has an advancement and all twenty-four combine into `complete_collection`. |
| Curios capacity | The data-driven `relic` slot has ten positions, eight available by default, and two unlocked for ten Netherite Blocks through the GUI. |
| Knowledge paths | Each relic supports an XP route and a riddle route. |
| Server authority | V, riddle answers, sequence trials, time-machine destinations, and slot upgrades use common custom payloads; active effects execute in common logic. |
| UI | R opens the Relik Journal, unknown relics open its riddle modal, and the Time Machine opens a twelve-destination historical GUI. |
| Localization | At least thirty-two locale files exist with the complete item and UI key set. |
| Texture quality | Every relic is a standalone 16×16 PNG generated from a deterministic palette script. |
| Build output | The Gradle jar task creates `build/libs/Pastbound-1.1.0.jar` with the current GUI, time-machine, loot, and slot systems. |
| Comments | Source code contains no line-style or Javadoc comment lines. |

## References

[1]: https://docs.neoforged.net/docs/gettingstarted/ "Getting Started with NeoForge"

[2]: https://github.com/NeoForgeMDKs/MDK-26.2-NeoGradle "NeoForge 26.2 NeoGradle MDK"

[3]: https://docs.illusivesoulworks.com/curios/ "Curios documentation"

[4]: https://github.com/TheIllusiveC4/Curios "Curios source repository"
