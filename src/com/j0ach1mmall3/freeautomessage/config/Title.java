package com.j0ach1mmall3.freeautomessage.config;

import com.j0ach1mmall3.freeautomessage.BroadcastScheduler;
import com.j0ach1mmall3.freeautomessage.Main;
import com.j0ach1mmall3.freeautomessage.api.TitleBroadcaster;
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
public class Title extends ConfigLoader {
    private boolean enabled;
    private final List<TitleBroadcaster> broadcasters;
    public Title(Main plugin) {
        super("title.yml", plugin);
        this.enabled = this.config.getBoolean("Enabled");
        if(this.enabled && !ReflectionAPI.verBiggerThan(1, 8)){
            if(plugin.getBabies().getLoggingLevel() >= 1) General.sendColoredMessage(plugin, "It seems that Title Broadcasting is enabled in the config, however the server is running 1.7 or lower! Fixing that for you :)", ChatColor.RED);
            this.enabled = false;
        }
        this.broadcasters = this.getBroadcasters();
        if(this.enabled) {
            this.broadcasters.forEach(broadcaster -> new BroadcastScheduler(broadcaster).runTaskTimer(plugin, 0, broadcaster.getInterval()));
            if(plugin.getBabies().getLoggingLevel() >= 2) General.sendColoredMessage(plugin, "Started broadcasting Title messages!", ChatColor.GREEN);
        }
        if(plugin.getBabies().getLoggingLevel() >= 2) General.sendColoredMessage(plugin, "Title config successfully loaded!", ChatColor.GREEN);
    }

    private List<TitleBroadcaster> getBroadcasters() {
        return this.customConfig.getKeys("TitleBroadcasters").stream().map(this::getBroadcasterByIdentifier).collect(Collectors.toList());
    }

    private TitleBroadcaster getBroadcasterByIdentifier(String identifier) {
        String path = "TitleBroadcasters." + identifier + ".";
        return new TitleBroadcaster(
                identifier,
                this.config.getBoolean(path + "Random"),
                this.config.getStringList(path + "EnabledWorlds"),
                this.config.getInt(path + "Interval"),
                this.config.getString(path + "Permission"),
                this.config.getInt(path + "FadeIn"),
                this.config.getInt(path + "Stay"),
                this.config.getInt(path + "FadeOut"),
                this.config.getStringList(path + "Messages")
        );
    }
}
