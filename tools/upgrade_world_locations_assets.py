from pathlib import Path
import json

root = Path(__file__).resolve().parents[1]
java = root / "src/main/java/dev/pastbound/history/TarihiKesifDunyasi.java"
s = java.read_text(encoding="utf-8")
s = s.replace("for (int y = 0; y <= 4; y++) {\n            for (int i = -10; i <= 10; i++) {", "for (int y = 0; y <= 4; y++) {\n            for (int i = -42; i <= 42; i++) {")
s = s.replace("for (int x = -10; x <= 10; x++) {\n            for (int z = -10; z <= 10; z++) {", "for (int x = -42; x <= 42; x++) {\n            for (int z = -42; z <= 42; z++) {")
s = s.replace("seviye.setBlock(merkez.north(10), Blocks.BARRIER.defaultBlockState(), 3);", "seviye.setBlock(merkez.north(42), Blocks.BARRIER.defaultBlockState(), 3);")
needle = "        if (donem != TarihDonemi.BAGDAT_PILI_ATOLYESI) {\n            return;\n        }\n"
replacement = "        tarihLokasyonlariniKur(seviye, merkez, donem);\n        if (donem != TarihDonemi.BAGDAT_PILI_ATOLYESI) {\n            return;\n        }\n"
if needle not in s:
    raise SystemExit("gorevVarliklariniKur noktasi bulunamadi")
s = s.replace(needle, replacement, 1)
anchor = "    public static void konusmaCevapla(ServerPlayer oyuncu, String donemKimligi, int konusmaci, int secim) {"
method = '''    private static void tarihLokasyonlariniKur(ServerLevel seviye, BlockPos merkez, TarihDonemi donem) {
        BlockPos koy = merkez.west(28).north(18);
        BlockPos maden = merkez.east(28).north(18);
        BlockPos atölye = merkez.south(28);
        lokasyonZeminiKur(seviye, koy, 7, Blocks.PATH_BLOCK);
        lokasyonZeminiKur(seviye, maden, 7, Blocks.DEEPSLATE);
        lokasyonZeminiKur(seviye, atölye, 6, Blocks.COBBLED_DEEPSLATE);
        koyEviKur(seviye, koy, donem);
        madenGirisiKur(seviye, maden);
        elektrikAtolyesiKur(seviye, atölye);
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
        for (int i = -yaricap; i <= yaricap; i++) {
            seviye.setBlock(merkez.offset(i, 1, -yaricap), Blocks.OAK_FENCE.defaultBlockState(), 3);
            seviye.setBlock(merkez.offset(i, 1, yaricap), Blocks.OAK_FENCE.defaultBlockState(), 3);
            seviye.setBlock(merkez.offset(-yaricap, 1, i), Blocks.OAK_FENCE.defaultBlockState(), 3);
            seviye.setBlock(merkez.offset(yaricap, 1, i), Blocks.OAK_FENCE.defaultBlockState(), 3);
        }
    }

    private static void koyEviKur(ServerLevel seviye, BlockPos merkez, TarihDonemi donem) {
        Block duvar = ahsapPlanki(donem);
        for (int y = 1; y <= 3; y++) {
            for (int x = -3; x <= 3; x++) {
                seviye.setBlock(merkez.offset(x, y, -3), duvar.defaultBlockState(), 3);
                seviye.setBlock(merkez.offset(x, y, 3), duvar.defaultBlockState(), 3);
            }
            for (int z = -2; z <= 2; z++) {
                seviye.setBlock(merkez.offset(-3, y, z), duvar.defaultBlockState(), 3);
                seviye.setBlock(merkez.offset(3, y, z), duvar.defaultBlockState(), 3);
            }
        }
        seviye.setBlock(merkez, Blocks.CRAFTING_TABLE.defaultBlockState(), 3);
        seviye.setBlock(merkez.east(), Blocks.LECTERN.defaultBlockState(), 3);
        seviye.setBlock(merkez.west(), Blocks.BELL.defaultBlockState(), 3);
        villagerKur(seviye, merkez.east(2), "entity.pastbound.village.archivist");
    }

    private static void villagerKur(ServerLevel seviye, BlockPos konum, String ad) {
        Entity varlik = EntityType.VILLAGER.create(seviye, EntitySpawnReason.COMMAND);
        if (varlik instanceof Villager koylu) {
            koylu.setPos(konum.getX() + 0.5D, konum.getY() + 1.0D, konum.getZ() + 0.5D);
            koylu.setInvulnerable(true);
            koylu.setCustomName(Component.translatable(ad));
            koylu.setCustomNameVisible(true);
            koylu.addTag("pastbound_saha_uzmani");
            seviye.addFreshEntity(koylu);
        }
    }

    private static void madenGirisiKur(ServerLevel seviye, BlockPos merkez) {
        for (int y = 1; y <= 4; y++) {
            for (int x = -3; x <= 3; x++) {
                seviye.setBlock(merkez.offset(x, y, -2), Blocks.DEEPSLATE_BRICKS.defaultBlockState(), 3);
            }
        }
        for (int i = -2; i <= 2; i++) {
            seviye.setBlock(merkez.offset(i, 1, 0), ModBlocks.STEEL_ORE.get().defaultBlockState(), 3);
            seviye.setBlock(merkez.offset(i, 2, 0), ModBlocks.STEEL_ORE.get().defaultBlockState(), 3);
        }
        seviye.setBlock(merkez.south(2), ModBlocks.HISTORICAL_FORGE.get().defaultBlockState(), 3);
        seviye.setBlock(merkez.south(3), Blocks.FURNACE.defaultBlockState(), 3);
    }

    private static void elektrikAtolyesiKur(ServerLevel seviye, BlockPos merkez) {
        for (int x = -4; x <= 4; x++) {
            seviye.setBlock(merkez.offset(x, 1, -3), Blocks.COPPER_BLOCK.defaultBlockState(), 3);
            seviye.setBlock(merkez.offset(x, 1, 3), Blocks.COPPER_BLOCK.defaultBlockState(), 3);
        }
        seviye.setBlock(merkez, ModBlocks.RESONANCE_PILLAR.get().defaultBlockState(), 3);
        seviye.setBlock(merkez.east(2), Blocks.REDSTONE_BLOCK.defaultBlockState(), 3);
        seviye.setBlock(merkez.west(2), Blocks.COPPER_BLOCK.defaultBlockState(), 3);
        seviye.setBlock(merkez.north(2), Blocks.CRAFTING_TABLE.defaultBlockState(), 3);
        villagerKur(seviye, merkez.south(2), "entity.pastbound.power.engineer");
    }

    private static void gorevIsaretiKur(ServerLevel seviye, BlockPos merkez, Block blok) {
        seviye.setBlock(merkez.above(), blok.defaultBlockState(), 3);
        seviye.setBlock(merkez.above(2), Blocks.LANTERN.defaultBlockState(), 3);
    }

'''
if anchor not in s:
    raise SystemExit("metot ankori bulunamadi")
