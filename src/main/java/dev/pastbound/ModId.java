package dev.pastbound;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import dev.pastbound.network.PastboundAg;
import dev.pastbound.registry.ModBlockEntities;
import dev.pastbound.registry.ModBlocks;
import dev.pastbound.registry.ModCreativeTabs;
import dev.pastbound.registry.ModEffects;
import dev.pastbound.registry.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(ModId.MOD_ID)
public final class ModId {
    public static final String MOD_ID = "pastbound";
    public static final String MOD_NAME = "Pastbound";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ModId(IEventBus modEventBus, ModContainer modContainer) {
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITY_TYPES.register(modEventBus);
        ModCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        ModEffects.EFFECTS.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(PastboundAg::kaydet);
        modEventBus.addListener(ModCreativeTabs::addCreativeItems);
        LOGGER.info("{} is binding the present to the past.", MOD_NAME);
    }

    private void commonSetup(FMLCommonSetupEvent olay) {
        LOGGER.info("{} common setup complete with Curios relic memory.", MOD_NAME);
    }
}
