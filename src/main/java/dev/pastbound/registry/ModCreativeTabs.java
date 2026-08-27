package dev.pastbound.registry;

import dev.pastbound.ModId;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ModId.MOD_ID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> PASTBOUND_TAB = CREATIVE_MODE_TABS.register("pastbound_tab", () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 7).title(Component.translatable("itemGroup.pastbound")).icon(() -> new ItemStack(ModItems.MEMORY_LENS.get())).build());

    private ModCreativeTabs() {
    }

    public static void addCreativeItems(BuildCreativeModeTabContentsEvent olay) {
        if (olay.getTabKey().equals(PASTBOUND_TAB.getKey())) {
            olay.accept(ModItems.ECHO_SHARD.get());
            olay.accept(ModItems.MEMORY_LENS.get());
            olay.accept(ModItems.ECHO_ARCHIVE.get());
            olay.accept(ModItems.RESONANCE_PILLAR.get());
            olay.accept(ModItems.CHRONICLE_SCRAP.get());
            olay.accept(ModItems.HISTORY_INK.get());
            olay.accept(ModItems.TIME_STONE.get());
            olay.accept(ModItems.ECHO_SEAL.get());
            olay.accept(ModItems.CHRONICLE_COMPASS.get());
            olay.accept(ModItems.ZAMAN_MAKINESI.get());
            olay.accept(ModItems.FIRIN_CUBUGU.get());
            olay.accept(ModItems.STEEL_ORE.get());
            olay.accept(ModItems.DEEPSLATE_STEEL_ORE.get());
            olay.accept(ModItems.RAW_STEEL.get());
            olay.accept(ModItems.STEEL_INGOT.get());
            olay.accept(ModItems.STEEL_PLATE.get());
            olay.accept(ModItems.STEEL_BLOCK.get());
            olay.accept(ModItems.HISTORICAL_FORGE.get());
            olay.accept(ModItems.TIME_STONE_ORE.get());
            olay.accept(ModItems.NETHER_TIME_STONE_ORE.get());
            olay.accept(ModItems.CHRONICLE_ORE.get());
            olay.accept(ModItems.ASH_CHRONICLE_ORE.get());
            olay.accept(ModItems.END_ECHO_ORE.get());
            olay.accept(ModItems.VOID_CHRONICLE_ORE.get());
            ModItems.RELIKLER.forEach(relik -> olay.accept(relik.get()));
            ModItems.TARIHI_AHSAP_ITEMLERI.forEach(item -> olay.accept(item.get()));
        }
    }
}
