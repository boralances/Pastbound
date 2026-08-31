package dev.pastbound.command;

import com.mojang.brigadier.CommandDispatcher;
import dev.pastbound.ModId;
import dev.pastbound.history.TarihiKesifDunyasi;
import dev.pastbound.registry.ModItems;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.permissions.Permissions;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@EventBusSubscriber(modid = ModId.MOD_ID)
public final class PastboundKomutlari {
    private PastboundKomutlari() {
    }

    @SubscribeEvent
    public static void komutlariKaydet(RegisterCommandsEvent olay) {
        CommandDispatcher<CommandSourceStack> dispatcher = olay.getDispatcher();
        dispatcher.register(Commands.literal("pastbound")
                .then(Commands.literal("progress").executes(komut -> {
                    ServerPlayer oyuncu = komut.getSource().getPlayerOrException();
                    komut.getSource().sendSuccess(() -> Component.literal("Completed dimensions: " + TarihiKesifDunyasi.tamamlananDunyalar(oyuncu)), false);
                    return 1;
                }))
                .then(Commands.literal("help").executes(komut -> {
                    komut.getSource().sendSuccess(() -> Component.literal("/pastbound progress - show completed dimensions"), false);
                    komut.getSource().sendSuccess(() -> Component.literal("/pastbound wiki - give the in-game field wiki"), false);
                    komut.getSource().sendSuccess(() -> Component.literal("/pastbound unlock_recipes - unlock all Pastbound recipes"), false);
                    komut.getSource().sendSuccess(() -> Component.literal("/pastbound test_kit - give coal, torches, pickaxe and steel tools"), false);
                    return 1;
                }))
                .then(Commands.literal("wiki").executes(komut -> {
                    ServerPlayer oyuncu = komut.getSource().getPlayerOrException();
                    oyuncu.getInventory().placeItemBackInInventory(new ItemStack(ModItems.TARIH_WIKI.get()));
                    komut.getSource().sendSuccess(() -> Component.literal("Pastbound Field Wiki given."), false);
                    return 1;
                }))
                .then(Commands.literal("unlock_recipes").executes(komut -> {
                    ServerPlayer oyuncu = komut.getSource().getPlayerOrException();
                    var tarifler = oyuncu.level().getServer().getRecipeManager().getRecipes().stream()
                            .filter(tarif -> tarif.id().identifier().getNamespace().equals(ModId.MOD_ID))
                            .toList();
                    oyuncu.awardRecipes(tarifler);
                    komut.getSource().sendSuccess(() -> Component.literal("Pastbound recipes unlocked: " + tarifler.size()), false);
                    return 1;
                }))
                .then(Commands.literal("test_kit").requires(source -> source.permissions().hasPermission(Permissions.COMMANDS_GAMEMASTER)).executes(komut -> {
                    ServerPlayer oyuncu = komut.getSource().getPlayerOrException();
                    oyuncu.getInventory().placeItemBackInInventory(new ItemStack(Items.COAL, 32));
                    oyuncu.getInventory().placeItemBackInInventory(new ItemStack(Items.TORCH, 32));
                    oyuncu.getInventory().placeItemBackInInventory(new ItemStack(Items.IRON_PICKAXE));
                    oyuncu.getInventory().placeItemBackInInventory(new ItemStack(ModItems.STEEL_PLATE.get(), 4));
                    komut.getSource().sendSuccess(() -> Component.literal("Pastbound test kit given."), false);
                    return 1;
                })));
    }
}
