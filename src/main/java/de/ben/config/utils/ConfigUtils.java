package de.ben.config.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigUtils {

    public static final String END_ENABLED_CONFIG ="end-enabled";
    public static final String CONFIG_KEY_XP_ENABLED = "cartographerXpEnabled";
    public static final String ROCKETS_ENABLED = "rockets-enabled";
    public static final String ROCKETS_COOLDOWN_INT = "cooldown-seconds";



    private static JavaPlugin plugin;

    private ConfigUtils() {}

    public static void init(JavaPlugin pl) {
        plugin = pl;
    }

    private static FileConfiguration config() {
        return plugin.getConfig();
    }

    public static boolean getBoolean(String key) {
        return config().getBoolean(key);
    }

    public static void setBoolean(String key, boolean value) {
        config().set(key, value);
        safeConfig();
    }

    public static void setDouble(String key, double value) {
        config().set(key, value);
        safeConfig();
    }

    public static int getInt(String key) {
        return config().getInt(key);
    }

    public static double getDouble(String key) {
        return config().getDouble(key);
    }

    private static void safeConfig() {
        plugin.saveConfig();
    }
}

