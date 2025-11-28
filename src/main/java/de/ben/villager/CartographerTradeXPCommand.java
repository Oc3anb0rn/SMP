package de.ben.villager;

import de.ben.commands.CommandUtils;
import de.ben.config.utils.ConfigUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Properties;

public class CartographerTradeXPCommand implements CommandExecutor {

    private final JavaPlugin plugin;
    private final Properties messages;

    public CartographerTradeXPCommand(JavaPlugin plugin, Properties messages) {
        this.plugin = plugin;
        this.messages = messages;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, @NotNull String label, String @NotNull [] args) {
        if (command.getName().equalsIgnoreCase(CommandUtils.CARTOGRAPHRTXP_COMMAND)) {
            if (!CommandUtils.checkOpOrDeny(sender, messages)) {
                return true;
            }
            boolean XPEnabled = ConfigUtils.getBoolean(ConfigUtils.CONFIG_KEY_XP_ENABLED);

            plugin.getConfig().set(ConfigUtils.CONFIG_KEY_XP_ENABLED, !XPEnabled);
            plugin.saveConfig();

            String msg = "XP sind jetzt " + (XPEnabled ? "aktiviert" : "deaktiviert") + "!";
            sender.sendMessage("§6Cartographer "+msg);
            return true;
        }
        return false;
    }
}