package de.ben;

import de.ben.rockets.RocketCommand;
import de.ben.rockets.RocketListener;
import de.ben.end.OpenEndCommand;
import de.ben.end.EndBlocker;
import de.ben.villager.CartographerTradeXPCommand;
import de.ben.villager.CartographerTradeXPListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class smp extends JavaPlugin {

    private Properties messages;

    @Override
    public void onEnable() {

        this.getCommand(CartographerTradeXPCommand.CARTOGRAPHRTXP_COMMAND).setExecutor(new CartographerTradeXPCommand(this, messages));
        getServer().getPluginManager().registerEvents(new CartographerTradeXPListener(this), this);

        // ------------------ CONFIG ------------------
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);

        // ------------------ ADMIN MENU (/admin) ------------------
        AdminMenu adminMenu = new AdminMenu(this);
        Bukkit.getPluginManager().registerEvents(adminMenu, this);
        getCommand("admin").setExecutor(adminMenu);

        // ------------------ VANISH (/vanish) ------------------
        VanishManager vanishManager = new VanishManager();
        Bukkit.getPluginManager().registerEvents(vanishManager, this);
        getCommand("vanish").setExecutor(vanishManager);
        getCommand("vanish").setTabCompleter(vanishManager);

        // ------------------ FIRST JOIN (/help) ------------------
        FirstJoin firstJoin = new FirstJoin(this);
        Bukkit.getPluginManager().registerEvents(firstJoin, this);
        getCommand("help").setExecutor(firstJoin);

        // ------------------ MESSAGES (Prefixes, Death, etc.) ------------------
        Bukkit.getPluginManager().registerEvents(new Messages(), this);

        // ------------------ TELEPORT SYSTEM (/tpa usw.) ------------------
        TeleportRequest tpa = new TeleportRequest();
        tpa.init(this);
        Bukkit.getPluginManager().registerEvents(tpa, this);
        getCommand("tpa").setExecutor(tpa);
        getCommand("tpahere").setExecutor(tpa);
        getCommand("tpaccept").setExecutor(tpa);
        getCommand("tpdeny").setExecutor(tpa);
        getCommand("tpa").setTabCompleter(tpa);
        getCommand("tpahere").setTabCompleter(tpa);

        // ------------------ ROCKETS SYSTEM (/rockets) ------------------
        getCommand("rockets").setExecutor(new RocketCommand(this));
        Bukkit.getPluginManager().registerEvents(new RocketListener(this), this);

        // ------------------ END OPEN/CLOSE SYSTEM (/openend) ------------------
        getCommand("openend").setExecutor(new OpenEndCommand(this));
        Bukkit.getPluginManager().registerEvents(new EndBlocker(this), this);

        // ------------------ LOG ------------------
        getLogger().info("SMP Plugin gestartet!");
        getLogger().info("Rockets-enabled: " + getConfig().getBoolean("rockets-enabled"));
        getLogger().info("End-enabled: " + getConfig().getBoolean("end-enabled"));
    }

    @Override
    public void onDisable() {
        saveConfig();
        getLogger().info("SMP Plugin gestoppt.");
    }

    private boolean loadMessages() {
        messages = new Properties();
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("messages.properties")) {
            if (in != null) {
                messages.load(in);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Properties getMessages() {
        return messages;
    }

}
