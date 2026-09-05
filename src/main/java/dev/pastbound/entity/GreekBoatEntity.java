package dev.pastbound.entity;

import dev.pastbound.registry.ModEntityTypes;
import dev.pastbound.registry.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.level.Level;

public class GreekBoatEntity extends Boat {
    public GreekBoatEntity(EntityType<GreekBoatEntity> type, Level level) {
        super(type, level, () -> ModItems.GREEK_BOAT.get());
    }

    public GreekBoatEntity(Level level, double x, double y, double z) {
        this(ModEntityTypes.GREEK_BOAT.get(), level);
        this.setPos(x, y, z);
    }
}
