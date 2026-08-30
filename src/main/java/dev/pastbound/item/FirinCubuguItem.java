package dev.pastbound.item;

import java.util.function.Consumer;

import dev.pastbound.client.ui.FirinCubuguMenusu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

public final class FirinCubuguItem extends Item {
    private final boolean gelistirilmis;

    public FirinCubuguItem(Properties ozellikler) {
        this(ozellikler, false);
    }

    public FirinCubuguItem(Properties ozellikler, boolean gelistirilmis) {
        super(ozellikler);
        this.gelistirilmis = gelistirilmis;
    }

    @Override
    public void appendHoverText(ItemStack yigin, TooltipContext baglam, TooltipDisplay gorunum, Consumer<Component> satir, TooltipFlag bayrak) {
        satir.accept(Component.translatable("tooltip.pastbound.portable_furnace"));
        if (gelistirilmis) {
            satir.accept(Component.translatable("tooltip.pastbound.portable_furnace_master"));
        }
    }

    @Override
    public InteractionResult use(Level seviye, Player oyuncu, InteractionHand el) {
        if (!seviye.isClientSide()) {
            oyuncu.openMenu(new SimpleMenuProvider((id, envanter, kullanici) -> FirinCubuguMenusu.ac(id, envanter, gelistirilmis), Component.translatable("container.furnace")));
        }
        return seviye.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.SUCCESS_SERVER;
    }
}
