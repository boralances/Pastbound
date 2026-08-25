package dev.pastbound.history;

import java.util.Arrays;

import dev.pastbound.relic.RelikMantigi;
import net.minecraft.advancements.AdvancementHolder;
import dev.pastbound.registry.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.extensions.IEntityExtension;

public final class ZamanMakinesiMantigi {
    private static final String KESFEDILEN_CAGLAR = "pastbound_kesfedilen_caglar";
    private static final String AYRAC = "|";

    private ZamanMakinesiMantigi() {
    }

    public static boolean kesifliMi(ServerPlayer oyuncu, TarihDonemi donem) {
        String kayit = ((IEntityExtension) oyuncu).getPersistentData().getStringOr(KESFEDILEN_CAGLAR, "");
        return Arrays.asList(kayit.split("\\|", -1)).contains(donem.kimlik());
    }

    public static int kesifSayisi(ServerPlayer oyuncu) {
        int sayi = 0;
        for (TarihDonemi donem : TarihDonemi.values()) {
            if (kesifliMi(oyuncu, donem)) {
                sayi++;
            }
        }
        return sayi;
    }

    public static void donemeGit(ServerPlayer oyuncu, String donemKimligi) {
        TarihDonemi donem = null;
        for (TarihDonemi aday : TarihDonemi.values()) {
            if (aday.kimlik().equalsIgnoreCase(donemKimligi)) {
                donem = aday;
                break;
            }
        }
        if (donem == null) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.time_machine.unknown"));
            return;
        }
        ItemStack makine = new ItemStack(ModItems.ZAMAN_MAKINESI.get());
        if (oyuncu.getCooldowns().isOnCooldown(makine)) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.time_machine.cooldown"));
            return;
        }
        if (!TarihiKesifDunyasi.baslat(oyuncu, donem)) {
            return;
        }
        boolean yeniKesif = kaydet(oyuncu, donem);
        tarihEtki(oyuncu, donem);
        yankiyiHazirla(oyuncu, donem);
        oyuncu.getCooldowns().addCooldown(makine, 240);
        if (yeniKesif) {
            zamanIlerlemesi(oyuncu, donem);
        }
        oyuncu.sendSystemMessage(Component.translatable("message.pastbound.time_machine.arrived", donem.ad(), donem.odak()));
        oyuncu.level().playSound(null, oyuncu.blockPosition(), SoundEvents.RESPAWN_ANCHOR_CHARGE, SoundSource.PLAYERS, 0.8F, 1.25F);
    }

    private static boolean kaydet(ServerPlayer oyuncu, TarihDonemi donem) {
        if (kesifliMi(oyuncu, donem)) {
            return false;
        }
        CompoundTag veri = ((IEntityExtension) oyuncu).getPersistentData();
        String eski = veri.getStringOr(KESFEDILEN_CAGLAR, "");
        veri.putString(KESFEDILEN_CAGLAR, eski.isEmpty() ? donem.kimlik() : eski + AYRAC + donem.kimlik());
        return true;
    }

    private static void zamanIlerlemesi(ServerPlayer oyuncu, TarihDonemi donem) {
        AdvancementHolder durak = ((ServerLevel) oyuncu.level()).getServer().getAdvancements().get(Identifier.fromNamespaceAndPath("pastbound", "time_machine/" + donem.kimlik()));
        if (durak != null) {
            oyuncu.getAdvancements().award(durak, "kesif");
        }
        if (kesifSayisi(oyuncu) >= TarihDonemi.values().length) {
            AdvancementHolder koleksiyon = ((ServerLevel) oyuncu.level()).getServer().getAdvancements().get(Identifier.fromNamespaceAndPath("pastbound", "time_machine/complete_expedition"));
            if (koleksiyon != null) {
                oyuncu.getAdvancements().award(koleksiyon, "kesif");
            }
        }
    }

    private static void tarihEtki(ServerPlayer oyuncu, TarihDonemi donem) {
        ServerLevel seviye = (ServerLevel) oyuncu.level();
        switch (donem) {
            case URUK_YAZI_EVI -> {
                efekt(oyuncu, MobEffects.HASTE, 240, 0);
                efekt(oyuncu, MobEffects.NIGHT_VISION, 240, 0);
                yankipar(oyuncu, seviye, ParticleTypes.ENCHANT);
            }
            case TERMOPIL_SAVASI -> {
                efekt(oyuncu, MobEffects.STRENGTH, 200, 0);
                yankipar(oyuncu, seviye, ParticleTypes.CRIT);
            }
            case ISKENDERIYE_KUTUPHANESI -> {
                efekt(oyuncu, MobEffects.HASTE, 220, 0);
                efekt(oyuncu, MobEffects.LUCK, 220, 0);
                oyuncu.giveExperiencePoints(4);
                yankipar(oyuncu, seviye, ParticleTypes.ENCHANT);
            }
            case BAGDAT_PILI_ATOLYESI -> {
                efekt(oyuncu, MobEffects.GLOWING, 180, 0);
                efekt(oyuncu, MobEffects.HASTE, 180, 0);
                yankipar(oyuncu, seviye, ParticleTypes.ELECTRIC_SPARK);
            }
            case ANTIKITHERA_LIMANI -> {
                efekt(oyuncu, MobEffects.SLOW_FALLING, 240, 0);
                efekt(oyuncu, MobEffects.DOLPHINS_GRACE, 240, 0);
                yankipar(oyuncu, seviye, ParticleTypes.END_ROD);
            }
            case BAGDAT_BILGI_EVI -> {
                efekt(oyuncu, MobEffects.HASTE, 220, 0);
                efekt(oyuncu, MobEffects.LUCK, 220, 1);
                oyuncu.giveExperiencePoints(5);
                yankipar(oyuncu, seviye, ParticleTypes.ENCHANT);
            }
            case TIMBUKTU_EL_YAZMALARI -> {
                efekt(oyuncu, MobEffects.SPEED, 220, 0);
                efekt(oyuncu, MobEffects.HERO_OF_THE_VILLAGE, 220, 0);
                yankipar(oyuncu, seviye, ParticleTypes.HAPPY_VILLAGER);
            }
            case TENOKTITLAN_GECIDI -> {
                efekt(oyuncu, MobEffects.WATER_BREATHING, 240, 0);
                efekt(oyuncu, MobEffects.LUCK, 240, 0);
                yankipar(oyuncu, seviye, ParticleTypes.BUBBLE);
            }
            case POLINEZYA_YILDIZ_YOLU -> {
                efekt(oyuncu, MobEffects.WATER_BREATHING, 260, 0);
                efekt(oyuncu, MobEffects.DOLPHINS_GRACE, 260, 0);
                yankipar(oyuncu, seviye, ParticleTypes.BUBBLE);
            }
            case CATALHOYUK_YERLESKESI -> {
                efekt(oyuncu, MobEffects.REGENERATION, 180, 0);
                efekt(oyuncu, MobEffects.HEALTH_BOOST, 180, 0);
                yankipar(oyuncu, seviye, ParticleTypes.HAPPY_VILLAGER);
            }
            case APOLLO_AY_ISTIGI -> {
                efekt(oyuncu, MobEffects.SLOW_FALLING, 300, 1);
                efekt(oyuncu, MobEffects.NIGHT_VISION, 300, 0);
                yankipar(oyuncu, seviye, ParticleTypes.END_ROD);
            }
            case IPEK_YOLU_KERVANSARAYI -> {
                efekt(oyuncu, MobEffects.SPEED, 220, 0);
                efekt(oyuncu, MobEffects.HERO_OF_THE_VILLAGE, 220, 0);
                oyuncu.giveExperiencePoints(3);
                yankipar(oyuncu, seviye, ParticleTypes.HAPPY_VILLAGER);
            }
        }
    }

    private static void yankiyiHazirla(ServerPlayer oyuncu, TarihDonemi donem) {
        String kimlik = switch (donem) {
            case URUK_YAZI_EVI -> "uruk_muhru";
            case TERMOPIL_SAVASI -> "bushido_yemini";
            case ISKENDERIYE_KUTUPHANESI -> "abbasi_bilgi_evi";
            case BAGDAT_PILI_ATOLYESI -> "antikithera_gok";
            case ANTIKITHERA_LIMANI -> "antikithera_gok";
            case BAGDAT_BILGI_EVI -> "abbasi_bilgi_evi";
            case TIMBUKTU_EL_YAZMALARI -> "timbuktu_kervani";
            case TENOKTITLAN_GECIDI -> "aztek_bes_cag";
            case POLINEZYA_YILDIZ_YOLU -> "polinezya_yildiz";
            case CATALHOYUK_YERLESKESI -> "catalhoyuk_evleri";
            case APOLLO_AY_ISTIGI -> "apollo_ay_yuruyusu";
            case IPEK_YOLU_KERVANSARAYI -> "song_firini";
        };
        TarihYankisi yanki = TarihYankilari.yankiBul(kimlik);
        if (yanki != null) {
            RelikMantigi.yankiyiHazirla(oyuncu, yanki);
        }
    }

    private static void efekt(ServerPlayer oyuncu, net.minecraft.core.Holder<net.minecraft.world.effect.MobEffect> etki, int sure, int seviye) {
        oyuncu.addEffect(new MobEffectInstance(etki, sure, seviye, false, false, true));
    }

    private static void yankipar(ServerPlayer oyuncu, ServerLevel seviye, net.minecraft.core.particles.ParticleOptions parcacik) {
        seviye.sendParticles(parcacik, oyuncu.getX(), oyuncu.getY() + 1.0D, oyuncu.getZ(), 14, 0.5D, 0.7D, 0.5D, 0.03D);
    }
}
