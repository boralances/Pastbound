package dev.pastbound;

import dev.pastbound.block.ResonancePillarBlock;
import dev.pastbound.block.entity.EchoArchiveBlockEntity;
import dev.pastbound.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public final class PastboundMemory {
    private PastboundMemory() {
    }

    public static InteractionResult recordShard(UseOnContext baglam) {
        Level seviye = baglam.getLevel();
        BlockPos konum = baglam.getClickedPos();
        Player oyuncu = baglam.getPlayer();
        if (oyuncu == null || !(seviye.getBlockEntity(konum) instanceof EchoArchiveBlockEntity arsiv)) {
            return InteractionResult.PASS;
        }
        if (arsiv.isComplete()) {
            if (!seviye.isClientSide()) {
                oyuncu.sendOverlayMessage(Component.translatable("message.pastbound.archive_complete"));
            }
            return seviye.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.SUCCESS_SERVER;
        }
        if (!seviye.isClientSide()) {
            ItemStack parca = baglam.getItemInHand();
            parca.shrink(1);
            arsiv.recordEcho(oyuncu);
            int sayi = arsiv.getEchoCount();
            oyuncu.sendOverlayMessage(Component.translatable("message.pastbound.archive_progress", sayi, EchoArchiveBlockEntity.SHARDS_REQUIRED));
            seviye.playSound(null, konum, SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 0.8F, 0.8F + sayi * 0.08F);
            if (seviye instanceof ServerLevel sunucu) {
                sunucu.sendParticles(ParticleTypes.END_ROD, konum.getX() + 0.5D, konum.getY() + 1.1D, konum.getZ() + 0.5D, 8 + sayi * 4, 0.25D, 0.35D, 0.25D, 0.02D);
            }
        }
        return seviye.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.SUCCESS_SERVER;
    }

    public static InteractionResult readLens(UseOnContext baglam) {
        Level seviye = baglam.getLevel();
        BlockPos konum = baglam.getClickedPos();
        Player oyuncu = baglam.getPlayer();
        if (oyuncu == null || !(seviye.getBlockEntity(konum) instanceof EchoArchiveBlockEntity arsiv)) {
            return InteractionResult.PASS;
        }
        if (!arsiv.isComplete()) {
            if (!seviye.isClientSide()) {
                oyuncu.sendOverlayMessage(Component.translatable("message.pastbound.archive_incomplete", arsiv.getEchoCount(), EchoArchiveBlockEntity.SHARDS_REQUIRED));
            }
            return seviye.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.SUCCESS_SERVER;
        }
        if (!seviye.isClientSide()) {
            awakenMemory(seviye, konum, oyuncu, baglam.getHand(), baglam.getItemInHand(), arsiv);
        }
        return seviye.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.SUCCESS_SERVER;
    }

    private static void awakenMemory(Level seviye, BlockPos arsivKonumu, Player oyuncu, InteractionHand el, ItemStack mercek, EchoArchiveBlockEntity arsiv) {
        String tanik = arsiv.getMemoryOwner();
        int uyandirilan = 0;
        BlockPos bas = arsivKonumu.offset(-2, -1, -2);
        BlockPos son = arsivKonumu.offset(2, 1, 2);
        for (BlockPos aday : BlockPos.betweenClosed(bas, son)) {
            BlockState durum = seviye.getBlockState(aday);
            if (durum.is(ModBlocks.RESONANCE_PILLAR.get()) && !durum.getValue(ResonancePillarBlock.CHARGED)) {
                seviye.setBlock(aday, durum.setValue(ResonancePillarBlock.CHARGED, true), 3);
                seviye.scheduleTick(aday, ModBlocks.RESONANCE_PILLAR.get(), ResonancePillarBlock.ACTIVE_TICKS);
                uyandirilan++;
            }
        }
        arsiv.clearMemory();
        oyuncu.addEffect(new net.minecraft.world.effect.MobEffectInstance(net.minecraft.world.effect.MobEffects.GLOWING, 200, 0, false, true, true));
        oyuncu.sendSystemMessage(Component.translatable("message.pastbound.memory_read", tanik, uyandirilan));
        seviye.playSound(null, arsivKonumu, SoundEvents.RESPAWN_ANCHOR_CHARGE, SoundSource.BLOCKS, 1.0F, 1.2F);
        if (seviye instanceof ServerLevel sunucu) {
            sunucu.sendParticles(ParticleTypes.END_ROD, arsivKonumu.getX() + 0.5D, arsivKonumu.getY() + 1.0D, arsivKonumu.getZ() + 0.5D, 48, 0.65D, 0.7D, 0.65D, 0.04D);
        }
        mercek.hurtAndBreak(1, oyuncu, el == InteractionHand.MAIN_HAND ? net.minecraft.world.entity.EquipmentSlot.MAINHAND : net.minecraft.world.entity.EquipmentSlot.OFFHAND);
        oyuncu.getCooldowns().addCooldown(mercek, 20);
    }
}
