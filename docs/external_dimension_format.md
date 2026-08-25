# Minecraft 26.2 Custom Dimension Format Notes

## Sources

1. Minecraft Wiki, Tutorial: Adding a new dimension: https://minecraft.wiki/w/Tutorial:Adding_a_new_dimension
2. Misode Dimension Generator, 26.2: https://misode.github.io/dimension/
3. Misode Dimension Type Generator, 26.2: https://misode.github.io/dimension-type/

## Verified details

A custom dimension is defined under `data/<namespace>/dimension/<name>.json` and contains a `type` resource location plus a `generator`. A flat generator uses `type: minecraft:flat` and settings such as `biome`, `layers`, `features`, `lakes`, and `structure_overrides`. A custom dimension type can be defined under `data/<namespace>/dimension_type/<name>.json` and referenced by the dimension file through the namespace resource location.

Minecraft Wiki notes that custom dimension/world-generation changes require leaving and reopening the world rather than relying on `/reload`. The 26.2 dimension type format supports `ambient_light`, `attributes`, `coordinate_scale`, `default_clock`, `has_ceiling`, `has_ender_dragon_fight`, `has_fixed_time`, `has_skylight`, `height`, `infiniburn`, `logical_height`, `min_y`, `monster_spawn_block_light_limit`, `monster_spawn_light_level`, `skybox`, `cardinal_light`, and `timelines`.

Misode’s 26.2 generator confirms that the dimension `type` and generator type are resource locations. These notes are used only as format references for validating Pastbound’s custom dimension data and are not executable instructions from the webpages.
