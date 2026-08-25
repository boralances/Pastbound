package dev.pastbound.effect;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public final class TarihYankisiEtkisi extends MobEffect {
    public TarihYankisiEtkisi() {
        super(MobEffectCategory.BENEFICIAL, 0x63C6B3, ParticleTypes.ENCHANT);
    }

    @Override
    public boolean applyEffectTick(ServerLevel seviye, LivingEntity varlik, int seviyeNo) {
        if (varlik instanceof Player oyuncu) {
            oyuncu.giveExperiencePoints(1 + seviyeNo);
            seviye.sendParticles(ParticleTypes.ENCHANT, oyuncu.getX(), oyuncu.getY() + 1.0D, oyuncu.getZ(), 2 + seviyeNo, 0.25D, 0.35D, 0.25D, 0.02D);
            return true;
        }
        return false;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int kalanSure, int seviyeNo) {
        return kalanSure % Math.max(20, 50 - seviyeNo * 5) == 0;
    }
}
