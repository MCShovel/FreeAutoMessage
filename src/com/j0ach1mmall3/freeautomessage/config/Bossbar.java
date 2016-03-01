package com.j0ach1mmall3.freeautomessage.config;

import com.j0ach1mmall3.freeautomessage.BroadcastScheduler;
import com.j0ach1mmall3.freeautomessage.Main;
import com.j0ach1mmall3.freeautomessage.api.BossbarBroadcaster;
import com.j0ach1mmall3.freeautomessage.api.Broadcaster;
import com.j0ach1mmall3.jlib.methods.General;
import com.j0ach1mmall3.jlib.storage.file.yaml.ConfigLoader;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/08/2015
 */
public final class Bossbar extends ConfigLoader {
    private final boolean enabled;
    private final List<Broadcaster> broadcasters;
    public Bossbar(Main plugin) {
        super("bossbar.yml", plugin);
        this.enabled = this.config.getBoolean("Enabled");
        this.broadcasters = this.getBroadcasters();
        if(this.enabled) {
            for(Broadcaster broadcaster : this.broadcasters) {
                new BroadcastScheduler(broadcaster).runTaskTimer(plugin, 0, broadcaster.getInterval());
            }
            if(plugin.getBabies().getLoggingLevel() >= 2) General.sendColoredMessage(plugin, "Started broadcasting Bossbar messages!", ChatColor.GREEN);
        }
        if(plugin.getBabies().getLoggingLevel() >= 2) General.sendColoredMessage(plugin, "Bossbar config successfully loaded!", ChatColor.GREEN);
    }

    private List<Broadcaster> getBroadcasters() {
        List<Broadcaster> broadcasters = new ArrayList<>();
        for(String s : this.customConfig.getKeys("BossbarBroadcasters")) {
            broadcasters.add(this.getBroadcasterByIdentifier(s));
        }
        return broadcasters;
    }

    private BossbarBroadcaster getBroadcasterByIdentifier(String identifier) {
        String path = "BossbarBroadcasters." + identifier + '.';
        return new BossbarBroadcaster(
                identifier,
                this.config.getBoolean(path + "Random"),
                this.config.getInt(path + "Interval"),
                this.config.getStringList(path + "Messages"),
                this.config.getStringList(path + "EnabledWorlds"),
                this.config.getString(path + "Permission")
        );
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void cleanup() {
        for(Broadcaster broadcaster : this.broadcasters) {
            ((BossbarBroadcaster) broadcaster).cleanup();
        }
    }
}
