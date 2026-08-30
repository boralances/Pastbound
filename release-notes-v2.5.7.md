# Pastbound v2.5.7 — Professional Polish

Pastbound 2.5.7 is a final quality pass for the Echoes of Legend ModJam build.

## Improvements

The asset validator now checks project-local model parents and multipart blockstate model lists in addition to JSON validity, texture references, blockstate references, PNG dimensions, and block alpha. This prevents future model-chain or multipart rendering regressions from reaching a release unnoticed.

A full texture audit found no missing texture references, fully transparent PNGs, invalid dimensions, or partial block alpha problems in the current asset set. The Time Machine logo remains a deterministic 512×512 nearest-neighbor enlargement of the real Time Machine texture, and the ten custom relic effects retain their dedicated icons.

The Curator’s Seal remains the final expedition reward: a single, fire-resistant EPIC item with localized English and Turkish names.

## Verification

Python tools compile successfully, all resource JSON files parse successfully, the asset audit reports 165 models and 106 textures, `git diff --check` passes, and `compileJava --offline --no-daemon` succeeds with Java 25 and NeoForge 26.2.0.66.
