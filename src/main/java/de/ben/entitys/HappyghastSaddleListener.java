package de.ben.entitys;

import de.ben.enchantments.HarnessUtils;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;


import java.util.Map;

public class HappyghastSaddleListener implements Listener {

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Entity entity = event.getRightClicked();
        Player player = event.getPlayer();

        if (isHappyGhast(entity)) {
            ItemStack itemInHand = player.getInventory().getItemInMainHand();
            if (HarnessUtils.endsWithHarness(itemInHand.getType())) {
                Map<Enchantment, Integer> enchants = itemInHand.getEnchantments();

                if (enchants.containsKey(Enchantment.SOUL_SPEED)) {
                    int soulSpeedLevel = enchants.get(Enchantment.SOUL_SPEED);
                    entity.addScoreboardTag("speed_harness");
                    entity.addScoreboardTag("harness_level-" + soulSpeedLevel);
                }
            } else if (itemInHand.getType().equals(Material.SHEARS)) {
                removeHarnessSpeedBonus(entity);
            }
        }
    }
    private boolean isHappyGhast(Entity entity) {
        return entity != null && entity.getType().equals(EntityType.HAPPY_GHAST);
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

}
