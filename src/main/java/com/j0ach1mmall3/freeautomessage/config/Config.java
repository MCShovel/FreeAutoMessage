package com.j0ach1mmall3.freeautomessage.config;

import com.j0ach1mmall3.freeautomessage.Main;
import com.j0ach1mmall3.jlib.logging.JLogger;
import com.j0ach1mmall3.jlib.storage.file.yaml.ConfigLoader;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/08/2015
 */
public final class Config extends ConfigLoader {
    private final boolean updateChecker;
    private final JLogger.LogLevel logLevel;
    private final String noPermissionMessage;
    public Config(Main plugin) {
        super("config.yml", plugin);
        this.customConfig.saveDefaultConfig();
        this.logLevel = JLogger.LogLevel.valueOf(this.config.getString("LogLevel"));
        this.updateChecker = this.config.getBoolean("UpdateChecker");
        this.noPermissionMessage = this.config.getString("NoPermissionMessage");
    }

    public boolean isUpdateChecker() {
        return this.updateChecker;
    }

    public JLogger.LogLevel getLogLevel() {
        return this.logLevel;
    }

    public String getNoPermissionMessage() {
        return this.noPermissionMessage;
    }
}
