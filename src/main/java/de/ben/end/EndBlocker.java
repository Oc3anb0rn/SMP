package de.ben.end;

import de.ben.commands.CommandUtils;
import de.ben.config.utils.ConfigUtils;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World.Environment;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Properties;

public class EndBlocker implements Listener {

    private final JavaPlugin plugin;
    private final Properties messages;

    public EndBlocker(JavaPlugin plugin, Properties messages) {
        this.plugin = plugin;
        this.messages = messages;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        boolean allowInsertion = plugin.getConfig().getBoolean(ConfigUtils.END_ENABLED_CONFIG, false);
        if (allowInsertion) {
            return;
        }
        if (event.getHand() != EquipmentSlot.HAND) return;

        Block block = event.getClickedBlock();
        ItemStack item = event.getItem();

        if (block == null || item == null) return;

        if (block.getType() == Material.END_PORTAL_FRAME && item.getType() == Material.ENDER_EYE) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(messages.getProperty("end.deactivated"));
        }
    }

    @EventHandler
    public void onPortal(PlayerPortalEvent e) {
        Player p = e.getPlayer();

        if (plugin.getConfig().getBoolean("end-enabled")) return;

        Location to = e.getTo();
        if (to.getWorld() != null && to.getWorld().getEnvironment() == Environment.THE_END) {

            e.setCancelled(true);
            p.sendMessage(NamedTextColor.RED + this.messages.getProperty("end.deactivated"));
        }
    }
}
