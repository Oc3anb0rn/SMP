package de.ben.rockets;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RocketCommand implements TabExecutor {

    private final JavaPlugin plugin;

    public RocketCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player p) {
            if (!p.hasPermission("ouh.admin.rockets")) {
                p.sendMessage(ChatColor.RED + "Keine Rechte!");
                return true;
            }
        }

        boolean current = plugin.getConfig().getBoolean("rockets-enabled");
        boolean newState = !current;

        plugin.getConfig().set("rockets-enabled", newState);
        plugin.saveConfig();

        sender.sendMessage(ChatColor.GOLD + "Rockets sind jetzt: " +
                (newState ? ChatColor.GREEN + "ENABLED" : ChatColor.RED + "DISABLED"));

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        return List.of();
    }
}
