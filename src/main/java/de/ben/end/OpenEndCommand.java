package de.ben.end;

import de.ben.config.utils.ConfigUtils;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class OpenEndCommand implements TabExecutor {

    private final JavaPlugin plugin;

    public OpenEndCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String @NotNull [] args) {

        if (sender instanceof Player p) {
            if (!p.hasPermission("ouh.admin.end")) {
                p.sendMessage(NamedTextColor.RED + "Keine Rechte!");
                return true;
            }
        }

        boolean current = ConfigUtils.getBoolean(ConfigUtils.END_ENABLED_CONFIG);
        plugin.getConfig().set("end-enabled", !current);
        //todo: add set to ConfigUtils


        plugin.saveConfig();
        String msg ="End ist jetzt: " + (!current ? "§2GEÖFFNET" : "§4GESCHLOSSEN");
        sender.sendMessage("§9"+msg);
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        return List.of();
    }
}
