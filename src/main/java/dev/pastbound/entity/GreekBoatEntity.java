package dev.pastbound.entity;

import dev.pastbound.registry.ModEntityTypes;
import dev.pastbound.registry.ModItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
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

    @Override
    public void tick() {
        super.tick();
        if (!level().isClientSide() && tickCount % 20 == 0) {
            for (var passenger : getPassengers()) {
                if (passenger instanceof Player player) {
                    player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 40, 0, false, false, true));
                    player.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 40, 0, false, false, true));
                }
            }
        }
    }
}
