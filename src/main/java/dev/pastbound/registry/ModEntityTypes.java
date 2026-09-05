package dev.pastbound.registry;

import dev.pastbound.ModId;
import dev.pastbound.entity.AncientBoatEntity;
import dev.pastbound.entity.EgyptianBoatEntity;
import dev.pastbound.entity.GreekBoatEntity;
import dev.pastbound.entity.VikingBoatEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, ModId.MOD_ID);
    public static final DeferredHolder<EntityType<?>, EntityType<AncientBoatEntity>> ANCIENT_BOAT = boat("ancient_boat", AncientBoatEntity::new);
    public static final DeferredHolder<EntityType<?>, EntityType<EgyptianBoatEntity>> EGYPTIAN_BOAT = boat("egyptian_boat", EgyptianBoatEntity::new);
    public static final DeferredHolder<EntityType<?>, EntityType<GreekBoatEntity>> GREEK_BOAT = boat("greek_boat", GreekBoatEntity::new);
    public static final DeferredHolder<EntityType<?>, EntityType<VikingBoatEntity>> VIKING_BOAT = boat("viking_boat", VikingBoatEntity::new);

    private static <T extends net.minecraft.world.entity.vehicle.boat.Boat> DeferredHolder<EntityType<?>, EntityType<T>> boat(
            String name, EntityType.EntityFactory<T> factory) {
        return ENTITY_TYPES.register(name, () -> EntityType.Builder.<T>of(factory, MobCategory.MISC)
                .noLootTable()
                .sized(1.375F, 0.5625F)
                .eyeHeight(0.5625F)
                .clientTrackingRange(10)
                .build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(ModId.MOD_ID, name))));
    }

    private ModEntityTypes() {
    }
}
