package dev.pastbound.entity;

import dev.pastbound.registry.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.level.Level;

public class EgyptianBoatEntity extends Boat {
    public EgyptianBoatEntity(EntityType<EgyptianBoatEntity> type, Level level) {
        super(type, level, () -> ModItems.EGYPTIAN_BOAT.get());
    }
}
