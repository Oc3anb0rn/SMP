package de.ben.end;

import de.ben.messages.AnnounceUtil;
import de.ben.smp;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.jetbrains.annotations.NotNull;

public class OpenEndCommand implements CommandExecutor {

    private final smp plugin;

    public OpenEndCommand(smp plugin) {
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

        boolean current = plugin.getConfig().getBoolean("end-enabled");
        plugin.getConfig().set("end-enabled", !current);
        plugin.saveConfig();
        String msg ="End ist jetzt: " + (!current ? "§2GEÖFFNET" : "§4GESCHLOSSEN");
        sender.sendMessage("§9"+msg);
        return true;
    }
}
