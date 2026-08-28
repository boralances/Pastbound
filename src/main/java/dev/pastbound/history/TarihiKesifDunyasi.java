package dev.pastbound.history;

import java.util.Set;

import dev.pastbound.ModId;
import dev.pastbound.network.PastboundPaketi;
import dev.pastbound.registry.ModBlocks;
import dev.pastbound.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.neoforged.neoforge.common.extensions.IEntityExtension;
import net.neoforged.neoforge.network.PacketDistributor;

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
        veri.putDouble(YURUME_MESAFESI, 0.0D);
        veri.remove(IZLEME_X);
        veri.remove(IZLEME_Y);
        veri.remove(IZLEME_Z);
        sahneyiKur(hedef, SAHNE_MERKEZI, donem);
        oyuncu.teleportTo(hedef, SAHNE_MERKEZI.getX() + 0.5D, SAHNE_MERKEZI.getY() + 1.0D, SAHNE_MERKEZI.getZ() + 0.5D, Set.of(), 0.0F, 0.0F, false);
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
        return veri.getIntOr(CELIK_GOREV_ASAMASI, 0) == 1 && oyuncu.level().getBlockState(konum).is(ModBlocks.STEEL_ORE.get());
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
            seviye.setBlock(durak.above(), Blocks.LANTERN.defaultBlockState(), 3);
        }
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

    public static void konusmaCevapla(ServerPlayer oyuncu, String donemKimligi, int konusmaci, int secim) {
        if (!boyuttaMi(oyuncu) || konusmaci < 0 || konusmaci > 3 || secim < 1 || secim > 3) {
            return;
        }
        TarihDonemi donem = donemBul(donemKimligi);
        if (donem == null) {
            return;
        }
        boolean yakin = false;
        for (Entity varlik : oyuncu.level().getEntitiesOfClass(Entity.class, oyuncu.getBoundingBox().inflate(5.0D))) {
            if (varlik.entityTags().contains("pastbound_sahne_" + konusmaci)) {
                yakin = true;
                break;
            }
        }
        if (!yakin) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.dialogue.too_far"));
            PacketDistributor.sendToPlayer(oyuncu, PastboundPaketi.konusmaCevabi(donem.kimlik(), konusmaci, 0));
            return;
        }
        oyuncu.sendSystemMessage(Component.translatable("history.pastbound.period." + donem.kimlik() + ".response_" + secim));
        PacketDistributor.sendToPlayer(oyuncu, PastboundPaketi.konusmaCevabi(donem.kimlik(), konusmaci, secim));
        CompoundTag veri = ((IEntityExtension) oyuncu).getPersistentData();
        int gorevMaskesi = veri.getIntOr(SAHNE_GOREV_MASKESI, 0) | (1 << konusmaci);
        veri.putInt(SAHNE_GOREV_MASKESI, gorevMaskesi);
        oyuncu.sendSystemMessage(Component.translatable("message.pastbound.scene.quest_talk", konusmaci + 1));
        goreviKontrolEt(oyuncu);
        oyuncu.level().playSound(null, oyuncu.blockPosition(), net.minecraft.sounds.SoundEvents.VILLAGER_TRADE, net.minecraft.sounds.SoundSource.NEUTRAL, 0.8F, 1.0F + secim * 0.08F);
    }

    public static void don(ServerPlayer oyuncu) {
        if (!boyuttaMi(oyuncu)) {
            return;
        }
        CompoundTag veri = ((IEntityExtension) oyuncu).getPersistentData();
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
        oyuncu.sendSystemMessage(Component.translatable("message.pastbound.scene.returned"));
    }

    public static void kontrolSonrasiTik(ServerPlayer oyuncu) {
        if (!boyuttaMi(oyuncu)) {
            return;
        }
        gercekYurumeyiIzle(oyuncu);
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
        goreviKontrolEt(oyuncu);
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
        boolean hareketTamam = duraklarTamam && durakKonusmalariTamam && durakCihazlariTamam && mesafeTamam;
        boolean incelemeTamam = (gorevMaskesi & SAHNE_INCELEME_BITI) != 0;
        boolean anitTamam = (gorevMaskesi & SAHNE_ANIT_BITI) != 0;
        if (konusmaTamam && hareketTamam && incelemeTamam && anitTamam && (gorevMaskesi & 64) == 0) {
            veri.putInt(SAHNE_GOREV_MASKESI, gorevMaskesi | 64);
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.scene.quest_complete"));
            oyuncu.getInventory().placeItemBackInInventory(new net.minecraft.world.item.ItemStack(dev.pastbound.registry.ModItems.CHRONICLE_SCRAP.get(), 2));
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
        sahneKapisiKur(seviye, merkez, donem);
        gorevVarliklariniKur(seviye, merkez, donem);
        sahneAktorleriniKur(seviye, merkez, donem);
    }

    private static void sahneBariyerleriniKur(ServerLevel seviye, BlockPos merkez) {
        for (int y = 0; y <= 4; y++) {
            for (int i = -10; i <= 10; i++) {
                seviye.setBlock(merkez.offset(i, y, -10), Blocks.BARRIER.defaultBlockState(), 3);
                seviye.setBlock(merkez.offset(i, y, 10), Blocks.BARRIER.defaultBlockState(), 3);
                seviye.setBlock(merkez.offset(-10, y, i), Blocks.BARRIER.defaultBlockState(), 3);
                seviye.setBlock(merkez.offset(10, y, i), Blocks.BARRIER.defaultBlockState(), 3);
            }
        }
        for (int x = -10; x <= 10; x++) {
            for (int z = -10; z <= 10; z++) {
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
        Block kapı = chinampaDonemiMi(donem) ? ModBlocks.CHINAMPA_CYPRESS_DOOR.get() : ModBlocks.URUK_CEDAR_DOOR.get();
        BlockPos alt = merkez.north(8).above();
        seviye.setBlock(alt, kapı.defaultBlockState().setValue(DoorBlock.HALF, DoubleBlockHalf.LOWER).setValue(DoorBlock.OPEN, false), 3);
        seviye.setBlock(alt.above(), kapı.defaultBlockState().setValue(DoorBlock.HALF, DoubleBlockHalf.UPPER).setValue(DoorBlock.OPEN, false), 3);
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
            Entity varlik = tip.create(seviye, EntitySpawnReason.COMMAND);
            if (varlik instanceof Villager aktor) {
                aktor.setPos(merkez.getX() + durakKonumlari[i][0] + 0.5D, merkez.getY() + 1.0D, merkez.getZ() + durakKonumlari[i][1] + 0.5D);
                aktor.setNoAi(true);
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
