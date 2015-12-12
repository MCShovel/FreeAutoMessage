package com.j0ach1mmall3.freeautomessage.config;

import com.j0ach1mmall3.freeautomessage.BroadcastScheduler;
import com.j0ach1mmall3.freeautomessage.Main;
import com.j0ach1mmall3.freeautomessage.api.ChatBroadcaster;
import com.j0ach1mmall3.jlib.methods.General;
import com.j0ach1mmall3.jlib.methods.ReflectionAPI;
import com.j0ach1mmall3.jlib.storage.file.yaml.ConfigLoader;
import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/08/2015
 */
public class Chat extends ConfigLoader {
    private final boolean enabled;
    private boolean json;
    private final List<ChatBroadcaster> broadcasters;
    public Chat(Main plugin) {
        super("chat.yml", plugin);
        this.enabled = this.config.getBoolean("Enabled");
        this.json = this.config.getBoolean("Json");
        if(this.json && !ReflectionAPI.verBiggerThan(1, 7)){
            if(plugin.getBabies().getLoggingLevel() >= 1) General.sendColoredMessage(plugin, "It seems that Json Chat formatting is enabled in the config, however the server is running 1.6 or lower! Fixing that for you :)", ChatColor.RED);
            this.json = false;
        }
        this.broadcasters = this.getBroadcasters();
        if(this.enabled) {
            this.broadcasters.forEach(broadcaster -> new BroadcastScheduler(broadcaster).runTaskTimer(plugin, 0, broadcaster.getInterval()));
            if(plugin.getBabies().getLoggingLevel() >= 2) General.sendColoredMessage(plugin, "Started broadcasting Chat messages!", ChatColor.GREEN);
        }
        if(plugin.getBabies().getLoggingLevel() >= 2) General.sendColoredMessage(plugin, "Chat config successfully loaded!", ChatColor.GREEN);
    }

    private List<ChatBroadcaster> getBroadcasters() {
        return this.customConfig.getKeys("ChatBroadcasters").stream().map(this::getBroadcasterByIdentifier).collect(Collectors.toList());
    }

    private ChatBroadcaster getBroadcasterByIdentifier(String identifier) {
        String path = "ChatBroadcasters." + identifier + ".";
        return new ChatBroadcaster(
                identifier,
                this.config.getBoolean(path + "Random"),
                this.config.getStringList(path + "EnabledWorlds"),
                this.config.getInt(path + "Interval"),
                this.config.getString(path + "Permission"),
                this.config.getStringList(path + "Messages"),
                this.json
        );
    }
}
