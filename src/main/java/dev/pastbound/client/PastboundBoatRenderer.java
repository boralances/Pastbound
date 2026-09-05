package dev.pastbound.client;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.pastbound.client.model.PastboundBoatModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.AbstractBoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.BoatRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Unit;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public final class PastboundBoatRenderer extends AbstractBoatRenderer {
    private final Model.Simple waterPatchModel;
    private final EntityModel<BoatRenderState> model;

    public PastboundBoatRenderer(EntityRendererProvider.Context context, ModelLayerLocation modelLayer, String textureName, PastboundBoatModel.Theme theme) {
        super(context, Identifier.fromNamespaceAndPath("pastbound", "textures/entity/" + textureName + ".png"));
        this.waterPatchModel = new Model.Simple(context.bakeLayer(ModelLayers.BOAT_WATER_PATCH), t -> RenderTypes.waterMask());
        this.model = new PastboundBoatModel(context.bakeLayer(modelLayer));
    }

    @Override
    protected EntityModel<BoatRenderState> model() {
        return this.model;
    }

    @Override
    protected void submitTypeAdditions(BoatRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords) {
        if (!state.isUnderWater) {
            submitNodeCollector.submitModel(
                    this.waterPatchModel,
                    Unit.INSTANCE,
                    poseStack,
                    this.texture,
                    lightCoords,
                    OverlayTexture.NO_OVERLAY,
                    state.outlineColor,
                    null);
        }
    }
}
