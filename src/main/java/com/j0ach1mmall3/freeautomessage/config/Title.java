package com.j0ach1mmall3.freeautomessage.config;

import com.j0ach1mmall3.freeautomessage.Main;
import com.j0ach1mmall3.freeautomessage.api.TitleBroadcaster;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/08/2015
 */
public final class Title extends BroadcasterConfig {
    public Title(Main plugin) {
        super("title.yml", plugin, "Title");
    }

    @Override
    protected TitleBroadcaster getBroadcasterByIdentifier(String identifier) {
        String path = "TitleBroadcasters." + identifier + '.';
        return new TitleBroadcaster(
                (Main) this.getStorage().getPlugin(),
                identifier,
                this.config.getBoolean(path + "Random"),
                this.config.getInt(path + "Interval"),
                this.config.getStringList(path + "Messages"),
                this.config.getStringList(path + "EnabledWorlds"),
                this.config.getString(path + "Permission"),
                this.config.getInt(path + "FadeIn"),
                this.config.getInt(path + "Stay"),
                this.config.getInt(path + "FadeOut")
        );
    }
}
