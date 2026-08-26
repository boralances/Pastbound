package dev.pastbound.history;

import dev.pastbound.ModId;
import dev.pastbound.registry.ModBlocks;
import dev.pastbound.registry.ModItems;
import dev.pastbound.relic.RelikMantigi;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import dev.pastbound.network.PastboundPaketi;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.block.BreakBlockEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = ModId.MOD_ID)
public final class TarihYankilari {
    private TarihYankilari() {
    }

    public static TarihYankisi yankiBul(String kimlik) {
        for (TarihYankisi yanki : TarihYankisi.values()) {
            if (yanki.kimlik().equalsIgnoreCase(kimlik)) {
                return yanki;
            }
        }
        return null;
    }

    public static TarihYankisi yankiBulRelik(dev.pastbound.relic.RelikTanimi relik) {
        for (TarihYankisi yanki : TarihYankisi.values()) {
            if (yanki.relik() == relik) {
                return yanki;
            }
        }
        return null;
    }

    private static void yankiyiBaslat(Player oyuncu, TarihYankisi yanki) {
        if (!oyuncu.level().isClientSide()) {
            RelikMantigi.yankiyiHazirla(oyuncu, yanki);
        }
    }

    @SubscribeEvent
    public static void hasarOnlendi(LivingDamageEvent.Pre olay) {
        if (olay.getEntity() instanceof ServerPlayer oyuncu && TarihiKesifDunyasi.boyuttaMi(oyuncu)) {
            olay.setNewDamage(0.0F);
            if (oyuncu.getHealth() < 1.0F) {
                oyuncu.setHealth(1.0F);
            }
        }
    }

    @SubscribeEvent
    public static void olumOnlendi(LivingDeathEvent olay) {
        if (olay.getEntity() instanceof ServerPlayer oyuncu && TarihiKesifDunyasi.boyuttaMi(oyuncu)) {
            olay.setCanceled(true);
            oyuncu.setHealth(Math.max(1.0F, oyuncu.getHealth()));
            oyuncu.clearFire();
            oyuncu.setDeltaMovement(0.0D, 0.0D, 0.0D);
        }
    }

    @SubscribeEvent
    public static void blokKirildi(BreakBlockEvent olay) {
        Player oyuncu = olay.getPlayer();
        if (oyuncu.level().isClientSide()) {
            return;
        }
        if (oyuncu instanceof ServerPlayer sunucu && TarihiKesifDunyasi.boyuttaMi(sunucu)) {
            if (olay.getState().is(ModBlocks.STEEL_ORE.get()) && TarihiKesifDunyasi.celiKirilabilir(sunucu, olay.getPos())) {
                TarihiKesifDunyasi.celiKirilmasi(sunucu);
                return;
            }
            if (olay.getState().is(ModBlocks.ECHO_ARCHIVE.get()) && TarihiKesifDunyasi.anitKirilabilir(sunucu, olay.getPos())) {
                TarihiKesifDunyasi.anitKirildi(sunucu);
                return;
            }
            olay.setCanceled(true);
            return;
        }
        if (olay.getState().is(ModBlocks.ECHO_ARCHIVE.get())) {
            yankiyiBaslat(oyuncu, TarihYankisi.CATALHOYUK_EVLERI);
        } else if (olay.getState().is(net.minecraft.world.level.block.Blocks.CLAY)) {
            yankiyiBaslat(oyuncu, TarihYankisi.URUK_MUHRU);
        } else if (olay.getState().is(net.minecraft.world.level.block.Blocks.SAND)) {
            yankiyiBaslat(oyuncu, TarihYankisi.TIMBUKTU_KERVANI);
        } else if (olay.getState().is(net.minecraft.world.level.block.Blocks.COPPER_ORE)) {
            yankiyiBaslat(oyuncu, TarihYankisi.BENIN_DOKUMU);
        } else if (olay.getState().is(net.minecraft.world.level.block.Blocks.LAPIS_ORE)) {
            yankiyiBaslat(oyuncu, TarihYankisi.RUNE_TASI);
        } else if (olay.getState().is(net.minecraft.world.level.block.Blocks.AMETHYST_BLOCK)) {
            yankiyiBaslat(oyuncu, TarihYankisi.ANTIKITHERA_GOK);
        }
    }

