package com.j0ach1mmall3.freeautomessage.api;

import com.j0ach1mmall3.freeautomessage.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 1/03/2016
 */
public abstract class PlayerBroadcaster extends Broadcaster {
    protected final String permission;

    public PlayerBroadcaster(Main plugin, String identifier, boolean random, int interval, List<String> messages, String permission) {
        super(plugin, identifier, random, interval, messages);
        this.permission = permission;
    }

    public String getPermission() {
        return this.permission;
    }

    @Override
    protected void broadcast(String message) {
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(p.hasPermission(this.permission)) this.broadcastInternal(p, message);
        }
    }

    protected abstract void broadcastInternal(Player p, String message);
}
