package dev.pastbound.entity;

import dev.pastbound.registry.ModItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.level.Level;

public class VikingBoatEntity extends Boat {
    public VikingBoatEntity(EntityType<VikingBoatEntity> type, Level level) {
        super(type, level, () -> ModItems.VIKING_BOAT.get());
    }

    @Override
    public void tick() {
        super.tick();
        if (!level().isClientSide() && tickCount % 20 == 0) {
            for (var passenger : getPassengers()) {
                if (passenger instanceof Player player) {
                    player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 40, 0, false, false, true));
                    player.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 40, 0, false, false, true));
                }
            }
        }
    }
}
