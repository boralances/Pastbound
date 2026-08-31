# Pastbound

Pastbound is a NeoForge mod for the **Echoes of Legend** ModJam. It treats history as something the player can investigate and use, not as decoration in the background. You play as a field historian: recover fragments, restore relics, visit historical places through the Time Machine, and decide how the knowledge of the past should affect the present.

The mod is built around a simple idea: every artefact should have a story, and that story should be visible in gameplay. A relic is not just another stat item. It has a historical trace, a restoration step, an ability, and a place in the wider archive.

## Project status

| Item | Current value |
|---|---|
| Mod | Pastbound |
| Release | 2.5.9 |
| Minecraft / NeoForge target | 26.2 / NeoForge 26.2.0.66 |
| Curios | 16.0.0+26.2 |
| Java | 25 |
| Loader | NeoForge |
| License | MIT |
| Current release | [Pastbound v2.5.9](https://github.com/boralances/Pastbound/releases/tag/v2.5.9) |

The version numbers above are taken from `gradle.properties`, which is the source of truth for the project version and dependency targets.

## What the player does

The first part of the journey is archival work. Echo Shards are placed into an Echo Archive, Resonance Pillars are built around it, and a Memory Lens is used to reveal the recovered witness. This turns a small structure into a repeatable historical shrine with its own light, sound, particles, and afterimage.

The second part is the relic hunt. Pastbound currently contains twenty-four history-inspired relics covering writing, translation, navigation, astronomy, trade, statecraft, ritual, mathematics, settlement, and exploration. Relics are discovered through chest loot and cannot be crafted. An unknown relic can be restored through its journal activity or identified by spending the required knowledge levels. Once restored, it can be worn in a Curios relic slot and awakened with **V** or by right-clicking it.

The Relic Journal opens with **R**. It records recovered memories, known relics, historical traces, activation status, echo trials, and the progression of the relic slots. The first eight relic positions are available immediately; the final two are unlocked through the journal by exchanging ten Netherite Blocks.

The Time Machine is an interactive historical observatory rather than a simple teleport button. Its paged interface previews each destination, highlights the selected route, and explains the arrival gate before travel. After entering a historical dimension, the player starts at the gate and walks to the marked site instead of appearing directly at the central objective. The twelve destinations cover subjects such as early writing, war and strategy, libraries, translation, mechanical astronomy, trade, ocean navigation, early settlement, and lunar exploration. Each destination has its own dimension-specific expedition objective and echo advancement; the Overworld is not used as a mandatory quest chain.

## Main systems

| System | Purpose in the mod |
|---|---|
| Echo Archive and Resonance Pillars | Recover and replay historical witness memories with validated Echo Shards. |
| Relic Journal | Provide the player’s research log, restoration interface, and progression view. |
| Curios relic slot | Turn restored relics into a deliberate equipment loadout. |
| Time Machine | Travel to themed historical dimensions through a mission-focused interface. |
| Ancient Storage | Archive items across a connected network with compaction, sorting, and quick-archive support. |
| Furnace on a Stick | Open a server-authoritative portable furnace without placing a block. |
| Crafting Table on a Stick | Use a portable crafting interface while travelling. |
| Historical relic effects | Add ten custom status effects with dedicated icons and Turkish/English names. |
| Global historical events | Rotate server-wide historical periods and unlock related echo trials. |
| Advancements | Track relic research, time-machine expeditions, workstations, archives, and the Curator’s Seal. |

The portable furnace also has an enhanced tier with faster processing. Ancient Storage is designed as an in-world archive rather than a generic technology network: it compacts matching stacks, sorts deterministically, and supports quick archiving with **Shift-right-click**. Ancient Storage and Echo Archive use explicit inventory item models and textures, so they remain identifiable outside the world.

## Relics and custom effects

Each relic has its own texture, model, historical description, restoration activity, knowledge cost, activation cooldown, and ability branch. The common relic logic uses Turkish method and variable names, as required by the project’s coding style. Comments are intentionally not used in the Java source; the code is kept readable through class structure, naming, and small focused methods.

The ten newer relic effects are registered as real NeoForge `MobEffect` instances and have matching textures in `assets/pastbound/textures/mob_effect/`. Their names are generated for both Turkish and English locales. Status effects are not granted on a random timer: they come from explicit relic activation or a completed, selected witness dialogue. The effects are inspired by the relics rather than presented as arbitrary potion upgrades: Rosetta Knowledge, Gilgamesh Endurance, Anubis Cleansing, Minos Leap, Roman Aureus, Viking Night Sight, Samurai Guard, Maya Calendar, Inca Knots, and Harappan Clay Tablets.

## Controls

| Key or action | Result |
|---|---|
| **R** | Open the Relic Journal. |
| **V** | Awaken the first known relic in the Curios relic inventory. |
| **U** | Use the journal’s Netherite exchange to unlock the final relic slots. |
| Right-click a known relic | Awaken its ability. |
| Right-click an unknown relic | Open its restoration interface. |
| Shift-right-click an unknown relic | Identify it through the direct experience-cost route. |
| Shift-right-click Ancient Storage | Quickly archive the held item stack. |
| Right-click Chronicle Compass | Scan the nearby area for historical ores. |

All important actions use server-authoritative custom payloads. The mod does not depend on chat commands for relic discovery, restoration, activation, or Time Machine travel.

## Requirements

A development checkout needs a **64-bit Java 25 JDK**. The built mod requires the Minecraft/NeoForge version declared in `gradle.properties` and Curios at runtime. Curios is available as a compile-time API dependency and as a local runtime dependency for development.

| Dependency | Version used by this checkout |
|---|---|
| Java | 25 |
| Minecraft target | 26.2 |
| NeoForge | 26.2.0.66 |
| Curios | 16.0.0+26.2 |

## Getting the source

The complete source code is available in the [Pastbound GitHub repository](https://github.com/boralances/Pastbound). Stable packaged builds are attached to the [GitHub Releases page](https://github.com/boralances/Pastbound/releases).

## Building a JAR with Gradle

For a normal development machine, the Gradle build is the preferred route. Before compiling, regenerate the source-controlled data and assets if you have changed their generators.

```bash
cd Pastbound
java -version
./gradlew --version

python3 tools/generate_relic_assets.py
python3 tools/generate_relic_recipes.py
python3 tools/generate_history_data.py
python3 tools/generate_time_machine_data.py
python3 tools/generate_locales.py
python3 tools/build_time_machine_logo.py
python3 tools/build_relic_effect_textures.py

python3 tools/audit_assets.py
./gradlew clean build
```

The finished development JAR is written to:

```text
build/libs/Pastbound-2.5.9.jar
```

The version in that filename changes automatically when `mod_version` in `gradle.properties` changes. Do not rename the file by hand when preparing a release; update the project version, rebuild, and let Gradle produce the matching artifact.

On Windows PowerShell, use the same workflow with the Gradle wrapper batch file:

```powershell
cd Pastbound
java -version
.\gradlew.bat clean build
```

If Gradle’s dependency cache is incomplete, retry with:

```bash
./gradlew --refresh-dependencies clean build
```

## Fallback release packaging

Some development environments occasionally stall while NeoForge downloads or decompiles its asset mappings. In that situation, use the repository’s deterministic fallback packager after Java classes have been compiled successfully.

```bash
cd Pastbound
python3 tools/audit_assets.py
./gradlew compileJava --offline --no-daemon
python3 tools/assemble_release_jar.py
```

`assemble_release_jar.py` reads `mod_version` from `gradle.properties`, takes compiled classes from `build/classes/java/main`, takes resources from `src/main/resources`, expands the NeoForge metadata placeholders, and writes the result to `build/libs/Pastbound-<version>.jar`. The packager sorts archive entries so repeated builds are stable and easy to compare.

For Pastbound 2.5.9, the fallback output is `build/libs/Pastbound-2.5.9.jar`. Before distributing it, check the archive contents and its version metadata:

```bash
unzip -p build/libs/Pastbound-2.5.9.jar META-INF/neoforge.mods.toml | grep -E 'version=|logoFile'
unzip -l build/libs/Pastbound-2.5.9.jar | grep -E 'textures/mob_effect|pastbound_logo.png|RelikMantigi.class'
```

The current 2.5.9 release contains the deterministic 512×512 logo made from the actual Time Machine texture. It does not use AI-generated logo artwork. Dimension distance hints are sent once per expedition instead of repeatedly filling the chat.

## Validation checklist

Run the asset validator before committing texture or model changes. It checks JSON syntax, model texture references, project-local parent models, blockstate references including multipart variants, PNG dimensions, and alpha handling. GUI and mob-effect textures are allowed to use their intended non-16×16 dimensions.

```bash
python3 -m py_compile tools/*.py
python3 tools/audit_assets.py
git diff --check
./gradlew compileJava --offline --no-daemon
```

A healthy audit prints a line in this form:

```text
ASSET_AUDIT_OK models=<number> textures=<number>
```

For a complete local build, replace `compileJava` with `build`. If the Java compile succeeds but the full Gradle build is blocked by a NeoForge cache or decompiler problem, the fallback packaging workflow above is the intended project path.

## Running the development client

NeoForge’s development runs use the project’s `run/` directory and can be started with:

```bash
./gradlew runClient
./gradlew runServer
```

The server run includes `--nogui`. Curios is available through the project’s `localRuntime` configuration, so the development client and server can exercise the relic slot without copying the dependency into the source tree.

## Project layout

```text
Pastbound/
├── build.gradle
├── gradle.properties
├── gradlew
├── gradlew.bat
├── README.md
├── LICENSE
├── docs/
│   └── design.md
├── tools/
│   ├── audit_assets.py
│   ├── assemble_release_jar.py
│   ├── build_relic_effect_textures.py
│   ├── build_time_machine_logo.py
│   ├── generate_locales.py
│   ├── generate_relic_assets.py
│   ├── generate_relic_recipes.py
│   ├── generate_history_data.py
│   ├── generate_time_machine_data.py
│   └── generate_textures.py
└── src/main/
    ├── java/dev/pastbound/
    │   ├── block/
    │   ├── client/
    │   ├── effect/
    │   ├── entity/
    │   ├── history/
    │   ├── item/
    │   ├── network/
    │   ├── relic/
    │   └── registry/
    └── resources/
        ├── META-INF/neoforge.mods.toml
        ├── assets/pastbound/
        │   ├── blockstates/
        │   ├── lang/
        │   ├── models/
        │   └── textures/
        └── data/
            ├── curios/
            └── pastbound/
```

## Coding style

Pastbound keeps gameplay code deliberately direct. Variables, methods, and event names use Turkish identifiers where they describe the project’s own concepts. Java source files do not contain comment lines; intent is expressed through meaningful names, small classes, explicit server-side validation, and data-driven assets. New features should preserve the historical theme, avoid unnecessary generic systems, and include the textures, translations, recipes, loot data, or advancements that make the feature complete in-game.

## License and attribution

Pastbound is released under the MIT License. Minecraft, NeoForge, and Curios remain the property of their respective owners. Pastbound is a fan-made ModJam project and is not affiliated with Mojang, NeoForged, or Illusive Soulworks.

## References

[1]: https://docs.neoforged.net/docs/gettingstarted/ "NeoForge Getting Started"
[2]: https://github.com/NeoForgeMDKs/MDK-26.2-NeoGradle "NeoForge 26.2 NeoGradle MDK"
[3]: https://docs.illusivesoulworks.com/curios/ "Curios Documentation"
[4]: https://github.com/TheIllusiveC4/Curios "Curios Source Repository"
