package dev.pastbound.block;

import dev.pastbound.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

public final class OliveAltarBlock extends Block {
    public static final IntegerProperty ACTIVATIONS = IntegerProperty.create("activations", 0, 44);

    public OliveAltarBlock(BlockBehaviour.Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(ACTIVATIONS, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ACTIVATIONS);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState();
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        return activate(state, level, pos, player);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        return activate(state, level, pos, player);
    }

    private static InteractionResult activate(BlockState state, Level level, BlockPos pos, Player player) {
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        int next = state.getValue(ACTIVATIONS) + 1;
        if (next >= 45) {
            level.setBlock(pos, state.setValue(ACTIVATIONS, 0), 3);
            ItemStack reward = new ItemStack(ModItems.TIME_STONE.get());
            if (!player.addItem(reward)) {
                player.drop(reward, false);
            }
            player.sendSystemMessage(Component.translatable("message.pastbound.altar.time_stone_reward"));
            player.playSound(SoundEvents.AMETHYST_BLOCK_CHIME, 1.0F, 1.15F);
        } else {
            level.setBlock(pos, state.setValue(ACTIVATIONS, next), 3);
            player.sendSystemMessage(Component.translatable("message.pastbound.altar.progress", 45 - next));
            player.playSound(SoundEvents.AMETHYST_BLOCK_CHIME, 0.35F, 0.8F + next / 100.0F);
        }
        return InteractionResult.SUCCESS_SERVER;
    }
}

