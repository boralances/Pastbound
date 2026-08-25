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
    public static final KeyMapping DEFTER_KISAYOLU = new KeyMapping("key.pastbound.journal", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, KeyMapping.Category.register(Identifier.parse("pastbound:relics")));
    public static final KeyMapping AKTIFLESTIRME_KISAYOLU = new KeyMapping("key.pastbound.activate", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_V, KeyMapping.Category.register(Identifier.parse("pastbound:relics")));

    private RelikClient() {
    }

    @SubscribeEvent
    public static void tuslariKaydet(RegisterKeyMappingsEvent olay) {
        olay.register(DEFTER_KISAYOLU);
        olay.register(AKTIFLESTIRME_KISAYOLU);
    }
}
