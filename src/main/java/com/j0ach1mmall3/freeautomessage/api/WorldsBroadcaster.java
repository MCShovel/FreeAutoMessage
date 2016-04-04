package com.j0ach1mmall3.freeautomessage.api;

import com.j0ach1mmall3.freeautomessage.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/04/2016
 */
public abstract class WorldsBroadcaster extends PlayerBroadcaster {
    private final List<String> enabledWorlds;

    public WorldsBroadcaster(Main plugin, String identifier, boolean random, int interval, List<String> messages, String permission, List<String> enabledWorlds) {
        super(plugin, identifier, random, interval, messages, permission);
        this.enabledWorlds = enabledWorlds;
    }

    public List<String> getEnabledWorlds() {
        return this.enabledWorlds;
    }

    @Override
    protected final void broadcast(String message) {
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(p.hasPermission(this.permission) && this.enabledWorlds.contains(p.getWorld().getName())) this.broadcastInternal(p, message);
        }
    }

    @Override
    protected abstract void broadcastInternal(Player p, String message);
}
