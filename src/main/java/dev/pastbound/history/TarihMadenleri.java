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
import net.minecraft.world.level.block.Blocks;
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

    private TarihMadenleri() {
    }

    @SubscribeEvent
    public static void chunkYuklendi(ChunkEvent.Load olay) {
        if (!olay.isNewChunk() || !(olay.getChunk() instanceof LevelChunk chunk) || !(chunk.getLevel() instanceof ServerLevel seviye)) {
            return;
        }
        int tohum = chunk.getPos().x() * 341873128 + chunk.getPos().z() * 1328979875;
        RandomSource rastgele = RandomSource.create(tohum);
        if (rastgele.nextInt(128) != 0) {
            return;
        }
        if (seviye.dimension().equals(Level.OVERWORLD)) {
            overworldCebi(seviye, chunk, rastgele);
        } else if (seviye.dimension().equals(Level.NETHER)) {
            netherDamar(seviye, chunk, rastgele);
        } else if (seviye.dimension().equals(Level.END)) {
            endDamar(seviye, chunk, rastgele);
        }
    }

    private static void overworldCebi(ServerLevel seviye, LevelChunk chunk, RandomSource rastgele) {
        boolean chinampa = Math.floorMod(chunk.getPos().x() + chunk.getPos().z(), 2) == 0;
        Holder<Biome> biyom = seviye.registryAccess().lookupOrThrow(Registries.BIOME).get(chinampa ? CHINAMPA_BIYOMU : URUK_BIYOMU).orElse(null);
        if (biyom != null) {
            biyomCebiUygula(chunk, biyom);
        }
        for (int i = 0; i < 2; i++) {
            int x = chunk.getPos().getMinBlockX() + 2 + rastgele.nextInt(12);
            int z = chunk.getPos().getMinBlockZ() + 2 + rastgele.nextInt(12);
            int y = 8 + rastgele.nextInt(48);
            BlockPos konum = new BlockPos(x, y, z);
            if (chunk.getBlockState(konum).is(Blocks.STONE) || chunk.getBlockState(konum).is(Blocks.DEEPSLATE)) {
                seviye.setBlock(konum, (i == 0 ? ModBlocks.TIME_STONE_ORE : ModBlocks.CHRONICLE_ORE).get().defaultBlockState(), 3);
            }
        }
        tarihAgaci(seviye, chunk, rastgele, chinampa);
    }

    private static void netherDamar(ServerLevel seviye, LevelChunk chunk, RandomSource rastgele) {
        for (int i = 0; i < 3; i++) {
            int x = chunk.getPos().getMinBlockX() + 2 + rastgele.nextInt(12);
            int z = chunk.getPos().getMinBlockZ() + 2 + rastgele.nextInt(12);
            int y = 18 + rastgele.nextInt(62);
            BlockPos konum = new BlockPos(x, y, z);
            if (chunk.getBlockState(konum).is(Blocks.NETHERRACK) || chunk.getBlockState(konum).is(Blocks.BLACKSTONE) || chunk.getBlockState(konum).is(Blocks.BASALT)) {
                seviye.setBlock(konum, (i == 0 ? ModBlocks.NETHER_TIME_STONE_ORE : ModBlocks.ASH_CHRONICLE_ORE).get().defaultBlockState(), 3);
            }
        }
    }

    private static void endDamar(ServerLevel seviye, LevelChunk chunk, RandomSource rastgele) {
        for (int i = 0; i < 3; i++) {
            int x = chunk.getPos().getMinBlockX() + 2 + rastgele.nextInt(12);
            int z = chunk.getPos().getMinBlockZ() + 2 + rastgele.nextInt(12);
            int y = 20 + rastgele.nextInt(72);
            BlockPos konum = new BlockPos(x, y, z);
            if (chunk.getBlockState(konum).is(Blocks.END_STONE)) {
                seviye.setBlock(konum, (i == 0 ? ModBlocks.END_ECHO_ORE : ModBlocks.VOID_CHRONICLE_ORE).get().defaultBlockState(), 3);
            }
        }
    }

    private static void tarihAgaci(ServerLevel seviye, LevelChunk chunk, RandomSource rastgele, boolean chinampa) {
        int x = chunk.getPos().getMinBlockX() + 5 + rastgele.nextInt(6);
        int z = chunk.getPos().getMinBlockZ() + 5 + rastgele.nextInt(6);
        int yuzey = chunk.getHeight(Heightmap.Types.WORLD_SURFACE, x - chunk.getPos().getMinBlockX(), z - chunk.getPos().getMinBlockZ());
        BlockPos taban = new BlockPos(x, yuzey, z);
        if (!seviye.getBlockState(taban.below()).is(Blocks.GRASS_BLOCK) && !seviye.getBlockState(taban.below()).is(Blocks.DIRT)) {
            return;
        }
        var kutuk = chinampa ? ModBlocks.CHINAMPA_CYPRESS_LOG.get() : ModBlocks.URUK_CEDAR_LOG.get();
        var yaprak = chinampa ? ModBlocks.CHINAMPA_CYPRESS_LEAVES.get() : ModBlocks.URUK_CEDAR_LEAVES.get();
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

