package dev.pastbound.registry;

import java.util.ArrayList;
import java.util.List;

import dev.pastbound.ModId;
import dev.pastbound.item.EchoShardItem;
import dev.pastbound.item.FirinCubuguItem;
import dev.pastbound.item.MemoryLensItem;
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
    public static final DeferredItem<BlockItem> RESONANCE_PILLAR = ITEMS.registerSimpleBlockItem("resonance_pillar", ModBlocks.RESONANCE_PILLAR);

    public static final DeferredItem<EchoShardItem> ECHO_SHARD = ITEMS.register("echo_shard", registryName -> new EchoShardItem(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, registryName)).stacksTo(16)));
    public static final DeferredItem<MemoryLensItem> MEMORY_LENS = ITEMS.register("memory_lens", registryName -> new MemoryLensItem(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, registryName)).stacksTo(1).durability(128)));
    public static final DeferredItem<Item> CHRONICLE_SCRAP = ITEMS.register("chronicle_scrap", registryName -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, registryName)).stacksTo(32)));
    public static final DeferredItem<Item> HISTORY_INK = ITEMS.register("history_ink", registryName -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, registryName)).stacksTo(16)));
    public static final DeferredItem<Item> TIME_STONE = ITEMS.register("time_stone", registryName -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, registryName)).stacksTo(16)));
    public static final DeferredItem<Item> ECHO_SEAL = ITEMS.register("echo_seal", registryName -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, registryName)).stacksTo(16)));
    public static final DeferredItem<ZamanMakinesiItem> ZAMAN_MAKINESI = ITEMS.register("zaman_makinesi", registryName -> new ZamanMakinesiItem(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, registryName)).stacksTo(1).fireResistant()));
    public static final DeferredItem<FirinCubuguItem> FIRIN_CUBUGU = ITEMS.register("firin_cubugu", registryName -> new FirinCubuguItem(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, registryName)).stacksTo(1)));

    public static final List<DeferredItem<RelikItem>> RELIKLER = new ArrayList<>();

    static {
        for (RelikTanimi tanim : RelikTanimi.values()) {
            RELIKLER.add(ITEMS.register(tanim.kimlik(), registryName -> new RelikItem(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, registryName)).stacksTo(1).fireResistant(), tanim)));
        }
    }

    private ModItems() {
    }
}
