package de.ben.enchantments;

import org.bukkit.Material;

import java.util.Set;

public class HarnessUtils {

    private static final Set<Material> HARNESS_MATERIALS = Set.of(
            Material.BLACK_HARNESS,
            Material.BLUE_HARNESS,
            Material.WHITE_HARNESS,
            Material.BROWN_HARNESS,
            Material.GRAY_HARNESS,
            Material.CYAN_HARNESS,
            Material.GREEN_HARNESS,
            Material.YELLOW_HARNESS,
            Material.LIGHT_BLUE_HARNESS,
            Material.LIME_HARNESS,
            Material.RED_HARNESS,
            Material.PINK_HARNESS,
            Material.PURPLE_HARNESS
    );

    public static boolean isHarness(Material material) {
        return HARNESS_MATERIALS.contains(material);
    }

    public static boolean endsWithHarness(Material material) {
        return material.toString().endsWith("_HARNESS");
    }

}
