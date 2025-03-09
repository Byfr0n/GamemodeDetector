package com.byfr0n.gamemodedetector.utils;

public class TweenUtils {

    public enum Easing {
        LINEAR, QUAD_IN, QUAD_OUT, QUAD_INOUT,
        CUBIC_IN, CUBIC_OUT, CUBIC_INOUT
    }

    public static float ease(Easing easing, float time, float start, float change, float duration) {
        switch (easing) {
            case QUAD_IN:
                time /= duration;
                return change * time * time + start;
            case QUAD_OUT:
                time /= duration;
                return -change * time * (time - 2) + start;
            case QUAD_INOUT:
                time /= duration / 2;
                if (time < 1) return (change / 2) * (time * time) + start;
                time--;
                return (-change / 2) * (time * (time - 2) - 1) + start;
            case CUBIC_IN:
                time /= duration;
                return change * time * time * time + start;
            case CUBIC_OUT:
                time = time / duration - 1;
                return change * (time * time * time + 1) + start;
            case CUBIC_INOUT:
                time /= duration / 2;
                if (time < 1) return (change / 2) * time * time * time + start;
                time -= 2;
                return (change / 2) * (time * time * time + 2) + start;
            case LINEAR:
            default:
                return change * (time / duration) + start;
        }
    }
}