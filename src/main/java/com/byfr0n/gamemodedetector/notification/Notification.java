package com.byfr0n.gamemodedetector.notification;

public abstract class Notification {
    protected final String message;

    public Notification(String message) {
        this.message = message;
    }

    public abstract void send();
}
