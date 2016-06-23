package com.j0ach1mmall3.freeautomessage.config;

import com.j0ach1mmall3.freeautomessage.Main;
import com.j0ach1mmall3.freeautomessage.api.CommandBroadcaster;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 12/12/15
 */
public final class Command extends BroadcasterConfig {
    public Command(Main plugin) {
        super("command.yml", plugin, "Command");
    }

    @Override
    protected CommandBroadcaster getBroadcasterByIdentifier(String identifier) {
        String path = "CommandBroadcasters." + identifier + '.';
        return new CommandBroadcaster(
                identifier,
                this.config.getBoolean(path + "Random"),
                this.config.getInt(path + "Interval"),
                this.config.getStringList(path + "Commands")
        );
    }
}
