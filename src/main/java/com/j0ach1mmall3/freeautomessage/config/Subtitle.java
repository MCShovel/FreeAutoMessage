package com.j0ach1mmall3.freeautomessage.config;

import com.j0ach1mmall3.freeautomessage.Main;
import com.j0ach1mmall3.freeautomessage.api.SubtitleBroadcaster;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/08/2015
 */
public final class Subtitle extends BroadcasterConfig {
    public Subtitle(Main plugin) {
        super("subtitle.yml", plugin, "Subtitle");
    }

    @Override
    protected SubtitleBroadcaster getBroadcasterByIdentifier(String identifier) {
        String path = "SubtitleBroadcasters." + identifier + '.';
        return new SubtitleBroadcaster(
                identifier,
                this.config.getBoolean(path + "Random"),
                this.config.getInt(path + "Interval"),
                this.config.getStringList(path + "Messages"),
                this.config.getString(path + "Permission"),
                this.config.getStringList(path + "EnabledWorlds"),
                this.config.getInt(path + "FadeIn"),
                this.config.getInt(path + "Stay"),
                this.config.getInt(path + "FadeOut")
        );
    }
}
