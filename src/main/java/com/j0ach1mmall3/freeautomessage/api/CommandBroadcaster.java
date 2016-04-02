package com.j0ach1mmall3.freeautomessage.api;

import com.j0ach1mmall3.freeautomessage.Main;
import org.bukkit.Bukkit;

import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 12/12/15
 */
public final class CommandBroadcaster extends Broadcaster {
    public CommandBroadcaster(Main plugin, String identifier, boolean random, int interval, List<String> messages) {
        super(plugin, identifier, random, interval, messages);
    }

    @Override
    protected void broadcast(String message) {
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), message);
    }
}
