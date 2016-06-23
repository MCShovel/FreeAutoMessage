package com.j0ach1mmall3.freeautomessage.api;


import com.j0ach1mmall3.jlib.integration.Placeholders;
import com.j0ach1mmall3.jlib.visual.JsonText;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 18/08/2015
 */
public final class ChatBroadcaster extends WorldsBroadcaster {
    private final boolean json;

    public ChatBroadcaster(String identifier, boolean random, int interval, List<String> messages, String permission, List<String> enabledWorlds, boolean json) {
        super(identifier, random, interval, messages, permission, enabledWorlds);
        this.json = json;
    }

    @Override
    protected void broadcastInternal(Player p, String message) {
        String[] splitted = message.split("\\|");
        for(String s : splitted) {
            if(this.json) new JsonText(p, s).send();
            else p.sendMessage(Placeholders.parse(s, p));
        }
    }
}
