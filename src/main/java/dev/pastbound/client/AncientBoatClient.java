package dev.pastbound.client;

import dev.pastbound.registry.ModEntityTypes;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.object.boat.BoatModel;
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
        event.registerLayerDefinition(ANCIENT_BOAT_LAYER, BoatModel::createBoatModel);
        event.registerLayerDefinition(EGYPTIAN_BOAT_LAYER, BoatModel::createBoatModel);
        event.registerLayerDefinition(GREEK_BOAT_LAYER, BoatModel::createBoatModel);
        event.registerLayerDefinition(VIKING_BOAT_LAYER, BoatModel::createBoatModel);
    }

    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityTypes.ANCIENT_BOAT.get(), context -> new BoatRenderer(context, ANCIENT_BOAT_LAYER));
        event.registerEntityRenderer(ModEntityTypes.EGYPTIAN_BOAT.get(), context -> new BoatRenderer(context, EGYPTIAN_BOAT_LAYER));
        event.registerEntityRenderer(ModEntityTypes.GREEK_BOAT.get(), context -> new BoatRenderer(context, GREEK_BOAT_LAYER));
        event.registerEntityRenderer(ModEntityTypes.VIKING_BOAT.get(), context -> new BoatRenderer(context, VIKING_BOAT_LAYER));
    }
}
