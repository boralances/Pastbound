package dev.pastbound.item;

import dev.pastbound.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public final class KronikPusulasiItem extends Item {
    public static final int YATAY_TARAMA_YARICAPI = 32;
    public static final int DIKEY_TARAMA_YARICAPI = 24;

    public KronikPusulasiItem(Properties ozellikler) {
        super(ozellikler);
    }

    @Override
    public InteractionResult use(Level seviye, Player oyuncu, InteractionHand el) {
        if (seviye.isClientSide()) {
            return InteractionResult.SUCCESS;
        }
        BlockPos hedef = enYakinCevher(seviye, oyuncu.blockPosition());
        if (hedef == null) {
            oyuncu.sendSystemMessage(Component.translatable("message.pastbound.compass.none"));
            return InteractionResult.SUCCESS_SERVER;
        }
        BlockState durum = seviye.getBlockState(hedef);
        int uzaklik = (int) Math.sqrt(oyuncu.blockPosition().distSqr(hedef));
        oyuncu.sendSystemMessage(Component.translatable("message.pastbound.compass.found", cevherAdi(durum), uzaklik, hedef.getX(), hedef.getY(), hedef.getZ()));
        oyuncu.getCooldowns().addCooldown(oyuncu.getItemInHand(el), 60);
        return InteractionResult.SUCCESS_SERVER;
    }

    private static BlockPos enYakinCevher(Level seviye, BlockPos merkez) {
        BlockPos hedef = null;
        double enKisa = Double.MAX_VALUE;
        for (int x = merkez.getX() - YATAY_TARAMA_YARICAPI; x <= merkez.getX() + YATAY_TARAMA_YARICAPI; x++) {
            for (int y = Math.max(seviye.getMinY(), merkez.getY() - DIKEY_TARAMA_YARICAPI); y <= Math.min(seviye.getMaxY(), merkez.getY() + DIKEY_TARAMA_YARICAPI); y++) {
                for (int z = merkez.getZ() - YATAY_TARAMA_YARICAPI; z <= merkez.getZ() + YATAY_TARAMA_YARICAPI; z++) {
                    BlockPos konum = new BlockPos(x, y, z);
                    if (cevherMi(seviye.getBlockState(konum))) {
                        double uzaklik = merkez.distSqr(konum);
                        if (uzaklik < enKisa) {
                            enKisa = uzaklik;
                            hedef = konum;
                        }
                    }
                }
            }
        }
        return hedef;
    }

    private static boolean cevherMi(BlockState durum) {
        return durum.is(ModBlocks.TIME_STONE_ORE.get()) || durum.is(ModBlocks.NETHER_TIME_STONE_ORE.get()) || durum.is(ModBlocks.CHRONICLE_ORE.get()) || durum.is(ModBlocks.ASH_CHRONICLE_ORE.get()) || durum.is(ModBlocks.END_ECHO_ORE.get()) || durum.is(ModBlocks.VOID_CHRONICLE_ORE.get()) || durum.is(ModBlocks.STEEL_ORE.get()) || durum.is(ModBlocks.DEEPSLATE_STEEL_ORE.get());
    }

    private static Component cevherAdi(BlockState durum) {
        if (durum.is(ModBlocks.TIME_STONE_ORE.get())) {
            return Component.translatable("block.pastbound.time_stone_ore");
        }
        if (durum.is(ModBlocks.NETHER_TIME_STONE_ORE.get())) {
            return Component.translatable("block.pastbound.nether_time_stone_ore");
        }
        if (durum.is(ModBlocks.CHRONICLE_ORE.get())) {
            return Component.translatable("block.pastbound.chronicle_ore");
        }
        if (durum.is(ModBlocks.ASH_CHRONICLE_ORE.get())) {
            return Component.translatable("block.pastbound.ash_chronicle_ore");
        }
        if (durum.is(ModBlocks.END_ECHO_ORE.get())) {
            return Component.translatable("block.pastbound.end_echo_ore");
        }
        if (durum.is(ModBlocks.VOID_CHRONICLE_ORE.get())) {
            return Component.translatable("block.pastbound.void_chronicle_ore");
        }
        if (durum.is(ModBlocks.DEEPSLATE_STEEL_ORE.get())) {
            return Component.translatable("block.pastbound.deepslate_steel_ore");
        }
        return Component.translatable("block.pastbound.steel_ore");
    }
}
