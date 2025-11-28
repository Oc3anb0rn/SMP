package de.ben.commands;

import de.ben.entitys.HappyGhastSpeedCommand;
import de.ben.entitys.HappyGhastSpeedSwitchCommand;
import de.ben.villager.CartographerTradeXPCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Properties;

public class CommandUtils {
    public static final String CARTOGRAPHRTXP_COMMAND ="cartographerxp";
    public static final String HAPPYGHAST_SPEED_MULTIPLIER_COMMAND ="happyghastspeedmultiplier";
    public static final String HARNESS_SPEE_MULTIPLIER_CONFIG = "happyGhastspeedMultiplier";
    public static final String HAPPYGHAST_SPEED_ENABLED_COMMAND ="happyhastpeed";

    public static boolean checkOpOrDeny(CommandSender sender, Properties messages) {
        if (sender instanceof Player && !sender.isOp()) {
            sender.sendMessage(messages.getProperty("permission.deny"));
            return false;
        }
        return true;
    }

    public static boolean checkPermission(CommandSender sender, Properties messages, String permission) {
        if (sender instanceof Player p) {
            if (!p.hasPermission(permission)) {
                p.sendMessage(messages.getProperty("permission.deny"));
                return true;
            }
        }
        return true;
    }



    public static void registerCommands(JavaPlugin plugin, Properties messages) {
        plugin.getCommand(CARTOGRAPHRTXP_COMMAND).setExecutor(new CartographerTradeXPCommand(plugin, messages));
        plugin.getCommand(HAPPYGHAST_SPEED_MULTIPLIER_COMMAND).setExecutor(new HappyGhastSpeedCommand(plugin, messages));
        plugin.getCommand(HAPPYGHAST_SPEED_ENABLED_COMMAND).setExecutor(new HappyGhastSpeedSwitchCommand(plugin, messages));

    }
}
