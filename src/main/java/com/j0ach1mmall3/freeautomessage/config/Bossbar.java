package com.j0ach1mmall3.freeautomessage.config;

import com.j0ach1mmall3.freeautomessage.Main;
import com.j0ach1mmall3.freeautomessage.api.BossbarBroadcaster;
import com.j0ach1mmall3.freeautomessage.api.Broadcaster;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/08/2015
 */
public final class Bossbar extends BroadcasterConfig {
    public Bossbar(Main plugin) {
        super("bossbar.yml", plugin, "Bossbar");
    }

    @Override
    protected Broadcaster getBroadcasterByIdentifier(String identifier) {
        String path = "BossbarBroadcasters." + identifier + '.';
        return new BossbarBroadcaster(
                (Main) this.getStorage().getPlugin(),
                identifier,
                this.config.getBoolean(path + "Random"),
                this.config.getInt(path + "Interval"),
                this.config.getStringList(path + "Messages"),
                this.config.getStringList(path + "EnabledWorlds"),
                this.config.getString(path + "Permission")
        );
    }

    public void cleanup() {
        for(Broadcaster broadcaster : this.broadcasters) {
            ((BossbarBroadcaster) broadcaster).cleanup();
        }
    }
}
