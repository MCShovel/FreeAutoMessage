package com.j0ach1mmall3.freeautomessage.config;

import com.j0ach1mmall3.freeautomessage.Main;
import com.j0ach1mmall3.jlib.storage.file.yaml.ConfigLoader;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/08/2015
 */
public class Config extends ConfigLoader {
    private final boolean updateChecker;
    private final int loggingLevel;
    private final String noPermissionMessage;
    public Config(Main plugin) {
        super("config.yml", plugin);
        this.customConfig.saveDefaultConfig();
        this.loggingLevel = this.config.getInt("LoggingLevel");
        this.updateChecker = this.config.getBoolean("UpdateChecker");
        this.noPermissionMessage = this.config.getString("NoPermissionMessage");
    }

    public boolean getUpdateChecker() {
        return this.updateChecker;
    }

    public int getLoggingLevel() {
        return this.loggingLevel;
    }

    public String getNoPermissionMessage() {
        return this.noPermissionMessage;
    }
}
