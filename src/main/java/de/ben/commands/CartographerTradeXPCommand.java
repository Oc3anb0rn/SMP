package de.ben.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Properties;

public class CartographerTradeXPCommand implements CommandExecutor {

    public static final String CONFIG_KEY_XP_ENABLED = "cartographerXpEnabled";
    public static final String CARTOGRAPHRTXP_COMMAND ="cartographerxp";
    public static final String CARTOGRAPHRTXP_ANNOUNCEMENT = "cartographerXPAnnouncement";

    private final JavaPlugin plugin;
    private final Properties messages;

    public CartographerTradeXPCommand(JavaPlugin plugin, Properties messages) {
        this.plugin = plugin;
        this.messages = messages;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase(CARTOGRAPHRTXP_COMMAND)) {
//            if (!CommandUtils.checkOpOrDeny(sender, messages)) {
//                return true;
//            }
            boolean XPEnabled = plugin.getConfig().getBoolean(CONFIG_KEY_XP_ENABLED, true);
            plugin.getConfig().set(CONFIG_KEY_XP_ENABLED, !XPEnabled);
            plugin.saveConfig();

            String msg = "XP sind jetzt " + (XPEnabled ? "aktiviert" : "deaktiviert") + "!";
//            if (plugin.getConfig().getBoolean(CARTOGRAPHRTXP_ANNOUNCEMENT, false)) {
//                AnnounceUtil.sendTitleAll("§6Cartographer ", msg, 10, 60, 10);
//            } else {
                sender.sendMessage("§6Cartographer "+msg);
//            }

            return true;
        }
        return false;
    }
}