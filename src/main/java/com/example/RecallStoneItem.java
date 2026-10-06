package com.example;

import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.Level;

import java.util.Set;

public class RecallStoneItem extends Item {

    public RecallStoneItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {

        // Only run on server
        if (level.isClientSide()) {
            return InteractionResult.PASS;
        }

        if (player instanceof ServerPlayer serverPlayer) {

            var config = serverPlayer.getRespawnConfig();

            if (config != null) {
                var respawn = config.respawnData().globalPos();
                var pos = respawn.pos();
                var destination = serverPlayer.level().getServer().getLevel(respawn.dimension());

                if (destination != null) {
                    serverPlayer.teleportTo(
                        destination,
                        pos.getX(),
                        pos.getY(),
                        pos.getZ(),
                        Set.of(),
                        serverPlayer.getYRot(),
                        serverPlayer.getXRot(),
                        false
                    );
                }
            }
        }

        return InteractionResult.SUCCESS;
    }
}
