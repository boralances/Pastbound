package dev.pastbound.history;

import dev.pastbound.ModId;
import dev.pastbound.relic.RelikMantigi;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

@EventBusSubscriber(modid = ModId.MOD_ID)
public final class KureselTarihOlaylari {
    private static final int DUYURU_ARALIGI = 1200;
    private static final int YAZI_BONUS_ARALIGI = 100;
    private static final int KERVAN_BONUS_ARALIGI = 200;

    private KureselTarihOlaylari() {
    }

    @SubscribeEvent
    public static void sunucuTiklandi(ServerTickEvent.Post olay) {
        MinecraftServer sunucu = olay.getServer();
        int tik = sunucu.getTickCount();
        if (!olay.hasTime()) {
            return;
        }
        if (tik % DUYURU_ARALIGI == 0) {
            donemDuyurusu(sunucu, KureselTarihOlayi.values()[(tik / DUYURU_ARALIGI) % KureselTarihOlayi.values().length]);
        }
        for (ServerPlayer oyuncu : sunucu.getPlayerList().getPlayers()) {
            ServerLevel seviye = (ServerLevel) oyuncu.level();
            long saat = seviye.getOverworldClockTime() % 24000L;
            boolean gece = saat >= 12500L && saat <= 23000L;
            boolean suda = oyuncu.isInWater();
            boolean yuksekte = oyuncu.getY() >= 120.0D;
            boolean yeraltinda = oyuncu.getY() <= 48.0D;
            boolean yaziyla = elindeVar(oyuncu, Items.WRITABLE_BOOK) || elindeVar(oyuncu, Items.BOOK);
            boolean haritayla = elindeVar(oyuncu, Items.MAP) || elindeVar(oyuncu, Items.COMPASS);
            boolean metalle = elindeVar(oyuncu, Items.IRON_INGOT) || elindeVar(oyuncu, Items.COPPER_INGOT);
            boolean altinla = elindeVar(oyuncu, Items.GOLD_INGOT) || elindeVar(oyuncu, Items.GOLD_NUGGET);
            boolean iplikle = elindeVar(oyuncu, Items.STRING);
            boolean saatle = elindeVar(oyuncu, Items.CLOCK);

            if (yaziyla && tik % YAZI_BONUS_ARALIGI == 0) {
                yaziHafizasi(oyuncu);
            }
            if (haritayla && yuksekte && tik % 80 == 0) {
                yildizSeferi(oyuncu, seviye);
            }
            if (metalle && yeraltinda && tik % 100 == 0) {
                demirciHafizasi(oyuncu, seviye);
            }
            if (altinla && tik % KERVAN_BONUS_ARALIGI == 0) {
                kervanYolu(oyuncu);
            }
            if (gece && tik % 160 == 0) {
                runeNobeti(oyuncu, seviye);
            }
            if (suda && tik % 160 == 0) {
                nilKanallari(oyuncu, seviye);
            }
            if (oyuncu.level().dimension() == net.minecraft.world.level.Level.END && tik % 200 == 0) {
                ayMisyonu(oyuncu, seviye);
            }
            if (saatle && tik % 180 == 0) {
                takvimMeclisi(oyuncu, seviye);
            }
            if (oyuncu.onGround() && !suda && tik % 220 == 0) {
                mozaikBarisi(oyuncu, seviye);
            }
            if (iplikle && tik % 200 == 0) {
                ipekDegisimi(oyuncu);
            }
            if (iplikle && tik % 240 == 0) {
                quipuSayimi(oyuncu);
            }
            if (haritayla && tik % 260 == 0) {
                astrolabGogu(oyuncu, seviye);
            }
        }
    }

    private static void donemDuyurusu(MinecraftServer sunucu, KureselTarihOlayi donem) {
        sunucu.getPlayerList().broadcastSystemMessage(Component.translatable(donem.mesajAnahtari()), false);
        for (ServerPlayer oyuncu : sunucu.getPlayerList().getPlayers()) {
            yankiyiHazirla(oyuncu, donem);
        }
    }

