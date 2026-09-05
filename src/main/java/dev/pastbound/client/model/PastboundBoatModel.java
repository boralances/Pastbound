package dev.pastbound.client.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.object.boat.BoatModel;

public final class PastboundBoatModel extends BoatModel {
    public enum Theme {
        ANCIENT,
        EGYPTIAN,
        GREEK,
        VIKING
    }

    public PastboundBoatModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createAncientModel() {
        return createModel(Theme.ANCIENT);
    }

    public static LayerDefinition createEgyptianModel() {
        return createModel(Theme.EGYPTIAN);
    }

    public static LayerDefinition createGreekModel() {
        return createModel(Theme.GREEK);
    }

    public static LayerDefinition createVikingModel() {
        return createModel(Theme.VIKING);
    }

    private static LayerDefinition createModel(Theme theme) {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        addHull(root);
        addPaddles(root);
        switch (theme) {
            case ANCIENT -> addAncientDetails(root);
            case EGYPTIAN -> addEgyptianDetails(root);
            case GREEK -> addGreekDetails(root);
            case VIKING -> addVikingDetails(root);
        }
        return LayerDefinition.create(mesh, 128, 64);
    }

    private static void addHull(PartDefinition root) {
        root.addOrReplaceChild(
                "bottom",
                CubeListBuilder.create().texOffs(0, 0).addBox(-14.0F, -9.0F, -3.0F, 28.0F, 16.0F, 3.0F),
                PartPose.offsetAndRotation(0.0F, 3.0F, 1.0F, (float) (Math.PI / 2), 0.0F, 0.0F));
        root.addOrReplaceChild(
                "back",
                CubeListBuilder.create().texOffs(0, 19).addBox(-13.0F, -7.0F, -1.0F, 18.0F, 6.0F, 2.0F),
                PartPose.offsetAndRotation(-15.0F, 4.0F, 4.0F, 0.0F, (float) (Math.PI * 3.0 / 2.0), 0.0F));
        root.addOrReplaceChild(
                "front",
                CubeListBuilder.create().texOffs(0, 27).addBox(-8.0F, -7.0F, -1.0F, 16.0F, 6.0F, 2.0F),
                PartPose.offsetAndRotation(15.0F, 4.0F, 0.0F, 0.0F, (float) (Math.PI / 2), 0.0F));
        root.addOrReplaceChild(
                "right",
                CubeListBuilder.create().texOffs(0, 35).addBox(-14.0F, -7.0F, -1.0F, 28.0F, 6.0F, 2.0F),
                PartPose.offsetAndRotation(0.0F, 4.0F, -9.0F, 0.0F, (float) Math.PI, 0.0F));
        root.addOrReplaceChild(
                "left",
                CubeListBuilder.create().texOffs(0, 43).addBox(-14.0F, -7.0F, -1.0F, 28.0F, 6.0F, 2.0F),
                PartPose.offset(0.0F, 4.0F, 9.0F));
    }

    private static void addPaddles(PartDefinition root) {
        root.addOrReplaceChild(
                "left_paddle",
                CubeListBuilder.create().texOffs(62, 0)
                        .addBox(-1.0F, 0.0F, -5.0F, 2.0F, 2.0F, 18.0F)
                        .addBox(-1.001F, -3.0F, 8.0F, 1.0F, 6.0F, 7.0F),
                PartPose.offsetAndRotation(3.0F, -5.0F, 9.0F, 0.0F, 0.0F, (float) (Math.PI / 16)));
        root.addOrReplaceChild(
                "right_paddle",
                CubeListBuilder.create().texOffs(62, 20)
                        .addBox(-1.0F, 0.0F, -5.0F, 2.0F, 2.0F, 18.0F)
                        .addBox(0.001F, -3.0F, 8.0F, 1.0F, 6.0F, 7.0F),
                PartPose.offsetAndRotation(3.0F, -5.0F, -9.0F, 0.0F, (float) Math.PI, (float) (Math.PI / 16)));
    }

    private static void addAncientDetails(PartDefinition root) {
        root.addOrReplaceChild("keel", CubeListBuilder.create().texOffs(96, 0).addBox(-10.0F, 0.0F, -2.0F, 20.0F, 3.0F, 4.0F), PartPose.offset(0.0F, 8.0F, 1.0F));
        root.addOrReplaceChild("stern_post", CubeListBuilder.create().texOffs(96, 8).addBox(-2.0F, -9.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(-14.0F, 1.0F, 4.0F));
    }

    private static void addEgyptianDetails(PartDefinition root) {
        root.addOrReplaceChild("sun_canopy", CubeListBuilder.create().texOffs(88, 0).addBox(-8.0F, -2.0F, -5.0F, 16.0F, 2.0F, 10.0F), PartPose.offset(0.0F, -4.0F, 1.0F));
        root.addOrReplaceChild("sun_disc", CubeListBuilder.create().texOffs(88, 14).addBox(-3.0F, -3.0F, -1.0F, 6.0F, 6.0F, 2.0F), PartPose.offset(0.0F, -5.0F, 1.0F));
        root.addOrReplaceChild("papyrus_bow", CubeListBuilder.create().texOffs(104, 14).addBox(-2.0F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(15.0F, 4.0F, 1.0F));
    }

    private static void addGreekDetails(PartDefinition root) {
        root.addOrReplaceChild("mast", CubeListBuilder.create().texOffs(88, 0).addBox(-1.0F, -19.0F, -1.0F, 2.0F, 20.0F, 2.0F), PartPose.offset(0.0F, -1.0F, 1.0F));
        root.addOrReplaceChild("sail", CubeListBuilder.create().texOffs(96, 0).addBox(0.0F, -16.0F, 0.0F, 10.0F, 14.0F, 1.0F), PartPose.offset(1.0F, -2.0F, 1.0F));
        root.addOrReplaceChild("ram", CubeListBuilder.create().texOffs(88, 24).addBox(0.0F, -4.0F, -2.0F, 8.0F, 4.0F, 4.0F), PartPose.offset(14.0F, 2.0F, 1.0F));
    }

    private static void addVikingDetails(PartDefinition root) {
        root.addOrReplaceChild("dragon_head", CubeListBuilder.create().texOffs(88, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F), PartPose.offset(16.0F, 0.0F, 1.0F));
        root.addOrReplaceChild("horn_left", CubeListBuilder.create().texOffs(104, 0).addBox(-1.0F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F), PartPose.offset(18.0F, -4.0F, 4.0F));
        root.addOrReplaceChild("horn_right", CubeListBuilder.create().texOffs(104, 8).addBox(-1.0F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F), PartPose.offset(18.0F, -4.0F, -2.0F));
        root.addOrReplaceChild("shield_rail", CubeListBuilder.create().texOffs(88, 32).addBox(-10.0F, -5.0F, -1.0F, 20.0F, 2.0F, 2.0F), PartPose.offset(-1.0F, 1.0F, -10.0F));
    }
}
