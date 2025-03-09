package com.byfr0n.gamemodedetector;

import com.byfr0n.gamemodedetector.modmenu.Config;
import com.byfr0n.gamemodedetector.notification.ChatNotification;
import com.byfr0n.gamemodedetector.rendering.NotificationRenderer;
import com.byfr0n.gamemodedetector.utils.PlayerUtils;
import com.byfr0n.gamemodedetector.utils.SoundUtils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.GameMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Gamemodedetector implements ClientModInitializer {
    public static final Config CONFIG = new Config();
    public static final Logger LOGGER = LoggerFactory.getLogger("Gamemode Detector");

    private static final Map<UUID, GameMode> lastGamemodes = new HashMap<>();

    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            NotificationRenderer.render(drawContext);
        });

        SoundUtils.registerSounds();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.world == null || client.getNetworkHandler() == null) return;

            for (PlayerEntity player : client.world.getPlayers()) {
                if (player == client.player) continue;

                GameMode currentGamemode = PlayerUtils.getPlayerGamemode(player);
                UUID playerId = player.getUuid();

                if (currentGamemode != null && currentGamemode != lastGamemodes.get(playerId)) {
                    SoundUtils.playGamemodeSound();
                    new ChatNotification(player.getName().getString() + " switched to " + currentGamemode.getName()).send();
                    NotificationRenderer.addNotification(player.getName().getString() + " changed gamemode!");

                    lastGamemodes.put(playerId, currentGamemode);
                }
            }
        });
    }
}
