package me.odionwolf.headdrop;

import java.util.ArrayList;
import java.util.List;
import me.odionwolf.headdrop.events.HeadDropEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Repairable;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class HeadDrop extends JavaPlugin {
    public ItemStack axe;

    public String axename;

    public void onEnable() {
        new HeadDropEvent(this);
        Bukkit.addRecipe((Recipe)getRecipie());
    }

    public ShapedRecipe getRecipie() {
        ItemStack item = new ItemStack(Material.NETHERITE_AXE);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lHead Axe"));
        lore.add("");
        lore.add(ChatColor.translateAlternateColorCodes('&', "&aHeadless XXX"));
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setLore(lore);
        if (meta instanceof Damageable) {
            Damageable dmg = (Damageable)meta;
            dmg.setDamage(1931);
        }
        Repairable repair = (Repairable)meta;
        repair.setRepairCost(10000);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "head_axe");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("NSH", "WAZ", "HCN");
        recipe.setIngredient('S', Material.SKELETON_SKULL);
        recipe.setIngredient('W', Material.WITHER_SKELETON_SKULL);
        recipe.setIngredient('A', Material.NETHERITE_AXE);
        recipe.setIngredient('Z', Material.ZOMBIE_HEAD);
        recipe.setIngredient('C', Material.CREEPER_HEAD);
        recipe.setIngredient('N', Material.NAUTILUS_SHELL);
        recipe.setIngredient('H', Material.HEART_OF_THE_SEA);
        this.axe = item;
        this.axename = meta.getDisplayName();
        return recipe;
    }

    public void onDisable() {}
}
