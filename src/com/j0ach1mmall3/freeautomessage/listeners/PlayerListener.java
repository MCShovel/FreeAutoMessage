package com.j0ach1mmall3.freeautomessage.listeners;

import com.j0ach1mmall3.freeautomessage.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 3/02/16
 */
public class PlayerListener implements Listener {
    private final Main plugin;

    public PlayerListener(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent e) {
        if(this.plugin.isBossBarAPI()) org.inventivetalent.bossbar.BossBarAPI.removeBar(e.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if(this.plugin.isBossBarAPI()) org.inventivetalent.bossbar.BossBarAPI.removeBar(e.getPlayer());
    }

    @EventHandler
    public void onKick(PlayerKickEvent e) {
        if(this.plugin.isBossBarAPI()) org.inventivetalent.bossbar.BossBarAPI.removeBar(e.getPlayer());
    }
}
