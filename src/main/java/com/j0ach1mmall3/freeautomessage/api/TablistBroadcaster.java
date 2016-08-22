package com.j0ach1mmall3.freeautomessage.api;

import com.j0ach1mmall3.jlib.visual.Tab;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/08/2015
 */
public final class TablistBroadcaster extends WorldsBroadcaster {
    public TablistBroadcaster(String identifier, boolean random, int interval, List<String> messages, String permission, List<String> enabledWorlds) {
        super(identifier, random, interval, messages, permission, enabledWorlds);
    }

    @Override
    protected void broadcastInternal( Player p, String message) {
        if(message.contains("|")) {
            String[] parts = message.split("\\|");
            new Tab(p, parts[0], parts[1]).send();
        } else new Tab(p, message, "").send();
    }
}
