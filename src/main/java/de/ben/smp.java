package de.ben;

import de.ben.admin.utils.VanishManager;
import de.ben.commands.CommandUtils;
import de.ben.config.utils.ConfigUtils;
import de.ben.enchantments.HarnessEnchantAnvilListener;
import de.ben.entitys.HappyghastMountListener;
import de.ben.entitys.HappyghastSaddleListener;
import de.ben.admin.gui.AdminMenu;
import de.ben.messages.FirstJoin;
import de.ben.messages.Messages;
import de.ben.player.utils.TeleportRequest;
import de.ben.rockets.RocketListener;
import de.ben.end.EndBlocker;
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
        // creates config.yml if it does not already exist
        saveDefaultConfig();

        // ensures that all default values are active
        getConfig().options().copyDefaults(true);

        //initializes the configuration class so that it can be used.
        ConfigUtils.init(this);

        // ensures that messages.properties is loaded
        loadMessages();

        //all own commands are registered
        CommandUtils.registerCommands(this, this.getMessages());

        //registers all own events
        registerCustomEvents();



        // todo: unten noch alles auslagern!



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

        // ------------------ LOG ------------------
        getLogger().info("SMP Plugin gestartet!");
        getLogger().info("Rockets-enabled: " + ConfigUtils.getBoolean(ConfigUtils.ROCKETS_ENABLED));
        getLogger().info("End-enabled: " + ConfigUtils.getBoolean(ConfigUtils.END_ENABLED_CONFIG));
    }

    private void registerCustomEvents() {
        getServer().getPluginManager().registerEvents(new CartographerTradeXPListener(this), this);
        getServer().getPluginManager().registerEvents(new HarnessEnchantAnvilListener(), this);
        getServer().getPluginManager().registerEvents(new HappyghastSaddleListener(), this);
        getServer().getPluginManager().registerEvents(new HappyghastMountListener(this.getMessages()), this);
        getServer().getPluginManager().registerEvents(new EndBlocker(this.getMessages()), this);
        getServer().getPluginManager().registerEvents(new AdminMenu(), this);
        getServer().getPluginManager().registerEvents(new VanishManager(), this);
        getServer().getPluginManager().registerEvents(new RocketListener(), this);
    }

    @Override
    public void onDisable() {
        saveConfig();
        getLogger().info("SMP Plugin gestoppt.");
    }

    private void loadMessages() {
        messages = new Properties();
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("messages.properties")) {
            if (in != null) {
                messages.load(in);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Properties getMessages() {
        return messages;
    }
}
