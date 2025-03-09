package com.byfr0n.gamemodedetector;

import com.byfr0n.gamemodedetector.modmenu.Config;
import com.byfr0n.gamemodedetector.notification.ChatNotification;
import com.byfr0n.gamemodedetector.rendering.NotificationRenderer;
import com.byfr0n.gamemodedetector.utils.PlayerUtils;
import com.byfr0n.gamemodedetector.utils.SoundUtils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.world.GameMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Gamemodedetector implements ClientModInitializer {
    public static final Config CONFIG = new Config();
    public static final Logger LOGGER = LoggerFactory.getLogger("Gamemode Detector");
    private static GameMode lastGamemode = null;
    @Override
    public void onInitializeClient() {

        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            NotificationRenderer.render(drawContext);
        });

        SoundUtils.registerSounds();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                GameMode currentGamemode = PlayerUtils.getPlayerGamemode(client);

                if (currentGamemode != lastGamemode) {
                    if (currentGamemode != null) {
                        SoundUtils.playGamemodeSound();

                        new ChatNotification("You switched gamemodes!").send();
                        NotificationRenderer.addNotification("Gamemode changed!");
                    }
                    lastGamemode = currentGamemode;
                }
            }
        });
    }
}