package dev.pastbound.registry;

import java.util.ArrayList;
import java.util.List;

import dev.pastbound.ModId;
import dev.pastbound.item.EchoShardItem;
import dev.pastbound.item.FirinCubuguItem;
import dev.pastbound.item.CraftingTableCubuguItem;
import dev.pastbound.item.MemoryLensItem;
import dev.pastbound.item.KronikPusulasiItem;
import dev.pastbound.item.ZamanMakinesiItem;
import dev.pastbound.relic.RelikItem;
import dev.pastbound.relic.RelikTanimi;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ModId.MOD_ID);

    public static final DeferredItem<BlockItem> ECHO_ARCHIVE = ITEMS.registerSimpleBlockItem("echo_archive", ModBlocks.ECHO_ARCHIVE);
    public static final DeferredItem<BlockItem> ANCIENT_STORAGE = ITEMS.registerSimpleBlockItem("ancient_storage", ModBlocks.ANCIENT_STORAGE);
    public static final DeferredItem<BlockItem> RESONANCE_PILLAR = ITEMS.registerSimpleBlockItem("resonance_pillar", ModBlocks.RESONANCE_PILLAR);
    public static final DeferredItem<BlockItem> NETHER_WART_DOOR = ITEMS.registerSimpleBlockItem("nether_wart_door", ModBlocks.NETHER_WART_DOOR);
    public static final DeferredItem<BlockItem> NETHER_WART_TRAPDOOR = ITEMS.registerSimpleBlockItem("nether_wart_trapdoor", ModBlocks.NETHER_WART_TRAPDOOR);

    public static final DeferredItem<EchoShardItem> ECHO_SHARD = ITEMS.register("echo_shard", registryName -> new EchoShardItem(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, registryName)).stacksTo(16)));
    public static final DeferredItem<MemoryLensItem> MEMORY_LENS = ITEMS.register("memory_lens", registryName -> new MemoryLensItem(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, registryName)).stacksTo(1).durability(128)));
    public static final DeferredItem<Item> CHRONICLE_SCRAP = ITEMS.register("chronicle_scrap", registryName -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, registryName)).stacksTo(32)));
    public static final DeferredItem<Item> HISTORY_INK = ITEMS.register("history_ink", registryName -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, registryName)).stacksTo(16)));
    public static final DeferredItem<Item> TIME_STONE = ITEMS.register("time_stone", registryName -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, registryName)).stacksTo(16)));
    public static final DeferredItem<Item> ECHO_SEAL = ITEMS.register("echo_seal", registryName -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, registryName)).stacksTo(16)));
    public static final DeferredItem<KronikPusulasiItem> CHRONICLE_COMPASS = ITEMS.register("chronicle_compass", registryName -> new KronikPusulasiItem(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, registryName)).stacksTo(1)));
    public static final DeferredItem<ZamanMakinesiItem> ZAMAN_MAKINESI = ITEMS.register("zaman_makinesi", registryName -> new ZamanMakinesiItem(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, registryName)).stacksTo(1).fireResistant()));
    public static final DeferredItem<FirinCubuguItem> FIRIN_CUBUGU = ITEMS.register("firin_cubugu", registryName -> new FirinCubuguItem(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, registryName)).stacksTo(1)));
    public static final DeferredItem<FirinCubuguItem> GELISTIRILMIS_FIRIN_CUBUGU = ITEMS.register("gelistirilmis_firin_cubugu", registryName -> new FirinCubuguItem(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, registryName)).stacksTo(1).fireResistant()));
    public static final DeferredItem<CraftingTableCubuguItem> CRAFTING_TABLE_CUBUGU = ITEMS.register("crafting_table_cubugu", registryName -> new CraftingTableCubuguItem(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, registryName)).stacksTo(1)));
    public static final DeferredItem<BlockItem> STEEL_ORE = ITEMS.registerSimpleBlockItem("steel_ore", ModBlocks.STEEL_ORE);
    public static final DeferredItem<BlockItem> DEEPSLATE_STEEL_ORE = ITEMS.registerSimpleBlockItem("deepslate_steel_ore", ModBlocks.DEEPSLATE_STEEL_ORE);
    public static final DeferredItem<BlockItem> STEEL_BLOCK = ITEMS.registerSimpleBlockItem("steel_block", ModBlocks.STEEL_BLOCK);
    public static final DeferredItem<BlockItem> HISTORICAL_FORGE = ITEMS.registerSimpleBlockItem("historical_forge", ModBlocks.HISTORICAL_FORGE);
    public static final DeferredItem<Item> RAW_STEEL = ITEMS.register("raw_steel", registryName -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, registryName)).stacksTo(64)));
    public static final DeferredItem<Item> STEEL_INGOT = ITEMS.register("steel_ingot", registryName -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, registryName)).stacksTo(64)));
    public static final DeferredItem<Item> STEEL_PLATE = ITEMS.register("steel_plate", registryName -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, registryName)).stacksTo(64)));
    public static final DeferredItem<BlockItem> TIME_STONE_ORE = ITEMS.registerSimpleBlockItem("time_stone_ore", ModBlocks.TIME_STONE_ORE);
    public static final DeferredItem<BlockItem> NETHER_TIME_STONE_ORE = ITEMS.registerSimpleBlockItem("nether_time_stone_ore", ModBlocks.NETHER_TIME_STONE_ORE);
    public static final DeferredItem<BlockItem> CHRONICLE_ORE = ITEMS.registerSimpleBlockItem("chronicle_ore", ModBlocks.CHRONICLE_ORE);
    public static final DeferredItem<BlockItem> ASH_CHRONICLE_ORE = ITEMS.registerSimpleBlockItem("ash_chronicle_ore", ModBlocks.ASH_CHRONICLE_ORE);
    public static final DeferredItem<BlockItem> END_ECHO_ORE = ITEMS.registerSimpleBlockItem("end_echo_ore", ModBlocks.END_ECHO_ORE);
    public static final DeferredItem<BlockItem> VOID_CHRONICLE_ORE = ITEMS.registerSimpleBlockItem("void_chronicle_ore", ModBlocks.VOID_CHRONICLE_ORE);

    public static final DeferredItem<BlockItem> URUK_CEDAR_LOG = ITEMS.registerSimpleBlockItem("uruk_cedar_log", ModBlocks.URUK_CEDAR_LOG);
    public static final DeferredItem<BlockItem> URUK_CEDAR_STRIPPED_LOG = ITEMS.registerSimpleBlockItem("uruk_cedar_stripped_log", ModBlocks.URUK_CEDAR_STRIPPED_LOG);
    public static final DeferredItem<BlockItem> URUK_CEDAR_WOOD = ITEMS.registerSimpleBlockItem("uruk_cedar_wood", ModBlocks.URUK_CEDAR_WOOD);
    public static final DeferredItem<BlockItem> URUK_CEDAR_STRIPPED_WOOD = ITEMS.registerSimpleBlockItem("uruk_cedar_stripped_wood", ModBlocks.URUK_CEDAR_STRIPPED_WOOD);
    public static final DeferredItem<BlockItem> URUK_CEDAR_LEAVES = ITEMS.registerSimpleBlockItem("uruk_cedar_leaves", ModBlocks.URUK_CEDAR_LEAVES);
    public static final DeferredItem<BlockItem> URUK_CEDAR_SAPLING = ITEMS.registerSimpleBlockItem("uruk_cedar_sapling", ModBlocks.URUK_CEDAR_SAPLING);
    public static final DeferredItem<BlockItem> URUK_CEDAR_PLANKS = ITEMS.registerSimpleBlockItem("uruk_cedar_planks", ModBlocks.URUK_CEDAR_PLANKS);
    public static final DeferredItem<BlockItem> URUK_CEDAR_SLAB = ITEMS.registerSimpleBlockItem("uruk_cedar_slab", ModBlocks.URUK_CEDAR_SLAB);
    public static final DeferredItem<BlockItem> URUK_CEDAR_STAIRS = ITEMS.registerSimpleBlockItem("uruk_cedar_stairs", ModBlocks.URUK_CEDAR_STAIRS);
    public static final DeferredItem<BlockItem> URUK_CEDAR_DOOR = ITEMS.registerSimpleBlockItem("uruk_cedar_door", ModBlocks.URUK_CEDAR_DOOR);
    public static final DeferredItem<BlockItem> URUK_CEDAR_TRAPDOOR = ITEMS.registerSimpleBlockItem("uruk_cedar_trapdoor", ModBlocks.URUK_CEDAR_TRAPDOOR);
    public static final DeferredItem<BlockItem> URUK_CEDAR_FENCE = ITEMS.registerSimpleBlockItem("uruk_cedar_fence", ModBlocks.URUK_CEDAR_FENCE);
    public static final DeferredItem<BlockItem> URUK_CEDAR_FENCE_GATE = ITEMS.registerSimpleBlockItem("uruk_cedar_fence_gate", ModBlocks.URUK_CEDAR_FENCE_GATE);
    public static final DeferredItem<BlockItem> URUK_CEDAR_PRESSURE_PLATE = ITEMS.registerSimpleBlockItem("uruk_cedar_pressure_plate", ModBlocks.URUK_CEDAR_PRESSURE_PLATE);
    public static final DeferredItem<BlockItem> URUK_CEDAR_BUTTON = ITEMS.registerSimpleBlockItem("uruk_cedar_button", ModBlocks.URUK_CEDAR_BUTTON);

    public static final DeferredItem<BlockItem> CHINAMPA_CYPRESS_LOG = ITEMS.registerSimpleBlockItem("chinampa_cypress_log", ModBlocks.CHINAMPA_CYPRESS_LOG);
    public static final DeferredItem<BlockItem> CHINAMPA_CYPRESS_STRIPPED_LOG = ITEMS.registerSimpleBlockItem("chinampa_cypress_stripped_log", ModBlocks.CHINAMPA_CYPRESS_STRIPPED_LOG);
    public static final DeferredItem<BlockItem> CHINAMPA_CYPRESS_WOOD = ITEMS.registerSimpleBlockItem("chinampa_cypress_wood", ModBlocks.CHINAMPA_CYPRESS_WOOD);
    public static final DeferredItem<BlockItem> CHINAMPA_CYPRESS_STRIPPED_WOOD = ITEMS.registerSimpleBlockItem("chinampa_cypress_stripped_wood", ModBlocks.CHINAMPA_CYPRESS_STRIPPED_WOOD);
    public static final DeferredItem<BlockItem> CHINAMPA_CYPRESS_LEAVES = ITEMS.registerSimpleBlockItem("chinampa_cypress_leaves", ModBlocks.CHINAMPA_CYPRESS_LEAVES);
    public static final DeferredItem<BlockItem> CHINAMPA_CYPRESS_SAPLING = ITEMS.registerSimpleBlockItem("chinampa_cypress_sapling", ModBlocks.CHINAMPA_CYPRESS_SAPLING);
    public static final DeferredItem<BlockItem> CHINAMPA_CYPRESS_PLANKS = ITEMS.registerSimpleBlockItem("chinampa_cypress_planks", ModBlocks.CHINAMPA_CYPRESS_PLANKS);
    public static final DeferredItem<BlockItem> CHINAMPA_CYPRESS_SLAB = ITEMS.registerSimpleBlockItem("chinampa_cypress_slab", ModBlocks.CHINAMPA_CYPRESS_SLAB);
    public static final DeferredItem<BlockItem> CHINAMPA_CYPRESS_STAIRS = ITEMS.registerSimpleBlockItem("chinampa_cypress_stairs", ModBlocks.CHINAMPA_CYPRESS_STAIRS);
    public static final DeferredItem<BlockItem> CHINAMPA_CYPRESS_DOOR = ITEMS.registerSimpleBlockItem("chinampa_cypress_door", ModBlocks.CHINAMPA_CYPRESS_DOOR);
    public static final DeferredItem<BlockItem> CHINAMPA_CYPRESS_TRAPDOOR = ITEMS.registerSimpleBlockItem("chinampa_cypress_trapdoor", ModBlocks.CHINAMPA_CYPRESS_TRAPDOOR);
    public static final DeferredItem<BlockItem> CHINAMPA_CYPRESS_FENCE = ITEMS.registerSimpleBlockItem("chinampa_cypress_fence", ModBlocks.CHINAMPA_CYPRESS_FENCE);
    public static final DeferredItem<BlockItem> CHINAMPA_CYPRESS_FENCE_GATE = ITEMS.registerSimpleBlockItem("chinampa_cypress_fence_gate", ModBlocks.CHINAMPA_CYPRESS_FENCE_GATE);
    public static final DeferredItem<BlockItem> CHINAMPA_CYPRESS_PRESSURE_PLATE = ITEMS.registerSimpleBlockItem("chinampa_cypress_pressure_plate", ModBlocks.CHINAMPA_CYPRESS_PRESSURE_PLATE);
    public static final DeferredItem<BlockItem> CHINAMPA_CYPRESS_BUTTON = ITEMS.registerSimpleBlockItem("chinampa_cypress_button", ModBlocks.CHINAMPA_CYPRESS_BUTTON);

    public static final List<DeferredItem<BlockItem>> TARIHI_AHSAP_ITEMLERI = List.of(
            URUK_CEDAR_LOG, URUK_CEDAR_STRIPPED_LOG, URUK_CEDAR_WOOD, URUK_CEDAR_STRIPPED_WOOD, URUK_CEDAR_LEAVES, URUK_CEDAR_SAPLING, URUK_CEDAR_PLANKS, URUK_CEDAR_SLAB, URUK_CEDAR_STAIRS, URUK_CEDAR_FENCE, URUK_CEDAR_FENCE_GATE, URUK_CEDAR_PRESSURE_PLATE, URUK_CEDAR_BUTTON,
            CHINAMPA_CYPRESS_LOG, CHINAMPA_CYPRESS_STRIPPED_LOG, CHINAMPA_CYPRESS_WOOD, CHINAMPA_CYPRESS_STRIPPED_WOOD, CHINAMPA_CYPRESS_LEAVES, CHINAMPA_CYPRESS_SAPLING, CHINAMPA_CYPRESS_PLANKS, CHINAMPA_CYPRESS_SLAB, CHINAMPA_CYPRESS_STAIRS, CHINAMPA_CYPRESS_FENCE, CHINAMPA_CYPRESS_FENCE_GATE, CHINAMPA_CYPRESS_PRESSURE_PLATE, CHINAMPA_CYPRESS_BUTTON);

    public static final List<DeferredItem<RelikItem>> RELIKLER = new ArrayList<>();

    static {
        for (RelikTanimi tanim : RelikTanimi.values()) {
            RELIKLER.add(ITEMS.register(tanim.kimlik(), registryName -> new RelikItem(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, registryName)).stacksTo(1).fireResistant(), tanim)));
        }
    }

    private ModItems() {
    }
}
