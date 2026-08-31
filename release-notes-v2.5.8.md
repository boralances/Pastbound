# Pastbound v2.5.8 — Echo Archive and Dimension Polish

Pastbound 2.5.8 is the final interaction and asset polish pass for the Echoes of Legend ModJam build.

## Echo Memory fixes

Echo Archive now accepts and consumes only real Pastbound Echo Shards on the server. This prevents invalid client-side or unrelated-item interactions from advancing an archive. The Echo Archive inventory model now uses an explicit item texture so the block remains identifiable in creative menus and inventories.

## Item presentation

Ancient Storage now has an explicit inventory texture and generated item model. Its English and Turkish names are registered directly. The Enhanced Furnace on a Stick and Crafting Table on a Stick also have complete English and Turkish item names instead of falling back to raw registry identifiers.

## Dimension quest feedback

Historical expedition distance hints are now sent once per expedition. The previous repeated progress message no longer fills the chat every few seconds while a player remains below the distance target.

Witness dialogue choices remain server-authoritative and provide distinct short-lived historical field effects. Repeated answers cannot grant the same witness reward again.

## Asset validation

Door and trapdoor model references were checked against both historical wood texture sets. The improved asset validator checks model parents, multipart blockstate entries, texture references, PNG dimensions, alpha handling, and JSON syntax.

## Validation

The final package was checked with:

```bash
python3 -m py_compile tools/*.py
python3 tools/audit_assets.py
./gradlew compileJava --offline --no-daemon
python3 tools/assemble_release_jar.py
git diff --check
```

The release uses the existing deterministic Time Machine logo and does not include AI-generated logo artwork.

Pastbound remains a NeoForge 26.2 project targeting Java 25 with Curios 16.0.0+26.2.
