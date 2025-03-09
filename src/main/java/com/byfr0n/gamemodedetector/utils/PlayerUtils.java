package com.byfr0n.gamemodedetector.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.GameMode;

public class PlayerUtils {
    public static GameMode getPlayerGamemode(PlayerEntity player) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client.getNetworkHandler() != null) {
            PlayerListEntry entry = client.getNetworkHandler().getPlayerListEntry(player.getUuid());
            if (entry != null) {
                return entry.getGameMode();
            }
        }

        return player.isSpectator() ? GameMode.SPECTATOR : null;
    }
}