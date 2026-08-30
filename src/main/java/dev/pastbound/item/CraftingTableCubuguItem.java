package dev.pastbound.item;

import java.util.function.Consumer;

import dev.pastbound.history.TarihBasarilari;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

public final class CraftingTableCubuguItem extends Item {
    public CraftingTableCubuguItem(Properties ozellikler) {
        super(ozellikler);
    }

    @Override
    public void appendHoverText(ItemStack yigin, TooltipContext baglam, TooltipDisplay gorunum, Consumer<Component> satir, TooltipFlag bayrak) {
        satir.accept(Component.translatable("tooltip.pastbound.portable_crafting"));
    }

    @Override
    public InteractionResult use(Level seviye, Player oyuncu, InteractionHand el) {
        if (!seviye.isClientSide()) {
            oyuncu.openMenu(new SimpleMenuProvider((id, envanter, kullanici) -> new CraftingMenu(id, envanter, net.minecraft.world.inventory.ContainerLevelAccess.NULL), Component.translatable("container.crafting")));
            if (oyuncu instanceof net.minecraft.server.level.ServerPlayer sunucu) {
                TarihBasarilari.ver(sunucu, "workstations/portable_crafting");
            }
        }
        return seviye.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.SUCCESS_SERVER;
    }
}
