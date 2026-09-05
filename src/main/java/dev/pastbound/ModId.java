package dev.pastbound;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import dev.pastbound.network.PastboundAg;
import dev.pastbound.registry.ModBlockEntities;
import dev.pastbound.registry.ModBlocks;
import dev.pastbound.registry.ModCreativeTabs;
import dev.pastbound.registry.ModEffects;
import dev.pastbound.registry.ModFeatures;
import dev.pastbound.registry.ModEntityTypes;
import dev.pastbound.registry.ModItems;
import dev.pastbound.registry.ModLootModifiers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;

@Mod(ModId.MOD_ID)
public final class ModId {
    public static final String MOD_ID = "pastbound";
    public static final String MOD_NAME = "Pastbound";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ModId(IEventBus modEventBus, ModContainer modContainer) {
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModEntityTypes.ENTITY_TYPES.register(modEventBus);
        ModLootModifiers.SERIALIZERS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITY_TYPES.register(modEventBus);
        ModCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        ModEffects.EFFECTS.register(modEventBus);
        ModFeatures.FEATURES.register(modEventBus);
        modContainer.registerConfig(ModConfig.Type.CLIENT, PastboundConfig.SPEC);
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(dev.pastbound.client.AncientBoatClient::registerLayerDefinitions);
        modEventBus.addListener(dev.pastbound.client.AncientBoatClient::registerRenderers);
        modEventBus.addListener(PastboundAg::kaydet);
        modEventBus.addListener(ModCreativeTabs::addCreativeItems);
        LOGGER.info("{} is binding the present to the past.", MOD_NAME);
    }

    private void commonSetup(FMLCommonSetupEvent olay) {
        LOGGER.info("{} common setup complete with Curios relic memory.", MOD_NAME);
    }
}
