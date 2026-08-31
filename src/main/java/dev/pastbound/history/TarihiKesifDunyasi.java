package dev.pastbound.history;

import java.util.Set;

import dev.pastbound.ModId;
import dev.pastbound.network.PastboundPaketi;
import dev.pastbound.block.ResonancePillarBlock;
import dev.pastbound.registry.ModBlocks;
import dev.pastbound.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.StructureTags;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.neoforged.neoforge.common.extensions.IEntityExtension;
import net.neoforged.neoforge.network.PacketDistributor;
import net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket;

public final class TarihiKesifDunyasi {
    private static final String DONUS_BOYUTU = "pastbound_donus_boyutu";
    private static final String DONUS_X = "pastbound_donus_x";
    private static final String DONUS_Y = "pastbound_donus_y";
    private static final String DONUS_Z = "pastbound_donus_z";
    private static final String DONUS_YAW = "pastbound_donus_yaw";
    private static final String DONUS_PITCH = "pastbound_donus_pitch";
    private static final String SAHNE_CAGI = "pastbound_sahne_cagi";
    private static final String SAHNE_AKTIF = "pastbound_sahne_aktif";
    private static final String SAHNE_SAYACI = "pastbound_sahne_sayaci";
    private static final String SAHNE_GOREV_MASKESI = "pastbound_sahne_gorev_maskesi";
    private static final String CELIK_GOREV_ASAMASI = "pastbound_celik_gorev_asamasi";
    private static final String CELIK_DAMAR_SAYISI = "pastbound_celik_damar_sayisi";
    private static final String YURUME_MESAFESI = "pastbound_yurume_mesafesi";
    private static final String IZLEME_X = "pastbound_izleme_x";
    private static final String IZLEME_Y = "pastbound_izleme_y";
    private static final String IZLEME_Z = "pastbound_izleme_z";
    private static final String DUNYA_GOREVI = "pastbound_dunya_gorevi";
    private static final String DUNYA_HEDEF_X = "pastbound_dunya_hedef_x";
    private static final String DUNYA_HEDEF_Y = "pastbound_dunya_hedef_y";
    private static final String DUNYA_HEDEF_Z = "pastbound_dunya_hedef_z";
    private static final String DUNYA_MADEN_X = "pastbound_dunya_maden_x";
    private static final String DUNYA_MADEN_Y = "pastbound_dunya_maden_y";
    private static final String DUNYA_MADEN_Z = "pastbound_dunya_maden_z";
    private static final String DUNYA_CELIK = "pastbound_dunya_celik";
    private static final String DUNYA_MADEN_GIRILDI = "pastbound_dunya_maden_girildi";
    private static final String TAMAMLANAN_DUNYALAR = "pastbound_tamamlanan_dunyalar";
    private static final String AMETIST_X = "pastbound_amatist_x";
    private static final String AMETIST_Y = "pastbound_amatist_y";
    private static final String AMETIST_Z = "pastbound_amatist_z";
    private static final String AMETIST_TICK = "pastbound_amatist_tick";
    private static final String BASARISIZ_KESIFLER = "pastbound_basarisiz_kesifler";
    private static final int SAHNE_ANIT_BITI = 256;
    private static final int SAHNE_INCELEME_BITI = 128;
    private static final int SAHNE_DURAK_A_BITI = 16;
    private static final int SAHNE_DURAK_B_BITI = 32;
    private static final int SAHNE_DURAK_C_BITI = 512;
    private static final int SAHNE_DURAK_A_KONUSMA_BITI = 4096;
    private static final int SAHNE_DURAK_B_KONUSMA_BITI = 8192;
    private static final int SAHNE_DURAK_C_KONUSMA_BITI = 16384;
    private static final int SAHNE_DURAK_A_CIHAZ_BITI = 32768;
    private static final int SAHNE_DURAK_B_CIHAZ_BITI = 65536;
    private static final int SAHNE_DURAK_C_CIHAZ_BITI = 131072;
    private static final int DONEM_OZEL_BITI = 1048576;
    private static final int SAHNE_KOY_BITI = 2097152;
    private static final int SAHNE_MADEN_BITI = 4194304;
    private static final int SAHNE_ATOLYE_BITI = 8388608;
    private static final int SAHNE_UZAK_LOKASYON_MASKESI = SAHNE_KOY_BITI | SAHNE_MADEN_BITI | SAHNE_ATOLYE_BITI;
    private static final int SAHNE_DURAKLAR_MASKESI = SAHNE_DURAK_A_BITI | SAHNE_DURAK_B_BITI | SAHNE_DURAK_C_BITI;
    private static final int SAHNE_DURAK_KONUSMA_MASKESI = SAHNE_DURAK_A_KONUSMA_BITI | SAHNE_DURAK_B_KONUSMA_BITI | SAHNE_DURAK_C_KONUSMA_BITI;
    private static final int SAHNE_DURAK_CIHAZ_MASKESI = SAHNE_DURAK_A_CIHAZ_BITI | SAHNE_DURAK_B_CIHAZ_BITI | SAHNE_DURAK_C_CIHAZ_BITI;
    private static final double YURUME_HEDEFI_BLOK = 55.0D;
    private static final BlockPos SAHNE_MERKEZI = new BlockPos(0, 64, 0);
    private static final BlockPos[] GOREV_DURAKLARI = {
            SAHNE_MERKEZI.offset(-7, 0, -7),
            SAHNE_MERKEZI.offset(7, 0, -7),
            SAHNE_MERKEZI.offset(0, 0, 7)
    };

    private TarihiKesifDunyasi() {
    }

    public static ResourceKey<Level> boyut(TarihDonemi donem) {
        return ResourceKey.create(Registries.DIMENSION, Identifier.fromNamespaceAndPath(ModId.MOD_ID, "tarih_" + donem.kimlik()));
    }

    public static boolean tarihBoyutuMu(ResourceKey<Level> kimlik) {
        return donemBulBoyuttan(kimlik) != null;
    }

    public static boolean boyuttaMi(ServerPlayer oyuncu) {
        return donemBulBoyuttan(oyuncu.level().dimension()) != null;
    }

    public static boolean canlandirmaAktifMi(ServerPlayer oyuncu) {
        return boyuttaMi(oyuncu) && ((IEntityExtension) oyuncu).getPersistentData().getBooleanOr(SAHNE_AKTIF, false);
    }

