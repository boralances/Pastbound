# Pastbound 3.10.0

## Time-machine improvements

Steel Ore can now be mined reliably in the Baghdad Battery time-machine dimension. The mission accepts both Steel Ore and Deepslate Steel Ore, and the historical protection handler no longer treats the deepslate variant as an unrelated protected block. Entry now checks for a usable iron-, diamond-, or netherite-level pickaxe, including durability, so a damaged or unsuitable pickaxe cannot silently block the steel objective.

The time machine also provides a one-time expedition kit on the first historical journey: bread for field travel and copper ingots for the later power-repair objective. This gives the expedition a clearer preparation loop without duplicating supplies on every return trip.

## Validation

The release passes `./gradlew check build` with configuration-cache disabled. The generated mod metadata reports version 3.10.0, and the steel block-break handlers and localized expedition-kit message are included in the release JAR.
