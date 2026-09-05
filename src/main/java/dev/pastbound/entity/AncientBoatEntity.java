package dev.pastbound.entity;

import dev.pastbound.registry.ModEntityTypes;
import dev.pastbound.registry.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.level.Level;

public class AncientBoatEntity extends Boat {
    public AncientBoatEntity(EntityType<AncientBoatEntity> type, Level level) {
        super(type, level, () -> ModItems.ANCIENT_BOAT.get());
    }

    public AncientBoatEntity(Level level, double x, double y, double z) {
        this(ModEntityTypes.ANCIENT_BOAT.get(), level);
        this.setPos(x, y, z);
    }
}
