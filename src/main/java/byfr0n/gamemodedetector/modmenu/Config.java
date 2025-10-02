package byfr0n.gamemodedetector.modmenu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
    public boolean enableSound = true;
    public boolean visualNotification = true;
    public boolean chatNotification = true;
    public boolean debugLogs = true;
    public boolean ignoreLocalPlayer = false;

    private static final File CONFIG_FILE = new File(FabricLoader.getInstance().getConfigDir().toFile(), "gamemodedetector/config.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public void save() {
        try {
            CONFIG_FILE.getParentFile().mkdirs();
            try (var writer = new FileWriter(CONFIG_FILE)) {
                GSON.toJson(this, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Config load() {
        if (CONFIG_FILE.exists()) {
            try (var reader = new FileReader(CONFIG_FILE)) {
                return GSON.fromJson(reader, Config.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new Config();
    }
}