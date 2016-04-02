package com.j0ach1mmall3.freeautomessage.api;


import com.j0ach1mmall3.freeautomessage.Main;
import com.j0ach1mmall3.jlib.integration.Placeholders;
import com.j0ach1mmall3.jlib.visual.JsonText;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 18/08/2015
 */
public final class ChatBroadcaster extends PlayerBroadcaster {
    public ChatBroadcaster(Main plugin, String identifier, boolean random, int interval, List<String> messages, List<String> enabledWorlds, String permission) {
        super(plugin, identifier, random, interval, messages, enabledWorlds, permission);
    }

    @Override
    protected void broadcastInternal(Player p, String message) {
        String[] splitted = message.split("\\|");
        for(String s : splitted) {
            if(this.plugin.getChat().isJson()) new JsonText(p, s).send();
            else p.sendMessage(Placeholders.parse(s, p));
        }
    }
}
