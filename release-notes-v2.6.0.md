# Pastbound v2.6.0 — Dimension Stability and Crafting Repair

Pastbound 2.6.0 is a stability-focused release for the historical dimension quest loop.

## Dimension entity stability

Historical scene setup now removes existing nearby scene villagers before creating the intended specialist. Re-entering a dimension or restarting an expedition no longer leaves multiple witnesses stacked at the same coordinates.

## Quest arrival flow

A new expedition begins at a controlled arrival gate inside the historical scene. Players receive a clear instruction to walk to the marked site, making the dimension feel like a place to explore rather than a direct teleport to the objective. The existing server-authoritative quest state and return location remain intact.

## Time Machine presentation

The Time Machine UI now highlights the selected destination with a stronger visual accent and shows an explicit arrival-route line. The panel explains that the player will enter through the gate and walk to the historical site before accepting travel.

## Crafting repairs

Crafting recipe key entries were normalized to valid Minecraft 26.2 ingredient objects. This fixes recipes that previously failed because their key values were encoded as raw strings. Memory Lens and Chronicle Compass recipes are included in the final package.

## Mod metadata and logo

The NeoForge metadata now includes the project homepage and issue tracker. The existing deterministic Time Machine logo remains available both as the 512×512 in-game GUI logo and the root `logo.png` used by mod metadata.

## Validation

The final package passed resource JSON parsing with duplicate-key detection, Python tool compilation, the enhanced asset audit, Java 25 / NeoForge 26.2 compilation, deterministic JAR assembly, metadata inspection, recipe presence checks, and `git diff --check`.
