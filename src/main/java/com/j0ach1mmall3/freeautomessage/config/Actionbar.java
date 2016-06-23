package com.j0ach1mmall3.freeautomessage.config;

import com.j0ach1mmall3.freeautomessage.Main;
import com.j0ach1mmall3.freeautomessage.api.ActionbarBroadcaster;
import com.j0ach1mmall3.freeautomessage.api.Broadcaster;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/08/2015
 */
public final class Actionbar extends BroadcasterConfig {
    public Actionbar(Main plugin) {
        super("actionbar.yml", plugin, "Actionbar");
    }

    @Override
    protected Broadcaster getBroadcasterByIdentifier(String identifier) {
        String path = "ActionbarBroadcasters." + identifier + '.';
        return new ActionbarBroadcaster(
                identifier,
                this.config.getBoolean(path + "Random"),
                this.config.getInt(path + "Interval"),
                this.config.getStringList(path + "Messages"),
                this.config.getString(path + "Permission"),
                this.config.getStringList(path + "EnabledWorlds")
        );
    }
}
