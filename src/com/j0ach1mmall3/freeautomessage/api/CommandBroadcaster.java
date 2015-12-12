package com.j0ach1mmall3.freeautomessage.api;

import com.j0ach1mmall3.jlib.methods.Random;
import org.bukkit.Bukkit;

import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 12/12/15
 */
public class CommandBroadcaster implements Broadcaster {
    private String identifier;
    private boolean random;
    private int interval;
    private List<String> commands;
    private int count = 0;

    public CommandBroadcaster(String identifier, boolean random, int interval, List<String> commands) {
        this.identifier = identifier;
        this.random = random;
        this.interval = interval;
        this.commands = commands;
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

    public int getInterval() {
        return this.interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public List<String> getCommands() {
        return this.commands;
    }

    public void setCommands(List<String> commands) {
        this.commands = commands;
    }

    public void broadcast() {
        int id;
        if(this.random) id = Random.getInt(this.commands.size());
        else {
            if(this.count >= this.commands.size()) this.count = 0;
            id = this.count;
            this.count++;
        }
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), this.commands.get(id));
    }
}
