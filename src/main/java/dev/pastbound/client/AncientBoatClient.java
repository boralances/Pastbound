package dev.pastbound.client;

import dev.pastbound.client.model.PastboundBoatModel;
import dev.pastbound.registry.ModEntityTypes;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public final class AncientBoatClient {
    public static final ModelLayerLocation ANCIENT_BOAT_LAYER = layer("ancient_boat");
    public static final ModelLayerLocation EGYPTIAN_BOAT_LAYER = layer("egyptian_boat");
    public static final ModelLayerLocation GREEK_BOAT_LAYER = layer("greek_boat");
    public static final ModelLayerLocation VIKING_BOAT_LAYER = layer("viking_boat");

    private AncientBoatClient() {
    }

    private static ModelLayerLocation layer(String name) {
        return new ModelLayerLocation(Identifier.fromNamespaceAndPath("pastbound", name), "main");
    }

    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ANCIENT_BOAT_LAYER, PastboundBoatModel::createAncientModel);
        event.registerLayerDefinition(EGYPTIAN_BOAT_LAYER, PastboundBoatModel::createEgyptianModel);
        event.registerLayerDefinition(GREEK_BOAT_LAYER, PastboundBoatModel::createGreekModel);
        event.registerLayerDefinition(VIKING_BOAT_LAYER, PastboundBoatModel::createVikingModel);
    }

    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityTypes.ANCIENT_BOAT.get(), context -> new PastboundBoatRenderer(context, ANCIENT_BOAT_LAYER, "ancient_boat", PastboundBoatModel.Theme.ANCIENT));
        event.registerEntityRenderer(ModEntityTypes.EGYPTIAN_BOAT.get(), context -> new PastboundBoatRenderer(context, EGYPTIAN_BOAT_LAYER, "egyptian_boat", PastboundBoatModel.Theme.EGYPTIAN));
        event.registerEntityRenderer(ModEntityTypes.GREEK_BOAT.get(), context -> new PastboundBoatRenderer(context, GREEK_BOAT_LAYER, "greek_boat", PastboundBoatModel.Theme.GREEK));
        event.registerEntityRenderer(ModEntityTypes.VIKING_BOAT.get(), context -> new PastboundBoatRenderer(context, VIKING_BOAT_LAYER, "viking_boat", PastboundBoatModel.Theme.VIKING));
    }
}
