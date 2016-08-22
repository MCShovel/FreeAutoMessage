package com.j0ach1mmall3.freeautomessage.api;

import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/04/2016
 */
public abstract class WorldsBroadcaster extends PlayerBroadcaster {
    private final List<String> enabledWorlds;

    protected WorldsBroadcaster(String identifier, boolean random, int interval, List<String> messages, String permission, List<String> enabledWorlds) {
        super(identifier, random, interval, messages, permission);
        this.enabledWorlds = enabledWorlds;
    }

    public List<String> getEnabledWorlds() {
        return this.enabledWorlds;
    }

    @Override
    protected final void broadcast(Player p, String message) {
        if(this.enabledWorlds.contains(p.getWorld().getName())) this.broadcastInternal(p, message);
    }

    protected abstract void broadcastInternal(Player p, String message);
}
