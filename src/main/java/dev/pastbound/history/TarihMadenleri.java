package dev.pastbound.history;

import dev.pastbound.ModId;
import dev.pastbound.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.chunk.PalettedContainer;
import net.minecraft.world.level.chunk.PalettedContainerFactory;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.ChunkEvent;

@EventBusSubscriber(modid = ModId.MOD_ID)
public final class TarihMadenleri {
    private static final ResourceKey<Biome> URUK_BIYOMU = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(ModId.MOD_ID, "uruk_floodplain"));
    private static final ResourceKey<Biome> CHINAMPA_BIYOMU = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(ModId.MOD_ID, "tenochtitlan_chinampa"));
    private static final ResourceKey<Biome> END_MIDLANDS_BIYOMU = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("minecraft", "end_midlands"));
    private static final ResourceKey<Biome> VOID_CHRONICLE_BIYOMU = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(ModId.MOD_ID, "void_chronicle_wastes"));

    private TarihMadenleri() {
    }

    @SubscribeEvent
    public static void chunkYuklendi(ChunkEvent.Load olay) {
        if (!olay.isNewChunk() || !(olay.getChunk() instanceof LevelChunk chunk) || !(chunk.getLevel() instanceof ServerLevel seviye)) {
            return;
        }
        int tohum = chunk.getPos().x() * 341873128 + chunk.getPos().z() * 1328979875;
        RandomSource rastgele = RandomSource.create(tohum);
        if (seviye.dimension().equals(Level.OVERWORLD)) {
            if (rastgele.nextInt(32) == 0) {
                overworldCebi(seviye, chunk, rastgele);
            } else if (rastgele.nextInt(9) == 0) {
                overworldDamar(seviye, chunk, rastgele);
            }
        } else if (seviye.dimension().equals(Level.NETHER)) {
            if (rastgele.nextInt(18) == 0) {
                netherDamar(seviye, chunk, rastgele);
            }
        } else if (seviye.dimension().equals(Level.END)) {
            if (rastgele.nextInt(8) == 0) {
                endBiyomCebi(seviye, chunk, rastgele);
            } else if (rastgele.nextInt(6) == 0) {
                endDamar(seviye, chunk, rastgele);
            }
        }
    }

    private static void overworldCebi(ServerLevel seviye, LevelChunk chunk, RandomSource rastgele) {
        boolean chinampa = Math.floorMod(chunk.getPos().x() + chunk.getPos().z(), 2) == 0;
        Holder<Biome> biyom = seviye.registryAccess().lookupOrThrow(Registries.BIOME).get(chinampa ? CHINAMPA_BIYOMU : URUK_BIYOMU).orElse(null);
        if (biyom != null) {
            biyomCebiUygula(chunk, biyom);
        }
        overworldDamar(seviye, chunk, rastgele);
        tarihEkosistemi(seviye, chunk, rastgele, chinampa);
    }

    private static void overworldDamar(ServerLevel seviye, LevelChunk chunk, RandomSource rastgele) {
        damarYerlestir(seviye, chunk, rastgele, ModBlocks.TIME_STONE_ORE.get(), 10 + rastgele.nextInt(44), 5, false, false);
        damarYerlestir(seviye, chunk, rastgele, ModBlocks.CHRONICLE_ORE.get(), 16 + rastgele.nextInt(48), 5, false, false);
    }

    private static void netherDamar(ServerLevel seviye, LevelChunk chunk, RandomSource rastgele) {
        damarYerlestir(seviye, chunk, rastgele, ModBlocks.NETHER_TIME_STONE_ORE.get(), 18 + rastgele.nextInt(62), 6, true, false);
        damarYerlestir(seviye, chunk, rastgele, ModBlocks.ASH_CHRONICLE_ORE.get(), 24 + rastgele.nextInt(50), 5, true, false);
        netherAniti(seviye, chunk, rastgele);
    }


    private static void endBiyomCebi(ServerLevel seviye, LevelChunk chunk, RandomSource rastgele) {
        ResourceKey<Biome> anahtar = Math.floorMod(chunk.getPos().x() + chunk.getPos().z(), 2) == 0 ? END_MIDLANDS_BIYOMU : VOID_CHRONICLE_BIYOMU;
        Holder<Biome> biyom = seviye.registryAccess().lookupOrThrow(Registries.BIOME).get(anahtar).orElse(null);
        if (biyom != null) {
            biyomCebiUygula(chunk, biyom);
        }
        endDamar(seviye, chunk, rastgele);
        if (anahtar.equals(END_MIDLANDS_BIYOMU) && rastgele.nextInt(256) == 0) {
            damarYerlestir(seviye, chunk, rastgele, ModBlocks.ERVANIUM_ORE.get(), 18 + rastgele.nextInt(36), 1, false, true);
        }
    }

    private static void endDamar(ServerLevel seviye, LevelChunk chunk, RandomSource rastgele) {
        damarYerlestir(seviye, chunk, rastgele, ModBlocks.END_ECHO_ORE.get(), 20 + rastgele.nextInt(68), 6, false, true);
        damarYerlestir(seviye, chunk, rastgele, ModBlocks.VOID_CHRONICLE_ORE.get(), 28 + rastgele.nextInt(60), 5, false, true);
        endGozlemeNoktasi(seviye, chunk, rastgele);
    }

    private static void damarYerlestir(ServerLevel seviye, LevelChunk chunk, RandomSource rastgele, Block cevher, int merkezY, int boyut, boolean nether, boolean end) {
        int baslangicX = chunk.getPos().getMinBlockX() + 2 + rastgele.nextInt(12);
        int baslangicZ = chunk.getPos().getMinBlockZ() + 2 + rastgele.nextInt(12);
        for (int i = 0; i < boyut; i++) {
            int x = baslangicX + rastgele.nextInt(5) - 2;
            int z = baslangicZ + rastgele.nextInt(5) - 2;
            int y = Math.max(5, merkezY + rastgele.nextInt(7) - 3);
            BlockPos konum = new BlockPos(x, y, z);
            BlockState temel = seviye.getBlockState(konum);
            boolean uygun = end ? temel.is(Blocks.END_STONE) : nether ? temel.is(Blocks.NETHERRACK) || temel.is(Blocks.BLACKSTONE) || temel.is(Blocks.BASALT) : temel.is(Blocks.STONE) || temel.is(Blocks.DEEPSLATE) || temel.is(Blocks.TUFF);
            if (uygun) {
                seviye.setBlock(konum, cevher.defaultBlockState(), 3);
            }
        }
    }

    private static void tarihEkosistemi(ServerLevel seviye, LevelChunk chunk, RandomSource rastgele, boolean chinampa) {
        int ilkX = chunk.getPos().getMinBlockX() + 3;
        int ilkZ = chunk.getPos().getMinBlockZ() + 3;
        int yuzey = chunk.getHeight(Heightmap.Types.WORLD_SURFACE, 3, 3);
        if (chinampa) {
            kanalOlustur(seviye, ilkX, ilkZ, yuzey);
            kanalOlustur(seviye, ilkX + 8, ilkZ + 4, yuzey);
        }
        for (int i = 0; i < (chinampa ? 3 : 2); i++) {
            int x = chunk.getPos().getMinBlockX() + 3 + rastgele.nextInt(10);
            int z = chunk.getPos().getMinBlockZ() + 3 + rastgele.nextInt(10);
            tarihAgaci(seviye, chunk, x, z, chinampa);
        }
        arkeolojiNoktasi(seviye, chunk, rastgele, chinampa);
    }

    private static void kanalOlustur(ServerLevel seviye, int x, int z, int yuzey) {
        for (int i = 0; i < 6; i++) {
            BlockPos su = new BlockPos(x + i, yuzey, z);
            if (seviye.isEmptyBlock(su) && seviye.getBlockState(su.below()).is(Blocks.GRASS_BLOCK)) {
                seviye.setBlock(su, Blocks.WATER.defaultBlockState(), 3);
            }
        }
    }

    private static void tarihAgaci(ServerLevel seviye, LevelChunk chunk, int x, int z, boolean chinampa) {
        int yerelX = x - chunk.getPos().getMinBlockX();
        int yerelZ = z - chunk.getPos().getMinBlockZ();
        int yuzey = chunk.getHeight(Heightmap.Types.WORLD_SURFACE, Math.max(0, Math.min(15, yerelX)), Math.max(0, Math.min(15, yerelZ)));
        BlockPos taban = new BlockPos(x, yuzey, z);
        if (!seviye.getBlockState(taban.below()).is(Blocks.GRASS_BLOCK) && !seviye.getBlockState(taban.below()).is(Blocks.DIRT)) {
            return;
        }
        Block kutuk = chinampa ? ModBlocks.CHINAMPA_CYPRESS_LOG.get() : ModBlocks.URUK_CEDAR_LOG.get();
        Block yaprak = chinampa ? ModBlocks.CHINAMPA_CYPRESS_LEAVES.get() : ModBlocks.URUK_CEDAR_LEAVES.get();
        for (int y = 0; y < 3; y++) {
            seviye.setBlock(taban.above(y), kutuk.defaultBlockState(), 3);
        }
        for (int dx = -2; dx <= 2; dx++) {
            for (int dz = -2; dz <= 2; dz++) {
                if (Math.abs(dx) + Math.abs(dz) <= 3) {
                    seviye.setBlock(taban.above(3).offset(dx, 0, dz), yaprak.defaultBlockState(), 3);
                }
            }
        }
    }

    private static void arkeolojiNoktasi(ServerLevel seviye, LevelChunk chunk, RandomSource rastgele, boolean chinampa) {
        int x = chunk.getPos().getMinBlockX() + 5 + rastgele.nextInt(6);
        int z = chunk.getPos().getMinBlockZ() + 5 + rastgele.nextInt(6);
        int yerelX = x - chunk.getPos().getMinBlockX();
        int yerelZ = z - chunk.getPos().getMinBlockZ();
        int yuzey = chunk.getHeight(Heightmap.Types.WORLD_SURFACE, yerelX, yerelZ);
        Block taban = chinampa ? Blocks.PRISMARINE_BRICKS : Blocks.CHISELED_SANDSTONE;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                BlockPos konum = new BlockPos(x + dx, yuzey, z + dz);
                if (seviye.isEmptyBlock(konum)) {
                    seviye.setBlock(konum, taban.defaultBlockState(), 3);
                }
            }
        }
        BlockPos merkez = new BlockPos(x, yuzey + 1, z);
        if (seviye.isEmptyBlock(merkez)) {
            seviye.setBlock(merkez, Blocks.LANTERN.defaultBlockState(), 3);
        }
    }

    private static void netherAniti(ServerLevel seviye, LevelChunk chunk, RandomSource rastgele) {
        int x = chunk.getPos().getMinBlockX() + 5 + rastgele.nextInt(6);
        int z = chunk.getPos().getMinBlockZ() + 5 + rastgele.nextInt(6);
        int y = 32 + rastgele.nextInt(32);
        BlockPos merkez = new BlockPos(x, y, z);
        if (!seviye.getBlockState(merkez).is(Blocks.NETHERRACK)) {
            return;
        }
        seviye.setBlock(merkez, Blocks.SOUL_LANTERN.defaultBlockState(), 3);
        seviye.setBlock(merkez.below(), Blocks.POLISHED_BLACKSTONE.defaultBlockState(), 3);
        seviye.setBlock(merkez.north(), Blocks.POLISHED_BLACKSTONE.defaultBlockState(), 3);
        seviye.setBlock(merkez.south(), Blocks.POLISHED_BLACKSTONE.defaultBlockState(), 3);
        seviye.setBlock(merkez.east(), Blocks.POLISHED_BLACKSTONE.defaultBlockState(), 3);
        seviye.setBlock(merkez.west(), Blocks.POLISHED_BLACKSTONE.defaultBlockState(), 3);
    }

    private static void endGozlemeNoktasi(ServerLevel seviye, LevelChunk chunk, RandomSource rastgele) {
        int x = chunk.getPos().getMinBlockX() + 5 + rastgele.nextInt(6);
        int z = chunk.getPos().getMinBlockZ() + 5 + rastgele.nextInt(6);
        int yuzey = chunk.getHeight(Heightmap.Types.WORLD_SURFACE, x - chunk.getPos().getMinBlockX(), z - chunk.getPos().getMinBlockZ());
        BlockPos merkez = new BlockPos(x, yuzey, z);
        if (!seviye.getBlockState(merkez.below()).is(Blocks.END_STONE)) {
            return;
        }
        seviye.setBlock(merkez, Blocks.PURPUR_BLOCK.defaultBlockState(), 3);
        seviye.setBlock(merkez.above(), Blocks.END_ROD.defaultBlockState(), 3);
        if (seviye.isEmptyBlock(merkez.above(2))) {
            seviye.setBlock(merkez.above(2), Blocks.CHORUS_FLOWER.defaultBlockState(), 3);
        }
    }

    private static void biyomCebiUygula(LevelChunk chunk, Holder<Biome> biyom) {
        ServerLevel seviye = (ServerLevel) chunk.getLevel();
        PalettedContainerFactory fabrika = PalettedContainerFactory.create(seviye.registryAccess());
        LevelChunkSection[] kesitler = chunk.getSections();
        for (int i = 0; i < kesitler.length; i++) {
            LevelChunkSection eski = kesitler[i];
            PalettedContainer<Holder<Biome>> yeni = fabrika.createForBiomes();
            for (int x = 0; x < 4; x++) {
                for (int y = 0; y < 4; y++) {
                    for (int z = 0; z < 4; z++) {
                        yeni.set(x, y, z, biyom);
                    }
                }
            }
            kesitler[i] = new LevelChunkSection(eski.getStates(), yeni);
        }
        chunk.markUnsaved();
    }
}
