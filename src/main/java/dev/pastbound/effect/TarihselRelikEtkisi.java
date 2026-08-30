package dev.pastbound.effect;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public final class TarihselRelikEtkisi extends MobEffect {
    public TarihselRelikEtkisi(int renk, ParticleOptions parcacik) {
        super(MobEffectCategory.BENEFICIAL, renk, parcacik);
    }
}
