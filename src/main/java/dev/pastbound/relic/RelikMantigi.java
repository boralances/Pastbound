package dev.pastbound.relic;

import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import net.minecraft.server.level.ServerLevel;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import net.neoforged.neoforge.common.extensions.IEntityExtension;

public final class RelikMantigi {
    private static final String BILINEN_RELIKLER = "pastbound_bilinen_relikler";
    private static final String AYRAC = "|";

    private RelikMantigi() {
    }

    public static boolean biliyorMu(Player oyuncu, RelikTanimi tanim) {
        String kayit = ((IEntityExtension) oyuncu).getPersistentData().getStringOr(BILINEN_RELIKLER, "");
        return Arrays.asList(kayit.split("\\|", -1)).contains(tanim.kimlik());
    }

    public static void bilgiyeEkle(Player oyuncu, RelikTanimi tanim) {
        if (biliyorMu(oyuncu, tanim)) {
            return;
        }
        CompoundTag veri = ((IEntityExtension) oyuncu).getPersistentData();
        String eski = veri.getStringOr(BILINEN_RELIKLER, "");
        veri.putString(BILINEN_RELIKLER, eski.isEmpty() ? tanim.kimlik() : eski + AYRAC + tanim.kimlik());
        oyuncu.sendSystemMessage(Component.translatable("message.pastbound.relic.knowledge", tanim.ad()));
    }

    public static int bilinenSayi(Player oyuncu) {
        int sayi = 0;
        for (RelikTanimi tanim : RelikTanimi.values()) {
            if (biliyorMu(oyuncu, tanim)) {
                sayi++;
            }
        }
        return sayi;
    }

    public static RelikTanimi tanimBul(String kimlik) {
        for (RelikTanimi tanim : RelikTanimi.values()) {
            if (tanim.kimlik().equalsIgnoreCase(kimlik)) {
                return tanim;
            }
        }
        return null;
    }

