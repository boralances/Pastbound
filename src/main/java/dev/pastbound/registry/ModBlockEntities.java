package dev.pastbound.registry;

import java.util.Set;

import dev.pastbound.ModId;
import dev.pastbound.block.entity.EchoArchiveBlockEntity;
import dev.pastbound.block.entity.AncientStorageBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, ModId.MOD_ID);
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<EchoArchiveBlockEntity>> ECHO_ARCHIVE = BLOCK_ENTITY_TYPES.register("echo_archive", () -> new BlockEntityType<>(EchoArchiveBlockEntity::new, Set.of(ModBlocks.ECHO_ARCHIVE.get())));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AncientStorageBlockEntity>> ANCIENT_STORAGE = BLOCK_ENTITY_TYPES.register("ancient_storage", () -> new BlockEntityType<>(AncientStorageBlockEntity::new, Set.of(ModBlocks.ANCIENT_STORAGE.get())));

    private ModBlockEntities() {
    }
}
