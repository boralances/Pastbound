package dev.pastbound.registry;

import com.mojang.serialization.MapCodec;
import dev.pastbound.ModId;
import dev.pastbound.loot.StructureChestLootModifier;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;

public final class ModLootModifiers {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> SERIALIZERS = DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, ModId.MOD_ID);
    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<StructureChestLootModifier>> STRUCTURE_CHEST_REWARDS = SERIALIZERS.register("structure_chest_rewards", () -> StructureChestLootModifier.CODEC);

    private ModLootModifiers() {
    }
}
