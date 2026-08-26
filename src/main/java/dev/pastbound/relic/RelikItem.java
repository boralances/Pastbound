package dev.pastbound.relic;

import java.util.function.Consumer;

import dev.pastbound.client.ui.RelikDefteriEkrani;
import dev.pastbound.history.TarihYankisi;
import dev.pastbound.history.TarihYankilari;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public final class RelikItem extends Item implements ICurioItem {
    private final RelikTanimi tanim;

    public RelikItem(Properties ozellikler, RelikTanimi tanim) {
        super(ozellikler);
        this.tanim = tanim == null ? RelikTanimi.ROSSETTA_TASI : tanim;
    }

    public RelikTanimi tanim() {
        return tanim;
    }

    @Override
    public InteractionResult use(Level seviye, Player oyuncu, InteractionHand el) {
        ItemStack yigin = oyuncu.getItemInHand(el);
        if (seviye.isClientSide()) {
            if (!oyuncu.isShiftKeyDown() && !RelikMantigi.biliyorMu(oyuncu, tanim)) {
                RelikDefteriEkrani.etkinlikAc(tanim);
            }
            return InteractionResult.SUCCESS;
        }
        if (oyuncu.isShiftKeyDown()) {
            RelikMantigi.deneyimleTani(oyuncu, tanim);
        } else if (RelikMantigi.biliyorMu(oyuncu, tanim)) {
            RelikMantigi.etkinlestir(oyuncu, tanim, yigin);
        }
        return InteractionResult.SUCCESS_SERVER;
    }

    @Override
    public void curioTick(SlotContext yuva, ItemStack yigin) {
        LivingEntity varlik = yuva.entity();
        if (varlik instanceof Player oyuncu && !oyuncu.level().isClientSide() && oyuncu.tickCount % 40 == 0 && RelikMantigi.biliyorMu(oyuncu, tanim)) {
            RelikMantigi.pasifUygula(oyuncu, tanim);
        }
    }

    @Override
    public boolean canEquip(SlotContext yuva, ItemStack yigin) {
        if (yuva.identifier().equals("relic") && yuva.entity() instanceof Player oyuncu) {
            return yuva.index() < RelikMantigi.acikRelikYuvasi(oyuncu);
        }
        return yuva.identifier().equals("charm") || yuva.identifier().equals("curio");
    }

    @Override
    public void appendHoverText(ItemStack yigin, TooltipContext baglam, TooltipDisplay gorunum, Consumer<Component> satir, TooltipFlag bayrak) {
        satir.accept(Component.translatable("tooltip.pastbound.relic.era", tanim.tarihBasligiBileseni()));
        satir.accept(Component.translatable("tooltip.pastbound.relic.power", tanim.yeti().name()));
        satir.accept(Component.translatable("tooltip.pastbound.relic.activity", tanim.etkinlikBileseni()));
        satir.accept(Component.translatable("tooltip.pastbound.relic.identify", tanim.bilmeSeviyesi()));
        satir.accept(Component.translatable("tooltip.pastbound.relic.xp", tanim.bilmeSeviyesi()));
        satir.accept(Component.translatable("tooltip.pastbound.relic.shortcut"));
        TarihYankisi yanki = TarihYankilari.yankiBulRelik(tanim);
        if (yanki != null) {
            satir.accept(Component.translatable("tooltip.pastbound.relic.echo", yanki.baslik()));
        }
    }
}
