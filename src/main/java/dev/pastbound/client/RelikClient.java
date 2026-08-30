package dev.pastbound.client;

import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.platform.InputConstants;

import dev.pastbound.ModId;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

@EventBusSubscriber(modid = ModId.MOD_ID, value = Dist.CLIENT)
public final class RelikClient {
    private static final KeyMapping.Category PASTBOUND_KATEGORI = KeyMapping.Category.register(Identifier.parse("pastbound:relics"));
    public static final KeyMapping DEFTER_KISAYOLU = new KeyMapping("key.pastbound.journal", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, PASTBOUND_KATEGORI);
    public static final KeyMapping AKTIFLESTIRME_KISAYOLU = new KeyMapping("key.pastbound.activate", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_V, PASTBOUND_KATEGORI);
    public static final KeyMapping TARIH_KONTROL_KISAYOLU = new KeyMapping("key.pastbound.take_control", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_D, PASTBOUND_KATEGORI);
    public static final KeyMapping CRAFTING_TABLE_CUBUGU_KISAYOLU = new KeyMapping("key.pastbound.crafting_table_stick", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_C, PASTBOUND_KATEGORI);
    public static final KeyMapping FIRIN_CUBUGU_KISAYOLU = new KeyMapping("key.pastbound.furnace_stick", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_H, PASTBOUND_KATEGORI);
    public static final KeyMapping DIL_KISAYOLU = new KeyMapping("key.pastbound.language", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_L, PASTBOUND_KATEGORI);
    public static final KeyMapping[] RELIK_YUVA_KISAYOLLARI = {
            new KeyMapping("key.pastbound.relic_slot_1", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_KP_1, PASTBOUND_KATEGORI),
            new KeyMapping("key.pastbound.relic_slot_2", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_KP_2, PASTBOUND_KATEGORI),
            new KeyMapping("key.pastbound.relic_slot_3", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_KP_3, PASTBOUND_KATEGORI),
            new KeyMapping("key.pastbound.relic_slot_4", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_KP_4, PASTBOUND_KATEGORI),
            new KeyMapping("key.pastbound.relic_slot_5", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_KP_5, PASTBOUND_KATEGORI),
            new KeyMapping("key.pastbound.relic_slot_6", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_KP_6, PASTBOUND_KATEGORI),
            new KeyMapping("key.pastbound.relic_slot_7", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_KP_7, PASTBOUND_KATEGORI),
            new KeyMapping("key.pastbound.relic_slot_8", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_KP_8, PASTBOUND_KATEGORI)
    };

    private RelikClient() {
    }

    @SubscribeEvent
    public static void tuslariKaydet(RegisterKeyMappingsEvent olay) {
        olay.register(DEFTER_KISAYOLU);
        olay.register(AKTIFLESTIRME_KISAYOLU);
        olay.register(TARIH_KONTROL_KISAYOLU);
        olay.register(DIL_KISAYOLU);
        olay.register(CRAFTING_TABLE_CUBUGU_KISAYOLU);
        olay.register(FIRIN_CUBUGU_KISAYOLU);
        for (KeyMapping kisayol : RELIK_YUVA_KISAYOLLARI) {
            olay.register(kisayol);
        }
    }
}
