# Pastbound 3.9.0 — Implementation Summary

## Crash fix

The End-entry crash was traced to `TarihMadenleri.biyomCebiUygula`, which rebuilt every `LevelChunkSection` biome palette during `ChunkEvent.Load` for newly generated End chunks. That low-level replacement could leave the End section palette in an invalid state while the chunk was being attached, producing the reported negative-index failure. End chunk loading now skips biome-palette replacement entirely while preserving End Echo Ore, Void Chronicle Ore, rare Ervanium Ore, and observation-point generation.

## New content

The time machine now includes two additional destinations: **Olympia Games** (`olimpia_olimpiyatlari`) and **Giza Pyramids** (`giza_piramidi`). Both have flat historical dimensions, advancement milestones, English and Turkish localization, period-specific tasks, arrival effects, dialogue text, and scene themes.

Ancient Greece content now includes a craftable Greek trireme with a custom entity renderer layer, 16×16 inventory icon, 128×64 entity texture, recipe, and creative-tab entry. It also adds Greek Marble, Greek Column, and Greek Olive Altar blocks, a rare Greek temple worldgen feature in plains, savanna, and windswept hills, recipes, modern item definitions, block models, and pixel-art textures.

Ancient Egypt content now includes Egyptian Hieroglyph Wall and Egyptian Sphinx Stone blocks, recipes, models, inventory definitions, textures, and enhanced Egyptian ruins that generate hieroglyph panels and a sphinx marker in addition to the existing obelisk and gold inlays.

## Build and validation

The following checks passed:

| Check | Result |
|---|---|
| `./gradlew compileJava --no-daemon` | Passed |
| `./gradlew processResources --no-daemon` | Passed |
| `python3 tools/validate_assets.py` | `ASSET_OK` |
| `python3 tools/validate_recipes.py` | `RECIPES_VALID` |
| `python3 tools/validate_runtime_assets.py` | `RUNTIME_ASSETS_OK` |
| `./gradlew packageRelease --no-daemon` | Passed |
| `sha256sum -c dist/Pastbound-3.9.0.jar.sha256` | `OK` |
| `git diff --cached --check` | Passed |

The complete change set is committed locally as `5152292` (`Fix End palette crash and expand Greek Egyptian content`). The local branch is one commit ahead of `origin/main`; it has not been pushed.
