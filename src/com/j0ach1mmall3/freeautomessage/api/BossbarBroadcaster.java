package com.j0ach1mmall3.freeautomessage.api;

import com.j0ach1mmall3.jlib.integration.Placeholders;
import com.j0ach1mmall3.jlib.methods.Parsing;
import com.j0ach1mmall3.jlib.methods.Random;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.inventivetalent.bossbar.BossBarAPI;

import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/08/2015
 */
public class BossbarBroadcaster implements Broadcaster {
    private String identifier;
    private boolean random;
    private List<String> enabledWorlds;
    private int interval;
    private String permission;
    private List<String> messages;
    private int count = 0;

    public BossbarBroadcaster(String identifier, boolean random, List<String> enabledWorlds, int interval, String permission, List<String> messages) {
        this.identifier = identifier;
        this.random = random;
        this.enabledWorlds = enabledWorlds;
        this.interval = interval;
        this.permission = permission;
        this.messages = messages;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public boolean getRandom() {
        return this.random;
    }

    public void setRandom(boolean random) {
        this.random = random;
    }

    public List<String> getEnabledWorlds() {
        return this.enabledWorlds;
    }

    public void setEnabledWorlds(List<String> enabledWorlds) {
        this.enabledWorlds = enabledWorlds;
    }

    public int getInterval() {
        return this.interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public String getPermission() {
        return this.permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public List<String> getMessages() {
        return this.messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public void broadcast() {
        int id;
        if(this.random) id = Random.getInt(this.messages.size());
        else {
            if(this.count >= this.messages.size()) this.count = 0;
            id = this.count;
            this.count++;
        }
        Bukkit.getOnlinePlayers().stream().filter(p -> p.hasPermission(this.permission) && this.enabledWorlds.contains(p.getWorld().getName())).forEach(p -> this.displayPlayer(p, this.messages.get(id)));
    }

    private void displayPlayer(Player p, String message) {
        BossBarAPI.removeBar(p);
        if(message.contains("|")) {
            String[] parts = message.split("\\|");
            BossBarAPI.setMessage(p, Placeholders.parse(parts[0], p), Parsing.parseFloat(parts[1]));
        } else BossBarAPI.setMessage(p, Placeholders.parse(message, p));
    }
}