package dev.pastbound.entity;

import dev.pastbound.registry.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.level.Level;

public class VikingBoatEntity extends Boat {
    public VikingBoatEntity(EntityType<VikingBoatEntity> type, Level level) {
        super(type, level, () -> ModItems.VIKING_BOAT.get());
    }
}
