package com.j0ach1mmall3.freeautomessage.config;

import com.j0ach1mmall3.freeautomessage.Main;
import com.j0ach1mmall3.freeautomessage.api.Broadcaster;
import com.j0ach1mmall3.freeautomessage.api.ChatBroadcaster;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/08/2015
 */
public final class Chat extends BroadcasterConfig {
    public Chat(Main plugin) {
        super("chat.yml", plugin, "Chat");
    }

    @Override
    protected Broadcaster getBroadcasterByIdentifier(String identifier) {
        String path = "ChatBroadcasters." + identifier + '.';
        return new ChatBroadcaster(
                identifier,
                this.config.getBoolean(path + "Random"),
                this.config.getInt(path + "Interval"),
                this.config.getStringList(path + "Messages"),
                this.config.getString(path + "Permission"),
                this.config.getStringList(path + "EnabledWorlds"),
                this.config.getBoolean(path + "Json")
        );
    }
}
