package byfr0n.gamemodedetector;

import byfr0n.gamemodedetector.modmenu.Config;
import byfr0n.gamemodedetector.notification.ChatNotification;
import byfr0n.gamemodedetector.rendering.NotificationRenderer;
import byfr0n.gamemodedetector.utils.PlayerUtils;
import byfr0n.gamemodedetector.utils.SoundUtils;
import byfr0n.gamemodedetector.utils.TabRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.GameMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Gamemodedetector implements ClientModInitializer {
    public static final Config CONFIG = Config.load();
    public static final Logger LOGGER = LoggerFactory.getLogger("Gamemode Detector");

    public static TabRenderer tabRenderer = new TabRenderer();

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
                if (CONFIG.ignoreLocalPlayer && player == client.player) continue;

                GameMode currentGamemode = PlayerUtils.getPlayerGamemode(player);
                UUID playerId = player.getUuid();

                if (currentGamemode != null && currentGamemode != lastGamemodes.get(playerId)) {
                    SoundUtils.playGamemodeSound();
                    new ChatNotification(player.getName().getString() + " switched to " + currentGamemode.getTranslatableName().getString()).send();
                    NotificationRenderer.addNotification(player.getName().getString() + " changed to " + currentGamemode.getTranslatableName().getString());

                    lastGamemodes.put(playerId, currentGamemode);
                }
            }
        });
    }
}