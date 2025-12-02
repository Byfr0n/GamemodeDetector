package byfr0n.gamemodedetector.notification;

import byfr0n.gamemodedetector.Gamemodedetector;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

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
                client.player.sendMessage(
                        Text.literal("[GamemodeDetector] ")
                                .formatted(Formatting.BLUE)
                                .append(Text.literal(message).formatted(Formatting.WHITE)),
                        false
                );
            }
        }
    }
}
