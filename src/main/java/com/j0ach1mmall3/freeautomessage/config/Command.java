package com.j0ach1mmall3.freeautomessage.config;

import com.j0ach1mmall3.freeautomessage.BroadcastScheduler;
import com.j0ach1mmall3.freeautomessage.Main;
import com.j0ach1mmall3.freeautomessage.api.Broadcaster;
import com.j0ach1mmall3.freeautomessage.api.CommandBroadcaster;
import com.j0ach1mmall3.jlib.methods.General;
import com.j0ach1mmall3.jlib.storage.file.yaml.ConfigLoader;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 12/12/15
 */
public final class Command extends ConfigLoader {
    private final boolean enabled;
    private final List<Broadcaster> broadcasters;
    public Command(Main plugin) {
        super("command.yml", plugin);
        this.enabled = this.config.getBoolean("Enabled");
        this.broadcasters = this.getBroadcasters();
        if(this.enabled) {
            for(Broadcaster broadcaster : this.broadcasters) {
                new BroadcastScheduler(broadcaster).runTaskTimer(plugin, 0, broadcaster.getInterval());
            }
            if(plugin.getBabies().getLoggingLevel() >= 2) General.sendColoredMessage(plugin, "Started broadcasting Command messages!", ChatColor.GREEN);
        }
        if(plugin.getBabies().getLoggingLevel() >= 2) General.sendColoredMessage(plugin, "Command config successfully loaded!", ChatColor.GREEN);
    }

    private List<Broadcaster> getBroadcasters() {
        List<Broadcaster> broadcasters = new ArrayList<>();
        for(String s : this.customConfig.getKeys("CommandBroadcasters")) {
            broadcasters.add(this.getBroadcasterByIdentifier(s));
        }
        return broadcasters;
    }

    private CommandBroadcaster getBroadcasterByIdentifier(String identifier) {
        String path = "CommandBroadcasters." + identifier + '.';
        return new CommandBroadcaster(
                identifier,
                this.config.getBoolean(path + "Random"),
                this.config.getInt(path + "Interval"),
                this.config.getStringList(path + "Commands")
        );
    }
}