    @SubscribeEvent
    public static void blokKoyuldu(BlockEvent.EntityPlaceEvent olay) {
        if (olay.getEntity() instanceof ServerPlayer oyuncu && TarihiKesifDunyasi.boyuttaMi(oyuncu)) {
            olay.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void blokEtkilesildi(PlayerInteractEvent.RightClickBlock olay) {
        Player oyuncu = olay.getEntity();
        if (oyuncu.level().isClientSide()) {
            return;
        }
        if (oyuncu instanceof ServerPlayer sunucu && TarihiKesifDunyasi.boyuttaMi(sunucu)) {
            if (TarihiKesifDunyasi.tarihForgeMi(sunucu, olay.getPos())) {
                olay.setCanceled(true);
                TarihiKesifDunyasi.forgeOnar(sunucu, olay.getPos());
                return;
            }
            if (olay.getLevel().getBlockState(olay.getPos()).is(net.minecraft.world.level.block.Blocks.FURNACE) || olay.getLevel().getBlockState(olay.getPos()).is(net.minecraft.world.level.block.Blocks.CRAFTING_TABLE)) {
                return;
            }
            olay.setCanceled(true);
            return;
        }
        if (olay.getLevel().getBlockState(olay.getPos()).is(ModBlocks.ECHO_ARCHIVE.get())) {
            yankiyiBaslat(oyuncu, TarihYankisi.PAPIRUS_SIFRESI);
        } else if (olay.getLevel().getBlockState(olay.getPos()).is(net.minecraft.world.level.block.Blocks.CRAFTING_TABLE)) {
            yankiyiBaslat(oyuncu, TarihYankisi.ROMA_FORUMU);
        } else if (olay.getLevel().getBlockState(olay.getPos()).is(net.minecraft.world.level.block.Blocks.FURNACE)) {
            yankiyiBaslat(oyuncu, TarihYankisi.SONG_FIRINI);
        } else if (olay.getLevel().getBlockState(olay.getPos()).is(net.minecraft.world.level.block.Blocks.ENCHANTING_TABLE)) {
            yankiyiBaslat(oyuncu, TarihYankisi.ABBASI_BILGI_EVI);
        } else if (olay.getLevel().getBlockState(olay.getPos()).is(net.minecraft.world.level.block.Blocks.CARTOGRAPHY_TABLE)) {
            yankiyiBaslat(oyuncu, TarihYankisi.POLINEZYA_YILDIZ);
        }
    }

    @SubscribeEvent
    public static void varlikEtkilesildi(PlayerInteractEvent.EntityInteract olay) {
        Player oyuncu = olay.getEntity();
        if (oyuncu.level().isClientSide() || !(olay.getTarget() instanceof Villager villager)) {
            return;
        }
        if (oyuncu instanceof ServerPlayer sunucu && TarihiKesifDunyasi.boyuttaMi(sunucu)) {
            int konusmaci = 0;
            for (int i = 0; i < 4; i++) {
                if (villager.entityTags().contains("pastbound_sahne_" + i)) {
                    konusmaci = i;
                    break;
                }
            }
            String donem = ((net.neoforged.neoforge.common.extensions.IEntityExtension) sunucu).getPersistentData().getStringOr("pastbound_sahne_cagi", "");
            if (!donem.isEmpty()) {
                olay.setCanceled(true);
                PacketDistributor.sendToPlayer(sunucu, PastboundPaketi.konusma(donem, konusmaci));
            }
            return;
        }
        yankiyiBaslat(oyuncu, TarihYankisi.ILHANLI_MENZIL);
    }

    @SubscribeEvent
    public static void esyaUretildi(PlayerEvent.ItemCraftedEvent olay) {
        Player oyuncu = olay.getEntity();
        if (oyuncu.level().isClientSide()) {
            return;
        }
        ItemStack yigin = olay.getCrafting();
        if (yigin.is(ModItems.STEEL_PLATE.get())) {
            TarihiKesifDunyasi.celikLevhaUretildi((ServerPlayer) oyuncu);
        } else if (yigin.is(Items.WRITABLE_BOOK)) {
            yankiyiBaslat(oyuncu, TarihYankisi.TIMBUKTU_KERVANI);
        } else if (yigin.is(Items.CLOCK)) {
            yankiyiBaslat(oyuncu, TarihYankisi.ANTIKITHERA_GOK);
        } else if (yigin.is(Items.COMPASS)) {
            yankiyiBaslat(oyuncu, TarihYankisi.KUZEY_GUNESI);
        } else if (yigin.is(Items.SPYGLASS)) {
            yankiyiBaslat(oyuncu, TarihYankisi.RONESANS_ATOLYESI);
        } else if (yigin.is(Items.BOOK)) {
            yankiyiBaslat(oyuncu, TarihYankisi.ABBASI_BILGI_EVI);
        } else if (yigin.is(Items.OAK_BOAT)) {
            yankiyiBaslat(oyuncu, TarihYankisi.POLINEZYA_YILDIZ);
        } else if (yigin.is(Items.GOLD_INGOT)) {
            yankiyiBaslat(oyuncu, TarihYankisi.ROMA_FORUMU);
        }
    }

    @SubscribeEvent
    public static void esyaPisirildi(PlayerEvent.ItemSmeltedEvent olay) {
        Player oyuncu = olay.getEntity();
        if (oyuncu.level().isClientSide()) {
            return;
        }
        ItemStack yigin = olay.getSmelting();
        if (yigin.is(ModItems.STEEL_INGOT.get())) {
            TarihiKesifDunyasi.celikKulluguEritildi((ServerPlayer) oyuncu);
        } else if (yigin.is(Items.GLASS)) {
            yankiyiBaslat(oyuncu, TarihYankisi.SONG_FIRINI);
        } else if (yigin.is(Items.IRON_INGOT)) {
            yankiyiBaslat(oyuncu, TarihYankisi.BENIN_DOKUMU);
        } else if (yigin.is(Items.COPPER_INGOT)) {
            yankiyiBaslat(oyuncu, TarihYankisi.MAYA_TAKVIMI);
        } else if (yigin.is(Items.BRICK)) {
            yankiyiBaslat(oyuncu, TarihYankisi.CATALHOYUK_EVLERI);
        }
    }

    @SubscribeEvent
    public static void boyutDegisti(PlayerEvent.PlayerChangedDimensionEvent olay) {
        Player oyuncu = olay.getEntity();
        if (oyuncu.level().isClientSide()) {
            return;
        }
        if (TarihiKesifDunyasi.tarihBoyutuMu(olay.getFrom()) || TarihiKesifDunyasi.tarihBoyutuMu(olay.getTo())) {
            return;
        }
        if (olay.getTo().equals(Level.NETHER)) {
            yankiyiBaslat(oyuncu, TarihYankisi.AZTEK_BES_CAG);
        } else if (olay.getTo().equals(Level.END)) {
            yankiyiBaslat(oyuncu, TarihYankisi.APOLLO_AY_YURUYUSU);
        } else {
            yankiyiBaslat(oyuncu, TarihYankisi.POLINEZYA_YILDIZ);
        }
    }

    @SubscribeEvent
    public static void oyunaGirdi(PlayerEvent.PlayerLoggedInEvent olay) {
        Player oyuncu = olay.getEntity();
        yankiyiBaslat(oyuncu, TarihYankisi.PAPIRUS_SIFRESI);
    }

    @SubscribeEvent
    public static void oyuncuTiklandi(PlayerTickEvent.Post olay) {
        Player oyuncu = olay.getEntity();
        if (oyuncu.level().isClientSide()) {
            return;
        }
        if (oyuncu instanceof net.minecraft.server.level.ServerPlayer sunucu && TarihiKesifDunyasi.canlandirmaAktifMi(sunucu)) {
            TarihiKesifDunyasi.tik(sunucu);
            return;
        }
        if (oyuncu instanceof net.minecraft.server.level.ServerPlayer sunucu && TarihiKesifDunyasi.boyuttaMi(sunucu)) {
            TarihiKesifDunyasi.kontrolSonrasiTik(sunucu);
        }
        if (oyuncu.tickCount % 40 != 0) {
            return;
        }
        long gunSaati = oyuncu.level().getOverworldClockTime() % 24000L;
        if (gunSaati > 12500L && gunSaati < 23000L) {
            yankiyiBaslat(oyuncu, TarihYankisi.KUZEY_GUNESI);
        }
        if (oyuncu.isInWater()) {
            yankiyiBaslat(oyuncu, TarihYankisi.NIL_TORENI);
        }
        if (oyuncu.getY() > 120.0D) {
            yankiyiBaslat(oyuncu, TarihYankisi.RONESANS_ATOLYESI);
        }
        if (oyuncu.isCrouching() && oyuncu.onGround()) {
            yankiyiBaslat(oyuncu, TarihYankisi.BUSHIDO_YEMINI);
        }
    }
}
