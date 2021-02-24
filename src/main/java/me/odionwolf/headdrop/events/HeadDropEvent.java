package me.odionwolf.headdrop.events;

import me.odionwolf.headdrop.HeadDrop;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;

public class HeadDropEvent implements Listener {
    private HeadDrop headDrop;

    public HeadDropEvent(HeadDrop headDrop) {
        this.headDrop = headDrop;
        Bukkit.getPluginManager().registerEvents(this, (Plugin)headDrop);
    }

    public static Boolean percentChance(double chance) {
        return (Math.random() <= chance);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        ItemStack axe = this.headDrop.axe;
        Player killer = event.getEntity().getKiller();
        if (killer != null &&
                killer.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(this.headDrop.axename) &&
                percentChance(0.2D)) {
            Player player = event.getEntity();
            World world = player.getWorld();
            Location location = player.getLocation();
            ItemStack item = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta meta = (SkullMeta)item.getItemMeta();
            meta.setOwningPlayer(player);
            item.setItemMeta(meta);
            world.dropItemNaturally(location, item);
        }
    }
}
