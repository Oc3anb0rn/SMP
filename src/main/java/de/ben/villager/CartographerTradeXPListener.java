package de.ben.villager;

import de.ben.config.utils.ConfigUtils;
import io.papermc.paper.event.player.PlayerTradeEvent;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class CartographerTradeXPListener implements Listener {

    public CartographerTradeXPListener(JavaPlugin plugin) {
    }

    @EventHandler
    public void onPlayerTrade(PlayerTradeEvent event) {
        boolean xpEnabled = ConfigUtils.getBoolean(ConfigUtils.CONFIG_KEY_XP_ENABLED);

        if (!(event.getVillager() instanceof Villager villager)) return;
        if (villager.getProfession() != Villager.Profession.CARTOGRAPHER) return;
        if (!xpEnabled) return;

        if (villager.getProfession() == Villager.Profession.CARTOGRAPHER) {
            boolean isMaster = villager.getVillagerLevel() >= 5;
            event.setRewardExp(!isMaster);
        }
    }
}
