package com.j0ach1mmall3.freeautomessage.config;

import com.j0ach1mmall3.freeautomessage.BroadcastScheduler;
import com.j0ach1mmall3.freeautomessage.Main;
import com.j0ach1mmall3.freeautomessage.api.BossbarBroadcaster;
import com.j0ach1mmall3.jlib.methods.General;
import com.j0ach1mmall3.jlib.storage.file.yaml.ConfigLoader;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.inventivetalent.bossbar.BossBarAPI;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/08/2015
 */
public class Bossbar extends ConfigLoader {
    private boolean enabled;
    private final List<BossbarBroadcaster> broadcasters;
    public Bossbar(Main plugin) {
        super("bossbar.yml", plugin);
        if(this.enabled && !plugin.getBossBarAPI()){
            if(plugin.getBabies().getLoggingLevel() >= 1) General.sendColoredMessage(plugin, "It seems that Bossbar Broadcasting is enabled in the config, but BossBarAPI isn't found! Fixing that for you :)", ChatColor.RED);
            this.enabled = false;
        }
        this.broadcasters = this.getBroadcasters();
        if(this.enabled) {
            Bukkit.getOnlinePlayers().forEach(BossBarAPI::removeBar);
            this.broadcasters.forEach(broadcaster -> new BroadcastScheduler(broadcaster).runTaskTimer(plugin, 0, broadcaster.getInterval()));
            if(plugin.getBabies().getLoggingLevel() >= 2) General.sendColoredMessage(plugin, "Started broadcasting Bossbar messages!", ChatColor.GREEN);
        }
        if(plugin.getBabies().getLoggingLevel() >= 2) General.sendColoredMessage(plugin, "Bossbar config successfully loaded!", ChatColor.GREEN);
    }

    private List<BossbarBroadcaster> getBroadcasters() {
        return this.customConfig.getKeys("BossbarBroadcasters").stream().map(this::getBroadcasterByIdentifier).collect(Collectors.toList());
    }

    private BossbarBroadcaster getBroadcasterByIdentifier(String identifier) {
        String path = "BossbarBroadcasters." + identifier + ".";
        return new BossbarBroadcaster(
                identifier,
                this.config.getBoolean(path + "Random"),
                this.config.getStringList(path + "EnabledWorlds"),
                this.config.getInt(path + "Interval"),
                this.config.getString(path + "Permission"),
                this.config.getStringList(path + "Messages")
        );
    }

    public boolean isEnabled() {
        return this.enabled;
    }
}
