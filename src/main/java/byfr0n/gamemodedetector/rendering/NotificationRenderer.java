package byfr0n.gamemodedetector.rendering;

import byfr0n.gamemodedetector.Gamemodedetector;
import byfr0n.gamemodedetector.notification.ScreenNotification;
import byfr0n.gamemodedetector.utils.TweenUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.util.ArrayList;
import java.util.List;

public class NotificationRenderer {
    private static final List<ScreenNotification> activeNotifications = new ArrayList<>();
    private static final int NOTIFICATION_DURATION = 5000;
    private static final int NOTIFICATION_ANIMATION_DURATION = 500;
    private static final int NOTIFICATION_HEIGHT = 12;
    private static final int NOTIFICATION_SPACING = 5;
    private static final MinecraftClient client = MinecraftClient.getInstance();

    public static void addNotification(String message) {
        activeNotifications.add(new ScreenNotification(message, System.currentTimeMillis()));
    }

    public static void render(DrawContext context) {
        if (client.player == null) return;
        if (!Gamemodedetector.CONFIG.visualNotification) return;


        var y = 20;
        var currentTime = System.currentTimeMillis();

        activeNotifications.removeIf(notification -> currentTime - notification.getStartTime() > NOTIFICATION_DURATION);

        for (ScreenNotification notification : activeNotifications) {
            var timeSinceStart = currentTime - notification.getStartTime();

            var alpha = getAlpha(timeSinceStart);

            var x = (int) TweenUtils.ease(TweenUtils.Easing.QUAD_OUT, (float) Math.min(timeSinceStart, NOTIFICATION_ANIMATION_DURATION), -100.0f, 110.0f, (float) NOTIFICATION_ANIMATION_DURATION);

            var color = (int) (alpha * 255) << 24 | 0xFFFFFF;
            context.drawText(client.textRenderer, notification.getMessage(), x, y, color, true);

            y += NOTIFICATION_HEIGHT + NOTIFICATION_SPACING;
        }
    }

    private static float getAlpha(long timeSinceStart) {
        float alpha = 1.0f;
        if (timeSinceStart < NOTIFICATION_ANIMATION_DURATION) {
            alpha = TweenUtils.ease(TweenUtils.Easing.QUAD_OUT, (float) timeSinceStart, 0.0f, 1.0f, (float) NOTIFICATION_ANIMATION_DURATION);
        } else if (timeSinceStart > NOTIFICATION_DURATION - NOTIFICATION_ANIMATION_DURATION) {
            alpha = TweenUtils.ease(TweenUtils.Easing.QUAD_IN, (float) (timeSinceStart - (NOTIFICATION_DURATION - NOTIFICATION_ANIMATION_DURATION)), 1.0f, -1.0f, (float) NOTIFICATION_ANIMATION_DURATION);
        }
        return alpha;
    }
}