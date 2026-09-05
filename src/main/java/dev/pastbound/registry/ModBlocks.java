package dev.pastbound.registry;

import dev.pastbound.ModId;
import dev.pastbound.block.EchoArchiveBlock;
import dev.pastbound.block.AncientStorageBlock;
import dev.pastbound.block.ResonancePillarBlock;
import dev.pastbound.block.TarihBasincPlakaBlock;
import dev.pastbound.block.TarihBasamakBlock;
import dev.pastbound.block.TarihDugmeBlock;
import dev.pastbound.block.TarihKapakBlock;
import dev.pastbound.block.TarihKapiBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ModId.MOD_ID);

    public static final DeferredBlock<EchoArchiveBlock> ECHO_ARCHIVE = BLOCKS.register("echo_archive", kimlik -> new EchoArchiveBlock(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, kimlik)).mapColor(MapColor.COLOR_PURPLE).sound(SoundType.AMETHYST).destroyTime(2.5F).explosionResistance(6.0F).lightLevel(durum -> 5)));
    public static final DeferredBlock<AncientStorageBlock> ANCIENT_STORAGE = BLOCKS.register("ancient_storage", kimlik -> new AncientStorageBlock(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, kimlik)).mapColor(MapColor.COLOR_BROWN).sound(SoundType.WOOD).destroyTime(3.0F).explosionResistance(6.0F)));
    public static final DeferredBlock<ResonancePillarBlock> RESONANCE_PILLAR = BLOCKS.register("resonance_pillar", kimlik -> new ResonancePillarBlock(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, kimlik)).mapColor(MapColor.COLOR_BLUE).sound(SoundType.COPPER).destroyTime(3.0F).explosionResistance(7.0F).lightLevel(durum -> durum.getValue(ResonancePillarBlock.CHARGED) ? 15 : 4)));
    public static final DeferredBlock<Block> NETHER_WART_DOOR = BLOCKS.register("nether_wart_door", kimlik -> new TarihKapiBlock(BlockSetType.CRIMSON, ozellik(Blocks.CRIMSON_DOOR, kimlik)));
    public static final DeferredBlock<Block> NETHER_WART_TRAPDOOR = BLOCKS.register("nether_wart_trapdoor", kimlik -> new TarihKapakBlock(BlockSetType.CRIMSON, ozellik(Blocks.CRIMSON_TRAPDOOR, kimlik)));
    public static final DeferredBlock<Block> STEEL_ORE = BLOCKS.register("steel_ore", kimlik -> new Block(ozellik(Blocks.IRON_ORE, kimlik)));
    public static final DeferredBlock<Block> DEEPSLATE_STEEL_ORE = BLOCKS.register("deepslate_steel_ore", kimlik -> new Block(ozellik(Blocks.DEEPSLATE_IRON_ORE, kimlik)));
    public static final DeferredBlock<Block> STEEL_BLOCK = BLOCKS.register("steel_block", kimlik -> new Block(ozellik(Blocks.IRON_BLOCK, kimlik)));
    public static final DeferredBlock<Block> HISTORICAL_FORGE = BLOCKS.register("historical_forge", kimlik -> new Block(ozellik(Blocks.IRON_BLOCK, kimlik).sound(SoundType.NETHERITE_BLOCK).destroyTime(4.0F).explosionResistance(6.0F)));
    public static final DeferredBlock<Block> TIME_STONE_ORE = BLOCKS.register("time_stone_ore", kimlik -> new Block(ozellik(Blocks.DEEPSLATE_IRON_ORE, kimlik)));
    public static final DeferredBlock<Block> NETHER_TIME_STONE_ORE = BLOCKS.register("nether_time_stone_ore", kimlik -> new Block(ozellik(Blocks.NETHER_QUARTZ_ORE, kimlik)));
    public static final DeferredBlock<Block> CHRONICLE_ORE = BLOCKS.register("chronicle_ore", kimlik -> new Block(ozellik(Blocks.DEEPSLATE_IRON_ORE, kimlik)));
    public static final DeferredBlock<Block> ASH_CHRONICLE_ORE = BLOCKS.register("ash_chronicle_ore", kimlik -> new Block(ozellik(Blocks.NETHER_QUARTZ_ORE, kimlik)));
    public static final DeferredBlock<Block> END_ECHO_ORE = BLOCKS.register("end_echo_ore", kimlik -> new Block(ozellik(Blocks.END_STONE, kimlik)));
    public static final DeferredBlock<Block> VOID_CHRONICLE_ORE = BLOCKS.register("void_chronicle_ore", kimlik -> new Block(ozellik(Blocks.END_STONE, kimlik)));
    public static final DeferredBlock<Block> ERVANIUM_ORE = BLOCKS.register("ervanium_ore", kimlik -> new Block(ozellik(Blocks.END_STONE, kimlik).destroyTime(8.0F).explosionResistance(9.0F).lightLevel(durum -> 3)));
    public static final DeferredBlock<Block> EGYPTIAN_SANDSTONE = BLOCKS.register("egyptian_sandstone", kimlik -> new Block(ozellik(Blocks.SANDSTONE, kimlik).destroyTime(0.8F)));
    public static final DeferredBlock<Block> EGYPTIAN_GOLD_INLAY = BLOCKS.register("egyptian_gold_inlay", kimlik -> new Block(ozellik(Blocks.GOLD_BLOCK, kimlik).destroyTime(3.0F).explosionResistance(6.0F).lightLevel(durum -> 4)));
    public static final DeferredBlock<Block> EGYPTIAN_OBELISK = BLOCKS.register("egyptian_obelisk", kimlik -> new Block(ozellik(Blocks.SMOOTH_SANDSTONE, kimlik).destroyTime(1.0F).explosionResistance(5.0F)));

    public static final DeferredBlock<Block> URUK_CEDAR_LOG = BLOCKS.register("uruk_cedar_log", kimlik -> new RotatedPillarBlock(ozellik(Blocks.OAK_LOG, kimlik)));
    public static final DeferredBlock<Block> URUK_CEDAR_STRIPPED_LOG = BLOCKS.register("uruk_cedar_stripped_log", kimlik -> new RotatedPillarBlock(ozellik(Blocks.STRIPPED_OAK_LOG, kimlik)));
    public static final DeferredBlock<Block> URUK_CEDAR_WOOD = BLOCKS.register("uruk_cedar_wood", kimlik -> new RotatedPillarBlock(ozellik(Blocks.OAK_WOOD, kimlik)));
    public static final DeferredBlock<Block> URUK_CEDAR_STRIPPED_WOOD = BLOCKS.register("uruk_cedar_stripped_wood", kimlik -> new RotatedPillarBlock(ozellik(Blocks.STRIPPED_OAK_WOOD, kimlik)));
    public static final DeferredBlock<Block> URUK_CEDAR_LEAVES = BLOCKS.register("uruk_cedar_leaves", kimlik -> new Block(ozellik(Blocks.OAK_LEAVES, kimlik).noOcclusion()));
    public static final DeferredBlock<Block> URUK_CEDAR_SAPLING = BLOCKS.register("uruk_cedar_sapling", kimlik -> new Block(ozellik(Blocks.OAK_SAPLING, kimlik).noOcclusion().sound(SoundType.GRASS)));
    public static final DeferredBlock<Block> URUK_CEDAR_PLANKS = BLOCKS.register("uruk_cedar_planks", kimlik -> new Block(ozellik(Blocks.OAK_PLANKS, kimlik)));
    public static final DeferredBlock<Block> URUK_CEDAR_SLAB = BLOCKS.register("uruk_cedar_slab", kimlik -> new SlabBlock(ozellik(Blocks.OAK_SLAB, kimlik)));
    public static final DeferredBlock<Block> URUK_CEDAR_STAIRS = BLOCKS.register("uruk_cedar_stairs", kimlik -> new TarihBasamakBlock(ModBlocks.URUK_CEDAR_PLANKS.get().defaultBlockState(), ozellik(Blocks.OAK_STAIRS, kimlik)));
    public static final DeferredBlock<Block> URUK_CEDAR_DOOR = BLOCKS.register("uruk_cedar_door", kimlik -> new TarihKapiBlock(BlockSetType.OAK, ozellik(Blocks.OAK_DOOR, kimlik)));
    public static final DeferredBlock<Block> URUK_CEDAR_TRAPDOOR = BLOCKS.register("uruk_cedar_trapdoor", kimlik -> new TarihKapakBlock(BlockSetType.OAK, ozellik(Blocks.OAK_TRAPDOOR, kimlik)));
    public static final DeferredBlock<Block> URUK_CEDAR_FENCE = BLOCKS.register("uruk_cedar_fence", kimlik -> new FenceBlock(ozellik(Blocks.OAK_FENCE, kimlik)));
    public static final DeferredBlock<Block> URUK_CEDAR_FENCE_GATE = BLOCKS.register("uruk_cedar_fence_gate", kimlik -> new FenceGateBlock(WoodType.OAK, ozellik(Blocks.OAK_FENCE_GATE, kimlik)));
    public static final DeferredBlock<Block> URUK_CEDAR_PRESSURE_PLATE = BLOCKS.register("uruk_cedar_pressure_plate", kimlik -> new TarihBasincPlakaBlock(BlockSetType.OAK, ozellik(Blocks.OAK_PRESSURE_PLATE, kimlik)));
    public static final DeferredBlock<Block> URUK_CEDAR_BUTTON = BLOCKS.register("uruk_cedar_button", kimlik -> new TarihDugmeBlock(BlockSetType.OAK, 30, ozellik(Blocks.OAK_BUTTON, kimlik)));

    public static final DeferredBlock<Block> CHINAMPA_CYPRESS_LOG = BLOCKS.register("chinampa_cypress_log", kimlik -> new RotatedPillarBlock(ozellik(Blocks.BIRCH_LOG, kimlik)));
    public static final DeferredBlock<Block> CHINAMPA_CYPRESS_STRIPPED_LOG = BLOCKS.register("chinampa_cypress_stripped_log", kimlik -> new RotatedPillarBlock(ozellik(Blocks.STRIPPED_BIRCH_LOG, kimlik)));
    public static final DeferredBlock<Block> CHINAMPA_CYPRESS_WOOD = BLOCKS.register("chinampa_cypress_wood", kimlik -> new RotatedPillarBlock(ozellik(Blocks.BIRCH_WOOD, kimlik)));
    public static final DeferredBlock<Block> CHINAMPA_CYPRESS_STRIPPED_WOOD = BLOCKS.register("chinampa_cypress_stripped_wood", kimlik -> new RotatedPillarBlock(ozellik(Blocks.STRIPPED_BIRCH_WOOD, kimlik)));
    public static final DeferredBlock<Block> CHINAMPA_CYPRESS_LEAVES = BLOCKS.register("chinampa_cypress_leaves", kimlik -> new Block(ozellik(Blocks.BIRCH_LEAVES, kimlik).noOcclusion()));
    public static final DeferredBlock<Block> CHINAMPA_CYPRESS_SAPLING = BLOCKS.register("chinampa_cypress_sapling", kimlik -> new Block(ozellik(Blocks.BIRCH_SAPLING, kimlik).noOcclusion().sound(SoundType.GRASS)));
    public static final DeferredBlock<Block> CHINAMPA_CYPRESS_PLANKS = BLOCKS.register("chinampa_cypress_planks", kimlik -> new Block(ozellik(Blocks.BIRCH_PLANKS, kimlik)));
    public static final DeferredBlock<Block> CHINAMPA_CYPRESS_SLAB = BLOCKS.register("chinampa_cypress_slab", kimlik -> new SlabBlock(ozellik(Blocks.BIRCH_SLAB, kimlik)));
    public static final DeferredBlock<Block> CHINAMPA_CYPRESS_STAIRS = BLOCKS.register("chinampa_cypress_stairs", kimlik -> new TarihBasamakBlock(ModBlocks.CHINAMPA_CYPRESS_PLANKS.get().defaultBlockState(), ozellik(Blocks.BIRCH_STAIRS, kimlik)));
    public static final DeferredBlock<Block> CHINAMPA_CYPRESS_DOOR = BLOCKS.register("chinampa_cypress_door", kimlik -> new TarihKapiBlock(BlockSetType.OAK, ozellik(Blocks.BIRCH_DOOR, kimlik)));
    public static final DeferredBlock<Block> CHINAMPA_CYPRESS_TRAPDOOR = BLOCKS.register("chinampa_cypress_trapdoor", kimlik -> new TarihKapakBlock(BlockSetType.OAK, ozellik(Blocks.BIRCH_TRAPDOOR, kimlik)));
    public static final DeferredBlock<Block> CHINAMPA_CYPRESS_FENCE = BLOCKS.register("chinampa_cypress_fence", kimlik -> new FenceBlock(ozellik(Blocks.BIRCH_FENCE, kimlik)));
    public static final DeferredBlock<Block> CHINAMPA_CYPRESS_FENCE_GATE = BLOCKS.register("chinampa_cypress_fence_gate", kimlik -> new FenceGateBlock(WoodType.BIRCH, ozellik(Blocks.BIRCH_FENCE_GATE, kimlik)));
    public static final DeferredBlock<Block> CHINAMPA_CYPRESS_PRESSURE_PLATE = BLOCKS.register("chinampa_cypress_pressure_plate", kimlik -> new TarihBasincPlakaBlock(BlockSetType.OAK, ozellik(Blocks.BIRCH_PRESSURE_PLATE, kimlik)));
    public static final DeferredBlock<Block> CHINAMPA_CYPRESS_BUTTON = BLOCKS.register("chinampa_cypress_button", kimlik -> new TarihDugmeBlock(BlockSetType.OAK, 30, ozellik(Blocks.BIRCH_BUTTON, kimlik)));

    private ModBlocks() {
    }

    private static BlockBehaviour.Properties ozellik(BlockBehaviour temel, Identifier kimlik) {
        return BlockBehaviour.Properties.ofFullCopy(temel).setId(ResourceKey.create(Registries.BLOCK, kimlik));
    }
}
