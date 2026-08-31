# Pastbound v2.5.9 — Deterministic Effects and Living History Routes

Pastbound 2.5.9 refines the historical expedition loop and removes unsolicited status effects.

## Deterministic effects

Global historical events no longer grant status effects on a repeating timer when the player happens to hold a particular item or stand in a particular location. Effects now come from explicit relic activation, a completed selected witness dialogue, or a direct archive interaction. This keeps rewards readable and prevents unexplained effects during normal play.

## Living history travel

The Time Machine now presents the selected route more clearly, including a dedicated arrival-route line and stronger destination highlighting. Players enter a historical dimension at its arrival gate and walk toward the marked historical site instead of appearing directly on the central objective. The route is still server-authoritative and consumes one Time Stone.

## Crafting progression

Two missing research tools now have recipes: Memory Lens and Chronicle Compass. Their recipes use Echo Shards, Chronicle Scraps, and familiar Minecraft materials so the archive loop is understandable without relying on creative mode.

## Quality checks

The release was validated with resource JSON parsing, duplicate-key detection, Python tool compilation, the enhanced asset audit, Java compilation, deterministic JAR assembly, and `git diff --check`.

The package continues to use the deterministic Time Machine logo and the Turkish naming/no-comment project conventions.
