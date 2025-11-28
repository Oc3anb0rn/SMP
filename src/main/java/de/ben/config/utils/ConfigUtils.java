package de.ben.config.utils;

import org.bukkit.plugin.java.JavaPlugin;

public class ConfigUtils {

    public static final String END_ENABLED_CONFIG ="end-enabled";
//    ...

    public static boolean getBoolean(String configKey, JavaPlugin plugin) {
        return plugin.getConfig().getBoolean(configKey);
    }

    public static boolean getDubble(String configKey, JavaPlugin plugin) {
        return plugin.getConfig().getBoolean(configKey);
    }

}
