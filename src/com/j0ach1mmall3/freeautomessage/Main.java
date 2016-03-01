package com.j0ach1mmall3.freeautomessage;

import com.j0ach1mmall3.freeautomessage.commands.FAMCommandHandler;
import com.j0ach1mmall3.freeautomessage.config.Actionbar;
import com.j0ach1mmall3.freeautomessage.config.Bossbar;
import com.j0ach1mmall3.freeautomessage.config.Chat;
import com.j0ach1mmall3.freeautomessage.config.Command;
import com.j0ach1mmall3.freeautomessage.config.Config;
import com.j0ach1mmall3.freeautomessage.config.Signs;
import com.j0ach1mmall3.freeautomessage.config.Subtitle;
import com.j0ach1mmall3.freeautomessage.config.Tablist;
import com.j0ach1mmall3.freeautomessage.config.Title;
import com.j0ach1mmall3.jlib.integration.updatechecker.AsyncUpdateChecker;
import com.j0ach1mmall3.jlib.integration.updatechecker.UpdateCheckerResult;
import com.j0ach1mmall3.jlib.methods.General;
import com.j0ach1mmall3.jlib.methods.ReflectionAPI;
import com.j0ach1mmall3.jlib.storage.database.CallbackHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 18/08/2015
 */
public final class Main extends JavaPlugin {
    public static final String BUKKIT_VERSION = Bukkit.getBukkitVersion().split("\\-")[0];
    public static final String MINECRAFT_VERSION = ReflectionAPI.getNmsVersion();
    private Actionbar actionbar;
    private Bossbar bossBar;
    private Chat chat;
    private Command command;
    private Config config;
    private Signs signs;
    private Subtitle subtitle;
    private Tablist tablist;
    private Title title;
    @Override
    public void onEnable() {
        this.config = new Config(this);
        if(this.config.getLoggingLevel() >= 2 && !this.config.getUpdateChecker()) General.sendColoredMessage(this, "Update Checking is not enabled! You will not receive console notifications!", ChatColor.GOLD);
        if(this.config.getLoggingLevel() >= 2) General.sendColoredMessage(this, "Main config successfully loaded!", ChatColor.GREEN);
        if(this.config.getLoggingLevel() >= 2) General.sendColoredMessage(this, "You are running Bukkit version " + BUKKIT_VERSION + " (MC " + MINECRAFT_VERSION + ')', ChatColor.GOLD);
        if(this.config.getUpdateChecker()) {
            AsyncUpdateChecker checker = new AsyncUpdateChecker(this, 11191, this.getDescription().getVersion());
            checker.checkUpdate(new CallbackHandler<UpdateCheckerResult>() {
                @Override
                public void callback(UpdateCheckerResult o) {
                    switch (o.getType()) {
                        case NEW_UPDATE:
                            if(Main.this.config.getLoggingLevel() >= 1) {
                                General.sendColoredMessage(Main.this, "A new update is available!", ChatColor.GOLD);
                                General.sendColoredMessage(Main.this, "Version " + o.getNewVersion() + " (Current: " + Main.this.getDescription().getVersion() + ')', ChatColor.GOLD);
                            }
                            break;
                        case UP_TO_DATE:
                            if(Main.this.config.getLoggingLevel() >= 1) General.sendColoredMessage(Main.this, "You are up to date!", ChatColor.GREEN);
                            break;
                        case ERROR:
                            if(Main.this.config.getLoggingLevel() >= 1) General.sendColoredMessage(Main.this, "An error occured while trying to check for updates on spigotmc.org!", ChatColor.RED);
                            break;
                    }
                }
            });
        }
        if(this.config.getLoggingLevel() >= 1) General.sendColoredMessage(this, "Loading configs...", ChatColor.GREEN);
        this.actionbar = new Actionbar(this);
        this.bossBar = new Bossbar(this);
        this.chat = new Chat(this);
        this.command = new Command(this);
        this.signs = new Signs(this);
        this.subtitle = new Subtitle(this);
        this.tablist = new Tablist(this);
        this.title = new Title(this);
        if(this.config.getLoggingLevel() >= 1) General.sendColoredMessage(this, "Loaded all configs!", ChatColor.GREEN);
        if(this.config.getLoggingLevel() >= 1) General.sendColoredMessage(this, "Registering command...", ChatColor.GREEN);
        new FAMCommandHandler(this).registerCommand(new com.j0ach1mmall3.jlib.commands.Command(this, "FreeAutoMessage", Arrays.asList("reload", "addsign", "removesign", "listsigns"), "/fam reload, /fam addsign, /fam removesign, /fam listsigns", this.config.getNoPermissionMessage()));
        if(this.config.getLoggingLevel() >= 1) General.sendColoredMessage(this, "Done!", ChatColor.GREEN);
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
        this.bossBar.cleanup();
    }

    public void reload() {
        Bukkit.getScheduler().cancelTasks(this);
        this.bossBar.cleanup();
        this.actionbar = new Actionbar(this);
        this.bossBar = new Bossbar(this);
        this.chat = new Chat(this);
        this.command = new Command(this);
        this.signs = new Signs(this);
        this.subtitle = new Subtitle(this);
        this.tablist = new Tablist(this);
        this.title = new Title(this);
    }

    public Actionbar getActionbar() {
        return this.actionbar;
    }

    public Bossbar getBossBar() {
        return this.bossBar;
    }

    public Chat getChat() {
        return this.chat;
    }

    public Command getCommand() {
        return this.command;
    }

    public Config getBabies() {
        return this.config;
    }

    public Signs getSigns() {
        return this.signs;
    }

    public Subtitle getSubtitle() {
        return this.subtitle;
    }

    public Tablist getTablist() {
        return this.tablist;
    }

    public Title getTitle() {
        return this.title;
    }
}
