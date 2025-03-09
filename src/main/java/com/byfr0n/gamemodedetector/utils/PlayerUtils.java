package com.byfr0n.gamemodedetector.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.world.GameMode;

public class PlayerUtils {
    public static GameMode getPlayerGamemode(MinecraftClient client) {
        if (client.interactionManager != null) {
            return client.interactionManager.getCurrentGameMode();
        }
        return null;
    }
}