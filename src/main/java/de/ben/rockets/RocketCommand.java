package de.ben.rockets;

import de.ben.config.utils.ConfigUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RocketCommand implements TabExecutor {

    public RocketCommand() {
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String @NotNull [] args) {

        if (sender instanceof Player p) {
            if (!p.hasPermission("ouh.admin.rockets")) {
                p.sendMessage(ChatColor.RED + "Keine Rechte!");
                return true;
            }
        }

        boolean newState = !ConfigUtils.getBoolean(ConfigUtils.ROCKETS_ENABLED);
        ConfigUtils.setBoolean(ConfigUtils.ROCKETS_ENABLED, newState);

        sender.sendMessage(ChatColor.GOLD + "Rockets sind jetzt: " +
                (newState ? ChatColor.GREEN + "ENABLED" : ChatColor.RED + "DISABLED"));

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        return List.of();
    }
}
