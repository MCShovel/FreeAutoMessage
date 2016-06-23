package com.j0ach1mmall3.freeautomessage.api;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 1/03/2016
 */
public abstract class PlayerBroadcaster extends Broadcaster {
    protected final String permission;

    public PlayerBroadcaster(String identifier, boolean random, int interval, List<String> messages, String permission) {
        super(identifier, random, interval, messages);
        this.permission = permission;
    }

    public final String getPermission() {
        return this.permission;
    }

    @Override
    protected final void broadcast(String message) {
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(p.hasPermission(this.permission)) this.broadcast(p, message);
        }
    }

    protected abstract void broadcast(Player p, String message);
}
