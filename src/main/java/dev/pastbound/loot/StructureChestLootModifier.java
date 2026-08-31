package dev.pastbound.loot;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.pastbound.registry.ModItems;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

public final class StructureChestLootModifier extends LootModifier {
    public static final MapCodec<StructureChestLootModifier> CODEC = RecordCodecBuilder.mapCodec(instance -> LootModifier.codecStart(instance).apply(instance, StructureChestLootModifier::new));

    private StructureChestLootModifier(LootItemCondition[] conditions, int priority) {
        super(conditions, priority);
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        Identifier lootTableId = context.getQueriedLootTableId();
        if (!"minecraft".equals(lootTableId.getNamespace()) || !lootTableId.getPath().startsWith("chests/")) {
            return generatedLoot;
        }
        if (context.getRandom().nextFloat() >= dev.pastbound.PastboundConfig.DEGERLER.sandikOdulSansı.get()) {
            return generatedLoot;
        }

        if (ModItems.RELIKLER.isEmpty()) {
            return generatedLoot;
        }
        int rewardCount = ModItems.RELIKLER.size() + 2;
        int reward = context.getRandom().nextInt(rewardCount);
        if (reward < ModItems.RELIKLER.size()) {
            generatedLoot.add(new ItemStack(ModItems.RELIKLER.get(reward).get()));
        } else if (reward == ModItems.RELIKLER.size()) {
            generatedLoot.add(new ItemStack(ModItems.TIME_STONE.get(), 1));
        } else {
            generatedLoot.add(new ItemStack(ModItems.CHRONICLE_COMPASS.get(), 1));
        }
        return generatedLoot;
    }
}
