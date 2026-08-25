package dev.pastbound.history;

import java.util.Set;

import dev.pastbound.ModId;
import dev.pastbound.network.PastboundPaketi;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
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
    private static final BlockPos SAHNE_MERKEZI = new BlockPos(0, 64, 0);

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
        sahneyiKur(hedef, SAHNE_MERKEZI, donem);
        oyuncu.teleportTo(hedef, SAHNE_MERKEZI.getX() + 0.5D, SAHNE_MERKEZI.getY() + 1.0D, SAHNE_MERKEZI.getZ() + 0.5D, Set.of(), 0.0F, 0.0F, false);
        oyuncu.sendSystemMessage(Component.translatable("message.pastbound.scene.enter", donem.adBileseni()));
        oyuncu.sendSystemMessage(Component.translatable("message.pastbound.scene.press_d"));
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
        if (sayac % 20 == 0) {
            PacketDistributor.sendToPlayer(oyuncu, PastboundPaketi.sahne(donem.kimlik(), sayac));
        }
        if (sayac == 1) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.scene.narration_intro", donem.adBileseni(), donem.aciklamaBileseni()));
        } else if (sayac == 80) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.scene.narration_focus", donem.odakBileseni()));
        } else if (sayac == 160) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.scene.narration_detail", donem.aciklamaBileseni()));
        } else if (sayac == 220) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.scene.ready"));
        }
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
            return;
        }
        oyuncu.sendSystemMessage(Component.translatable("history.pastbound.period." + donem.kimlik() + ".dialogue_" + secim));
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
        oyuncu.sendSystemMessage(Component.translatable("message.pastbound.scene.returned"));
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
        BlockState zemin = Blocks.GRASS_BLOCK.defaultBlockState();
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
        seviye.setBlock(merkez, dekor.defaultBlockState(), 3);
        seviye.setBlock(merkez.above(), Blocks.BEACON.defaultBlockState(), 3);
        seviye.setBlock(merkez.north(3), dekor.defaultBlockState(), 3);
        seviye.setBlock(merkez.south(3), dekor.defaultBlockState(), 3);
        seviye.setBlock(merkez.east(3), dekor.defaultBlockState(), 3);
        seviye.setBlock(merkez.west(3), dekor.defaultBlockState(), 3);
        sahneAktorleriniKur(seviye, merkez, donem);
    }

    private static void sahneAktorleriniKur(ServerLevel seviye, BlockPos merkez, TarihDonemi donem) {
        for (Entity varlik : seviye.getEntitiesOfClass(Entity.class, new net.minecraft.world.phys.AABB(merkez).inflate(12.0D))) {
            if (varlik.entityTags().contains("pastbound_sahne")) {
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
        };
    }
}
