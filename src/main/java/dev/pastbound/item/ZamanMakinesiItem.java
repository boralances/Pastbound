package dev.pastbound.item;

import dev.pastbound.client.ui.ZamanMakinesiEkrani;
import dev.pastbound.history.TarihiKesifDunyasi;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public final class ZamanMakinesiItem extends Item {
    public ZamanMakinesiItem(Properties ozellikler) {
        super(ozellikler);
    }

    @Override
    public InteractionResult use(Level seviye, Player oyuncu, InteractionHand el) {
        if (seviye.isClientSide()) {
            if (seviye.dimension().equals(TarihiKesifDunyasi.BOYUT)) {
                if (oyuncu instanceof net.minecraft.client.player.LocalPlayer yerel) {
                    yerel.connection.send(new ServerboundCustomPayloadPacket(dev.pastbound.network.PastboundPaketi.don()));
                }
            } else {
                ZamanMakinesiEkrani.ac();
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.SUCCESS_SERVER;
    }
}
