package me.lojosho.belowbedrock;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

public final class BelowBedrock extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        CheckPlayerBelowBedrock();
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public @NotNull Logger getSLF4JLogger() {
        return super.getSLF4JLogger();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void CheckPlayerBelowBedrock() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.getLocation().getY() < 0) {
                        if (player.getLocation().getWorld().getName().equalsIgnoreCase("spawn")) {
                            player.teleport(player.getWorld().getSpawnLocation());
                        } else {
                            player.teleport(player.getLocation().toHighestLocation());
                        }
                    }
                }
            }
        }.runTaskTimer(this, 0, 50);
    }
}
