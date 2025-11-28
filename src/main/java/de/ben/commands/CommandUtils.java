package de.ben.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Properties;

public class CommandUtils {

    public static boolean checkOpOrDeny(CommandSender sender, Properties messages) {
        if (sender instanceof Player && !sender.isOp()) {
            sender.sendMessage(messages.getProperty("permission.deny"));
            return false;
        }
        return true;
    }

    public static void registerCommands(JavaPlugin plugin, Properties messages) {
        plugin.getCommand(CartographerTradeXPCommand.CARTOGRAPHRTXP_COMMAND).setExecutor(new CartographerTradeXPCommand(plugin, messages));
        plugin.getCommand(HappyGhastSpeedCommand.HAPPYGHAST_SPEED_MULTIPLIER_COMMAND).setExecutor(new HappyGhastSpeedCommand(plugin, messages));
        plugin.getCommand(HappyGhastSpeedSwitchCommand.HAPPYGHAST_SPEED_ENABLED_COMMAND).setExecutor(new HappyGhastSpeedSwitchCommand(plugin, messages));

    }
}
