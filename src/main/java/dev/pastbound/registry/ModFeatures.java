package dev.pastbound.registry;

import dev.pastbound.ModId;
import dev.pastbound.worldgen.EgyptianRuinFeature;
import dev.pastbound.worldgen.GizaPyramidFeature;
import dev.pastbound.worldgen.GreekTempleFeature;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, ModId.MOD_ID);
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> EGYPTIAN_RUIN = FEATURES.register(
            "egyptian_ruin", EgyptianRuinFeature::new);
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> GIZA_PYRAMID = FEATURES.register(
            "giza_pyramid", GizaPyramidFeature::new);
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> GREEK_TEMPLE = FEATURES.register(
            "greek_temple", GreekTempleFeature::new);

    private ModFeatures() {
    }
}
