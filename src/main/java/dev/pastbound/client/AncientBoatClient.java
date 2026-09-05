package dev.pastbound.client;

import dev.pastbound.registry.ModEntityTypes;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public final class AncientBoatClient {
    public static final ModelLayerLocation ANCIENT_BOAT_LAYER = new ModelLayerLocation(
            Identifier.fromNamespaceAndPath("pastbound", "ancient_boat"), "main");

    private AncientBoatClient() {
    }

    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ANCIENT_BOAT_LAYER, BoatModel::createBoatModel);
    }

    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityTypes.ANCIENT_BOAT.get(), context -> new BoatRenderer(context, ANCIENT_BOAT_LAYER));
    }
}