s = s.replace(anchor, method + anchor, 1)
java.write_text(s, encoding="utf-8")

for name in ("uruk_cedar", "chinampa_cypress"):
    item = root / f"src/main/resources/assets/pastbound/models/item/{name}_door.json"
    item.write_text(json.dumps({"parent": "minecraft:item/generated", "textures": {"layer0": f"pastbound:block/{name}_door"}}, indent=2) + "\n")
    item = root / f"src/main/resources/assets/pastbound/models/item/{name}_trapdoor.json"
    item.write_text(json.dumps({"parent": "minecraft:item/generated", "textures": {"layer0": f"pastbound:block/{name}_trapdoor"}}, indent=2) + "\n")

biome_template = json.loads((root / "src/main/resources/data/pastbound/worldgen/biome/uruk_floodplain.json").read_text())
end_a = dict(biome_template)
end_a["downfall"] = 0.0
end_a["temperature"] = 0.8
end_a["effects"] = {"water_color": "#6b4ca5", "water_fog_color": "#2f1f52"}
end_a["attributes"] = {"minecraft:visual/sky_color": "#46346e", "minecraft:visual/fog_color": "#241b3d"}
end_a["spawners"] = {"ambient": [{"type": "minecraft:endermite", "weight": 8, "minCount": 1, "maxCount": 2}], "axolotls": [], "creature": [], "monster": [{"type": "minecraft:enderman", "weight": 10, "minCount": 1, "maxCount": 2}], "underground_water_creature": [], "water_ambient": [], "water_creature": []}
end_b = json.loads(json.dumps(end_a))
end_b["temperature"] = 0.2
end_b["effects"] = {"water_color": "#5b9da4", "water_fog_color": "#1c4248"}
end_b["attributes"] = {"minecraft:visual/sky_color": "#1e5261", "minecraft:visual/fog_color": "#102b35"}
for name, data in (("end_echo_gardens", end_a), ("void_chronicle_wastes", end_b)):
    (root / f"src/main/resources/data/pastbound/worldgen/biome/{name}.json").write_text(json.dumps(data, indent=2) + "\n")

p = root / "src/main/java/dev/pastbound/history/TarihMadenleri.java"
s = p.read_text(encoding="utf-8")
s = s.replace('    private static final ResourceKey<Biome> CHINAMPA_BIYOMU = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(ModId.MOD_ID, "tenochtitlan_chinampa"));\n', '    private static final ResourceKey<Biome> CHINAMPA_BIYOMU = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(ModId.MOD_ID, "tenochtitlan_chinampa"));\n    private static final ResourceKey<Biome> END_ECHO_BIYOMU = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(ModId.MOD_ID, "end_echo_gardens"));\n    private static final ResourceKey<Biome> VOID_CHRONICLE_BIYOMU = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(ModId.MOD_ID, "void_chronicle_wastes"));\n')
s = s.replace('        } else if (seviye.dimension().equals(Level.END) && rastgele.nextInt(18) == 0) {\n            endDamar(seviye, chunk, rastgele);\n        }', '        } else if (seviye.dimension().equals(Level.END)) {\n            if (rastgele.nextInt(8) == 0) {\n                endBiyomCebi(seviye, chunk, rastgele);\n            } else if (rastgele.nextInt(6) == 0) {\n                endDamar(seviye, chunk, rastgele);\n            }\n        }')
insert = '''\n    private static void endBiyomCebi(ServerLevel seviye, LevelChunk chunk, RandomSource rastgele) {\n        ResourceKey<Biome> anahtar = Math.floorMod(chunk.getPos().x() + chunk.getPos().z(), 2) == 0 ? END_ECHO_BIYOMU : VOID_CHRONICLE_BIYOMU;\n        Holder<Biome> biyom = seviye.registryAccess().lookupOrThrow(Registries.BIOME).get(anahtar).orElse(null);\n        if (biyom != null) {\n            biyomCebiUygula(chunk, biyom);\n        }\n        endDamar(seviye, chunk, rastgele);\n    }\n'''
s = s.replace('    private static void endDamar(ServerLevel seviye, LevelChunk chunk, RandomSource rastgele) {', insert + '\n    private static void endDamar(ServerLevel seviye, LevelChunk chunk, RandomSource rastgele) {', 1)
p.write_text(s, encoding="utf-8")
print("world_locations_assets_biomes_updated")