    private static void yaziHafizasi(ServerPlayer oyuncu) {
        oyuncu.giveExperiencePoints(1);
        oyuncu.addEffect(new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, 100, 0, false, false, true));
        yankiyiHazirla(oyuncu, KureselTarihOlayi.YAZI_DEVRIMI);
    }

    private static void yildizSeferi(ServerPlayer oyuncu, ServerLevel seviye) {
        oyuncu.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 100, 0, false, false, true));
        seviye.sendParticles(ParticleTypes.END_ROD, oyuncu.getX(), oyuncu.getY() + 1.2D, oyuncu.getZ(), 3, 0.2D, 0.2D, 0.2D, 0.01D);
        yankiyiHazirla(oyuncu, KureselTarihOlayi.YILDIZ_SEFERI);
    }

    private static void demirciHafizasi(ServerPlayer oyuncu, ServerLevel seviye) {
        oyuncu.addEffect(new MobEffectInstance(MobEffects.HASTE, 100, 0, false, false, true));
        seviye.playSound(null, BlockPos.containing(oyuncu.position()), SoundEvents.ANVIL_LAND, SoundSource.PLAYERS, 0.25F, 1.7F);
        yankiyiHazirla(oyuncu, KureselTarihOlayi.DEMIRCI_HAFIZASI);
    }

    private static void kervanYolu(ServerPlayer oyuncu) {
        oyuncu.addEffect(new MobEffectInstance(MobEffects.SPEED, 100, 0, false, false, true));
        oyuncu.giveExperiencePoints(1);
        yankiyiHazirla(oyuncu, KureselTarihOlayi.KERVAN_YOLU);
    }

    private static void runeNobeti(ServerPlayer oyuncu, ServerLevel seviye) {
        oyuncu.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 100, 0, false, false, true));
        seviye.sendParticles(ParticleTypes.ENCHANT, oyuncu.getX(), oyuncu.getY() + 1.0D, oyuncu.getZ(), 4, 0.3D, 0.4D, 0.3D, 0.02D);
        yankiyiHazirla(oyuncu, KureselTarihOlayi.RUNE_NOBETI);
    }

    private static void nilKanallari(ServerPlayer oyuncu, ServerLevel seviye) {
        oyuncu.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 100, 0, false, false, true));
        seviye.sendParticles(ParticleTypes.BUBBLE, oyuncu.getX(), oyuncu.getY(), oyuncu.getZ(), 3, 0.2D, 0.2D, 0.2D, 0.01D);
        yankiyiHazirla(oyuncu, KureselTarihOlayi.NIL_KANALLARI);
    }

    private static void ayMisyonu(ServerPlayer oyuncu, ServerLevel seviye) {
        oyuncu.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 120, 0, false, false, true));
        seviye.sendParticles(ParticleTypes.END_ROD, oyuncu.getX(), oyuncu.getY(), oyuncu.getZ(), 2, 0.4D, 0.6D, 0.4D, 0.01D);
        yankiyiHazirla(oyuncu, KureselTarihOlayi.AY_MISYONU);
    }

    private static void takvimMeclisi(ServerPlayer oyuncu, ServerLevel seviye) {
        oyuncu.addEffect(new MobEffectInstance(MobEffects.LUCK, 120, 0, false, false, true));
        seviye.sendParticles(ParticleTypes.ENCHANT, oyuncu.getX(), oyuncu.getY() + 1.0D, oyuncu.getZ(), 2, 0.25D, 0.35D, 0.25D, 0.02D);
        yankiyiHazirla(oyuncu, KureselTarihOlayi.TAKVIM_MECLISI);
    }

    private static void mozaikBarisi(ServerPlayer oyuncu, ServerLevel seviye) {
        oyuncu.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 100, 0, false, false, true));
        seviye.sendParticles(ParticleTypes.HAPPY_VILLAGER, oyuncu.getX(), oyuncu.getY() + 1.0D, oyuncu.getZ(), 3, 0.3D, 0.4D, 0.3D, 0.01D);
        yankiyiHazirla(oyuncu, KureselTarihOlayi.MOZAIK_BARISI);
    }

    private static void ipekDegisimi(ServerPlayer oyuncu) {
        oyuncu.addEffect(new MobEffectInstance(MobEffects.SPEED, 100, 0, false, false, true));
        yankiyiHazirla(oyuncu, KureselTarihOlayi.IPEK_DEGISIMI);
    }

    private static void quipuSayimi(ServerPlayer oyuncu) {
        oyuncu.giveExperiencePoints(1);
        yankiyiHazirla(oyuncu, KureselTarihOlayi.QUIPU_SAYIMI);
    }

    private static void astrolabGogu(ServerPlayer oyuncu, ServerLevel seviye) {
        oyuncu.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 100, 0, false, false, true));
        seviye.sendParticles(ParticleTypes.END_ROD, oyuncu.getX(), oyuncu.getY() + 1.4D, oyuncu.getZ(), 2, 0.3D, 0.2D, 0.3D, 0.01D);
        yankiyiHazirla(oyuncu, KureselTarihOlayi.ASTROLAB_GOGU);
    }

    private static void yankiyiHazirla(ServerPlayer oyuncu, KureselTarihOlayi donem) {
        String kimlik = switch (donem) {
            case YAZI_DEVRIMI -> "papirus_sifresi";
            case NIL_KANALLARI -> "nil_toreni";
            case YILDIZ_SEFERI -> "polinezya_yildiz";
            case KERVAN_YOLU -> "timbuktu_kervani";
            case TAKVIM_MECLISI -> "maya_takvimi";
            case DEMIRCI_HAFIZASI -> "benin_dokumu";
            case MOZAIK_BARISI -> "bizans_parca";
            case IPEK_DEGISIMI -> "song_firini";
            case QUIPU_SAYIMI -> "inka_dugumleri";
            case ASTROLAB_GOGU -> "ronesans_atolyesi";
            case RUNE_NOBETI -> "rune_tasi";
            case AY_MISYONU -> "apollo_ay_yuruyusu";
        };
        TarihYankisi yanki = TarihYankilari.yankiBul(kimlik);
        if (yanki != null) {
            RelikMantigi.yankiyiHazirla(oyuncu, yanki);
        }
    }

    private static boolean elindeVar(ServerPlayer oyuncu, Item esya) {
        return oyuncu.getMainHandItem().is(esya) || oyuncu.getOffhandItem().is(esya);
    }
}
