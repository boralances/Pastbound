package dev.pastbound.relic;

import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

import dev.pastbound.history.TarihYankisi;
import dev.pastbound.history.TarihYankilari;
import net.minecraft.advancements.AdvancementHolder;
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
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import dev.pastbound.registry.ModEffects;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import net.neoforged.neoforge.common.extensions.IEntityExtension;

public final class RelikMantigi {
    private static final String BILINEN_RELIKLER = "pastbound_bilinen_relikler";
    private static final String TAMAMLANAN_YANKILAR = "pastbound_tamamlanan_tarih_yankilari";
    private static final String HAZIR_YANKILAR = "pastbound_hazir_tarih_yankilari";
    private static final String ACIK_RELIK_YUVALARI = "pastbound_acik_relik_yuvalari";
    private static final int BASLANGIC_RELIK_YUVASI = 8;
    private static final int AZAMI_RELIK_YUVASI = 10;
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
        oyuncu.sendSystemMessage(Component.translatable("message.pastbound.relic.knowledge", tanim.adBileseni()));
    }

    public static int acikRelikYuvasi(Player oyuncu) {
        return ((IEntityExtension) oyuncu).getPersistentData().getIntOr(ACIK_RELIK_YUVALARI, BASLANGIC_RELIK_YUVASI);
    }

    public static boolean slotYukselt(Player oyuncu) {
        int acik = acikRelikYuvasi(oyuncu);
        if (acik >= AZAMI_RELIK_YUVASI) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.slot.max"));
            return false;
        }
        int blok = 0;
        for (int i = 0; i < oyuncu.getInventory().getContainerSize(); i++) {
            ItemStack yigin = oyuncu.getInventory().getItem(i);
            if (yigin.is(Items.NETHERITE_BLOCK)) {
                blok += yigin.getCount();
            }
        }
        if (blok < 10) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.slot.cost", 10));
            return false;
        }
        int kalan = 10;
        for (int i = 0; i < oyuncu.getInventory().getContainerSize() && kalan > 0; i++) {
            ItemStack yigin = oyuncu.getInventory().getItem(i);
            if (yigin.is(Items.NETHERITE_BLOCK)) {
                int alinacak = Math.min(kalan, yigin.getCount());
                oyuncu.getInventory().removeItem(i, alinacak);
                kalan -= alinacak;
            }
        }
        ((IEntityExtension) oyuncu).getPersistentData().putInt(ACIK_RELIK_YUVALARI, AZAMI_RELIK_YUVASI);
        oyuncu.sendSystemMessage(Component.translatable("message.pastbound.slot.unlocked", AZAMI_RELIK_YUVASI));
        oyuncu.level().playSound(null, oyuncu.blockPosition(), SoundEvents.NETHERITE_BLOCK_PLACE, SoundSource.PLAYERS, 0.8F, 0.8F);
        return true;
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

    public static boolean yankiTamamlandiMi(Player oyuncu, TarihYankisi yanki) {
        String kayit = ((IEntityExtension) oyuncu).getPersistentData().getStringOr(TAMAMLANAN_YANKILAR, "");
        return Arrays.asList(kayit.split("\\|", -1)).contains(yanki.kimlik());
    }

    public static boolean yankiHazirMi(Player oyuncu, TarihYankisi yanki) {
        String kayit = ((IEntityExtension) oyuncu).getPersistentData().getStringOr(HAZIR_YANKILAR, "");
        return Arrays.asList(kayit.split("\\|", -1)).contains(yanki.kimlik());
    }

    public static void yankiyiHazirla(Player oyuncu, TarihYankisi yanki) {
        if (yankiTamamlandiMi(oyuncu, yanki) || yankiHazirMi(oyuncu, yanki)) {
            return;
        }
        CompoundTag veri = ((IEntityExtension) oyuncu).getPersistentData();
        String eski = veri.getStringOr(HAZIR_YANKILAR, "");
        veri.putString(HAZIR_YANKILAR, eski.isEmpty() ? yanki.kimlik() : eski + AYRAC + yanki.kimlik());
        oyuncu.sendOverlayMessage(Component.translatable("message.pastbound.echo.stirs", yanki.baslik()));
        oyuncu.sendSystemMessage(Component.translatable("message.pastbound.echo.open_journal"));
    }

    public static int tamamlananYankiSayisi(Player oyuncu) {
        int sayi = 0;
        for (TarihYankisi yanki : TarihYankisi.values()) {
            if (yankiTamamlandiMi(oyuncu, yanki)) {
                sayi++;
            }
        }
        return sayi;
    }

    public static void yankiyiTamamla(Player oyuncu, TarihYankisi yanki) {
        if (yankiTamamlandiMi(oyuncu, yanki)) {
            return;
        }
        CompoundTag veri = ((IEntityExtension) oyuncu).getPersistentData();
        String eski = veri.getStringOr(TAMAMLANAN_YANKILAR, "");
        veri.putString(TAMAMLANAN_YANKILAR, eski.isEmpty() ? yanki.kimlik() : eski + AYRAC + yanki.kimlik());
        bilgiyeEkle(oyuncu, yanki.relik());
        oyuncu.giveExperiencePoints(yanki.deneyim());
        oyuncu.sendSystemMessage(Component.translatable("message.pastbound.echo.discovered", yanki.baslik()));
        oyuncu.sendSystemMessage(Component.translatable("message.pastbound.echo.trace", yanki.tarihIzi()));
        ilerlemeyiVer(oyuncu, yanki);
    }

    public static boolean yankiyiCoz(Player oyuncu, String kimlik, String hamle) {
        TarihYankisi yanki = TarihYankilari.yankiBul(kimlik);
        if (yanki == null) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.echo.bad_name"));
            return false;
        }
        if (yankiTamamlandiMi(oyuncu, yanki)) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.echo.already", yanki.baslik()));
            return true;
        }
        if (!yankiHazirMi(oyuncu, yanki)) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.echo.locked", yanki.baslik()));
            return false;
        }
        String temiz = hamle.replaceAll("[^1-3]", "");
        if (!temiz.equals(yanki.kod())) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.echo.wrong", yanki.hamle()));
            return false;
        }
        yankiyiTamamla(oyuncu, yanki);
        oyuncu.level().playSound(null, oyuncu.blockPosition(), SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.PLAYERS, 1.0F, 1.5F);
        return true;
    }

    private static void ilerlemeyiVer(Player oyuncu, TarihYankisi yanki) {
        if (oyuncu instanceof ServerPlayer sunucu) {
            AdvancementHolder basari = sunucu.level().getServer().getAdvancements().get(Identifier.fromNamespaceAndPath("pastbound", "history/" + yanki.kimlik()));
            if (basari != null) {
                sunucu.getAdvancements().award(basari, "kesif");
            }
            AdvancementHolder koleksiyon = sunucu.level().getServer().getAdvancements().get(Identifier.fromNamespaceAndPath("pastbound", "history/complete_collection"));
            if (koleksiyon != null && tamamlananYankiSayisi(oyuncu) >= TarihYankisi.values().length) {
                sunucu.getAdvancements().award(koleksiyon, "kesif");
            }
        }
    }

    public static boolean deneyimleTani(Player oyuncu, RelikTanimi tanim) {
        if (biliyorMu(oyuncu, tanim)) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.relic.already_known", tanim.adBileseni()));
            return true;
        }
        int bedel = tanim.bilmeSeviyesi();
        if (oyuncu.experienceLevel < bedel) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.relic.xp_hint", bedel));
            return false;
        }
        oyuncu.giveExperienceLevels(-bedel);
        bilgiyeEkle(oyuncu, tanim);
        TarihYankisi yanki = TarihYankilari.yankiBulRelik(tanim);
        if (yanki != null) {
            yankiyiTamamla(oyuncu, yanki);
        }
        oyuncu.sendSystemMessage(Component.translatable("message.pastbound.relic.identified", tanim.adBileseni()));
        oyuncu.level().playSound(null, oyuncu.blockPosition(), SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.PLAYERS, 1.0F, 1.4F);
        return true;
    }

    public static boolean bilmeceCevapla(Player oyuncu, String kimlik, String cevap) {
        RelikTanimi tanim = tanimBul(kimlik);
        if (tanim == null) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.relic.bad_name"));
            return false;
        }
        if (biliyorMu(oyuncu, tanim)) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.relic.already_known", tanim.adBileseni()));
            return true;
        }
        String temiz = cevap.toLowerCase(Locale.ROOT).replace('ı', 'i').replace('ş', 's').replace('ğ', 'g').replace('ü', 'u').replace('ö', 'o').replace('ç', 'c');
        if (!cevapDogruMu(tanim, temiz)) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.relic.riddle_wrong"));
            return false;
        }
        bilgiyeEkle(oyuncu, tanim);
        TarihYankisi yanki = TarihYankilari.yankiBulRelik(tanim);
        if (yanki != null) {
            yankiyiTamamla(oyuncu, yanki);
        }
        oyuncu.sendSystemMessage(Component.translatable("message.pastbound.relic.riddle_right", tanim.adBileseni()));
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
        oyuncu.sendSystemMessage(Component.translatable("message.pastbound.relic.activated", tanim.adBileseni()));
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
        ozelYankiUygula(oyuncu, tanim);
    }

    private static void ozelYankiUygula(Player oyuncu, RelikTanimi tanim) {
        if (tanim.ordinal() < 16) {
            oyuncu.addEffect(new MobEffectInstance(ModEffects.TARIH_YANKISI, 220, tanim.ordinal() % 3, false, true, true));
        }
        switch (tanim) {
            case ROSSETTA_TASI -> {
                oyuncu.giveExperiencePoints(6);
                yakinCanlilariAydinlat(oyuncu, 8.0D);
            }
            case GILGAMESH_TABLETI -> {
                efekt(oyuncu, MobEffects.ABSORPTION, 160, 1);
                oyuncu.setDeltaMovement(oyuncu.getDeltaMovement().add(0.0D, 0.18D, 0.0D));
            }
            case ANUBIS_ANKHI -> {
                efekt(oyuncu, MobEffects.REGENERATION, 100, 0);
                oyuncu.heal(4.0F);
                oyuncu.setRemainingFireTicks(0);
            }
            case MINOS_LABIRENT_MUHRU -> {
                efekt(oyuncu, MobEffects.SPEED, 100, 0);
                oyuncu.setDeltaMovement(oyuncu.getDeltaMovement().add(0.0D, 0.28D, 0.0D));
            }
            case ROMA_AUREUSU -> {
                oyuncu.giveExperiencePoints(8);
                oyuncu.addItem(new ItemStack(Items.GOLD_NUGGET));
            }
            case VIKING_GUNES_PUSULASI -> {
                efekt(oyuncu, MobEffects.HASTE, 120, 0);
                efekt(oyuncu, MobEffects.NIGHT_VISION, 120, 0);
            }
            case SAMURAY_KABZASI -> {
                efekt(oyuncu, MobEffects.RESISTANCE, 120, 0);
                oyuncu.setAbsorptionAmount(Math.max(2.0F, oyuncu.getAbsorptionAmount()));
            }
            case MAYA_GUNES_CARKI -> {
                oyuncu.setRemainingFireTicks(0);
                efekt(oyuncu, MobEffects.FIRE_RESISTANCE, 120, 0);
            }
            case INKA_QUIPUSU -> {
                oyuncu.giveExperiencePoints(5);
                efekt(oyuncu, MobEffects.SPEED, 100, 1);
            }
            case HARAPPA_MUHRU -> {
                efekt(oyuncu, MobEffects.HASTE, 120, 1);
                oyuncu.addItem(new ItemStack(Items.CLAY_BALL, 2));
            }
            case SONG_PORSELENI -> {
                efekt(oyuncu, MobEffects.WATER_BREATHING, 120, 0);
                oyuncu.setAirSupply(Math.min(oyuncu.getMaxAirSupply(), oyuncu.getAirSupply() + 80));
            }
            case BENIN_BRONZU -> {
                efekt(oyuncu, MobEffects.ABSORPTION, 120, 0);
                yakinCanlilariAydinlat(oyuncu, 5.0D);
            }
            case AZTEK_GUNES_TASI -> {
                oyuncu.setRemainingFireTicks(0);
                efekt(oyuncu, MobEffects.FIRE_RESISTANCE, 160, 0);
                oyuncu.giveExperiencePoints(3);
            }
            case ABBASID_MUREKKEBI -> {
                oyuncu.giveExperiencePoints(10);
                efekt(oyuncu, MobEffects.HASTE, 100, 0);
            }
            case RONESANS_ASTROLABI -> {
                efekt(oyuncu, MobEffects.SLOW_FALLING, 160, 0);
                oyuncu.setDeltaMovement(oyuncu.getDeltaMovement().add(0.0D, 0.22D, 0.0D));
            }
            case ANTIKITHERA_DUZENEĞI -> {
                oyuncu.getCooldowns().addCooldown(oyuncu.getMainHandItem(), 20);
                oyuncu.addEffect(new MobEffectInstance(MobEffects.HASTE, 80, 1, false, false, true));
                oyuncu.giveExperiencePoints(4);
            }
            case CATALHOYUK_BONCUGU -> {
                efekt(oyuncu, MobEffects.REGENERATION, 80, 0);
                oyuncu.heal(2.0F);
            }
            case BIZANS_MOZAIGI -> {
                efekt(oyuncu, MobEffects.RESISTANCE, 100, 0);
                yakinCanlilariAydinlat(oyuncu, 6.0D);
            }
            case TIMBUKTU_KALEMI -> {
                oyuncu.giveExperiencePoints(9);
            }
            case APOLLO17_ARMASI -> {
                efekt(oyuncu, MobEffects.SLOW_FALLING, 220, 1);
                efekt(oyuncu, MobEffects.NIGHT_VISION, 220, 0);
                oyuncu.setDeltaMovement(oyuncu.getDeltaMovement().add(0.0D, 0.16D, 0.0D));
            }
            case ILHANLI_MADALYONU -> {
                efekt(oyuncu, MobEffects.HERO_OF_THE_VILLAGE, 120, 0);
                oyuncu.addItem(new ItemStack(Items.EMERALD));
            }
            case POLINEZYA_YILDIZ_HARITASI -> {
                efekt(oyuncu, MobEffects.WATER_BREATHING, 160, 0);
                efekt(oyuncu, MobEffects.DOLPHINS_GRACE, 160, 0);
                oyuncu.setDeltaMovement(oyuncu.getDeltaMovement().multiply(1.15D, 1.0D, 1.15D));
            }
            case MALI_TUZ_MUHRU -> {
                oyuncu.giveExperiencePoints(7);
                efekt(oyuncu, MobEffects.LUCK, 160, 1);
            }
            case ISKANDINAV_RUNETASI -> {
                efekt(oyuncu, MobEffects.INVISIBILITY, 80, 0);
                efekt(oyuncu, MobEffects.SPEED, 80, 0);
            }
        }
    }

    private static void yakinCanlilariAydinlat(Player oyuncu, double mesafe) {
        if (oyuncu.level() instanceof ServerLevel seviye) {
            AABB alan = oyuncu.getBoundingBox().inflate(mesafe);
            for (LivingEntity varlik : seviye.getEntitiesOfClass(LivingEntity.class, alan)) {
                if (varlik != oyuncu) {
                    varlik.addEffect(new MobEffectInstance(MobEffects.GLOWING, 100, 0, false, false, true));
                }
            }
        }
    }

    public static void pasifUygula(Player oyuncu, RelikTanimi tanim) {
        switch (tanim.yeti()) {
            case BILGI, TECRUBE -> oyuncu.giveExperiencePoints(1);
            case MADEN -> efekt(oyuncu, MobEffects.HASTE, 70, 0);
            case HIZ -> efekt(oyuncu, MobEffects.SPEED, 70, 0);
            case GORUS -> efekt(oyuncu, MobEffects.NIGHT_VISION, 70, 0);
            case SU, DENIZ -> efekt(oyuncu, MobEffects.WATER_BREATHING, 70, 0);
            case DIRENC, SAVUNMA, ZIRH -> {
            }
            case KALP -> efekt(oyuncu, MobEffects.REGENERATION, 70, 0);
            default -> {
            }
        }
    }

    private static void efekt(Player oyuncu, net.minecraft.core.Holder<net.minecraft.world.effect.MobEffect> etki, int sure, int seviye) {
        oyuncu.addEffect(new MobEffectInstance(etki, sure, seviye, false, true, true));
    }
}
