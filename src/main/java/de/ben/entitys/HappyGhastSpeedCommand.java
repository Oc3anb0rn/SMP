package de.ben.entitys;

import de.ben.commands.CommandUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Properties;

public class HappyGhastSpeedCommand implements TabExecutor {
    private final JavaPlugin plugin;
    private final Properties messages;

    public HappyGhastSpeedCommand(JavaPlugin plugin, Properties messages) {
        this.plugin = plugin;
        this.messages = messages;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (!CommandUtils.checkOpOrDeny(sender, messages)){
            return false;
        }

        if (command.getName().equalsIgnoreCase(CommandUtils.HAPPYGHAST_SPEED_MULTIPLIER_COMMAND)) {
            double multiplier;

            if (args.length == 0) {
                multiplier = plugin.getConfig().getDouble(CommandUtils.HARNESS_SPEE_MULTIPLIER_CONFIG);
                sender.sendMessage(messages.getProperty("harnessspeedmultiplier.current") + multiplier);
                return true;
            }

            try {
                multiplier = Double.parseDouble(args[0]);
            } catch (NumberFormatException e) {
                sender.sendMessage(messages.getProperty("harnessspeedmultiplier.nonumber") + args[0]);
                return false;
            }

            plugin.getConfig().set(CommandUtils.HARNESS_SPEE_MULTIPLIER_CONFIG, multiplier);
            plugin.saveConfig();
            sender.sendMessage(messages.getProperty("harnessspeedmultiplier.current") + multiplier);

            return true;
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        return List.of();
    }
}
