package de.ben.entitys;

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

public class HappyGhastSpeedSwitchCommand implements TabExecutor {

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
            boolean speedEnabled = ConfigUtils.getBoolean(HAPPYGHAST_SPEED_ENABLED_CONFIG);
            plugin.getConfig().set(HAPPYGHAST_SPEED_ENABLED_CONFIG, !speedEnabled);
            //todo: add set to ConfigUtils
            plugin.saveConfig();

            String msg ="Speed ist jetzt " + (speedEnabled ? "deaktiviert" : "aktiviert") + "!";
            sender.sendMessage("§6HappyGhast " + msg);
            return true;
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        return List.of();
    }
}
