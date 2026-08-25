package dev.pastbound.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public final class FirinCubuguItem extends Item {
    public FirinCubuguItem(Properties ozellikler) {
        super(ozellikler);
    }

    @Override
    public InteractionResult use(Level seviye, Player oyuncu, InteractionHand el) {
        if (!seviye.isClientSide()) {
            oyuncu.openMenu(new SimpleMenuProvider((id, envanter, kullanici) -> new FurnaceMenu(id, envanter), Component.translatable("container.furnace")));
        }
        return seviye.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.SUCCESS_SERVER;
    }
}
