package com.j0ach1mmall3.freeautomessage.api;

import com.j0ach1mmall3.freeautomessage.Main;
import com.j0ach1mmall3.jlib.visual.Actionbar;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 18/08/2015
 */
public final class ActionbarBroadcaster extends WorldsBroadcaster {
    public ActionbarBroadcaster(Main plugin, String identifier, boolean random, int interval, List<String> messages, String permission, List<String> enabledWorlds) {
        super(plugin, identifier, random, interval, messages, permission, enabledWorlds);
    }

    @Override
    protected void broadcastInternal(Player p, String message) {
        new Actionbar(p, message).send();
    }
}
