package com.j0ach1mmall3.freeautomessage.config;

import com.j0ach1mmall3.freeautomessage.Main;
import com.j0ach1mmall3.freeautomessage.api.Broadcaster;
import com.j0ach1mmall3.jlib.logging.JLogger;
import com.j0ach1mmall3.jlib.methods.ReflectionAPI;
import com.j0ach1mmall3.jlib.storage.file.yaml.ConfigLoader;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 2/04/2016
 */
public abstract class BroadcasterConfig extends ConfigLoader<Main> {
    private final boolean enabled;
    protected final List<Broadcaster> broadcasters;

    protected BroadcasterConfig(String config, Main plugin, String broadcaster) {
        super(config, plugin);
        this.enabled = this.getEnabled(broadcaster);
        this.broadcasters = this.getBroadcasters(broadcaster);
        if(this.enabled) {
            for(Broadcaster b : this.broadcasters) {
                b.start(plugin);
            }
            plugin.getjLogger().log(ChatColor.GREEN + "Started " + broadcaster + " broadcasters!", JLogger.LogLevel.EXTENDED);
        }
        plugin.getjLogger().log(ChatColor.GREEN + "Loaded " + broadcaster + " config!", JLogger.LogLevel.NORMAL);
    }

    private List<Broadcaster> getBroadcasters(String section) {
        List<Broadcaster> broadcasters = new ArrayList<>();
        for(String s : this.customConfig.getKeys(section + "Broadcasters")) {
            broadcasters.add(this.getBroadcasterByIdentifier(s));
        }
        return broadcasters;
    }

    private boolean getEnabled(String broadcaster) {
        boolean enabled = this.config.getBoolean("Enabled");
        switch (broadcaster) {
            case "Actionbar":
                if(enabled && !ReflectionAPI.verBiggerThan(1, 8)) {
                    this.storage.getPlugin().getjLogger().log(ChatColor.RED + "Actionbar Broadcasting is enabled in the config, but you are running 1.7 or lower. Adjusting that value.", JLogger.LogLevel.MINIMAL);
                    enabled = false;
                }
                break;
            case "Bossbar":
                if(enabled && !ReflectionAPI.verBiggerThan(1, 9)) {
                    this.storage.getPlugin().getjLogger().log(ChatColor.RED + "Bossbar Broadcasting is enabled in the config, but you are running 1.8 or lower. Adjusting that value.", JLogger.LogLevel.MINIMAL);
                    enabled = false;
                }
                break;
            case "Title":
                if(enabled && !ReflectionAPI.verBiggerThan(1, 8)) {
                    this.storage.getPlugin().getjLogger().log(ChatColor.RED + "Title Broadcasting is enabled in the config, but you are running 1.7 or lower. Adjusting that value.", JLogger.LogLevel.MINIMAL);
                    enabled = false;
                }
                break;
            case "Tablist":
                if(enabled && !ReflectionAPI.verBiggerThan(1, 8)) {
                    this.storage.getPlugin().getjLogger().log(ChatColor.RED + "Tablist Broadcasting is enabled in the config, but you are running 1.7 or lower. Adjusting that value.", JLogger.LogLevel.MINIMAL);
                    enabled = false;
                }
                break;
            case "Subtitle":
                if(enabled && !ReflectionAPI.verBiggerThan(1, 8)) {
                    this.storage.getPlugin().getjLogger().log(ChatColor.RED + "Subtitle Broadcasting is enabled in the config, but you are running 1.7 or lower. Adjusting that value.", JLogger.LogLevel.MINIMAL);
                    enabled = false;
                }
                break;
        }
        return enabled;
    }

    protected abstract Broadcaster getBroadcasterByIdentifier(String identifier);
}
