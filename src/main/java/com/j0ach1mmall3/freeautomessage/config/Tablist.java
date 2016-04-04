package com.j0ach1mmall3.freeautomessage.config;

import com.j0ach1mmall3.freeautomessage.Main;
import com.j0ach1mmall3.freeautomessage.api.TablistBroadcaster;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/08/2015
 */
public class Tablist extends BroadcasterConfig {
    public Tablist(Main plugin) {
        super("tablist.yml", plugin, "Tablist");
    }

    @Override
    protected TablistBroadcaster getBroadcasterByIdentifier(String identifier) {
        String path = "TablistBroadcasters." + identifier + '.';
        return new TablistBroadcaster(
                (Main) this.getStorage().getPlugin(),
                identifier,
                this.config.getBoolean(path + "Random"),
                this.config.getInt(path + "Interval"),
                this.config.getStringList(path + "Messages"),
                this.config.getString(path + "Permission"),
                this.config.getStringList(path + "EnabledWorlds")

        );
    }
}
