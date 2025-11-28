package de.ben.villager;

import de.ben.commands.CartographerTradeXPCommand;
import io.papermc.paper.event.player.PlayerTradeEvent;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class CartographerTradeXPListener implements Listener {

    private boolean xpEnabled = false;
    private final JavaPlugin plugin;

    public CartographerTradeXPListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerTrade(PlayerTradeEvent event) {
        this.xpEnabled = plugin.getConfig().getBoolean(CartographerTradeXPCommand.CONFIG_KEY_XP_ENABLED, false);

        if (!(event.getVillager() instanceof Villager villager)) return;
        if (villager.getProfession() != Villager.Profession.CARTOGRAPHER) return;
        if (!this.xpEnabled) return;

        if (villager.getProfession() == Villager.Profession.CARTOGRAPHER) {
            boolean isMaster = villager.getVillagerLevel() >= 5;
            event.setRewardExp(!isMaster);
        }
    }
}
