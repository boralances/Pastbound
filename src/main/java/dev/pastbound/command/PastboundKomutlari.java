package dev.pastbound.command;

import com.mojang.brigadier.arguments.StringArgumentType;

import dev.pastbound.history.TarihYankisi;
import dev.pastbound.relic.RelikMantigi;
import dev.pastbound.relic.RelikTanimi;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

public final class PastboundKomutlari {
    private PastboundKomutlari() {
    }

    public static void kaydet(RegisterCommandsEvent olay) {
        var yankiyiCoz = Commands.literal("echo")
                .then(Commands.argument("echo", StringArgumentType.word())
                        .then(Commands.argument("sequence", StringArgumentType.word())
                                .executes(kontekst -> {
                                    String kimlik = StringArgumentType.getString(kontekst, "echo");
                                    String dizi = StringArgumentType.getString(kontekst, "sequence");
                                    boolean dogru = RelikMantigi.yankiyiCoz(kontekst.getSource().getPlayerOrException(), kimlik, dizi);
                                    return dogru ? 1 : 0;
                                })));
        var bilmece = Commands.literal("riddle")
                .then(Commands.argument("relic", StringArgumentType.word())
                        .then(Commands.argument("answer", StringArgumentType.greedyString())
                                .executes(kontekst -> {
                                    String kimlik = StringArgumentType.getString(kontekst, "relic");
                                    String cevap = StringArgumentType.getString(kontekst, "answer");
                                    boolean dogru = RelikMantigi.bilmeceCevapla(kontekst.getSource().getPlayerOrException(), kimlik, cevap);
                                    return dogru ? 1 : 0;
                                })));
        olay.getDispatcher().register(Commands.literal("pastbound")
                .then(Commands.literal("activate").executes(kontekst -> RelikMantigi.etkinlestirIlk(kontekst.getSource().getPlayerOrException()) ? 1 : 0))
                .then(Commands.literal("journal").executes(kontekst -> {
                    int sayi = RelikMantigi.bilinenSayi(kontekst.getSource().getPlayerOrException());
                    kontekst.getSource().sendSuccess(() -> Component.translatable("message.pastbound.journal_count", sayi, RelikTanimi.values().length), false);
                    return sayi;
                }))
                .then(Commands.literal("codex").executes(kontekst -> {
                    int sayi = RelikMantigi.tamamlananYankiSayisi(kontekst.getSource().getPlayerOrException());
                    kontekst.getSource().sendSuccess(() -> Component.translatable("message.pastbound.echo_count", sayi, TarihYankisi.values().length), false);
                    return sayi;
                }))
                .then(yankiyiCoz)
                .then(bilmece));
    }
}
