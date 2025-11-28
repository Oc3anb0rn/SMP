package de.ben.villager;

import de.ben.commands.CommandUtils;
import de.ben.config.utils.ConfigUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Properties;

public class CartographerTradeXPCommand implements TabExecutor {

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
            //todo: add set to ConfigUtils
            plugin.saveConfig();

            String msg = "XP sind jetzt " + (XPEnabled ? "aktiviert" : "deaktiviert") + "!";
            sender.sendMessage("§6Cartographer "+msg);
            return true;
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        return List.of();
    }
}