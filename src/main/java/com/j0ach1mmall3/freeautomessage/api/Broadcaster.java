package com.j0ach1mmall3.freeautomessage.api;

import com.j0ach1mmall3.jlib.methods.Random;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 18/08/2015
 */
public abstract class Broadcaster implements Runnable {
    private final String identifier;
    private final boolean random;
    private final int interval;
    private final List<String> messages;
    private int count;

    public Broadcaster(String identifier, boolean random, int interval, List<String> messages) {
        this.identifier = identifier;
        this.random = random;
        this.interval = interval;
        this.messages = messages;
    }

    public final String getIdentifier() {
        return this.identifier;
    }

    public final boolean isRandom() {
        return this.random;
    }

    public final int getInterval() {
        return this.interval;
    }

    public final List<String> getMessages() {
        return this.messages;
    }

    @Override
    public final void run() {
        int id;
        if(this.random) id = Random.getInt(this.messages.size());
        else {
            if(this.count >= this.messages.size()) this.count = 0;
            id = this.count;
            this.count++;
        }
        this.broadcast(this.messages.get(id));
    }

    public final void start(Plugin plugin) {
        Bukkit.getScheduler().runTaskTimer(plugin, this, this.interval, this.interval);
    }

    protected abstract void broadcast(String message);
}
