package byfr0n.gamemodedetector.notification;

public class ScreenNotification {
    private final String message;
    private final long startTime;

    public ScreenNotification(String message, long startTime) {
        this.message = message;
        this.startTime = startTime;
    }

    public String getMessage() {
        return message;
    }

    public long getStartTime() {
        return startTime;
    }
}