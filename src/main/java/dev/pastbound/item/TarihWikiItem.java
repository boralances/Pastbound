package dev.pastbound.item;

import dev.pastbound.client.ui.TarihWikiEkrani;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public final class TarihWikiItem extends Item {
    public TarihWikiItem(Properties ozellikler) {
        super(ozellikler);
    }

    @Override
    public InteractionResult use(Level seviye, Player oyuncu, InteractionHand el) {
        if (seviye.isClientSide()) {
            TarihWikiEkrani.ac();
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.SUCCESS_SERVER;
    }
}
