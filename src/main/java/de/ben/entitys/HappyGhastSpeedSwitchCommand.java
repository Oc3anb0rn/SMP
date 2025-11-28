package de.ben.entitys;

import de.ben.commands.CommandUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Properties;

public class HappyGhastSpeedSwitchCommand implements CommandExecutor {

    public static final String HAPPYGHAST_SPEED_ENABLED_CONFIG= "happyGhastSpeedEnabled";

    private final JavaPlugin plugin;
    private final Properties messages;

    public HappyGhastSpeedSwitchCommand(JavaPlugin plugin, Properties messages) {
        this.plugin = plugin;
        this.messages = messages;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        if (command.getName().equalsIgnoreCase(CommandUtils.HAPPYGHAST_SPEED_ENABLED_COMMAND)) {
            if (!CommandUtils.checkOpOrDeny(sender, messages)) {
                return true;
            }
            boolean speedEnabled = plugin.getConfig().getBoolean(HAPPYGHAST_SPEED_ENABLED_CONFIG, true);
            plugin.getConfig().set(HAPPYGHAST_SPEED_ENABLED_CONFIG, !speedEnabled);
            plugin.saveConfig();

            String msg ="Speed ist jetzt " + (speedEnabled ? "deaktiviert" : "aktiviert") + "!";
            sender.sendMessage("§6HappyGhast " + msg);
            return true;
        }
        return false;
    }
}
