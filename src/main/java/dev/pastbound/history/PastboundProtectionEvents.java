package dev.pastbound.history;

import dev.pastbound.ModId;
import dev.pastbound.registry.ModItems;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

/** Keeps protection rules explicit: historical dimensions or a complete Ervanium set only. */
@EventBusSubscriber(modid = ModId.MOD_ID)
public final class PastboundProtectionEvents {
    private PastboundProtectionEvents() {
    }

    @SubscribeEvent
    public static void incomingDamage(LivingIncomingDamageEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }
        if (TarihiKesifDunyasi.boyuttaMi(player) || hasCompleteErvaniumSet(player)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void playerTick(PlayerTickEvent.Post event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }
        if (TarihiKesifDunyasi.boyuttaMi(player) || hasCompleteErvaniumSet(player)) {
            player.setHealth(player.getMaxHealth());
            player.clearFire();
        }
    }

    private static boolean hasCompleteErvaniumSet(Player player) {
        return is(player, EquipmentSlot.HEAD, ModItems.ERVANIUM_HELMET.get())
                && is(player, EquipmentSlot.CHEST, ModItems.ERVANIUM_CHESTPLATE.get())
                && is(player, EquipmentSlot.LEGS, ModItems.ERVANIUM_LEGGINGS.get())
                && is(player, EquipmentSlot.FEET, ModItems.ERVANIUM_BOOTS.get());
    }

    private static boolean is(Player player, EquipmentSlot slot, Item expected) {
        return player.getItemBySlot(slot).is(expected);
    }
}
