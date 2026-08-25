package dev.pastbound.item;

import dev.pastbound.PastboundMemory;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;

public final class MemoryLensItem extends Item {
    public MemoryLensItem(Properties ozellikler) {
        super(ozellikler);
    }

    @Override
    public InteractionResult useOn(UseOnContext baglam) {
        return PastboundMemory.readLens(baglam);
    }
}
