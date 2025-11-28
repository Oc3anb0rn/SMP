package de.ben.entitys;

import de.ben.commands.CommandUtils;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityMountEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Properties;

public class HappyghastMountListener implements Listener {

    private final JavaPlugin plugin;
    private final Properties messages;

    public HappyghastMountListener(JavaPlugin plugin, Properties messages) {
        this.plugin = plugin;
        this.messages = messages;
    }

    @EventHandler
    public void onEntityMount(EntityMountEvent event) {
        Entity mount = event.getMount();
        Entity rider = event.getEntity();

        if (isHappyghast(mount) && rider instanceof Player player) {
            if (plugin.getConfig().getBoolean(HappyGhastSpeedSwitchCommand.HAPPYGHAST_SPEED_ENABLED_CONFIG, false)) {
                double speed = applyHarnessSpeedBonus(mount);
                rider.sendMessage(messages.getProperty("happyghast.speed") + speed);
            } else {
                removeHarnessSpeedBonus(mount);
            }
        }
    }

    private boolean isHappyghast(Entity entity) {
        return entity != null && entity.getType().equals(EntityType.HAPPY_GHAST);
    }

    public double applyHarnessSpeedBonus(Entity happyghast) {
        if (!happyghast.getScoreboardTags().contains("speed_harness")) return 0;
        int harnessSpeed = getGhastHarnessLevel(happyghast); // soulspeed enchant 1-3

        if (happyghast instanceof LivingEntity livingGhast) {
            AttributeInstance speedAttribute = livingGhast.getAttribute(Attribute.MOVEMENT_SPEED);
            AttributeInstance flyAttribute = livingGhast.getAttribute(Attribute.FLYING_SPEED);
            assert speedAttribute != null;
            double basespeed = speedAttribute.getBaseValue();

            double multiplyer = plugin.getConfig().getDouble(CommandUtils.HARNESS_SPEE_MULTIPLIER_CONFIG);
            double formulaResult = basespeed * (2.5 * Math.pow(harnessSpeed, 2) - 2.5 * harnessSpeed + 4.8) + 0.01 * multiplyer;
            formulaResult = formulaResult/10;
            assert flyAttribute != null;
            flyAttribute.setBaseValue(formulaResult);
            return formulaResult;
        }
        return 0;
    }
    public void removeHarnessSpeedBonus(Entity happyghast) {
        if (!happyghast.getScoreboardTags().contains("speed_harness")) return;
        if (happyghast instanceof LivingEntity livingGhast) {
            AttributeInstance speedAttribute = livingGhast.getAttribute(Attribute.MOVEMENT_SPEED);
            AttributeInstance flyAttribute = livingGhast.getAttribute(Attribute.FLYING_SPEED);

            if (speedAttribute != null && flyAttribute != null) {
                flyAttribute.setBaseValue(speedAttribute.getBaseValue());
            }
        }
    }

    private int getGhastHarnessLevel(Entity happyghast) {
        for (String tag : happyghast.getScoreboardTags()) {
            if (tag.startsWith("harness_level-")) {
                String levelStr = tag.substring("harness_level-".length());
                try {
                    return Integer.parseInt(levelStr);
                } catch (NumberFormatException e) {
                    return 0;
                }
            }
        }
        return 0;
    }
}