    public static boolean bilmeceCevapla(Player oyuncu, String kimlik, String cevap) {
        RelikTanimi tanim = tanimBul(kimlik);
        if (tanim == null) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.relic.bad_name"));
            return false;
        }
        if (biliyorMu(oyuncu, tanim)) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.relic.already_known", tanim.ad()));
            return true;
        }
        String temiz = cevap.toLowerCase(Locale.ROOT).replace('ı', 'i').replace('ş', 's').replace('ğ', 'g').replace('ü', 'u').replace('ö', 'o').replace('ç', 'c');
        if (!cevapDogruMu(tanim, temiz)) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.relic.riddle_wrong"));
            return false;
        }
        bilgiyeEkle(oyuncu, tanim);
        oyuncu.sendSystemMessage(Component.translatable("message.pastbound.relic.riddle_right", tanim.ad()));
        oyuncu.level().playSound(null, oyuncu.blockPosition(), SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.PLAYERS, 1.0F, 1.4F);
        return true;
    }

    private static boolean cevapDogruMu(RelikTanimi tanim, String cevap) {
        return switch (tanim) {
            case ROSSETTA_TASI -> cevap.contains("dil") || cevap.contains("rosetta") || cevap.contains("tas");
            case GILGAMESH_TABLETI -> cevap.contains("gilgamesh") || cevap.contains("destan") || cevap.contains("kral");
            case ANUBIS_ANKHI -> cevap.contains("ankh") || cevap.contains("yasam") || cevap.contains("nil");
            case MINOS_LABIRENT_MUHRU -> cevap.contains("labirent") || cevap.contains("minos") || cevap.contains("merkez");
            case ROMA_AUREUSU -> cevap.contains("altin") || cevap.contains("roma") || cevap.contains("imparator");
            case VIKING_GUNES_PUSULASI -> cevap.contains("pusula") || cevap.contains("gunes") || cevap.contains("kuzey");
            case SAMURAY_KABZASI -> cevap.contains("onur") || cevap.contains("samuray") || cevap.contains("kilic");
            case MAYA_GUNES_CARKI -> cevap.contains("takvim") || cevap.contains("maya") || cevap.contains("gunes");
            case INKA_QUIPUSU -> cevap.contains("dugum") || cevap.contains("quipu") || cevap.contains("ip");
            case HARAPPA_MUHRU -> cevap.contains("muhur") || cevap.contains("harappa") || cevap.contains("indus");
            case SONG_PORSELENI -> cevap.contains("porselen") || cevap.contains("song") || cevap.contains("ipek");
            case BENIN_BRONZU -> cevap.contains("bronz") || cevap.contains("benin") || cevap.contains("ates");
            case AZTEK_GUNES_TASI -> cevap.contains("aztek") || cevap.contains("cag") || cevap.contains("gunes");
            case ABBASID_MUREKKEBI -> cevap.contains("murekkep") || cevap.contains("abbasi") || cevap.contains("fikir");
            case RONESANS_ASTROLABI -> cevap.contains("astrolab") || cevap.contains("yildiz") || cevap.contains("olcum");
            case ANTIKITHERA_DUZENEĞI -> cevap.contains("disli") || cevap.contains("antikithera") || cevap.contains("gok");
            case CATALHOYUK_BONCUGU -> cevap.contains("boncuk") || cevap.contains("catalhoyuk") || cevap.contains("renk");
            case BIZANS_MOZAIGI -> cevap.contains("mozaik") || cevap.contains("bizans") || cevap.contains("parca");
            case TIMBUKTU_KALEMI -> cevap.contains("kalem") || cevap.contains("timbuktu") || cevap.contains("yazi");
            case APOLLO17_ARMASI -> cevap.contains("apollo") || cevap.contains("ay") || cevap.contains("astronot");
            case ILHANLI_MADALYONU -> cevap.contains("madalyon") || cevap.contains("ilhanli") || cevap.contains("kervan");
            case POLINEZYA_YILDIZ_HARITASI -> cevap.contains("yildiz") || cevap.contains("polinezya") || cevap.contains("ada");
            case MALI_TUZ_MUHRU -> cevap.contains("tuz") || cevap.contains("mali") || cevap.contains("sahra");
            case ISKANDINAV_RUNETASI -> cevap.contains("rune") || cevap.contains("iskandinav") || cevap.contains("tas");
        };
    }

    public static boolean etkinlestirIlk(Player oyuncu) {
        ICuriosItemHandler envanter = CuriosApi.getCuriosInventoryOrNull(oyuncu);
        if (envanter == null) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.relic.no_curios"));
            return false;
        }
        Optional<SlotResult> bulunan = envanter.findCurios(yigin -> yigin.getItem() instanceof RelikItem && biliyorMu(oyuncu, ((RelikItem) yigin.getItem()).tanim())).stream().findFirst();
        if (bulunan.isEmpty()) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.relic.no_known"));
            return false;
        }
        SlotResult sonuc = bulunan.get();
        RelikItem item = (RelikItem) sonuc.stack().getItem();
        return etkinlestir(oyuncu, item.tanim(), sonuc.stack());
    }

    public static boolean etkinlestir(Player oyuncu, RelikTanimi tanim, ItemStack yigin) {
        if (!biliyorMu(oyuncu, tanim)) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.relic.unknown", tanim.bilmece()));
            return false;
        }
        ItemCooldowns beklemeler = oyuncu.getCooldowns();
        if (beklemeler.isOnCooldown(yigin)) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.relic.cooldown"));
            return false;
        }
        aktifUygula(oyuncu, tanim);
        beklemeler.addCooldown(yigin, tanim.beklemeSuresi());
        oyuncu.sendSystemMessage(Component.translatable("message.pastbound.relic.activated", tanim.ad()));
        oyuncu.level().playSound(null, oyuncu.blockPosition(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 0.8F, 0.7F + tanim.ordinal() % 6 * 0.1F);
        if (oyuncu.level() instanceof ServerLevel sunucu) {
            sunucu.sendParticles(ParticleTypes.ENCHANT, oyuncu.getX(), oyuncu.getY() + 1.0D, oyuncu.getZ(), 18, 0.45D, 0.65D, 0.45D, 0.08D);
        }
        return true;
    }

    public static void aktifUygula(Player oyuncu, RelikTanimi tanim) {
        switch (tanim.yeti()) {
            case BILGI -> oyuncu.giveExperiencePoints(12);
            case GUC -> efekt(oyuncu, MobEffects.STRENGTH, 180, 0);
            case YENILENME -> efekt(oyuncu, MobEffects.REGENERATION, 160, 1);
            case CEVIKLIK -> efekt(oyuncu, MobEffects.JUMP_BOOST, 220, 1);
            case SANS -> efekt(oyuncu, MobEffects.LUCK, 300, 1);
            case GORUS -> efekt(oyuncu, MobEffects.NIGHT_VISION, 360, 0);
            case SAVUNMA -> efekt(oyuncu, MobEffects.RESISTANCE, 160, 0);
            case GECE -> efekt(oyuncu, MobEffects.INVISIBILITY, 140, 0);
            case HIZ -> efekt(oyuncu, MobEffects.SPEED, 220, 1);
            case MADEN -> efekt(oyuncu, MobEffects.HASTE, 240, 1);
            case SU -> efekt(oyuncu, MobEffects.WATER_BREATHING, 320, 0);
            case ZIRH -> efekt(oyuncu, MobEffects.ABSORPTION, 260, 2);
            case ATES -> efekt(oyuncu, MobEffects.FIRE_RESISTANCE, 300, 0);
            case KESKINLIK -> efekt(oyuncu, MobEffects.STRENGTH, 180, 0);
            case YUKSELIS -> efekt(oyuncu, MobEffects.SLOW_FALLING, 320, 0);
            case ZAMAN -> efekt(oyuncu, MobEffects.DOLPHINS_GRACE, 260, 0);
            case KALP -> efekt(oyuncu, MobEffects.HEALTH_BOOST, 300, 1);
            case DIRENC -> efekt(oyuncu, MobEffects.RESISTANCE, 300, 1);
            case TECRUBE -> oyuncu.giveExperiencePoints(24);
            case KOZMIK -> {
                efekt(oyuncu, MobEffects.NIGHT_VISION, 400, 0);
                efekt(oyuncu, MobEffects.SLOW_FALLING, 400, 0);
            }
            case TICARET -> efekt(oyuncu, MobEffects.HERO_OF_THE_VILLAGE, 360, 0);
            case DENIZ -> efekt(oyuncu, MobEffects.DOLPHINS_GRACE, 360, 1);
            case BEREKET -> efekt(oyuncu, MobEffects.LUCK, 400, 2);
            case SESSIZLIK -> efekt(oyuncu, MobEffects.INVISIBILITY, 180, 0);
        }
    }

    public static void pasifUygula(Player oyuncu, RelikTanimi tanim) {
        switch (tanim.yeti()) {
            case BILGI, TECRUBE -> oyuncu.giveExperiencePoints(1);
            case MADEN -> efekt(oyuncu, MobEffects.HASTE, 70, 0);
            case HIZ -> efekt(oyuncu, MobEffects.SPEED, 70, 0);
            case GORUS -> efekt(oyuncu, MobEffects.NIGHT_VISION, 70, 0);
            case SU, DENIZ -> efekt(oyuncu, MobEffects.WATER_BREATHING, 70, 0);
            case DIRENC, SAVUNMA, ZIRH -> efekt(oyuncu, MobEffects.RESISTANCE, 70, 0);
            case KALP -> efekt(oyuncu, MobEffects.REGENERATION, 70, 0);
            default -> {
            }
        }
    }

    private static void efekt(Player oyuncu, net.minecraft.core.Holder<net.minecraft.world.effect.MobEffect> etki, int sure, int seviye) {
        oyuncu.addEffect(new MobEffectInstance(etki, sure, seviye, false, true, true));
    }
}
