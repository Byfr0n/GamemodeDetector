package byfr0n.gamemodedetector.modmenu;

import byfr0n.gamemodedetector.Gamemodedetector;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return this::createConfigScreen;
    }

    private Screen createConfigScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.of("Gamemode Detector Settings"));

        builder.getOrCreateCategory(Text.of("General"))
                .addEntry(builder.entryBuilder()
                        .startBooleanToggle(Text.of("Sound Effect"), Gamemodedetector.CONFIG.enableSound)
                        .setSaveConsumer(value -> Gamemodedetector.CONFIG.enableSound = value)
                        .build());

        builder.getOrCreateCategory(Text.of("General"))
                .addEntry(builder.entryBuilder()
                        .startBooleanToggle(Text.of("Visual Notifications"), Gamemodedetector.CONFIG.visualNotification)
                        .setSaveConsumer(value -> Gamemodedetector.CONFIG.visualNotification = value)
                        .build());

        builder.getOrCreateCategory(Text.of("General"))
                .addEntry(builder.entryBuilder()
                        .startBooleanToggle(Text.of("Chat Notifications"), Gamemodedetector.CONFIG.chatNotification)
                        .setSaveConsumer(value -> Gamemodedetector.CONFIG.chatNotification = value)
                        .build());

        builder.getOrCreateCategory(Text.of("General"))
                .addEntry(builder.entryBuilder()
                        .startBooleanToggle(Text.of("Debug"), Gamemodedetector.CONFIG.debugLogs)
                        .setSaveConsumer(value -> Gamemodedetector.CONFIG.debugLogs = value)
                        .build());

        return builder.build();
    }
}
