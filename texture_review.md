# Pastbound texture review

The Ancient Storage side texture is a 16×16 dark wood/archive tile with a restrained violet frame and gold rune mark, consistent with the historical archive palette.

The generated time-machine GUI texture is a 256×192 dark slate panel with gold and teal inset borders and a compass-like astronomical instrument. The redesigned screen keeps those colors and motifs through procedural UI drawing, while using the existing texture as the canonical visual reference.

The canonical wood generator now emits binary-alpha foliage and sapling pixels so transparent regions are cutout-safe. Generated block models also include explicit particle texture bindings.

## External reference

NeoForged’s model specification explains that model texture materials with only alpha 0 and 255 pixels render in the cutout layer, while partially transparent pixels render as translucent; it also recommends explicit `particle` texture bindings for block models. Source: [NeoForged Models documentation](https://docs.neoforged.net/docs/resources/client/models/).
