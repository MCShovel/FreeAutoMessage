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
import com.j0ach1mmall3.jlib.logging.JLogger;
import com.j0ach1mmall3.jlib.methods.ReflectionAPI;
import com.j0ach1mmall3.jlib.plugin.JlibPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.Arrays;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 18/08/2015
 */
public final class Main extends JlibPlugin {
    public static final String BUKKIT_VERSION = Bukkit.getBukkitVersion().split("\\-")[0];
    public static final String MINECRAFT_VERSION = ReflectionAPI.getNmsVersion();
    private Actionbar actionbar;
    private Bossbar bossBar;
    private Chat chat;
    private Command command;
    private Signs signs;
    private Subtitle subtitle;
    private Tablist tablist;
    private Title title;

    @Override
    public void onEnable() {
        this.config = new Config(this);
        this.jLogger.setLogLevel(((Config) this.config).getLogLevel());
        if(((Config) this.config).isUpdateChecker()) this.jLogger.log(ChatColor.GOLD + "Update Checking is not enabled! You will not receive console notifications!", JLogger.LogLevel.NORMAL);
        this.jLogger.log(ChatColor.GREEN + "Main config successfully loaded!", JLogger.LogLevel.EXTENDED);
        this.jLogger.log(ChatColor.GREEN + "You are running Bukkit version " + BUKKIT_VERSION + " (MC " + MINECRAFT_VERSION + ')', JLogger.LogLevel.EXTENDED);
        if(((Config) this.config).isUpdateChecker()) this.checkUpdate(11191);
        this.jLogger.log(ChatColor.GREEN + "Loading configs...", JLogger.LogLevel.EXTENDED);
        this.actionbar = new Actionbar(this);
        this.bossBar = new Bossbar(this);
        this.chat = new Chat(this);
        this.command = new Command(this);
        this.signs = new Signs(this);
        this.subtitle = new Subtitle(this);
        this.tablist = new Tablist(this);
        this.title = new Title(this);
        this.jLogger.log(ChatColor.GREEN + "Loaded all configs!", JLogger.LogLevel.EXTENDED);
        this.jLogger.log(ChatColor.GREEN + "Registering command...", JLogger.LogLevel.EXTENDED);
        new FAMCommandHandler(this).registerCommand(new com.j0ach1mmall3.jlib.commands.Command(this, "FreeAutoMessage", Arrays.asList("reload", "addsign", "removesign", "listsigns"), "/fam reload, /fam addsign, /fam removesign, /fam listsigns", ((Config) this.config).getNoPermissionMessage()));
        this.jLogger.log(ChatColor.GREEN + "Done!", JLogger.LogLevel.NORMAL);
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
