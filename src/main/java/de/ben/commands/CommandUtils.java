package de.ben.commands;

import de.ben.admin.gui.AdminMenu;
import de.ben.admin.utils.VanishManager;
import de.ben.end.OpenEndCommand;
import de.ben.entitys.HappyGhastSpeedCommand;
import de.ben.entitys.HappyGhastSpeedSwitchCommand;
import de.ben.rockets.RocketCommand;
import de.ben.villager.CartographerTradeXPCommand;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.PluginCommand;
import java.util.Properties;

public class CommandUtils {
    public static final String CARTOGRAPHRTXP_COMMAND ="cartographerxp";
    public static final String HAPPYGHAST_SPEED_MULTIPLIER_COMMAND ="happyghastspeedmultiplier";
    public static final String HAPPYGHAST_SPEED_ENABLED_COMMAND ="happyhastpeed";
    public static final String ADMIN_COMMAND ="admin";
    public static final String VANISH_COMMAND ="vanish";
    public static final String HELP_COMMAND ="help";
    public static final String TPA_COMMAND ="tpa";
    public static final String TPAHERE_COMMAND ="tpahere";
    public static final String TPACCEPT_COMMAND ="tpaccept";
    public static final String TPDENEY_COMMAND ="tpdeny";
    public static final String ROCKETS_COMMAND ="rockets";
    public static final String OPENEND_COMMAND ="openend";

    public static final String HARNESS_SPEE_MULTIPLIER_CONFIG = "happyGhastspeedMultiplier";

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
        registerCommand(plugin, CARTOGRAPHRTXP_COMMAND, new CartographerTradeXPCommand(plugin, messages));
        registerCommand(plugin, HAPPYGHAST_SPEED_MULTIPLIER_COMMAND, new HappyGhastSpeedCommand(plugin, messages));
        registerCommand(plugin, HAPPYGHAST_SPEED_ENABLED_COMMAND, new HappyGhastSpeedSwitchCommand(plugin, messages));
        registerCommand(plugin, ADMIN_COMMAND, new AdminMenu(plugin));
        registerCommand(plugin, VANISH_COMMAND, new VanishManager());
        registerCommand(plugin, ROCKETS_COMMAND, new RocketCommand(plugin));
        registerCommand(plugin, VANISH_COMMAND, new VanishManager());
        registerCommand(plugin, OPENEND_COMMAND, new OpenEndCommand(plugin));
    }

    private static void registerCommand(JavaPlugin plugin, String name, TabExecutor executor) {
        PluginCommand cmd = plugin.getCommand(name);
        if (cmd != null) {
            cmd.setExecutor(executor);
            cmd.setTabCompleter(executor);
        } else {
            plugin.getLogger().warning("Command '" + name + "' not found in plugin.yml!");
        }
    }

}
