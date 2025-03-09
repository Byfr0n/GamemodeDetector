package com.byfr0n.gamemodedetector.notification;

import com.byfr0n.gamemodedetector.Gamemodedetector;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class ChatNotification extends Notification {
    public ChatNotification(String message) {
        super(message);
    }

    @Override
    public void send() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null) {
            if (Gamemodedetector.CONFIG.chatNotification)
            {
                client.player.sendMessage(Text.of("[GamemodeDetector] " + message), false);
            }
        }
    }
}
