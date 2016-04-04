package com.j0ach1mmall3.freeautomessage.config;

import com.j0ach1mmall3.freeautomessage.Main;
import com.j0ach1mmall3.freeautomessage.api.Broadcaster;
import com.j0ach1mmall3.freeautomessage.api.ChatBroadcaster;
import com.j0ach1mmall3.jlib.logging.JLogger;
import com.j0ach1mmall3.jlib.methods.ReflectionAPI;
import org.bukkit.ChatColor;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/08/2015
 */
public final class Chat extends BroadcasterConfig {
    private final boolean json;
    public Chat(Main plugin) {
        super("chat.yml", plugin, "Chat");
        boolean json = this.config.getBoolean("Json");
        if(json && !ReflectionAPI.verBiggerThan(1, 7)){
            plugin.getjLogger().log(ChatColor.RED + "Json Chat formatting is enabled in the config, however the server is running 1.6 or lower. Adjusting that value.", JLogger.LogLevel.MINIMAL);
            json = false;
        }
        this.json = json;
    }

    @Override
    protected Broadcaster getBroadcasterByIdentifier(String identifier) {
        String path = "ChatBroadcasters." + identifier + '.';
        return new ChatBroadcaster(
                (Main) this.getStorage().getPlugin(),
                identifier,
                this.config.getBoolean(path + "Random"),
                this.config.getInt(path + "Interval"),
                this.config.getStringList(path + "Messages"),
                this.config.getString(path + "Permission"),
                this.config.getStringList(path + "EnabledWorlds")
        );
    }

    public boolean isJson() {
        return this.json;
    }
}
