package com.byfr0n.gamemodedetector.utils;

import com.byfr0n.gamemodedetector.Gamemodedetector;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.byfr0n.gamemodedetector.Gamemodedetector.LOGGER;

public class SoundUtils {
    public static final SoundEvent GAMEMODE_SWITCH_SOUND = SoundEvent.of(Identifier.of("gamemodedetector", "gamemodeswitch"));

    public static void registerSounds() {
        Registry.register(Registries.SOUND_EVENT, Identifier.of("gamemodedetector", "gamemodeswitch"), GAMEMODE_SWITCH_SOUND);
    }
    public static void playGamemodeSound() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.world == null) {
            LOGGER.error("SoundUtils: Player or world is null, cannot play sound.");
            return;
        }

        if (Gamemodedetector.CONFIG.enableSound)
        {
            client.getSoundManager().play(PositionedSoundInstance.master(GAMEMODE_SWITCH_SOUND, 1.0f));
        }

    }
}
