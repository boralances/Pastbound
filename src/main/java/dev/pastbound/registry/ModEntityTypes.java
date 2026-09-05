package dev.pastbound.registry;

import dev.pastbound.ModId;
import dev.pastbound.entity.AncientBoatEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, ModId.MOD_ID);
    public static final DeferredHolder<EntityType<?>, EntityType<AncientBoatEntity>> ANCIENT_BOAT = ENTITY_TYPES.register("ancient_boat", () ->
            EntityType.Builder.<AncientBoatEntity>of(AncientBoatEntity::new, MobCategory.MISC)
                    .noLootTable()
                    .sized(1.375F, 0.5625F)
                    .eyeHeight(0.5625F)
                    .clientTrackingRange(10)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(ModId.MOD_ID, "ancient_boat"))));

    private ModEntityTypes() {
    }
}
