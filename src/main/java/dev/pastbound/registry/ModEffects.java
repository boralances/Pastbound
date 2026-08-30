package dev.pastbound.registry;

import dev.pastbound.ModId;
import dev.pastbound.effect.TarihYankisiEtkisi;
import dev.pastbound.effect.TarihselRelikEtkisi;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, ModId.MOD_ID);
    public static final DeferredHolder<MobEffect, MobEffect> TARIH_YANKISI = EFFECTS.register("tarih_yankisi", registryName -> new TarihYankisiEtkisi());
    public static final DeferredHolder<MobEffect, MobEffect> ROSSETTA_BILGISI = kaydet("rossetta_bilgisi", 0xD9C27C);
    public static final DeferredHolder<MobEffect, MobEffect> GILGAMESH_DAYANIKLILIGI = kaydet("gilgamesh_dayanikliligi", 0x8F5D3E);
    public static final DeferredHolder<MobEffect, MobEffect> ANUBIS_ARINDIRMASI = kaydet("anubis_arindirmasi", 0xD1B35A);
    public static final DeferredHolder<MobEffect, MobEffect> MINOS_SICRAMASI = kaydet("minos_sicramasi", 0x728E9E);
    public static final DeferredHolder<MobEffect, MobEffect> ROMA_AUREUSU = kaydet("roma_aureusu", 0xE5B43C);
    public static final DeferredHolder<MobEffect, MobEffect> VIKING_GECE_GORUSU = kaydet("viking_gece_gorusu", 0x6EA7B8);
    public static final DeferredHolder<MobEffect, MobEffect> SAMURAY_KORUMASI = kaydet("samuray_korumasi", 0xB83F3F);
    public static final DeferredHolder<MobEffect, MobEffect> MAYA_TAKVIMI = kaydet("maya_takvimi", 0xD9793D);
    public static final DeferredHolder<MobEffect, MobEffect> INKA_BAGLARI = kaydet("inka_baglari", 0xA56A45);
    public static final DeferredHolder<MobEffect, MobEffect> HARAPPA_KIL_TABLETLERI = kaydet("harappa_kil_tabletleri", 0x718C74);

    private static DeferredHolder<MobEffect, MobEffect> kaydet(String kimlik, int renk) {
        return EFFECTS.register(kimlik, registryName -> new TarihselRelikEtkisi(renk, ParticleTypes.ENCHANT));
    }

    private ModEffects() {
    }
}
