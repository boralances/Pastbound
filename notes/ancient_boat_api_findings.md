# Ancient Boat API Findings

NeoForm Minecraft 26.2 sources show that `net.minecraft.world.entity.vehicle.boat.Boat` has constructor `Boat(EntityType<? extends Boat> type, Level level, Supplier<Item> dropItem)` and extends `AbstractBoat`.

`net.minecraft.world.item.BoatItem` accepts `EntityType<? extends AbstractBoat>` in its constructor and handles raycast, collision, server-side entity spawning, item consumption, and placement statistics. A custom boat item can therefore use `new BoatItem(ModEntityTypes.ANCIENT_BOAT.get(), properties)` once the custom entity type is registered.

The custom entity can extend vanilla `Boat` for all functional movement and passenger behavior while supplying the custom entity type and drop item. A custom client renderer can bind the custom texture while reusing vanilla boat model infrastructure if the renderer API permits it.

`BoatRenderer` constructor is `BoatRenderer(EntityRendererProvider.Context, ModelLayerLocation)` and derives its texture path from `modelId.model().withPath(p -> "textures/entity/" + p + ".png")`; it bakes a `BoatModel` and water patch. `AbstractBoat` exposes the standard movement, rowing, collision, passenger, and placement behavior. A custom entity extending `Boat` should therefore be fully drivable, while a custom renderer can use a custom `ModelLayerLocation` whose path maps to the desired texture.
