package dev.pastbound.registry;

import dev.pastbound.ModId;
import dev.pastbound.effect.TarihYankisiEtkisi;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, ModId.MOD_ID);
    public static final DeferredHolder<MobEffect, MobEffect> TARIH_YANKISI = EFFECTS.register("tarih_yankisi", registryName -> new TarihYankisiEtkisi());

    private ModEffects() {
    }
}
