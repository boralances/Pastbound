## Pastbound v2.5.5

This correction removes the AI-created logo artwork from the build. The logo is now produced deterministically from the existing 16×16 time-machine item texture using nearest-neighbor enlargement into the 512×512 metadata and GUI assets. No generated logo artwork is used.

The ten historical relic bonuses now have real custom Minecraft status-effect registrations and matching 18×18 effect textures derived from their existing relic item textures. Their Turkish and English names are generated in the normal locale pipeline, so the icons and names appear correctly in the status-effect display.

The complete asset audit passes with 165 models and 106 textures. Python tools compile, diff hygiene passes, Java compilation succeeds offline, and Pastbound-2.5.5.jar contains the time-machine item texture, deterministic logo, all eleven effect textures, and the compiled relic logic.
