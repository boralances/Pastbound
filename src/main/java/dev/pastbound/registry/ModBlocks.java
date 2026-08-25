package dev.pastbound.registry;

import dev.pastbound.ModId;
import dev.pastbound.block.EchoArchiveBlock;
import dev.pastbound.block.ResonancePillarBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ModId.MOD_ID);

    public static final DeferredBlock<EchoArchiveBlock> ECHO_ARCHIVE = BLOCKS.register("echo_archive", kimlik -> new EchoArchiveBlock(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, kimlik)).mapColor(MapColor.COLOR_PURPLE).sound(SoundType.AMETHYST).destroyTime(2.5F).explosionResistance(6.0F).lightLevel(durum -> 5)));
    public static final DeferredBlock<ResonancePillarBlock> RESONANCE_PILLAR = BLOCKS.register("resonance_pillar", kimlik -> new ResonancePillarBlock(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, kimlik)).mapColor(MapColor.COLOR_BLUE).sound(SoundType.COPPER).destroyTime(3.0F).explosionResistance(7.0F).lightLevel(durum -> durum.getValue(ResonancePillarBlock.CHARGED) ? 15 : 4)));

    private ModBlocks() {
    }
}
