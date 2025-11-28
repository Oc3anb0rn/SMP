package de.ben.enchantments;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.view.AnvilView;

public class HarnessEnchantAnvilListener implements Listener {

    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        ItemStack firstItem = event.getInventory().getFirstItem();
        ItemStack secondItem = event.getInventory().getSecondItem();

        if (firstItem == null || secondItem == null) return;
        if(!HarnessUtils.endsWithHarness(firstItem.getType())) return;

        int harnessSoulSpeedLevel = getSoulSpeedLevel(firstItem);
        int secondItemSoulSpeedLevel = getSoulSpeedLevel(secondItem);

        if (harnessSoulSpeedLevel  == secondItemSoulSpeedLevel && harnessSoulSpeedLevel < 3) {
            event.setResult(enchantItem(firstItem, harnessSoulSpeedLevel+1, event));
        } else if (harnessSoulSpeedLevel == 0) {
            event.setResult(enchantItem(firstItem, secondItemSoulSpeedLevel, event ));
        } else {
            event.setResult(null);
        }
    }

    private ItemStack enchantItem(ItemStack item, int level, PrepareAnvilEvent event) {
        ItemStack result = item.clone();
        ItemMeta meta = result.getItemMeta();
        meta.addEnchant(Enchantment.SOUL_SPEED, level, false);
        result.setItemMeta(meta);

        AnvilView anvilInventory = event.getView();
        anvilInventory.setRepairCost(20);
        return result;
    }

    private int getSoulSpeedLevel(ItemStack item) {
        if (item == null) return 0;
        int directLevel = item.getEnchantmentLevel(Enchantment.SOUL_SPEED);
        if (directLevel > 0) return directLevel;
        if (item.hasItemMeta() && item.getItemMeta() instanceof EnchantmentStorageMeta meta) {
            int storedLevel = meta.getStoredEnchantLevel(Enchantment.SOUL_SPEED);
            if (storedLevel > 0) return storedLevel;
        }
        return 0;
    }
}