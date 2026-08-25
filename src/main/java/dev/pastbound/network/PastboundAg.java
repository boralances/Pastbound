package dev.pastbound.network;

import dev.pastbound.ModId;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

public final class PastboundAg {
    private PastboundAg() {
    }

    public static void kaydet(RegisterPayloadHandlersEvent olay) {
        olay.registrar(ModId.MOD_ID)
                .versioned("1")
                .commonToServer(PastboundPaketi.TIP, PastboundPaketi.KODLAYICI, PastboundPaketi::eleAl);
    }
}
