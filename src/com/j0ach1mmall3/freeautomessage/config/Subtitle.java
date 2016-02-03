package com.j0ach1mmall3.freeautomessage.config;

import com.j0ach1mmall3.freeautomessage.BroadcastScheduler;
import com.j0ach1mmall3.freeautomessage.Main;
import com.j0ach1mmall3.freeautomessage.api.Broadcaster;
import com.j0ach1mmall3.freeautomessage.api.SubtitleBroadcaster;
import com.j0ach1mmall3.jlib.methods.General;
import com.j0ach1mmall3.jlib.methods.ReflectionAPI;
import com.j0ach1mmall3.jlib.storage.file.yaml.ConfigLoader;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/08/2015
 */
public class Subtitle extends ConfigLoader {
    private boolean enabled;
    private final List<Broadcaster> broadcasters;
    public Subtitle(Main plugin) {
        super("subtitle.yml", plugin);
        this.enabled = this.config.getBoolean("Enabled");
        if(this.enabled && !ReflectionAPI.verBiggerThan(1, 8)){
            if(plugin.getBabies().getLoggingLevel() >= 1) General.sendColoredMessage(plugin, "It seems that Subtitle Broadcasting is enabled in the config, however the server is running 1.7 or lower! Fixing that for you :)", ChatColor.RED);
            this.enabled = false;
        }
        this.broadcasters = this.getBroadcasters();
        if(this.enabled) {
            for(Broadcaster broadcaster : this.broadcasters) {
                new BroadcastScheduler(broadcaster).runTaskTimer(plugin, 0, broadcaster.getInterval());
            }
            if(plugin.getBabies().getLoggingLevel() >= 2) General.sendColoredMessage(plugin, "Started broadcasting Subtitle messages!", ChatColor.GREEN);
        }
        if(plugin.getBabies().getLoggingLevel() >= 2) General.sendColoredMessage(plugin, "Subtitle config successfully loaded!", ChatColor.GREEN);
    }

    private List<Broadcaster> getBroadcasters() {
        List<Broadcaster> broadcasters = new ArrayList<>();
        for(String s : this.customConfig.getKeys("SubtitleBroadcasters")) {
            broadcasters.add(this.getBroadcasterByIdentifier(s));
        }
        return broadcasters;
    }

    private SubtitleBroadcaster getBroadcasterByIdentifier(String identifier) {
        String path = "SubtitleBroadcasters." + identifier + ".";
        return new SubtitleBroadcaster(
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