    public static boolean baslat(ServerPlayer oyuncu, TarihDonemi donem) {
        MinecraftServer sunucu = oyuncu.level().getServer();
        ServerLevel hedef = sunucu.getLevel(boyut(donem));
        if (hedef == null) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.time_machine.dimension_unavailable"));
            return false;
        }
        CompoundTag veri = ((IEntityExtension) oyuncu).getPersistentData();
        if (!boyuttaMi(oyuncu) || veri.getStringOr(DONUS_BOYUTU, "").isEmpty()) {
            veri.putString(DONUS_BOYUTU, oyuncu.level().dimension().identifier().toString());
            veri.putDouble(DONUS_X, oyuncu.getX());
            veri.putDouble(DONUS_Y, oyuncu.getY());
            veri.putDouble(DONUS_Z, oyuncu.getZ());
            veri.putFloat(DONUS_YAW, oyuncu.getYRot());
            veri.putFloat(DONUS_PITCH, oyuncu.getXRot());
        }
        veri.putString(SAHNE_CAGI, donem.kimlik());
        veri.putBoolean(SAHNE_AKTIF, true);
        veri.putInt(SAHNE_SAYACI, 0);
        veri.putInt(SAHNE_GOREV_MASKESI, 0);
        veri.putInt(CELIK_GOREV_ASAMASI, donem == TarihDonemi.BAGDAT_PILI_ATOLYESI ? 1 : 0);
        veri.putInt(CELIK_DAMAR_SAYISI, 0);
        veri.remove("pastbound_donem_ozel_sayac");
        veri.putDouble(YURUME_MESAFESI, 0.0D);
        veri.remove(IZLEME_X);
        veri.remove(IZLEME_Y);
        veri.remove(IZLEME_Z);
        sahneyiKur(hedef, SAHNE_MERKEZI, donem);
        oyuncu.teleportTo(hedef, SAHNE_MERKEZI.getX() + 0.5D, SAHNE_MERKEZI.getY() + 1.0D, SAHNE_MERKEZI.getZ() + 0.5D, Set.of(), 0.0F, 0.0F, false);
        if (oyuncu.getInventory().countItem(Items.IRON_PICKAXE) == 0 && oyuncu.getInventory().countItem(Items.DIAMOND_PICKAXE) == 0 && oyuncu.getInventory().countItem(Items.NETHERITE_PICKAXE) == 0) {
            oyuncu.getInventory().placeItemBackInInventory(new ItemStack(Items.IRON_PICKAXE));
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.scene.mining_tool"));
        }
        if (oyuncu.getInventory().countItem(Items.COAL) < 16) {
            oyuncu.getInventory().placeItemBackInInventory(new ItemStack(Items.COAL, 16));
            oyuncu.getInventory().placeItemBackInInventory(new ItemStack(Items.TORCH, 16));
        }
        oyuncu.sendSystemMessage(Component.translatable("message.pastbound.scene.enter", donem.adBileseni()));
        oyuncu.sendSystemMessage(Component.translatable("message.pastbound.scene.press_d"));
        oyuncu.sendSystemMessage(Component.translatable(donem == TarihDonemi.BAGDAT_PILI_ATOLYESI ? "message.pastbound.mission.steel_start" : "message.pastbound.scene.quest_period", Component.translatable("screen.pastbound.scene.task." + donem.kimlik())));
        PacketDistributor.sendToPlayer(oyuncu, PastboundPaketi.sahne(donem.kimlik(), 0));
        return true;
    }

    public static void kontroluAl(ServerPlayer oyuncu) {
        if (!canlandirmaAktifMi(oyuncu)) {
            return;
        }
        CompoundTag veri = ((IEntityExtension) oyuncu).getPersistentData();
        veri.putBoolean(SAHNE_AKTIF, false);
        veri.putInt(SAHNE_SAYACI, 0);
        oyuncu.sendSystemMessage(Component.translatable("message.pastbound.scene.control"));
        PacketDistributor.sendToPlayer(oyuncu, PastboundPaketi.sahne("", -1));
        oyuncu.level().playSound(null, oyuncu.blockPosition(), net.minecraft.sounds.SoundEvents.RESPAWN_ANCHOR_CHARGE, net.minecraft.sounds.SoundSource.PLAYERS, 0.8F, 1.5F);
    }

    public static void tik(ServerPlayer oyuncu) {
        if (!canlandirmaAktifMi(oyuncu)) {
            return;
        }
        CompoundTag veri = ((IEntityExtension) oyuncu).getPersistentData();
        int sayac = veri.getIntOr(SAHNE_SAYACI, 0) + 1;
        veri.putInt(SAHNE_SAYACI, sayac);
        oyuncu.setDeltaMovement(0.0D, 0.0D, 0.0D);
        TarihDonemi donem = donemBul(veri.getStringOr(SAHNE_CAGI, ""));
        if (donem == null) {
            return;
        }
        sahneAktorleriniHareketEttir(oyuncu.level(), SAHNE_MERKEZI, sayac);
        if (sayac % 50 == 0) {
            sahneKapisiAnimasyonla(oyuncu.level(), SAHNE_MERKEZI, donem, (sayac / 50) % 2 == 1);
        }
        if (sayac % 20 == 0) {
            PacketDistributor.sendToPlayer(oyuncu, PastboundPaketi.sahne(donem.kimlik(), sayac));
        }
        if (sayac == 1) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.scene.narration_intro", donem.adBileseni(), donem.aciklamaBileseni()));
        } else if (sayac == 80) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.scene.narration_focus", donem.odakBileseni()));
        } else if (sayac == 160) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.scene.narration_detail", donem.aciklamaBileseni()));
        } else         if (sayac == 220) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.scene.ready"));
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.scene.quest_explore"));
        }

    }

    public static boolean celiKirilabilir(ServerPlayer oyuncu, BlockPos konum) {
        if (!boyuttaMi(oyuncu) || donemBulBoyuttan(oyuncu.level().dimension()) != TarihDonemi.BAGDAT_PILI_ATOLYESI) {
            return false;
        }
        CompoundTag veri = ((IEntityExtension) oyuncu).getPersistentData();
        return veri.getIntOr(CELIK_GOREV_ASAMASI, 0) <= 1 && oyuncu.level().getBlockState(konum).is(ModBlocks.STEEL_ORE.get());
    }

    public static void celiKirilmasi(ServerPlayer oyuncu) {
        CompoundTag veri = ((IEntityExtension) oyuncu).getPersistentData();
        if (veri.getIntOr(CELIK_GOREV_ASAMASI, 0) != 1) {
            return;
        }
        int damar = veri.getIntOr(CELIK_DAMAR_SAYISI, 0) + 1;
        veri.putInt(CELIK_DAMAR_SAYISI, damar);
        oyuncu.giveExperiencePoints(1);
        if (damar < 6) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.mission.steel_vein", damar, 6));
            return;
        }
        veri.putInt(CELIK_GOREV_ASAMASI, 2);
        oyuncu.sendSystemMessage(Component.translatable("message.pastbound.mission.steel_mined"));
        oyuncu.giveExperiencePoints(2);
        goreviKontrolEt(oyuncu);
    }

    public static void celikKulluguEritildi(ServerPlayer oyuncu) {
        if (!boyuttaMi(oyuncu) || donemBulBoyuttan(oyuncu.level().dimension()) != TarihDonemi.BAGDAT_PILI_ATOLYESI) {
            return;
        }
        CompoundTag veri = ((IEntityExtension) oyuncu).getPersistentData();
        if (veri.getIntOr(CELIK_GOREV_ASAMASI, 0) == 2) {
            veri.putInt(CELIK_GOREV_ASAMASI, 3);
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.mission.steel_smelted"));
            oyuncu.giveExperiencePoints(4);
        }
    }

    public static void celikLevhaUretildi(ServerPlayer oyuncu) {
        if (!boyuttaMi(oyuncu) || donemBulBoyuttan(oyuncu.level().dimension()) != TarihDonemi.BAGDAT_PILI_ATOLYESI) {
            return;
        }
        CompoundTag veri = ((IEntityExtension) oyuncu).getPersistentData();
        if (veri.getIntOr(CELIK_GOREV_ASAMASI, 0) == 3) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.mission.plate_ready"));
        }
    }

    public static boolean forgeOnar(ServerPlayer oyuncu, BlockPos konum) {
        if (!boyuttaMi(oyuncu) || donemBulBoyuttan(oyuncu.level().dimension()) != TarihDonemi.BAGDAT_PILI_ATOLYESI || oyuncu.distanceToSqr(konum.getX() + 0.5D, konum.getY() + 0.5D, konum.getZ() + 0.5D) > 36.0D) {
            return false;
        }
        CompoundTag veri = ((IEntityExtension) oyuncu).getPersistentData();
        if (veri.getIntOr(CELIK_GOREV_ASAMASI, 0) >= 4) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.mission.already_repaired"));
            return true;
        }
        if (veri.getIntOr(CELIK_GOREV_ASAMASI, 0) < 3) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.mission.forge_locked"));
            return true;
        }
        if (!oyuncu.getInventory().contains(new ItemStack(ModItems.STEEL_PLATE.get()))) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.mission.plate_needed"));
            return true;
        }
        for (int i = 0; i < oyuncu.getInventory().getContainerSize(); i++) {
            ItemStack yigin = oyuncu.getInventory().getItem(i);
            if (yigin.is(ModItems.STEEL_PLATE.get())) {
                yigin.shrink(1);
                break;
            }
        }
        veri.putInt(CELIK_GOREV_ASAMASI, 4);
        oyuncu.sendSystemMessage(Component.translatable("message.pastbound.mission.forge_repaired"));
        oyuncu.giveExperiencePoints(8);
        goreviKontrolEt(oyuncu);
        return true;
    }

    public static boolean anitKirilabilir(ServerPlayer oyuncu, BlockPos konum) {
        if (!boyuttaMi(oyuncu) || !konum.equals(SAHNE_MERKEZI.north(6))) {
            return false;
        }
        CompoundTag veri = ((IEntityExtension) oyuncu).getPersistentData();
        TarihDonemi donem = donemBul(veri.getStringOr(SAHNE_CAGI, ""));
        return donem != null && (veri.getIntOr(SAHNE_GOREV_MASKESI, 0) & SAHNE_ANIT_BITI) == 0 && oyuncu.level().getBlockState(konum).is(gorevAniti(donem));
    }

    public static void anitKirildi(ServerPlayer oyuncu) {
        CompoundTag veri = ((IEntityExtension) oyuncu).getPersistentData();
        int maske = veri.getIntOr(SAHNE_GOREV_MASKESI, 0) | SAHNE_ANIT_BITI;
        veri.putInt(SAHNE_GOREV_MASKESI, maske);
        oyuncu.sendSystemMessage(Component.translatable("message.pastbound.scene.quest_artifact"));
        oyuncu.giveExperiencePoints(3);
        goreviKontrolEt(oyuncu);
    }

    public static boolean anitIncelenebilir(ServerPlayer oyuncu, BlockPos konum) {
        if (!boyuttaMi(oyuncu) || !konum.equals(SAHNE_MERKEZI.north(6))) {
            return false;
        }
        CompoundTag veri = ((IEntityExtension) oyuncu).getPersistentData();
        TarihDonemi donem = donemBul(veri.getStringOr(SAHNE_CAGI, ""));
        return donem != null && (veri.getIntOr(SAHNE_GOREV_MASKESI, 0) & SAHNE_INCELEME_BITI) == 0 && oyuncu.level().getBlockState(konum).is(gorevAniti(donem));
    }

    public static void anitIncelendi(ServerPlayer oyuncu) {
        CompoundTag veri = ((IEntityExtension) oyuncu).getPersistentData();
        veri.putInt(SAHNE_GOREV_MASKESI, veri.getIntOr(SAHNE_GOREV_MASKESI, 0) | SAHNE_INCELEME_BITI);
        oyuncu.sendSystemMessage(Component.translatable("message.pastbound.scene.quest_inspect"));
        oyuncu.giveExperiencePoints(2);
        goreviKontrolEt(oyuncu);
    }

    public static void durakAletiEtkilesildi(ServerPlayer oyuncu, BlockPos konum) {
        if (!boyuttaMi(oyuncu)) {
            return;
        }
        int durak = -1;
        for (int i = 0; i < GOREV_DURAKLARI.length; i++) {
            if (GOREV_DURAKLARI[i].equals(konum)) {
                durak = i;
                break;
            }
        }
        if (durak < 0) {
            return;
        }
        CompoundTag veri = ((IEntityExtension) oyuncu).getPersistentData();
        int[] hareketBitleri = {SAHNE_DURAK_A_BITI, SAHNE_DURAK_B_BITI, SAHNE_DURAK_C_BITI};
        int[] cihazBitleri = {SAHNE_DURAK_A_CIHAZ_BITI, SAHNE_DURAK_B_CIHAZ_BITI, SAHNE_DURAK_C_CIHAZ_BITI};
        int maske = veri.getIntOr(SAHNE_GOREV_MASKESI, 0);
        if ((maske & hareketBitleri[durak]) == 0) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.scene.waypoint_locked"));
            return;
        }
        if ((maske & cihazBitleri[durak]) == 0) {
            veri.putInt(SAHNE_GOREV_MASKESI, maske | cihazBitleri[durak]);
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.scene.station_used", durak + 1));
            oyuncu.giveExperiencePoints(1);
            goreviKontrolEt(oyuncu);
        }
    }

    public static void durakKonusuldu(ServerPlayer oyuncu, int durak) {
        if (!boyuttaMi(oyuncu) || durak < 0 || durak > 2) {
            return;
        }
        CompoundTag veri = ((IEntityExtension) oyuncu).getPersistentData();
        int[] hareketBitleri = {SAHNE_DURAK_A_BITI, SAHNE_DURAK_B_BITI, SAHNE_DURAK_C_BITI};
        int[] konusmaBitleri = {SAHNE_DURAK_A_KONUSMA_BITI, SAHNE_DURAK_B_KONUSMA_BITI, SAHNE_DURAK_C_KONUSMA_BITI};
        int maske = veri.getIntOr(SAHNE_GOREV_MASKESI, 0);
        if ((maske & hareketBitleri[durak]) == 0) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.scene.waypoint_locked"));
            return;
        }
        if ((maske & konusmaBitleri[durak]) != 0) {
            return;
        }
        veri.putInt(SAHNE_GOREV_MASKESI, maske | konusmaBitleri[durak]);
        oyuncu.sendSystemMessage(Component.translatable("message.pastbound.scene.waypoint_talk", durak + 1));
        oyuncu.giveExperiencePoints(2);
        goreviKontrolEt(oyuncu);
    }

    public static void dunyaGoreviniBaslat(ServerPlayer oyuncu) {
        if (!oyuncu.level().dimension().equals(Level.OVERWORLD)) {
            return;
        }
        CompoundTag veri = ((IEntityExtension) oyuncu).getPersistentData();
        if (veri.getIntOr(DUNYA_GOREVI, 0) != 0) {
            return;
        }
        ServerLevel seviye = (ServerLevel) oyuncu.level();
        BlockPos koy = seviye.findNearestMapStructure(StructureTags.VILLAGE, oyuncu.blockPosition(), 512, false);
        if (koy == null || koy.equals(BlockPos.ZERO)) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.world.structure_missing"));
            return;
        }
        BlockPos maden = seviye.findNearestMapStructure(StructureTags.MINESHAFT, koy, 768, false);
        if (maden == null || maden.equals(BlockPos.ZERO)) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.world.structure_missing"));
            return;
        }
        BlockPos uzmanKonumu = new BlockPos(koy.getX(), seviye.getHeight(net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, koy.getX(), koy.getZ()), koy.getZ());
        baslangicUzmaniKur(seviye, uzmanKonumu);
        veri.putInt(DUNYA_GOREVI, 1);
        veri.putDouble(DUNYA_HEDEF_X, koy.getX() + 0.5D);
        veri.putDouble(DUNYA_HEDEF_Y, seviye.getHeight(net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, koy.getX(), koy.getZ()) + 1.0D);
        veri.putDouble(DUNYA_HEDEF_Z, koy.getZ() + 0.5D);
        veri.putDouble(DUNYA_MADEN_X, maden.getX() + 0.5D);
        veri.putDouble(DUNYA_MADEN_Y, seviye.getHeight(net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, maden.getX(), maden.getZ()) + 1.0D);
        veri.putDouble(DUNYA_MADEN_Z, maden.getZ() + 0.5D);
        veri.putInt(DUNYA_CELIK, 0);
        oyuncu.sendSystemMessage(Component.translatable("message.pastbound.world.quest_title"));
        oyuncu.sendSystemMessage(Component.translatable("message.pastbound.world.go_village"));
    }

    public static void dunyaGoreviTik(ServerPlayer oyuncu) {
        if (!oyuncu.level().dimension().equals(Level.OVERWORLD)) {
            return;
        }
        CompoundTag veri = ((IEntityExtension) oyuncu).getPersistentData();
        temizleYanlisBaslangicUzmani(oyuncu, veri);
        int asama = veri.getIntOr(DUNYA_GOREVI, 0);
        if (asama == 1 && hedefeUlastiMi(oyuncu)) {
            veri.putInt(DUNYA_GOREVI, 2);
            hedefiMadenYap(oyuncu);
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.world.village_reached"));
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.world.go_mine"));
        } else if (asama == 2 && hedefeUlastiMi(oyuncu)) {
            if (!veri.getBooleanOr(DUNYA_MADEN_GIRILDI, false)) {
                veri.putBoolean(DUNYA_MADEN_GIRILDI, true);
                oyuncu.sendSystemMessage(Component.translatable("message.pastbound.world.mine_reached"));
                oyuncu.sendSystemMessage(Component.translatable("message.pastbound.world.mine_steel"));
            }
        } else if (asama == 3 && hedefeUlastiMi(oyuncu)) {
            if (oyuncu.getInventory().countItem(ModItems.RAW_STEEL.get()) >= 3) {
                veri.putInt(DUNYA_GOREVI, 4);
                MinecraftServer sunucu = oyuncu.level().getServer();
                ServerLevel seviye = sunucu.overworld();
                BlockPos merkez = new BlockPos(0, 64, 0);
                seviye.setBlock(merkez, ModBlocks.RESONANCE_PILLAR.get().defaultBlockState(), 3);
                oyuncu.sendSystemMessage(Component.translatable("message.pastbound.world.returned_with_steel"));
                oyuncu.sendSystemMessage(Component.translatable("message.pastbound.world.build_power"));
            }
        }
    }

    private static void temizleYanlisBaslangicUzmani(ServerPlayer oyuncu, CompoundTag veri) {
        double hedefX = veri.getDoubleOr(DUNYA_HEDEF_X, Double.MAX_VALUE);
        double hedefY = veri.getDoubleOr(DUNYA_HEDEF_Y, Double.MAX_VALUE);
        double hedefZ = veri.getDoubleOr(DUNYA_HEDEF_Z, Double.MAX_VALUE);
        for (Entity varlik : oyuncu.level().getEntitiesOfClass(Entity.class, oyuncu.getBoundingBox().inflate(16.0D))) {
            if (varlik.entityTags().contains("pastbound_koy_uzmani") && varlik.distanceToSqr(hedefX, hedefY, hedefZ) > 1024.0D) {
                varlik.discard();
            }
        }
    }

    private static boolean hedefeUlastiMi(ServerPlayer oyuncu) {
        CompoundTag veri = ((IEntityExtension) oyuncu).getPersistentData();
        double dx = oyuncu.getX() - veri.getDoubleOr(DUNYA_HEDEF_X, Double.MAX_VALUE);
        double dy = oyuncu.getY() - veri.getDoubleOr(DUNYA_HEDEF_Y, Double.MAX_VALUE);
        double dz = oyuncu.getZ() - veri.getDoubleOr(DUNYA_HEDEF_Z, Double.MAX_VALUE);
        return dx * dx + dy * dy + dz * dz <= 64.0D;
    }

    private static void baslangicUzmaniKur(ServerLevel seviye, BlockPos merkez) {
        for (Entity varlik : seviye.getEntitiesOfClass(Entity.class, new net.minecraft.world.phys.AABB(merkez).inflate(12.0D))) {
            if (varlik.entityTags().contains("pastbound_koy_uzmani")) {
                varlik.discard();
            }
        }
        Entity varlik = BuiltInRegistries.ENTITY_TYPE.getValue(Identifier.parse("minecraft:villager")).create(seviye, EntitySpawnReason.COMMAND);
        if (varlik instanceof Villager koylu) {
            koylu.setPos(merkez.getX() + 2.5D, merkez.getY() + 1.0D, merkez.getZ() + 2.5D);
            koylu.setNoAi(true);
            koylu.setInvulnerable(true);
            koylu.setCustomName(Component.translatable("entity.pastbound.village.archivist"));
            koylu.setCustomNameVisible(true);
            koylu.addTag("pastbound_koy_uzmani");
            seviye.addFreshEntity(koylu);
        }
    }

    public static void koyUzmaniIleKonusuldu(ServerPlayer oyuncu) {
        CompoundTag veri = ((IEntityExtension) oyuncu).getPersistentData();
        if (veri.getIntOr(DUNYA_GOREVI, 0) == 1) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.world.archivist_spoke"));
            veri.putInt(DUNYA_GOREVI, 2);
            hedefiMadenYap(oyuncu);
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.world.go_mine"));
        }
    }

    public static void dunyaCelikKirildi(ServerPlayer oyuncu) {
        CompoundTag veri = ((IEntityExtension) oyuncu).getPersistentData();
        if (veri.getIntOr(DUNYA_GOREVI, 0) != 2) {
            return;
        }
        int sayi = veri.getIntOr(DUNYA_CELIK, 0) + 1;
        veri.putInt(DUNYA_CELIK, sayi);
        oyuncu.sendSystemMessage(Component.translatable("message.pastbound.world.steel_found", sayi, 3));
        if (sayi >= 3) {
            veri.putInt(DUNYA_GOREVI, 3);
            veri.putDouble(DUNYA_HEDEF_X, 0.5D);
            veri.putDouble(DUNYA_HEDEF_Y, 65.0D);
            veri.putDouble(DUNYA_HEDEF_Z, 0.5D);
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.world.carry_steel"));
        }
    }

    public static boolean tarihiResonansEtkilesildi(ServerPlayer oyuncu, BlockPos konum) {
        if (!boyuttaMi(oyuncu) || !oyuncu.level().getBlockState(konum).is(ModBlocks.RESONANCE_PILLAR.get())) {
            return false;
        }
        CompoundTag veri = ((IEntityExtension) oyuncu).getPersistentData();
        int asama = veri.getIntOr(CELIK_GOREV_ASAMASI, 0);
        if (asama < 4) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.scene.pillar_locked"));
            return true;
        }
        veri.putInt(SAHNE_GOREV_MASKESI, veri.getIntOr(SAHNE_GOREV_MASKESI, 0) | DONEM_OZEL_BITI);
        BlockState durum = oyuncu.level().getBlockState(konum);
        oyuncu.level().setBlock(konum, durum.setValue(ResonancePillarBlock.CHARGED, true), 3);
        oyuncu.level().scheduleTick(konum, ModBlocks.RESONANCE_PILLAR.get(), ResonancePillarBlock.ACTIVE_TICKS);
        oyuncu.sendSystemMessage(Component.translatable("message.pastbound.scene.pillar_active"));
        oyuncu.level().playSound(null, konum, net.minecraft.sounds.SoundEvents.AMETHYST_BLOCK_CHIME, net.minecraft.sounds.SoundSource.BLOCKS, 1.0F, 1.2F);
        return true;
    }

    public static boolean dunyaElektrikEtkilesildi(ServerPlayer oyuncu, BlockPos konum) {
        CompoundTag veri = ((IEntityExtension) oyuncu).getPersistentData();
        if (veri.getIntOr(DUNYA_GOREVI, 0) != 4 || !oyuncu.level().dimension().equals(Level.OVERWORLD) || !oyuncu.level().getBlockState(konum).is(ModBlocks.RESONANCE_PILLAR.get()) || oyuncu.distanceToSqr(konum.getX() + 0.5D, konum.getY() + 0.5D, konum.getZ() + 0.5D) > 25.0D) {
            if (veri.getIntOr(DUNYA_GOREVI, 0) == 4) {
                oyuncu.sendSystemMessage(Component.translatable("message.pastbound.world.power_find_pillar"));
            }
            return false;
        }
        if (oyuncu.getInventory().countItem(Items.COPPER_INGOT) < 2 || oyuncu.getInventory().countItem(Items.REDSTONE) < 4 || oyuncu.getInventory().countItem(ModItems.STEEL_PLATE.get()) < 1) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.world.power_needs"));
            return true;
        }
        oyuncu.getInventory().clearOrCountMatchingItems(yigin -> yigin.is(Items.COPPER_INGOT), 2, oyuncu.getInventory());
        oyuncu.getInventory().clearOrCountMatchingItems(yigin -> yigin.is(Items.REDSTONE), 4, oyuncu.getInventory());
        oyuncu.getInventory().clearOrCountMatchingItems(yigin -> yigin.is(ModItems.STEEL_PLATE.get()), 1, oyuncu.getInventory());
        veri.putInt(DUNYA_GOREVI, 5);
        BlockState durum = oyuncu.level().getBlockState(konum);
        oyuncu.level().setBlock(konum, durum.setValue(ResonancePillarBlock.CHARGED, true), 3);
        oyuncu.level().scheduleTick(konum, ModBlocks.RESONANCE_PILLAR.get(), ResonancePillarBlock.ACTIVE_TICKS);
        oyuncu.sendSystemMessage(Component.translatable("message.pastbound.world.power_active"));
        return true;
    }

    private static void hedefiMadenYap(ServerPlayer oyuncu) {
        CompoundTag veri = ((IEntityExtension) oyuncu).getPersistentData();
        BlockPos maden = new BlockPos((int) veri.getDoubleOr(DUNYA_MADEN_X, 0.0D), (int) veri.getDoubleOr(DUNYA_MADEN_Y, 64.0D), (int) veri.getDoubleOr(DUNYA_MADEN_Z, 0.0D));
        veri.putDouble(DUNYA_HEDEF_X, maden.getX());
        veri.putDouble(DUNYA_HEDEF_Y, maden.getY());
        veri.putDouble(DUNYA_HEDEF_Z, maden.getZ());
    }

    private static void koyYapisiKur(ServerLevel seviye, BlockPos merkez) {
        for (int x = -4; x <= 4; x++) {
            for (int z = -4; z <= 4; z++) {
                seviye.setBlock(merkez.offset(x, 0, z), Blocks.GRASS_BLOCK.defaultBlockState(), 3);
            }
        }
        for (int x = -3; x <= 3; x++) {
            seviye.setBlock(merkez.offset(x, 1, -3), Blocks.OAK_PLANKS.defaultBlockState(), 3);
            seviye.setBlock(merkez.offset(x, 1, 3), Blocks.OAK_PLANKS.defaultBlockState(), 3);
        }
        for (int z = -3; z <= 3; z++) {
            seviye.setBlock(merkez.offset(-3, 1, z), Blocks.OAK_PLANKS.defaultBlockState(), 3);
            seviye.setBlock(merkez.offset(3, 1, z), Blocks.OAK_PLANKS.defaultBlockState(), 3);
        }
        seviye.setBlock(merkez, Blocks.BELL.defaultBlockState(), 3);
        seviye.setBlock(merkez.east(2), Blocks.CRAFTING_TABLE.defaultBlockState(), 3);
        seviye.setBlock(merkez.west(2), Blocks.FURNACE.defaultBlockState(), 3);
    }

    private static void madenYapisiKur(ServerLevel seviye, BlockPos merkez) {
        for (int x = -3; x <= 3; x++) {
            for (int z = -3; z <= 3; z++) {
                seviye.setBlock(merkez.offset(x, -1, z), Blocks.DEEPSLATE.defaultBlockState(), 3);
            }
        }
        for (int i = -1; i <= 1; i++) {
            seviye.setBlock(merkez.offset(i, 0, 0), ModBlocks.STEEL_ORE.get().defaultBlockState(), 3);
            seviye.setBlock(merkez.offset(i, 0, 1), ModBlocks.STEEL_ORE.get().defaultBlockState(), 3);
        }
        seviye.setBlock(merkez.east(3), ModBlocks.RESONANCE_PILLAR.get().defaultBlockState(), 3);
        for (int y = 0; y <= 2; y++) {
            seviye.setBlock(merkez.offset(-3, y, -3), Blocks.DEEPSLATE_BRICKS.defaultBlockState(), 3);
            seviye.setBlock(merkez.offset(3, y, -3), Blocks.DEEPSLATE_BRICKS.defaultBlockState(), 3);
        }
    }

    public static boolean sahneKapisiMi(ServerPlayer oyuncu, BlockPos konum) {
        if (!boyuttaMi(oyuncu)) {
            return false;
        }
        BlockPos alt = konum;
        if (oyuncu.level().getBlockState(alt).getBlock() instanceof DoorBlock && oyuncu.level().getBlockState(alt).getValue(DoorBlock.HALF) == DoubleBlockHalf.UPPER) {
            alt = konum.below();
        }
        return alt.equals(SAHNE_MERKEZI.north(8).above()) && oyuncu.level().getBlockState(alt).getBlock() instanceof DoorBlock;
    }

    public static void sahneKapisiEtkilesildi(ServerPlayer oyuncu, BlockPos konum) {
        if (!sahneKapisiMi(oyuncu, konum)) {
            return;
        }
        BlockPos alt = konum;
        BlockState durum = oyuncu.level().getBlockState(alt);
        if (durum.getValue(DoorBlock.HALF) == DoubleBlockHalf.UPPER) {
            alt = konum.below();
            durum = oyuncu.level().getBlockState(alt);
        }
        boolean acik = !durum.getValue(DoorBlock.OPEN);
        ((DoorBlock) durum.getBlock()).setOpen(oyuncu, oyuncu.level(), durum, alt, acik);
        oyuncu.sendSystemMessage(Component.translatable(acik ? "message.pastbound.scene.door_opened" : "message.pastbound.scene.door_closed"));
    }

    public static boolean tarihForgeMi(ServerPlayer oyuncu, BlockPos konum) {
        return boyuttaMi(oyuncu) && oyuncu.level().getBlockState(konum).is(ModBlocks.HISTORICAL_FORGE.get());
    }

    public static int celikGorevAsamasi(ServerPlayer oyuncu) {
        return ((IEntityExtension) oyuncu).getPersistentData().getIntOr(CELIK_GOREV_ASAMASI, 0);
    }

    private static Block gorevAniti(TarihDonemi donem) {
        return switch (donem) {
            case URUK_YAZI_EVI -> Blocks.CLAY;
            case TERMOPIL_SAVASI -> Blocks.STONE;
            case ISKENDERIYE_KUTUPHANESI -> Blocks.BOOKSHELF;
            case BAGDAT_PILI_ATOLYESI -> Blocks.COPPER_BLOCK.weathering().unaffected();
            case ANTIKITHERA_LIMANI -> Blocks.POLISHED_DEEPSLATE;
            case BAGDAT_BILGI_EVI -> Blocks.CHISELED_BOOKSHELF;
            case TIMBUKTU_EL_YAZMALARI -> Blocks.SANDSTONE;
            case TENOKTITLAN_GECIDI -> Blocks.PRISMARINE;
            case POLINEZYA_YILDIZ_YOLU -> Blocks.LAPIS_BLOCK;
            case CATALHOYUK_YERLESKESI -> Blocks.TERRACOTTA;
            case APOLLO_AY_ISTIGI -> Blocks.IRON_BLOCK;
            case IPEK_YOLU_KERVANSARAYI -> Blocks.RED_SANDSTONE;
            case EPIDAURUM_TİYATROSU -> Blocks.CALCITE;
        };
    }

    public static void gorevVarliklariniKur(ServerLevel seviye, BlockPos merkez, TarihDonemi donem) {
        seviye.setBlock(merkez.north(6), gorevAniti(donem).defaultBlockState(), 3);
        Block[] durakAletleri = {Blocks.CRAFTING_TABLE, Blocks.FURNACE, Blocks.CARTOGRAPHY_TABLE};
        for (int i = 0; i < GOREV_DURAKLARI.length; i++) {
            BlockPos durak = GOREV_DURAKLARI[i];
            seviye.setBlock(durak, durakAletleri[i].defaultBlockState(), 3);
            seviye.setBlock(durak.above(), Blocks.AMETHYST_BLOCK.defaultBlockState(), 3);
        }
        tarihLokasyonlariniKur(seviye, merkez, donem);
        if (donem != TarihDonemi.BAGDAT_PILI_ATOLYESI) {
            return;
        }
        seviye.setBlock(merkez.north(5).west(3), ModBlocks.STEEL_ORE.get().defaultBlockState(), 3);
        seviye.setBlock(merkez.north(5), ModBlocks.STEEL_ORE.get().defaultBlockState(), 3);
        seviye.setBlock(merkez.north(5).east(3), ModBlocks.STEEL_ORE.get().defaultBlockState(), 3);
        seviye.setBlock(merkez.south(5), ModBlocks.HISTORICAL_FORGE.get().defaultBlockState(), 3);
        seviye.setBlock(merkez.east(5), Blocks.FURNACE.defaultBlockState(), 3);
        seviye.setBlock(merkez.west(5), Blocks.CRAFTING_TABLE.defaultBlockState(), 3);
    }

    private static void tarihLokasyonlariniKur(ServerLevel seviye, BlockPos merkez, TarihDonemi donem) {
        BlockPos koy = merkez.west(28).north(18);
        BlockPos maden = merkez.east(28).north(18);
        BlockPos atölye = merkez.south(28);
        lokasyonZeminiKur(seviye, koy, 7, Blocks.DIRT_PATH);
        lokasyonZeminiKur(seviye, maden, 7, Blocks.DEEPSLATE);
        lokasyonZeminiKur(seviye, atölye, 6, Blocks.COBBLED_DEEPSLATE);
        koyEviKur(seviye, koy, donem);
        madenGirisiKur(seviye, maden);
        elektrikAtolyesiKur(seviye, atölye, donem);
        gorevIsaretiKur(seviye, koy, Blocks.BELL);
        gorevIsaretiKur(seviye, maden, Blocks.LANTERN);
        gorevIsaretiKur(seviye, atölye, Blocks.REDSTONE_TORCH);
    }

    private static void lokasyonZeminiKur(ServerLevel seviye, BlockPos merkez, int yaricap, Block zemin) {
        for (int x = -yaricap; x <= yaricap; x++) {
            for (int z = -yaricap; z <= yaricap; z++) {
                seviye.setBlock(merkez.offset(x, 0, z), zemin.defaultBlockState(), 3);
            }
        }
        for (int x = -yaricap; x <= yaricap; x++) {
            for (int z = -yaricap; z <= yaricap; z++) {
                seviye.setBlock(merkez.offset(x, -2, z), Blocks.DEEPSLATE.defaultBlockState(), 3);
            }
        }
    }

    private static void koyEviKur(ServerLevel seviye, BlockPos merkez, TarihDonemi donem) {
        Block duvar = ahsapPlanki(donem);
        Block tema = koyTemaBlogu(donem);
        int taban = -2;
        for (int x = -3; x <= 3; x++) {
            for (int z = -3; z <= 3; z++) {
                seviye.setBlock(merkez.offset(x, taban, z), Blocks.DEEPSLATE.defaultBlockState(), 3);
            }
        }
        for (int y = taban + 1; y <= taban + 3; y++) {
            for (int x = -3; x <= 3; x++) {
                if (x < -2 || x > 2) {
                    seviye.setBlock(merkez.offset(x, y, -3), duvar.defaultBlockState(), 3);
                }
            }
            for (int z = -3; z <= 3; z++) {
                if (z < -1 || z > 2) {
                    seviye.setBlock(merkez.offset(-3, y, z), duvar.defaultBlockState(), 3);
                    seviye.setBlock(merkez.offset(3, y, z), duvar.defaultBlockState(), 3);
                }
            }
        }
        for (int x = -3; x <= 3; x++) {
            for (int z = -3; z <= 3; z++) {
                if (Math.abs(x) + Math.abs(z) <= 5) {
                    seviye.setBlock(merkez.offset(x, taban + 4, z), duvar.defaultBlockState(), 3);
                }
            }
        }
        for (int x = -2; x <= 2; x++) {
            for (int y = taban + 1; y <= taban + 2; y++) {
                seviye.setBlock(merkez.offset(x, y, 3), Blocks.AIR.defaultBlockState(), 3);
            }
        }
        seviye.setBlock(merkez, tema.defaultBlockState(), 3);
        seviye.setBlock(merkez.east(), Blocks.LECTERN.defaultBlockState(), 3);
        seviye.setBlock(merkez.west(), Blocks.BELL.defaultBlockState(), 3);
        seviye.setBlock(merkez.north(), koyMeslekBlogu(donem).defaultBlockState(), 3);
        villagerKur(seviye, merkez.east(2), koyUzmaniAdi(donem));
    }

    private static void villagerKur(ServerLevel seviye, BlockPos konum, String ad) {
        Entity varlik = BuiltInRegistries.ENTITY_TYPE.getValue(Identifier.parse("minecraft:villager")).create(seviye, EntitySpawnReason.COMMAND);
        if (varlik instanceof Villager koylu) {
            koylu.setPos(konum.getX() + 0.5D, konum.getY() + 1.0D, konum.getZ() + 0.5D);
            koylu.setInvulnerable(true);
            koylu.setCustomName(Component.translatable(ad));
            koylu.setCustomNameVisible(true);
            koylu.addTag("pastbound_saha_uzmani");
            koylu.addTag("pastbound_dialogue_3");
            seviye.addFreshEntity(koylu);
        }
    }

    private static void madenGirisiKur(ServerLevel seviye, BlockPos merkez) {
        for (int y = 1; y <= 4; y++) {
            for (int x = -3; x <= 3; x++) {
                seviye.setBlock(merkez.offset(x, y, -2), Blocks.DEEPSLATE_BRICKS.defaultBlockState(), 3);
            }
        }
        BlockPos kapi = merkez.north(2);
        seviye.setBlock(kapi, Blocks.AIR.defaultBlockState(), 3);
        seviye.setBlock(kapi.above(), Blocks.AIR.defaultBlockState(), 3);
        seviye.setBlock(kapi, Blocks.AIR.defaultBlockState(), 3);
        seviye.setBlock(kapi.above(), Blocks.AIR.defaultBlockState(), 3);
        for (int i = -2; i <= 2; i++) {
            seviye.setBlock(merkez.offset(i, 1, 0), ModBlocks.STEEL_ORE.get().defaultBlockState(), 3);
            seviye.setBlock(merkez.offset(i, 2, 0), ModBlocks.STEEL_ORE.get().defaultBlockState(), 3);
        }
        seviye.setBlock(merkez.south(2), ModBlocks.HISTORICAL_FORGE.get().defaultBlockState(), 3);
        seviye.setBlock(merkez.south(3), Blocks.FURNACE.defaultBlockState(), 3);
    }

    private static void elektrikAtolyesiKur(ServerLevel seviye, BlockPos merkez, TarihDonemi donem) {
        Block duvar = Blocks.COPPER_BLOCK.weathering().unaffected();
        for (int y = 1; y <= 3; y++) {
            for (int x = -4; x <= 4; x++) {
                seviye.setBlock(merkez.offset(x, y, -3), duvar.defaultBlockState(), 3);
                seviye.setBlock(merkez.offset(x, y, 3), duvar.defaultBlockState(), 3);
            }
            for (int z = -2; z <= 2; z++) {
                seviye.setBlock(merkez.offset(-4, y, z), duvar.defaultBlockState(), 3);
                seviye.setBlock(merkez.offset(4, y, z), duvar.defaultBlockState(), 3);
            }
        }
        for (int x = -4; x <= 4; x++) {
            for (int z = -3; z <= 3; z++) {
                if (Math.abs(x) + Math.abs(z) <= 6) {
                    seviye.setBlock(merkez.offset(x, 4, z), duvar.defaultBlockState(), 3);
                }
            }
        }
        BlockPos kapi = merkez.south(3);
        seviye.setBlock(kapi, Blocks.AIR.defaultBlockState(), 3);
        seviye.setBlock(kapi.above(), Blocks.AIR.defaultBlockState(), 3);
        seviye.setBlock(kapi, Blocks.AIR.defaultBlockState(), 3);
        seviye.setBlock(kapi.above(), Blocks.AIR.defaultBlockState(), 3);
        seviye.setBlock(merkez, ModBlocks.RESONANCE_PILLAR.get().defaultBlockState(), 3);
        seviye.setBlock(merkez.east(2), Blocks.REDSTONE_BLOCK.defaultBlockState(), 3);
        seviye.setBlock(merkez.west(2), Blocks.COPPER_BLOCK.weathering().unaffected().defaultBlockState(), 3);
        seviye.setBlock(merkez.north(2), Blocks.CRAFTING_TABLE.defaultBlockState(), 3);
        villagerKur(seviye, merkez.south(2), "entity.pastbound.power.engineer");
    }

    private static void gorevIsaretiKur(ServerLevel seviye, BlockPos merkez, Block blok) {
        seviye.setBlock(merkez.above(), blok.defaultBlockState(), 3);
        seviye.setBlock(merkez.above(2), Blocks.AMETHYST_BLOCK.defaultBlockState(), 3);
    }

    public static void konusmaCevapla(ServerPlayer oyuncu, String donemKimligi, int konusmaci, int secim) {
        if (!boyuttaMi(oyuncu) || konusmaci < 0 || konusmaci > 3 || secim < 1 || secim > 3) {
            return;
        }
        TarihDonemi donem = donemBul(donemKimligi);
        if (donem == null) {
            return;
        }
        CompoundTag veri = ((IEntityExtension) oyuncu).getPersistentData();
        boolean yakin = false;
        for (Entity varlik : oyuncu.level().getEntitiesOfClass(Entity.class, oyuncu.getBoundingBox().inflate(5.0D))) {
            if (varlik.entityTags().contains("pastbound_sahne_" + konusmaci) || varlik.entityTags().contains("pastbound_dialogue_" + konusmaci)) {
                yakin = true;
                break;
            }
        }
        if (!yakin) {
            long simdi = oyuncu.level().getGameTime();
            long sonMesaj = veri.getLongOr("pastbound_dialogue_hint_tick", -1000L);
            if (simdi - sonMesaj >= 60L) {
                veri.putLong("pastbound_dialogue_hint_tick", simdi);
                oyuncu.sendSystemMessage(Component.translatable("message.pastbound.dialogue.too_far"));
            }
            PacketDistributor.sendToPlayer(oyuncu, PastboundPaketi.konusmaCevabi(donem.kimlik(), konusmaci, 0));
            return;
        }
        oyuncu.sendSystemMessage(Component.translatable("history.pastbound.period." + donem.kimlik() + ".response_" + secim));
        PacketDistributor.sendToPlayer(oyuncu, PastboundPaketi.konusmaCevabi(donem.kimlik(), konusmaci, secim));
        int gorevMaskesi = veri.getIntOr(SAHNE_GOREV_MASKESI, 0) | (1 << konusmaci);
        veri.putInt(SAHNE_GOREV_MASKESI, gorevMaskesi);
        oyuncu.sendSystemMessage(Component.translatable("message.pastbound.scene.quest_talk", konusmaci + 1));
        goreviKontrolEt(oyuncu);
        oyuncu.level().playSound(null, oyuncu.blockPosition(), net.minecraft.sounds.SoundEvents.VILLAGER_TRADE, net.minecraft.sounds.SoundSource.NEUTRAL, 0.8F, 1.0F + secim * 0.08F);
    }

    public static void kesifBasarisiz(ServerPlayer oyuncu) {
        if (!boyuttaMi(oyuncu)) {
            return;
        }
        CompoundTag veri = ((IEntityExtension) oyuncu).getPersistentData();
        TarihDonemi donem = donemBul(veri.getStringOr(SAHNE_CAGI, ""));
        if (donem != null && oyuncu.level() instanceof ServerLevel seviye) {
            sahneyiKur(seviye, SAHNE_MERKEZI, donem);
        }
        veri.putInt(BASARISIZ_KESIFLER, veri.getIntOr(BASARISIZ_KESIFLER, 0) + 1);
        oyuncu.sendSystemMessage(Component.translatable("message.pastbound.expedition.reset"));
        don(oyuncu);
    }

    public static int tamamlananDunyalar(ServerPlayer oyuncu) {
        return ((IEntityExtension) oyuncu).getPersistentData().getIntOr(TAMAMLANAN_DUNYALAR, 0);
    }

    private static void tamamlanmaOdulu(ServerPlayer oyuncu, TarihDonemi donem) {
        CompoundTag veri = ((IEntityExtension) oyuncu).getPersistentData();
        int tamamlanan = veri.getIntOr(TAMAMLANAN_DUNYALAR, 0) + 1;
        veri.putInt(TAMAMLANAN_DUNYALAR, tamamlanan);
        oyuncu.getInventory().placeItemBackInInventory(new ItemStack(ModItems.CHRONICLE_SCRAP.get(), 4));
        oyuncu.getInventory().placeItemBackInInventory(new ItemStack(ModItems.TIME_STONE.get(), 1));
        oyuncu.giveExperiencePoints(12 + tamamlanan * 2);
        if (tamamlanan % 3 == 0) {
            oyuncu.getInventory().placeItemBackInInventory(new ItemStack(ModItems.ECHO_SEAL.get(), 1));
        }
        if (tamamlanan == 5) {
            oyuncu.getInventory().placeItemBackInInventory(new ItemStack(ModItems.ERVANIUM_SMITHING_TEMPLATE.get(), 2));
            oyuncu.sendSystemMessage(Component.literal("Milestone reward: 2 Ervanium Smithing Templates."));
        }
        if (tamamlanan == TarihDonemi.values().length) {
            oyuncu.getInventory().placeItemBackInInventory(new ItemStack(ModItems.CHRONICLE_COMPASS.get(), 1));
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.expedition.master_complete"));
        }
        oyuncu.sendSystemMessage(Component.translatable("message.pastbound.expedition.reward", donem.adBileseni(), tamamlanan, TarihDonemi.values().length));
    }

    public static void don(ServerPlayer oyuncu) {
        if (!boyuttaMi(oyuncu)) {
            return;
        }
        CompoundTag veri = ((IEntityExtension) oyuncu).getPersistentData();
        TarihDonemi ayrilanDonem = donemBul(veri.getStringOr(SAHNE_CAGI, ""));
        if (ayrilanDonem != null && oyuncu.level() instanceof ServerLevel ayrilanSeviye) {
            sahneyiKur(ayrilanSeviye, SAHNE_MERKEZI, ayrilanDonem);
        }
        ServerLevel hedef = oyuncu.level().getServer().overworld();
        String boyutKimligi = veri.getStringOr(DONUS_BOYUTU, "");
        if (!boyutKimligi.isEmpty()) {
            try {
                ResourceKey<Level> boyut = ResourceKey.create(Registries.DIMENSION, Identifier.parse(boyutKimligi));
                ServerLevel kayitliSeviye = oyuncu.level().getServer().getLevel(boyut);
                if (kayitliSeviye != null) {
                    hedef = kayitliSeviye;
                }
            } catch (RuntimeException hata) {
                hedef = oyuncu.level().getServer().overworld();
            }
        }
        oyuncu.teleportTo(hedef, veri.getDoubleOr(DONUS_X, 0.5D), veri.getDoubleOr(DONUS_Y, 65.0D), veri.getDoubleOr(DONUS_Z, 0.5D), Set.of(), veri.getFloatOr(DONUS_YAW, 0.0F), veri.getFloatOr(DONUS_PITCH, 0.0F), false);
        veri.remove(DONUS_BOYUTU);
        veri.remove(DONUS_X);
        veri.remove(DONUS_Y);
        veri.remove(DONUS_Z);
        veri.remove(DONUS_YAW);
        veri.remove(DONUS_PITCH);
        veri.remove(SAHNE_CAGI);
        veri.remove(SAHNE_AKTIF);
        veri.remove(SAHNE_SAYACI);
        veri.remove(SAHNE_GOREV_MASKESI);
        veri.remove(CELIK_GOREV_ASAMASI);
        veri.remove(CELIK_DAMAR_SAYISI);
        veri.remove(YURUME_MESAFESI);
        veri.remove(IZLEME_X);
        veri.remove(IZLEME_Y);
        veri.remove(IZLEME_Z);
        veri.remove("pastbound_donem_ozel_sayac");
        oyuncu.sendSystemMessage(Component.translatable("message.pastbound.scene.returned"));
        if (hedef.dimension().equals(Level.OVERWORLD)) {
            dunyaGoreviniBaslat(oyuncu);
        }
    }

    public static void kontrolSonrasiTik(ServerPlayer oyuncu) {
        if (!boyuttaMi(oyuncu)) {
            return;
        }
        gercekYurumeyiIzle(oyuncu);
        if (oyuncu.tickCount % 5 == 0 && oyuncu.level() instanceof ServerLevel seviye) {
            rotaAmetistleriniGoster(oyuncu, seviye);
        }
        if (oyuncu.tickCount % 10 != 0) {
            return;
        }
        CompoundTag veri = ((IEntityExtension) oyuncu).getPersistentData();
        int gorevMaskesi = veri.getIntOr(SAHNE_GOREV_MASKESI, 0);
        String[] durakMesajlari = {
                "message.pastbound.scene.quest_perimeter",
                "message.pastbound.scene.quest_waypoint_b",
                "message.pastbound.scene.quest_return"
        };
        int[] durakBitleri = {SAHNE_DURAK_A_BITI, SAHNE_DURAK_B_BITI, SAHNE_DURAK_C_BITI};
        int[] konusmaBitleri = {SAHNE_DURAK_A_KONUSMA_BITI, SAHNE_DURAK_B_KONUSMA_BITI, SAHNE_DURAK_C_KONUSMA_BITI};
        for (int i = 0; i < GOREV_DURAKLARI.length; i++) {
            if ((gorevMaskesi & durakBitleri[i]) != 0) {
                continue;
            }
            BlockPos durak = GOREV_DURAKLARI[i];
            if (oyuncu.distanceToSqr(durak.getX() + 0.5D, durak.getY() + 1.0D, durak.getZ() + 0.5D) <= 6.25D) {
                gorevMaskesi |= durakBitleri[i];
                veri.putInt(SAHNE_GOREV_MASKESI, gorevMaskesi);
                oyuncu.sendSystemMessage(Component.translatable(durakMesajlari[i]));
                oyuncu.sendSystemMessage(Component.translatable("message.pastbound.scene.waypoint_need_talk"));
            } else if (oyuncu.level() instanceof ServerLevel seviye) {
                seviye.sendParticles(ParticleTypes.END_ROD, durak.getX() + 0.5D, durak.getY() + 1.2D, durak.getZ() + 0.5D, 2, 0.2D, 0.4D, 0.2D, 0.01D);
            }
        }
        double yuruneMesafe = veri.getDoubleOr(YURUME_MESAFESI, 0.0D);
        if (yuruneMesafe >= YURUME_HEDEFI_BLOK && (gorevMaskesi & 1024) == 0) {
            gorevMaskesi |= 1024;
            veri.putInt(SAHNE_GOREV_MASKESI, gorevMaskesi);
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.scene.quest_distance"));
        } else if (yuruneMesafe < YURUME_HEDEFI_BLOK && oyuncu.tickCount % 100 == 0) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.scene.quest_distance_progress", Math.round(yuruneMesafe), Math.round(YURUME_HEDEFI_BLOK)));
        }
                uzakLokasyonlariIzle(oyuncu);
        goreviKontrolEt(oyuncu);
    }

    private static void rotaAmetistleriniGoster(ServerPlayer oyuncu, ServerLevel seviye) {
        CompoundTag veri = ((IEntityExtension) oyuncu).getPersistentData();
        BlockPos eski = geciciAmetistKonumu(veri);
        BlockPos alt = oyuncu.blockPosition().below();
        if (!oyuncu.onGround() || !seviye.getBlockState(alt).is(Blocks.BARRIER)) {
            geciciAmetistiTemizle(oyuncu, veri, seviye);
            return;
        }
        if (eski != null && !eski.equals(alt)) {
            geciciAmetistiTemizle(oyuncu, veri, seviye);
            eski = null;
        }
        long simdi = seviye.getGameTime();
        long bitis = veri.getLongOr(AMETIST_TICK, 0L);
        boolean yeniKare = eski == null;
        if (eski == null || simdi >= bitis) {
            oyuncu.connection.send(new ClientboundBlockUpdatePacket(alt, Blocks.AMETHYST_BLOCK.defaultBlockState()));
            if (yeniKare) {
                seviye.playSound(oyuncu, alt, net.minecraft.sounds.SoundEvents.AMETHYST_BLOCK_BREAK, net.minecraft.sounds.SoundSource.BLOCKS, 0.45F, 1.0F);
            }
            veri.putInt(AMETIST_X, alt.getX());
            veri.putInt(AMETIST_Y, alt.getY());
            veri.putInt(AMETIST_Z, alt.getZ());
        }
        veri.putLong(AMETIST_TICK, simdi + 15L);
    }

    private static BlockPos geciciAmetistKonumu(CompoundTag veri) {
        if (!veri.contains(AMETIST_X) || !veri.contains(AMETIST_Y) || !veri.contains(AMETIST_Z)) {
            return null;
        }
        return new BlockPos(veri.getIntOr(AMETIST_X, 0), veri.getIntOr(AMETIST_Y, 0), veri.getIntOr(AMETIST_Z, 0));
    }

    private static void geciciAmetistiTemizle(ServerPlayer oyuncu, CompoundTag veri, ServerLevel seviye) {
        BlockPos eski = geciciAmetistKonumu(veri);
        if (eski != null) {
            oyuncu.connection.send(new ClientboundBlockUpdatePacket(eski, seviye.getBlockState(eski)));
        }
        veri.remove(AMETIST_X);
        veri.remove(AMETIST_Y);
        veri.remove(AMETIST_Z);
        veri.remove(AMETIST_TICK);
    }

    private static void uzakLokasyonlariIzle(ServerPlayer oyuncu) {
        CompoundTag veri = ((IEntityExtension) oyuncu).getPersistentData();
        int maske = veri.getIntOr(SAHNE_GOREV_MASKESI, 0);
        BlockPos koy = SAHNE_MERKEZI.west(28).north(18);
        BlockPos maden = SAHNE_MERKEZI.east(28).north(18);
        BlockPos atolye = SAHNE_MERKEZI.south(28);
        if ((maske & SAHNE_KOY_BITI) == 0 && oyuncu.distanceToSqr(koy.getX() + 0.5D, koy.getY() + 1.0D, koy.getZ() + 0.5D) <= 100.0D) {
            maske |= SAHNE_KOY_BITI;
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.world.village_reached"));
        }
        if ((maske & SAHNE_MADEN_BITI) == 0 && oyuncu.distanceToSqr(maden.getX() + 0.5D, maden.getY() + 1.0D, maden.getZ() + 0.5D) <= 100.0D) {
            maske |= SAHNE_MADEN_BITI;
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.world.mine_reached"));
        }
        if ((maske & SAHNE_ATOLYE_BITI) == 0 && oyuncu.distanceToSqr(atolye.getX() + 0.5D, atolye.getY() + 1.0D, atolye.getZ() + 0.5D) <= 100.0D) {
            maske |= SAHNE_ATOLYE_BITI;
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.world.build_power"));
        }
        veri.putInt(SAHNE_GOREV_MASKESI, maske);
    }

    public static boolean uzakAtolyeEtkilesildi(ServerPlayer oyuncu, BlockPos konum) {
        if (!boyuttaMi(oyuncu)) {
            return false;
        }
        BlockPos atolye = SAHNE_MERKEZI.south(28);
        if (!konum.closerThan(atolye, 4.0D)) {
            return false;
        }
        BlockState durum = oyuncu.level().getBlockState(konum);
        if (!durum.is(Blocks.FURNACE) && !durum.is(Blocks.CRAFTING_TABLE) && !durum.is(ModBlocks.HISTORICAL_FORGE.get())) {
            return false;
        }
        CompoundTag veri = ((IEntityExtension) oyuncu).getPersistentData();
        veri.putInt(SAHNE_GOREV_MASKESI, veri.getIntOr(SAHNE_GOREV_MASKESI, 0) | SAHNE_ATOLYE_BITI);
        oyuncu.sendSystemMessage(Component.translatable("message.pastbound.world.power_active"));
        goreviKontrolEt(oyuncu);
        return true;
    }

    private static void gercekYurumeyiIzle(ServerPlayer oyuncu) {
        CompoundTag veri = ((IEntityExtension) oyuncu).getPersistentData();
        double x = oyuncu.getX();
        double y = oyuncu.getY();
        double z = oyuncu.getZ();
        if (!veri.contains(IZLEME_X)) {
            veri.putDouble(IZLEME_X, x);
            veri.putDouble(IZLEME_Y, y);
            veri.putDouble(IZLEME_Z, z);
            return;
        }
        double eskiX = veri.getDoubleOr(IZLEME_X, x);
        double eskiY = veri.getDoubleOr(IZLEME_Y, y);
        double eskiZ = veri.getDoubleOr(IZLEME_Z, z);
        double dx = x - eskiX;
        double dy = y - eskiY;
        double dz = z - eskiZ;
        double adim = Math.sqrt(dx * dx + dy * dy + dz * dz);
        
        if (adim > 0.005D && adim < 1.2D) {
            double toplam = veri.getDoubleOr(YURUME_MESAFESI, 0.0D) + adim;
            veri.putDouble(YURUME_MESAFESI, toplam);
        }
        veri.putDouble(IZLEME_X, x);
        veri.putDouble(IZLEME_Y, y);
        veri.putDouble(IZLEME_Z, z);
    }

    public static void donemOzelEylem(ServerPlayer oyuncu, BlockPos konum, ItemStack yigin) {
        if (!boyuttaMi(oyuncu)) {
            return;
        }
        CompoundTag veri = ((IEntityExtension) oyuncu).getPersistentData();
        TarihDonemi donem = donemBul(veri.getStringOr(SAHNE_CAGI, ""));
        if (donem == null || (veri.getIntOr(SAHNE_GOREV_MASKESI, 0) & DONEM_OZEL_BITI) != 0) {
            return;
        }
        BlockState durum = oyuncu.level().getBlockState(konum);
        boolean eylem = switch (donem) {
            case URUK_YAZI_EVI -> durum.is(Blocks.CLAY);
            case TERMOPIL_SAVASI -> yigin.is(Items.SHIELD);
            case ISKENDERIYE_KUTUPHANESI -> durum.is(Blocks.BOOKSHELF);
            case BAGDAT_PILI_ATOLYESI -> celikGorevAsamasi(oyuncu) >= 4;
            case ANTIKITHERA_LIMANI -> yigin.is(Items.CLOCK);
            case BAGDAT_BILGI_EVI -> yigin.is(Items.WRITABLE_BOOK);
            case TIMBUKTU_EL_YAZMALARI -> yigin.is(Items.PAPER);
            case TENOKTITLAN_GECIDI -> yigin.is(Items.WATER_BUCKET) || oyuncu.isInWater();
            case POLINEZYA_YILDIZ_YOLU -> yigin.is(Items.OAK_BOAT) || yigin.is(Items.BAMBOO_RAFT);
            case CATALHOYUK_YERLESKESI -> durum.is(Blocks.TERRACOTTA);
            case APOLLO_AY_ISTIGI -> oyuncu.getY() >= 78.0D;
            case IPEK_YOLU_KERVANSARAYI -> yigin.is(Items.EMERALD);
            case EPIDAURUM_TİYATROSU -> durum.is(Blocks.CALCITE);
        };
        if (eylem) {
            int hedef = switch (donem) {
                case URUK_YAZI_EVI, ISKENDERIYE_KUTUPHANESI, CATALHOYUK_YERLESKESI, EPIDAURUM_TİYATROSU -> 3;
                case TERMOPIL_SAVASI, ANTIKITHERA_LIMANI, BAGDAT_BILGI_EVI, TIMBUKTU_EL_YAZMALARI, IPEK_YOLU_KERVANSARAYI -> 2;
                default -> 1;
            };
            int sayac = veri.getIntOr("pastbound_donem_ozel_sayac", 0) + 1;
            veri.putInt("pastbound_donem_ozel_sayac", sayac);
            if (sayac < hedef) {
                oyuncu.sendSystemMessage(Component.translatable("message.pastbound.period.unique_progress", sayac, hedef));
                return;
            }
            veri.putInt(SAHNE_GOREV_MASKESI, veri.getIntOr(SAHNE_GOREV_MASKESI, 0) | DONEM_OZEL_BITI);
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.period.unique_complete", donem.adBileseni()));
            oyuncu.giveExperiencePoints(2);
            goreviKontrolEt(oyuncu);
        }
    }

    private static void goreviKontrolEt(ServerPlayer oyuncu) {
        CompoundTag veri = ((IEntityExtension) oyuncu).getPersistentData();
        int gorevMaskesi = veri.getIntOr(SAHNE_GOREV_MASKESI, 0);
        TarihDonemi donem = donemBul(((IEntityExtension) oyuncu).getPersistentData().getStringOr(SAHNE_CAGI, ""));
        if (donem == TarihDonemi.BAGDAT_PILI_ATOLYESI && celikGorevAsamasi(oyuncu) < 4) {
            return;
        }
        boolean konusmaTamam = (gorevMaskesi & 15) == 15;
        boolean duraklarTamam = (gorevMaskesi & SAHNE_DURAKLAR_MASKESI) == SAHNE_DURAKLAR_MASKESI;
        boolean durakKonusmalariTamam = (gorevMaskesi & SAHNE_DURAK_KONUSMA_MASKESI) == SAHNE_DURAK_KONUSMA_MASKESI;
        boolean durakCihazlariTamam = (gorevMaskesi & SAHNE_DURAK_CIHAZ_MASKESI) == SAHNE_DURAK_CIHAZ_MASKESI;
        boolean mesafeTamam = (gorevMaskesi & 1024) != 0;
        boolean uzakLokasyonlarTamam = (gorevMaskesi & SAHNE_UZAK_LOKASYON_MASKESI) == SAHNE_UZAK_LOKASYON_MASKESI;
        boolean hareketTamam = duraklarTamam && durakKonusmalariTamam && durakCihazlariTamam && mesafeTamam && uzakLokasyonlarTamam;
        boolean incelemeTamam = (gorevMaskesi & SAHNE_INCELEME_BITI) != 0;
        boolean anitTamam = (gorevMaskesi & SAHNE_ANIT_BITI) != 0;
        boolean donemOzelTamam = (gorevMaskesi & DONEM_OZEL_BITI) != 0 || (donem == TarihDonemi.BAGDAT_PILI_ATOLYESI && celikGorevAsamasi(oyuncu) >= 4);
        if (konusmaTamam && hareketTamam && incelemeTamam && anitTamam && donemOzelTamam && (gorevMaskesi & 64) == 0) {
            veri.putInt(SAHNE_GOREV_MASKESI, gorevMaskesi | 64);
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.scene.quest_complete"));
            tamamlanmaOdulu(oyuncu, donem);
            oyuncu.level().playSound(null, oyuncu.blockPosition(), net.minecraft.sounds.SoundEvents.PLAYER_LEVELUP, net.minecraft.sounds.SoundSource.PLAYERS, 0.8F, 1.15F);
        }
    }

    private static TarihDonemi donemBul(String kimlik) {
        for (TarihDonemi donem : TarihDonemi.values()) {
            if (donem.kimlik().equalsIgnoreCase(kimlik)) {
                return donem;
            }
        }
        return null;
    }

    private static TarihDonemi donemBulBoyuttan(ResourceKey<Level> kimlik) {
        if (kimlik == null || !kimlik.identifier().getNamespace().equals(ModId.MOD_ID)) {
            return null;
        }
        String yol = kimlik.identifier().getPath();
        for (TarihDonemi donem : TarihDonemi.values()) {
            if (("tarih_" + donem.kimlik()).equals(yol)) {
                return donem;
            }
        }
        return null;
    }

    private static void sahneyiKur(ServerLevel seviye, BlockPos merkez, TarihDonemi donem) {
        if (seviye.dimension().equals(Level.OVERWORLD)) {
            return;
        }
        modNPCleriTemizle(seviye, merkez);
        Block dekor = dekorBlogu(donem);
        BlockState zemin = zeminBlogu(donem).defaultBlockState();
        BlockState kenar = dekor.defaultBlockState();
        for (int x = -9; x <= 9; x++) {
            for (int z = -9; z <= 9; z++) {
                BlockPos konum = merkez.offset(x, 0, z);
                seviye.setBlock(konum, zemin, 3);
                if (Math.abs(x) == 9 || Math.abs(z) == 9) {
                    seviye.setBlock(konum.above(), kenar, 3);
                }
            }
        }
        sahneBariyerleriniKur(seviye, merkez);
        if (chinampaDonemiMi(donem)) {
            for (int x = -7; x <= 7; x++) {
                seviye.setBlock(merkez.offset(x, 0, -6), Blocks.WATER.defaultBlockState(), 3);
                seviye.setBlock(merkez.offset(x, 0, 6), Blocks.WATER.defaultBlockState(), 3);
            }
        } else {
            for (int x = -7; x <= 7; x++) {
                seviye.setBlock(merkez.offset(x, 0, -6), Blocks.CLAY.defaultBlockState(), 3);
                seviye.setBlock(merkez.offset(x, 0, 6), Blocks.CLAY.defaultBlockState(), 3);
            }
        }
        seviye.setBlock(merkez, dekor.defaultBlockState(), 3);
        seviye.setBlock(merkez.above(), Blocks.BEACON.defaultBlockState(), 3);
        seviye.setBlock(merkez.north(3), dekor.defaultBlockState(), 3);
        seviye.setBlock(merkez.south(3), dekor.defaultBlockState(), 3);
        seviye.setBlock(merkez.east(3), dekor.defaultBlockState(), 3);
        seviye.setBlock(merkez.west(3), dekor.defaultBlockState(), 3);
        tarihiYapiKur(seviye, merkez, donem);
        sahneAcikGecidiniKur(seviye, merkez);
        sahneKapisiKur(seviye, merkez, donem);
        gorevVarliklariniKur(seviye, merkez, donem);
        sahneAktorleriniKur(seviye, merkez, donem);
    }


    private static void sahneAcikGecidiniKur(ServerLevel seviye, BlockPos merkez) {
        for (int x = -2; x <= 1; x++) {
            for (int y = 0; y <= 3; y++) {
                seviye.setBlock(merkez.offset(x, y, -8), Blocks.AIR.defaultBlockState(), 3);
                seviye.setBlock(merkez.offset(x, y, -9), Blocks.AIR.defaultBlockState(), 3);
            }
        }
    }

    private static void sahneBariyerleriniKur(ServerLevel seviye, BlockPos merkez) {
        for (int y = 0; y <= 4; y++) {
            for (int i = -42; i <= 42; i++) {
                seviye.setBlock(merkez.offset(i, y, -42), Blocks.BARRIER.defaultBlockState(), 3);
                seviye.setBlock(merkez.offset(i, y, 42), Blocks.BARRIER.defaultBlockState(), 3);
                seviye.setBlock(merkez.offset(-42, y, i), Blocks.BARRIER.defaultBlockState(), 3);
                seviye.setBlock(merkez.offset(42, y, i), Blocks.BARRIER.defaultBlockState(), 3);
            }
        }
        for (int x = -42; x <= 42; x++) {
            for (int z = -42; z <= 42; z++) {
                seviye.setBlock(merkez.offset(x, -1, z), Blocks.BARRIER.defaultBlockState(), 3);
            }
        }
    }

    private static void tarihiYapiKur(ServerLevel seviye, BlockPos merkez, TarihDonemi donem) {
        Block tahta = ahsapPlanki(donem);
        Block kütük = ahsapKütüğü(donem);
        Block yaprak = ahsapYapragi(donem);
        for (int x = -8; x <= 8; x++) {
            seviye.setBlock(merkez.offset(x, 1, -8), tahta.defaultBlockState(), 3);
            seviye.setBlock(merkez.offset(x, 1, 8), tahta.defaultBlockState(), 3);
        }
        for (int z = -8; z <= 8; z++) {
            seviye.setBlock(merkez.offset(-8, 1, z), tahta.defaultBlockState(), 3);
            seviye.setBlock(merkez.offset(8, 1, z), tahta.defaultBlockState(), 3);
        }
        for (int i = 0; i < 4; i++) {
            int x = i < 2 ? -7 : 7;
            int z = i % 2 == 0 ? -7 : 7;
            for (int y = 1; y <= 4; y++) {
                seviye.setBlock(merkez.offset(x, y, z), kütük.defaultBlockState(), 3);
            }
            for (int dx = -2; dx <= 2; dx++) {
                for (int dz = -2; dz <= 2; dz++) {
                    if (Math.abs(dx) + Math.abs(dz) <= 3) {
                        seviye.setBlock(merkez.offset(x + dx, 5, z + dz), yaprak.defaultBlockState(), 3);
                    }
                }
            }
        }
        for (int x = -3; x <= 3; x++) {
            seviye.setBlock(merkez.offset(x, 1, -1), tahta.defaultBlockState(), 3);
            seviye.setBlock(merkez.offset(x, 1, 1), tahta.defaultBlockState(), 3);
        }
    }

    private static Block kapisi(TarihDonemi donem) {
        return chinampaDonemiMi(donem) ? ModBlocks.CHINAMPA_CYPRESS_DOOR.get() : ModBlocks.URUK_CEDAR_DOOR.get();
    }

    private static Block koyTemaBlogu(TarihDonemi donem) {
        return switch (donem) {
            case URUK_YAZI_EVI -> Blocks.CLAY;
            case TERMOPIL_SAVASI -> Blocks.STONE_BRICKS;
            case ISKENDERIYE_KUTUPHANESI, BAGDAT_BILGI_EVI -> Blocks.BOOKSHELF;
            case BAGDAT_PILI_ATOLYESI -> Blocks.COPPER_BLOCK.weathering().unaffected();
            case ANTIKITHERA_LIMANI -> Blocks.POLISHED_DEEPSLATE;
            case TIMBUKTU_EL_YAZMALARI, IPEK_YOLU_KERVANSARAYI -> Blocks.SANDSTONE;
            case TENOKTITLAN_GECIDI -> Blocks.PRISMARINE;
            case POLINEZYA_YILDIZ_YOLU -> Blocks.LAPIS_BLOCK;
            case CATALHOYUK_YERLESKESI -> Blocks.TERRACOTTA;
            case APOLLO_AY_ISTIGI -> Blocks.IRON_BLOCK;
            case EPIDAURUM_TİYATROSU -> Blocks.CALCITE;
        };
    }

    private static Block koyMeslekBlogu(TarihDonemi donem) {
        return switch (donem) {
            case URUK_YAZI_EVI, ISKENDERIYE_KUTUPHANESI, BAGDAT_BILGI_EVI -> Blocks.LECTERN;
            case TERMOPIL_SAVASI, BAGDAT_PILI_ATOLYESI -> Blocks.SMITHING_TABLE;
            case ANTIKITHERA_LIMANI, APOLLO_AY_ISTIGI, TENOKTITLAN_GECIDI, POLINEZYA_YILDIZ_YOLU -> Blocks.CARTOGRAPHY_TABLE;
            case TIMBUKTU_EL_YAZMALARI, IPEK_YOLU_KERVANSARAYI -> Blocks.LOOM;
            case CATALHOYUK_YERLESKESI -> Blocks.STONECUTTER;
            case EPIDAURUM_TİYATROSU -> Blocks.CRAFTING_TABLE;
        };
    }

    private static String koyUzmaniAdi(TarihDonemi donem) {
        return switch (donem) {
            case BAGDAT_PILI_ATOLYESI -> "entity.pastbound.power.engineer";
            case TERMOPIL_SAVASI, ANTIKITHERA_LIMANI -> "entity.pastbound.scene.engineer";
            case TIMBUKTU_EL_YAZMALARI, IPEK_YOLU_KERVANSARAYI -> "entity.pastbound.scene.scribe";
            case TENOKTITLAN_GECIDI, POLINEZYA_YILDIZ_YOLU -> "entity.pastbound.scene.archaeologist";
            default -> "entity.pastbound.village.archivist";
        };
    }

    private static Block ahsapPlanki(TarihDonemi donem) {
        return donem == TarihDonemi.TENOKTITLAN_GECIDI || donem == TarihDonemi.POLINEZYA_YILDIZ_YOLU || donem == TarihDonemi.ANTIKITHERA_LIMANI || donem == TarihDonemi.ISKENDERIYE_KUTUPHANESI || donem == TarihDonemi.TERMOPIL_SAVASI || donem == TarihDonemi.APOLLO_AY_ISTIGI ? ModBlocks.CHINAMPA_CYPRESS_PLANKS.get() : ModBlocks.URUK_CEDAR_PLANKS.get();
    }

    private static Block ahsapKütüğü(TarihDonemi donem) {
        return donem == TarihDonemi.TENOKTITLAN_GECIDI || donem == TarihDonemi.POLINEZYA_YILDIZ_YOLU || donem == TarihDonemi.ANTIKITHERA_LIMANI || donem == TarihDonemi.ISKENDERIYE_KUTUPHANESI || donem == TarihDonemi.TERMOPIL_SAVASI || donem == TarihDonemi.APOLLO_AY_ISTIGI ? ModBlocks.CHINAMPA_CYPRESS_LOG.get() : ModBlocks.URUK_CEDAR_LOG.get();
    }

    private static Block ahsapYapragi(TarihDonemi donem) {
        return donem == TarihDonemi.TENOKTITLAN_GECIDI || donem == TarihDonemi.POLINEZYA_YILDIZ_YOLU || donem == TarihDonemi.ANTIKITHERA_LIMANI || donem == TarihDonemi.ISKENDERIYE_KUTUPHANESI || donem == TarihDonemi.TERMOPIL_SAVASI || donem == TarihDonemi.APOLLO_AY_ISTIGI ? ModBlocks.CHINAMPA_CYPRESS_LEAVES.get() : ModBlocks.URUK_CEDAR_LEAVES.get();
    }

    private static void sahneKapisiKur(ServerLevel seviye, BlockPos merkez, TarihDonemi donem) {
        for (int x = -2; x <= 1; x++) {
            for (int y = 0; y <= 4; y++) {
                seviye.setBlock(merkez.offset(x, y, -8), Blocks.AIR.defaultBlockState(), 3);
            }
        }
    }

    private static void sahneKapisiAnimasyonla(Level seviye, BlockPos merkez, TarihDonemi donem, boolean acik) {
        BlockPos alt = merkez.north(8).above();
        BlockState durum = seviye.getBlockState(alt);
        if (!(durum.getBlock() instanceof DoorBlock)) {
            return;
        }
        seviye.setBlock(alt, durum.setValue(DoorBlock.OPEN, acik), 3);
        BlockState ust = seviye.getBlockState(alt.above());
        if (ust.getBlock() instanceof DoorBlock) {
            seviye.setBlock(alt.above(), ust.setValue(DoorBlock.OPEN, acik), 3);
        }
    }

    private static void modNPCleriTemizle(ServerLevel seviye, BlockPos merkez) {
        for (Entity varlik : seviye.getEntitiesOfClass(Entity.class, new net.minecraft.world.phys.AABB(merkez).inflate(60.0D))) {
            if (varlik.entityTags().contains("pastbound_sahne")
                    || varlik.entityTags().contains("pastbound_durak_0")
                    || varlik.entityTags().contains("pastbound_durak_1")
                    || varlik.entityTags().contains("pastbound_durak_2")
                    || varlik.entityTags().contains("pastbound_saha_uzmani")
                    || varlik.entityTags().contains("pastbound_koy_uzmani")) {
                varlik.discard();
            }
        }
    }

    private static void sahneAktorleriniKur(ServerLevel seviye, BlockPos merkez, TarihDonemi donem) {
        for (Entity varlik : seviye.getEntitiesOfClass(Entity.class, new net.minecraft.world.phys.AABB(merkez).inflate(12.0D))) {
            if (varlik.entityTags().contains("pastbound_sahne") || varlik.entityTags().contains("pastbound_durak_0") || varlik.entityTags().contains("pastbound_durak_1") || varlik.entityTags().contains("pastbound_durak_2")) {
                varlik.discard();
            }
        }
        String[] roller = {"entity.pastbound.scene.narrator", "entity.pastbound.scene.craftsman", "entity.pastbound.scene.witness", "entity.pastbound.scene.scribe"};
        int[][] konumlar = {{-5, -3}, {5, -3}, {-5, 4}, {5, 4}};
        EntityType<?> tip = BuiltInRegistries.ENTITY_TYPE.getValue(Identifier.parse("minecraft:villager"));
        if (tip == null) {
            return;
        }
        for (int i = 0; i < roller.length; i++) {
            Entity varlik = tip.create(seviye, EntitySpawnReason.COMMAND);
            if (varlik instanceof Villager aktor) {
                aktor.setPos(merkez.getX() + konumlar[i][0] + 0.5D, merkez.getY() + 1.0D, merkez.getZ() + konumlar[i][1] + 0.5D);
                aktor.setYRot(i * 90.0F);
                aktor.setNoAi(true);
                aktor.setInvulnerable(true);
                aktor.setSilent(false);
                aktor.addTag("pastbound_sahne");
                aktor.addTag("pastbound_sahne_" + i);
                aktor.setCustomName(Component.translatable(roller[i]).append(" — ").append(donem.adBileseni()));
                aktor.setCustomNameVisible(true);
                seviye.addFreshEntity(aktor);
            }
        }
        int[][] durakKonumlari = {{-7, -7}, {7, -7}, {0, 7}};
        String[] durakRolleri = {"entity.pastbound.scene.archaeologist", "entity.pastbound.scene.miner", "entity.pastbound.scene.engineer"};
        for (int i = 0; i < durakKonumlari.length; i++) {
            EntityType<?> uzmanTipi = BuiltInRegistries.ENTITY_TYPE.getValue(Identifier.parse("minecraft:armor_stand"));
            Entity aktor = uzmanTipi == null ? null : uzmanTipi.create(seviye, EntitySpawnReason.COMMAND);
            if (aktor != null) {
                aktor.setPos(merkez.getX() + durakKonumlari[i][0] + 0.5D, merkez.getY() + 1.0D, merkez.getZ() + durakKonumlari[i][1] + 0.5D);
                aktor.setNoGravity(true);
                aktor.setInvulnerable(true);
                aktor.setSilent(false);
                aktor.addTag("pastbound_durak_" + i);
                aktor.setCustomName(Component.translatable(durakRolleri[i]).append(" — ").append(donem.adBileseni()));
                aktor.setCustomNameVisible(true);
                seviye.addFreshEntity(aktor);
            }
        }
    }

    private static void sahneAktorleriniHareketEttir(Level seviye, BlockPos merkez, int sayac) {
        for (int i = 0; i < 4; i++) {
            for (Entity varlik : seviye.getEntitiesOfClass(Entity.class, new net.minecraft.world.phys.AABB(merkez).inflate(12.0D))) {
                if (varlik.entityTags().contains("pastbound_sahne_" + i)) {
                    double aci = sayac * 0.035D + i * 1.57D;
                    double yaricap = 3.2D + (i % 2) * 0.8D;
                    varlik.setPos(merkez.getX() + Math.cos(aci) * yaricap, merkez.getY() + 1.0D, merkez.getZ() + Math.sin(aci) * yaricap);
                    varlik.setYRot((float) Math.toDegrees(aci) + 90.0F);
                }
            }
        }
    }

    private static Block zeminBlogu(TarihDonemi donem) {
        return chinampaDonemiMi(donem) ? Blocks.GRASS_BLOCK : Blocks.CLAY;
    }

    private static boolean chinampaDonemiMi(TarihDonemi donem) {
        return donem == TarihDonemi.TENOKTITLAN_GECIDI || donem == TarihDonemi.POLINEZYA_YILDIZ_YOLU || donem == TarihDonemi.ANTIKITHERA_LIMANI || donem == TarihDonemi.ISKENDERIYE_KUTUPHANESI || donem == TarihDonemi.TERMOPIL_SAVASI || donem == TarihDonemi.APOLLO_AY_ISTIGI;
    }

    private static Block dekorBlogu(TarihDonemi donem) {
        return switch (donem) {
            case URUK_YAZI_EVI -> Blocks.CLAY;
            case TERMOPIL_SAVASI -> Blocks.STONE;
            case ISKENDERIYE_KUTUPHANESI -> Blocks.BOOKSHELF;
            case BAGDAT_PILI_ATOLYESI -> Blocks.IRON_BLOCK;
            case ANTIKITHERA_LIMANI -> Blocks.IRON_BLOCK;
            case BAGDAT_BILGI_EVI -> Blocks.BOOKSHELF;
            case TIMBUKTU_EL_YAZMALARI -> Blocks.SANDSTONE;
            case TENOKTITLAN_GECIDI -> Blocks.PRISMARINE;
            case POLINEZYA_YILDIZ_YOLU -> Blocks.LAPIS_BLOCK;
            case CATALHOYUK_YERLESKESI -> Blocks.TERRACOTTA;
            case APOLLO_AY_ISTIGI -> Blocks.IRON_BLOCK;
            case IPEK_YOLU_KERVANSARAYI -> Blocks.RED_SANDSTONE;
            case EPIDAURUM_TİYATROSU -> Blocks.CALCITE;
        };
    }
}
