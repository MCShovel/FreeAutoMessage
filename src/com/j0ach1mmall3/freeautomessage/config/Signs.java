package com.j0ach1mmall3.freeautomessage.config;

import com.j0ach1mmall3.freeautomessage.BroadcastScheduler;
import com.j0ach1mmall3.freeautomessage.Main;
import com.j0ach1mmall3.freeautomessage.api.Broadcaster;
import com.j0ach1mmall3.freeautomessage.api.Sign;
import com.j0ach1mmall3.freeautomessage.api.SignsBroadcaster;
import com.j0ach1mmall3.jlib.methods.General;
import com.j0ach1mmall3.jlib.storage.file.yaml.ConfigLoader;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/08/2015
 */
public final class Signs extends ConfigLoader {
    private static final List<String> ALL_WORLDS = new ArrayList<>();

    static {
        for(World w : Bukkit.getWorlds()) {
            ALL_WORLDS.add(w.getName());
        }
    }

    private final boolean enabled;
    private final List<Broadcaster> broadcasters;
    public Signs(Main plugin) {
        super("signs.yml", plugin);
        this.enabled = this.config.getBoolean("Enabled");
        this.broadcasters = this.getBroadcasters();
        if(this.enabled) {
            for(Broadcaster broadcaster : this.broadcasters) {
                new BroadcastScheduler(broadcaster).runTaskTimer(plugin, 0, broadcaster.getInterval());
            }
            if(plugin.getBabies().getLoggingLevel() >= 2) General.sendColoredMessage(plugin, "Started broadcasting Signs messages!", ChatColor.GREEN);
        }
        if(plugin.getBabies().getLoggingLevel() >= 2) General.sendColoredMessage(plugin, "Signs config successfully loaded!", ChatColor.GREEN);
    }

    private List<Broadcaster> getBroadcasters() {
        List<Broadcaster> broadcasters = new ArrayList<>();
        for(String s : this.customConfig.getKeys("SignsBroadcasters")) {
            broadcasters.add(this.getBroadcasterByIdentifier(s));
        }
        return broadcasters;
    }

    private SignsBroadcaster getBroadcasterByIdentifier(String identifier) {
        String path = "SignsBroadcasters." + identifier + '.';
        return new SignsBroadcaster(
                identifier,
                this.config.getBoolean(path + "Random"),
                this.config.getInt(path + "Interval"),
                this.config.getStringList(path + "Messages"),
                ALL_WORLDS,
                this.config.getString(path + "Permission"),
                this.config.getStringList(path + "Signs")
        );
    }

    public void addSign(Sign sign) {
        List<String> signs = this.config.getStringList("SignsBroadcasters." + sign.getBroadcasterIdentifier() + "Signs");
        signs.add(this.serializeLocation(sign.getLocation()));
        this.config.set("SignsBroadcasters." + sign.getBroadcasterIdentifier() + ".Signs", signs);
        this.customConfig.saveConfig(this.config);
    }

    public void removeSign(Sign sign) {
        List<String> signs = this.config.getStringList("SignsBroadcasters." + sign.getBroadcasterIdentifier() + "Signs");
        signs.remove(this.serializeLocation(sign.getLocation()));
        this.config.set("SignsBroadcasters." + sign.getBroadcasterIdentifier() + ".Signs", signs);
        this.customConfig.saveConfig(this.config);
    }

    public List<String> listSigns(String broadcaster) {
        return this.config.getStringList("SignsBroadcasters." + broadcaster + ".Signs");
    }

    public boolean existsSign(Sign sign) {
        List<String> signs = this.config.getStringList("SignsBroadcasters." + sign.getBroadcasterIdentifier() + "Signs");
        return signs.contains(this.serializeLocation(sign.getLocation()));
    }

    public boolean isSignsBroadcaster(String identifier) {
        return this.customConfig.getKeys("SignsBroadcasters").contains(identifier);
    }

    private String serializeLocation(Location l) {
        return l.getWorld().getName() + '/' + String.valueOf(l.getBlockX()) + '/' + String.valueOf(l.getBlockY()) + '/' + String.valueOf(l.getBlockZ());
    }
}
