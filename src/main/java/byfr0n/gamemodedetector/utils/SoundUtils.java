package byfr0n.gamemodedetector.utils;

import byfr0n.gamemodedetector.Gamemodedetector;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import static byfr0n.gamemodedetector.Gamemodedetector.LOGGER;

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
