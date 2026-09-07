# Pastbound 3.9.9

## Hotfix

Fixed the crash that occurred when opening the inventory or creative inventory with the **E** key. The custom Pastbound creative tab was adding Olive Log, Olive Leaves, and Olive Sapling twice: once explicitly and once through the historical wood item collection. NeoForge now receives each item only once, preventing the `Itemstack ... pastbound:olive_log already exists in the tab's list` error.

## Validation

The project passes `./gradlew check build` with configuration-cache disabled for this hotfix build. The generated mod metadata reports version 3.9.9.
