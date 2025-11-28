package de.ben.end;

import de.ben.config.utils.ConfigUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World.Environment;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.Properties;

public class EndBlocker implements Listener {

    private final Properties messages;

    public EndBlocker(Properties messages) {
        this.messages = messages;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (ConfigUtils.getBoolean(ConfigUtils.END_ENABLED_CONFIG)) {
            return;
        }
        if (event.getHand() != EquipmentSlot.HAND) return;

        Block block = event.getClickedBlock();
        ItemStack item = event.getItem();

        if (block == null || item == null) return;

        if (block.getType() == Material.END_PORTAL_FRAME && item.getType() == Material.ENDER_EYE) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("§4" + this.messages.getProperty("end.deactivated"));
        }
    }

    @EventHandler
    public void onPortal(PlayerPortalEvent event) {
        if (ConfigUtils.getBoolean(ConfigUtils.END_ENABLED_CONFIG)) return;

        Location to = event.getTo();
        if (to.getWorld() != null && to.getWorld().getEnvironment() == Environment.THE_END) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("§4" + this.messages.getProperty("end.deactivated"));
        }
    }
}
